import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class BookDownloader {
    private final int MAX_ITERS = 11;

    private final String bookUrl = "https://www.gutenberg.org/cache/epub/";
    private final String page = "/pg";
    private final String extension = ".txt";

    public void downloadInto(String directory, int randInt) throws IOException {
        String bookName = "book" + randInt + extension;
        String finalDirectory = buildDirectory(randInt, directory) + bookName;
        URL url = new URL(bookUrl + randInt + page + randInt + extension);
        File file = new File(finalDirectory);
        FileUtils.copyURLToFile(url, file);
        System.out.println("*** Downloaded " + bookName + " into " + finalDirectory);
    }

    private String buildDirectory(int randInt, String directory) {
        int lastDigit = Integer.parseInt(String.valueOf(randInt).
                substring(String.valueOf(randInt).length() - 1));
        return directory + "/" + lastDigit + "/";
    }
}
