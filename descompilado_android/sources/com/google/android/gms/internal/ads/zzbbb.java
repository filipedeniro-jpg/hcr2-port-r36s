package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbbb implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsPromptResult zzedu;

    zzbbb(JsPromptResult jsPromptResult) {
        this.zzedu = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzedu.cancel();
    }
}
