package lab4;

public class TestRequest {
    private String packageId;
    private String jsScript;
    private String functionName;
    private Test[] tests;

    public TestRequest(String packageId, String jsScript, String functionName, Test[] tests) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }
}
