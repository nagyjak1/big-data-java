import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        /*Path path = Paths.get("/Users/jakub/Desktop/DataLake");
        Crawler crawler = new Crawler(path);

        URL url = new URL("https://www.gutenberg.org/cache/epub/84/pg84-images.html");
        crawler.downloadContent(url);
        */

        Path path = Paths.get("/Users/jakub/Desktop/SearchEngine/Crawler/data");
        DataLakeManager dataLakeManager = new DataLakeManager(path, 100000, 100);


        //dataLakeManager.increaseCapacity();
    }
}
