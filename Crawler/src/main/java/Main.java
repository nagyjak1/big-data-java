import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        DatalakeManager datalakeManager = new DatalakeManager();
        datalakeManager.build("Crawler/datalake");

        String directory = new DateTreeDirectoryBuider().getPath("Crawler/datalake");

        BookDownloader bookDownloader = new BookDownloader();
        bookDownloader.bookDownloader("Crawler/datalake");
    }

}
