package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class KC implements DialogInterface.OnClickListener {
    public final /* synthetic */ KD B;

    public KC(KD this$1) {
        this.B = this$1;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int which) {
        if (this.B.B.B == null) {
            return;
        }
        this.B.B.B.e(this.B.D());
    }
}
