package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, List<TestResult>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, m -> {
                    store.put(m.get(), m.getValue());
                    System.out.println("receive message! "+m.toString());
                })
                .match(GetMessage.class, req -> sender().tell(
                        new StoreMessage(req.getKey(), store.get(req.getKey())), self())
                ).build();
    }
}
