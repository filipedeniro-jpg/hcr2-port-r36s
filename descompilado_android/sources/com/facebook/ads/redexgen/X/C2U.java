package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public abstract class C2U implements C2T {
    public final String B = UUID.randomUUID().toString();
    public final C2R C;
    public final Context D;
    public final HandlerC01802h E;
    private final Handler F;
    public static int H = 0;
    public static int G = 0;

    public abstract Message A();

    public abstract void C();

    public abstract void D(Message message);

    public abstract void E();

    public C2U(Context applicationContext) {
        this.D = applicationContext;
        C0588Ia.G(this.D);
        this.C = new C2R(applicationContext, this);
        this.E = new HandlerC01802h(applicationContext, this);
        this.F = new Handler(Looper.getMainLooper());
    }

    public final void F() {
        try {
            if (this.E.D != null) {
                this.E.D.send(A());
            }
        } catch (RemoteException e) {
            this.E.C();
            E();
            this.E.A("Error during sending load command!");
        }
    }

    public final void G() {
        if (!JA.I(this.D) || this.C.B == C2Q.DESTROYED) {
            return;
        }
        if (!BuildConfigApi.isDebug()) {
            C0585Hx.F(this.D, "api", C0584Hw.W, new C0583Hv("Destroy was not called."));
        }
        Log.e(AudienceNetworkAds.TAG, "You didn't call destroy() for Ad Object. This may lead to leaking memory. Please, always call destroy() when you don't need this Ad Object any more.");
        C();
    }

    public final void H(int msgCode, AdErrorType adErrorType, @Nullable String customMessage) {
        Bundle bundle = new Bundle();
        if (customMessage != null) {
            bundle.putString("STR_ERROR_MESSAGE_KEY", customMessage);
        } else {
            bundle.putString("STR_ERROR_MESSAGE_KEY", adErrorType.getDefaultErrorMessage());
        }
        bundle.putInt("INT_ERROR_CODE_KEY", adErrorType.getErrorCode());
        cE(msgCode, this.B, bundle);
    }

    public final void I(int command, @Nullable Bundle dataBundle) {
        try {
            Messenger messenger = this.E.D;
            if (messenger != null) {
                this.E.D(messenger, command, dataBundle);
            }
        } catch (RemoteException e) {
            this.E.C();
            this.E.A("Error during sending command!");
        }
    }

    public final boolean J(Context context) {
        if (AdInternalSettings.sForceIpc) {
            return true;
        }
        if (AdInternalSettings.sMultiprocessSupportMode == AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_ON && C2F.C(context)) {
            return true;
        }
        if (AdInternalSettings.sMultiprocessSupportMode == AdSettings.MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_OFF) {
            C0585Hx.F(this.D, "ipc", C0584Hw.YB, new C0583Hv("Multiprocess support is off"));
            return false;
        }
        if (!JA.L(context)) {
            return false;
        }
        int i = H;
        H = i + 1;
        if (i <= 0 && JA.DB(context)) {
            return false;
        }
        if (!IF.D(this.D)) {
            int i2 = G;
            G = i2 + 1;
            if (i2 > 0) {
                if (G == 3) {
                    C0585Hx.F(this.D, "ipc", C0584Hw.XB, new C0583Hv("Marker file not created after 3 requests."));
                }
                return false;
            }
        }
        return C0590Ic.B(this.D);
    }

    @Override // com.facebook.ads.redexgen.X.C2T
    public final void cE(int messageId, String adId, @Nullable Bundle extraData) {
        final Message messageObtain = Message.obtain((Handler) null, messageId);
        messageObtain.getData().putString("STR_AD_ID_KEY", adId);
        if (extraData != null) {
            messageObtain.getData().putBundle("BUNDLE_EXTRAS_KEY", extraData);
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.F.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.2S
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.D(messageObtain);
                }
            });
        } else {
            D(messageObtain);
        }
    }
}
