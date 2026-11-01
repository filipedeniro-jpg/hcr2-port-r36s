package com.facebook.ads.redexgen.X;

import com.facebook.ads.VideoStartReason;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7A, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum C7A {
    NOT_STARTED(VideoStartReason.NOT_STARTED),
    USER_STARTED(VideoStartReason.USER_STARTED),
    AUTO_STARTED(VideoStartReason.AUTO_STARTED);

    private final VideoStartReason B;

    C7A(VideoStartReason videoStartReason) {
        this.B = videoStartReason;
    }

    public static C7A B(VideoStartReason reason) {
        for (C7A c7a : values()) {
            if (c7a.B == reason) {
                return c7a;
            }
        }
        return NOT_STARTED;
    }
}
