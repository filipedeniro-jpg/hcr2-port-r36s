package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcdp implements zzboi {
    private final zzarv zzbmt;
    private final Context zzlk;

    zzcdp(Context context, zzarv zzarvVar) {
        this.zzlk = context;
        this.zzbmt = zzarvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(zzcvj zzcvjVar) {
        if (TextUtils.isEmpty(zzcvjVar.zzgjp.zzgjm.zzdle)) {
            return;
        }
        this.zzbmt.zza(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs);
        this.zzbmt.zzj(this.zzlk, zzcvjVar.zzgjp.zzgjm.zzdle);
    }
}
