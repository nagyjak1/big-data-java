import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/Users/jakub/Desktop/SearchEngine/Crawler/data");
        DataLakeManager dataLakeManager = new DataLakeManager(path, 10000, 100);

        System.out.println(dataLakeManager.calculateCapacity());

        dataLakeManager.increaseCapacity();

        System.out.println(dataLakeManager.calculateCapacity());
    }
}
