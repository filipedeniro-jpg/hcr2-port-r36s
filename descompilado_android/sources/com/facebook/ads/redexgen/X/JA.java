package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JA {
    private static final String C = JA.class.getSimpleName();
    private static JA D;
    private final SharedPreferences B;

    private JA(Context context) {
        this.B = context.getApplicationContext().getSharedPreferences(I6.C("com.facebook.ads.FEATURE_CONFIG", context), 0);
    }

    public final boolean A(String key, boolean defaultValue) {
        String string = this.B.getString(key, String.valueOf(defaultValue));
        return string.equals("null") ? defaultValue : Boolean.valueOf(string).booleanValue();
    }

    public static boolean AB(Context context) {
        return Y(context).A("adnw_stacktrace_grouping_enabled", false);
    }

    public final double B(String key, double defaultValue) {
        String string = this.B.getString(key, String.valueOf(defaultValue));
        try {
            if (string.equals("null")) {
                return defaultValue;
            }
            defaultValue = Double.valueOf(string).doubleValue();
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int B(Context context) {
        return Y(context).C("adnw_log_additional_logging_events_limit", 10);
    }

    public static boolean BB(Context context) {
        return Y(context).A("accidental_clicks_config.two_step_confirmation", false);
    }

    public static int C(Context context) {
        return Y(context).C("adnw_additional_logging_retry_limit", 0);
    }

    public final int C(String key, int defaultValue) {
        String string = this.B.getString(key, String.valueOf(defaultValue));
        try {
            if (string.equals("null")) {
                return defaultValue;
            }
            defaultValue = Integer.valueOf(string).intValue();
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean CB(Context context) {
        return Y(context).A("adnw_log_additional_logging_session_events", false);
    }

    @Nullable
    public final String D(String key, String defaultValue) {
        String string = this.B.getString(key, defaultValue);
        return (string == null || string.equals("null")) ? defaultValue : string;
    }

    public static boolean D(Context context) {
        return Y(context).A("adnw_additional_logging_use_temp_file_for_rewrite", true);
    }

    public static boolean DB(Context context) {
        return Y(context).A("adnw_request_first_ad_from_main_process", true);
    }

    public final void E(@Nullable String jsonString) throws JSONException {
        bB(jsonString, null);
    }

    public static boolean E(Context context) {
        return Y(context).D("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static boolean EB(Context context) {
        return Y(context).A("adnw_restart_proxy_server_after_failure", false);
    }

    public static void F(Context context) {
        Y(context).B.edit().clear().commit();
    }

    public static boolean FB(Context context) {
        return Y(context).A("adnw_send_additional_debug_logging_stacktrace", false);
    }

    public static boolean G(Context context) {
        return Y(context).A("adnw_use_short_env_data_for_additional_logging", true);
    }

    public static boolean GB(Context context) {
        return Y(context).A("adnw_block_cta_before_impression", false);
    }

    public static boolean H(Context context) {
        return Y(context).A("adnw_do_not_reload_interstitial_adapter", true);
    }

    public static boolean HB(Context context) {
        return Y(context).A("adnw_block_lockscreen", false);
    }

    public static boolean I(Context context) {
        return Y(context).A("adnw_enable_auto_destroy_leaks", true);
    }

    public static boolean IB(Context context) {
        return Y(context).A("adnw_should_clear_feature_config_on_crashes", true);
    }

    public static boolean J(Context context) {
        return Y(context).A("adnw_enable_circular_process_binding", true);
    }

    public static boolean JB(Context context) {
        return Y(context).A("adnw_android_disable_blur", false);
    }

    public static boolean K(Context context) {
        return Y(context).A("adnw_android_interstitial_enable_end_card_blur_bg", true);
    }

    public static boolean KB(Context context) {
        return Y(context).A("adnw_android_disable_playable_precache", false);
    }

    public static boolean L(Context context) {
        return Y(context).A("adnw_enable_multiprocess_support", false);
    }

    public static boolean LB(Context context) {
        return Y(context).A("adnw_enhanced_viewability_area_check", false);
    }

    public static boolean M(Context context) {
        return Y(context).A("adnw_native_banner_templates_redesign", false);
    }

    public static boolean MB(Context context) {
        return Y(context).A("adnw_top_activity_viewability", false);
    }

    public static boolean N(Context context) {
        return Y(context).A("adnw_native_templates_redesign", false);
    }

    public static boolean NB(Context context) {
        return Y(context).A("adnw_android_mrc_impression_for_native_video_ads", false);
    }

    public static boolean O(Context context) {
        return Y(context).A("adnw_enable_wrong_ad_states_check", true);
    }

    public static boolean OB(Context context) {
        return Y(context).A("adnw_should_fail_on_cleartext_http_blocked", false);
    }

    public static int P(Context context) {
        return Y(context).C("adnw_acceptable_stacktrace_length", 100);
    }

    public static boolean PB(Context context) {
        return Y(context).A("adnw_video_interstitial_new_design", false);
    }

    public static int Q(Context context) {
        return cB(context, "additional_debug_logging_black_list_percentage", 0);
    }

    public static boolean QB(Context context) {
        return Y(context).A("adnw_mapp_markup_impression_after_image_load", false);
    }

    public static HashMap<String, Integer> R(Context context) throws JSONException {
        String strD = Y(context).D("additional_debug_logging_black_list", "[]");
        HashMap<String, Integer> map = new HashMap<>();
        JSONArray jSONArray = new JSONArray(strD);
        for (int i = 0; i < i; i++) {
            String eventConfig = jSONArray.optString(i);
            if (!TextUtils.isEmpty(eventConfig)) {
                if (eventConfig.contains(">")) {
                    String[] strArrSplit = eventConfig.split(">");
                    try {
                        map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
                    } catch (NumberFormatException e) {
                        throw new JSONException("Can't parse sampling.");
                    }
                } else {
                    map.put(eventConfig, -1);
                }
            }
        }
        return map;
    }

    public static boolean RB(Context context) {
        return Y(context).A("adnw_log_request_id_for_dsl_debug_logs", false);
    }

    public static int S(Context context) {
        return cB(context, "additional_debug_logging_sampling_percentage", 0);
    }

    public static boolean SB(Context context) {
        return Y(context).A("adnw_pre_cache_all_assets", true);
    }

    public static int T(Context context) {
        return Y(context).C("bd_data_logging_limit", 10);
    }

    public static boolean TB(Context context) {
        return Y(context).A("adnw_purge_on_413_response", false);
    }

    public static int U(Context context) {
        if (BuildConfigApi.isDebug()) {
            return 1;
        }
        return Y(context).C("adnw_android_cache_debug_events_sampling_rate", 0);
    }

    public static boolean UB(Context context) {
        return Y(context).A("adnw_show_cta_in_rv_pre_endcards", false);
    }

    public static int V(Context context) {
        return Y(context).C("clickguard_time_ms", 0);
    }

    public static boolean VB(Context context) {
        return Y(context).A("adnw_viewability_check_area_based", false);
    }

    public static int W(Context context) {
        return Y(context).C("adnw_debug_log_file_size_limit_bytes", 10485760);
    }

    public static boolean WB(Context context) {
        return Y(context).A("adnw_arrows_instead_of_x_skip_button", false);
    }

    public static int X(Context context) {
        return Y(context).C("adnw_debug_logging_event_limit", m(context));
    }

    public static boolean XB(Context context) {
        return Y(context).A("adnw_enable_inline_x_out_on_sdk", false);
    }

    public static synchronized JA Y(Context context) {
        if (D == null) {
            D = new JA(context);
        }
        return D;
    }

    public static boolean YB(Context context) {
        return Y(context).A("adnw_enable_inline_x_out_non_fullscreen_on_sdk", false);
    }

    public static boolean Z(Context context) {
        return Y(context).A("adnw_use_file_video_cache", false);
    }

    public static boolean ZB(Context context) {
        return Y(context).A("adnw_toolbar_new_design", false);
    }

    public static boolean a(Context context) {
        return Y(context).A("adnw_unique_db_name_per_process", false);
    }

    private final long aB(String key, long defaultValue) {
        String string = this.B.getString(key, String.valueOf(defaultValue));
        try {
            if (string.equals("null")) {
                return defaultValue;
            }
            defaultValue = Long.valueOf(string).longValue();
            return defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int b(Context context) {
        return Y(context).C("adnw_native_carousel_compact_threshold", 225);
    }

    private void bB(@Nullable String jsonString, String parentKey) throws JSONException {
        if (jsonString == null || jsonString.isEmpty() || jsonString.equals("[]")) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.B.edit();
        JSONObject jSONObject = new JSONObject(jsonString);
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.equals("accidental_clicks_config")) {
                bB(jSONObject.getString(next), next);
            } else {
                String str = next;
                if (parentKey != null) {
                    str = parentKey + "." + str;
                }
                String sharedPrefKey = jSONObject.getString(next);
                editorEdit.putString(str, sharedPrefKey);
            }
        }
        editorEdit.apply();
    }

    @Nullable
    public static String c(Context context) {
        return Y(context).D("adnw_logging_endpoint_prefix", "www");
    }

    private static int cB(Context context, String key, int defaultValue) {
        int iC = Y(context).C(key, defaultValue);
        return (iC < 0 || iC >= 101) ? defaultValue : iC;
    }

    public static int d(Context context) {
        return Y(context).C("minimum_elapsed_time_after_impression", -1);
    }

    public static int e(Context context) {
        return Y(context).C("adnw_acceptable_stacktrace_context_filter_size", -1);
    }

    public static int f(Context context) {
        return Y(context).C("stack_trace_sample_rate", 0);
    }

    public static int g(Context context) {
        return Y(context).C("adnw_time_to_wait_for_video_prepared_ms", 3000);
    }

    @Nullable
    public static String h(Context context) {
        return Y(context).D("accidental_clicks_config.two_step_cancel_button_text", "Cancel");
    }

    @Nullable
    public static String i(Context context) {
        return Y(context).D("accidental_clicks_config.two_step_confirmation_body", "You will be taken to another destination.");
    }

    @Nullable
    public static String j(Context context) {
        return Y(context).D("accidental_clicks_config.two_step_confirm_button_text", "Continue");
    }

    @Nullable
    public static String k(Context context) {
        return Y(context).D("accidental_clicks_config.two_step_confirmation_title", "Continue?");
    }

    public static long l(Context context) {
        return ((long) Y(context).C("unified_logging_dispatch_interval_seconds", 300)) * 1000;
    }

    public static int m(Context context) {
        return Y(context).C("unified_logging_event_limit", -1);
    }

    public static long n(Context context) {
        return Y(context).aB("unified_logging_immediate_delay_ms", 500L);
    }

    public static int o(Context context) {
        return Y(context).C("unified_logging_retry_limit", 20);
    }

    public static boolean p(Context context) {
        return Y(context).A("adnw_hide_error_dialog_for_ad_process", true);
    }

    public static boolean q(Context context) {
        return Y(context).A("adnw_filter_bidding_token", false);
    }

    public static boolean r(Context context) {
        return Y(context).A("adnw_debug_logging", false);
    }

    public static boolean s(Context context) {
        return Build.VERSION.SDK_INT >= 18 && Y(context).A("adnw_enable_debug_overlay", false);
    }

    public static boolean t(Context context) {
        return Build.VERSION.SDK_INT >= 19 && Y(context).A("adnw_enable_iab", false);
    }

    public static boolean u(Context context) {
        return Y(context).A("adnw_jnitest_enabled", false);
    }

    public static boolean v(Context context) {
        return Y(context).A("adnw_android_memory_opt", false);
    }

    public static boolean w(Context context) {
        return Y(context).A("adnw_native_view_snapshot_logging_enabled", false);
    }

    public static boolean x(Context context) {
        return Y(context).A("adnw_enable_rage_shake", false);
    }

    public static boolean y(Context context) {
        return Y(context).A("adnw_rv_buffer_check_enabled", true);
    }

    public static boolean z(Context context) {
        return Y(context).A("adnw_rv_playback_crash_fallback", true);
    }
}
