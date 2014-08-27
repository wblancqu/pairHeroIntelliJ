package pairhero.application;

import org.junit.Test;
import org.mockito.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import pairhero.AbstractTest;
import pairhero.event.EventBus;
import pairhero.test.event.listener.TestExecutionFinishedListener;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PairHeroTest extends AbstractTest {

    @TestedObject
    private PairHero pairHero;

    @Mock
    @InjectIntoByType
    private EventBus eventBus;

    @Test
    public void initialisation() {
        pairHero.initComponent();

        verify(eventBus).register(any(TestExecutionFinishedListener.class));
        verifyNoMoreInteractions(eventBus);
    }
}