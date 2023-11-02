import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FolderManager {

    public void createLibrary() {

        File libraryFolder = new File("Indexer/Library");
        File metadataFolder = new File("Indexer/Library/Metadata");
        File contentFolder = new File("Indexer/Library/Content");

        if (!libraryFolder.exists()) {
            libraryFolder.mkdir();
        }
        if (!metadataFolder.exists()) {
            metadataFolder.mkdir();
        }
        if (!contentFolder.exists()) {
            contentFolder.mkdir();
        }
    }

    public String getContentPath() {
        return "Indexer/Library/Content";
    }

    public String getMetadataPath() {
        return "Indexer/Library/Metadata";
    }

    public void deleteLibrary() {
        File folder = new File("Indexer/Library/");
        try {
            FileUtils.deleteDirectory(folder);
            System.out.println("Folder deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete the folder: " + e.getMessage());
        }
    }
}
