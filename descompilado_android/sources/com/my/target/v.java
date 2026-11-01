package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: JsEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public interface v {
    public static final String aA = "onExpand";
    public static final String aB = "onCollapse";
    public static final String aC = "onError";
    public static final String aD = "onAdError";
    public static final String aE = "onCloseClick";
    public static final String aF = "onComplete";
    public static final String aG = "onNoAd";
    public static final String aH = "onAdStart";
    public static final String aI = "onAdStop";
    public static final String aJ = "onAdPause";
    public static final String aK = "onAdResume";
    public static final String aL = "onAdComplete";
    public static final String aM = "onAdClick";
    public static final String aN = "onStat";
    public static final String aO = "onSizeChange";
    public static final String aP = "onRequestNewAds";
    public static final String az = "onReady";

    @NonNull
    String getType();
}
