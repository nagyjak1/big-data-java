import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class BookDownloader {
    private final int MAX_BOOKS = 70_000;
    private final int MAX_ITERS = 10;

    private final String bookUrl = "https://www.gutenberg.org/cache/epub/";
    private final String page = "/pg";
    private final String extension = ".txt";

    public void bookDownloader(String directory) throws IOException {
        for (int i = 1; i < MAX_ITERS; i++) {
            int randInt = new Random().nextInt(MAX_BOOKS);
            URL url = new URL(bookUrl + randInt + page + randInt + extension);
            File file = new File(directory + "/book" + randInt + extension);
            FileUtils.copyURLToFile(url, file);
        }
    }

    private void parseDirectory(int randInt, String directory) {

    }
}
