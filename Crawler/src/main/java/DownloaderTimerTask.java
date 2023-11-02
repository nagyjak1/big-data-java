import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class DownloaderTimerTask extends TimerTask {
    private Set<Integer> usedNumbers = new HashSet<>();

    public void executeTask() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    new Controller().execute(usedNumbers);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(timerTask, 0, 60000);
    }

    public void run() {
        executeTask();
    }
}