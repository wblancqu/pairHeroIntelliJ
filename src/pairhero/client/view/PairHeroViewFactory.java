package pairhero.client.view;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import pairhero.client.presenter.Presenter;

import javax.swing.*;

import static com.intellij.openapi.components.ServiceManager.getService;

public class PairHeroViewFactory implements ToolWindowFactory {

    private final Presenter presenter;

    public PairHeroViewFactory() {
        presenter = getService(Presenter.class);
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        final JFrame parent = (JFrame)toolWindow.getComponent().getRootPane().getParent();
        ContentManager contentManager = toolWindow.getContentManager();
        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        contentManager.addContent(factory.createContent(new PairHeroView(parent, presenter), "", false));
    }
}
