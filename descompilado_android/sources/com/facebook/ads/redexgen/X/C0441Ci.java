package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AudienceNetworkAds;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ci, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0441Ci {
    private C0441Ci() {
    }

    public static View B(Context context, Throwable renderException) {
        C(context, renderException);
        return new View(context);
    }

    private static void C(Context context, Throwable renderException) {
        C0585Hx.D(context, "native_template", C0584Hw.bB, new C0583Hv(renderException));
        Log.e(AudienceNetworkAds.TAG, "Fail to render Native template. Empty Android View will be returned.", renderException);
    }
}
