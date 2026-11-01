package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpb implements Runnable {
    private final /* synthetic */ zzox zzbjd;
    private final /* synthetic */ int zzbjj;
    private final /* synthetic */ long zzbjk;

    zzpb(zzox zzoxVar, int i, long j) {
        this.zzbjd = zzoxVar;
        this.zzbjj = i;
        this.zzbjk = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zze(this.zzbjj, this.zzbjk);
    }
}
