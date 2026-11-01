package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class B3 implements DialogInterface.OnClickListener {
    public final /* synthetic */ B2 B;
    public final /* synthetic */ B0 C;

    public B3(B2 this$0, B0 b0) {
        this.B = this$0;
        this.C = b0;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int which) {
        if (this.C.B != null) {
            this.C.B.AD(this.B.D, this.B.E);
        }
    }
}
