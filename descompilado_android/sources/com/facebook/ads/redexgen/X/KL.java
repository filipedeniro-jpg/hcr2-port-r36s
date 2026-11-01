package com.facebook.ads.redexgen.X;

import com.facebook.ads.VideoAutoplayBehavior;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum KL {
    DEFAULT,
    ON,
    OFF;

    public static VideoAutoplayBehavior B(KL behavior) {
        if (behavior == null) {
            return VideoAutoplayBehavior.DEFAULT;
        }
        switch (behavior) {
            case DEFAULT:
                return VideoAutoplayBehavior.DEFAULT;
            case ON:
                return VideoAutoplayBehavior.ON;
            case OFF:
                return VideoAutoplayBehavior.OFF;
            default:
                return VideoAutoplayBehavior.DEFAULT;
        }
    }
}
