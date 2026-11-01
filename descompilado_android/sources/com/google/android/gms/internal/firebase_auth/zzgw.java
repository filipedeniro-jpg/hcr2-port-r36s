package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
class zzgw extends zzgx {
    protected final byte[] zzb;

    zzgw(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzb = bArr;
    }

    protected int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    public byte zza(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    public final zzgm zza(int i, int i2) {
        int iZzb = zzb(0, i2, zza());
        if (iZzb == 0) {
            return zzgm.zza;
        }
        return new zzgt(this.zzb, zze(), iZzb);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    final void zza(zzgn zzgnVar) throws IOException {
        zzgnVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    protected final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    public final boolean zzc() {
        int iZze = zze();
        return zzla.zza(this.zzb, iZze, zza() + iZze);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgm) || zza() != ((zzgm) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (obj instanceof zzgw) {
            zzgw zzgwVar = (zzgw) obj;
            int iZzd = zzd();
            int iZzd2 = zzgwVar.zzd();
            if (iZzd == 0 || iZzd2 == 0 || iZzd == iZzd2) {
                return zza(zzgwVar, 0, zza());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgx
    final boolean zza(zzgm zzgmVar, int i, int i2) {
        if (i2 > zzgmVar.zza()) {
            int iZza = zza();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(iZza);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzgmVar.zza()) {
            int iZza2 = zzgmVar.zza();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(iZza2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzgmVar instanceof zzgw) {
            zzgw zzgwVar = (zzgw) zzgmVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzgwVar.zzb;
            int iZze = zze() + i2;
            int iZze2 = zze();
            int iZze3 = zzgwVar.zze();
            while (iZze2 < iZze) {
                if (bArr[iZze2] != bArr2[iZze3]) {
                    return false;
                }
                iZze2++;
                iZze3++;
            }
            return true;
        }
        return zzgmVar.zza(0, i2).equals(zza(0, i2));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgm
    protected final int zza(int i, int i2, int i3) {
        return zzib.zza(i, this.zzb, zze(), i3);
    }
}
