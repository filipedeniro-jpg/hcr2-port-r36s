package com.applovin.impl.sdk.b;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class a<T> extends b<T> {
    public static final b<String> a = a("mediation_endpoint", "https://ms.applovin.com/");
    public static final b<String> b = a("mediation_backup_endpoint", "https://ms.applvn.com/");
    public static final b<Boolean> c = a("immediate_mediation_ad_fetch_after_signal_collection", true);
    public static final b<Long> d = a("fetch_next_ad_retry_delay_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(2)));
    public static final b<Long> e = a("fetch_next_ad_timeout_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(5)));
    public static final b<Long> f = a("fetch_mediation_debugger_info_timeout_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(3)));
    public static final b<Boolean> g = a("pass_extra_parameters", true);
    public static final b<Boolean> h = a("process_ad_waterfall_immediately", false);
    public static final b<String> i = a("postback_macros", "{\"{MCODE}\":\"mcode\",\"{BCODE}\":\"bcode\",\"{ICODE}\":\"icode\",\"{SCODE}\":\"scode\"}");
    public static final b<Boolean> j = a("persistent_mediated_postbacks", false);
    public static final b<Integer> k = a("max_signal_collector_threads", 3);
    public static final b<Boolean> l = a("signal_collector_clear_threads", false);
    public static final b<Long> m = a("max_signal_provider_latency_ms", 30000L);
    public static final b<Integer> n = a("max_adapter_version_length", 20);
    public static final b<Integer> o = a("max_adapter_sdk_version_length", 20);
    public static final b<Integer> p = a("max_adapter_signal_length", 5120);
    public static final b<Long> q = a("init_completion_delay_ms", -1L);
    public static final b<Integer> r = a("max_auto_init_adapters_threads", 3);
    public static final b<Boolean> s = a("run_adapter_operations_on_ui_thread", true);
    public static final b<Long> t = a("default_adapter_timeout_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(10)));
    public static final b<Integer> u = a("default_ad_view_width", -1);
    public static final b<Integer> v = a("default_ad_view_height", 50);
    public static final b<Long> w = a("ad_refresh_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(30)));
    public static final b<Long> x = a("ad_load_failure_refresh_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(30)));
    public static final b<String> y = a("ad_load_failure_refresh_ignore_error_codes", "204");
    public static final b<Long> z = a("refresh_ad_on_app_resume_elapsed_threshold_ms", 0L);
    public static final b<Boolean> A = a("refresh_ad_view_timer_responds_to_background", true);
    public static final b<Boolean> B = a("refresh_ad_view_timer_responds_to_store_kit", true);
    public static final b<Long> C = a("ad_view_fade_in_animation_ms", 150L);
    public static final b<Long> D = a("ad_view_fade_out_animation_ms", 150L);
    public static final b<Long> E = a("fullscreen_display_delay_ms", 600L);
    public static final b<Boolean> F = a("is_testing", false);
    public static final b<Boolean> G = a("process_next_waterfall_ad_on_mediation_main_queue", false);
    public static final b<Boolean> H = a("use_mediated_sdk_queues", true);
    public static final b<Long> I = a("ad_view_refresh_precache_request_viewability_undesired_flags", 118L);
    public static final b<Long> J = a("ad_view_refresh_precache_request_delay_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(2)));
    public static final b<Boolean> K = a("ad_view_block_publisher_load_if_refresh_scheduled", true);
    public static final b<Boolean> L = a("fullscreen_ads_block_publisher_load_if_another_showing", true);
    public static final b<Long> M = a("ad_expiration_ms", Long.valueOf(TimeUnit.HOURS.toMillis(4)));
    public static final b<Long> N = a("ad_hidden_timeout_ms", -1L);
    public static final b<Boolean> O = a("fdq", false);
    public static final b<Boolean> P = a("schedule_ad_hidden_on_ad_dismiss", false);
    public static final b<Long> Q = a("ad_hidden_on_ad_dismiss_callback_delay_ms", Long.valueOf(TimeUnit.SECONDS.toMillis(1)));
    public static final b<Boolean> R = a("destroy_on_load_failure", false);
    public static final b<Boolean> S = a("validate_ad_format", false);
}
