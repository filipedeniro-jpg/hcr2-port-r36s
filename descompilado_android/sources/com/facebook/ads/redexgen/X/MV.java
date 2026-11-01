package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class MV implements DialogInterface.OnClickListener {
    public final /* synthetic */ DialogInterface.OnClickListener B;

    public MV(DialogInterface.OnClickListener onClickListener) {
        this.B = onClickListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int which) {
        this.B.onClick(dialog, which);
    }
}
