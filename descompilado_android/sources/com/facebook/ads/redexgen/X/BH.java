package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BH {
    private final String B = BH.class.getSimpleName();
    private final WeakReference<AtomicBoolean> C;
    private final WeakReference<AtomicBoolean> D;
    private final boolean E;
    private final WeakReference<C0V> F;
    private final WeakReference<C5I> G;
    private final WeakReference<BB> H;

    public BH(BB webView, C0V listener, C5I viewabilityChecker, AtomicBoolean assetsLoadedBoolean, AtomicBoolean checkAssetsByJavascriptBridge, boolean postAssetLoadingImpressionLogging) {
        this.H = new WeakReference<>(webView);
        this.F = new WeakReference<>(listener);
        this.G = new WeakReference<>(viewabilityChecker);
        this.C = new WeakReference<>(assetsLoadedBoolean);
        this.D = new WeakReference<>(checkAssetsByJavascriptBridge);
        this.E = postAssetLoadingImpressionLogging;
    }

    @JavascriptInterface
    public void alert(String message) {
        Log.e(this.B, message);
    }

    @JavascriptInterface
    public String getAnalogInfo() {
        return MJ.C(IK.F());
    }

    @JavascriptInterface
    public void onMainAssetLoaded() {
        if (this.H.get() == null || this.C.get() == null || this.D.get() == null || !this.E || !this.D.get().get()) {
            return;
        }
        this.C.get().set(true);
        if (!this.H.get().isShown()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new BF(this.G));
    }

    @JavascriptInterface
    public void onPageInitialized() {
        BB webView = this.H.get();
        if (webView == null || webView.C()) {
            return;
        }
        C0V c0v = this.F.get();
        if (c0v != null) {
            c0v.lE();
        }
        if (this.E || !this.H.get().isShown()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new BF(this.G));
    }
}
