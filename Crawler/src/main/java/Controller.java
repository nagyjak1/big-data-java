import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class Controller {
    private final int MAX_BOOKS = 70_000;
    private final int MAX_ITERS = 11;

    public void execute(Set<Integer> usedNumbers) throws IOException {
        new DatalakeManager().build("Crawler/datalake");
        String directory = new DateTreeDirectoryBuider().getPath("Crawler/datalake");

        for (int i = 1; i < MAX_ITERS; i++) {
            int randInt;
            do {
                randInt = new Random().nextInt(MAX_BOOKS) + 1;
            } while (usedNumbers.contains(randInt));

            try {
                new BookDownloader().downloadInto(directory, randInt);
            } catch (FileNotFoundException e) {
                continue;
            }
            usedNumbers.add(randInt);
        }
    }
}
