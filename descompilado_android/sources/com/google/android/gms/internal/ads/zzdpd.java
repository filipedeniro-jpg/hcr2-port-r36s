package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzdpd extends zzdpe {
    protected final byte[] zzhfu;

    zzdpd(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzhfu = bArr;
    }

    protected int zzaxm() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public byte zzfn(int i) {
        return this.zzhfu[i];
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    byte zzfo(int i) {
        return this.zzhfu[i];
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public int size() {
        return this.zzhfu.length;
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public final zzdot zzy(int i, int i2) {
        int iZzh = zzh(0, i2, size());
        if (iZzh == 0) {
            return zzdot.zzhfn;
        }
        return new zzdpa(this.zzhfu, zzaxm(), iZzh);
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    protected void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzhfu, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    final void zza(zzdou zzdouVar) throws IOException {
        zzdouVar.zzi(this.zzhfu, zzaxm(), size());
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    protected final String zza(Charset charset) {
        return new String(this.zzhfu, zzaxm(), size(), charset);
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public final boolean zzaxj() {
        int iZzaxm = zzaxm();
        return zzdtd.zzl(this.zzhfu, iZzaxm, size() + iZzaxm);
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdot) || size() != ((zzdot) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof zzdpd) {
            zzdpd zzdpdVar = (zzdpd) obj;
            int iZzaxl = zzaxl();
            int iZzaxl2 = zzdpdVar.zzaxl();
            if (iZzaxl == 0 || iZzaxl2 == 0 || iZzaxl == iZzaxl2) {
                return zza(zzdpdVar, 0, size());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.ads.zzdpe
    final boolean zza(zzdot zzdotVar, int i, int i2) {
        if (i2 > zzdotVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzdotVar.size()) {
            int size2 = zzdotVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzdotVar instanceof zzdpd) {
            zzdpd zzdpdVar = (zzdpd) zzdotVar;
            byte[] bArr = this.zzhfu;
            byte[] bArr2 = zzdpdVar.zzhfu;
            int iZzaxm = zzaxm() + i2;
            int iZzaxm2 = zzaxm();
            int iZzaxm3 = zzdpdVar.zzaxm();
            while (iZzaxm2 < iZzaxm) {
                if (bArr[iZzaxm2] != bArr2[iZzaxm3]) {
                    return false;
                }
                iZzaxm2++;
                iZzaxm3++;
            }
            return true;
        }
        return zzdotVar.zzy(0, i2).equals(zzy(0, i2));
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    protected final int zzg(int i, int i2, int i3) {
        return zzdqe.zza(i, this.zzhfu, zzaxm(), i3);
    }

    @Override // com.google.android.gms.internal.ads.zzdot
    public final zzdpf zzaxk() {
        return zzdpf.zzc(this.zzhfu, zzaxm(), size(), true);
    }
}
