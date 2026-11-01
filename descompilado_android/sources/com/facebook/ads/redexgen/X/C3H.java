package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C3H {
    public static JSONObject E = new JSONObject();
    public static JSONObject D = new JSONObject();
    public static final int B = Build.VERSION.SDK_INT;
    public static final String C = C3H.class.getSimpleName();

    public final void A(String signalName, @InterfaceC01641r Object signalValue, boolean isValidValue) {
        try {
            if (isValidValue) {
                JSONObject jSONObject = E;
                if (signalValue == null) {
                    signalValue = "null";
                }
                jSONObject.put(signalName, signalValue);
                return;
            }
            D.put(signalName, signalValue == null ? "error" : signalValue.toString());
        } catch (JSONException e) {
            Log.e(C, "Error adding signal value to JSON", e);
        }
    }

    public final boolean B(Context context, String permission) {
        return context != null && context.checkCallingOrSelfPermission(permission) == 0;
    }

    public final boolean C(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth");
    }

    public final boolean D(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.wifi");
    }
}
