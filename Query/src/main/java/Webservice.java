import static spark.Spark.*;

public class Webservice {
    private final String root = "Indexer/datamart/";

    public void run() {
    }

    public void browse() {
        get("/search/:word", (req, res) -> response(req.params(":word")));
    }

    public String response(String word) {
        return null;
    }
}