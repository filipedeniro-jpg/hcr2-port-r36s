package com.google.firebase.auth.api.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfz;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzs implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    final /* synthetic */ zzdr zza;
    final /* synthetic */ zza zzb;
    private final /* synthetic */ zzfz zzc;
    private final /* synthetic */ Context zzd = null;

    zzs(zza zzaVar, zzfz zzfzVar, Context context, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zzc = zzfzVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        this.zzc.zza(zzeyVar.zzd());
        this.zzb.zzb.zza(this.zzd, this.zzc, new zzr(this, this));
    }
}
