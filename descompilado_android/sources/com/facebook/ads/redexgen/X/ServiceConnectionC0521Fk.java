package com.facebook.ads.redexgen.X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ServiceConnectionC0521Fk implements ServiceConnection {
    public final /* synthetic */ C0523Fm B;

    public ServiceConnectionC0521Fk(C0523Fm this$0) {
        this.B = this$0;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName name, IBinder service) {
        this.B.B = true;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName name) {
        this.B.B = false;
        this.B.D.unbindService(this.B.E);
    }
}
