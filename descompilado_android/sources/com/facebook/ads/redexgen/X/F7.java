package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class F7 implements View.OnClickListener {
    public final /* synthetic */ C0510Ez B;

    public F7(C0510Ez this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.B.G.A(EnumC0617Je.AD_CHOICES_CLICKED, null);
        this.B.M.show();
        this.B.I = true;
    }
}
