package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(19)
public final class BJ extends IE {
    private long B;
    private InterfaceC0569Hh C;
    private long D;
    private long E;
    private long F;
    private BN G;
    private static final String I = BJ.class.getSimpleName();
    private static final Set<String> H = new HashSet(2);

    static {
        H.add("http");
        H.add("https");
    }

    public BJ(Context context) {
        super(context);
        this.E = -1L;
        this.B = -1L;
        this.F = -1L;
        this.D = -1L;
        D();
    }

    public BJ(Context context, InterfaceC0569Hh listener) {
        super(context);
        this.E = -1L;
        this.B = -1L;
        this.F = -1L;
        this.D = -1L;
        this.C = listener;
        setWebChromeClient(A());
        setWebViewClient(mo240B());
        D();
    }

    @Override // com.facebook.ads.redexgen.X.IE
    public final WebChromeClient A() {
        return new BL(new WeakReference(this.C), new WeakReference(this.G));
    }

    @Override // com.facebook.ads.redexgen.X.IE
    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final WebViewClient mo240B() {
        return new BK(new WeakReference(this.C), new WeakReference(getContext()));
    }

    private void D() {
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.G = new BN(this);
    }

    public final void D(String js) {
        try {
            evaluateJavascript(js, null);
        } catch (IllegalStateException e) {
            loadUrl("javascript:" + js);
        }
    }

    private void E() {
        if (this.B > -1 && this.F > -1 && this.D > -1) {
            this.G.C(false);
        }
    }

    public final void E(long timingMs) {
        if (this.B < 0) {
            this.B = timingMs;
        }
        E();
    }

    public final void F(long timingMs) {
        if (this.D < 0) {
            this.D = timingMs;
        }
        E();
    }

    public final void G(long timingMs) {
        if (this.E < 0) {
            this.E = timingMs;
        }
    }

    @Override // com.facebook.ads.redexgen.X.IE, android.webkit.WebView
    public final void destroy() {
        this.C = null;
        ID.C(this);
        super.destroy();
    }

    public long getDomContentLoadedMs() {
        return this.B;
    }

    public String getFirstUrl() {
        WebBackForwardList webBackForwardListCopyBackForwardList = copyBackForwardList();
        return webBackForwardListCopyBackForwardList.getSize() > 0 ? webBackForwardListCopyBackForwardList.getItemAtIndex(0).getUrl() : getUrl();
    }

    public long getLoadFinishMs() {
        return this.D;
    }

    public long getResponseEndMs() {
        return this.E;
    }

    public long getScrollReadyMs() {
        return this.F;
    }

    @Override // android.webkit.WebView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.F < 0 && computeVerticalScrollRange() > getHeight()) {
            this.F = System.currentTimeMillis();
            E();
        }
    }

    public void setListener(InterfaceC0569Hh listener) {
        this.C = listener;
    }
}
