package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.firebase.auth.EmailAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzd implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    private final /* synthetic */ EmailAuthCredential zza;
    private final /* synthetic */ zzdr zzb;
    private final /* synthetic */ zza zzc;

    zzd(zza zzaVar, EmailAuthCredential emailAuthCredential, zzdr zzdrVar) {
        this.zzc = zzaVar;
        this.zza = emailAuthCredential;
        this.zzb = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zzb.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        this.zzc.zza(new com.google.android.gms.internal.firebase_auth.zzej(this.zza, zzeyVar.zzd()), this.zzb);
    }
}
