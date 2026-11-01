package com.adcolony.sdk;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.appsflyer.ServerParameters;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import com.ironsource.sdk.constants.Constants;
import com.vungle.warren.model.Cookie;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColony {
    static ExecutorService a = Executors.newSingleThreadExecutor();

    static boolean a(String str, ba baVar, int i, int i2) {
        return true;
    }

    public static boolean disable() {
        if (!a.e()) {
            return false;
        }
        Activity activityC = a.c();
        if (activityC != null && (activityC instanceof b)) {
            activityC.finish();
        }
        final l lVarA = a.a();
        for (final AdColonyInterstitial adColonyInterstitial : lVarA.m().c().values()) {
            aw.a(new Runnable() { // from class: com.adcolony.sdk.AdColony.1
                @Override // java.lang.Runnable
                public void run() {
                    AdColonyInterstitialListener listener = adColonyInterstitial.getListener();
                    adColonyInterstitial.a(true);
                    if (listener != null) {
                        listener.onExpiring(adColonyInterstitial);
                    }
                }
            });
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.AdColony.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<ai> arrayList = new ArrayList();
                Iterator<ai> it = lVarA.q().c().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                for (ai aiVar : arrayList) {
                    lVarA.a(aiVar.a());
                    if (aiVar instanceof ay) {
                        ay ayVar = (ay) aiVar;
                        if (!ayVar.g()) {
                            ayVar.loadUrl("about:blank");
                            ayVar.clearCache(true);
                            ayVar.removeAllViews();
                            ayVar.a(true);
                        }
                    }
                }
            }
        });
        a.a().a(true);
        return true;
    }

    public static boolean configure(Activity activity, @NonNull String str, @NonNull String... strArr) {
        return configure(activity, null, str, strArr);
    }

    public static boolean configure(Activity activity, AdColonyAppOptions adColonyAppOptions, @NonNull String str, @NonNull String... strArr) {
        Activity activityC;
        if (ak.a(0, null)) {
            new aa.a().a("Cannot configure AdColony; configuration mechanism requires 5 ").a("seconds between attempts.").a(aa.e);
            return false;
        }
        if (activity == null && (activityC = a.c()) != null) {
            activity = activityC;
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (adColonyAppOptions == null) {
            adColonyAppOptions = new AdColonyAppOptions();
        }
        if (a.b() && !y.d(a.a().d().d(), "reconfigurable")) {
            l lVarA = a.a();
            if (!lVarA.d().a().equals(str)) {
                new aa.a().a("Ignoring call to AdColony.configure, as the app id does not ").a("match what was used during the initial configuration.").a(aa.e);
                return false;
            }
            if (aw.a(strArr, lVarA.d().b())) {
                new aa.a().a("Ignoring call to AdColony.configure, as the same zone ids ").a("were used during the previous configuration.").a(aa.e);
                return false;
            }
        }
        adColonyAppOptions.a(str);
        adColonyAppOptions.a(strArr);
        adColonyAppOptions.e();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str2 = simpleDateFormat.format(new Date(jCurrentTimeMillis));
        boolean z = true;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && !strArr[i].equals("")) {
                z = false;
            }
        }
        if (str.equals("") || z) {
            new aa.a().a("AdColony.configure() called with an empty app or zone id String.").a(aa.g);
            return false;
        }
        a.a = true;
        if (Build.VERSION.SDK_INT < 14) {
            new aa.a().a("The minimum API level for the AdColony SDK is 14.").a(aa.e);
            a.a(activity, adColonyAppOptions, true);
        } else {
            a.a(activity, adColonyAppOptions, false);
        }
        String str3 = a.a().o().c() + "/adc3/AppInfo";
        JSONObject jSONObjectA = y.a();
        if (new File(str3).exists()) {
            jSONObjectA = y.c(str3);
        }
        JSONObject jSONObjectA2 = y.a();
        if (y.b(jSONObjectA, Cookie.APP_ID).equals(str)) {
            y.a(jSONObjectA2, "zoneIds", y.a(y.g(jSONObjectA, "zoneIds"), strArr, true));
            y.a(jSONObjectA2, Cookie.APP_ID, str);
        } else {
            y.a(jSONObjectA2, "zoneIds", y.a(strArr));
            y.a(jSONObjectA2, Cookie.APP_ID, str);
        }
        y.h(jSONObjectA2, str3);
        new aa.a().a("Configure: Total Time (ms): ").a("" + (System.currentTimeMillis() - jCurrentTimeMillis)).a(" and started at " + str2).a(aa.f);
        return true;
    }

    public static AdColonyZone getZone(@NonNull String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.getZone() as AdColony has not yet been ").a("configured.").a(aa.e);
            return null;
        }
        HashMap<String, AdColonyZone> mapF = a.a().f();
        if (mapF.containsKey(str)) {
            return mapF.get(str);
        }
        AdColonyZone adColonyZone = new AdColonyZone(str);
        a.a().f().put(str, adColonyZone);
        return adColonyZone;
    }

    public static boolean notifyIAPComplete(@NonNull String str, @NonNull String str2) {
        return notifyIAPComplete(str, str2, null, -1.0d);
    }

    public static boolean notifyIAPComplete(@NonNull final String str, @NonNull final String str2, final String str3, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) final double d) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to notifyIAPComplete as AdColony has not yet been ").a("configured.").a(aa.e);
            return false;
        }
        if (!aw.d(str) || !aw.d(str2)) {
            new aa.a().a("Ignoring call to notifyIAPComplete as one of the passed Strings ").a("is greater than ").a(128).a(" characters.").a(aa.e);
            return false;
        }
        if (str3 != null && str3.length() > 3) {
            new aa.a().a("You are trying to report an IAP event with a currency String ").a("containing more than 3 characters.").a(aa.e);
        }
        a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.5
            @Override // java.lang.Runnable
            public void run() {
                AdColony.a();
                JSONObject jSONObjectA = y.a();
                double d2 = d;
                if (d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    y.a(jSONObjectA, "price", d2);
                }
                String str4 = str3;
                if (str4 != null && str4.length() <= 3) {
                    y.a(jSONObjectA, "currency_code", str3);
                }
                y.a(jSONObjectA, "product_id", str);
                y.a(jSONObjectA, "transaction_id", str2);
                new af("AdColony.on_iap_report", 1, jSONObjectA).b();
            }
        });
        return true;
    }

    public static boolean requestNativeAdView(@NonNull String str, @NonNull AdColonyNativeAdViewListener adColonyNativeAdViewListener, @NonNull AdColonyAdSize adColonyAdSize) {
        return requestNativeAdView(str, adColonyNativeAdViewListener, adColonyAdSize, null);
    }

    public static boolean requestNativeAdView(@NonNull final String str, @NonNull final AdColonyNativeAdViewListener adColonyNativeAdViewListener, @NonNull final AdColonyAdSize adColonyAdSize, final AdColonyAdOptions adColonyAdOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to requestNativeAdView as AdColony has not yet been").a(" configured.").a(aa.e);
            a(adColonyNativeAdViewListener, str);
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", str);
        if (ak.a(1, bundle)) {
            a(adColonyNativeAdViewListener, str);
            return false;
        }
        try {
            a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.6
                @Override // java.lang.Runnable
                public void run() {
                    l lVarA = a.a();
                    if (lVarA.g() || lVarA.h()) {
                        AdColony.b();
                        AdColony.a(adColonyNativeAdViewListener, str);
                    }
                    if (!AdColony.a() && a.d()) {
                        AdColony.a(adColonyNativeAdViewListener, str);
                    }
                    if (lVarA.f().get(str) == null) {
                        new AdColonyZone(str);
                        new aa.a().a("Zone info for ").a(str).a(" doesn't exist in hashmap").a(aa.b);
                    }
                    lVarA.m().a(str, adColonyNativeAdViewListener, adColonyAdSize, adColonyAdOptions);
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            a(adColonyNativeAdViewListener, str);
            return false;
        }
    }

    public static boolean setAppOptions(@NonNull final AdColonyAppOptions adColonyAppOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.setAppOptions() as AdColony has not yet").a(" been configured.").a(aa.e);
            return false;
        }
        a.a().b(adColonyAppOptions);
        adColonyAppOptions.e();
        try {
            a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.7
                @Override // java.lang.Runnable
                public void run() {
                    AdColony.a();
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "options", adColonyAppOptions.d());
                    new af("Options.set_options", 1, jSONObjectA).b();
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public static AdColonyAppOptions getAppOptions() {
        if (a.e()) {
            return a.a().d();
        }
        return null;
    }

    public static boolean setRewardListener(@NonNull AdColonyRewardListener adColonyRewardListener) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.setRewardListener() as AdColony has not").a(" yet been configured.").a(aa.e);
            return false;
        }
        a.a().a(adColonyRewardListener);
        return true;
    }

    public static boolean removeRewardListener() {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.removeRewardListener() as AdColony has ").a("not yet been configured.").a(aa.e);
            return false;
        }
        a.a().a((AdColonyRewardListener) null);
        return true;
    }

    public static String getSDKVersion() {
        return !a.e() ? "" : a.a().n().C();
    }

    public static AdColonyRewardListener getRewardListener() {
        if (a.e()) {
            return a.a().i();
        }
        return null;
    }

    public static boolean addCustomMessageListener(@NonNull AdColonyCustomMessageListener adColonyCustomMessageListener, final String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.addCustomMessageListener as AdColony ").a("has not yet been configured.").a(aa.e);
            return false;
        }
        if (!aw.d(str)) {
            new aa.a().a("Ignoring call to AdColony.addCustomMessageListener.").a(aa.e);
            return false;
        }
        try {
            a.a().z().put(str, adColonyCustomMessageListener);
            a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.8
                @Override // java.lang.Runnable
                public void run() {
                    AdColony.a();
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "type", str);
                    new af("CustomMessage.register", 1, jSONObjectA).b();
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public static AdColonyCustomMessageListener getCustomMessageListener(@NonNull String str) {
        if (a.e()) {
            return a.a().z().get(str);
        }
        return null;
    }

    public static boolean removeCustomMessageListener(@NonNull final String str) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.removeCustomMessageListener as AdColony").a(" has not yet been configured.").a(aa.e);
            return false;
        }
        a.a().z().remove(str);
        a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.9
            @Override // java.lang.Runnable
            public void run() {
                AdColony.a();
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "type", str);
                new af("CustomMessage.unregister", 1, jSONObjectA).b();
            }
        });
        return true;
    }

    public static boolean clearCustomMessageListeners() {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.clearCustomMessageListeners as AdColony").a(" has not yet been configured.").a(aa.e);
            return false;
        }
        a.a().z().clear();
        a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.10
            @Override // java.lang.Runnable
            public void run() {
                AdColony.a();
                for (String str : a.a().z().keySet()) {
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "type", str);
                    new af("CustomMessage.unregister", 1, jSONObjectA).b();
                }
            }
        });
        return true;
    }

    public static boolean requestInterstitial(@NonNull String str, @NonNull AdColonyInterstitialListener adColonyInterstitialListener) {
        return requestInterstitial(str, adColonyInterstitialListener, null);
    }

    public static boolean requestInterstitial(@NonNull final String str, @NonNull final AdColonyInterstitialListener adColonyInterstitialListener, final AdColonyAdOptions adColonyAdOptions) {
        if (!a.e()) {
            new aa.a().a("Ignoring call to AdColony.requestInterstitial as AdColony has not").a(" yet been configured.").a(aa.e);
            adColonyInterstitialListener.onRequestNotFilled(new AdColonyZone(str));
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("zone_id", str);
        if (ak.a(1, bundle)) {
            AdColonyZone adColonyZone = a.a().f().get(str);
            if (adColonyZone == null) {
                adColonyZone = new AdColonyZone(str);
                new aa.a().a("Zone info for ").a(str + " doesn't exist in hashmap").a(aa.b);
            }
            adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
            return false;
        }
        try {
            a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColony.11
                @Override // java.lang.Runnable
                public void run() {
                    l lVarA = a.a();
                    if (lVarA.g() || lVarA.h()) {
                        AdColony.b();
                        AdColony.a(adColonyInterstitialListener, str);
                        return;
                    }
                    if (!AdColony.a() && a.d()) {
                        AdColony.a(adColonyInterstitialListener, str);
                        return;
                    }
                    final AdColonyZone adColonyZone2 = lVarA.f().get(str);
                    if (adColonyZone2 == null) {
                        adColonyZone2 = new AdColonyZone(str);
                        new aa.a().a("Zone info for ").a(str + " doesn't exist in hashmap").a(aa.b);
                    }
                    if (adColonyZone2.getZoneType() != 2) {
                        lVarA.m().a(str, adColonyInterstitialListener, adColonyAdOptions);
                        return;
                    }
                    Activity activityC = a.c();
                    if (activityC != null) {
                        activityC.runOnUiThread(new Runnable() { // from class: com.adcolony.sdk.AdColony.11.1
                            @Override // java.lang.Runnable
                            public void run() {
                                adColonyInterstitialListener.onRequestNotFilled(adColonyZone2);
                            }
                        });
                    }
                }
            });
            return true;
        } catch (RejectedExecutionException unused) {
            a(adColonyInterstitialListener, str);
            return false;
        }
    }

    static boolean a() {
        aw.a aVar = new aw.a(15.0d);
        l lVarA = a.a();
        while (!lVarA.A() && !aVar.b()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            }
        }
        return lVarA.A();
    }

    static boolean a(final AdColonyInterstitialListener adColonyInterstitialListener, final String str) {
        if (adColonyInterstitialListener == null || !a.d()) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.AdColony.2
            @Override // java.lang.Runnable
            public void run() {
                AdColonyZone adColonyZone = a.a().f().get(str);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                }
                adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
            }
        });
        return false;
    }

    static boolean a(final AdColonyNativeAdViewListener adColonyNativeAdViewListener, final String str) {
        if (adColonyNativeAdViewListener == null || !a.d()) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.AdColony.3
            @Override // java.lang.Runnable
            public void run() {
                AdColonyZone adColonyZone = !a.b() ? null : a.a().f().get(str);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(str);
                }
                adColonyNativeAdViewListener.onRequestNotFilled(adColonyZone);
            }
        });
        return false;
    }

    static void a(Activity activity, AdColonyAppOptions adColonyAppOptions) {
        String str;
        if (adColonyAppOptions == null || activity == null) {
            return;
        }
        String strA = aw.a(activity);
        String strB = aw.b();
        int iC = aw.c();
        String strH = a.a().c.h();
        if (a.a().p().a()) {
            str = "wifi";
        } else {
            str = a.a().p().b() ? "mobile" : "none";
        }
        HashMap map = new HashMap();
        map.put("sessionId", "unknown");
        map.put(ServerParameters.ADVERTISING_ID_PARAM, "unknown");
        map.put("countryLocale", Locale.getDefault().getDisplayLanguage() + " (" + Locale.getDefault().getDisplayCountry() + ")");
        map.put("countryLocalShort", a.a().c.u());
        map.put("manufacturer", a.a().c.w());
        map.put("model", a.a().c.x());
        map.put("osVersion", a.a().c.y());
        map.put("carrierName", strH);
        map.put("networkType", str);
        map.put("platform", "android");
        map.put("appName", strA);
        map.put(Constants.RequestParameters.APPLICATION_VERSION_NAME, strB);
        map.put("appBuildNumber", Integer.valueOf(iC));
        map.put(Cookie.APP_ID, "" + adColonyAppOptions.a());
        map.put("apiLevel", Integer.valueOf(a.a().c.r()));
        map.put(GeneralPropertiesWorker.SDK_VERSION, a.a().c.C());
        map.put("controllerVersion", "unknown");
        map.put("zoneIds", adColonyAppOptions.c());
        JSONObject mediationInfo = adColonyAppOptions.getMediationInfo();
        JSONObject pluginInfo = adColonyAppOptions.getPluginInfo();
        if (!y.b(mediationInfo, "mediation_network").equals("")) {
            map.put("mediationNetwork", y.b(mediationInfo, "mediation_network"));
            map.put("mediationNetworkVersion", y.b(mediationInfo, "mediation_network_version"));
        }
        if (!y.b(pluginInfo, "plugin").equals("")) {
            map.put("plugin", y.b(pluginInfo, "plugin"));
            map.put("pluginVersion", y.b(pluginInfo, "plugin_version"));
        }
        ac.a((HashMap<String, Object>) map);
    }

    static void b() {
        new aa.a().a("The AdColony API is not available while AdColony is disabled.").a(aa.g);
    }

    static String c() {
        return a.a().n().c();
    }

    static String d() {
        return a.a().n().b();
    }
}
