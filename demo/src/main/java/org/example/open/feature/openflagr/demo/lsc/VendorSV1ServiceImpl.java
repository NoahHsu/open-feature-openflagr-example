package org.example.open.feature.openflagr.demo.lsc;

import org.springframework.stereotype.Service;

import static org.example.open.feature.openflagr.demo.DemoHelper.*;

@Service
public class VendorSV1ServiceImpl implements VendorService{
    @Override
    public boolean accept(RequestModel request) {
        return request.vendor().isVip();
    }

    @Override
    public String step1() {
        return PURPLE + "S1" + RESET;
    }

    @Override
    public String step2() {
        return PURPLE + "S2" + RESET;
    }

    @Override
    public String step3() {
        return PURPLE + "S3" + RESET;
    }
}
