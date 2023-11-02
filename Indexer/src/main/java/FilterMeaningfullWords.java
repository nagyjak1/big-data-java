import java.util.Set;

public class FilterMeaningfullWords {
    Set<String> prepositions = MeaningfullWords.prepositions;
    Set<String> determiners = MeaningfullWords.determiners;

    public Boolean isMeaningfulWord(String word) {
        if (prepositions.contains(word) || determiners.contains(word)) {
            return false;
        }
        return word.length() > 2;
    }
}
