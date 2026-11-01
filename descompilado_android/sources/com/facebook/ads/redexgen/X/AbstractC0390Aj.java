package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Aj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0390Aj extends RelativeLayout {
    public static final int E = (int) (16.0f * J6.B);
    public static final int F = (int) (28.0f * J6.B);
    private final JV B;
    private final B0 C;
    private final C0394An D;

    public abstract boolean B();

    public AbstractC0390Aj(AF params, C1Y colors, boolean textInsideMedia) {
        super(params.B());
        this.B = params.A();
        this.C = new B0(params.B(), D(), C(), "com.facebook.ads.interstitial.clicked", colors, params.A(), params.D(), params.M(), params.L());
        J6.T(1001, this.C);
        this.D = new C0394An(getContext(), colors, textInsideMedia, N(), O());
        J6.U(this.D);
    }

    public boolean A() {
        return false;
    }

    public boolean C() {
        return true;
    }

    public boolean D() {
        return true;
    }

    public void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState, @Nullable D4 onAdShownListener) {
        this.D.A(adInfo.D().H(), adInfo.D().C(), null, false, !B() && aspectRatio > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && aspectRatio < 1.0d);
        this.C.setCta(adInfo.A(), clientToken, new HashMap());
    }

    public void F() {
    }

    public void G(Bundle savedInstanceState) {
    }

    public void H(AnonymousClass76 event) {
    }

    public final void I(AnonymousClass75 event) {
    }

    public final void J(AnonymousClass72 event) {
    }

    public final void K(AnonymousClass71 event) {
    }

    public final void L(AnonymousClass70 event) {
    }

    public void M(C03026z event) {
    }

    public boolean N() {
        return true;
    }

    public boolean O() {
        return true;
    }

    public JV getAdEventManager() {
        return this.B;
    }

    public B0 getCtaButton() {
        return this.C;
    }

    public int getExactMediaHeightIfAvailable() {
        return 0;
    }

    public int getExactMediaWidthIfAvailable() {
        return 0;
    }

    public C0394An getTitleDescContainer() {
        return this.D;
    }
}
