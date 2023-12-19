package org.example.open.feature.openflagr.demo.lsc;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.MutableContext;
import lombok.RequiredArgsConstructor;
import org.example.open.feature.openflagr.client.FeatureToggleApiProvider;
import org.springframework.stereotype.Service;

import static org.example.open.feature.openflagr.demo.DemoHelper.BLUE;
import static org.example.open.feature.openflagr.demo.DemoHelper.RESET;
import static org.example.open.feature.openflagr.demo.lsc.LargeScaleChangeDemo.FLAG_KEY;
import static org.example.open.feature.openflagr.demo.lsc.LargeScaleChangeDemo.USER_ID_KEY;

@Service
@RequiredArgsConstructor
public class VendorAV1ServiceImpl implements VendorService {

    private final FeatureToggleApiProvider featureToggleApiProvider;

    @Override
    public boolean accept(RequestModel request) {
        Client client = featureToggleApiProvider.getFlagrApiClient();
        MutableContext evaluationContext = new MutableContext(request.userId());
        evaluationContext.add(USER_ID_KEY, request.userId());

        boolean isToggleOn = client.getBooleanValue(FLAG_KEY, false, evaluationContext);

        return !isToggleOn && request.vendor().isNormal();
    }

    @Override
    public String step1() {
        return BLUE + "A1" + RESET;
    }

    @Override
    public String step2() {
        return BLUE + "A2" + RESET;
    }

    @Override
    public String step3() {
        return BLUE + "A3" + RESET;
    }

}
