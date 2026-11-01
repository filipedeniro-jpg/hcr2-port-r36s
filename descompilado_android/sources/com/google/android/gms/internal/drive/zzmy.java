package com.google.android.gms.internal.drive;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class zzmy {
    private static final zzmy zzvr = new zzmy(0, new int[0], new Object[0], false);
    private int count;
    private boolean zznh;
    private int zzrr;
    private Object[] zzue;
    private int[] zzvs;

    public static zzmy zzfa() {
        return zzvr;
    }

    static zzmy zzfb() {
        return new zzmy();
    }

    static zzmy zza(zzmy zzmyVar, zzmy zzmyVar2) {
        int i = zzmyVar.count + zzmyVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zzmyVar.zzvs, i);
        System.arraycopy(zzmyVar2.zzvs, 0, iArrCopyOf, zzmyVar.count, zzmyVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zzmyVar.zzue, i);
        System.arraycopy(zzmyVar2.zzue, 0, objArrCopyOf, zzmyVar.count, zzmyVar2.count);
        return new zzmy(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzmy() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmy(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzrr = -1;
        this.count = i;
        this.zzvs = iArr;
        this.zzue = objArr;
        this.zznh = z;
    }

    public final void zzbp() {
        this.zznh = false;
    }

    final void zza(zzns zznsVar) throws IOException {
        if (zznsVar.zzcd() == zzkk.zze.zzsj) {
            for (int i = this.count - 1; i >= 0; i--) {
                zznsVar.zza(this.zzvs[i] >>> 3, this.zzue[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zznsVar.zza(this.zzvs[i2] >>> 3, this.zzue[i2]);
        }
    }

    public final void zzb(zzns zznsVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zznsVar.zzcd() == zzkk.zze.zzsi) {
            for (int i = 0; i < this.count; i++) {
                zzb(this.zzvs[i], this.zzue[i], zznsVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzb(this.zzvs[i2], this.zzue[i2], zznsVar);
        }
    }

    private static void zzb(int i, Object obj, zzns zznsVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zznsVar.zzi(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zznsVar.zzc(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zznsVar.zza(i2, (zzjc) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zznsVar.zzf(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzkq.zzdl());
        }
        if (zznsVar.zzcd() == zzkk.zze.zzsi) {
            zznsVar.zzak(i2);
            ((zzmy) obj).zzb(zznsVar);
            zznsVar.zzal(i2);
        } else {
            zznsVar.zzal(i2);
            ((zzmy) obj).zzb(zznsVar);
            zznsVar.zzak(i2);
        }
    }

    public final int zzfc() {
        int i = this.zzrr;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            iZzd += zzjr.zzd(this.zzvs[i2] >>> 3, (zzjc) this.zzue[i2]);
        }
        this.zzrr = iZzd;
        return iZzd;
    }

    public final int zzcx() {
        int iZze;
        int i = this.zzrr;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzvs[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZze = zzjr.zze(i5, ((Long) this.zzue[i3]).longValue());
            } else if (i6 == 1) {
                iZze = zzjr.zzg(i5, ((Long) this.zzue[i3]).longValue());
            } else if (i6 == 2) {
                iZze = zzjr.zzc(i5, (zzjc) this.zzue[i3]);
            } else if (i6 == 3) {
                iZze = (zzjr.zzab(i5) << 1) + ((zzmy) this.zzue[i3]).zzcx();
            } else if (i6 == 5) {
                iZze = zzjr.zzj(i5, ((Integer) this.zzue[i3]).intValue());
            } else {
                throw new IllegalStateException(zzkq.zzdl());
            }
            i2 += iZze;
        }
        this.zzrr = i2;
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0046 A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmy)) {
            return false;
        }
        zzmy zzmyVar = (zzmy) obj;
        int i = this.count;
        if (i == zzmyVar.count) {
            int[] iArr = this.zzvs;
            int[] iArr2 = zzmyVar.zzvs;
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
                Object[] objArr = this.zzue;
                Object[] objArr2 = zzmyVar.zzue;
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
        int[] iArr = this.zzvs;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzue;
        int i6 = this.count;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzlt.zza(sb, i, String.valueOf(this.zzvs[i2] >>> 3), this.zzue[i2]);
        }
    }

    final void zzb(int i, Object obj) {
        if (!this.zznh) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.count;
        if (i2 == this.zzvs.length) {
            int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
            this.zzvs = Arrays.copyOf(this.zzvs, i3);
            this.zzue = Arrays.copyOf(this.zzue, i3);
        }
        int[] iArr = this.zzvs;
        int i4 = this.count;
        iArr[i4] = i;
        this.zzue[i4] = obj;
        this.count = i4 + 1;
    }
}
