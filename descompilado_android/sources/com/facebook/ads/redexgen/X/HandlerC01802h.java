package com.facebook.ads.redexgen.X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.internal.protocol.AdErrorType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2h, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HandlerC01802h extends Handler {
    public boolean B;
    public boolean C;

    @Nullable
    public Messenger D;
    private final C2U E;
    private final Context F;
    private ServiceConnection G;
    private final Messenger H;

    public HandlerC01802h(Context applicationContext, C2U adIpcProxy) {
        super(Looper.getMainLooper());
        this.G = new ServiceConnection() { // from class: com.facebook.ads.redexgen.X.2g
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName className, IBinder service) {
                this.B.D = new Messenger(service);
                this.B.A("Attached.");
                try {
                    this.B.D(this.B.D, 1, null);
                    if (this.B.C) {
                        this.B.C = false;
                        this.B.E.F();
                    }
                } catch (RemoteException e) {
                    this.B.D();
                }
                this.B.A("Remote service connected.");
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName className) {
                this.B.A("Disconnected.");
                if (!this.B.B) {
                    return;
                }
                this.B.D();
            }
        };
        this.F = applicationContext;
        this.H = new Messenger(this);
        this.E = adIpcProxy;
    }

    public final void A(String message) {
    }

    public final void B() {
        this.B = this.F.bindService(new Intent(this.F, (Class<?>) AdsMessengerService.class), this.G, 1);
        if (this.B) {
            A("Binding.");
            return;
        }
        C0585Hx.F(this.F, "ipc", C0584Hw.ZB, new C0583Hv("Context.bind() returned false."));
        this.C = false;
        A("Can't bind to service. Use internal.");
        this.E.E();
    }

    public final void C() {
        if (this.B) {
            if (this.D != null) {
                try {
                    D(this.D, 2, null);
                } catch (RemoteException e) {
                }
            }
            this.B = false;
            this.F.unbindService(this.G);
            A("Unbinding.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        this.D = null;
        C();
        this.E.H(10, AdErrorType.REMOTE_ADS_SERVICE_ERROR, null);
    }

    public final void D(Messenger service, int command, @Nullable Bundle bundle) throws RemoteException {
        Message messageObtain = Message.obtain((Handler) null, command);
        messageObtain.replyTo = this.H;
        if (bundle != null) {
            messageObtain.setData(bundle);
        }
        messageObtain.getData().putString("STR_AD_ID_KEY", this.E.B);
        service.send(messageObtain);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        if (msg.what == 3) {
            A("Received check alive.");
            return;
        }
        String string = msg.getData().getString("STR_AD_ID_KEY");
        A("Received message " + msg.what + " for Ad: " + string);
        if (!this.E.B.equals(string)) {
            return;
        }
        this.E.D(msg);
    }
}
