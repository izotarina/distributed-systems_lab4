package lab4;

public class InputDataTests {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final Test test;

    public TestExecuteRequest(String packageId, String jsScript, String functionName, Test test) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.test = test;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Test getTest() {
        return test;
    }
}
