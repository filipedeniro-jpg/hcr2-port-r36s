package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.internal.NativeProtocol;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BC extends WebViewClient {
    private final WeakReference<BB> B;
    private final WeakReference<C0V> C;
    private final WeakReference<AtomicBoolean> D;
    private final Context E;
    private boolean F = false;
    private final AtomicReference<String> G;
    private Date H;
    private final AtomicInteger I;
    private final WeakReference<C0621Ji> J;
    private final WeakReference<C5I> K;

    public BC(Context context, WeakReference<C0V> weakReference, WeakReference<C5I> weakReference2, WeakReference<C0621Ji> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<BB> weakReference5, AtomicInteger timeOutInMillis, AtomicReference<String> atomicReference) {
        this.E = context.getApplicationContext();
        this.C = weakReference;
        this.K = weakReference2;
        this.J = weakReference3;
        this.D = weakReference4;
        this.B = weakReference5;
        this.I = timeOutInMillis;
        this.G = atomicReference;
    }

    private void D(int errorCode, CharSequence description, long loadingTimeInMillis) {
        JSONObject jSONObject = new JSONObject();
        boolean z = description != null;
        try {
            jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, errorCode);
            jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION, description);
            jSONObject.put("is_web_resource_error", z);
            jSONObject.put("loading_time_in_millis", loadingTimeInMillis);
            jSONObject.put("request_id", this.G.get());
        } catch (JSONException e) {
        }
        C0585Hx.D(this.E, "web_view", C0584Hw.vB, new C0583Hv(AdErrorType.WEB_VIEW_FAILED_TO_LOAD.getDefaultErrorMessage(), "Extra data: " + jSONObject.toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(int errorCode, @Nullable String description) {
        if ("net::ERR_EMPTY_RESPONSE".equals(description)) {
            return;
        }
        D(errorCode, description, new Date().getTime() - this.H.getTime());
        if (this.C.get() == null) {
            return;
        }
        this.C.get().BE(errorCode, description);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView view, String url) {
        if (this.B.get() != null && this.D.get() != null && !this.D.get().get()) {
            this.B.get().H();
        }
        this.F = true;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.H = new Date();
        new Handler().postDelayed(new BD(this), this.I.get());
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        this.F = true;
        E(errorCode, description);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        this.F = true;
        if (Build.VERSION.SDK_INT >= 23) {
            E(error.getErrorCode(), error.getDescription().toString());
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.cancel();
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView view, String url) {
        HashMap map = new HashMap();
        if (this.K.get() != null) {
            this.K.get().A(map);
        }
        if (this.J.get() != null) {
            map.put("touch", MJ.C(this.J.get().m258B()));
        }
        if (this.C.get() != null) {
            this.C.get().hD(url, map);
            return true;
        }
        return true;
    }
}
