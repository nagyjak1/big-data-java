import datamarthandler.DatamartManager;
import indexer.Indexer;
import library.filemanagers.ContentFileManager;
import library.FolderManager;
import library.filemanagers.MetadataFileManager;
import library.metadata.Metadata;
import library.metadata.MetadataBuilder;
import paths.DatePathBuilder;
import paths.PathsProvider;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Controller {
    private final String root = "Crawler/datalake/";
    private final DatamartManager datamartManager = new DatamartManager();
    private final FolderManager folderManager = new FolderManager();
    private final PathsProvider pathsProvider = new PathsProvider();
    private final ContentFileManager contentFileManager = new ContentFileManager();
    private final MetadataFileManager metadataFileManager = new MetadataFileManager();
    private final Indexer indexer = new Indexer();

    public void execute() throws InterruptedException {
        datamartManager.createDatamart();
        folderManager.createLibrary();
        buildLibrary();
        sleep(10000);
        runIndexer();
        folderManager.deleteLibrary();
        System.out.println("The Indexing has been done.");
    }

    private void runIndexer() throws InterruptedException {
        try {
            indexer.invertedIndex(folderManager.getContentPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildLibrary() {
        try {
            for (String path : pathsProvider.provideAll(new DatePathBuilder().getPath(root))) {
                Metadata metadata = new MetadataBuilder().build(path);
                if (metadata.isInEnglish()) {
                    metadataFileManager.separate(new File(path));
                    contentFileManager.separate(new File(path));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
