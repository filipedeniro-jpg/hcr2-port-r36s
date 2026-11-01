package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgu {
    private final zzhh zza;
    private final byte[] zzb;

    private zzgu(int i) {
        this.zzb = new byte[i];
        this.zza = zzhh.zza(this.zzb);
    }

    public final zzgm zza() {
        this.zza.zzb();
        return new zzgw(this.zzb);
    }

    public final zzhh zzb() {
        return this.zza;
    }

    /* synthetic */ zzgu(int i, zzgp zzgpVar) {
        this(i);
    }
}
