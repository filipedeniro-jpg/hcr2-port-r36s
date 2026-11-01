package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznn {
    private final ExecutorService zzbfm;
    private zznp<? extends zzno> zzbfn;
    private IOException zzbfo;

    public zznn(String str) {
        this.zzbfm = zzoh.zzbf(str);
    }

    public final <T extends zzno> long zza(T t, zznm<T> zznmVar, int i) {
        Looper looperMyLooper = Looper.myLooper();
        zznt.checkState(looperMyLooper != null);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        new zznp(this, looperMyLooper, t, zznmVar, i, jElapsedRealtime).zzee(0L);
        return jElapsedRealtime;
    }

    public final boolean isLoading() {
        return this.zzbfn != null;
    }

    public final void zzic() {
        this.zzbfn.zzk(false);
    }

    public final void zza(Runnable runnable) {
        zznp<? extends zzno> zznpVar = this.zzbfn;
        if (zznpVar != null) {
            zznpVar.zzk(true);
        }
        this.zzbfm.execute(runnable);
        this.zzbfm.shutdown();
    }

    public final void zzbb(int i) throws IOException {
        IOException iOException = this.zzbfo;
        if (iOException != null) {
            throw iOException;
        }
        zznp<? extends zzno> zznpVar = this.zzbfn;
        if (zznpVar != null) {
            zznpVar.zzbb(zznpVar.zzbfr);
        }
    }
}
