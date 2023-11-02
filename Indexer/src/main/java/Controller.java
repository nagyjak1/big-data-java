import java.io.File;
import java.io.IOException;

public class Controller {
    public void controller(){
        DatamartManager datamartManager = new DatamartManager();
        datamartManager.createDatamart();

        FolderManager folderManager = new FolderManager();
        folderManager.createLibrary();

        PathsProvider pathsProvider = new PathsProvider();

        ContentFileManager contentFileManager = new ContentFileManager();
        MetadataFileManager metadataFileManager = new MetadataFileManager();

        try {
            for (String path : pathsProvider.provideAll("/Users/haito/ULPGC/TERCERO/BigData/Final Project/SearchEngine/big-data-java/Crawler/datalake/2023/11/02/")){
                metadataFileManager.separate(new File(path));
                contentFileManager.separate(new File(path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Indexer indexer = new Indexer();
        try {
            indexer.invertedIndex(folderManager.getContentPath(), folderManager.getMetadataPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}