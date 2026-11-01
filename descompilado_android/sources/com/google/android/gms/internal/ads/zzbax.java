package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbax implements DialogInterface.OnCancelListener {
    private final /* synthetic */ JsResult zzedt;

    zzbax(JsResult jsResult) {
        this.zzedt = jsResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        this.zzedt.cancel();
    }
}
