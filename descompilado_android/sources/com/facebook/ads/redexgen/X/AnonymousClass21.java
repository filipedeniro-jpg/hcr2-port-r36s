package com.facebook.ads.redexgen.X;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AdError;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.21, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnonymousClass21 extends WebViewClient {
    public final boolean B;
    public boolean C = false;
    public final WeakReference<InterfaceC01410u> D;
    public final C01571k E;

    public AnonymousClass21(C01571k playableAdData, WeakReference<InterfaceC01410u> weakReference, boolean failOnCacheFailure) {
        this.E = playableAdData;
        this.D = weakReference;
        this.B = failOnCacheFailure;
    }

    private void C() {
        if (this.D.get() != null) {
            this.D.get().sE();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(WebResourceError error) {
        if (this.D.get() == null) {
            return;
        }
        if (this.B) {
            this.D.get().rE(AdError.CACHE_ERROR);
        } else {
            C();
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView view, String url) {
        this.C = true;
        C();
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        new Handler().postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.20
            @Override // java.lang.Runnable
            public final void run() {
                if (this.B.C) {
                    return;
                }
                this.B.D(null);
            }
        }, this.E.I());
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        this.C = true;
        D(error);
    }
}
