package com.facebook.ads.internal.protocol;

import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.my.target.aa;
import com.my.target.ai;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum AdPlacementType {
    BANNER(ai.a.cW),
    INSTREAM("instream"),
    INTERSTITIAL(aa.e.bo),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
    NATIVE_BANNER("native_banner"),
    REWARDED_VIDEO("rewarded_video"),
    UNKNOWN("unknown");

    private String B;

    AdPlacementType(String mVal) {
        this.B = mVal;
    }

    public static AdPlacementType fromString(String type) {
        if (TextUtils.isEmpty(type)) {
            return UNKNOWN;
        }
        try {
            return valueOf(type.toUpperCase(Locale.US));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.B;
    }
}
