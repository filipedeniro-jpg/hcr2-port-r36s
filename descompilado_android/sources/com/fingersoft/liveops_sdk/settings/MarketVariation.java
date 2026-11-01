package com.fingersoft.liveops_sdk.settings;

import com.fingersoft.ads_sdk.advertising.providers.AdProviders;
import com.fingersoft.hcr2.Config;

/* JADX INFO: loaded from: classes.dex */
public class MarketVariation {
    public static final int MV_AMAZON_APPSTORE = 1;
    public static final int MV_AMAZON_STREAMING_BOX = 8;
    public static final int MV_ANDROID_TV = 9;
    public static final int MV_GOOGLE_PLAY = 0;
    public static final int MV_MYGAMEZ = 5;
    public static final int MV_NOKIA_STORE = 6;
    public static final int MV_SAMSUNG_APPSTORE = 2;

    public static String derivePlatformName(int i) {
        if (i == 1) {
            return "amazon";
        }
        if (i == 2) {
            return AdProviders.SAMSUNG;
        }
        if (i == 5) {
            return Config.MARKET_MYGAMEZ;
        }
        if (i == 6) {
            return "nokia";
        }
        if (i != 8) {
            return i != 9 ? "android" : "androidtv";
        }
        return "amazonsb";
    }
}
