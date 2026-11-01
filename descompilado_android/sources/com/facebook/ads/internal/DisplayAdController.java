package com.facebook.ads.internal;

import android.util.Log;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DisplayAdController {
    private static final String B = DisplayAdController.class.getSimpleName();
    private static boolean C = false;

    private DisplayAdController() {
    }

    @Deprecated
    public static synchronized void setMainThreadForced(boolean forced) {
        Log.d(B, "BaseAdController changed main thread forced from " + C + " to " + forced);
        C = forced;
    }
}
