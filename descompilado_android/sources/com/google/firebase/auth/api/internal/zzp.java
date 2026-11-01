package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzp implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzdr zzc;
    private final /* synthetic */ zza zzd;

    zzp(zza zzaVar, String str, String str2, zzdr zzdrVar) {
        this.zzd = zzaVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zzc.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        com.google.android.gms.internal.firebase_auth.zzey zzeyVar2 = zzeyVar;
        zzfl zzflVar = new zzfl();
        zzflVar.zzb(zzeyVar2.zzd()).zzc(this.zza).zzd(this.zzb);
        this.zzd.zza(this.zzc, zzeyVar2, zzflVar, this);
    }
}
