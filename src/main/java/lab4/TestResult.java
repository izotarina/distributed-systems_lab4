package lab4;

public class TestResult {
    private final Test test;
    private final String result;

    public TestResult(Test test, String result) {
        this.test = test;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public Test getTest() {
        return test;
    }
}
