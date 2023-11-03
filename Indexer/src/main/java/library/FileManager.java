package library;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileManager {
    void separate(File file) throws FileNotFoundException;
}
