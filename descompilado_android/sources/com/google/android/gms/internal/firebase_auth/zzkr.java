package com.google.android.gms.internal.firebase_auth;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkr {
    private static final zzkr zza = new zzkr(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public static zzkr zza() {
        return zza;
    }

    static zzkr zzb() {
        return new zzkr();
    }

    static zzkr zza(zzkr zzkrVar, zzkr zzkrVar2) {
        int i = zzkrVar.zzb + zzkrVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzkrVar.zzc, i);
        System.arraycopy(zzkrVar2.zzc, 0, iArrCopyOf, zzkrVar.zzb, zzkrVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzkrVar.zzd, i);
        System.arraycopy(zzkrVar2.zzd, 0, objArrCopyOf, zzkrVar.zzb, zzkrVar2.zzb);
        return new zzkr(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzkr() {
        this(0, new int[8], new Object[8], true);
    }

    private zzkr(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public final void zzc() {
        this.zzf = false;
    }

    final void zza(zzll zzllVar) throws IOException {
        if (zzllVar.zza() == zzhx.zze.zzk) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzllVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzllVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    public final void zzb(zzll zzllVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zzllVar.zza() == zzhx.zze.zzj) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zzllVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zzllVar);
        }
    }

    private static void zza(int i, Object obj, zzll zzllVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzllVar.zza(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zzllVar.zzd(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zzllVar.zza(i2, (zzgm) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zzllVar.zzd(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzig.zzf());
        }
        if (zzllVar.zza() == zzhx.zze.zzj) {
            zzllVar.zza(i2);
            ((zzkr) obj).zzb(zzllVar);
            zzllVar.zzb(i2);
        } else {
            zzllVar.zzb(i2);
            ((zzkr) obj).zzb(zzllVar);
            zzllVar.zza(i2);
        }
    }

    public final int zzd() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            iZzd += zzhh.zzd(this.zzc[i2] >>> 3, (zzgm) this.zzd[i2]);
        }
        this.zze = iZzd;
        return iZzd;
    }

    public final int zze() {
        int iZze;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZze = zzhh.zze(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 1) {
                iZze = zzhh.zzg(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 2) {
                iZze = zzhh.zzc(i5, (zzgm) this.zzd[i3]);
            } else if (i6 == 3) {
                iZze = (zzhh.zze(i5) << 1) + ((zzkr) this.zzd[i3]).zze();
            } else if (i6 == 5) {
                iZze = zzhh.zzi(i5, ((Integer) this.zzd[i3]).intValue());
            } else {
                throw new IllegalStateException(zzig.zzf());
            }
            i2 += iZze;
        }
        this.zze = i2;
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0046 A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzkr)) {
            return false;
        }
        zzkr zzkrVar = (zzkr) obj;
        int i = this.zzb;
        if (i == zzkrVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzkrVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                }
                if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzkrVar.zzd;
                int i3 = this.zzb;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        if (!z2) {
                            return true;
                        }
                    }
                }
                z2 = true;
                if (!z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzjl.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.zzb;
        if (i2 == this.zzc.length) {
            int i3 = this.zzb + (i2 < 4 ? 8 : i2 >> 1);
            this.zzc = Arrays.copyOf(this.zzc, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
        int[] iArr = this.zzc;
        int i4 = this.zzb;
        iArr[i4] = i;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }
}
