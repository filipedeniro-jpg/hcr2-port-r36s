package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AC implements View.OnClickListener {
    public final /* synthetic */ A3 B;

    public AC(A3 this$0) {
        this.B = this$0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        if (this.B.E.G() && this.B.Q != null && this.B.Q.getToolbarListener() != null) {
            this.B.Q.getToolbarListener().jD();
        } else if (this.B.S != null) {
            this.B.S.jD();
        }
    }
}
