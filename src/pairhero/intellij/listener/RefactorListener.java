package pairhero.intellij.listener;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.usageView.UsageInfo;
import pairhero.PairHeroToolWindowFactory;
import pairhero.event.EventBus;
import pairhero.refactoring.event.RefactoringPerformed;

public class RefactorListener implements com.intellij.refactoring.RefactoringHelper {

    private EventBus eventBus;
    private PairHeroToolWindowFactory pairHeroToolWindowFactory;

	@Override
	public Object prepareOperation(UsageInfo[] usageInfos) {
		return null;
	}

	@Override
	public void performOperation(Project project, Object operationData) {
        eventBus.post(new RefactoringPerformed());
	}

    private EventBus eventBus() {
        if(eventBus == null) {
            eventBus = ServiceManager.getService(EventBus.class);
        }
        return eventBus;
    }
}
