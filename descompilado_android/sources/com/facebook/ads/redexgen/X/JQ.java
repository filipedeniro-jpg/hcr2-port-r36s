package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JQ {
    private final Map<String, String> B;
    private final JZ C;
    private final String D;
    private final double E;
    private final boolean F;
    private final double G = System.currentTimeMillis() / 1000.0d;
    private final String H;
    private final EnumC0613Ja I;

    public JQ(String mToken, double mSessionTime, String mSessionId, Map<String, String> map, JZ mPriority, EnumC0613Ja mType, boolean mShouldAppendAnalogData, boolean shouldIncludeInFunnelLogging) {
        this.H = mToken;
        this.E = mSessionTime;
        this.D = mSessionId;
        this.C = mPriority;
        this.I = mType;
        this.F = mShouldAppendAnalogData;
        HashMap map2 = new HashMap();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        if (shouldIncludeInFunnelLogging) {
            map2.put("should_include_in_funnel", String.valueOf(shouldIncludeInFunnelLogging));
        }
        if (I()) {
            map2.put("analog", MJ.C(IK.F()));
        }
        this.B = B(map2);
    }

    public final Map<String, String> A() {
        return this.B;
    }

    public final JZ B() {
        return this.C;
    }

    private static Map<String, String> B(Map<String, String> cleansedMap) {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : cleansedMap.entrySet()) {
            String key = entry.getKey();
            String key2 = entry.getValue();
            if (key2 != null) {
                map.put(key, key2);
            }
        }
        return map;
    }

    public final String C() {
        return this.D;
    }

    public final double D() {
        return this.E;
    }

    public final double E() {
        return this.G;
    }

    public final String F() {
        return this.H;
    }

    public final EnumC0613Ja G() {
        return this.I;
    }

    public final boolean H() {
        return !TextUtils.isEmpty(this.H);
    }

    public final boolean I() {
        return this.C == JZ.IMMEDIATE;
    }
}
