package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class F5 implements View.OnClickListener {
    public final /* synthetic */ C0510Ez B;
    public final /* synthetic */ String C;

    public F5(C0510Ez this$0, String str) {
        this.B = this$0;
        this.C = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.B.G.A(EnumC0617Je.AD_REPORT_CLICKED, null);
        this.B.C.YC(this.C, true, null);
    }
}
