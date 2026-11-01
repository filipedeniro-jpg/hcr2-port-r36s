package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class C4 implements View.OnClickListener {
    public final /* synthetic */ C2 B;

    public C4(C2 this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        if (this.B.D != null) {
            this.B.D.ZC();
        }
    }
}
