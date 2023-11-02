import static spark.Spark.*;

public class Webservice {
    private final String root = "Indexer/datamart/";

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }

    public void run() {
    }
}