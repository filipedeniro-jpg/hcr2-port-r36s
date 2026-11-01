package com.facebook.ads.redexgen.X;

import com.my.target.aq;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Je, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC0617Je {
    AD_RESPONSE_RECEIVED("ad_response_received"),
    AD_LOADED_CALLBACK("ad_loaded_callback"),
    ADAPTER_TIMEOUT("adapter_timeout"),
    CACHE_SUCCESS("cache_success"),
    CACHE_FAILURE("cache_failure"),
    SHOW_AD_CALLED("show_ad_called"),
    IMPRESSION(aq.a.dB),
    AD_CLOSED("ad_closed"),
    AD_CLOSE_CLICKED("ad_close_clicked"),
    SWIPE_TO_CLICK("swipe_to_click"),
    STORE_CLICK("store_click"),
    LINK_CLICK("link_click"),
    CLICKGUARD_CLICK("clickguard_click"),
    TWO_STEP_CLICK("two_step_click"),
    TWO_STEP_CANCEL_CLICK("two_step_cancel_click"),
    OFF_TARGET_CLICK("off_target_click"),
    CTA_CLICKED("cta_clicked"),
    PLAYABLE_CTA_CLICKED("playable_cta_clicked"),
    INTRO_CARD_SHOWN("intro_card_shown"),
    END_CARD_SHOWN("end_card_shown"),
    VIDEO_PREPARED("video_prepared"),
    VIDEO_ERROR("video_error"),
    VIDEO_COMPLETED("video_completed"),
    VIDEO_IDLE("video_idle"),
    VIDEO_PLAY("video_play"),
    VIDEO_PAUSED("video_paused"),
    VIDEO_PLAY_PAUSE_CLICKED("video_play_pause_clicked"),
    SOUND_ON("sound_on"),
    SOUND_OFF("sound_off"),
    SOUND_BUTTON_PRESSED("sound_btn_pressed"),
    VIDEO_SKIP_CLICKED("video_skip_clicked"),
    AD_CHOICES_CLICKED("ad_choices_clicked"),
    AD_REPORT_CLICKED("ad_report_clicked"),
    AD_REPORT_WHY_SEEING_THIS_CLICKED("ad_report_why_seeing_this_clicked"),
    AD_REPORT_MANAGE_PREFS_CLICKED("ad_report_manage_prefs_clicked"),
    AD_REPORTED("ad_reported"),
    APP_MOVED_TO_BACKGROUND("app_moved_to_background"),
    APP_RETURNED_TO_FOREGROUND("app_returned_to_foreground"),
    DEVICE_ROTATED("device_rotated"),
    AD_NUMBER_SELECTED("ad_number_selected"),
    AD_SELECTION_TIMEOUT("ad_selection_timeout");

    private final String B;

    EnumC0617Je(String name) {
        this.B = name;
    }

    public final String A() {
        return this.B;
    }
}
