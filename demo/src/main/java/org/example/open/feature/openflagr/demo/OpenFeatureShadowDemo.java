package org.example.open.feature.openflagr.demo;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.MutableContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.open.feature.openflagr.demo.DemoHelper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpenFeatureShadowDemo {

    private static final String FLAG_KEY = "on-off";

    public static void iteration(Client client, int times, int iterationCount) throws InterruptedException {
        printStartIteration(iterationCount);

        int i = 0;
        int v1 = 0;
        AtomicInteger v2 = new AtomicInteger();

        AtomicReference<String> shadowMessage = new AtomicReference<>("");
        long startTime = System.nanoTime();

        while (i < times) {
            UUID userId = UUID.randomUUID();
            MutableContext ctx = new MutableContext(userId.toString());

            boolean toggleOn = client.getBooleanValue(FLAG_KEY, false, ctx);

            String message;

            message = v1Feature();
            v1++;
            if (toggleOn) {
                Thread newThread = new Thread(() -> {
                    shadowMessage.accumulateAndGet(v2Feature(), String::concat);
                    v2.getAndIncrement();
                });
                newThread.start();
            }
            System.out.print(message);

            i++;
        }

        long duration = System.nanoTime() - startTime;
        Thread.sleep(1000);
        System.out.println();
        System.out.println(shadowMessage.get());
        printReport(times, iterationCount, v1, v2.get(), duration);
    }

}
