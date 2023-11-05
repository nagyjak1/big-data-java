import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class Listener {
    private final int MAX_ITERS = 10;
    private List<String> dirs = new ArrayList<>();

    public List<String> detect(String path) {
        try {
            Path directory = Paths.get(path);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            System.out.println("File Event Listener started for directory: " + path);
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    if (kind == ENTRY_CREATE) {
                        System.out.println("File created: " + eventPath);
                    } else if (kind == ENTRY_MODIFY) {
                        System.out.println("File modified: " + eventPath);
                    } else if (kind == ENTRY_DELETE) {
                        System.out.println("File deleted: " + eventPath);
                    }
                    dirs.add(String.valueOf(eventPath));
                    if (dirs.size() == MAX_ITERS) {
                        return dirs;
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
