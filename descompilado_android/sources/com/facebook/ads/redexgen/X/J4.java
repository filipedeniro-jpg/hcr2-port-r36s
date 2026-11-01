package com.facebook.ads.redexgen.X;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class J4 {
    private static final String B = J4.class.getSimpleName();

    private J4() {
    }

    public static boolean B(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean C(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(B, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = map.get("wfdkg");
        String str2 = map.get("wfswl");
        String str3 = map.get("kgr");
        return str != null && str.equals("1") && str2 != null && str2.equals("1") && str3 != null && str3.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public static boolean D(Map<String, String> map) {
        String str;
        if (map == null || map.isEmpty()) {
            Log.v(B, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
            return false;
        }
        String str2 = map.get("wfdkg");
        String str3 = map.get("wfswl");
        if (str2 == null || !str2.equals("1")) {
            return (str3 == null || !str3.equals("1")) && (str = map.get("kgr")) != null && str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        return false;
    }
}
