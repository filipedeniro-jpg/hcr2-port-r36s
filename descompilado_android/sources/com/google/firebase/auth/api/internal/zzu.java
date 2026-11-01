package com.google.firebase.auth.api.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfr;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzu implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    final /* synthetic */ zzdr zza;
    final /* synthetic */ zza zzb;
    private final /* synthetic */ zzfr zzc;

    zzu(zza zzaVar, zzfr zzfrVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zzc = zzfrVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        com.google.android.gms.internal.firebase_auth.zzey zzeyVar2 = zzeyVar;
        if (this.zzb.zzc.zza()) {
            this.zzc.zzc(true);
        }
        this.zzc.zza(zzeyVar2.zzd());
        this.zzb.zzb.zza((Context) null, this.zzc, new zzt(this, this));
    }
}
