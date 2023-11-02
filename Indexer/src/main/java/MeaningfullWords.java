import java.util.HashSet;
import java.util.Set;

public interface MeaningfullWords {
    Set<String> prepositions = new HashSet<>(Set.of(
            "about", "above", "across", "after", "against", "along", "amid", "among",
            "around", "before", "behind", "below", "beneath", "beside", "between",
            "beyond", "but", "concerning", "considering", "despite", "down",
            "during", "except", "for", "from", "inside", "into", "like", "near",
            "off", "onto", "out", "outside", "over", "past", "regarding", "round",
            "since", "through", "throughout", "toward", "under", "underneath",
            "until", "unto", "upon", "with", "within", "without", "close", "front"
    ));

    Set<String> determiners = new HashSet<>(Set.of(
            "the", "some", "any", "all", "many", "few", "several",
            "both", "neither", "either", "each", "every", "this", "that",
            "your", "his", "her", "its", "our", "their",
            "those", "these", "whose", "half", "much", "little",
            "enough", "plenty", "most", "more", "another", "other", "others", "one", "two",
            "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "what", "rather", "such", "quite", "when", "where",
            "which", "who", "she", "you", "they", "him"
    ));
}

