package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzai implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzdr zzb;
    private final /* synthetic */ zza zzc;

    zzai(zza zzaVar, String str, zzdr zzdrVar) {
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
        zzfl zzflVar = new zzfl();
        zzflVar.zzb(zzeyVar2.zzd()).zzc(this.zza);
        this.zzc.zza(this.zzb, zzeyVar2, zzflVar, this);
    }
}
