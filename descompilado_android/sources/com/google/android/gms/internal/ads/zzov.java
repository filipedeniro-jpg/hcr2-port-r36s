package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzov implements Handler.Callback, Choreographer.FrameCallback {
    private static final zzov zzbiz = new zzov();
    private final Handler handler;
    public volatile long zzbiy;
    private final HandlerThread zzbja = new HandlerThread("ChoreographerOwner:Handler");
    private Choreographer zzbjb;
    private int zzbjc;

    public static zzov zzix() {
        return zzbiz;
    }

    private zzov() {
        this.zzbja.start();
        this.handler = new Handler(this.zzbja.getLooper(), this);
        this.handler.sendEmptyMessage(0);
    }

    public final void zziy() {
        this.handler.sendEmptyMessage(1);
    }

    public final void zziz() {
        this.handler.sendEmptyMessage(2);
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.zzbiy = j;
        this.zzbjb.postFrameCallbackDelayed(this, 500L);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            this.zzbjb = Choreographer.getInstance();
            return true;
        }
        if (i == 1) {
            this.zzbjc++;
            if (this.zzbjc == 1) {
                this.zzbjb.postFrameCallback(this);
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        this.zzbjc--;
        if (this.zzbjc == 0) {
            this.zzbjb.removeFrameCallback(this);
            this.zzbiy = 0L;
        }
        return true;
    }
}
