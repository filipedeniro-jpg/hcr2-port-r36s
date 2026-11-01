package com.applovin.mediation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.google.android.gms.ads.AdSize;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinUtils {
    private static final String DEFAULT_ZONE = "";

    public static int toAdMobErrorCode(int i) {
        if (i == 204) {
            return 3;
        }
        return i == -102 ? 2 : 0;
    }

    private static class ServerParameterKeys {
        private static final String PLACEMENT = "placement";
        private static final String SDK_KEY = "sdkKey";
        private static final String ZONE_ID = "zone_id";

        private ServerParameterKeys() {
        }
    }

    public static AppLovinSdk retrieveSdk(Bundle bundle, Context context) {
        AppLovinSdk appLovinSdk;
        String string = bundle != null ? bundle.getString("sdkKey") : null;
        if (!TextUtils.isEmpty(string)) {
            appLovinSdk = AppLovinSdk.getInstance(string, new AppLovinSdkSettings(), context);
        } else {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        appLovinSdk.setPluginVersion(BuildConfig.VERSION_NAME);
        appLovinSdk.setMediationProvider("admob");
        return appLovinSdk;
    }

    public static boolean androidManifestHasValidSdkKey(Context context) {
        Bundle bundleRetrieveMetadata = retrieveMetadata(context);
        if (bundleRetrieveMetadata != null) {
            return !TextUtils.isEmpty(bundleRetrieveMetadata.getString("applovin.sdk.key"));
        }
        return false;
    }

    private static Bundle retrieveMetadata(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String retrievePlacement(Bundle bundle) {
        if (bundle.containsKey("placement")) {
            return bundle.getString("placement");
        }
        return null;
    }

    public static String retrieveZoneId(Bundle bundle) {
        return bundle.containsKey("zone_id") ? bundle.getString("zone_id") : "";
    }

    public static boolean shouldMuteAudio(Bundle bundle) {
        return bundle != null && bundle.getBoolean("mute_audio");
    }

    public static AppLovinAdSize appLovinAdSizeFromAdMobAdSize(Context context, AdSize adSize) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(0, AdSize.BANNER);
        arrayList.add(1, AdSize.LEADERBOARD);
        arrayList.add(2, AdSize.MEDIUM_RECTANGLE);
        AdSize adSizeFindClosestSize = findClosestSize(context, adSize, arrayList);
        if (adSizeFindClosestSize == null) {
            return null;
        }
        if (AdSize.BANNER.equals(adSizeFindClosestSize)) {
            return AppLovinAdSize.BANNER;
        }
        if (AdSize.MEDIUM_RECTANGLE.equals(adSizeFindClosestSize)) {
            return AppLovinAdSize.MREC;
        }
        if (AdSize.LEADERBOARD.equals(adSizeFindClosestSize)) {
            return AppLovinAdSize.LEADER;
        }
        return null;
    }

    public static AdSize findClosestSize(Context context, AdSize adSize, ArrayList<AdSize> arrayList) {
        AdSize adSize2 = null;
        if (arrayList != null && adSize != null) {
            float f = context.getResources().getDisplayMetrics().density;
            AdSize adSize3 = new AdSize(Math.round(adSize.getWidthInPixels(context) / f), Math.round(adSize.getHeightInPixels(context) / f));
            for (AdSize largerByArea : arrayList) {
                if (isSizeInRange(adSize3, largerByArea)) {
                    if (adSize2 != null) {
                        largerByArea = getLargerByArea(adSize2, largerByArea);
                    }
                    adSize2 = largerByArea;
                }
            }
        }
        return adSize2;
    }

    private static boolean isSizeInRange(AdSize adSize, AdSize adSize2) {
        if (adSize2 == null) {
            return false;
        }
        int width = adSize.getWidth();
        int width2 = adSize2.getWidth();
        int height = adSize.getHeight();
        int height2 = adSize2.getHeight();
        double d = width;
        Double.isNaN(d);
        if (d * 0.5d <= width2 && width >= width2) {
            double d2 = height;
            Double.isNaN(d2);
            if (d2 * 0.7d <= height2 && height >= height2) {
                return true;
            }
        }
        return false;
    }

    private static AdSize getLargerByArea(AdSize adSize, AdSize adSize2) {
        return adSize.getWidth() * adSize.getHeight() > adSize2.getWidth() * adSize2.getHeight() ? adSize : adSize2;
    }
}
