package pairhero.test;

public class TestMethodName {

    private String name;

    private TestMethodName(String name) {
        this.name = name;
    }

    public static TestMethodName aTestMethod(String name) {
        return new TestMethodName(name);
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestMethodName testMethodName = (TestMethodName) o;

        if (name != null ? !name.equals(testMethodName.name) : testMethodName.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
