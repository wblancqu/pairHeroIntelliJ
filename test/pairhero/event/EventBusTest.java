package pairhero.event;

import com.intellij.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.util.ReflectionUtils;
import pairhero.AbstractTest;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.intellij.util.ReflectionUtil.findField;
import static com.intellij.util.ReflectionUtil.getField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventBusTest extends AbstractTest {

    @TestedObject
    private EventBus eventBus;

    @InjectIntoByType
    private List<Listener> listeners;

    @Mock
    private Listener listener, anotherListener;
    @Mock
    private Event event;

    @Before
    public void setUp() throws NoSuchFieldException {
        listeners = newArrayList(listener);
        when(listener.canHandle(event)).thenReturn(true);
    }

    @Test
    public void register() {
        eventBus.register(anotherListener);

        List listeners = getField(EventBus.class, eventBus, List.class, "listeners");

        assertThat(listeners).containsExactly(listener, anotherListener);
    }

    @Test
    public void post() {
        eventBus.post(event);

        verify(listener).handle(event);
    }
}