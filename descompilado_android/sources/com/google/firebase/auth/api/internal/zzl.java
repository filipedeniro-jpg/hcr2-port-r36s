package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfv;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzl implements zzfe<zzfv> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzl(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzfv zzfvVar) {
        zzfv zzfvVar2 = zzfvVar;
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzey(zzfvVar2.zzc(), zzfvVar2.zzb(), Long.valueOf(zzfvVar2.zzd()), "Bearer"), null, null, Boolean.valueOf(zzfvVar2.zze()), null, this.zza, this);
    }
}
