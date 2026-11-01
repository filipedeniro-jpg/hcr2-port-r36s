package com.facebook.ads.redexgen.X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class ServiceConnectionC0548Gm implements ServiceConnection {
    private AtomicBoolean B;
    private final BlockingQueue<IBinder> C;

    private ServiceConnectionC0548Gm() {
        this.B = new AtomicBoolean(false);
        this.C = new LinkedBlockingDeque();
    }

    public final IBinder A() throws InterruptedException {
        if (this.B.compareAndSet(true, true)) {
            throw new IllegalStateException("Binder already consumed");
        }
        return this.C.take();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName name, IBinder service) {
        try {
            this.C.put(service);
        } catch (InterruptedException e) {
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName name) {
    }
}
