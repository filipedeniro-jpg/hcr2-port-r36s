package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfq;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzb implements zzfe<zzfq> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzb(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzfq zzfqVar) {
        zzfq zzfqVar2 = zzfqVar;
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzey(zzfqVar2.zzc(), zzfqVar2.zzb(), Long.valueOf(zzfqVar2.zzd()), "Bearer"), null, null, true, null, this.zza, this);
    }
}
