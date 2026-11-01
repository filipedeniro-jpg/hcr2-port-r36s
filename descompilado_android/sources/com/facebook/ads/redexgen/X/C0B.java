package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0B, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum C0B {
    NONE,
    INSTALLED,
    NOT_INSTALLED;

    public static C0B B(String invalidationBehaviorStr) {
        if (TextUtils.isEmpty(invalidationBehaviorStr)) {
            return NONE;
        }
        try {
            return valueOf(invalidationBehaviorStr.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
