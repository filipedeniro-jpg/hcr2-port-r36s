package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8l, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC03408l {
    HANDLE_CTA("handle_cta"),
    LAUNCH_AD_REPORTING("launch_ad_reporting_flow"),
    CLOSE_BROWSER("close_browser"),
    ENABLE_BACK_BUTTON("enable_back_button"),
    DISABLE_BACK_BUTTON("disable_back_button"),
    LOG("log"),
    DEBUG(Constants.RequestParameters.DEBUG),
    GET_ASSETS("get_assets"),
    UNKNOWN("");

    private final String B;

    EnumC03408l(String actionText) {
        this.B = actionText;
    }

    public static EnumC03408l B(String text) {
        for (EnumC03408l enumC03408l : values()) {
            if (enumC03408l.B.equalsIgnoreCase(text)) {
                return enumC03408l;
            }
        }
        return UNKNOWN;
    }
}
