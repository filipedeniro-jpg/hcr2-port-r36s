package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Message;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.Serializable;
import java.util.EnumSet;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JC {
    public static final String D = JC.class.getSimpleName();
    private final C01952w B = C01952w.B();
    private final Context C;

    public JC(Context applicationContext) {
        this.C = applicationContext;
    }

    public final boolean A(Message msg) {
        String adId = msg.getData().getString("STR_AD_ID_KEY");
        switch (msg.what) {
            case 1010:
                String string = msg.getData().getString("STR_PLACEMENT_KEY");
                String string2 = msg.getData().getString("STR_BID_PAYLOAD_KEY");
                EnumSet<CacheFlag> enumSet = (EnumSet) LZ.B(msg.getData().getByteArray("SRL_INT_CACHE_FLAGS_KEY"));
                String string3 = msg.getData().getString("STR_EXTRA_HINTS_KEY");
                String string4 = msg.getData().getString("STR_MEDIATION_DATA_KEY");
                AdInternalSettings.sSettingsBundle.reset(msg.getData().getBundle("BUNDLE_SETTINGS_KEY"));
                C01782f c01782f = new C01782f(this.C, null, string);
                c01782f.G = string3;
                c01782f.H = string4;
                c01782f.E = string2;
                c01782f.F = enumSet;
                C01942v c01942vF = C01952w.B().F(adId);
                if (c01942vF != null) {
                    if (c01942vF.B == null) {
                        c01942vF.B = B(c01782f, adId);
                    } else if (c01942vF.B instanceof C01732a) {
                        ((C01732a) c01942vF.B).A(c01782f.F, c01782f.E);
                    }
                    this.B.G(1015, adId);
                } else {
                    C0585Hx.D(this.C, "api", C0584Hw.T, new C0583Hv("Missing ad.", "AdId" + adId));
                }
                return true;
            case 1011:
                C2V c2vE = C01952w.B().E(adId);
                if (c2vE != null && (c2vE instanceof C01732a)) {
                    ((C01732a) c2vE).E();
                    this.B.G(1016, adId);
                } else {
                    C0585Hx.D(this.C, "api", C0584Hw.T, new C0583Hv("Missing bundle for message.", "Message: " + msg));
                }
                return true;
            case 1012:
                C01952w.B().D(adId);
                this.B.G(1017, adId);
                return true;
            case 2000:
                String string5 = msg.getData().getString("STR_PLACEMENT_KEY");
                String rvPlacement = msg.getData().getString("STR_BID_PAYLOAD_KEY");
                Boolean boolValueOf = Boolean.valueOf(msg.getData().getBoolean("BOOL_RV_FAIL_ON_CACHE_FAILURE_KEY"));
                String string6 = msg.getData().getString("STR_EXTRA_HINTS_KEY");
                String string7 = msg.getData().getString("STR_MEDIATION_DATA_KEY");
                AdInternalSettings.sSettingsBundle.reset(msg.getData().getBundle("BUNDLE_SETTINGS_KEY"));
                C01822j c01822j = new C01822j(this.C, string5, null);
                c01822j.F = string6;
                c01822j.H = string7;
                c01822j.E = rvPlacement;
                c01822j.G = boolValueOf.booleanValue();
                Serializable rvRewardData = LZ.B(msg.getData().getByteArray("SRL_RV_REWARD_DATA_KEY"));
                if (rvRewardData instanceof RewardData) {
                    c01822j.J = (RewardData) rvRewardData;
                }
                C01942v c01942vF2 = C01952w.B().F(adId);
                if (c01942vF2 != null) {
                    if (c01942vF2.B == null) {
                        c01942vF2.B = C(c01822j, adId);
                    } else if (c01942vF2.B instanceof C01762d) {
                        ((C01762d) c01942vF2.B).A(c01822j.E, c01822j.G);
                    }
                    this.B.G(2010, adId);
                } else {
                    String rvExtraHints = "AdId" + adId;
                    C0585Hx.D(this.C, "api", C0584Hw.T, new C0583Hv("Missing ad.", rvExtraHints));
                }
                return true;
            case 2001:
                C2V c2vE2 = C01952w.B().E(adId);
                if (c2vE2 != null && (c2vE2 instanceof C01762d)) {
                    ((C01762d) c2vE2).F(msg.getData().getInt("INT_RV_APP_ORIENTATION_KEY", 0));
                    this.B.G(2011, adId);
                } else {
                    C0585Hx.D(this.C, "api", C0584Hw.T, new C0583Hv("Missing ad: ", "AdId: " + adId));
                }
                return true;
            case 2002:
                C01952w.B().D(adId);
                this.B.G(2012, adId);
                return true;
            case 2003:
                C2V c2vE3 = C01952w.B().E(adId);
                if (c2vE3 instanceof C01762d) {
                    C01762d c01762d = (C01762d) c2vE3;
                    Serializable serializableB = LZ.B(msg.getData().getByteArray("SRL_RV_REWARD_DATA_KEY"));
                    if (serializableB instanceof RewardData) {
                        c01762d.E((RewardData) serializableB);
                    }
                } else {
                    C0585Hx.D(this.C, "api", C0584Hw.T, new C0583Hv("Missing ad.", "AdId" + adId));
                }
                return true;
            default:
                return false;
        }
    }

    private C01732a B(C01782f interstitialAdModel, String adId) {
        C01732a c01732a = new C01732a(interstitialAdModel, this.B, adId);
        c01732a.A(interstitialAdModel.F, interstitialAdModel.E);
        return c01732a;
    }

    private C01762d C(C01822j rewardedVideoAdModel, String adId) {
        C01762d c01762d = new C01762d(rewardedVideoAdModel, this.B, adId);
        c01762d.A(rewardedVideoAdModel.E, rewardedVideoAdModel.G);
        return c01762d;
    }
}
