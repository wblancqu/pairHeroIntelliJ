package pairhero.client.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.util.Icons;

import javax.swing.*;

public class ForceSwitchAction extends AnAction {

    private static final ImageIcon ICON = Icons.anIcon("button/forceSwitch");
    private final Presenter presenter;

    public ForceSwitchAction(Presenter presenter) {
        super("", "Force Pair Switch", ICON);
        this.presenter = presenter;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        presenter.forceSwitch();
    }
}
