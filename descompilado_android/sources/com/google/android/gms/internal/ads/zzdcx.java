package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdcx extends zzdbf.zzj<Void> implements Runnable {
    private final Runnable zzgrm;

    public zzdcx(Runnable runnable) {
        this.zzgrm = (Runnable) zzczx.checkNotNull(runnable);
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzgrm.run();
        } catch (Throwable th) {
            setException(th);
            throw zzdac.zzg(th);
        }
    }
}
