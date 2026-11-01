package com.moat.analytics.mobile.vng;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MoatAdEvent {
    Integer b;
    Double c;
    MoatAdEventType d;
    private final Long f;
    static final Integer a = Integer.MIN_VALUE;
    private static final Double e = Double.valueOf(Double.NaN);
    public static final Double VOLUME_MUTED = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, a, e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f = Long.valueOf(System.currentTimeMillis());
        this.d = moatAdEventType;
        this.c = d;
        this.b = num;
    }

    Map<String, Object> a() {
        HashMap map = new HashMap();
        map.put("adVolume", this.c);
        map.put("playhead", this.b);
        map.put("aTimeStamp", this.f);
        map.put("type", this.d.toString());
        return map;
    }
}
