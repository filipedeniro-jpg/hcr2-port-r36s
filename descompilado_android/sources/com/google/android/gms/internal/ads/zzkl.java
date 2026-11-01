package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkl {
    public final int[] zzaml;
    public final long[] zzamm;
    public final int zzavb;
    public final int zzawk;
    public final int[] zzawm;
    public final long[] zzaxm;

    public zzkl(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
        zznt.checkArgument(iArr.length == jArr2.length);
        zznt.checkArgument(jArr.length == jArr2.length);
        zznt.checkArgument(iArr2.length == jArr2.length);
        this.zzamm = jArr;
        this.zzaml = iArr;
        this.zzawk = i;
        this.zzaxm = jArr2;
        this.zzawm = iArr2;
        this.zzavb = jArr.length;
    }

    public final int zzdw(long j) {
        for (int iZza = zzoh.zza(this.zzaxm, j, true, false); iZza >= 0; iZza--) {
            if ((this.zzawm[iZza] & 1) != 0) {
                return iZza;
            }
        }
        return -1;
    }

    public final int zzdx(long j) {
        for (int iZzb = zzoh.zzb(this.zzaxm, j, true, false); iZzb < this.zzaxm.length; iZzb++) {
            if ((this.zzawm[iZzb] & 1) != 0) {
                return iZzb;
            }
        }
        return -1;
    }
}
