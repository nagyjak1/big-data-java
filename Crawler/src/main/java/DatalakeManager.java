import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatalakeManager {

    public void build(String root) throws IOException {
        DateTreeDirectoryBuider dateTreeDirectoryBuider = new DateTreeDirectoryBuider();
        dateTreeDirectoryBuider.createDirectory(root);

        File file = new File(dateTreeDirectoryBuider.getPath(root));
        Path path = Paths.get(file.getPath());

        new TreeDirectoryBuilder(path, 1000, 100);
    }

}
