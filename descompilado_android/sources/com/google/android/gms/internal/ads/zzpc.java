package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpc implements Runnable {
    private final /* synthetic */ zzij zzahl;
    private final /* synthetic */ zzox zzbjd;

    zzpc(zzox zzoxVar, zzij zzijVar) {
        this.zzbjd = zzoxVar;
        this.zzahl = zzijVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahl.zzfu();
        this.zzbjd.zzbje.zzf(this.zzahl);
    }
}
