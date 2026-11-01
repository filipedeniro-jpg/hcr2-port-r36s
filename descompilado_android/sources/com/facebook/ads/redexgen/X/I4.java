package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class I4 implements Runnable {
    public final /* synthetic */ I3 B;
    public final /* synthetic */ DialogInterface C;

    public I4(I3 this$1, DialogInterface dialogInterface) {
        this.B = this$1;
        this.C = dialogInterface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.B.D.D(C0661Ky.D(), this.B.B.D.C().B(this.B.B.F(this.B.C.getText().toString())));
        this.C.cancel();
    }
}
