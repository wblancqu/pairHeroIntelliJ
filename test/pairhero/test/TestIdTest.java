package pairhero.test;

import org.junit.Before;
import org.junit.Test;
import pairhero.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pairhero.test.TestClassName.aTestClass;
import static pairhero.test.TestId.aTestId;
import static pairhero.test.TestMethodName.aTestMethod;

public class TestIdTest extends AbstractTest {

    private static final TestClassName TEST_CLASS = aTestClass("TEST_CLASS");
    private static final TestClassName ANOTHER_TEST_CLASS = aTestClass("ANOTHER_TEST_CLASS");
    private static final TestMethodName TEST_METHOD = aTestMethod("TEST_METHOD");
    private static final TestMethodName ANOTHER_TEST_METHOD = aTestMethod("ANOTHER_TEST_METHOD");

    private TestId testId, anotherTestId, yetAnotherTestIt;

    @Before
    public void setUp() {
        testId = aTestId(TEST_CLASS, TEST_METHOD);
        anotherTestId = aTestId(ANOTHER_TEST_CLASS, TEST_METHOD);
        yetAnotherTestIt = aTestId(TEST_CLASS, ANOTHER_TEST_METHOD);
    }

    @Test
    public void equals() {
        assertThat(testId).isEqualTo(testId);
    }

    @Test
    public void notEquals() {
        assertThat(testId).isNotEqualTo(anotherTestId);
        assertThat(testId).isNotEqualTo(yetAnotherTestIt);
    }
}