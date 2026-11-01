package com.inmobi.ads;

import android.graphics.Color;
import androidx.annotation.NonNull;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tonyodev.fetch.FetchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends com.inmobi.commons.core.configs.a {
    private static boolean q = true;
    private static boolean r = false;
    private static boolean s = true;
    private static boolean t = true;
    private static final String u = "c";
    private static final Object v = new Object();
    a f;
    Map<String, a> g;
    public JSONObject l;
    private d x;
    private Map<String, d> y;
    String a = "https://i.w.inmobi.com/showad.asm";
    public String b = "https://sdktm.w.inmobi.com/sdkpubreq/v2";
    public int c = 20;
    int d = 60;
    int e = 60;
    boolean o = false;
    public e h = new e();
    public h i = new h();
    public f j = new f();
    public k k = new k();
    public j m = new j();
    public b n = new b();
    private g A = new g();
    private Map<String, g> z = new HashMap();
    private List<String> w = new LinkedList();

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class b {
        public int a = 3;
        public int b = 1;
        int c = 10;
        public long d = 104857600;
        public long e = 259200;
    }

    /* JADX INFO: renamed from: com.inmobi.ads.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdConfig.java */
    public static final class C0072c {
        boolean a = false;
        int b = 2000;
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class e {
        public int a = 3;
        public int b = 60;
        public int c = 120;
        public int d = 500;
        public int e = 10;
        public long f = 10800;
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class f {
        public long a = 432000;
        public int b = 3;
        public int c = 60;
        public String d = "https://i.l.inmobicdn.net/sdk/sdk/500/android/mraid.js";
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class h {
        int a = 60;
        int b = FetchService.ACTION_LOGGING;
        int c = FetchService.QUERY_SINGLE;
        int d = 100;
        String e = "#00000000";
        public int f = Color.parseColor("#00000000");
        public boolean g = true;
        int h = 5;
        int i = 20;
        long j = 5242880;
        ArrayList<String> k = new ArrayList<>(Arrays.asList(MimeTypes.VIDEO_MP4));
        public boolean l = false;
        public boolean m = false;
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class j {
        int a = 3;
        long b = 3145728;
        public long c = 31457280;
        C0072c d = new C0072c();
        public ArrayList<String> e = new ArrayList<>(Arrays.asList(MimeTypes.VIDEO_MP4, "image/jpeg", "image/jpg", "image/gif", "image/png"));
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class k {
        int a = 50;
        int b = 1000;
        int c = 100;
        int d = 250;
        int e = 67;
        int f = 50;
        public int g = 2000;
        int h = 50;
        boolean i = true;
        boolean j = true;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final String a() {
        return "ads";
    }

    private static JSONObject e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", 3L);
        jSONObject2.put("maxBatchSize", 10);
        jSONObject.put("wifi", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", 3L);
        jSONObject3.put("maxBatchSize", 5);
        jSONObject.put("others", jSONObject3);
        return jSONObject;
    }

    public c() {
        this.w.add("bannerDict");
        this.w.add("intDict");
        this.w.add("nativeDict");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", 1);
            jSONObject2.put("fetchLimit", 1);
            jSONObject2.put("minThreshold", 0);
            jSONObject2.put("timeToLive", IronSourceConstants.BN_INSTANCE_LOAD_ERROR);
            jSONObject2.put("sortByBid", false);
            jSONObject.put("base", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("maxCacheSize", 1);
            jSONObject3.put("fetchLimit", 1);
            jSONObject3.put("minThreshold", 1);
            jSONObject3.put("timeToLive", IronSourceConstants.BN_INSTANCE_LOAD_ERROR);
            jSONObject.put(com.my.target.ai.a.cW, jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("maxCacheSize", 1);
            jSONObject4.put("fetchLimit", 1);
            jSONObject4.put("minThreshold", 1);
            jSONObject4.put("timeToLive", IronSourceConstants.BN_INSTANCE_LOAD_ERROR);
            jSONObject.put("int", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("maxCacheSize", 100);
            jSONObject5.put("fetchLimit", 1);
            jSONObject5.put("minThreshold", 1);
            jSONObject5.put("timeToLive", IronSourceConstants.BN_INSTANCE_LOAD_ERROR);
            jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, jSONObject5);
            c(jSONObject);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("enabled", true);
            jSONObject6.put("samplingFactor", 0);
            this.l = jSONObject6;
            JSONObject jSONObject7 = new JSONObject();
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("enabled", q);
            jSONObject8.put("maxRetryCount", 1);
            jSONObject8.put("eventTTL", 14400L);
            jSONObject8.put("maxEventsToPersist", 1000);
            jSONObject8.put("txLatency", 60L);
            jSONObject8.put("processingInterval", 0L);
            jSONObject8.put("networkType", e());
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("enabled", r);
            jSONObject9.put("maxRetryCount", 1);
            jSONObject9.put("eventTTL", 14400L);
            jSONObject9.put("maxEventsToPersist", 1000);
            jSONObject9.put("txLatency", 60L);
            jSONObject9.put("processingInterval", 0L);
            jSONObject9.put("networkType", e());
            JSONObject jSONObject10 = new JSONObject();
            jSONObject10.put("enabled", s);
            jSONObject10.put("maxRetryCount", 1);
            jSONObject10.put("eventTTL", 14400L);
            jSONObject10.put("maxEventsToPersist", 1000);
            jSONObject10.put("txLatency", 60L);
            jSONObject10.put("processingInterval", 0L);
            jSONObject10.put("networkType", e());
            JSONObject jSONObject11 = new JSONObject();
            jSONObject11.put("enabled", t);
            jSONObject11.put("maxRetryCount", 1);
            jSONObject11.put("eventTTL", 14400L);
            jSONObject11.put("maxEventsToPersist", 1000);
            jSONObject11.put("txLatency", 60L);
            jSONObject11.put("processingInterval", 0L);
            jSONObject11.put("networkType", e());
            jSONObject7.put("baseDict", jSONObject8);
            jSONObject7.put("bannerDict", jSONObject9);
            jSONObject7.put("intDict", jSONObject10);
            jSONObject7.put("nativeDict", jSONObject11);
            b(jSONObject7);
        } catch (JSONException unused) {
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        if (jSONObject.has("url")) {
            this.a = jSONObject.getString("url");
        }
        if (jSONObject.has("trueRequestUrl")) {
            this.b = jSONObject.getString("trueRequestUrl");
        }
        this.c = jSONObject.getInt("minimumRefreshInterval");
        this.d = jSONObject.getInt("defaultRefreshInterval");
        this.e = jSONObject.getInt("fetchTimeout");
        this.o = jSONObject.getBoolean("flushCacheOnStart");
        c(jSONObject.getJSONObject("cache"));
        b(jSONObject.getJSONObject("trcFlagDict"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("preload");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("base");
        this.A = new g();
        this.A.a = jSONObject3.getBoolean("enabled");
        this.A.b = jSONObject3.getLong("placementExpiry");
        this.A.c = jSONObject3.getInt("maxPreloadedAds");
        jSONObject2.remove("base");
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject4 = jSONObject2.getJSONObject(next);
            g gVar = new g();
            gVar.a = jSONObject4.optBoolean("enabled", this.A.a);
            gVar.b = jSONObject4.optLong("placementExpiry", this.A.b);
            gVar.c = jSONObject4.optInt("maxPreloadedAds", this.A.c);
            this.z.put(next, gVar);
        }
        JSONObject jSONObject5 = jSONObject.getJSONObject("imai");
        this.h.a = jSONObject5.getInt("maxRetries");
        this.h.b = jSONObject5.getInt("pingInterval");
        this.h.c = jSONObject5.getInt("pingTimeout");
        this.h.d = jSONObject5.getInt("maxDbEvents");
        this.h.e = jSONObject5.getInt("maxEventBatch");
        this.h.f = jSONObject5.getLong("pingCacheExpiry");
        JSONObject jSONObject6 = jSONObject.getJSONObject("rendering");
        this.i.a = jSONObject6.getInt("renderTimeout");
        this.i.c = jSONObject6.getInt("picHeight");
        this.i.b = jSONObject6.getInt("picWidth");
        this.i.d = jSONObject6.getInt("picQuality");
        this.i.e = jSONObject6.getString("webviewBackground");
        this.i.g = jSONObject6.getBoolean("autoRedirectionEnforcement");
        this.i.h = jSONObject6.getInt("maxVibrationDuration");
        this.i.i = jSONObject6.getInt("maxVibrationPatternLength");
        this.i.m = jSONObject6.optBoolean("enablePubMuteControl", false);
        this.i.j = jSONObject6.getJSONObject("savecontent").getInt("maxSaveSize");
        synchronized (v) {
            this.i.k.clear();
            JSONArray jSONArray = jSONObject6.getJSONObject("savecontent").getJSONArray("allowedContentType");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.i.k.add(jSONArray.getString(i2));
            }
        }
        this.i.l = jSONObject6.getBoolean("shouldRenderPopup");
        JSONObject jSONObject7 = jSONObject.getJSONObject("mraid");
        this.j.a = jSONObject7.getLong("expiry");
        this.j.b = jSONObject7.getInt("maxRetries");
        this.j.c = jSONObject7.getInt("retryInterval");
        this.j.d = jSONObject7.getString("url");
        if (jSONObject.has("telemetry")) {
            this.l = jSONObject.getJSONObject("telemetry");
        }
        JSONObject jSONObject8 = jSONObject.getJSONObject("viewability");
        this.k.a = jSONObject8.getInt("impressionMinPercentageViewed");
        this.k.b = jSONObject8.getInt("impressionMinTimeViewed");
        this.k.e = jSONObject8.optInt("displayMinPercentageAnimate", 67);
        this.k.c = jSONObject8.optInt("visibilityThrottleMillis", 100);
        this.k.d = jSONObject8.optInt("impressionPollIntervalMillis", 250);
        this.k.i = jSONObject8.optBoolean("moatEnabled", false);
        this.k.j = jSONObject8.optBoolean("iasEnabled", false);
        JSONObject jSONObject9 = jSONObject8.getJSONObject("video");
        this.k.f = jSONObject9.getInt("impressionMinPercentageViewed");
        this.k.g = jSONObject9.getInt("impressionMinTimeViewed");
        this.k.h = jSONObject9.optInt("videoMinPercentagePlay", 50);
        JSONObject jSONObject10 = jSONObject.getJSONObject("vastVideo");
        this.m.a = jSONObject10.getInt("maxWrapperLimit");
        this.m.b = jSONObject10.getLong("optimalVastVideoSize");
        this.m.c = jSONObject10.getLong("vastMaxAssetSize");
        synchronized (v) {
            this.m.e.clear();
            JSONArray jSONArray2 = jSONObject10.getJSONArray("allowedContentType");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                this.m.e.add(jSONArray2.getString(i3));
            }
        }
        C0072c c0072c = this.m.d;
        JSONObject jSONObject11 = jSONObject10.getJSONObject("bitRate");
        c0072c.a = jSONObject11.getBoolean("bitrate_mandatory");
        c0072c.b = jSONObject11.getInt("headerTimeout");
        JSONObject jSONObject12 = jSONObject.getJSONObject("assetCache");
        this.n.b = jSONObject12.getInt("retryInterval");
        this.n.a = jSONObject12.getInt("maxRetries");
        this.n.c = jSONObject12.getInt("maxCachedAssets");
        this.n.d = jSONObject12.getInt("maxCacheSize");
        this.n.e = jSONObject12.getLong("timeToLive");
    }

    private void b(JSONObject jSONObject) throws JSONException {
        List<String> list;
        JSONObject jSONObject2 = jSONObject.getJSONObject("baseDict");
        this.f = new a();
        this.f.h = jSONObject2.getBoolean("enabled");
        this.f.a = jSONObject2.getInt("maxRetryCount");
        this.f.b = jSONObject2.getLong("eventTTL");
        this.f.c = jSONObject2.getInt("maxEventsToPersist");
        this.f.d = jSONObject2.getLong("processingInterval");
        this.f.e = jSONObject2.getLong("txLatency");
        a(jSONObject2.getJSONObject("networkType"), this.f);
        jSONObject.remove("baseDict");
        this.g = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next != null && (list = this.w) != null && list.contains(next)) {
                JSONObject jSONObject3 = jSONObject.getJSONObject(next);
                a aVar = new a();
                aVar.h = jSONObject3.optBoolean("enabled", this.f.h);
                aVar.a = jSONObject3.optInt("maxRetryCount", this.f.a);
                aVar.b = jSONObject3.optLong("eventTTL", this.f.b);
                aVar.c = jSONObject3.optInt("maxEventsToPersist", this.f.c);
                aVar.d = jSONObject3.optLong("processingInterval", this.f.d);
                aVar.e = jSONObject3.optLong("txLatency", this.f.e);
                a(jSONObject3.getJSONObject("networkType"), aVar);
                this.g.put(next, aVar);
            }
        }
    }

    private static void a(JSONObject jSONObject, a aVar) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            i iVar = new i();
            iVar.a = jSONObject2.getLong("retryInterval");
            iVar.b = jSONObject2.getInt("maxBatchSize");
            byte b2 = -1;
            int iHashCode = next.hashCode();
            if (iHashCode != -1068855134) {
                if (iHashCode != -1006804125) {
                    if (iHashCode == 3649301 && next.equals("wifi")) {
                        b2 = 0;
                    }
                } else if (next.equals("others")) {
                    b2 = 2;
                }
            } else if (next.equals("mobile")) {
                b2 = 1;
            }
            if (b2 == 0) {
                aVar.g = iVar;
            } else {
                aVar.f = iVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.x = new d();
        this.x.a = jSONObject2.getInt("maxCacheSize");
        this.x.b = jSONObject2.getInt("fetchLimit");
        this.x.c = jSONObject2.getInt("minThreshold");
        this.x.d = jSONObject2.getLong("timeToLive");
        this.x.e = jSONObject2.getBoolean("sortByBid");
        jSONObject.remove("base");
        this.y = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject3 = jSONObject.getJSONObject(next);
            d dVar = new d();
            dVar.a = jSONObject3.optInt("maxCacheSize", this.x.a);
            dVar.b = jSONObject3.optInt("fetchLimit", this.x.b);
            dVar.c = jSONObject3.optInt("minThreshold", this.x.c);
            dVar.d = jSONObject3.optLong("timeToLive", this.x.d);
            dVar.e = jSONObject3.optBoolean("sortByBid", this.x.e);
            this.y.put(next, dVar);
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final JSONObject b() throws JSONException {
        JSONObject jSONObjectB = super.b();
        jSONObjectB.put("url", this.a);
        jSONObjectB.put("trueRequestUrl", this.b);
        jSONObjectB.put("minimumRefreshInterval", this.c);
        jSONObjectB.put("defaultRefreshInterval", this.d);
        jSONObjectB.put("fetchTimeout", this.e);
        jSONObjectB.put("flushCacheOnStart", this.o);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("maxCacheSize", this.x.a);
        jSONObject2.put("fetchLimit", this.x.b);
        jSONObject2.put("minThreshold", this.x.c);
        jSONObject2.put("timeToLive", this.x.d);
        jSONObject.put("base", jSONObject2);
        for (Map.Entry<String, d> entry : this.y.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            d value = entry.getValue();
            jSONObject3.put("maxCacheSize", value.a);
            jSONObject3.put("fetchLimit", value.b);
            jSONObject3.put("minThreshold", value.c);
            jSONObject3.put("timeToLive", value.d);
            jSONObject.put(entry.getKey(), jSONObject3);
        }
        jSONObjectB.put("cache", jSONObject);
        jSONObjectB.put("trcFlagDict", g());
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("maxRetries", this.h.a);
        jSONObject4.put("pingInterval", this.h.b);
        jSONObject4.put("pingTimeout", this.h.c);
        jSONObject4.put("maxDbEvents", this.h.d);
        jSONObject4.put("maxEventBatch", this.h.e);
        jSONObject4.put("pingCacheExpiry", this.h.f);
        jSONObjectB.put("imai", jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("renderTimeout", this.i.a);
        jSONObject5.put("picWidth", this.i.b);
        jSONObject5.put("picHeight", this.i.c);
        jSONObject5.put("picQuality", this.i.d);
        jSONObject5.put("webviewBackground", this.i.e);
        jSONObject5.put("autoRedirectionEnforcement", this.i.g);
        jSONObject5.put("maxVibrationDuration", this.i.h);
        jSONObject5.put("maxVibrationPatternLength", this.i.i);
        jSONObject5.put("enablePubMuteControl", this.i.m);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("maxSaveSize", this.i.j);
        jSONObject6.put("allowedContentType", new JSONArray((Collection) this.i.k));
        jSONObject5.put("savecontent", jSONObject6);
        jSONObject5.put("shouldRenderPopup", this.i.l);
        jSONObjectB.put("rendering", jSONObject5);
        JSONObject jSONObject7 = new JSONObject();
        jSONObject7.put("expiry", this.j.a);
        jSONObject7.put("maxRetries", this.j.b);
        jSONObject7.put("retryInterval", this.j.c);
        jSONObject7.put("url", this.j.d);
        jSONObjectB.put("mraid", jSONObject7);
        JSONObject jSONObject8 = new JSONObject();
        jSONObject8.put("impressionMinPercentageViewed", this.k.a);
        jSONObject8.put("impressionMinTimeViewed", this.k.b);
        jSONObject8.put("displayMinPercentageAnimate", this.k.e);
        jSONObject8.put("visibilityThrottleMillis", this.k.c);
        jSONObject8.put("impressionPollIntervalMillis", this.k.d);
        jSONObject8.put("moatEnabled", this.k.i);
        jSONObject8.put("iasEnabled", this.k.j);
        JSONObject jSONObject9 = new JSONObject();
        jSONObject9.put("impressionMinPercentageViewed", this.k.f);
        jSONObject9.put("impressionMinTimeViewed", this.k.g);
        jSONObject9.put("videoMinPercentagePlay", this.k.h);
        jSONObject8.put("video", jSONObject9);
        jSONObjectB.put("viewability", jSONObject8);
        jSONObjectB.put("preload", f());
        JSONObject jSONObject10 = new JSONObject();
        jSONObject10.put("maxWrapperLimit", this.m.a);
        jSONObject10.put("optimalVastVideoSize", this.m.b);
        jSONObject10.put("vastMaxAssetSize", this.m.c);
        jSONObject10.put("allowedContentType", new JSONArray((Collection) this.m.e));
        C0072c c0072c = this.m.d;
        JSONObject jSONObject11 = new JSONObject();
        jSONObject11.put("headerTimeout", c0072c.b);
        jSONObject11.put("bitrate_mandatory", c0072c.a);
        jSONObject10.put("bitRate", jSONObject11);
        jSONObjectB.put("vastVideo", jSONObject10);
        JSONObject jSONObject12 = new JSONObject();
        jSONObject12.put("retryInterval", this.n.b);
        jSONObject12.put("maxRetries", this.n.a);
        jSONObject12.put("maxCachedAssets", this.n.c);
        jSONObject12.put("maxCacheSize", this.n.d);
        jSONObject12.put("timeToLive", this.n.e);
        jSONObjectB.put("assetCache", jSONObject12);
        Object obj = this.l;
        if (obj != null) {
            jSONObjectB.put("telemetry", obj);
        }
        return jSONObjectB;
    }

    private JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.A.a);
        jSONObject2.put("placementExpiry", this.A.b);
        jSONObject2.put("maxPreloadedAds", this.A.c);
        jSONObject.put("base", jSONObject2);
        for (Map.Entry<String, g> entry : this.z.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            g value = entry.getValue();
            jSONObject3.put("enabled", value.a);
            jSONObject3.put("placementExpiry", value.b);
            jSONObject3.put("maxPreloadedAds", value.c);
            jSONObject.put(entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private JSONObject g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("enabled", this.f.h);
        jSONObject2.put("maxRetryCount", this.f.a);
        jSONObject2.put("eventTTL", this.f.b);
        jSONObject2.put("maxEventsToPersist", this.f.c);
        jSONObject2.put("processingInterval", this.f.d);
        jSONObject2.put("txLatency", this.f.e);
        jSONObject2.put("networkType", a(this.f));
        jSONObject.put("baseDict", jSONObject2);
        for (Map.Entry<String, a> entry : this.g.entrySet()) {
            JSONObject jSONObject3 = new JSONObject();
            a value = entry.getValue();
            jSONObject3.put("enabled", value.h);
            jSONObject3.put("maxRetryCount", value.a);
            jSONObject3.put("eventTTL", value.b);
            jSONObject3.put("maxEventsToPersist", value.c);
            jSONObject3.put("processingInterval", value.d);
            jSONObject3.put("txLatency", value.e);
            jSONObject3.put("networkType", a(value));
            jSONObject.put(entry.getKey(), jSONObject3);
        }
        return jSONObject;
    }

    private static JSONObject a(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        i iVar = aVar.g;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", iVar.a);
        jSONObject2.put("maxBatchSize", iVar.b);
        jSONObject.put("wifi", jSONObject2);
        i iVar2 = aVar.f;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", iVar2.a);
        jSONObject3.put("maxBatchSize", iVar2.b);
        jSONObject.put("others", jSONObject3);
        return jSONObject;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final boolean c() {
        d dVar;
        g gVar;
        if ((this.a.startsWith("http://") || this.a.startsWith("https://")) && ((this.b.startsWith("http://") || this.b.startsWith("https://")) && this.c >= 0 && this.d >= 0 && this.e > 0 && (dVar = this.x) != null && dVar.a())) {
            Iterator<Map.Entry<String, d>> it = this.y.entrySet().iterator();
            while (it.hasNext()) {
                if (!it.next().getValue().a()) {
                    return false;
                }
            }
            a aVar = this.f;
            if (aVar != null && aVar.a()) {
                Iterator<Map.Entry<String, a>> it2 = this.g.entrySet().iterator();
                while (it2.hasNext()) {
                    if (!it2.next().getValue().a()) {
                        return false;
                    }
                }
                if (this.h.d >= 0 && this.h.e >= 0 && this.h.a >= 0 && this.h.b >= 0 && this.h.c > 0 && this.h.f > 0 && this.j.a >= 0 && this.j.c >= 0 && this.j.b >= 0 && ((this.j.d.startsWith("http://") || this.j.d.startsWith("https://")) && this.i.a >= 0 && this.i.c >= 0 && this.i.b >= 0 && this.i.d >= 0 && this.i.h >= 0 && this.i.i >= 0 && this.i.j >= 0 && this.i.e != null && this.i.e.trim().length() != 0)) {
                    try {
                        this.i.f = Color.parseColor(this.i.e);
                        if (this.j.b >= 0 && this.j.c >= 0 && this.j.d != null && this.j.d.trim().length() != 0 && this.k.a > 0 && this.k.a <= 100 && this.k.b >= 0 && this.k.e > 0 && this.k.e <= 100 && this.k.f > 0 && this.k.f <= 100 && this.k.g >= 0 && this.k.h > 0 && this.k.h <= 100 && this.k.c >= 50 && this.k.c * 5 <= this.k.b && this.k.d >= 50 && this.k.d * 4 <= this.k.b && (gVar = this.A) != null && gVar.a()) {
                            Iterator<Map.Entry<String, g>> it3 = this.z.entrySet().iterator();
                            while (it3.hasNext()) {
                                if (!it3.next().getValue().a()) {
                                    return false;
                                }
                            }
                            if (this.m.b <= 31457280 && this.m.b > 0 && this.m.a >= 0 && this.m.c > 0 && this.m.c <= 31457280 && this.n.b >= 0 && this.n.c <= 20 && this.n.c >= 0 && this.n.e >= 0 && this.n.d >= 0 && this.n.a >= 0) {
                                return true;
                            }
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
        }
        return false;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final com.inmobi.commons.core.configs.a d() {
        return new c();
    }

    public final d a(String str) {
        d dVar = this.y.get(str);
        return dVar == null ? this.x : dVar;
    }

    @NonNull
    public final a b(String str) {
        a aVar = this.g.get(str + "Dict");
        return aVar == null ? this.f : aVar;
    }

    public final g c(String str) {
        g gVar = this.z.get(str);
        return gVar == null ? this.A : gVar;
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class a {
        public int a;
        public long b;
        public int c;
        public long d;
        public long e;
        public i f;
        public i g;
        public boolean h;

        public final boolean a() {
            int i;
            int i2;
            long j = this.e;
            long j2 = this.d;
            if (j >= j2) {
                long j3 = this.b;
                if (j <= j3 && j3 >= j2 && this.f.a() && this.g.a() && (i = this.a) >= 0 && i <= 3) {
                    long j4 = this.b;
                    if (j4 > 0 && j4 <= 86400 && (i2 = this.c) > 0 && i2 <= 1000) {
                        long j5 = this.e;
                        if (j5 > 0 && j5 <= 180) {
                            long j6 = this.d;
                            if (j6 >= 0 && j6 <= 60) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class i {
        public long a;
        public int b;

        public final boolean a() {
            int i;
            long j = this.a;
            return j > 0 && j <= 60 && (i = this.b) > 0 && i <= 97;
        }
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class d {
        int a = 1;
        int b;
        int c;
        long d;
        public boolean e;

        public final boolean a() {
            return this.b > 0 && this.a >= 0 && this.c >= 0 && this.d >= 0;
        }
    }

    /* JADX INFO: compiled from: AdConfig.java */
    public static final class g {
        public boolean a = false;
        public long b = 259200;
        public int c = 5;

        public final boolean a() {
            return this.b >= 0 && this.c > 0;
        }
    }
}
