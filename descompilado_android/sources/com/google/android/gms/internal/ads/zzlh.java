package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlh implements zzme {
    private final zzme[] zzbap;

    public zzlh(zzme[] zzmeVarArr) {
        this.zzbap = zzmeVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzme
    public final long zzgy() {
        long jMin = Long.MAX_VALUE;
        for (zzme zzmeVar : this.zzbap) {
            long jZzgy = zzmeVar.zzgy();
            if (jZzgy != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jZzgy);
            }
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }

    @Override // com.google.android.gms.internal.ads.zzme
    public final boolean zzdz(long j) {
        boolean zZzdz;
        boolean z = false;
        do {
            long jZzgy = zzgy();
            if (jZzgy == Long.MIN_VALUE) {
                break;
            }
            zZzdz = false;
            for (zzme zzmeVar : this.zzbap) {
                if (zzmeVar.zzgy() == jZzgy) {
                    zZzdz |= zzmeVar.zzdz(j);
                }
            }
            z |= zZzdz;
        } while (zZzdz);
        return z;
    }
}
