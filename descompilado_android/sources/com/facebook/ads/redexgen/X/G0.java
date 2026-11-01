package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G0 implements View.OnLongClickListener {
    public final /* synthetic */ G1 B;

    public G0(G1 this$1) {
        this.B = this$1;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View v) {
        if (this.B.B.N == null || this.B.B.B == null) {
            return false;
        }
        this.B.B.B.setBounds(0, 0, this.B.B.N.getWidth(), this.B.B.N.getHeight());
        this.B.B.B.C(this.B.B.B.A() ? false : true);
        return true;
    }
}
