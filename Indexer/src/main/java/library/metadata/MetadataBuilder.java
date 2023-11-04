package library.metadata;

import java.io.*;
import java.io.FileReader;

public class MetadataBuilder {
    private String author;
    private String title;
    private String releaseDate;
    private String language;

    public Metadata build(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("Title: ")) {
                    title = line.replace("Title: ", "");
                } else if (line.trim().startsWith("Author: ")) {
                    author = line.replace("Author: ", "");
                } else if (line.trim().startsWith("Release date: ")) {
                    releaseDate = line.replace("Release date: ", "");
                } else if (line.trim().startsWith("Language: ")) {
                    language = line.replace("Language: ", "");
                }
            }
            return new Metadata(title, author, releaseDate, language);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
