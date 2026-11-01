package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1T, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum C1T {
    CONTEXTUAL_APP("contextual_app"),
    PAGE_POST("page_post");

    private final String B;

    C1T(String objectiveString) {
        this.B = objectiveString;
    }

    public static C1T B(String value) {
        switch (value) {
            case "contextual_app":
                return CONTEXTUAL_APP;
            case "page_post":
            default:
                return PAGE_POST;
        }
    }
}
