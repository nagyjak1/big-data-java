package responsebuilder;

import java.util.ArrayList;
import java.util.List;

public class WordBuilder {
    private List<String> ids = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<Integer> appearances = new ArrayList<>();

    public Word build(List<Response> responses) {
        for (Response respone : responses) {
            ids.add(respone.id());
            titles.add(respone.title());
            appearances.add(respone.appearance());
        }
        return new Word(ids, titles, appearances);
    }
}
