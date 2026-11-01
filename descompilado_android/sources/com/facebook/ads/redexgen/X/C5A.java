package com.facebook.ads.redexgen.X;

import android.content.Context;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5A, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum C5A {
    BATTERY("adnw_wo_is_native_battery_signal_enabled"),
    ACTIVITY_MANAGER("adnw_wo_is_native_activity_manager_signal_enabled"),
    POWER_MANAGER("adnw_wo_is_native_power_manager_signal_enabled"),
    SYSTEM_SETTINGS("adnw_wo_is_native_system_settings_signal_enabled"),
    PACKAGE_MANAGER("adnw_wo_is_native_package_manager_signal_enabled"),
    APPLICATION_INFO("adnw_wo_is_native_application_info_signal_enabled"),
    KEYGUARD_MANAGER("adnw_wo_is_native_keyguard_manager_signal_enabled"),
    TELEPHONY_MANAGER("adnw_wo_is_native_telephony_manager_signal_enabled"),
    DEVICE_SETTINGS("adnw_wo_is_native_device_settings_signal_enabled"),
    AUDIO_MANAGER("adnw_wo_is_native_audio_manager_signal_enabled"),
    NETWORK_NATIVE("adnw_wo_is_native_network_signal_enabled"),
    ROOT_INFO("adnw_wo_is_native_root_info_signal_enabled"),
    CONNECTIVITY_MANAGER("adnw_wo_is_native_connectivity_manager_signal_enabled");

    public final String B;

    C5A(String name) {
        this.B = name;
    }

    public final boolean A(Context context) {
        return JA.Y(context).A(this.B, false);
    }
}
