package lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class App {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("test");
        ActorRef router = system.actorOf(
                Props.create(RouterActor.class)
        );

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        App instance = new App();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute(router).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute(ActorRef routerActor) {
        Route router = route(
                path("result", () ->
                        route(
                                get( () -> {
                                    Future<Object> result = Patterns.ask(GetTestResults,
                                            StorageActor.makeRequest(), 5000);
                                    return completeOKWithFuture(result, Jackson.marshaller());
                                }))),
                path("test", () ->
                        route(
                                post(() ->
                                        entity(Jackson.unmarshaller(InputDataTests.class), msg -> {
                                            routerActor.tell(msg, ActorRef.noSender());
                                            return complete("Test started!");
                                        })))));
        return router;
    }
}
