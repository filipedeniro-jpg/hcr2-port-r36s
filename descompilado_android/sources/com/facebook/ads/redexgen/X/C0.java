package com.facebook.ads.redexgen.X;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0 implements C1 {
    public final /* synthetic */ AbstractC0432Bz B;

    public C0(AbstractC0432Bz this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void MC() {
        if (this.B.J == null) {
            hB(false);
            return;
        }
        AbstractC0432Bz.G(this.B);
        if (this.B.J.D() == null) {
            this.B.T();
        } else {
            this.B.U(this.B.J.D());
        }
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void ZC() {
        if (!TextUtils.isEmpty(C01972y.J(this.B.getContext()))) {
            I7.B(new I7(), this.B.getContext(), Uri.parse(C01972y.J(this.B.getContext())), this.B.I);
        }
        this.B.E.E();
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void aC() {
        this.B.G();
        if (this.B.H != null) {
            this.B.H.yE(true);
        }
        if (!TextUtils.isEmpty(C01972y.B(this.B.getContext()))) {
            I7.B(new I7(), this.B.getContext(), Uri.parse(C01972y.B(this.B.getContext())), this.B.I);
        }
        this.B.E.I();
        this.B.R();
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void bE(EnumC01982z adReportingFlowType) {
        AnonymousClass31 anonymousClass31M;
        AbstractC0432Bz.F(this.B);
        this.B.G = adReportingFlowType;
        if (this.B.G == EnumC01982z.HIDE) {
            anonymousClass31M = C01972y.G(this.B.getContext());
        } else {
            anonymousClass31M = C01972y.M(this.B.getContext());
        }
        this.B.U(anonymousClass31M);
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void gB() {
        if (this.B.K != null) {
            this.B.K.aB("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW");
        }
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void hB(boolean endOfReporting) {
        this.B.G();
        if (this.B.H != null) {
            this.B.H.yE(true);
        }
        if (this.B.F != null) {
            this.B.F.QD(endOfReporting);
        }
        if (!endOfReporting) {
            this.B.R();
        }
    }

    @Override // com.facebook.ads.redexgen.X.C1
    public final void jE(AnonymousClass31 newMenu) {
        AbstractC0432Bz.F(this.B);
        this.B.E.G(newMenu.C());
        if (!newMenu.B().isEmpty()) {
            this.B.U(newMenu);
            return;
        }
        this.B.S(newMenu);
        if (this.B.F != null) {
            this.B.F.RD(newMenu, this.B.G);
        }
    }
}
