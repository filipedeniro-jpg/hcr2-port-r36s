package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.tonyodev.fetch.FetchConst;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum KW {
    UNKNOWN(0),
    WEBVIEW_BANNER_LEGACY(4),
    WEBVIEW_BANNER_50(5),
    WEBVIEW_BANNER_90(6),
    WEBVIEW_BANNER_250(7),
    WEBVIEW_INTERSTITIAL_UNKNOWN(100),
    WEBVIEW_INTERSTITIAL_HORIZONTAL(101),
    WEBVIEW_INTERSTITIAL_VERTICAL(102),
    WEBVIEW_INTERSTITIAL_TABLET(103),
    NATIVE_UNKNOWN(200),
    NATIVE_BANNER(500),
    NATIVE_250(FetchConst.NETWORK_WIFI),
    REWARDED_VIDEO(400),
    INSTREAM_VIDEO(300);

    private final int B;

    KW(int mValue) {
        this.B = mValue;
    }

    public final int A() {
        return this.B;
    }

    @Nullable
    public static KW B(int id) {
        for (KW kw : values()) {
            if (kw.B == id) {
                return kw;
            }
        }
        return null;
    }
}
