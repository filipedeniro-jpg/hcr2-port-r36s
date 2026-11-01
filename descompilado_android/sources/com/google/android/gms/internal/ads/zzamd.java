package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzamd implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzame zzdew;

    zzamd(zzame zzameVar) {
        this.zzdew = zzameVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent intentCreateIntent = this.zzdew.createIntent();
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzatv.zza(this.zzdew.zzlk, intentCreateIntent);
    }
}
