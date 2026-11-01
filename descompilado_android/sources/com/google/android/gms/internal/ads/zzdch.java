package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdch<V> implements Runnable {
    private final Future<V> zzgqy;
    private final zzdcg<? super V> zzgqz;

    zzdch(Future<V> future, zzdcg<? super V> zzdcgVar) {
        this.zzgqy = future;
        this.zzgqz = zzdcgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzgqz.onSuccess(zzdcf.zzb(this.zzgqy));
        } catch (Error e) {
            e = e;
            this.zzgqz.zzb(e);
        } catch (RuntimeException e2) {
            e = e2;
            this.zzgqz.zzb(e);
        } catch (ExecutionException e3) {
            this.zzgqz.zzb(e3.getCause());
        }
    }

    public final String toString() {
        return zzczr.zzx(this).zzy(this.zzgqz).toString();
    }
}
