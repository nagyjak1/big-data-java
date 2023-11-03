import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileEncodingHandler {
    public boolean checkFileEncoding(String path) throws IOException {
        File file = new File(path);
        DefaultDetector detector = new DefaultDetector();
        try (InputStream input = TikaInputStream.get(file)) {
            Metadata metadata = new Metadata();
            String encoding = detector.detect(input, metadata).toString();
            return encoding.equals("text/plain");
        }
    }
}