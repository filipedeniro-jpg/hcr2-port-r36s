package com.facebook.ads.redexgen.X;

import com.my.target.aq;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ja, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC0613Ja {
    TEST("test"),
    BROWSER_SESSION("browser_session"),
    CLOSE("close"),
    SHOW_AD_CALLED("show_ad_called"),
    IMPRESSION(aq.a.dB),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    VIDEO("video"),
    USER_RETURN("user_return"),
    AD_REPORTING("x_out"),
    PREVIEW_IMPRESSION("preview_impression"),
    AD_SELECTION("ad_selection"),
    CLICK_GUARD("click_guard"),
    TWO_STEP("two_step_dialog"),
    TWO_STEP_CANCEL("two_step_cancel"),
    SWIPE_TO_CLICK("swipe_click"),
    CLOSE_BROWSE_VIEW("browse_view_closed"),
    WATCH_AND_X_MINIMIZED("watch_and_x_minimized"),
    FUNNEL_LOGGING("ux_funnel"),
    DYNAMIC_SDK_LAYER_EVENT("dynamic_sdk_layer_event");

    private String B;

    EnumC0613Ja(String s) {
        this.B = s;
    }

    public static EnumC0613Ja B(String s) {
        for (EnumC0613Ja enumC0613Ja : values()) {
            if (enumC0613Ja.B.equalsIgnoreCase(s)) {
                return enumC0613Ja;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.B;
    }
}
