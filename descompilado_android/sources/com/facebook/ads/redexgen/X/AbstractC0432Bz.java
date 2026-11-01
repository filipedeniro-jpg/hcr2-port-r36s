package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0432Bz extends FrameLayout {
    public final C1 B;
    public boolean C;
    private final JV D;
    private AnonymousClass30 E;

    @Nullable
    private H6 F;
    private EnumC01982z G;

    @Nullable
    private final InterfaceC0578Hq H;
    private final String I;
    private AnonymousClass31 J;

    @Nullable
    private final InterfaceC0525Fo K;
    private int L;

    public abstract void B(AnonymousClass31 anonymousClass31, EnumC01982z enumC01982z);

    public abstract void D();

    public abstract void E(AnonymousClass31 anonymousClass31, EnumC01982z enumC01982z);

    public abstract void G();

    public abstract boolean H();

    public AbstractC0432Bz(Context context, JV adEventManager, String clientToken) {
        this(context, adEventManager, clientToken, null, null);
    }

    public AbstractC0432Bz(Context context, JV adEventManager, String clientToken, @Nullable InterfaceC0578Hq audienceNetworkView, @Nullable InterfaceC0525Fo listener) {
        super(context);
        this.L = 0;
        this.G = EnumC01982z.NONE;
        this.J = null;
        this.B = new C0(this);
        this.D = adEventManager;
        this.H = audienceNetworkView;
        this.K = listener;
        this.I = clientToken;
    }

    public final void A() {
        R();
    }

    public static /* synthetic */ int F(AbstractC0432Bz x0) {
        int i = x0.L;
        x0.L = i + 1;
        return i;
    }

    public final void F() {
        this.E = new AnonymousClass30(new C0618Jf(this.I, this.D));
        if (this.H != null) {
            this.H.nE(true);
        }
        T();
        if (this.F != null) {
            this.F.SD();
        }
    }

    public static /* synthetic */ int G(AbstractC0432Bz x0) {
        int i = x0.L;
        x0.L = i - 1;
        return i;
    }

    public final void I(boolean shouldCloseAd) {
        this.C = shouldCloseAd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        if (this.E.C()) {
            this.D.eC(this.I, this.E.B());
            this.E.A();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(AnonymousClass31 reason) {
        this.E.D(this.G);
        B(reason, this.G);
        if (H()) {
            R();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        this.J = null;
        this.E.H();
        D();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(AnonymousClass31 reason) {
        this.J = reason;
        this.E.F(this.G, this.L);
        E(reason, this.G);
    }

    public void setAdReportingFlowListener(@Nullable H6 listener) {
        this.F = listener;
    }
}
