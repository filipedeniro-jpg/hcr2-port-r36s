package com.google.android.gms.internal.firebase_remote_config;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class zzka {
    private static final zzka zzxg = new zzka(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzol;
    private int zztd;
    private Object[] zzvs;
    private int[] zzxh;

    public static zzka zzja() {
        return zzxg;
    }

    static zzka zzjb() {
        return new zzka();
    }

    static zzka zza(zzka zzkaVar, zzka zzkaVar2) {
        int i = zzkaVar.count + zzkaVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zzkaVar.zzxh, i);
        System.arraycopy(zzkaVar2.zzxh, 0, iArrCopyOf, zzkaVar.count, zzkaVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zzkaVar.zzvs, i);
        System.arraycopy(zzkaVar2.zzvs, 0, objArrCopyOf, zzkaVar.count, zzkaVar2.count);
        return new zzka(i, iArrCopyOf, objArrCopyOf, true);
    }

    private zzka() {
        this(0, new int[8], new Object[8], true);
    }

    private zzka(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zztd = -1;
        this.count = i;
        this.zzxh = iArr;
        this.zzvs = objArr;
        this.zzol = z;
    }

    public final void zzes() {
        this.zzol = false;
    }

    final void zza(zzku zzkuVar) throws IOException {
        if (zzkuVar.zzgd() == zzhi.zze.zzty) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzkuVar.zza(this.zzxh[i] >>> 3, this.zzvs[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzkuVar.zza(this.zzxh[i2] >>> 3, this.zzvs[i2]);
        }
    }

    public final void zzb(zzku zzkuVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzkuVar.zzgd() == zzhi.zze.zztx) {
            for (int i = 0; i < this.count; i++) {
                zzb(this.zzxh[i], this.zzvs[i], zzkuVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzb(this.zzxh[i2], this.zzvs[i2], zzkuVar);
        }
    }

    private static void zzb(int i, Object obj, zzku zzkuVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzkuVar.zzi(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zzkuVar.zzc(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zzkuVar.zza(i2, (zzfx) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zzkuVar.zzg(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzhq.zzhj());
        }
        if (zzkuVar.zzgd() == zzhi.zze.zztx) {
            zzkuVar.zzaz(i2);
            ((zzka) obj).zzb(zzkuVar);
            zzkuVar.zzba(i2);
        } else {
            zzkuVar.zzba(i2);
            ((zzka) obj).zzb(zzkuVar);
            zzkuVar.zzaz(i2);
        }
    }

    public final int zzjc() {
        int i = this.zztd;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            iZzd += zzgs.zzd(this.zzxh[i2] >>> 3, (zzfx) this.zzvs[i2]);
        }
        this.zztd = iZzd;
        return iZzd;
    }

    public final int zzgs() {
        int iZze;
        int i = this.zztd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzxh[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZze = zzgs.zze(i5, ((Long) this.zzvs[i3]).longValue());
            } else if (i6 == 1) {
                iZze = zzgs.zzg(i5, ((Long) this.zzvs[i3]).longValue());
            } else if (i6 == 2) {
                iZze = zzgs.zzc(i5, (zzfx) this.zzvs[i3]);
            } else if (i6 == 3) {
                iZze = (zzgs.zzaq(i5) << 1) + ((zzka) this.zzvs[i3]).zzgs();
            } else if (i6 == 5) {
                iZze = zzgs.zzk(i5, ((Integer) this.zzvs[i3]).intValue());
            } else {
                throw new IllegalStateException(zzhq.zzhj());
            }
            i2 += iZze;
        }
        this.zztd = i2;
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0046 A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzka)) {
            return false;
        }
        zzka zzkaVar = (zzka) obj;
        int i = this.count;
        if (i == zzkaVar.count) {
            int[] iArr = this.zzxh;
            int[] iArr2 = zzkaVar.zzxh;
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
                Object[] objArr = this.zzvs;
                Object[] objArr2 = zzkaVar.zzvs;
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
        int[] iArr = this.zzxh;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzvs;
        int i6 = this.count;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    final void zzb(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzir.zza(sb, i, String.valueOf(this.zzxh[i2] >>> 3), this.zzvs[i2]);
        }
    }

    final void zzb(int i, Object obj) {
        if (!this.zzol) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.count;
        if (i2 == this.zzxh.length) {
            int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
            this.zzxh = Arrays.copyOf(this.zzxh, i3);
            this.zzvs = Arrays.copyOf(this.zzvs, i3);
        }
        int[] iArr = this.zzxh;
        int i4 = this.count;
        iArr[i4] = i;
        this.zzvs[i4] = obj;
        this.count = i4 + 1;
    }
}
