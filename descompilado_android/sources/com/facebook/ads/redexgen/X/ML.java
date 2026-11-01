package com.facebook.ads.redexgen.X;

import org.json.JSONArray;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum ML {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11),
    UNIFIED_LOGGING(16),
    HTTP_LINKS(17);

    private static final String S;
    private final int B;
    public static final ML[] R = {LINK_AD_V2, APP_ENGAGEMENT_AD, AD_CHOICES, JS_TRIGGER_NO_AUTO_IMP_LOGGING, NATIVE_CLOSE_BUTTON, UNIFIED_LOGGING, HTTP_LINKS};

    static {
        JSONArray array = new JSONArray();
        for (ML ml : R) {
            array.put(ml.C());
        }
        S = array.toString();
    }

    ML(int mValue) {
        this.B = mValue;
    }

    public static String B() {
        return S;
    }

    private final int C() {
        return this.B;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return String.valueOf(this.B);
    }
}
