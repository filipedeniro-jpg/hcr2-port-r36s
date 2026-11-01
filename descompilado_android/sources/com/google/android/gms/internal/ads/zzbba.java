package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbba implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzedu;
    private final /* synthetic */ EditText zzedv;

    zzbba(JsPromptResult jsPromptResult, EditText editText) {
        this.zzedu = jsPromptResult;
        this.zzedv = editText;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzedu.confirm(this.zzedv.getText().toString());
    }
}
