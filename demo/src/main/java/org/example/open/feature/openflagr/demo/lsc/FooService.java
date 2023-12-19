package org.example.open.feature.openflagr.demo.lsc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FooService {

    private final VendorServiceFactory vendorServiceFactory;

    public String step1(RequestModel request) {
        VendorService vendorService = vendorServiceFactory.findTargetVendor(request);
        try {
            return vendorService.step1() + " -> ";
        } catch (Exception e) {
            return "X -> ";
        }
    }

    public String step2(RequestModel request) {
        VendorService vendorService = vendorServiceFactory.findTargetVendor(request);
        try {
            return vendorService.step2() + " -> ";
        } catch (Exception e) {
            return "X -> ";
        }
    }

    public String step3(RequestModel request) {
        VendorService vendorService = vendorServiceFactory.findTargetVendor(request);
        try {
            return vendorService.step3();
        } catch (Exception e) {
            return "X";
        }
    }
}
