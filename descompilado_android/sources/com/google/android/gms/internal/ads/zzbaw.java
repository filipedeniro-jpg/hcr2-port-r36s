package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbaw implements DialogInterface.OnClickListener {
    private final /* synthetic */ JsResult zzedt;

    zzbaw(JsResult jsResult) {
        this.zzedt = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzedt.cancel();
    }
}
