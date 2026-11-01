package com.applovin.impl.sdk.utils;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public static double a(long j) {
        double d = j;
        Double.isNaN(d);
        return d / 1000.0d;
    }

    public static float a(float f) {
        return f * 1000.0f;
    }

    public static int a(JSONObject jSONObject) {
        int iB = g.b(jSONObject, "video_completion_percent", -1, (com.applovin.impl.sdk.h) null);
        if (iB < 0 || iB > 100) {
            return 95;
        }
        return iB;
    }

    public static long a(com.applovin.impl.sdk.h hVar) {
        long jLongValue = ((Long) hVar.a(com.applovin.impl.sdk.b.b.eX)).longValue();
        long jLongValue2 = ((Long) hVar.a(com.applovin.impl.sdk.b.b.eY)).longValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        return (jLongValue <= 0 || jLongValue2 <= 0) ? jCurrentTimeMillis : jCurrentTimeMillis + (jLongValue - jLongValue2);
    }

    public static Activity a(View view, com.applovin.impl.sdk.h hVar) {
        if (view == null) {
            return null;
        }
        int i = 0;
        while (i < 1000) {
            i++;
            try {
                Context context = view.getContext();
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                Object parent = view.getParent();
                if (!(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } catch (Throwable th) {
                hVar.w().b("Utils", "Encountered error while retrieving activity from view", th);
            }
        }
        return null;
    }

    public static Bitmap a(Context context, int i, int i2) {
        FileInputStream fileInputStream = null;
        try {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int iPow = 1;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), i);
                if (options.outHeight > i2 || options.outWidth > i2) {
                    double d = i2;
                    double dMax = Math.max(options.outHeight, options.outWidth);
                    Double.isNaN(d);
                    Double.isNaN(dMax);
                    iPow = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(d / dMax) / Math.log(0.5d)));
                }
                new BitmapFactory.Options().inSampleSize = iPow;
                Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i);
                try {
                    fileInputStream.close();
                    fileInputStream.close();
                } catch (Exception unused) {
                }
                return bitmapDecodeResource;
            } catch (Exception unused2) {
                return null;
            }
        } catch (Exception unused3) {
            fileInputStream.close();
            fileInputStream.close();
            return null;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
                fileInputStream.close();
            } catch (Exception unused4) {
            }
            throw th;
        }
    }

    public static Bitmap a(File file, int i) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int iPow = 1;
            options.inJustDecodeBounds = true;
            fileInputStream = new FileInputStream(file);
            try {
                BitmapFactory.decodeStream(fileInputStream, null, options);
                fileInputStream.close();
                if (options.outHeight > i || options.outWidth > i) {
                    double d = i;
                    double dMax = Math.max(options.outHeight, options.outWidth);
                    Double.isNaN(d);
                    Double.isNaN(dMax);
                    iPow = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(d / dMax) / Math.log(0.5d)));
                }
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = iPow;
                fileInputStream2 = new FileInputStream(file);
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream2, null, options2);
                    fileInputStream2.close();
                    try {
                        fileInputStream.close();
                        fileInputStream2.close();
                    } catch (Exception unused) {
                    }
                    return bitmapDecodeStream;
                } catch (Exception unused2) {
                    try {
                        fileInputStream.close();
                        fileInputStream2.close();
                    } catch (Exception unused3) {
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream3 = fileInputStream2;
                    try {
                        fileInputStream.close();
                        fileInputStream3.close();
                    } catch (Exception unused4) {
                    }
                    throw th;
                }
            } catch (Exception unused5) {
                fileInputStream2 = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused6) {
            fileInputStream2 = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static View a(Context context, View view) {
        View viewF = f(context);
        return viewF != null ? viewF : a(view);
    }

    public static View a(View view) {
        View rootView;
        if (view == null || (rootView = view.getRootView()) == null) {
            return null;
        }
        View viewFindViewById = rootView.findViewById(R.id.content);
        return viewFindViewById != null ? viewFindViewById : rootView;
    }

    public static com.applovin.impl.sdk.ad.d a(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        return com.applovin.impl.sdk.ad.d.a(AppLovinAdSize.fromString(g.a(jSONObject, "ad_size", (String) null, hVar)), AppLovinAdType.fromString(g.a(jSONObject, AppEventsConstants.EVENT_PARAM_AD_TYPE, (String) null, hVar)), g.a(jSONObject, "zone_id", (String) null, hVar), hVar);
    }

    public static com.applovin.impl.sdk.h a(AppLovinSdk appLovinSdk) {
        try {
            Field declaredField = appLovinSdk.getClass().getDeclaredField("mSdkImpl");
            declaredField.setAccessible(true);
            return (com.applovin.impl.sdk.h) declaredField.get(appLovinSdk);
        } catch (Throwable unused) {
            throw new IllegalStateException("Internal error - unable to retrieve SDK implementation...");
        }
    }

    public static Object a(Object obj, com.applovin.impl.sdk.h hVar) {
        int iIntValue;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            HashMap map2 = new HashMap(map.size());
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                map2.put(key instanceof String ? (String) key : String.valueOf(key), a(entry.getValue(), hVar));
            }
            return map2;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next(), hVar));
            }
            return arrayList;
        }
        if (obj instanceof Date) {
            return String.valueOf(((Date) obj).getTime());
        }
        String strValueOf = String.valueOf(obj);
        if (obj instanceof String) {
            iIntValue = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.aR)).intValue();
            if (iIntValue <= 0 || strValueOf.length() <= iIntValue) {
                return strValueOf;
            }
        } else if (!(obj instanceof Uri) || (iIntValue = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.aS)).intValue()) <= 0 || strValueOf.length() <= iIntValue) {
            return strValueOf;
        }
        return strValueOf.substring(0, iIntValue);
    }

    public static String a(Context context) {
        Bundle bundleD = d(context);
        if (bundleD == null) {
            return null;
        }
        String string = bundleD.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(Constants.RequestParameters.AMPERSAND);
            }
            sb.append((Object) entry.getKey());
            sb.append('=');
            sb.append((Object) entry.getValue());
        }
        return sb.toString();
    }

    public static Field a(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Class superclass = cls.getSuperclass();
            if (superclass == null) {
                return null;
            }
            return a(superclass, str);
        }
    }

    public static List<com.applovin.impl.sdk.c.a> a(String str, JSONObject jSONObject, String str2, String str3, com.applovin.impl.sdk.h hVar) {
        return a(str, jSONObject, str2, null, str3, hVar);
    }

    public static List<com.applovin.impl.sdk.c.a> a(String str, JSONObject jSONObject, String str2, String str3, String str4, com.applovin.impl.sdk.h hVar) {
        JSONObject jSONObjectA = g.a(jSONObject, str, new JSONObject(), hVar);
        ArrayList arrayList = new ArrayList(jSONObjectA.length() + 1);
        if (k.b(str4)) {
            arrayList.add(new com.applovin.impl.sdk.c.a(str4, null));
        }
        if (jSONObjectA.length() > 0) {
            if (str3 == null) {
                str3 = "";
            }
            Iterator<String> itKeys = jSONObjectA.keys();
            while (itKeys.hasNext()) {
                try {
                    String next = itKeys.next();
                    if (!TextUtils.isEmpty(next)) {
                        String strOptString = jSONObjectA.optString(next);
                        if (!TextUtils.isEmpty(strOptString)) {
                            strOptString = strOptString.replace("{CLCODE}", str2).replace("{EVENT_ID}", str3);
                        }
                        arrayList.add(new com.applovin.impl.sdk.c.a(next.replace("{CLCODE}", str2).replace("{EVENT_ID}", str3), strOptString));
                    }
                } catch (Throwable th) {
                    hVar.w().b("Utils", "Failed to create and add postback url.", th);
                }
            }
        }
        return arrayList;
    }

    private static List<Class> a(List<String> list, com.applovin.impl.sdk.h hVar) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            try {
                arrayList.add(Class.forName(str));
            } catch (ClassNotFoundException unused) {
                hVar.w().d("Utils", "Failed to create class for name: " + str);
            }
        }
        return arrayList;
    }

    public static void a(AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.ad.d dVar, int i, com.applovin.impl.sdk.h hVar) {
        if (appLovinAdLoadListener != null) {
            try {
                if (appLovinAdLoadListener instanceof com.applovin.impl.sdk.k) {
                    ((com.applovin.impl.sdk.k) appLovinAdLoadListener).a(dVar, i);
                } else {
                    appLovinAdLoadListener.failedToReceiveAd(i);
                }
            } catch (Throwable th) {
                hVar.w().b("Utils", "Unable process a failure to receive an ad", th);
            }
        }
    }

    public static void a(String str, Boolean bool, Map<String, String> map) {
        if (bool.booleanValue()) {
            map.put(str, Boolean.toString(true));
        }
    }

    public static void a(String str, String str2, Map<String, String> map) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, str2);
    }

    public static void a(String str, JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        if (jSONObject.has("no_fill_reason")) {
            Object objA = g.a(jSONObject, "no_fill_reason", new Object(), hVar);
            StringBuilder sb = new StringBuilder();
            sb.append("\n**************************************************\n");
            sb.append("NO FILL received:\n");
            sb.append("..ID: " + str + "\n");
            sb.append("..SDK KEY: " + hVar.u() + "\n");
            sb.append("..Reason: " + objA + "\n");
            sb.append("**************************************************\n");
            hVar.w().e("AppLovinSdk", sb.toString());
        }
    }

    public static boolean a() {
        Bundle bundleD;
        Context contextA = com.applovin.impl.sdk.h.A();
        return (contextA == null || (bundleD = d(contextA)) == null || !bundleD.containsKey("applovin.sdk.verbose_logging")) ? false : true;
    }

    public static boolean a(long j, long j2) {
        return (j & j2) != 0;
    }

    public static boolean a(Context context, Uri uri, com.applovin.impl.sdk.h hVar) {
        boolean z;
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!(context instanceof Activity)) {
                intent.setFlags(DriveFile.MODE_READ_ONLY);
            }
            hVar.T().b();
            context.startActivity(intent);
            z = true;
        } catch (Throwable th) {
            hVar.w().b("Utils", "Unable to open \"" + uri + "\".", th);
            z = false;
        }
        if (!z) {
            hVar.T().c();
        }
        return z;
    }

    public static boolean a(View view, Activity activity) {
        View rootView;
        if (activity != null && view != null) {
            Window window = activity.getWindow();
            if (window != null) {
                rootView = window.getDecorView();
            } else {
                View viewFindViewById = activity.findViewById(R.id.content);
                if (viewFindViewById != null) {
                    rootView = viewFindViewById.getRootView();
                }
            }
            return a(view, rootView);
        }
        return false;
    }

    public static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (a(view, viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(com.applovin.impl.sdk.ad.g gVar, Context context, com.applovin.impl.sdk.h hVar) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        boolean z = gVar != null && (gVar.b() || gVar.e() == null || hVar.Q().a(gVar.e().getLastPathSegment(), context));
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        return z;
    }

    public static boolean a(AppLovinAd appLovinAd, com.applovin.impl.sdk.h hVar) {
        com.applovin.impl.sdk.n nVarW;
        String str;
        if (appLovinAd == null) {
            nVarW = hVar.w();
            str = "Failing ad display - ad is null.";
        } else {
            if (f.a(hVar.z(), hVar) || ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.dJ)).booleanValue()) {
                return true;
            }
            nVarW = hVar.w();
            str = "Failing ad display due to no internet connection.";
        }
        nVarW.e("AppLovinSdk", str);
        return false;
    }

    public static boolean a(Object obj, List<String> list, com.applovin.impl.sdk.h hVar) {
        if (list == null) {
            return false;
        }
        Iterator<Class> it = a(list, hVar).iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(obj)) {
                if (!(obj instanceof Map)) {
                    if (!(obj instanceof List)) {
                        return true;
                    }
                    Iterator it2 = ((List) obj).iterator();
                    while (it2.hasNext()) {
                        if (!a(it2.next(), list, hVar)) {
                            return false;
                        }
                    }
                    return true;
                }
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (!(entry.getKey() instanceof String)) {
                        hVar.w().a("Utils", "Invalid key type used. Map keys should be of type String.");
                        return false;
                    }
                    if (!a(entry.getValue(), list, hVar)) {
                        return false;
                    }
                }
                return true;
            }
        }
        hVar.w().a("Utils", "Object '" + obj + "' does not match any of the required types '" + list + "'.");
        return false;
    }

    public static boolean a(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static long b(float f) {
        return c(a(f));
    }

    public static AppLovinAd b(AppLovinAd appLovinAd, com.applovin.impl.sdk.h hVar) {
        if (!(appLovinAd instanceof com.applovin.impl.sdk.ad.h)) {
            return appLovinAd;
        }
        com.applovin.impl.sdk.ad.h hVar2 = (com.applovin.impl.sdk.ad.h) appLovinAd;
        AppLovinAd appLovinAdDequeueAd = hVar.p().dequeueAd(hVar2.getAdZone());
        hVar.w().a("Utils", "Dequeued ad for dummy ad: " + appLovinAdDequeueAd);
        if (appLovinAdDequeueAd != null) {
            hVar2.a(appLovinAdDequeueAd);
            ((AppLovinAdBase) appLovinAdDequeueAd).setDummyAd(hVar2);
            return appLovinAdDequeueAd;
        }
        if (((Boolean) hVar.a(com.applovin.impl.sdk.b.b.ck)).booleanValue()) {
            return hVar2.a();
        }
        return null;
    }

    public static String b(String str) {
        return str.replace("{PLACEMENT}", "");
    }

    public static Map<String, String> b(Map<String, String> map) {
        HashMap map2 = new HashMap(map);
        for (String str : map2.keySet()) {
            String str2 = (String) map2.get(str);
            if (str2 != null) {
                map2.put(str, k.e(str2));
            }
        }
        return map2;
    }

    public static boolean b(Context context) {
        Bundle bundleD;
        if (context == null) {
            context = com.applovin.impl.sdk.h.A();
        }
        return (context == null || (bundleD = d(context)) == null || !bundleD.getBoolean("applovin.sdk.test_ads", false)) ? false : true;
    }

    private static long c(float f) {
        return Math.round(f);
    }

    public static MaxAdFormat c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase(ai.a.cW)) {
            return MaxAdFormat.BANNER;
        }
        if (str.equalsIgnoreCase("mrec")) {
            return MaxAdFormat.MREC;
        }
        if (str.equalsIgnoreCase("leaderboard") || str.equalsIgnoreCase("leader")) {
            return MaxAdFormat.LEADER;
        }
        if (str.equalsIgnoreCase(aa.e.bo) || str.equalsIgnoreCase("inter")) {
            return MaxAdFormat.INTERSTITIAL;
        }
        if (str.equalsIgnoreCase(Constants.CONVERT_REWARDED) || str.equalsIgnoreCase("reward")) {
            return MaxAdFormat.REWARDED;
        }
        throw new IllegalArgumentException("Unknown format: " + str);
    }

    public static void c(AppLovinAd appLovinAd, com.applovin.impl.sdk.h hVar) {
        if (appLovinAd instanceof AppLovinAdBase) {
            String strU = hVar.u();
            String strU2 = ((AppLovinAdBase) appLovinAd).getSdk().u();
            if (strU.equals(strU2)) {
                return;
            }
            hVar.w().e("AppLovinAd", "Ad was loaded from sdk with key: " + strU2 + ", but is being rendered from sdk with key: " + strU);
            hVar.G().a(com.applovin.impl.sdk.c.g.p);
        }
    }

    public static boolean c(Context context) {
        Bundle bundleD;
        if (context == null) {
            context = com.applovin.impl.sdk.h.A();
        }
        return (context == null || (bundleD = d(context)) == null || !bundleD.getBoolean("applovin.sdk.verbose_logging", false)) ? false : true;
    }

    public static Bundle d(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable th) {
            Log.e("Utils", "Unable to retrieve application metadata", th);
            return null;
        }
    }

    public static String d(String str) {
        Uri uri = Uri.parse(str);
        return new Uri.Builder().scheme(uri.getScheme()).authority(uri.getAuthority()).path(uri.getPath()).build().toString();
    }

    public static int e(Context context) {
        Resources resources;
        Configuration configuration;
        if (context == null || (resources = context.getResources()) == null || (configuration = resources.getConfiguration()) == null) {
            return 0;
        }
        return configuration.orientation;
    }

    public static View f(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getDecorView().findViewById(R.id.content);
        }
        return null;
    }
}
