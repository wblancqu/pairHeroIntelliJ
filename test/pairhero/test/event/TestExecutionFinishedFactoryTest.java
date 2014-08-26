package pairhero.test.event;

import com.intellij.execution.testframework.AbstractTestProxy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.unitils.inject.annotation.TestedObject;
import pairhero.AbstractTest;
import pairhero.MockTestProxyBuilder;
import pairhero.test.ExecutedTest;
import pairhero.test.TestClassName;
import pairhero.test.TestMethodName;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static pairhero.test.TestClassName.aTestClass;
import static pairhero.test.TestMethodName.aTestMethod;

public class TestExecutionFinishedFactoryTest extends AbstractTest {

    private static final TestClassName TEST_CLASS = aTestClass("TEST_CLASS");
    private static final TestClassName ANOTHER_TEST_CLASS = aTestClass("ANOTHER_TEST_CLASS");
    private static final TestMethodName TEST_METHOD = aTestMethod("TEST_METHOD");
    private static final TestMethodName BROKEN_TEST_METHOD = aTestMethod("BROKEN_TEST_METHOD");

    @TestedObject
    private TestExecutionFinishedFactory factory;

    @Mock
    private AbstractTestProxy testResults;

    private AbstractTestProxy testClass, testClassWithBrokenTest;

    @Before
    public void setUp() {
        testClass = MockTestProxyBuilder.aTestClass().withName(TEST_CLASS)
                                        .withGreenTest(TEST_METHOD)
                                        .build();
        testClassWithBrokenTest = MockTestProxyBuilder.aTestClass().withName(ANOTHER_TEST_CLASS)
                                        .withRedTest(BROKEN_TEST_METHOD)
                                        .build();

        Mockito.<List<? extends AbstractTestProxy>>when(testResults.getChildren()).thenReturn(newArrayList(testClass, testClassWithBrokenTest));
    }

    @Test
    public void create() {
        TestExecutionFinished actual = factory.create(testResults);

        assertThat(actual.getTests()).hasSize(2);
        ExecutedTest actualTest = actual.getTests().get(0);
        assertThat(actualTest.getClassName()).isEqualTo(TEST_CLASS);
        assertThat(actualTest.getMethodName()).isEqualTo(TEST_METHOD);
        assertThat(actualTest.isBroken()).isEqualTo(false);
        ExecutedTest actualBrokenTest = actual.getTests().get(1);
        assertThat(actualBrokenTest.getClassName()).isEqualTo(ANOTHER_TEST_CLASS);
        assertThat(actualBrokenTest.getMethodName()).isEqualTo(BROKEN_TEST_METHOD);
        assertThat(actualBrokenTest.isBroken()).isEqualTo(true);
    }
}