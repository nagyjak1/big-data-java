import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordReader {
    private List<String> ids = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<Integer> appearance = new ArrayList<>();

    public Word read(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ids.add(line.split(";")[0]);
                titles.add(line.split(";")[1]);
                appearance.add(Integer.parseInt(line.split(";")[2]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Word(ids, titles, appearance);
    }
}
