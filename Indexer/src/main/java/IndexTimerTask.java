import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class IndexTimerTask {
    private Set<Integer> set = new HashSet<>();

    public void executeTask() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    new Controller().execute(set);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
    public void run() {
        executeTask();
    }
}
