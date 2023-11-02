import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
    private final FilterMeaningfullWords meaningfullWords = new FilterMeaningfullWords();
    public String getBookId (Path file){
        String fileNameWithExtension = file.getFileName().toString();
        String regex = "pg(\\d+)\\.txt";
        String bookId = fileNameWithExtension.replaceAll(regex, "$1");
        return bookId;
    }
    public String getBookTitle (Path file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            return null;
        }
    }

    public Map<String, Integer> wordTokenizer (Path file) throws IOException {
        String fileContent = new String(Files.readAllBytes(file), "UTF-8");
        Scanner scanner = new Scanner(fileContent);
        Map<String, Integer> words = new HashMap();
        scanner.useDelimiter("[^a-zA-Z]+");
        while (scanner.hasNext()) {
            String word = scanner.next();
            word = word.toLowerCase();
            addToDictionary(word, words);
        }
        scanner.close();
        return words;
    }

    private void addToDictionary(String word, Map < String, Integer > words){
        if (meaningfullWords.isMeaningfulWord(word)) {
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }

    }
}
