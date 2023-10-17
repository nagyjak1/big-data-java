import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("Crawler/data");
        Path path = Paths.get(file.getPath());
        DataLakeManager dataLakeManager = new DataLakeManager(path, 1000, 100);
    }
}
