package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmg extends zzha {
    private static final Object zzbcv = new Object();
    private final boolean zzagh;
    private final boolean zzagi;
    private final long zzbcw;
    private final long zzbcx;
    private final long zzbcy;
    private final long zzbcz;

    public zzmg(long j, boolean z) {
        this(j, j, 0L, 0L, z, false);
    }

    @Override // com.google.android.gms.internal.ads.zzha
    public final int zzem() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzha
    public final int zzen() {
        return 1;
    }

    private zzmg(long j, long j2, long j3, long j4, boolean z, boolean z2) {
        this.zzbcw = j;
        this.zzbcx = j2;
        this.zzbcy = 0L;
        this.zzbcz = 0L;
        this.zzagh = z;
        this.zzagi = false;
    }

    @Override // com.google.android.gms.internal.ads.zzha
    public final zzhb zza(int i, zzhb zzhbVar, boolean z, long j) {
        zznt.zzc(i, 0, 1);
        boolean z2 = this.zzagh;
        long j2 = this.zzbcx;
        zzhbVar.zzage = null;
        zzhbVar.zzagf = C.TIME_UNSET;
        zzhbVar.zzagg = C.TIME_UNSET;
        zzhbVar.zzagh = z2;
        zzhbVar.zzagi = false;
        zzhbVar.zzagl = 0L;
        zzhbVar.zzagm = j2;
        zzhbVar.zzagj = 0;
        zzhbVar.zzagk = 0;
        zzhbVar.zzagn = 0L;
        return zzhbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzha
    public final zzhc zza(int i, zzhc zzhcVar, boolean z) {
        zznt.zzc(i, 0, 1);
        Object obj = z ? zzbcv : null;
        return zzhcVar.zza(obj, obj, 0, this.zzbcw, 0L, false);
    }

    @Override // com.google.android.gms.internal.ads.zzha
    public final int zzc(Object obj) {
        return zzbcv.equals(obj) ? 0 : -1;
    }
}
