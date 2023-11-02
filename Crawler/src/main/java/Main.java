import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new DatalakeManager().build("Crawler/datalake");

        String directory = new DateTreeDirectoryBuider().getPath("Crawler/datalake");
        new BookDownloader().downloadInto(directory);
    }
}
