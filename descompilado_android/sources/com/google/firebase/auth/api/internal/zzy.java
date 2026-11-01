package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzy implements zzfe<com.google.android.gms.internal.firebase_auth.zzeq> {
    private final /* synthetic */ zzfe zza;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzey zzb;
    private final /* synthetic */ zzv zzc;

    zzy(zzv zzvVar, zzfe zzfeVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        this.zzc = zzvVar;
        this.zza = zzfeVar;
        this.zzb = zzeyVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zzc.zzb.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzeq zzeqVar) {
        List<com.google.android.gms.internal.firebase_auth.zzes> listZzb = zzeqVar.zzb();
        if (listZzb == null || listZzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        com.google.android.gms.internal.firebase_auth.zzes zzesVar = listZzb.get(0);
        zzfl zzflVar = new zzfl();
        zzflVar.zzb(this.zzb.zzd()).zzg(this.zzc.zza);
        this.zzc.zzc.zza(this.zzc.zzb, this.zzb, zzesVar, zzflVar, this.zza);
    }
}
