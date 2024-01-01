package edu.hw8;

import edu.hw8.task1.client.Client;
import edu.hw8.task1.server.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task1Test {
    Thread serverThread;

    @BeforeEach
    void setUp() {
        serverThread = new Thread(Server::start);
        serverThread.start();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Server.stop();
        serverThread.join();
    }

    @Test
    void testClientHandlerGetPhrase() throws InterruptedException {
        Thread.sleep(1000);

        String word = "глупый";
        String expectedPhrase =
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
        String actualPhrase = Client.run(word);
        assertEquals(expectedPhrase, actualPhrase);
    }

    @Test
    void testServerHandlesMultipleRequest1s() throws InterruptedException, ExecutionException {
        Thread.sleep(1000);

        int numRequests = 10;

        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (int i = 0; i < numRequests; i++) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> Client.run("test"));
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.get();

        for (CompletableFuture<String> future : futures) {
            assertNotNull(future.get());
        }
    }
}
