package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MK {
    private static boolean D = false;
    private static boolean C = false;
    private static final Map<String, String> B = Collections.synchronizedMap(new HashMap());

    private MK() {
    }

    @Nullable
    public static synchronized String B(String key) {
        String property;
        if (E()) {
            property = System.getProperty("fb.e2e." + key);
        } else {
            property = null;
        }
        return property;
    }

    public static Map<String, String> C() {
        return !E() ? Collections.emptyMap() : B;
    }

    public static synchronized boolean D(String feature) {
        return !TextUtils.isEmpty(B(feature));
    }

    public static synchronized boolean E() {
        if (!C) {
            D = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(System.getProperty("fb.running_e2e"));
            C = true;
        }
        return D;
    }
}
