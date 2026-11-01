package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;
import com.facebook.internal.ServerProtocol;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class B4 implements DialogInterface.OnClickListener {
    public final /* synthetic */ B2 B;
    public final /* synthetic */ B0 C;

    public B4(B2 this$0, B0 b0) {
        this.B = this$0;
        this.C = b0;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int which) {
        this.B.E.put("is_two_step", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        this.B.C(this.C);
    }
}
