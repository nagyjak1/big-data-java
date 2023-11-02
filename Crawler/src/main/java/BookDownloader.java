import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class BookDownloader {
    private final int MAX_BOOKS = 70_000;
    private final int MAX_ITERS = 11;

    private final String bookUrl = "https://www.gutenberg.org/cache/epub/";
    private final String page = "/pg";
    private final String extension = ".txt";

    public void downloadInto(String directory) throws IOException {
        for (int i = 1; i < MAX_ITERS; i++) {
            int randInt = new Random().nextInt(MAX_BOOKS);
            String bookName = "book" + randInt + extension;
            String finalDirectory = buildDirectory(randInt, directory) + bookName;
            URL url = new URL(bookUrl + randInt + page + randInt + extension);
            File file = new File(finalDirectory);
            try {
                FileUtils.copyURLToFile(url, file);
            } catch (IOException e) {
                continue;
            }
        }
    }

    private String buildDirectory(int randInt, String directory) {
        int lastDigit = Integer.parseInt(String.valueOf(randInt).
                substring(String.valueOf(randInt).length() - 1));
        return directory + "/" + lastDigit + "/";
    }
}
