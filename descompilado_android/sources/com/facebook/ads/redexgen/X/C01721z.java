package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.webkit.WebView;
import com.facebook.ads.AdError;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1z, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C01721z implements InterfaceC01280h {
    public final H2 B;
    public final Context C;
    public final boolean D;
    public final WeakReference<InterfaceC01410u> E;
    public final C01571k F;

    private C01721z(Context context, InterfaceC01410u listener, H2 cacheManager, C01571k playableAdData, boolean failOnCacheFailure) {
        this.C = context;
        this.E = new WeakReference<>(listener);
        this.B = cacheManager;
        this.F = playableAdData;
        this.D = failOnCacheFailure;
    }

    public /* synthetic */ C01721z(Context x0, InterfaceC01410u x1, H2 x2, C01571k x3, boolean x4, C01711y x5) {
        this(x0, x1, x2, x3, x4);
    }

    private void B() {
        WebView webView = new WebView(this.C);
        webView.getSettings().setCacheMode(1);
        webView.setWebViewClient(new AnonymousClass21(this.F, this.E, this.D));
        webView.loadUrl(this.F.D());
    }

    private void C(boolean successfulCache) {
        if (this.E.get() == null) {
            return;
        }
        if (this.F.F() == EnumC01591m.WEBVIEW_PRECACHE) {
            B();
            return;
        }
        String strD = this.F.D();
        if (successfulCache) {
            if (this.F.F() == EnumC01591m.FILE_PRECACHE) {
                strD = this.B.I(this.F.D());
            } else {
                strD = this.B.K(this.F.D());
            }
        }
        this.F.L(strD);
        this.E.get().sE();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void dD() {
        if (this.E.get() == null) {
            return;
        }
        if (this.D) {
            this.E.get().rE(AdError.CACHE_ERROR);
        } else {
            C(false);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void nD() {
        C(true);
    }
}
