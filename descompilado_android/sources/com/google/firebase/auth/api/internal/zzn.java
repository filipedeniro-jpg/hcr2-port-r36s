package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfm;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzn implements zzfe<zzfm> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzn(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
        zzfc unused = this.zzb.zzb;
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzfm zzfmVar) {
        this.zza.zzb(zzfmVar.zzb());
        zzfc unused = this.zzb.zzb;
    }
}
