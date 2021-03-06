package pairhero.time;

import com.intellij.util.ui.UIUtil;
import pairhero.client.presenter.Presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer implements Runnable {

    private static final int ONE_SECOND = 1000;

    private int countdownInSeconds;
    private boolean stopTimerSignal;
    private boolean hasEverBeenStarted = false;
    private Presenter presenter;

    public void start(Presenter presenter, int countdownInSeconds) {
        this.presenter = presenter;
        this.hasEverBeenStarted = true;
        this.countdownInSeconds = countdownInSeconds;
        run();
    }

    @Override
    public void run() {
        if (!stopTimerSignal) {
            countdownInSeconds--;
            presenter.onTimeChange(countdownInSeconds);
            reRunInASecond();
        }
    }

    void reRunInASecond() {
        javax.swing.Timer timer = UIUtil.createNamedTimer("reRunInASecond timer", ONE_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                run();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void stop() {
        stopTimerSignal = true;
    }

    public boolean isRunning() {
        return hasEverBeenStarted && !stopTimerSignal;
    }

    public int getCountDownInSeconds() {
        return countdownInSeconds;
    }
}
