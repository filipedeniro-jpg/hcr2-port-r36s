package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.StrictMode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3O, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C3O {

    @InterfaceC01641r
    private static String B = null;

    private C3O() {
    }

    @SuppressLint({"BadMethodUse-java.util.UUID.randomUUID"})
    @TargetApi(9)
    public static String B() {
        if (B == null) {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                B = UUID.randomUUID().toString();
            } finally {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            }
        }
        return B;
    }

    public static void C(AnonymousClass56 baseBDConfig, EnumC02504z level, String operation, @InterfaceC01641r String bdSessionId, @InterfaceC01641r String exceptionName, @InterfaceC01641r Exception exception) {
        String message;
        J1 j1A = baseBDConfig.A();
        if (j1A == null) {
        }
        switch (level) {
            case INFO:
                j1A.pC(EnumC02504z.INFO.A(), operation, F(baseBDConfig), bdSessionId, null, null, null);
                break;
            case ERROR:
                String strA = EnumC02504z.ERROR.A();
                Map<String, String> mapF = F(baseBDConfig);
                if (exception != null) {
                    message = exception.getMessage();
                } else {
                    message = null;
                }
                j1A.pC(strA, operation, mapF, bdSessionId, message, exception != null ? exception.toString() : null, exceptionName);
                break;
        }
    }

    public static float D(float value) {
        return new BigDecimal(value).setScale(3, RoundingMode.HALF_UP).floatValue();
    }

    public static boolean E(EnumC02083j reputationTier) {
        int reputationTierValue = reputationTier.A();
        return reputationTierValue == EnumC02083j.PARANOID.A() || reputationTierValue == EnumC02083j.SUSPICIOUS.A() || reputationTierValue == EnumC02083j.BENIGN_COLLECT.A();
    }

    private static Map<String, String> F(AnonymousClass56 baseBDConfig) {
        HashMap map = new HashMap();
        map.put(EnumC02484x.APP_SESSION_ID.A(), B());
        map.put(EnumC02484x.TIMER_INTERVAL_TIME.A(), Long.toString(TimeUnit.SECONDS.toMillis(baseBDConfig.I())));
        map.put(EnumC02484x.BUFFER_CAPACITY.A(), Float.toString(C02073i.C()));
        map.put(EnumC02484x.DYNAMIC_SIGNAL_CIRCULAR_BUFFER_LENGTH.A(), Integer.toString(baseBDConfig.G()));
        map.put(EnumC02484x.STATIC_SIGNAL_CIRCULAR_BUFFER_LENGTH.A(), Integer.toString(baseBDConfig.K()));
        map.put(EnumC02484x.BIOMETRIC_SIGNAL_CIRCULAR_BUFFER_LENGTH.A(), Integer.toString(baseBDConfig.D()));
        map.put(EnumC02484x.SIGNAL_CONFIG.A(), G(baseBDConfig.J()));
        return map;
    }

    @InterfaceC01641r
    @SuppressLint({"EmptyCatchBlock"})
    private static String G(String config) {
        String timestamp = null;
        try {
            JSONObject jSONObject = new JSONObject(config);
            if (!jSONObject.has("t")) {
                return null;
            }
            timestamp = Integer.toString(jSONObject.getInt("t"));
            return timestamp;
        } catch (JSONException e) {
            return timestamp;
        }
    }
}
