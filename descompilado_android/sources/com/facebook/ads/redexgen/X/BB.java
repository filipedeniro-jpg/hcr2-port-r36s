package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class BB extends IE {
    private static final String P = BB.class.getSimpleName();
    private final WeakReference<C0V> B;
    private final AtomicBoolean C;
    private final AtomicBoolean D;
    private final Path E;
    private boolean F;
    private boolean G;

    @Nullable
    private WeakReference<BG> H;
    private float I;
    private final RectF J;
    private final AtomicReference<String> K;
    private C0621Ji L;

    @Nullable
    private C5I M;
    private C1B N;
    private final AtomicInteger O;

    public BB(Context context, WeakReference<C0V> weakReference, int minViewablePercentage) {
        super(context);
        this.C = new AtomicBoolean();
        this.D = new AtomicBoolean(true);
        this.E = new Path();
        this.J = new RectF();
        this.O = new AtomicInteger(5000);
        this.K = new AtomicReference<>();
        this.L = new C0621Ji();
        this.G = true;
        this.F = JA.QB(context);
        this.B = weakReference;
        this.N = new BI(this);
        this.M = new C5I(this, minViewablePercentage, this.N);
        setWebChromeClient(A());
        setWebViewClient(mo240B());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        if (Build.VERSION.SDK_INT > 16) {
            addJavascriptInterface(new BH(this, weakReference.get(), this.M, this.C, this.D, this.F), "AdControl");
        }
    }

    @Override // com.facebook.ads.redexgen.X.IE
    public final WebChromeClient A() {
        return new BE();
    }

    @Override // com.facebook.ads.redexgen.X.IE
    /* JADX INFO: renamed from: B */
    public final WebViewClient mo240B() {
        return new BC(getContext(), this.B, new WeakReference(this.M), new WeakReference(this.L), new WeakReference(this.D), new WeakReference(this), this.O, this.K);
    }

    public final void D(int viewabilityCheckInitialDelayMs, int viewabilityCheckIntervalMs) {
        if (this.M != null) {
            this.M.D(viewabilityCheckInitialDelayMs);
            this.M.E(viewabilityCheckIntervalMs);
        }
    }

    @VisibleForTesting
    private final boolean G() {
        return !this.F || this.C.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        this.C.set(true);
        new Handler(Looper.getMainLooper()).post(new BF(this.M));
        if (this.H != null && this.H.get() != null) {
            this.H.get().XD();
        }
    }

    @Override // com.facebook.ads.redexgen.X.IE, android.webkit.WebView
    public final void destroy() {
        if (this.M != null) {
            this.M.G();
            this.M = null;
        }
        J6.M(this);
        this.N = null;
        this.L = null;
        ID.C(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.L.m258B();
    }

    public C0621Ji getTouchDataRecorder() {
        return this.L;
    }

    public C5I getViewabilityChecker() {
        return this.M;
    }

    @Override // android.webkit.WebView, android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.I > 0.0f) {
            this.J.set(0.0f, 0.0f, getWidth(), getHeight());
            this.E.reset();
            this.E.addRoundRect(this.J, this.I, this.I, Path.Direction.CW);
            canvas.clipPath(this.E);
        }
        super.onDraw(canvas);
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        this.L.F(event, this, this);
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

    @Override // android.webkit.WebView, android.view.View
    public final void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (this.B.get() != null) {
            this.B.get().RF(visibility);
        }
        if (this.M == null) {
            return;
        }
        if (visibility == 0 && G()) {
            this.M.F();
        } else {
            if (visibility != 8) {
                return;
            }
            this.M.G();
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean checkAssetsByJavascriptBridge) {
        this.D.set(checkAssetsByJavascriptBridge);
    }

    public void setCornerRadius(float mRadius) {
        this.I = mRadius;
        invalidate();
    }

    public void setLogMultipleImpressions(boolean logMultipleImpressions) {
        this.G = logMultipleImpressions;
    }

    public void setOnAssetsLoadedListener(BG onAssetsLoadedListener) {
        this.H = new WeakReference<>(onAssetsLoadedListener);
    }

    public void setRequestId(String mRequestId) {
        this.K.set(mRequestId);
    }

    public void setWaitForAssetsToLoad(boolean waitForAssetsToLoad) {
        this.F = waitForAssetsToLoad;
    }

    public void setWebViewTimeoutInMillis(int webViewTimeoutInMillis) {
        if (webViewTimeoutInMillis >= 0) {
            this.O.set(webViewTimeoutInMillis);
        }
    }
}
