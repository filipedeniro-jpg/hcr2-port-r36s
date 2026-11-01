package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ky, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0661Ky {
    private C0661Ky() {
    }

    public static String B() {
        String prefix = AdInternalSettings.getUrlPrefix();
        return TextUtils.isEmpty(prefix) ? "https://graph.facebook.com/network_ads_common" : String.format(Locale.US, "https://graph.%s.facebook.com/network_ads_common", prefix);
    }

    public static String C(Context context) {
        String str;
        String prefix = AdInternalSettings.getUrlPrefix();
        if (TextUtils.isEmpty(prefix)) {
            str = "https://www.facebook.com/adnw_logging/";
        } else {
            str = String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", prefix);
        }
        String strC = JA.c(context);
        return TextUtils.isEmpty(strC) ? str : str.replace("www", strC);
    }

    public static String D() {
        String prefix = AdInternalSettings.getUrlPrefix();
        return TextUtils.isEmpty(prefix) ? "https://graph.facebook.com/693953940997901/bugs" : String.format(Locale.US, "https://graph.%s.facebook.com/693953940997901/bugs", prefix);
    }

    public static String E() {
        String prefix = AdInternalSettings.getUrlPrefix();
        return TextUtils.isEmpty(prefix) ? "https://www.facebook.com/adnw_sync" : String.format(Locale.US, "https://www.%s.facebook.com/adnw_sync", prefix);
    }
}
