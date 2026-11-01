package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzoz implements Runnable {
    private final /* synthetic */ String zzahb;
    private final /* synthetic */ long zzahc;
    private final /* synthetic */ long zzahd;
    private final /* synthetic */ zzox zzbjd;

    zzoz(zzox zzoxVar, String str, long j, long j2) {
        this.zzbjd = zzoxVar;
        this.zzahb = str;
        this.zzahc = j;
        this.zzahd = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zzd(this.zzahb, this.zzahc, this.zzahd);
    }
}
