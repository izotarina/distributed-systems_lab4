package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    private final static String ENGINE_NAME = "nashborn";
    private final ActorRef storageActor;

    public TestExecutorActor(ActorRef storageActor) {
        this.storageActor = storageActor;
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, m -> {
                    ScriptEngine engine = new
                            ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    engine.eval(m.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    String result = invocable.invokeFunction(m.getFunctionName(), m.getTest().getParams()).toString();

                    TestResult testResult = new TestResult(m.getTest(), result);
                    StoreTestResult storeTestResult = new StoreTestResult(m.getPackageId(), testResult);

                    storageActor.tell(storeTestResult, self());
                }).build();
    }
}
