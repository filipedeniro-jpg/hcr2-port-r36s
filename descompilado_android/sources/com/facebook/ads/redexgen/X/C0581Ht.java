package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ht, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0581Ht {
    private static final String B = C0581Ht.class.getSimpleName();

    private C0581Ht() {
    }

    public static Map<String, String> B(Context context) {
        HashMap map = new HashMap();
        if (context == null) {
            Log.v(B, "Null context in window interactive check.");
        } else {
            try {
                map.put("kgr", String.valueOf(D(context)));
                if (context instanceof Activity) {
                    Window window = ((Activity) context).getWindow();
                    if (window != null) {
                        int i = window.getAttributes().flags;
                        int type = window.getAttributes().type;
                        map.put("wt", Integer.toString(type));
                        int type2 = 4194304 & i;
                        String flagDismissKeyguardEnabled = type2 > 0 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
                        map.put("wfdkg", flagDismissKeyguardEnabled);
                        int type3 = 524288 & i;
                        String flagDismissKeyguardEnabled2 = type3 > 0 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
                        map.put("wfswl", flagDismissKeyguardEnabled2);
                    } else {
                        String flagDismissKeyguardEnabled3 = B;
                        Log.v(flagDismissKeyguardEnabled3, "Invalid window in window interactive check, assuming interactive.");
                    }
                } else {
                    Log.v(B, "Invalid Activity context in window interactive check, assuming interactive.");
                }
            } catch (Exception e) {
                Log.e(B, "Exception in window info check", e);
                C0585Hx.D(context, "risky", C0584Hw.oB, new C0583Hv(e));
            }
        }
        return map;
    }

    public static boolean C(Context context) {
        return !J4.D(B(context));
    }

    private static boolean D(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }
}
