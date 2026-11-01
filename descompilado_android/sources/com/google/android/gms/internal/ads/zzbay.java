package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbay implements DialogInterface.OnCancelListener {
    private final /* synthetic */ JsPromptResult zzedu;

    zzbay(JsPromptResult jsPromptResult) {
        this.zzedu = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        this.zzedu.cancel();
    }
}
