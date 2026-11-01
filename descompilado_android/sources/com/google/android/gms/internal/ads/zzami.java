package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzami implements View.OnClickListener {
    private final /* synthetic */ zzamf zzdfr;

    zzami(zzamf zzamfVar) {
        this.zzdfr = zzamfVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzdfr.zzv(true);
    }
}
