package pairhero.client.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.util.Icons;

import javax.swing.*;

public class StartAction extends AnAction {

    private static final ImageIcon ICON = Icons.anIcon("button/start");

    private final Presenter presenter;

    public StartAction(Presenter presenter) {
        super("", "Start Pomodoro", ICON);
        this.presenter = presenter;
        //setEnabled(true);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        presenter.start();
    }
}
