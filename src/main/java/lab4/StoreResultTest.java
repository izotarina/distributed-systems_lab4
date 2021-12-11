package lab4;

public class StoreResultTest {
    private Test test;
    private String result;

    public StoreResultTest(Test test, String result) {
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
