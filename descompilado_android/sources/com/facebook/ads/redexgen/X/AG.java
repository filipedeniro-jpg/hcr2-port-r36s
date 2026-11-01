package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AG {
    private final JV B;
    private final Context C;
    private final C01481b D;
    private final InterfaceC0525Fo E;
    private final View F;

    @Nullable
    private View G;

    @Nullable
    private D4 H;

    @Nullable
    private C02605j J;

    @Nullable
    private C0510Ez K;
    private final C0621Ji M;
    private final C5I N;
    private int L = 0;
    private int I = 1;

    public AG(Context context, JV adEventManager, InterfaceC0525Fo listener, C01481b dataBundle, View mediaView, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        this.C = context;
        this.B = adEventManager;
        this.E = listener;
        this.D = dataBundle;
        this.F = mediaView;
        this.N = viewabilityChecker;
        this.M = touchDataRecorder;
    }

    public final AF A() {
        return new AF(this);
    }

    public final AG B(View muteButton) {
        this.G = muteButton;
        return this;
    }

    public final AG C(D4 onAdShownListener) {
        this.H = onAdShownListener;
        return this;
    }

    public final AG D(int orientation) {
        this.I = orientation;
        return this;
    }

    public final AG E(C02605j progressBar) {
        this.J = progressBar;
        return this;
    }

    public final AG F(C0510Ez toolbar) {
        this.K = toolbar;
        return this;
    }

    public final AG G(int topMargin) {
        this.L = topMargin;
        return this;
    }
}
