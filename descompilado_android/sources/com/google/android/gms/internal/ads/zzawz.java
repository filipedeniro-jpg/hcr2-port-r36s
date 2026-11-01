package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzawz implements Executor {
    private final Handler zzdwd = new zzatp(Looper.getMainLooper());

    zzawz() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            try {
                runnable.run();
                return;
            } catch (Throwable th) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                zzatv.zza(com.google.android.gms.ads.internal.zzp.zzkc().getApplicationContext(), th);
                throw th;
            }
        }
        this.zzdwd.post(runnable);
    }
}
