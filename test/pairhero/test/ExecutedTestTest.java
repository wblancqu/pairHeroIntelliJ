package pairhero.test;

import org.junit.Before;
import org.junit.Test;
import pairhero.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pairhero.test.TestClassName.aTestClass;
import static pairhero.test.TestId.aTestId;
import static pairhero.test.TestMethodName.aTestMethod;

public class ExecutedTestTest extends AbstractTest {

    private static final TestClassName TEST_CLASS = aTestClass("TEST_CLASS");
    private static final TestMethodName TEST_METHOD = aTestMethod("TEST_METHOD");

    private ExecutedTest test, brokenTest;

    @Before
    public void setUp() {
        test = new ExecutedTest(TEST_CLASS, TEST_METHOD, true);
        brokenTest = new ExecutedTest(TEST_CLASS, TEST_METHOD, false);
    }

    @Test
    public void creation() {
        assertThat(test.getClassName()).isEqualTo(TEST_CLASS);
        assertThat(test.getMethodName()).isEqualTo(TEST_METHOD);
        assertThat(test.getId()).isEqualTo(aTestId(TEST_CLASS, TEST_METHOD));
        assertThat(test.isBroken()).isFalse();
    }

    @Test
    public void broke() {
        assertThat(test.isBroken()).isFalse();

        test.broke();

        assertThat(test.isBroken()).isTrue();
    }

    @Test
    public void resolved() {
        assertThat(brokenTest.isBroken()).isTrue();

        brokenTest.resolved();

        assertThat(brokenTest.isBroken()).isFalse();
    }
}