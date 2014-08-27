package pairhero.test.event.listener;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pairhero.AbstractTest;
import pairhero.event.Event;
import pairhero.event.EventBus;
import pairhero.test.ExecutedTest;
import pairhero.test.ExecutedTestStore;
import pairhero.test.event.TestBroken;
import pairhero.test.event.TestExecutionFinished;
import pairhero.test.event.TestResolved;

import java.util.ArrayList;

import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TestExecutionFinishedListenerTest extends AbstractTest {

    private static final Optional<ExecutedTest> NO_PREVIOUS_EXECUTION = Optional.<ExecutedTest>absent();

    @InjectMocks
    private TestExecutionFinishedListener listener;

    @Mock
    private ExecutedTestStore store;
    @Mock
    private EventBus eventBus;

    @Mock
    private TestExecutionFinished event;
    @Mock
    private ExecutedTest test, brokenTest;

    @Before
    public void setUp() {
        when(test.isBroken()).thenReturn(false);
        when(brokenTest.isBroken()).thenReturn(true);
    }

    @Test
    public void handle_WhenPassingTest_NoPreviousExecution_ThenDoNothing() {
        when(event.getTests()).thenReturn(newArrayList(test));
        when(store.previousExecution(test)).thenReturn(NO_PREVIOUS_EXECUTION);

        listener.handle(event);

        verify(store).save(test);
        verifyZeroInteractions(eventBus);
    }

    @Test
    public void handle_WhenBrokenTest_NoPreviousExecution_ThenSendBrokenEvent() {
        when(event.getTests()).thenReturn(newArrayList(brokenTest));
        when(store.previousExecution(brokenTest)).thenReturn(NO_PREVIOUS_EXECUTION);

        listener.handle(event);

        InOrder inOrder = inOrder(store, eventBus);
        inOrder.verify(store).save(brokenTest);
        inOrder.verify(eventBus).post(any(TestBroken.class));
    }

    @Test
    public void handle_WhenPassingTest_NotPreviouslyBroken_ThenDoNothing() {
        when(event.getTests()).thenReturn(newArrayList(test));
        when(store.previousExecution(test)).thenReturn(of(test));

        listener.handle(event);

        verifyZeroInteractions(eventBus);
    }

    @Test
    public void handle_WhenBrokenTest_PreviouslyBroken_ThenDoNothing() {
        when(event.getTests()).thenReturn(newArrayList(brokenTest));
        when(store.previousExecution(brokenTest)).thenReturn(of(brokenTest));

        listener.handle(event);

        verify(store).previousExecution(brokenTest);
        verifyNoMoreInteractions(store);
        verifyZeroInteractions(eventBus);
    }

    @Test
    public void handle_WhenPassingTest_PreviouslyBroken_ThenSendResolvedEvent() {
        when(event.getTests()).thenReturn(newArrayList(test));
        when(store.previousExecution(test)).thenReturn(of(brokenTest));

        listener.handle(event);

        verify(brokenTest).resolved();
        verify(eventBus).post(any(TestResolved.class));
    }

    @Test
    public void handle_WhenBrokenTest_PreviouslyPassing_ThenSendBrokenEvent() {
        when(event.getTests()).thenReturn(newArrayList(brokenTest));
        when(store.previousExecution(brokenTest)).thenReturn(of(test));

        listener.handle(event);

        verify(test).broken();
        verify(eventBus).post(any(TestBroken.class));
    }

    @Test
    public void canHandle() {
        assertThat(listener.canHandle(new TestExecutionFinished(new ArrayList<ExecutedTest>()))).isTrue();
    }

    @Test
    public void cantHandle() {
        assertThat(listener.canHandle(new Event(){})).isFalse();
    }
}