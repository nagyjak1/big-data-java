package library;

import org.apache.commons.io.FileUtils;
import paths.PathsProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FolderManager {

    private final File libraryFolder = new File("Indexer/Library");
    private final File metadataFolder = new File("Indexer/Library/Metadata");
    private final File contentFolder = new File("Indexer/Library/Content");

    public void createLibrary() {
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
        return String.valueOf(contentFolder);
    }

    public String getMetadataPath() {
        return String.valueOf(metadataFolder);
    }
}
