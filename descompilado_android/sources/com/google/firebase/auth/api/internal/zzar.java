package com.google.firebase.auth.api.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzar {
    private final zzdy zza;
    private final zzel zzb;

    public zzar(zzdy zzdyVar, zzel zzelVar) {
        this.zza = zzdyVar;
        this.zzb = zzelVar;
    }

    public final boolean zza() {
        return this.zza.zza().booleanValue() && this.zzb.zza();
    }

    public final boolean zzb() {
        return this.zza.zzb().booleanValue() && this.zzb.zza(this.zza.zzc());
    }
}
