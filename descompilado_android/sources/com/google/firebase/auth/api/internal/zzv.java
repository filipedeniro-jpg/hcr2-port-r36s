package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzen;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzv implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    final /* synthetic */ String zza;
    final /* synthetic */ zzdr zzb;
    final /* synthetic */ zza zzc;

    zzv(zza zzaVar, String str, zzdr zzdrVar) {
        this.zzc = zzaVar;
        this.zza = str;
        this.zzb = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zzb.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        com.google.android.gms.internal.firebase_auth.zzey zzeyVar2 = zzeyVar;
        this.zzc.zzb.zza(new zzen(zzeyVar2.zzd()), new zzy(this, this, zzeyVar2));
    }
}
