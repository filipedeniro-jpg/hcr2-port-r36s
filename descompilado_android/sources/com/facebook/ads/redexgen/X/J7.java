package com.facebook.ads.redexgen.X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class J7 implements ServiceConnection {
    public final /* synthetic */ J8 B;

    public J7(J8 this$0) {
        this.B = this$0;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName name, IBinder service) {
        this.B.D = true;
        this.B.E = new Messenger(service);
        Message messageObtain = Message.obtain((Handler) null, 1);
        messageObtain.setData(this.B.G());
        try {
            this.B.E.send(messageObtain);
        } catch (RemoteException e) {
            C0585Hx.D(this.B.C, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.QB, new C0583Hv(e));
        }
        this.B.C.unbindService(this);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName name) {
        try {
            this.B.C.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
        this.B.E = null;
        this.B.D = false;
    }
}
