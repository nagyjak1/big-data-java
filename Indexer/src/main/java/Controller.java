import org.example.fileManager.FileManager;

import java.io.IOException;

public class Controller {
    public void controller(){
        DatamartManager datamartManager = new DatamartManager();
        datamartManager.createDatamart();
        FileManager fileManager = new FileManager();
        fileManager.processFilesInFolder("src/main/resources/datalake");
        Indexer indexer = new Indexer();
        try {
            indexer.invertedIndex("src/main/resources/separated/content");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
