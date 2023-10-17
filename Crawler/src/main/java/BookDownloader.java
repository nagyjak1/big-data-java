import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BookDownloader {
    public static final String bookUrl = "https://www.gutenberg.org/cache/epub/";
    public static final String page = "/pg";
    public static final String extension = ".txt";
    public static final String directory = "Crawler/src/main/resources/datalake";

    private void bookDownloader(int booksNumber, String directory) throws IOException {
        for (int i = 1; i < booksNumber; i++) {
            URL url = new URL(bookUrl + i + page + i + extension);
            File file = new File(directory + "/book" + i + extension);
            FileUtils.copyURLToFile(url, file);
        }
    }

    public static void main(String[] args) throws IOException {
        new BookDownloader().bookDownloader(10, directory);
    }
}
