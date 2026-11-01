package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzft;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzt implements zzfe<zzft> {
    private final /* synthetic */ zzfe zza;
    private final /* synthetic */ zzu zzb;

    zzt(zzu zzuVar, zzfe zzfeVar) {
        this.zzb = zzuVar;
        this.zza = zzfeVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzft zzftVar) {
        this.zzb.zzb.zza(zzftVar, this.zzb.zza, this);
    }
}
