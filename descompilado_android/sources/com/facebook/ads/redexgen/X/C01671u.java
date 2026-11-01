package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.CacheFlag;
import com.my.target.aa;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1u, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01671u {
    private final C01481b B;
    private B6 C = B6.UNSPECIFIED;
    private final InterfaceC01330m D;
    private InterfaceC01410u E;

    @Nullable
    private H2 F;

    public C01671u(Context context, Map<String, Object> map, InterfaceC01330m listener, @Nullable String extraHints) {
        this.B = C(context, map, extraHints, (JSONObject) map.get("data"));
        this.D = listener;
    }

    public final void A(Intent intent) {
        intent.putExtra("ad_data_bundle", this.B);
    }

    public final B6 B() {
        return this.C;
    }

    private static C01481b C(Context context, Map<String, Object> map, @Nullable String extraHints, JSONObject dataObject) {
        C01481b adDataBundle = C01481b.B(dataObject, context);
        adDataBundle.O(extraHints);
        IQ iq = (IQ) map.get("definition");
        if (iq != null) {
            adDataBundle.P(iq.I());
        }
        return adDataBundle;
    }

    public final String C() {
        return this.B.C();
    }

    @Nullable
    private AdError D(Context context) {
        if (!this.B.A().isEmpty()) {
            return null;
        }
        C0585Hx.D(context, "api", C0584Hw.X, new C0583Hv("Internal Error 2006 without a valid AdInfo."));
        return AdError.internalError(2006);
    }

    public final L2 D() {
        if (!TextUtils.isEmpty(this.B.E()) && Build.VERSION.SDK_INT >= 21) {
            return L2.DYNAMIC;
        }
        if (this.B.A().size() > 1) {
            return L2.INTERSTITIAL_NATIVE_CAROUSEL;
        }
        if (this.B.A().get(0).C().D() != null) {
            return L2.INTERSTITIAL_NATIVE_PLAYABLE;
        }
        if (!TextUtils.isEmpty(this.B.A().get(0).C().H())) {
            return L2.INTERSTITIAL_NATIVE_VIDEO;
        }
        return L2.INTERSTITIAL_NATIVE_IMAGE;
    }

    private H2 E(Context context) {
        return this.F != null ? this.F : new H2(context);
    }

    public final void E(Context context, EnumSet<CacheFlag> enumSet) {
        AdError adErrorD = D(context);
        if (adErrorD != null) {
            this.D.ND(adErrorD);
            return;
        }
        this.D.kF();
        if (D() == L2.INTERSTITIAL_NATIVE_PLAYABLE) {
            F(context, enumSet);
        } else {
            G(context, enumSet);
        }
    }

    public final void F() {
        this.D.xF();
        this.E = null;
    }

    private void F(Context context, EnumSet<CacheFlag> enumSet) {
        B6 b6E;
        C01581l c01581lB = C01581l.B(this.B);
        C01571k c01571kD = c01581lB.B().D();
        if (c01571kD != null) {
            b6E = c01571kD.E();
        } else {
            b6E = B6.UNSPECIFIED;
        }
        H(b6E);
        this.E = new InterfaceC01410u() { // from class: com.facebook.ads.redexgen.X.1t
            @Override // com.facebook.ads.redexgen.X.InterfaceC01410u
            public final void rE(AdError adError) {
                this.B.D.ND(AdError.CACHE_ERROR);
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC01410u
            public final void sE() {
                this.B.D.OD();
            }
        };
        AnonymousClass22.B(context, c01581lB, true, this.E);
    }

    private void G(Context context, EnumSet<CacheFlag> cacheFlags) {
        H2 h2E = E(context);
        h2E.L(new C0618Jf(this.B.C(), JX.E(context)));
        if (!TextUtils.isEmpty(this.B.E())) {
            H0 h0 = new H0(this.B.E(), this.B.J(), aa.e.bo);
            h0.D = true;
            h2E.A(h0);
        }
        h2E.C(new C0560Gy(this.B.I().B(), C03348f.E, C03348f.E, this.B.J(), aa.e.bo));
        boolean zContains = cacheFlags.contains(CacheFlag.VIDEO);
        int i = 0;
        Iterator<C01491c> it = this.B.A().iterator();
        while (cacheVideos) {
            C01491c adInfo = it.next();
            C0560Gy c0560Gy = new C0560Gy(adInfo.C().B(), C01701x.B(adInfo.C()), C01701x.C(adInfo.C()), this.B.J(), aa.e.bo);
            if (i == 0) {
                h2E.B(c0560Gy);
            } else {
                h2E.C(c0560Gy);
            }
            Iterator<String> it2 = adInfo.B().B().iterator();
            while (it2.hasNext()) {
                h2E.C(new C0560Gy(it2.next(), -1, -1, this.B.J(), aa.e.bo));
            }
            if (zContains && !TextUtils.isEmpty(adInfo.C().H())) {
                H0 h1 = new H0(adInfo.C().H(), this.B.J(), aa.e.bo);
                h1.D = true;
                if (i == 0) {
                    h2E.E(h1);
                } else {
                    h2E.D(h1);
                }
            }
            i++;
        }
        h2E.H(new InterfaceC01280h() { // from class: com.facebook.ads.redexgen.X.1s
            private void B(boolean cacheSuccess) {
                if (cacheSuccess) {
                    this.B.D.OD();
                } else {
                    this.B.D.ND(AdError.CACHE_ERROR);
                }
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
            public final void dD() {
                B(false);
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
            public final void nD() {
                B(true);
            }
        }, new C0558Gw(this.B.J(), aa.e.bo));
    }

    private void H(B6 adOrientation) {
        this.C = adOrientation;
    }
}
