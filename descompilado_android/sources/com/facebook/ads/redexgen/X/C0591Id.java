package com.facebook.ads.redexgen.X;

import android.content.Context;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Id, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0591Id {
    private static HashMap<String, String> B;

    private C0591Id() {
    }

    public static synchronized Map<String, String> B(Context context) {
        HashMap map;
        if (B != null) {
            map = new HashMap(B);
        } else {
            LX.E();
            B = new HashMap<>();
            B.put("BUNDLE", context.getPackageName());
            C(context, B);
            map = new HashMap(B);
        }
        return map;
    }

    public static synchronized Map<String, String> C(Context context, Map<String, String> map) {
        map.put("SDK", "android");
        map.put("SDK_VERSION", BuildConfigApi.getVersionName());
        map.put("OS", Constants.JAVASCRIPT_INTERFACE_NAME);
        map.put("OSVERS", IL.C);
        IL il = new IL(context);
        map.put("APPVERS", il.C());
        map.put("APPNAME", il.B());
        map.put("APPBUILD", String.valueOf(il.D()));
        map.put("MODEL", il.H());
        map.put("SESSION_ID", LX.B());
        return map;
    }
}
