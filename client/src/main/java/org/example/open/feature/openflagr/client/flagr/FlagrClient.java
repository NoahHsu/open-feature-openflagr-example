package org.example.open.feature.openflagr.client.flagr;

import feign.Headers;
import feign.RequestLine;
import io.micrometer.observation.annotation.Observed;
import org.example.open.feature.openflagr.client.flagr.model.V1EvaluationRequest;
import org.example.open.feature.openflagr.client.flagr.model.V1EvaluationResponse;

@Observed
public interface FlagrClient {

    String BASE_PATH = "/api/v1/";

    @RequestLine("POST " + BASE_PATH + "evaluation")
    @Headers("Content-Type: application/json")
    V1EvaluationResponse evaluate(V1EvaluationRequest request);

}