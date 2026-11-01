package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgt extends zzgw {
    private final int zzc;
    private final int zzd;

    zzgt(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgw, com.google.android.gms.internal.firebase_auth.zzgm
    public final byte zza(int i) {
        int iZza = zza();
        if (((iZza - (i + 1)) | i) >= 0) {
            return this.zzb[this.zzc + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(iZza);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgw, com.google.android.gms.internal.firebase_auth.zzgm
    final byte zzb(int i) {
        return this.zzb[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgw, com.google.android.gms.internal.firebase_auth.zzgm
    public final int zza() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgw
    protected final int zze() {
        return this.zzc;
    }
}
