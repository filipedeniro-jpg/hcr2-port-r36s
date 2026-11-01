package com.facebook.ads.redexgen.X;

import android.content.Context;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J5 {
    private J5() {
    }

    public static int B(Context context) {
        return JA.Y(context).C("bd_biometric_signal_circular_buffer_length", 0);
    }

    public static Map<Integer, String> C(Context context) {
        HashMap map = new HashMap();
        map.put(10920, L(context));
        map.put(10941, N(context));
        map.put(10300, M(context));
        map.put(10940, R(context));
        map.put(10943, O(context));
        map.put(10944, P(context));
        map.put(10945, Q(context));
        return Collections.unmodifiableMap(map);
    }

    public static HashMap<Integer, Integer> D() {
        return new HashMap<>();
    }

    public static int E(Context context) {
        return JA.Y(context).C("bd_dynamic_signal_circular_buffer_length", 0);
    }

    public static int F(Context context) {
        return JA.Y(context).C("bd_interval_duration_s", 0);
    }

    public static double G(Context context) {
        return JA.Y(context).B("bd_operational_logging_sampling_rate", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public static EnumC02083j H(Context context) {
        return EnumC02083j.B(JA.Y(context).C("bd_rt", 0));
    }

    public static String I(Context context) {
        String strD = JA.Y(context).D("bd_signals_config", "{}");
        return strD == null ? "{}" : strD;
    }

    public static int J(Context context) {
        return JA.Y(context).C("bd_static_signal_circular_buffer_length", 0);
    }

    public static boolean K(Context context) {
        return JA.Y(context).A("bd_is_interval_mode_enabled", false);
    }

    private static String L(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10920", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String M(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10300", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String N(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10941", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String O(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10943", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String P(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10944", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String Q(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10945", "{}");
        return strD == null ? "{}" : strD;
    }

    private static String R(Context context) {
        String strD = JA.Y(context).D("bd_signal_config_10940", "{}");
        return strD == null ? "{}" : strD;
    }
}
