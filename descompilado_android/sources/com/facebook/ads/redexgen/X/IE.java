package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.api.BuildConfigApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class IE extends WebView {
    private static final String C = IE.class.getSimpleName();
    private boolean B;

    public IE(Context context) {
        super(context);
        I();
    }

    public WebChromeClient A() {
        return new WebChromeClient();
    }

    /* JADX INFO: renamed from: B */
    public WebViewClient mo240B() {
        return new WebViewClient();
    }

    public final boolean C() {
        return this.B;
    }

    private void I() {
        setWebChromeClient(A());
        setWebViewClient(mo240B());
        ID.D(this);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (BuildConfigApi.isDebug() && Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            } catch (Exception e) {
                Log.w(C, "Failed to initialize CookieManager.");
            }
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        this.B = true;
        super.destroy();
    }
}
