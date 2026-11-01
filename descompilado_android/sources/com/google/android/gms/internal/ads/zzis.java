package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzis implements zzjd {
    private final int length;
    private final long zzagm;
    private final int[] zzaml;
    private final long[] zzamm;
    private final long[] zzamn;
    private final long[] zzamo;

    public zzis(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.zzaml = iArr;
        this.zzamm = jArr;
        this.zzamn = jArr2;
        this.zzamo = jArr3;
        this.length = iArr.length;
        int i = this.length;
        if (i > 0) {
            this.zzagm = jArr2[i - 1] + jArr3[i - 1];
        } else {
            this.zzagm = 0L;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final boolean zzfy() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long getDurationUs() {
        return this.zzagm;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long zzdt(long j) {
        return this.zzamm[zzoh.zza(this.zzamo, j, true, true)];
    }
}
