package org.example.open.feature.openflagr.client;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import org.example.open.feature.openflagr.client.flagr.FlagrClient;
import org.example.open.feature.openflagr.client.flagr.OpenFlagrProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureToggleApiProvider implements InitializingBean {
    @Autowired
    FlagrClient flagrClient;

    OpenFeatureAPI api = OpenFeatureAPI.getInstance();

    @Override
    public void afterPropertiesSet() throws Exception {
        OpenFlagrProvider openFlagrProvider = new OpenFlagrProvider(flagrClient);
        api.setProvider(openFlagrProvider);
    }

    public Client getFlagrApiClient() {
        return api.getClient();
    }

}
