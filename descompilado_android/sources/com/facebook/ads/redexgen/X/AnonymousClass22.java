package com.facebook.ads.redexgen.X;

import android.content.Context;
import com.facebook.ads.AdError;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.22, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass22 {
    private AnonymousClass22() {
    }

    public static void B(Context context, C01581l adDataBundle, boolean failOnCacheFailure, InterfaceC01410u preCacheListener) {
        if (JA.KB(context)) {
            preCacheListener.sE();
            return;
        }
        C01571k c01571kD = adDataBundle.B().D();
        H2 h2 = new H2(context);
        h2.L(new C0618Jf(adDataBundle.E(), JX.E(context)));
        if (c01571kD == null) {
            preCacheListener.rE(AdError.CACHE_ERROR);
            return;
        }
        H0 h0 = new H0(c01571kD.D(), adDataBundle.I(), adDataBundle.G());
        h0.D = true;
        switch (c01571kD.F()) {
            case PROXY_PRECACHE:
                h2.E(h0);
                break;
            case FILE_PRECACHE:
                h2.A(h0);
                break;
        }
        h2.B(new C0560Gy(adDataBundle.H().B(), -1, -1, adDataBundle.I(), adDataBundle.G()));
        h2.B(new C0560Gy(c01571kD.C(), -1, -1, adDataBundle.I(), adDataBundle.G()));
        h2.H(new C01721z(context, preCacheListener, h2, c01571kD, failOnCacheFailure, null), new C0558Gw(adDataBundle.I(), adDataBundle.G()));
    }
}
