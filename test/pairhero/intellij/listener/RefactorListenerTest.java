package pairhero.intellij.listener;

import com.intellij.openapi.project.Project;
import com.intellij.usageView.UsageInfo;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pairhero.AbstractTest;
import pairhero.event.EventBus;
import pairhero.refactoring.event.RefactoringPerformed;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class RefactorListenerTest extends AbstractTest {

    @InjectMocks
    private RefactorListener listener;

    @Mock
    private EventBus eventBus;

    @Mock
    private Project project;
    @Mock
    private Object operationData;

    @Test
    public void performOperation() {
        listener.performOperation(project, operationData);

        verify(eventBus).post(any(RefactoringPerformed.class));
    }

    @Test
    public void prepareOperation() {
        assertThat(listener.prepareOperation(new UsageInfo[]{})).isNull();
    }
}