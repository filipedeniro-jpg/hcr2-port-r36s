package com.facebook.ads.redexgen.X;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0565Hd {
    private final String B;
    private final String C;
    private final Map<String, String> D;

    public C0565Hd(String mStacktrace, Map<String, String> map) {
        this(mStacktrace, map, false);
    }

    public C0565Hd(String mStacktrace, Map<String, String> map, boolean mCaughtException) {
        this.C = mStacktrace;
        this.D = map;
        this.B = mCaughtException ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public final Map<String, String> A() {
        HashMap map = new HashMap();
        map.put("stacktrace", this.C);
        map.put("caught_exception", this.B);
        map.putAll(this.D);
        return map;
    }
}
