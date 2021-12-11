package lab4;

public class SendTestResults {
    private final TestResult[] testResults;

    public SendTestResults(TestResult[] testResults) {
        this.testResults = testResults;
    }

    public TestResult[] getTestResults() {
        return testResults;
    }
}
