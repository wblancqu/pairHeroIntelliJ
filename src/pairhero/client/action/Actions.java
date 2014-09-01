package pairhero.client.action;

import com.intellij.openapi.actionSystem.DefaultActionGroup;

public class Actions extends DefaultActionGroup {

    private StartAction startAction;
    private PauseAction pauseAction;
    private StopAction stopAction;
    private ForceSwitchAction forceSwitchAction;
    private ResetAction resetAction;

    public void addStartAction(StartAction startAction) {
        super.add(startAction);
        this.startAction = startAction;
    }

    public void addPauseAction(PauseAction pauseAction) {
        super.add(pauseAction);
        this.pauseAction = pauseAction;
    }

    public void addStopAction(StopAction stopAction) {
        super.add(stopAction);
        this.stopAction = stopAction;
    }

    public void addForceSwitchAction(ForceSwitchAction forceSwitchAction) {
        super.add(forceSwitchAction);
        this.forceSwitchAction = forceSwitchAction;
    }

    public void addResetAction(ResetAction resetAction) {
        super.add(resetAction);
        this.resetAction = resetAction;
    }

    public void running() {
        /*startAction.setEnabled(false);
        resumeAction.setEnabled(false);
        pauseAction.setEnabled(true);
        stopAction.setEnabled(true);
        forceSwitchAction.setEnabled(true);*/
    }

    public void paused() {
        /*resumeAction.setEnabled(true);
        pauseAction.setEnabled(false);
        forceSwitchAction.setEnabled(false);*/
    }

    public void resume() {
        /*resumeAction.setEnabled(false);
        pauseAction.setEnabled(true);
        forceSwitchAction.setEnabled(true);*/
    }

    public void stopped() {
        /*startAction.setEnabled(true);
        resumeAction.setEnabled(false);
        pauseAction.setEnabled(false);
        stopAction.setEnabled(false);
        forceSwitchAction.setEnabled(false);*/
    }
}
