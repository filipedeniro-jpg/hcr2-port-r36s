package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class I5 implements DialogInterface.OnClickListener {
    public final /* synthetic */ I1 B;

    public I5(I1 this$0) {
        this.B = this$0;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
    }
}
