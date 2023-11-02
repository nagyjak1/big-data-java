import java.io.File;

public class FolderManager {

    public FolderManager() {
    }

    public void createLibrary(){

        File separatedFolder = new File("Indexer/Library");
        File metadataFolder = new File("Indexer/Library/Metadata");
        File contentFolder = new File("Indexer/Library/Content");

        if (!separatedFolder.exists()) {
            separatedFolder.mkdir();
        }
        if (!metadataFolder.exists()) {
            metadataFolder.mkdir();
        }
        if (!contentFolder.exists()) {
            contentFolder.mkdir();
        }
    }

    public String getContentPath(){
        return "Indexer/Library/Content";
    }

    public String getMetadataPath(){
        return "Indexer/Library/Metadata";
    }

}
