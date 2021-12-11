package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class RouterActor extends AbstractActor {
    private final ActorRef storage;
    private final ActorRef pool;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StorageActor.class));
        pool = getContext().actorOf(
                new RoundRobinPool(5).props(Props.create(TestExecutorActor.class, storage)),
                "routerForTests");
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    store.put(m.getKey(), m.getValue());
                    System.out.println("receive message! "+m.toString());
                })
                .match(GetMessage.class, req -> sender().tell(
                        new StoreMessage(req.getKey(), store.get(req.getKey())), self())
                ).build();
    }
}
