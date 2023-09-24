package org.example.open.feature.openflagr.demo;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.MutableContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static org.example.open.feature.openflagr.demo.DemoHelper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OpenFeatureBlueGreenDemo {

    private static final String FLAG_KEY = "on-off";

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

            boolean toggleOn = client.getBooleanValue(FLAG_KEY, false, ctx);

            String message;
            if (toggleOn) {
                message = v2Feature();
                v2++;
            } else {
                message = v1Feature();
                v1++;
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
