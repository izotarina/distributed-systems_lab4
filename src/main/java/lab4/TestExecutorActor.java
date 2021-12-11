package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    private final static String ENGINE_NAME = "nashborn";

    @Override
    public AbstractActor.Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, m -> {
                    Test test = m.getTest();
                    ScriptEngine engine = new
                            ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    engine.eval(m.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    return invocable.invokeFunction(m.getFunctionName(), test.getParams()).toString();
                })
                .match(GetMessage.class, req -> sender().tell(
                        new StoreMessage(req.getKey(), store.get(req.getKey())), self())
                ).build();
    }
}
