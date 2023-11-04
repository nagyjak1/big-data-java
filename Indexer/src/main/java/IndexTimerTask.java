import java.util.Timer;
import java.util.TimerTask;

public class IndexTimerTask {
    public void executeTask() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    new Controller().execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(timerTask, 120000, 60000);
    }
    public void run() {
        executeTask();
    }
}
