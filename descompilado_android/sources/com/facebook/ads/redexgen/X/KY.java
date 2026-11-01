package com.facebook.ads.redexgen.X;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AdSize;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KY {
    private static final Map<KV, KW> B = new HashMap();

    static {
        B.put(KV.RECTANGLE_HEIGHT_250, KW.WEBVIEW_BANNER_250);
        B.put(KV.BANNER_HEIGHT_90, KW.WEBVIEW_BANNER_90);
        B.put(KV.BANNER_HEIGHT_50, KW.WEBVIEW_BANNER_50);
    }

    private KY() {
    }

    public static AdSize B(KW template) {
        for (Map.Entry<KV, KW> entry : B.entrySet()) {
            if (entry.getValue() == template) {
                return entry.getKey().A();
            }
        }
        return AdSize.BANNER_320_50;
    }

    public static KW C(DisplayMetrics metrics) {
        int i = (int) (metrics.widthPixels / metrics.density);
        int screenWidth = metrics.heightPixels;
        int i2 = (int) (screenWidth / metrics.density);
        if (MY.B(i, i2)) {
            return KW.WEBVIEW_INTERSTITIAL_TABLET;
        }
        if (i2 > i) {
            return KW.WEBVIEW_INTERSTITIAL_VERTICAL;
        }
        return KW.WEBVIEW_INTERSTITIAL_HORIZONTAL;
    }

    public static KW D(KV adSize) {
        KW kw = B.get(adSize);
        if (kw == null) {
            return KW.WEBVIEW_BANNER_LEGACY;
        }
        return kw;
    }

    public static void E(DisplayMetrics metrics, View view, KV adSize) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) metrics.widthPixels) / metrics.density)) >= adSize.getWidth() ? metrics.widthPixels : (int) Math.ceil(adSize.getWidth() * metrics.density), (int) Math.ceil(adSize.getHeight() * metrics.density));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
