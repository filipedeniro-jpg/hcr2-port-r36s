package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import com.facebook.ads.S2SRewardedVideoAdListener;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C01812i extends C2U {

    @Nullable
    private C01762d B;
    private final C01822j C;

    public C01812i(C01822j rewardedVideoAdModel) {
        super(rewardedVideoAdModel.D);
        this.C = rewardedVideoAdModel;
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final Message A() {
        Message messageObtain = Message.obtain((Handler) null, 2000);
        messageObtain.getData().putString("STR_PLACEMENT_KEY", this.C.I);
        messageObtain.getData().putString("STR_AD_ID_KEY", super.B);
        messageObtain.getData().putString("STR_BID_PAYLOAD_KEY", this.C.E);
        messageObtain.getData().putString("STR_EXTRA_HINTS_KEY", this.C.F);
        messageObtain.getData().putString("STR_MEDIATION_DATA_KEY", this.C.H);
        messageObtain.getData().putBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY", this.C.G);
        messageObtain.getData().putByteArray("SRL_RV_REWARD_DATA_KEY", LZ.C(this.C.J));
        messageObtain.getData().putBundle("BUNDLE_SETTINGS_KEY", AdInternalSettings.sSettingsBundle.toBundle());
        return messageObtain;
    }

    private void B() {
        I(2002, null);
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
        RewardedVideoAd rewardedVideoAdA = this.C.A();
        if (rewardedVideoAdA == null) {
            C0585Hx.D(this.D, "api", C0584Hw.K, new C0583Hv("Ad object is null"));
        }
        switch (msg.what) {
            case 10:
            case IronSourceConstants.IS_CHECK_CAPPED_TRUE /* 2103 */:
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
                        this.C.C.onError(rewardedVideoAdA, adError);
                    } else {
                        Log.e(AudienceNetworkAds.TAG, string);
                    }
                } else {
                    super.C.m227C(C2Q.ERROR);
                    C0585Hx.D(this.D, "api", C0584Hw.T, new C0583Hv("Missing bundle for message.", "Message: " + msg));
                }
                this.C.B(null);
                return;
            case 2010:
                this.E.A("Received load confirmation.");
                break;
            case 2011:
                this.E.A("Received show confirmation.");
                break;
            case 2100:
                super.C.m227C(C2Q.LOADED);
                Bundle bundle2 = msg.getData().getBundle("BUNDLE_EXTRAS_KEY");
                if (bundle2 != null) {
                    this.C.B = bundle2.getLong("LONG_INVALIDATION_TIME_KEY");
                    this.C.K = bundle2.getInt("INT_RV_VIDEO_DURATION_KEY");
                } else {
                    C0585Hx.D(this.D, "api", C0584Hw.T, new C0583Hv("Missing bundle for message", "Message: " + msg));
                }
                this.C.B(null);
                break;
            case 2106:
                super.C.m227C(C2Q.SHOWN);
                if (this.E.B) {
                    B();
                }
                this.C.B(null);
                break;
        }
        if (this.C.C == null) {
            return;
        }
        switch (msg.what) {
            case 2100:
                this.C.C.onAdLoaded(rewardedVideoAdA);
                break;
            case 2104:
                this.C.C.onAdClicked(rewardedVideoAdA);
                break;
            case 2105:
                this.C.C.onLoggingImpression(rewardedVideoAdA);
                break;
            case 2106:
                if (this.C.C instanceof RewardedVideoAdExtendedListener) {
                    ((RewardedVideoAdExtendedListener) this.C.C).onRewardedVideoActivityDestroyed();
                }
                break;
            case IronSourceConstants.IS_CALLBACK_LOAD_ERROR /* 2110 */:
                this.C.C.onRewardedVideoClosed();
                break;
            case 3000:
                this.C.C.onRewardedVideoCompleted();
                break;
            case 3001:
                if (this.C.C instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) this.C.C).onRewardServerSuccess();
                }
                break;
            case 3002:
                if (this.C.C instanceof S2SRewardedVideoAdListener) {
                    ((S2SRewardedVideoAdListener) this.C.C).onRewardServerFailed();
                }
                break;
        }
    }

    @Override // com.facebook.ads.redexgen.X.C2U
    public final void E() {
        this.B = new C01762d(this.C, this, super.B);
        this.B.A(this.C.E, this.C.G);
    }

    public final void K(RewardedVideoAd rewardedVideoAd, String bidPayload, boolean failOnCacheFailure) {
        KQ kqD = C2F.D(this.D, 0, 1);
        if (kqD != null) {
            H(10, AdErrorType.MISSING_DEPENDENCIES_ERROR, kqD.B());
            return;
        }
        if (super.C.A(C2Q.LOADING, "load()")) {
            return;
        }
        this.C.B(rewardedVideoAd);
        if (this.B != null) {
            this.B.A(bidPayload, failOnCacheFailure);
            return;
        }
        this.C.E = bidPayload;
        this.C.G = failOnCacheFailure;
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

    public final void N(RewardData rewardData) {
        this.C.J = rewardData;
        if (this.E.B) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("SRL_RV_REWARD_DATA_KEY", LZ.C(rewardData));
            I(2003, bundle);
        } else {
            if (this.B == null) {
                return;
            }
            this.B.E(rewardData);
        }
    }

    public final boolean O(RewardedVideoAd rewardedVideoAd, int appOrientation) {
        if (super.C.A(C2Q.SHOWING, "show()")) {
            return false;
        }
        this.C.B(rewardedVideoAd);
        if (this.E.B) {
            Bundle bundle = new Bundle();
            bundle.putInt("INT_RV_APP_ORIENTATION_KEY", appOrientation);
            I(2001, bundle);
            return true;
        }
        if (this.B != null) {
            return this.B.F(appOrientation);
        }
        this.B = new C01762d(this.C, this, super.B);
        this.B.F(appOrientation);
        return false;
    }
}
