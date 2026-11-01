package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzgb;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzq implements zzfe<zzgb> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzq(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzgb zzgbVar) {
        zzgb zzgbVar2 = zzgbVar;
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzey(zzgbVar2.zzc(), zzgbVar2.zzb(), Long.valueOf(zzgbVar2.zzd()), "Bearer"), null, null, Boolean.valueOf(zzgbVar2.zze()), null, this.zza, this);
    }
}
