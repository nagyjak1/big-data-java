package responsebuilder.wordtools;

import responsebuilder.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordReader {
    private List<Response> responses = new ArrayList<>();

    public List<Response> read(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String id = (line.split(";")[0]);
                String title = (line.split(";")[1]);
                int appearance = (Integer.parseInt(line.split(";")[2]));
                responses.add(new Response(id, title, appearance));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return responses;
    }
}
