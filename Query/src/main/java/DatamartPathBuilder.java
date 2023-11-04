import responsebuilder.wordtools.WordParser;

public class DatamartPathBuilder {
    public String build(String word) {
        char firstChar = new WordParser().parseCharacter(word, 0);
        char secondChar = new WordParser().parseCharacter(word, 1);
        return firstChar + "/" + secondChar + "/";
    }
}
