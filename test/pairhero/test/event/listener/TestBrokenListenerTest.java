package pairhero.test.event.listener;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pairhero.AbstractTest;
import pairhero.client.presenter.Presenter;
import pairhero.event.Event;
import pairhero.test.event.TestBroken;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class TestBrokenListenerTest extends AbstractTest {

    @InjectMocks
    private TestBrokenListener listener;

    @Mock
    private Presenter presenter;

    @Mock
    private TestBroken event;

    @Test
    public void handle() {
        listener.handle(event);

        verify(presenter).onBrokenTest();
    }

    @Test
    public void canHandle() {
        assertThat(listener.canHandle(new TestBroken())).isTrue();
    }

    @Test
    public void cantHandle() {
        assertThat(listener.canHandle(new Event() {
        })).isFalse();
    }
}