package pairhero.test;

import com.google.common.base.Optional;
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
import static com.intellij.util.ReflectionUtil.getField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ExecutedTestStoreTest extends AbstractTest {

    @TestedObject
    private ExecutedTestStore store;

    @InjectIntoByType
    private List<ExecutedTest> history;

    @Mock
    private ExecutedTest test, anotherTest;

    @Mock
    private TestId testId, anotherTestId;

    @Before
    public void setUp() {
        history = newArrayList(test);
        when(test.getId()).thenReturn(testId);
        when(anotherTest.getId()).thenReturn(anotherTestId);
    }

    @Test
    public void whenPreviousExecution_ThenReturnIt() {
        Optional<ExecutedTest> actual = store.previousExecution(test);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(test);
    }

    @Test
    public void whenNoPreviousExecution_ThenReturnNothing() {
        Optional<ExecutedTest> actual = store.previousExecution(anotherTest);

        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    public void save() throws Exception {
        store.save(anotherTest);

        List history = getField(ExecutedTestStore.class, store, List.class, "history");
        assertThat(history).containsExactly(test, anotherTest);
    }

    @Test
    public void save_DoNotWhenAlreadyExists() {
        store.save(test);

        List history = getField(ExecutedTestStore.class, store, List.class, "history");
        assertThat(history).containsExactly(test);
    }
}