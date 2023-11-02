import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

import static spark.Spark.get;

public class Webservice {
    private final String root = "Indexer/datamart/";

    public void browse() {
        get("/search/:word", (req, res) -> response(req.params(":word")));
    }

    public String response(String word) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = root + new DatamartPathBuilder().build(word) + word + ".txt";
        Word wordObject = new WordReader().read(filePath);
        sort(wordObject);
        return gson.toJson(wordObject.titles());
    }

    public void sort(Word word) {
        Collections.sort(word.titles(), new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int index1 = word.titles().indexOf(s1);
                int index2 = word.titles().indexOf(s2);
                return Integer.compare(word.appearance().get(index2), word.appearance().get(index1));
            }
        });
    }
}