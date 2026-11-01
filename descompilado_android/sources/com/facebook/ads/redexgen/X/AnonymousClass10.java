package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.10, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass10 extends C0Q {
    private static final String F = AnonymousClass10.class.getSimpleName();
    private boolean B;
    private C01450y C;
    private final JV D;
    private final IE E;

    public AnonymousClass10(Context context, JV adEventManager, IE webView, C5I viewabilityChecker, C0R impressionHelper) {
        super(context, impressionHelper, viewabilityChecker);
        this.D = adEventManager;
        this.E = webView;
    }

    @Override // com.facebook.ads.redexgen.X.C0Q
    public final void B(Map<String, String> map) {
        if (this.C != null && !TextUtils.isEmpty(this.C.AC())) {
            this.D.lC(this.C.AC(), map);
        }
    }

    /* JADX INFO: renamed from: C, reason: collision with other method in class */
    public final synchronized void m225C() {
        Looper looper;
        if (!this.B && this.C != null) {
            this.B = true;
            if (this.E != null && !TextUtils.isEmpty(this.C.A())) {
                Handler handler = this.E.getHandler();
                if (handler != null && handler.getLooper() == Looper.getMainLooper()) {
                    handler.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.0z
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (this.B.E.C()) {
                                Log.w(AnonymousClass10.F, "Webview already destroyed, cannot activate");
                            } else {
                                this.B.E.loadUrl("javascript:" + this.B.C.A());
                            }
                        }
                    });
                } else {
                    Context context = this.E.getContext();
                    int i = C0584Hw.uB;
                    StringBuilder sbAppend = new StringBuilder().append("WebView Handler is ").append(handler).append(", with Looper ");
                    if (handler != null) {
                        looper = handler.getLooper();
                    } else {
                        looper = null;
                    }
                    C0585Hx.D(context, "web_view", i, new C0583Hv("Can't post Runnable to WebView.", sbAppend.append(looper).append(" Is destroyed: ").append(this.E.C()).append(E()).toString()));
                }
            }
        }
    }

    public final void D(C01450y mAdDataModel) {
        this.C = mAdDataModel;
    }

    private String E() {
        return Build.VERSION.SDK_INT >= 19 ? " Is attached: " + this.E.isAttachedToWindow() : "";
    }
}
