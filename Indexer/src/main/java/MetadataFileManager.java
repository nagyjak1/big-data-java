import java.io.*;
import java.io.FileReader;

public class MetadataFileManager implements FileManager{

    public MetadataFileManager() {
    }

    @Override
    public void separate(File file) throws FileNotFoundException {
        StringBuilder metadata = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("***")) {
                   break;
                } else {
                    metadata.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Indexer/Library/Metadata/" + file.getName()))) {
            writer.write(metadata.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
