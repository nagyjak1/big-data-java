import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/Users/jakub/Desktop/SearchEngine/Crawler/data");
        DataLakeManager dataLakeManager = new DataLakeManager(path, 10000, 100);

        dataLakeManager.createFile(1023);

    }
}
