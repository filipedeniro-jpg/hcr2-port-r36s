package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.WorkerThread;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjb extends zzbhy {
    private final Executor zzfbm;
    private final zzack zzfea;
    private final Runnable zzfeb;

    public zzbjb(zzbjt zzbjtVar, zzack zzackVar, Runnable runnable, Executor executor) {
        super(zzbjtVar);
        this.zzfea = zzackVar;
        this.zzfeb = runnable;
        this.zzfbm = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final zzwk getVideoController() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final void zza(ViewGroup viewGroup, zztw zztwVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final zzcva zzaeq() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final View zzaer() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final int zzaev() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final void zzjn() {
    }

    @Override // com.google.android.gms.internal.ads.zzbju
    @WorkerThread
    public final void zzaew() {
        final AtomicReference atomicReference = new AtomicReference(this.zzfeb);
        final Runnable runnable = new Runnable(atomicReference) { // from class: com.google.android.gms.internal.ads.zzbje
            private final AtomicReference zzfed;

            {
                this.zzfed = atomicReference;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable2 = (Runnable) this.zzfed.getAndSet(null);
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        };
        this.zzfbm.execute(new Runnable(this, runnable) { // from class: com.google.android.gms.internal.ads.zzbjd
            private final Runnable zzezv;
            private final zzbjb zzfec;

            {
                this.zzfec = this;
                this.zzezv = runnable;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfec.zzf(this.zzezv);
            }
        });
    }

    final /* synthetic */ void zzf(Runnable runnable) {
        try {
            if (this.zzfea.zzq(ObjectWrapper.wrap(runnable))) {
                return;
            }
            runnable.run();
        } catch (RemoteException unused) {
            runnable.run();
        }
    }
}
