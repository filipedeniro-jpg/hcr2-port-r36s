package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjm implements zzjl {
    private final /* synthetic */ zzjk zzapj;

    private zzjm(zzjk zzjkVar) {
        this.zzapj = zzjkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final int zzag(int i) {
        return zzjk.zzag(i);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final boolean zzah(int i) {
        return zzjk.zzah(i);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zzd(int i, long j, long j2) throws zzgt {
        this.zzapj.zzd(i, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zzai(int i) throws zzgt {
        this.zzapj.zzai(i);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zzc(int i, long j) throws zzgt {
        this.zzapj.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zza(int i, double d) throws zzgt {
        this.zzapj.zza(i, d);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zza(int i, String str) throws zzgt {
        this.zzapj.zza(i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final void zza(int i, int i2, zzix zzixVar) throws InterruptedException, IOException {
        this.zzapj.zza(i, i2, zzixVar);
    }

    /* synthetic */ zzjm(zzjk zzjkVar, zzjn zzjnVar) {
        this(zzjkVar);
    }
}
