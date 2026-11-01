package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzju implements zzje {
    private final zzjg zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    zzju(zzjg zzjgVar, String str, Object[] objArr) {
        this.zza = zzjgVar;
        this.zzb = str;
        this.zzc = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.zzd = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.zzd = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    final String zzd() {
        return this.zzb;
    }

    final Object[] zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzje
    public final zzjg zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzje
    public final int zza() {
        return (this.zzd & 1) == 1 ? zzhx.zze.zzh : zzhx.zze.zzi;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzje
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }
}
