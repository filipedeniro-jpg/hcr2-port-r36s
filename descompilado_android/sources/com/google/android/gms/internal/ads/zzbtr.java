package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbtr implements Runnable {
    private final zzbuf zzfkm;

    private zzbtr(zzbuf zzbufVar) {
        this.zzfkm = zzbufVar;
    }

    static Runnable zza(zzbuf zzbufVar) {
        return new zzbtr(zzbufVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzfkm.zzahb();
    }
}
