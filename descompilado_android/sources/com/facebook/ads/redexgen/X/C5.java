package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class C5 implements View.OnClickListener {
    public final /* synthetic */ C2 B;

    public C5(C2 this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.B.D != null) {
            if (this.B.E) {
                this.B.D.gB();
            } else {
                this.B.D.hB(true);
            }
        }
    }
}
