import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class PathsProvider {
    public void provide(String path, String filename, String content) {
        FileManager fileManager = new FileManager();
        fileManager.createDirectory(path);
        fileManager.createFile(path + "/" + filename);
        fileManager.writeFile(path + "/" + filename, content);
    }

    public List<String> provideAll(String path) throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(path));
        return walk.filter(f -> !Files.isDirectory(f))
                .map(Path::toString)
                .filter(f -> f.endsWith(".txt"))
                .toList();
    }
}
