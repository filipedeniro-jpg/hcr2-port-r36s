package com.fingersoft.ads_sdk.advertising.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.BannerSize;
import com.tonyodev.fetch.FetchService;
import java.util.HashMap;
import net.hockeyapp.android.Strings;

/* JADX INFO: loaded from: classes.dex */
public class AdUtils {
    public static boolean logEnabled = true;

    public static BannerSize getBannerSize(Activity activity, int i) {
        HashMap map = new HashMap();
        map.put("480", new BannerSize(FetchService.ACTION_LOGGING, 50, 0));
        map.put("680", new BannerSize(FetchService.QUERY_SINGLE, 60, 1));
        map.put("1000", new BannerSize(728, 90, 2));
        map.put("10000", new BannerSize(Strings.EXPIRY_INFO_TITLE_ID, 90, 3));
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        for (String str : map.keySet()) {
            log("is " + displayMetrics.widthPixels + " < " + Integer.parseInt(str));
            if (displayMetrics.widthPixels >= Integer.parseInt(str)) {
                log("Returning: " + map.get(str));
                return (BannerSize) map.get(str);
            }
        }
        log("Returning thid: " + map.get("480"));
        return (BannerSize) map.get("480");
    }

    public static void log(String str) {
        if (str != null && logEnabled) {
            Log.d(AdManager.TAG, str);
        }
    }
}
