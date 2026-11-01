package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2w, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C01952w implements C2T {
    private static final String C = C01952w.class.getSimpleName();
    private static C01952w D;
    private final LinkedHashMap<String, C01942v> B = new LinkedHashMap<>();

    private C01952w() {
    }

    public final void A(String key, Messenger client) {
        this.B.put(key, new C01942v(key, client));
    }

    public static C01952w B() {
        if (D == null) {
            D = new C01952w();
        }
        return D;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final void m228B() {
        Iterator<Map.Entry<String, C01942v>> it = this.B.entrySet().iterator();
        while (it.hasNext()) {
            C2V c2v = it.next().getValue().B;
            if (c2v != null) {
                c2v.destroy();
            }
            it.remove();
        }
    }

    private static void C(String message) {
        Log.d(C, message);
    }

    public final void D(String key) {
        C01942v c01942v = this.B.get(key);
        if (c01942v != null && c01942v.B != null) {
            C("Destroyed Ad " + key);
            c01942v.B.destroy();
            this.B.remove(key);
        }
    }

    @Nullable
    public final C2V E(String key) {
        C01942v c01942v = this.B.get(key);
        if (c01942v != null) {
            return c01942v.B;
        }
        return null;
    }

    @Nullable
    public final C01942v F(String adId) {
        return this.B.get(adId);
    }

    public final void G(int messageId, String adId) {
        cE(messageId, adId, null);
    }

    public final void H(String key) {
        if (this.B.get(key) != null) {
            C("Removed Ad " + key);
            this.B.remove(key);
        }
    }

    public final void I(String adId) {
        this.B.remove(adId);
    }

    @Override // com.facebook.ads.redexgen.X.C2T
    public final void cE(int messageId, String adId, @Nullable Bundle extraData) {
        C01942v c01942vF = F(adId);
        if (c01942vF != null) {
            try {
                Message messageObtain = Message.obtain((Handler) null, messageId);
                messageObtain.getData().putString("STR_AD_ID_KEY", adId);
                if (extraData != null) {
                    messageObtain.getData().putBundle("BUNDLE_EXTRAS_KEY", extraData);
                }
                c01942vF.C.send(messageObtain);
            } catch (RemoteException e) {
                D(adId);
            }
        }
        Iterator<Map.Entry<String, C01942v>> it = this.B.entrySet().iterator();
        while (it.hasNext()) {
            C01942v value = it.next().getValue();
            try {
                value.C.send(Message.obtain((Handler) null, 3));
            } catch (RemoteException e2) {
                D(value.D);
            }
        }
    }
}
