package org.example.open.feature.openflagr.demo.lsc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceFactory {

    private final List<VendorService> vendorServices;

    VendorService findTargetVendor(RequestModel request) {
        for (VendorService strategy : vendorServices) {
            if (strategy.accept(request)) {
                return strategy;
            }
        }
        throw new RuntimeException("no match strategy");
    }

}
