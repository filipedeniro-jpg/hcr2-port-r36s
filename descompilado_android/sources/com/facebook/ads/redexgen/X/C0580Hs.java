package com.facebook.ads.redexgen.X;

import android.app.Activity;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0580Hs {
    private C0580Hs() {
    }

    public static void B(Activity activity, int requestedOrientation) {
        try {
            activity.setRequestedOrientation(requestedOrientation);
        } catch (IllegalStateException e) {
            C0585Hx.F(activity, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.I, new C0583Hv(e));
        }
    }
}
