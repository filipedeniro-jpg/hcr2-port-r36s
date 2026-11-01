package com.facebook.ads.redexgen.X;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import com.facebook.ads.internal.api.AdsMessengerServiceApi;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.internal.ipc.AdsProcessPriorityService;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0523Fm implements AdsMessengerServiceApi {
    private boolean B;
    private Messenger C;
    private final AdsMessengerService D;
    private final ServiceConnection E = new ServiceConnectionC0521Fk(this);

    public C0523Fm(AdsMessengerService service) {
        this.D = service;
    }

    @Override // com.facebook.ads.internal.api.AdsMessengerServiceApi
    public final IBinder onBind(Intent intent) {
        return this.C.getBinder();
    }

    @Override // com.facebook.ads.internal.api.AdsMessengerServiceApi
    public final void onCreate() {
        I6.D = true;
        C0588Ia.F(this.D, null, null);
        C0588Ia.G(this.D);
        this.C = new Messenger(new HandlerC0522Fl(this.D.getApplicationContext(), null));
        if (JA.J(this.D.getApplicationContext())) {
            this.D.bindService(new Intent(this.D.getApplicationContext(), (Class<?>) AdsProcessPriorityService.class), this.E, 1);
        }
    }

    @Override // com.facebook.ads.internal.api.AdsMessengerServiceApi
    public final void onDestroy() {
        C01952w.B().m228B();
        if (this.B) {
            this.D.unbindService(this.E);
        }
    }
}
