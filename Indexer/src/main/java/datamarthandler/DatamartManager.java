package datamarthandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class DatamartManager {

    private static final String DATAMART_PATH = "Indexer/datamart/";

    public void createDatamart() {
        File datamart = new File(DATAMART_PATH);
        if (!datamart.exists()) {
            datamart.mkdirs();
        }
    }

    public void addWordToDatamart(Map<String, Integer> words, String bookTitle, String bookId) throws IOException {
        for (String word : words.keySet()) {
            String FolderPath = getFolderPath(word);
            Files.createDirectories(Paths.get(FolderPath));
            String wordFilePath = Paths.get(FolderPath, word + ".txt").toAbsolutePath().toString();
            createFile(wordFilePath);
            writeFile(wordFilePath, bookTitle, bookId, words.get(word).toString());
        }
    }

    private static String getFolderPath(String word) {
        String firstLetter = word.substring(0, 1).toLowerCase();
        String secondLetter = word.substring(1, 2).toLowerCase();
        String firstLetterFolderPath = Paths.get(DATAMART_PATH, firstLetter).toString();
        return Paths.get(firstLetterFolderPath, secondLetter).toString();
    }

    private static void createFile(String filePath) {
        try {
            File myObj = new File(filePath);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeFile(String filePath, String bookTitle, String bookId, String appearances) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            String content = contentBuilder(bookTitle, bookId, appearances);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String contentBuilder(String bookTitle, String bookId, String appearances) {
        return bookTitle + ";" + bookId + ";" + appearances + "\n";
    }
}
