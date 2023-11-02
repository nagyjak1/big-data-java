import java.io.*;
import java.io.FileReader;

public class ContentFileManager implements FileManager {

    public ContentFileManager() {
    }

    @Override
    public void separate(File file){
        StringBuilder content = new StringBuilder();
        boolean startContent = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("***")) {
                    startContent = true;
                    continue;
                }
                if (startContent){
                    content.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Indexer/Library/Content/" + file.getName()))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
