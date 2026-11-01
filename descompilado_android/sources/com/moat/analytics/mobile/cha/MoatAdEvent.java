package com.moat.analytics.mobile.cha;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MoatAdEvent {

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private final Long f261;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    Double f262;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    Integer f263;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    MoatAdEventType f264;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private final Double f265;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static final Integer f259 = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static final Double f260 = Double.valueOf(Double.NaN);
    public static final Double VOLUME_MUTED = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f261 = Long.valueOf(System.currentTimeMillis());
        this.f264 = moatAdEventType;
        this.f262 = d;
        this.f263 = num;
        this.f265 = Double.valueOf(r.m410());
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f260);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f259, f260);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final Map<String, Object> m267() {
        HashMap map = new HashMap();
        map.put("adVolume", this.f262);
        map.put("playhead", this.f263);
        map.put("aTimeStamp", this.f261);
        map.put("type", this.f264.toString());
        map.put(Constants.RequestParameters.DEVICE_VOLUME, this.f265);
        return map;
    }
}
