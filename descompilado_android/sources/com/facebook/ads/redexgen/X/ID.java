package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class ID {
    private ID() {
    }

    public static String B() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        return TextUtils.isEmpty(urlPrefix) ? "https://www.facebook.com/" : String.format(Locale.US, "https://www.%s.facebook.com", urlPrefix);
    }

    public static void C(WebView adWebView) {
        adWebView.loadUrl("about:blank");
        adWebView.clearCache(true);
    }

    @TargetApi(21)
    public static void D(WebView webView) {
        WebSettings settings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        } else {
            try {
                WebSettings.class.getMethod("setMixedContentMode", new Class[0]).invoke(settings, 0);
            } catch (Exception e) {
            }
        }
    }
}
