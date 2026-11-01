package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzft;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzx implements zzfe<zzft> {
    private final /* synthetic */ zzdr zza;
    private final /* synthetic */ zza zzb;

    zzx(zza zzaVar, zzdr zzdrVar) {
        this.zzb = zzaVar;
        this.zza = zzdrVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzy.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzft zzftVar) {
        zzft zzftVar2 = zzftVar;
        if (!zzftVar2.zzo()) {
            this.zzb.zza(zzftVar2, this.zza, this);
        } else if (this.zzb.zzc.zzb()) {
            this.zza.zza(new com.google.android.gms.internal.firebase_auth.zzed(zzftVar2.zzn(), zzftVar2.zzm(), zzftVar2.zzp()));
        } else {
            zza.zza.e("Need to do multi-factor auth, but SDK does not support it.", new Object[0]);
            zza("REQUIRES_SECOND_FACTOR_AUTH");
        }
    }
}
