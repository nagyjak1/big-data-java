public class DatamartPathBuilder {
    public String build(String root) {
        char firstChar = new WordParser().parseCharacter(root, 0);
        char secondChar = new WordParser().parseCharacter(root, 1);
        return root + "/" + firstChar + "/" + secondChar + "/";
    }
}
