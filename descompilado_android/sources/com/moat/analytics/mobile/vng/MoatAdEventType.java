package com.moat.analytics.mobile.vng;

import com.my.target.bj;

/* JADX INFO: loaded from: classes2.dex */
public enum MoatAdEventType {
    AD_EVT_FIRST_QUARTILE("AdVideoFirstQuartile"),
    AD_EVT_MID_POINT("AdVideoMidpoint"),
    AD_EVT_THIRD_QUARTILE("AdVideoThirdQuartile"),
    AD_EVT_COMPLETE("AdVideoComplete"),
    AD_EVT_PAUSED("AdPaused"),
    AD_EVT_PLAYING("AdPlaying"),
    AD_EVT_START("AdVideoStart"),
    AD_EVT_STOPPED("AdStopped"),
    AD_EVT_SKIPPED("AdSkipped"),
    AD_EVT_VOLUME_CHANGE("AdVolumeChange"),
    AD_EVT_ENTER_FULLSCREEN("fullScreen"),
    AD_EVT_EXIT_FULLSCREEN(bj.gA);

    private final String a;

    MoatAdEventType(String str) {
        this.a = str;
    }

    public static MoatAdEventType fromString(String str) {
        if (str == null) {
            return null;
        }
        for (MoatAdEventType moatAdEventType : values()) {
            if (str.equalsIgnoreCase(moatAdEventType.toString())) {
                return moatAdEventType;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.a;
    }
}
