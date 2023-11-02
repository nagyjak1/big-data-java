import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Indexer {

    //TODO: Hacer que se adapte al datalake

    private DatamartManager datamartManager = new DatamartManager();
    private FileReader fileReader = new FileReader();

    public void invertedIndex(String datalakePath) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(datalakePath))) {
            for (Path file : directoryStream) {
                if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                    String bookId = fileReader.getBookId(file);
                    String bookTitle = fileReader.getBookTitle(file);
                    Map<String, Integer> words = fileReader.wordTokenizer(file);
                    datamartManager.addWordToDatamart(words, bookId, bookTitle);
                }
            }
        }
    }
}



