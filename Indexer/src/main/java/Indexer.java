import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Indexer {
    private final DatamartManager datamartManager = new DatamartManager();
    private final FileReader fileReader = new FileReader();

    public void invertedIndex(String contentPath) throws IOException, InterruptedException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(contentPath))) {
            for (Path file : directoryStream) {
                if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                    String bookId = fileReader.getBookId(file);
                    String bookTitle = getTitle(new FolderManager().getMetadataPath() + "/" + getFileName(file));
                    Map<String, Integer> words = fileReader.wordTokenizer(file);
                    datamartManager.addWordToDatamart(words, bookId, bookTitle);
                }
            }
        }
    }

    public String getTitle(String metadataPath) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(metadataPath))) {
            for (Path file : directoryStream) {
                if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                    return fileReader.getBookTitle(file);
                }
            }
        }
        return null;
    }

    private String getFileName(Path file) {
        return file.getFileName().toString();
    }
}



