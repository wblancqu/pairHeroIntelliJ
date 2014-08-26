package pairhero.intellij.listener;

import com.intellij.execution.testframework.AbstractTestProxy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import pairhero.AbstractTest;
import pairhero.event.EventBus;
import pairhero.test.event.TestExecutionFinished;
import pairhero.test.event.TestExecutionFinishedFactory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestExecutionListenerTest extends AbstractTest {

    @TestedObject
    private TestExecutionListener listener;

    @Mock
    @InjectIntoByType
    private EventBus eventBus;
    @Mock
    @InjectIntoByType
    private TestExecutionFinishedFactory factory;

    @Mock
    private AbstractTestProxy testProxy;
    @Mock
    private TestExecutionFinished testExecutionFinished;

    @Before
    public void setUp() {
        when(factory.create(testProxy)).thenReturn(testExecutionFinished);
    }

    @Test
    public void testSuiteFinished() {
        listener.testSuiteFinished(testProxy);

        verify(eventBus).post(testExecutionFinished);
    }
}