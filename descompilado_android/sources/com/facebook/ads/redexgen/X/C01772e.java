package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.EnumSet;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2e, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C01772e extends C2U {

    @Nullable
    private C01732a B;
    private final C01782f C;

    public C01772e(C01782f interstitialAdModel) {
        super(interstitialAdModel.D.getApplicationContext());
        this.C = interstitialAdModel;
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final Message A() {
        Message messageObtain = Message.obtain((Handler) null, 1010);
        messageObtain.getData().putString("STR_PLACEMENT_KEY", this.C.I);
        messageObtain.getData().putString("STR_AD_ID_KEY", super.B);
        messageObtain.getData().putString("STR_BID_PAYLOAD_KEY", this.C.E);
        messageObtain.getData().putString("STR_EXTRA_HINTS_KEY", this.C.G);
        messageObtain.getData().putString("STR_MEDIATION_DATA_KEY", this.C.H);
        messageObtain.getData().putByteArray("SRL_INT_CACHE_FLAGS_KEY", LZ.C(this.C.F));
        messageObtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.sSettingsBundle.toBundle());
        return messageObtain;
    }

    private void B() {
        I(1012, null);
        this.E.C();
        this.C.B(null);
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final void C() {
        if (this.E.B) {
            B();
        }
        if (this.B != null) {
            this.B.destroy();
        }
        super.C.m227C(C2Q.DESTROYED);
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final void D(Message msg) {
        InterstitialAd interstitialAdA = this.C.A();
        if (interstitialAdA == null) {
            C0585Hx.D(this.D, "api", C0584Hw.K, new C0583Hv("Ad object is null"));
        }
        switch (msg.what) {
            case 10:
            case IronSourceError.ERROR_RV_SHOW_CALLED_WRONG_STATE /* 1023 */:
                if (this.E.B) {
                    B();
                }
                Bundle bundle = msg.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle != null) {
                    int i = bundle.getInt("INT_ERROR_CODE_KEY");
                    String string = bundle.getString("STR_ERROR_MESSAGE_KEY");
                    AdError adError = new AdError(i, string);
                    super.C.B(adError);
                    if (this.C.C != null) {
                        this.C.C.onError(interstitialAdA, adError);
                    } else {
                        Log.e(AudienceNetworkAds.TAG, string);
                    }
                } else {
                    super.C.m227C(C2Q.ERROR);
                    C0585Hx.D(this.D, "api", C0584Hw.T, new C0583Hv("Missing bundle for message.", "Message: " + msg));
                }
                this.C.B(null);
                return;
            case 1015:
                this.E.A("Received load confirmation.");
                break;
            case 1016:
                this.E.A("Received show confirmation.");
                break;
            case 1017:
                this.E.A("Received destroy confirmation.");
                break;
            case 1020:
                super.C.m227C(C2Q.LOADED);
                Bundle bundle2 = msg.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle2 != null) {
                    this.C.B = bundle2.getLong("LONG_INVALIDATION_TIME_KEY");
                } else {
                    C0585Hx.D(this.D, "api", C0584Hw.T, new C0583Hv("Missing bundle for message.", "Message: " + msg));
                }
                this.C.B(null);
                break;
            case 1022:
                super.C.m227C(C2Q.SHOWN);
                if (this.E.B) {
                    B();
                }
                break;
        }
        if (this.C.C == null) {
            return;
        }
        switch (msg.what) {
            case 1020:
                this.C.C.onAdLoaded(interstitialAdA);
                return;
            case 1021:
                this.C.C.onInterstitialDisplayed(interstitialAdA);
                return;
            case 1022:
                this.C.C.onInterstitialDismissed(interstitialAdA);
                return;
            case 1024:
                this.C.C.onAdClicked(interstitialAdA);
                return;
            case 1025:
                this.C.C.onLoggingImpression(interstitialAdA);
                return;
            case 1026:
                if (this.C.C instanceof InterstitialAdExtendedListener) {
                    ((InterstitialAdExtendedListener) this.C.C).onInterstitialActivityDestroyed();
                }
                break;
        }
        if (this.C.J == null) {
            return;
        }
        switch (msg.what) {
            case 3000:
                this.C.J.onRewardedAdCompleted();
                break;
            case 3001:
                this.C.J.onRewardedAdServerSucceeded();
                break;
            case 3002:
                this.C.J.onRewardedAdServerFailed();
                break;
        }
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final void E() {
        this.B = new C01732a(this.C, this, super.B);
        this.B.A(this.C.F, this.C.E);
    }

    public final void K(InterstitialAd interstitialAd, EnumSet<CacheFlag> enumSet, String bidPayload) {
        KQ kqD = C2F.D(this.D, 0, 1);
        if (kqD != null) {
            H(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, kqD.B());
            return;
        }
        if (super.C.A(C2Q.LOADING, "load()")) {
            return;
        }
        this.C.B(interstitialAd);
        if (this.B != null) {
            this.B.A(enumSet, bidPayload);
            return;
        }
        this.C.F = enumSet;
        this.C.E = bidPayload;
        if (J(this.C.D)) {
            if (this.E.B) {
                F();
                return;
            } else {
                this.E.C = true;
                this.E.B();
                return;
            }
        }
        E();
    }

    public final boolean L() {
        if (this.B != null) {
            return this.B.C();
        }
        return this.C.B > 0 && C0622Jj.B() > this.C.B;
    }

    public final boolean M() {
        if (this.B != null) {
            return this.B.D();
        }
        return super.C.B == C2Q.LOADED;
    }

    public final boolean N(InterstitialAd interstitialAd) {
        if (super.C.A(C2Q.SHOWING, "show()")) {
            return false;
        }
        this.C.B(interstitialAd);
        if (this.E.B) {
            I(1011, null);
            return true;
        }
        if (this.B != null) {
            return this.B.E();
        }
        this.B = new C01732a(this.C, this, super.B);
        this.B.E();
        return false;
    }
}
