package com.appsflyer;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailability;
import com.vungle.warren.network.VungleApiClient;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class l {
    l() {
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static o m159(ContentResolver contentResolver) {
        String string;
        if (contentResolver == null || AppsFlyerProperties.getInstance().getString("amazon_aid") != null || !VungleApiClient.MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
            return null;
        }
        int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking", 2);
        if (i == 0) {
            return new o(o.a.AMAZON, Settings.Secure.getString(contentResolver, "advertising_id"), false);
        }
        if (i == 2) {
            return null;
        }
        try {
            string = Settings.Secure.getString(contentResolver, "advertising_id");
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't fetch Amazon Advertising ID (Ad-Tracking is limited!)", th);
            string = "";
        }
        return new o(o.a.AMAZON, string, true);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0047 A[Catch: Throwable -> 0x004f, TRY_LEAVE, TryCatch #1 {Throwable -> 0x004f, blocks: (B:17:0x0041, B:19:0x0047), top: B:59:0x0041 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x009a  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a7 A[Catch: Throwable -> 0x00ad, TRY_LEAVE, TryCatch #0 {Throwable -> 0x00ad, blocks: (B:32:0x008c, B:35:0x009b, B:37:0x00a1, B:39:0x00a7), top: B:57:0x008c }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:52:0x0114  */
    /* JADX WARN: Code duplicated, block: B:57:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static void m158(Context context, Map<String, Object> map) {
        int iIsGooglePlayServicesAvailable;
        String string;
        boolean z;
        AFLogger.afInfoLog("Trying to fetch GAID..");
        StringBuilder sb = new StringBuilder();
        try {
            iIsGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            iIsGooglePlayServicesAvailable = -1;
        }
        String string2 = null;
        try {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                string = advertisingIdInfo.getId();
                try {
                    string2 = Boolean.toString(!advertisingIdInfo.isLimitAdTrackingEnabled());
                    if (string != null) {
                        try {
                            if (string.length() == 0) {
                                sb.append("emptyOrNull |");
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            z = true;
                            AFLogger.afErrorLog(th.getMessage(), th);
                            sb.append(th.getClass().getSimpleName());
                            sb.append(" |");
                            AFLogger.afInfoLog("WARNING: Google Play Services is missing.");
                            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.ENABLE_GPS_FALLBACK, true)) {
                                try {
                                    h.e eVarM133 = h.m133(context);
                                    string = eVarM133.m138();
                                    string2 = Boolean.toString(eVarM133.m137() ? false : true);
                                    if (string != null || string.length() == 0) {
                                        sb.append("emptyOrNull (bypass) |");
                                    }
                                } catch (Throwable th3) {
                                    AFLogger.afErrorLog(th3.getMessage(), th3);
                                    sb.append(th3.getClass().getSimpleName());
                                    sb.append(" |");
                                    string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
                                    String string3 = AppsFlyerProperties.getInstance().getString("advertiserIdEnabled");
                                    if (th3.getLocalizedMessage() != null) {
                                        AFLogger.afInfoLog(th3.getLocalizedMessage());
                                    } else {
                                        AFLogger.afInfoLog(th3.toString());
                                    }
                                    string2 = string3;
                                }
                            }
                        }
                    } else {
                        sb.append("emptyOrNull |");
                    }
                    z = true;
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                    AFLogger.afErrorLog(th.getMessage(), th);
                    sb.append(th.getClass().getSimpleName());
                    sb.append(" |");
                    AFLogger.afInfoLog("WARNING: Google Play Services is missing.");
                    if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.ENABLE_GPS_FALLBACK, true)) {
                        h.e eVarM134 = h.m133(context);
                        string = eVarM134.m138();
                        string2 = Boolean.toString(eVarM134.m137() ? false : true);
                        if (string != null) {
                            sb.append("emptyOrNull (bypass) |");
                        } else {
                            sb.append("emptyOrNull (bypass) |");
                        }
                    }
                    if (context.getClass().getName().equals("android.app.ReceiverRestrictedContext")) {
                        string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
                        string2 = AppsFlyerProperties.getInstance().getString("advertiserIdEnabled");
                        sb.append("context = android.app.ReceiverRestrictedContext |");
                    }
                    if (sb.length() > 0) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(iIsGooglePlayServicesAvailable);
                        sb2.append(": ");
                        sb2.append((Object) sb);
                        map.put("gaidError", sb2.toString());
                    }
                    if (string != null) {
                        return;
                    } else {
                        return;
                    }
                }
                if (context.getClass().getName().equals("android.app.ReceiverRestrictedContext")) {
                    string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
                    string2 = AppsFlyerProperties.getInstance().getString("advertiserIdEnabled");
                    sb.append("context = android.app.ReceiverRestrictedContext |");
                }
                if (sb.length() > 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(iIsGooglePlayServicesAvailable);
                    sb3.append(": ");
                    sb3.append((Object) sb);
                    map.put("gaidError", sb3.toString());
                }
                if (string != null || string2 == null) {
                    return;
                }
                map.put(ServerParameters.ADVERTISING_ID_PARAM, string);
                map.put("advertiserIdEnabled", string2);
                AppsFlyerProperties.getInstance().set(ServerParameters.ADVERTISING_ID_PARAM, string);
                AppsFlyerProperties.getInstance().set("advertiserIdEnabled", string2);
                map.put("isGaidWithGps", String.valueOf(z));
                return;
            }
            sb.append("gpsAdInfo-null |");
            throw new a("GpsAdIndo is null");
        } catch (Throwable th5) {
            th = th5;
            string = null;
        }
    }

    static class a extends IllegalStateException {
        a(String str) {
            super(str);
        }
    }
}
