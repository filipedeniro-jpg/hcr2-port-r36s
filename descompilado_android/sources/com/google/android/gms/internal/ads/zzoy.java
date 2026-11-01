package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzoy implements Runnable {
    private final /* synthetic */ zzgq zzahj;
    private final /* synthetic */ zzox zzbjd;

    zzoy(zzox zzoxVar, zzgq zzgqVar) {
        this.zzbjd = zzoxVar;
        this.zzahj = zzgqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zzk(this.zzahj);
    }
}
