package pairhero.test;

public class TestClassName {

    private String name;

    private TestClassName(String name) {
        this.name = name;
    }

    public static TestClassName aTestClass(String name) {
        return new TestClassName(name);
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestClassName that = (TestClassName) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
