package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavq {
    private HandlerThread zzdui = null;
    private Handler handler = null;
    private int zzduj = 0;
    private final Object lock = new Object();

    public final Looper zzvz() {
        Looper looper;
        synchronized (this.lock) {
            if (this.zzduj == 0) {
                if (this.zzdui == null) {
                    zzatm.zzdy("Starting the looper thread.");
                    this.zzdui = new HandlerThread("LooperProvider");
                    this.zzdui.start();
                    this.handler = new zzczj(this.zzdui.getLooper());
                    zzatm.zzdy("Looper thread started.");
                } else {
                    zzatm.zzdy("Resuming the looper thread");
                    this.lock.notifyAll();
                }
            } else {
                Preconditions.checkNotNull(this.zzdui, "Invalid state: mHandlerThread should already been initialized.");
            }
            this.zzduj++;
            looper = this.zzdui.getLooper();
        }
        return looper;
    }

    public final Handler getHandler() {
        return this.handler;
    }
}
