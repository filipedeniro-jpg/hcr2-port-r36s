package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HJ {
    private static final Map<String, H0> D = Collections.synchronizedMap(new HashMap());
    private static final Set<String> B = Collections.newSetFromMap(Collections.synchronizedMap(new HashMap()));
    private static final Set<String> C = Collections.newSetFromMap(Collections.synchronizedMap(new HashMap()));

    private HJ() {
    }

    public static synchronized void B(H0 videoData, @Nullable Integer attempt) {
        if (HI.B()) {
            videoData.C = attempt;
            D.put(videoData.F, videoData);
            C.add(videoData.F);
            B.remove(videoData.F);
        }
    }

    public static HH C(String url, boolean loading) {
        H0 h0 = D.get(url);
        if (h0 == null) {
            h0 = new H0(url, "unknown", "unknown");
        }
        HH hh = new HH(h0.B, h0.E, h0.D ? "markup" : "video", loading, h0.F);
        hh.C = h0.C;
        return hh;
    }

    public static boolean D(HH debugData) {
        if (debugData.E) {
            return true;
        }
        if (!B.contains(debugData.G)) {
            B.add(debugData.G);
            return true;
        }
        return false;
    }

    public static boolean E(String url) {
        return C.remove(url);
    }
}
