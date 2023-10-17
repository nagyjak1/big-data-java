import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.LinkedList;



public class DataLakeManager {

    private final Path root;
    private final Integer dirCapacity;
    private Integer capacity;

    private static final Logger logger = LogManager.getLogger(DataLakeManager.class);


    public DataLakeManager(Path root, Integer capacity, Integer dirCapacity) throws IOException {

        logger.log(Level.INFO, "Creating DataLakeManager");

        if ((Math.log10(capacity) % 1) != 0) throw new InvalidParameterException("Capacity must be a power of 10");
        if ((Math.log10(dirCapacity) % 1) != 0)
            throw new InvalidParameterException("dirCapacity must be a power of 10");

        try {
            logger.log(Level.INFO, "Trying to create root directory for the datalake");
            Files.createDirectories(root);
            logger.log(Level.INFO, "Root directory created");
        } catch (FileAlreadyExistsException ex) {
            logger.log(Level.INFO, "Root directory already created");
        }

        this.root = root;
        this.capacity = capacity;
        this.dirCapacity = dirCapacity;
        if ( ! isStructureCreated() ) {
            logger.log(Level.INFO, "Creating directory structure");
            createStructure();
        }
        else {
            this.capacity = calculateCapacity();
            logger.log(Level.INFO, "Structure is already created, skipping creation");
        }
    }

    boolean isStructureCreated() {
        return root.toFile().listFiles().length != 0;
    }

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
                continue;
            }

            createStructureHelper(path, currentDepth + 1, maxDepth);
        }
    }

    int calculateCapacity() {
        return calculateCapacityHelper(1, this.root);
    }

    int calculateCapacityHelper( int capacity, Path root ) {
        File[] files = root.toFile().listFiles();
        if ( files.length == 0 || !Files.isDirectory(files[0].toPath()) )
            return capacity*this.dirCapacity;
        return calculateCapacityHelper( 10*capacity, root.resolve( files[0].getName() ));
    }

    void increaseCapacity() throws IOException {
        capacity *= 10;

        Files.createDirectory(root.resolve("tmp"));
        for (int i = 0; i < 10; i++)
            Files.move(root.resolve(i + ""), root.resolve("tmp").resolve(i + ""));

        Files.move(root.resolve("tmp"), root.resolve(0 + ""));

        createStructure();
    }


    public Path getFilePath (int documentId) {

        Integer fileName = documentId % this.dirCapacity;
        documentId = documentId / this.dirCapacity;

        LinkedList<Integer> path = new LinkedList<>();

        while (documentId != 0) {
            path.addFirst(documentId % 10);
            documentId = documentId / 10;
        }

        path.addLast(fileName);

        for (int i = path.size()-1 + (int)Math.log10(this.dirCapacity); i < Math.log10(this.capacity); i++)
            path.addFirst(0);

        return createPathFromLinkedList( path );
    }

    public Path createPathFromLinkedList( LinkedList<Integer> list ) {
        String res = "";
        for (Integer i : list) {
            if (i.equals(list.getLast()))
                res = res.concat(i + ".txt");
            else {
                res = res.concat(i + "/");
            }
        }
        return Paths.get(res);
    }

    File createFile(int id) {
        Path dirPath = getFilePath(id);
        Path path = this.root.resolve(dirPath);
        try {
            logger.log(Level.INFO, "Creating file with id: " + id);
            Files.createFile( path );
        } catch (IOException e) {
            logger.log(Level.ERROR, "File with id: " + id + "already exists");
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "File with id: " + id + "created");
        return new File( path.toUri() );
    }
}
