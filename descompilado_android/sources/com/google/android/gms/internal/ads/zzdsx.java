package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdsx {
    private static final zzdsx zzhob = new zzdsx(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzhfd;
    private int zzhke;
    private Object[] zzhmn;
    private int[] zzhoc;

    public static zzdsx zzbbs() {
        return zzhob;
    }

    static zzdsx zzbbt() {
        return new zzdsx();
    }

    static zzdsx zza(zzdsx zzdsxVar, zzdsx zzdsxVar2) {
        int i = zzdsxVar.count + zzdsxVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zzdsxVar.zzhoc, i);
        System.arraycopy(zzdsxVar2.zzhoc, 0, iArrCopyOf, zzdsxVar.count, zzdsxVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zzdsxVar.zzhmn, i);
        System.arraycopy(zzdsxVar2.zzhmn, 0, objArrCopyOf, zzdsxVar.count, zzdsxVar2.count);
        return new zzdsx(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzdsx() {
        this(0, new int[8], new Object[8], true);
    }

    private zzdsx(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzhke = -1;
        this.count = i;
        this.zzhoc = iArr;
        this.zzhmn = objArr;
        this.zzhfd = z;
    }

    public final void zzaxe() {
        this.zzhfd = false;
    }

    final void zza(zzdtr zzdtrVar) throws IOException {
        if (zzdtrVar.zzayt() == zzdqd.zzd.zzhkt) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzdtrVar.zzb(this.zzhoc[i] >>> 3, this.zzhmn[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzdtrVar.zzb(this.zzhoc[i2] >>> 3, this.zzhmn[i2]);
        }
    }

    public final void zzb(zzdtr zzdtrVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzdtrVar.zzayt() == zzdqd.zzd.zzhks) {
            for (int i = 0; i < this.count; i++) {
                zzb(this.zzhoc[i], this.zzhmn[i], zzdtrVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzb(this.zzhoc[i2], this.zzhmn[i2], zzdtrVar);
        }
    }

    private static void zzb(int i, Object obj, zzdtr zzdtrVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzdtrVar.zzo(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zzdtrVar.zzi(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zzdtrVar.zza(i2, (zzdot) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zzdtrVar.zzad(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzdqn.zzbac());
        }
        if (zzdtrVar.zzayt() == zzdqd.zzd.zzhks) {
            zzdtrVar.zzgn(i2);
            ((zzdsx) obj).zzb(zzdtrVar);
            zzdtrVar.zzgo(i2);
        } else {
            zzdtrVar.zzgo(i2);
            ((zzdsx) obj).zzb(zzdtrVar);
            zzdtrVar.zzgn(i2);
        }
    }

    public final int zzbbu() {
        int i = this.zzhke;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            iZzd += zzdpm.zzd(this.zzhoc[i2] >>> 3, (zzdot) this.zzhmn[i2]);
        }
        this.zzhke = iZzd;
        return iZzd;
    }

    public final int zzazp() {
        int iZzk;
        int i = this.zzhke;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzhoc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZzk = zzdpm.zzk(i5, ((Long) this.zzhmn[i3]).longValue());
            } else if (i6 == 1) {
                iZzk = zzdpm.zzm(i5, ((Long) this.zzhmn[i3]).longValue());
            } else if (i6 == 2) {
                iZzk = zzdpm.zzc(i5, (zzdot) this.zzhmn[i3]);
            } else if (i6 == 3) {
                iZzk = (zzdpm.zzge(i5) << 1) + ((zzdsx) this.zzhmn[i3]).zzazp();
            } else if (i6 == 5) {
                iZzk = zzdpm.zzah(i5, ((Integer) this.zzhmn[i3]).intValue());
            } else {
                throw new IllegalStateException(zzdqn.zzbac());
            }
            i2 += iZzk;
        }
        this.zzhke = i2;
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0046 A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzdsx)) {
            return false;
        }
        zzdsx zzdsxVar = (zzdsx) obj;
        int i = this.count;
        if (i == zzdsxVar.count) {
            int[] iArr = this.zzhoc;
            int[] iArr2 = zzdsxVar.zzhoc;
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
                Object[] objArr = this.zzhmn;
                Object[] objArr2 = zzdsxVar.zzhmn;
                int i3 = this.count;
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
        int i = this.count;
        int i2 = (i + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        int[] iArr = this.zzhoc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzhmn;
        int i6 = this.count;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzdro.zza(sb, i, String.valueOf(this.zzhoc[i2] >>> 3), this.zzhmn[i2]);
        }
    }

    final void zzc(int i, Object obj) {
        if (!this.zzhfd) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.count;
        if (i2 == this.zzhoc.length) {
            int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
            this.zzhoc = Arrays.copyOf(this.zzhoc, i3);
            this.zzhmn = Arrays.copyOf(this.zzhmn, i3);
        }
        int[] iArr = this.zzhoc;
        int i4 = this.count;
        iArr[i4] = i;
        this.zzhmn[i4] = obj;
        this.count = i4 + 1;
    }
}
