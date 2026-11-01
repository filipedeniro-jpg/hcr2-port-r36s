package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzow implements Runnable {
    private final /* synthetic */ zzij zzahf;
    private final /* synthetic */ zzox zzbjd;

    zzow(zzox zzoxVar, zzij zzijVar) {
        this.zzbjd = zzoxVar;
        this.zzahf = zzijVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbjd.zzbje.zze(this.zzahf);
    }
}
