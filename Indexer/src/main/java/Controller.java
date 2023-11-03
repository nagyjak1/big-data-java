import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Controller {
    private final String root = "Crawler/datalake/";

    public void controller() throws InterruptedException {
        DatamartManager datamartManager = new DatamartManager();
        datamartManager.createDatamart();

        FolderManager folderManager = new FolderManager();
        folderManager.createLibrary();

        PathsProvider pathsProvider = new PathsProvider();

        ContentFileManager contentFileManager = new ContentFileManager();
        MetadataFileManager metadataFileManager = new MetadataFileManager();

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
        sleep(10000);
        Indexer indexer = new Indexer();
        try {
            indexer.invertedIndex(folderManager.getContentPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        folderManager.deleteLibrary();
    }
}
