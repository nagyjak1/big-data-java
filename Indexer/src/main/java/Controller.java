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
import java.nio.file.Path;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Controller {
    private final String root = "Crawler/datalake/";
    private final DatamartManager datamartManager = new DatamartManager();
    private final FolderManager folderManager = new FolderManager();
    private final PathsProvider pathsProvider = new PathsProvider();
    private final ContentFileManager contentFileManager = new ContentFileManager();
    private final MetadataFileManager metadataFileManager = new MetadataFileManager();
    private final Indexer indexer = new Indexer();

    public void execute(Set<Integer> set) throws InterruptedException {
        datamartManager.createDatamart();
        folderManager.createLibrary();
        buildLibrary(set);
        sleep(10000);
        runIndexer();
        System.out.println("The Indexing has been done.");
    }

    private void runIndexer() throws InterruptedException {
        try {
            indexer.invertedIndex(folderManager.getContentPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildLibrary(Set<Integer> set) {
        try {
            for (String path : pathsProvider.provideAll(new DatePathBuilder().getPath(root))) {
                if (!check(path, set)) continue;
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

    private boolean check(String path, Set<Integer> set) {
        Path dir = Path.of(path);
        String book = String.valueOf(dir.getFileName());
        int id = Integer.parseInt(book.replace("book", "").replace(".txt", ""));
        if (!set.contains(id)) {
            set.add(id);
            return true;
        }
        return false;
    }
}
