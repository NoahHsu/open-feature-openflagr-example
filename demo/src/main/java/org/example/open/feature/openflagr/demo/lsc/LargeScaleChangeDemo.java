package org.example.open.feature.openflagr.demo.lsc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LargeScaleChangeDemo {
    public static final String FLAG_KEY = "lsc";
    public static final String USER_ID_KEY = "userId";

    private final FooService fooService;

    public void iteration(int times) {
        // release toggle for non-prepared feature
        // permission toggle for tester to test
        // operation toggle for canary release

        int i = 1;
        while (i <= times) {
            RequestModel request = getRequestModel(i);
            System.out.print(i + "\t:");
            // step1
            System.out.print(fooService.step1(request));

            // step2
            System.out.print(fooService.step2(request));

            // step3
            System.out.print(fooService.step3(request));
            if (i % 5 == 0) {
                System.out.println();
            } else {
                System.out.print("\t\t");
            }
            i++;
        }


    }

    private static RequestModel getRequestModel(int i) {
        return new RequestModel(
                String.valueOf(i),
                getA(i));
    }

    private static LevelName getA(int i) {
        if (i %10 == 0) return LevelName.VIP;
        return LevelName.NORMAL;
    }


}
