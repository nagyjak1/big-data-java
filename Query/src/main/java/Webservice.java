import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import responsebuilder.Response;
import responsebuilder.Word;
import responsebuilder.WordBuilder;
import responsebuilder.wordtools.WordReader;

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
        List<Response> response = new WordReader().read(filePath);
        Word wordTitles = new WordBuilder().build(sort(response));
        return gson.toJson(wordTitles.titles());
    }

    public List<Response> sort(List<Response> response) {
        Comparator<Response> comparator = Comparator.comparingInt(Response::appearance).reversed();
        response.sort(comparator);
        return response;
    }
}