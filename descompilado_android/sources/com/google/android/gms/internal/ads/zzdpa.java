package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpa extends zzdpd {
    private final int zzhfr;
    private final int zzhfs;

    zzdpa(byte[] bArr, int i, int i2) {
        super(bArr);
        zzh(i, i + i2, bArr.length);
        this.zzhfr = i;
        this.zzhfs = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzdpd, com.google.android.gms.internal.ads.zzdot
    public final byte zzfn(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzhfu[this.zzhfr + i];
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
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzdpd, com.google.android.gms.internal.ads.zzdot
    final byte zzfo(int i) {
        return this.zzhfu[this.zzhfr + i];
    }

    @Override // com.google.android.gms.internal.ads.zzdpd, com.google.android.gms.internal.ads.zzdot
    public final int size() {
        return this.zzhfs;
    }

    @Override // com.google.android.gms.internal.ads.zzdpd
    protected final int zzaxm() {
        return this.zzhfr;
    }

    @Override // com.google.android.gms.internal.ads.zzdpd, com.google.android.gms.internal.ads.zzdot
    protected final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzhfu, zzaxm(), bArr, 0, i3);
    }
}
