package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzahd implements Runnable {
    private final zzagk zzczo;

    private zzahd(zzagk zzagkVar) {
        this.zzczo = zzagkVar;
    }

    static Runnable zzb(zzagk zzagkVar) {
        return new zzahd(zzagkVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzczo.destroy();
    }
}
