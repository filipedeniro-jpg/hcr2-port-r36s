package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.io.File;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ll, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0674Ll {
    private File B;
    private M0 C = new MA(67108864);
    private M2 D = new M8();

    public C0674Ll(Context context) {
        this.B = C0688Lz.B(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public C0668Lf C() {
        return new C0668Lf(this.B, this.D, this.C);
    }
}
