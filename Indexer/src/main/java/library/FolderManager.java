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
            System.out.println("Se creó metadata");
        }
        if (!contentFolder.exists()) {
            contentFolder.mkdir();
            System.out.println("Se creó content");
        }
    }

    public String getContentPath() {
        return String.valueOf(contentFolder);
    }

    public String getMetadataPath() {
        return String.valueOf(metadataFolder);
    }

    public void deleteLibrary() {
        deleteFolder(metadataFolder);
        deleteFolder(contentFolder);
        deleteFolder(libraryFolder);
    }

    private void deleteFile(String path) throws IOException {
        List<String> paths = new PathsProvider().provideAll(path);
        for (String e : paths){
            File file = new File(e);
            file.delete();
        }
    }

    private void deleteFolder(File contentFolder) {
        try {
            deleteFile(String.valueOf(contentFolder));
            FileUtils.deleteDirectory(contentFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
