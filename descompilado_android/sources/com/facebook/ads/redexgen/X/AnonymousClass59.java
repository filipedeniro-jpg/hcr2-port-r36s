package com.facebook.ads.redexgen.X;

import android.content.Context;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.59, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass59 {
    private AnonymousClass59() {
    }

    public static int B(Context context) {
        return JA.Y(context).C("adnw_wo_network_signal_sampling_rate", 0);
    }

    public static boolean C(Context context) {
        return JA.Y(context).A("adnw_wo_network_signal_large_payload_enabled", false);
    }

    public static int D(Context context) {
        return JA.Y(context).C("adnw_wo_network_signal_large_payload_sampling_rate", -1);
    }

    public static int E(Context context) {
        return JA.Y(context).C("adnw_wo_network_signal_large_payload_size", -1);
    }

    public static boolean F(Context context) {
        return JA.Y(context).A("adnw_wo_is_native_signal_enabled", false);
    }

    public static boolean G(Context context) {
        return JA.Y(context).A("adnw_wo_network_signal_enabled", false);
    }
}
