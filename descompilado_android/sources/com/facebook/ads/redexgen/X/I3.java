package com.facebook.ads.redexgen.X;

import android.content.DialogInterface;
import android.widget.EditText;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class I3 implements DialogInterface.OnClickListener {
    public final /* synthetic */ I1 B;
    public final /* synthetic */ EditText C;

    public I3(I1 this$0, EditText editText) {
        this.B = this$0;
        this.C = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialog, int id) {
        this.B.C.execute(new I4(this, dialog));
    }
}
