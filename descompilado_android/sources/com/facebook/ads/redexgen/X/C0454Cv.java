package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0454Cv implements MR {
    public final /* synthetic */ AbstractC0466Dh B;
    public final /* synthetic */ int C;

    public C0454Cv(AbstractC0466Dh this$0, int i) {
        this.B = this$0;
        this.C = i;
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void mD() {
        this.B.E.E(true);
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void uE(int curSec) {
        this.B.E.setProgress(100.0f * (1.0f - (curSec / this.C)));
    }
}
