import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;

public class DataLakeManager {

    private final Path root;

    private final Integer capacity;

    private final Integer dirCapacity;


    public DataLakeManager(Path root, Integer capacity, Integer dirCapacity) throws IOException {

        if ((Math.log10(capacity) % 1) != 0) throw new InvalidParameterException("Capacity must be a power of 10");
        if ((Math.log10(dirCapacity) % 1) != 0)
            throw new InvalidParameterException("dirCapacity must be a power of 10");

        this.root = root;
        this.capacity = capacity;
        this.dirCapacity = dirCapacity;
        createStructure();
    }



  /*  public LinkedList<Integer> getDirPath(int documentId) {

        Integer documentFile = documentId % this.dirSize;
        documentId = documentId / this.dirSize;

        LinkedList<Integer> path = new LinkedList<>();

        while (documentId != 0) {
            path.addFirst(documentId % 10);
            documentId = documentId / 10;
        }

        path.addLast(documentFile);

        for (int i = path.size() + this.dirSize; i < this.dirTreeDepth; i++) {
            path.addFirst(0);
        }

        return path;
    }

    boolean createFile( int id ) {
        Path path = this.root;
        LinkedList<Integer> dirPath = getDirPath( id );
        for ( int dir : dirPath ) {
            path = path.resolve( dir + "");
        }
        try {
            path.toFile().createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println( path.getFileName() );
        System.out.println(path);
        return true;
    }*/

    void createStructure() throws IOException {
        Integer dirSizeDigits = (int) Math.log10(this.dirCapacity);
        Integer capacityDigits = (int) Math.log10(this.capacity);
        createStructureHelper(this.root, 0, capacityDigits - dirSizeDigits);
    }

    void createStructureHelper(Path location, int currentDepth, int maxDepth) throws IOException {
        if (currentDepth == maxDepth) return;

        for (int i = 0; i < 10; i++) {
            Path path = location.resolve(i + "");
            try {
                Files.createDirectory(path);
            } catch (FileAlreadyExistsException ex) {
                System.out.println(ex);
            }

            createStructureHelper(path, currentDepth + 1, maxDepth);
        }
    }
}
