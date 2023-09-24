package org.example.open.feature.openflagr.demo;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.MutableContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static org.example.open.feature.openflagr.demo.DemoHelper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpenFeatureCanaryDemo {

    private static final String FLAG_KEY = "canary-flag";

    public static void iteration(Client client, int times, int iterationCount) {
        printStartIteration(iterationCount);

        int i = 0, j = 0;
        int v1 = 0, v2 = 0;

        int maxColumn = 50;
        int maxRow = times / maxColumn;
        long startTime = System.nanoTime();

        while (j < maxRow) {
            UUID userId = UUID.randomUUID();
            MutableContext ctx = new MutableContext(userId.toString());

            String version = client.getStringValue(FLAG_KEY, "v1", ctx);

            String message = "";
            switch (version) {
                case "v1" -> {
                    message = v1Feature();
                    v1++;
                }
                case "v2" -> {
                    message = v2Feature();
                    v2++;
                }
            }
            System.out.print(message);

            // prettier format
            i++;
            if (i == maxColumn) {
                i = 0;
                j++;
                System.out.println();
            }

        }

        long duration = System.nanoTime() - startTime;
        printReport(times, iterationCount, v1, v2, duration);
    }

}
