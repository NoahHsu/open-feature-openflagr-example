package org.example.open.feature.openflagr.demo.lsc;

public enum LevelName {
    NORMAL, VIP;

    public boolean isNormal() {
        return this == NORMAL;
    }

    public boolean isVip() {
        return this == VIP;
    }
}
