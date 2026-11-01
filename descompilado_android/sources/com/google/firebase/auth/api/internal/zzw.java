package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzw implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzw(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        com.google.android.gms.internal.firebase_auth.zzey zzeyVar2 = zzeyVar;
        zzfl zzflVar = new zzfl();
        zzflVar.zzb(zzeyVar2.zzd()).zzc(null).zzd(null);
        this.zzb.zza(this.zza, zzeyVar2, zzflVar, this);
    }
}
