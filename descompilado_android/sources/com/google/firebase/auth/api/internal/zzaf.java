package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfl;
import com.google.firebase.auth.UserProfileChangeRequest;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaf implements zzfe<com.google.android.gms.internal.firebase_auth.zzey> {
    private final /* synthetic */ UserProfileChangeRequest zza;
    private final /* synthetic */ zzdr zzb;
    private final /* synthetic */ zza zzc;

    zzaf(zza zzaVar, UserProfileChangeRequest userProfileChangeRequest, zzdr zzdrVar) {
        this.zzc = zzaVar;
        this.zza = userProfileChangeRequest;
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
        zzflVar.zzb(zzeyVar2.zzd());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzflVar.zze(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzflVar.zzf(this.zza.zza());
        }
        this.zzc.zza(this.zzb, zzeyVar2, zzflVar, this);
    }
}
