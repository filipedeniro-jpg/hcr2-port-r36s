package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;
import com.facebook.internal.ServerProtocol;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class KB implements DialogInterface.OnClickListener {
    public final /* synthetic */ KD B;

    public KB(KD this$1) {
        this.B = this$1;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int which) {
        Map mapD = this.B.D();
        mapD.put("is_two_step", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        this.B.E(mapD);
    }
}
