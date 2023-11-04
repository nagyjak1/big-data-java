package library.metadata;

public record Metadata(String title, String author, String releaseDate, String language) {
    public boolean isInEnglish() {
        return language.equals("English");
    }
}
