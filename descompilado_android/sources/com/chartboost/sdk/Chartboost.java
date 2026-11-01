package com.chartboost.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.adcolony.sdk.AdColonyAppOptions;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.c.RunnableC0032c;
import com.chartboost.sdk.h.a;
import com.chartboost.sdk.impl.aq;
import com.chartboost.sdk.impl.c.a;
import com.chartboost.sdk.impl.e.a;
import com.chartboost.sdk.impl.s;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Chartboost {
    @Deprecated
    public static void closeImpression() {
    }

    public static String getSDKVersion() {
        return "7.3.1";
    }

    public static boolean hasMoreApps(String str) {
        return false;
    }

    public static void setShouldDisplayLoadingViewForMoreApps(boolean z) {
    }

    private Chartboost() {
    }

    public enum CBFramework {
        CBFrameworkUnity(AdColonyAppOptions.UNITY),
        CBFrameworkCorona(AdColonyAppOptions.CORONA),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS"),
        CBFrameworkCocos2dx("Cocos2dx"),
        CBFrameworkPrime31Unreal("Prime31Unreal"),
        CBFrameworkWeeby("Weeby"),
        CBFrameworkOther("Other");

        private final String a;

        CBFramework(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }

        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFrameworkAir || this == CBFrameworkCocos2dx;
        }

        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFrameworkAir;
        }
    }

    public enum CBMediation {
        CBMediationAdMarvel(AdColonyAppOptions.ADMARVEL),
        CBMediationAdMob(AdColonyAppOptions.ADMOB),
        CBMediationFuse("Fuse"),
        CBMediationFyber(AdColonyAppOptions.FYBER),
        CBMediationHeyZap("HeyZap"),
        CBMediationMoPub(AdColonyAppOptions.MOPUB),
        CBMediationironSource(AdColonyAppOptions.IRONSOURCE),
        CBMediationHyprMX("HyprMX"),
        CBMediationAerServ("AerServ"),
        CBMediationOther("Other");

        private final String a;

        CBMediation(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.a;
        }
    }

    public enum CBPIDataUseConsent {
        UNKNOWN(-1),
        NO_BEHAVIORAL(0),
        YES_BEHAVIORAL(1);

        private static Map<Integer, CBPIDataUseConsent> b = new HashMap();
        private int a;

        static {
            for (CBPIDataUseConsent cBPIDataUseConsent : values()) {
                b.put(Integer.valueOf(cBPIDataUseConsent.a), cBPIDataUseConsent);
            }
        }

        CBPIDataUseConsent(int i) {
            this.a = i;
        }

        public static CBPIDataUseConsent valueOf(int i) {
            CBPIDataUseConsent cBPIDataUseConsent = b.get(Integer.valueOf(i));
            return cBPIDataUseConsent == null ? UNKNOWN : cBPIDataUseConsent;
        }

        public int getValue() {
            return this.a;
        }
    }

    public static void startWithAppId(Activity activity, String str, String str2) {
        aq.a("Chartboost.startWithAppId", activity);
        f fVar = new f(0);
        fVar.h = activity;
        fVar.i = str;
        fVar.j = str2;
        h.b(fVar);
    }

    public static void setPIDataUseConsent(Context context, CBPIDataUseConsent cBPIDataUseConsent) {
        h.a(context, cBPIDataUseConsent);
    }

    public static CBPIDataUseConsent getPIDataUseConsent() {
        return i.x;
    }

    @Deprecated
    public static void restrictDataCollection(Context context, boolean z) {
        setPIDataUseConsent(context, z ? CBPIDataUseConsent.NO_BEHAVIORAL : CBPIDataUseConsent.UNKNOWN);
    }

    public static void onCreate(Activity activity) {
        aq.a("Chartboost.onCreate", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.b(activity);
    }

    public static void onStart(Activity activity) {
        aq.a("Chartboost.onStart", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.d(activity);
    }

    public static void onResume(Activity activity) {
        aq.a("Chartboost.onResume", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.f(activity);
    }

    public static void onPause(Activity activity) {
        aq.a("Chartboost.onPause", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.g(activity);
    }

    public static void onStop(Activity activity) {
        aq.a("Chartboost.onStop", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.h(activity);
    }

    public static boolean onBackPressed() {
        aq.a("Chartboost.onBackPressed");
        h hVarA = h.a();
        if (hVarA == null) {
            return false;
        }
        return hVarA.q.j();
    }

    public static void onDestroy(Activity activity) {
        aq.a("Chartboost.onDestroy", activity);
        h hVarA = h.a();
        if (hVarA == null || i.s) {
            return;
        }
        hVarA.q.j(activity);
    }

    public static boolean hasRewardedVideo(String str) {
        aq.a("Chartboost.hasRewardedVideo", str);
        h hVarA = h.a();
        return (hVarA == null || !b.a() || hVarA.k.a(str) == null) ? false : true;
    }

    public static void cacheRewardedVideo(String str) {
        aq.a("Chartboost.cacheRewardedVideo", str);
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            if (s.a().a(str)) {
                CBLogging.b("Chartboost", "cacheRewardedVideo location cannot be empty");
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.l;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
                return;
            }
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.E) || (eVar.e && eVar.j)) {
                com.chartboost.sdk.impl.e eVar2 = hVarA.k;
                eVar2.getClass();
                hVarA.a.execute(eVar2.new a(3, str, null, null));
                return;
            }
            Handler handler2 = hVarA.p;
            com.chartboost.sdk.impl.c cVar2 = hVarA.l;
            cVar2.getClass();
            handler2.post(cVar2.new a(4, str, CBError.CBImpressionError.END_POINT_DISABLED));
        }
    }

    public static void showRewardedVideo(String str) {
        aq.a("Chartboost.showRewardedVideo", str);
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            if (s.a().a(str)) {
                CBLogging.b("Chartboost", "showRewardedVideo location cannot be empty");
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.l;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
                return;
            }
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.E) || (eVar.e && eVar.j)) {
                com.chartboost.sdk.impl.e eVar2 = hVarA.k;
                eVar2.getClass();
                hVarA.a.execute(eVar2.new a(4, str, null, null));
                return;
            }
            Handler handler2 = hVarA.p;
            com.chartboost.sdk.impl.c cVar2 = hVarA.l;
            cVar2.getClass();
            handler2.post(cVar2.new a(4, str, CBError.CBImpressionError.END_POINT_DISABLED));
        }
    }

    public static boolean hasInterstitial(String str) {
        aq.a("Chartboost.hasInterstitial", str);
        h hVarA = h.a();
        return (hVarA == null || !b.a() || hVarA.f.a(str) == null) ? false : true;
    }

    public static void cacheInterstitial(String str) {
        aq.a("Chartboost.cacheInterstitial", str);
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            if (s.a().a(str)) {
                CBLogging.b("Chartboost", "cacheInterstitial location cannot be empty");
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.g;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
                return;
            }
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.A) || (eVar.e && eVar.g)) {
                com.chartboost.sdk.impl.e eVar2 = hVarA.f;
                eVar2.getClass();
                hVarA.a.execute(eVar2.new a(3, str, null, null));
                return;
            }
            Handler handler2 = hVarA.p;
            com.chartboost.sdk.impl.c cVar2 = hVarA.g;
            cVar2.getClass();
            handler2.post(cVar2.new a(4, str, CBError.CBImpressionError.END_POINT_DISABLED));
        }
    }

    public static void showInterstitial(String str) {
        aq.a("Chartboost.showInterstitial", str);
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            if (s.a().a(str)) {
                CBLogging.b("Chartboost", "showInterstitial location cannot be empty");
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.g;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
                return;
            }
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.A) || (eVar.e && eVar.g)) {
                com.chartboost.sdk.impl.e eVar2 = hVarA.f;
                eVar2.getClass();
                hVarA.a.execute(eVar2.new a(4, str, null, null));
                return;
            }
            Handler handler2 = hVarA.p;
            com.chartboost.sdk.impl.c cVar2 = hVarA.g;
            cVar2.getClass();
            handler2.post(cVar2.new a(4, str, CBError.CBImpressionError.END_POINT_DISABLED));
        }
    }

    public static void cacheMoreApps(String str) {
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            hVarA.getClass();
            h.a aVar = hVarA.new a(5);
            aVar.b = str;
            hVarA.p.postDelayed(aVar, com.chartboost.sdk.Libraries.b.c);
        }
    }

    public static void showMoreApps(String str) {
        cacheMoreApps(str);
    }

    public static boolean isAnyViewVisible() {
        aq.a("Chartboost.isAnyViewVisible");
        h hVarA = h.a();
        return hVarA != null && hVarA.q.e();
    }

    public static void setMediation(CBMediation cBMediation, String str) {
        aq.a("Chartboost.setMediation");
        f fVar = new f(3);
        fVar.c = cBMediation;
        fVar.d = str;
        h.b(fVar);
    }

    public static void setFramework(CBFramework cBFramework, String str) {
        aq.a("Chartboost.setFramework");
        f fVar = new f(4);
        fVar.b = cBFramework;
        fVar.d = str;
        h.b(fVar);
    }

    @Deprecated
    public static void setFrameworkVersion(String str) {
        aq.a("Chartboost.setFrameworkVersion", str);
        f fVar = new f(5);
        fVar.d = str;
        h.b(fVar);
    }

    public static void setChartboostWrapperVersion(String str) {
        aq.a("Chartboost.setChartboostWrapperVersion", str);
        f fVar = new f(5);
        fVar.d = str;
        h.b(fVar);
    }

    public static String getCustomId() {
        return !b.b() ? "" : i.a;
    }

    public static void setCustomId(String str) {
        aq.a("Chartboost.setCustomId", str);
        f fVar = new f(6);
        fVar.e = str;
        h.b(fVar);
    }

    public static void setLoggingLevel(CBLogging.Level level) {
        aq.a("Chartboost.setLoggingLevel", level.toString());
        f fVar = new f(7);
        fVar.f = level;
        h.b(fVar);
    }

    public static CBLogging.Level getLoggingLevel() {
        b.b();
        return CBLogging.a;
    }

    public static a getDelegate() {
        return i.c;
    }

    public static void setDelegate(ChartboostDelegate chartboostDelegate) {
        aq.a("Chartboost.setDelegate", chartboostDelegate);
        f fVar = new f(8);
        fVar.g = chartboostDelegate;
        h.b(fVar);
    }

    public static boolean getAutoCacheAds() {
        return i.t;
    }

    public static void setAutoCacheAds(boolean z) {
        aq.a("Chartboost.setAutoCacheAds", z);
        h hVarA = h.a();
        if (hVarA != null) {
            hVarA.getClass();
            h.a aVar = hVarA.new a(1);
            aVar.c = z;
            h.b(aVar);
        }
    }

    public static void setShouldRequestInterstitialsInFirstSession(boolean z) {
        aq.a("Chartboost.setShouldRequestInterstitialsInFirstSession", z);
        if (b.b()) {
            f fVar = new f(1);
            fVar.a = z;
            h.b(fVar);
        }
    }

    public static void setShouldPrefetchVideoContent(boolean z) {
        aq.a("Chartboost.setShouldPrefetchVideoContent", z);
        h hVarA = h.a();
        if (hVarA == null || !b.a()) {
            return;
        }
        hVarA.getClass();
        h.a aVar = hVarA.new a(2);
        aVar.d = z;
        h.b(aVar);
    }

    public static void setShouldHideSystemUI(Boolean bool) {
        aq.a("Chartboost.setHideSystemUI", bool);
        i.g = bool.booleanValue();
    }

    public static boolean isWebViewEnabled() {
        h hVarA = h.a();
        return hVarA == null || hVarA.m.get().y;
    }

    @TargetApi(14)
    public static void setActivityCallbacks(boolean z) {
        Activity activityA;
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
        aq.a("Chartboost.setActivityCallbacks", z);
        h hVarA = h.a();
        if (hVarA == null || (activityA = hVarA.q.a()) == null || (activityLifecycleCallbacks = hVarA.q.h) == null) {
            return;
        }
        if (!i.s && z) {
            activityA.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            i.s = true;
        } else {
            if (!i.s || z) {
                return;
            }
            activityA.getApplication().unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            i.s = false;
        }
    }

    private static void showInterstitialAIR(String str, boolean z) {
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.A) || (eVar.e && eVar.g)) {
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.g;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INTERNAL));
                return;
            }
            i.c.didFailToLoadInterstitial(str, CBError.CBImpressionError.END_POINT_DISABLED);
        }
    }

    private static void showMoreAppsAIR(String str, boolean z) {
        cacheMoreApps(str);
    }

    private static void showRewardedVideoAIR(String str, boolean z) {
        h hVarA = h.a();
        if (hVarA != null && b.a() && h.f()) {
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            if ((eVar.y && eVar.E) || (eVar.e && eVar.j)) {
                Handler handler = hVarA.p;
                com.chartboost.sdk.impl.c cVar = hVarA.g;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INTERNAL));
                return;
            }
            i.c.didFailToLoadRewardedVideo(str, CBError.CBImpressionError.END_POINT_DISABLED);
        }
    }

    private static void forwardTouchEventsAIR(boolean z) {
        h hVarA = h.a();
        if (hVarA == null) {
            return;
        }
        c cVar = hVarA.q;
        cVar.getClass();
        c.RunnableC0032c runnableC0032c = cVar.new RunnableC0032c(6);
        runnableC0032c.c = z;
        h.b(runnableC0032c);
    }

    @TargetApi(28)
    public static void setActivityAttrs(Activity activity) {
        if (activity == null || !i.g) {
            if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
                CBLogging.d("Chartboost", "Attempting to show Status and Navigation bars on a fullscreen activity. Please change your Chartboost activity theme to: \"@android:style/Theme.Translucent\"` in your Manifest file");
                return;
            }
            return;
        }
        Window window = activity.getWindow();
        int i = 2;
        if (s.a().a(16)) {
            i = Build.VERSION.SDK_INT >= 19 ? 5894 : 1798;
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
        }
        window.getDecorView().setSystemUiVisibility(i);
    }
}
