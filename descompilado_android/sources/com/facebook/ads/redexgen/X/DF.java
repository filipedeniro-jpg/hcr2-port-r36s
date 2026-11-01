package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DF implements DG {
    private final WeakReference<Activity> B;
    private final C01481b C;
    private final JV D;
    private final WeakReference<DE> E;
    private final WeakReference<InterfaceC0525Fo> F;

    public DF(Activity activity, DE interstitialPlayableView, C01481b adData, JV adEventManager, InterfaceC0525Fo listener) {
        this.B = new WeakReference<>(activity);
        this.E = new WeakReference<>(interstitialPlayableView);
        this.C = adData;
        this.D = adEventManager;
        this.F = new WeakReference<>(listener);
    }

    private B0 B(Context context, BB adWebView, InterfaceC0525Fo listener) {
        return new B0(context, true, false, "com.facebook.ads.interstitial.clicked", this.C.D().B(), this.D, listener, adWebView.getViewabilityChecker(), adWebView.getTouchDataRecorder());
    }

    private void C() {
        if (this.B.get() != null) {
            this.B.get().finish();
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void IE(C5I adViewabilityChecker, C0621Ji touchDataRecorder) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(this.C.C())) {
            adViewabilityChecker.A(map);
            map.put("touch", MJ.C(touchDataRecorder.m258B()));
            this.D.lC(this.C.C(), map);
            if (this.F.get() != null) {
                this.F.get().aB("com.facebook.ads.interstitial.impression.logged");
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void WE() {
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void YE() {
        if (this.F.get() != null) {
            this.F.get().aB("com.facebook.ads.interstitial.error");
        }
        C();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void iD() {
        C();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void oD(boolean skipPlayStore, Map<String, String> map) {
        if (this.E.get() == null || this.E.get().C.getAdWebView() == null || this.F.get() == null) {
            return;
        }
        B0 b0B = B(this.E.get().getContext(), this.E.get().C.getAdWebView(), this.F.get());
        b0B.setCta(this.C.A().get(0).A(), this.C.C(), map);
        b0B.setActionEnabled(skipPlayStore ? false : true);
        b0B.performClick();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void qE() {
        if (this.E.get() != null) {
            this.E.get().D = true;
        }
    }
}
