package com.moat.analytics.mobile.iro;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MoatAdEvent {

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private final Long f486;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private final Double f487;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    Integer f488;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    Double f489;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    MoatAdEventType f490;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static final Integer f485 = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static final Double f484 = Double.valueOf(Double.NaN);
    public static final Double VOLUME_MUTED = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static final Double VOLUME_UNMUTED = Double.valueOf(1.0d);

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f486 = Long.valueOf(System.currentTimeMillis());
        this.f490 = moatAdEventType;
        this.f489 = d;
        this.f488 = num;
        this.f487 = Double.valueOf(p.m580());
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f484);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f485, f484);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final Map<String, Object> m446() {
        HashMap map = new HashMap();
        map.put("adVolume", this.f489);
        map.put("playhead", this.f488);
        map.put("aTimeStamp", this.f486);
        map.put("type", this.f490.toString());
        map.put(Constants.RequestParameters.DEVICE_VOLUME, this.f487);
        return map;
    }
}
