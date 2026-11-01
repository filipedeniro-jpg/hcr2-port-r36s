package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzf implements zzfe<com.google.android.gms.internal.firebase_auth.zzeq> {
    private final /* synthetic */ zzfb zza;
    private final /* synthetic */ zzdr zzb;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzey zzc;
    private final /* synthetic */ zzfl zzd;
    private final /* synthetic */ zza zze;

    zzf(zza zzaVar, zzfb zzfbVar, zzdr zzdrVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar, zzfl zzflVar) {
        this.zze = zzaVar;
        this.zza = zzfbVar;
        this.zzb = zzdrVar;
        this.zzc = zzeyVar;
        this.zzd = zzflVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzeq zzeqVar) {
        List<com.google.android.gms.internal.firebase_auth.zzes> listZzb = zzeqVar.zzb();
        if (listZzb == null || listZzb.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zze.zza(this.zzb, this.zzc, listZzb.get(0), this.zzd, this.zza);
        }
    }
}
