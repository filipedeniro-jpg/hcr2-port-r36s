package com.facebook.ads.redexgen.X;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BK extends WebViewClient {
    private final WeakReference<Context> B;
    private final WeakReference<InterfaceC0569Hh> C;

    public BK(WeakReference<InterfaceC0569Hh> weakReference, WeakReference<Context> context) {
        this.C = weakReference;
        this.B = context;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (this.C.get() != null) {
            this.C.get().kE(url);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (this.C.get() != null) {
            this.C.get().mE(url);
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri = Uri.parse(url);
        if (!BJ.H.contains(uri.getScheme()) && this.B.get() != null) {
            try {
                this.B.get().startActivity(new Intent("android.intent.action.VIEW", uri));
                return true;
            } catch (ActivityNotFoundException e) {
                Log.w(BJ.I, "Activity not found to handle URI.", e);
            } catch (Exception e2) {
                Log.e(BJ.I, "Unknown exception occurred when trying to handle URI.", e2);
            }
        }
        return false;
    }
}
