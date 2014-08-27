package pairhero;

import com.intellij.execution.testframework.AbstractTestProxy;
import org.mockito.Mockito;
import pairhero.test.TestClassName;
import pairhero.test.TestMethodName;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockTestProxyBuilder {

    private TestClassName className;
    private Map<TestMethodName, Boolean> tests = newHashMap();

    private MockTestProxyBuilder() {}

    public static MockTestProxyBuilder aTestClass() {
        return new MockTestProxyBuilder();
    }

    public MockTestProxyBuilder withName(TestClassName clazz) {
        this.className = clazz;
        return this;
    }

    public MockTestProxyBuilder withGreenTest(TestMethodName methodName) {
        tests.put(methodName, true);
        return this;
    }

    public MockTestProxyBuilder withRedTest(TestMethodName methodName) {
        tests.put(methodName, false);
        return this;
    }

    public AbstractTestProxy build() {
        AbstractTestProxy testClass = newMock();
        when(testClass.getName()).thenReturn(className.value());
        List<AbstractTestProxy> testMethods = buildTests(testClass);
        Mockito.<List<? extends AbstractTestProxy>>when(testClass.getChildren()).thenReturn(testMethods);
        return testClass;
    }

    private List<AbstractTestProxy> buildTests(AbstractTestProxy testClass) {
        final List<AbstractTestProxy> allTests = newArrayList();
        for (TestMethodName testMethodName : tests.keySet()) {
            AbstractTestProxy method = newMock();
            when(method.getParent()).thenReturn(testClass);
            when(method.getName()).thenReturn(testMethodName.value());
            when(method.isLeaf()).thenReturn(true);
            when(method.isPassed()).thenReturn(tests.get(testMethodName));
            allTests.add(method);
        }
        return allTests;
    }

    private AbstractTestProxy newMock() {
       return mock(AbstractTestProxy.class);
    }
}