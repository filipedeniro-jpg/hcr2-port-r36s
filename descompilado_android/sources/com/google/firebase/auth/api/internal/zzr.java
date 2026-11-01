package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzgb;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzr implements zzfe<zzgb> {
    private final /* synthetic */ zzfe zza;
    private final /* synthetic */ zzs zzb;

    zzr(zzs zzsVar, zzfe zzfeVar) {
        this.zzb = zzsVar;
        this.zza = zzfeVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzgb zzgbVar) {
        zzgb zzgbVar2 = zzgbVar;
        if (!TextUtils.isEmpty(zzgbVar2.zzf())) {
            this.zzb.zza.zza(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zza(zzgbVar2.zzg(), zzgbVar2.zzf()));
        } else {
            this.zzb.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzey(zzgbVar2.zzc(), zzgbVar2.zzb(), Long.valueOf(zzgbVar2.zzd()), "Bearer"), null, "phone", Boolean.valueOf(zzgbVar2.zze()), null, this.zzb.zza, this.zza);
        }
    }
}
