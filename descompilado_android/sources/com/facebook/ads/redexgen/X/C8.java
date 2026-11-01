package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class C8 implements View.OnClickListener {
    public final /* synthetic */ C7 B;

    public C8(C7 this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        this.B.D.aB("performCtaClick");
    }
}
