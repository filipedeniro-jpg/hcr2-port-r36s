package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.ads.mediation.unity.BuildConfig;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: assets/audience_network.dex */
@AnyThread
public final class IB {
    private static String D = null;
    private static final Set<String> B = new HashSet(1);
    private static final Set<String> C = new HashSet(2);

    static {
        B.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        C.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        C.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        C.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        C.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    private IB() {
    }

    public static LI B(Context context) {
        return F(context, true);
    }

    public static LI C(Context context) {
        return D(context, true);
    }

    @WorkerThread
    public static LI D(Context context, boolean shouldCheckSystemHttpAgent) {
        LI li = new LI();
        H(context, li, shouldCheckSystemHttpAgent);
        if (!G()) {
            li.J(C);
            li.I(B);
        }
        if (MK.E()) {
            Map<String, String> mapC = MK.C();
            for (String str : mapC.keySet()) {
                String str2 = mapC.get(str);
                if (str2 != null) {
                    li.A(str, str2);
                }
            }
        }
        return li;
    }

    public static boolean E(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
        }
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    private static LI F(Context context, boolean shouldCheckSystemHttpAgent) {
        LI li = new LI();
        H(context, li, shouldCheckSystemHttpAgent);
        return li;
    }

    private static boolean G() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    private static void H(Context context, LI client, boolean shouldCheckSystemHttpAgent) {
        if (G()) {
            client.G(360000);
            client.m259K(120000);
        } else {
            client.G(BuildConfig.VERSION_CODE);
        }
        client.H(3);
        client.A("user-agent", C0601In.F(new IL(context), context, shouldCheckSystemHttpAgent));
    }
}
