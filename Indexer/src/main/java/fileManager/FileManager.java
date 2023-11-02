package fileManager;

import java.io.*;

public class FileManager {

    public void processFilesInFolder(String datalakePath) {
        File folder = new File(datalakePath);
        processFilesRecursively(folder);
    }

    private void processFilesRecursively(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processFilesRecursively(file);
                } else {
                    separateMetadataContent(file);
                }
            }
        }
    }

    private void separateMetadataContent(File file) {
        StringBuilder metadata = new StringBuilder();
        StringBuilder content = new StringBuilder();
        boolean readingMetadata = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("***")) {
                    readingMetadata = false;
                    continue;
                }

                if (readingMetadata) {
                    metadata.append(line).append("\n");
                } else {
                    content.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File separatedFolder = new File("src/main/resources/separated");
        File metadataFolder = new File("src/main/resources/separated/metadata");
        File contentFolder = new File("src/main/resources/separated/content");

        if (!separatedFolder.exists()) {
            separatedFolder.mkdir();
        }
        if (!metadataFolder.exists()) {
            metadataFolder.mkdir();
        }
        if (!contentFolder.exists()) {
            contentFolder.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/separated/metadata/" + file.getName()))) {
            writer.write(metadata.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/separated/content/" + file.getName()))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
