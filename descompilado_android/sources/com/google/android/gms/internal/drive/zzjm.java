package com.google.android.gms.internal.drive;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
class zzjm extends zzjl {
    protected final byte[] zzny;

    zzjm(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzny = bArr;
    }

    protected int zzbw() {
        return 0;
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    public byte zzs(int i) {
        return this.zzny[i];
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    byte zzt(int i) {
        return this.zzny[i];
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    public int size() {
        return this.zzny.length;
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    public final zzjc zza(int i, int i2) {
        int iZzb = zzb(0, i2, size());
        if (iZzb == 0) {
            return zzjc.zznq;
        }
        return new zzjh(this.zzny, zzbw(), iZzb);
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    final void zza(zzjb zzjbVar) throws IOException {
        zzjbVar.zza(this.zzny, zzbw(), size());
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    protected final String zza(Charset charset) {
        return new String(this.zzny, zzbw(), size(), charset);
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    public final boolean zzbu() {
        int iZzbw = zzbw();
        return zznf.zze(this.zzny, iZzbw, size() + iZzbw);
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjc) || size() != ((zzjc) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof zzjm) {
            zzjm zzjmVar = (zzjm) obj;
            int iZzbv = zzbv();
            int iZzbv2 = zzjmVar.zzbv();
            if (iZzbv == 0 || iZzbv2 == 0 || iZzbv == iZzbv2) {
                return zza(zzjmVar, 0, size());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.drive.zzjl
    final boolean zza(zzjc zzjcVar, int i, int i2) {
        if (i2 > zzjcVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzjcVar.size()) {
            int size2 = zzjcVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzjcVar instanceof zzjm) {
            zzjm zzjmVar = (zzjm) zzjcVar;
            byte[] bArr = this.zzny;
            byte[] bArr2 = zzjmVar.zzny;
            int iZzbw = zzbw() + i2;
            int iZzbw2 = zzbw();
            int iZzbw3 = zzjmVar.zzbw();
            while (iZzbw2 < iZzbw) {
                if (bArr[iZzbw2] != bArr2[iZzbw3]) {
                    return false;
                }
                iZzbw2++;
                iZzbw3++;
            }
            return true;
        }
        return zzjcVar.zza(0, i2).equals(zza(0, i2));
    }

    @Override // com.google.android.gms.internal.drive.zzjc
    protected final int zza(int i, int i2, int i3) {
        return zzkm.zza(i, this.zzny, zzbw(), i3);
    }
}
