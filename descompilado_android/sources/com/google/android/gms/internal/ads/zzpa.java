package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpa implements Runnable {
    private final /* synthetic */ zzox zzbjd;
    private final /* synthetic */ int zzbjf;
    private final /* synthetic */ int zzbjg;
    private final /* synthetic */ int zzbjh;
    private final /* synthetic */ float zzbji;

    zzpa(zzox zzoxVar, int i, int i2, int i3, float f) {
        this.zzbjd = zzoxVar;
        this.zzbjf = i;
        this.zzbjg = i2;
        this.zzbjh = i3;
        this.zzbji = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zza(this.zzbjf, this.zzbjg, this.zzbjh, this.zzbji);
    }
}
