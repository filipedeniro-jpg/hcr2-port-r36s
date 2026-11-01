package com.adcolony.sdk;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ADCCrashReportManager {
    static boolean a = false;
    static final int b = 256;
    private static final String d = "fatalLog.txt";
    private static final String e = "ad_cache_report.txt";
    private static final String f = "com.adcolony.sdk";
    private static final String m = "com.adcolony.crashreports";
    private static final String n = "current.crash";
    private boolean c = false;
    private String g;
    private String h;
    private Thread.UncaughtExceptionHandler i;
    private List<s> j;
    private JSONArray k;
    private JSONObject l;

    public native void initNativeCrashReporter(byte[] bArr);

    ADCCrashReportManager() {
    }

    synchronized void a() {
        if (!this.c) {
            new aa.a().a("Configuring Crash Reporter").a(aa.d);
            if (a) {
                this.i = Thread.getDefaultUncaughtExceptionHandler();
                a aVar = new a();
                new aa.a().a("adding exception handler.").a(aa.b);
                Thread.setDefaultUncaughtExceptionHandler(aVar);
                try {
                    this.h = k();
                    initNativeCrashReporter(this.h.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    new aa.a().a(e2.getMessage()).a(aa.h);
                    this.c = false;
                }
            }
            this.g = com.adcolony.sdk.a.a().o().e() + d;
            this.j = new ArrayList();
            this.k = y.b();
            d();
            this.c = true;
        }
    }

    synchronized void b() {
        if (a) {
            c();
            j();
            i();
        }
    }

    synchronized void c() {
        try {
            boolean zA = com.adcolony.sdk.a.a().j().a(this.g);
            boolean zA2 = com.adcolony.sdk.a.a().j().a(this.h);
            if (zA) {
                StringBuilder sbA = com.adcolony.sdk.a.a().j().a(this.g, false);
                JSONArray jSONArrayG = y.g(y.a(sbA.toString()), "crashList");
                for (int i = 0; i < jSONArrayG.length(); i++) {
                    JSONObject jSONObject = jSONArrayG.getJSONObject(i);
                    new aa.a().a("Log read from disk: ").a(jSONObject.toString()).a(aa.b);
                    this.j.add(new s().a(jSONObject));
                }
                new aa.a().a("Contents of crash Reporting file: ").a(sbA.toString()).a(aa.b);
            } else {
                new aa.a().a("Java Crash log doesn't exist.").a(aa.b);
            }
            if (zA2) {
                this.j.add(new s().a(a(com.adcolony.sdk.a.a().j().b(this.h, true))));
            } else {
                new aa.a().a("Native Crash log doesn't exist.").a(aa.b);
            }
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving logs. Exception Msg: ").a(e2.getMessage()).a(aa.h);
        }
    }

    synchronized void d() {
        this.l = y.a();
        try {
            String str = com.adcolony.sdk.a.a().o().e() + e;
            if (com.adcolony.sdk.a.a().j().a(str)) {
                this.l = y.c(str);
            }
        } catch (Exception e2) {
            new aa.a().a("Exception occurred when retrieving ad-cache log. Exception Msg: ").a(e2.getMessage()).a(aa.h);
        }
    }

    JSONArray e() {
        return this.k;
    }

    List<s> f() {
        return this.j;
    }

    synchronized void a(Throwable th) {
        String message;
        StackTraceElement stackTraceElementA;
        new aa.a().a("Writing crash log...").a(aa.b);
        if (th == null) {
            return;
        }
        JSONArray jSONArrayB = y.b();
        StackTraceElement stackTraceElementA2 = a(th.getStackTrace(), jSONArrayB);
        if (stackTraceElementA2 == null) {
            Throwable cause = th.getCause();
            message = null;
            if (cause == null || (stackTraceElementA = a(cause.getStackTrace(), (jSONArrayB = y.b()))) == null) {
                stackTraceElementA2 = null;
            } else {
                message = cause.getMessage();
                stackTraceElementA2 = stackTraceElementA;
            }
        } else {
            message = th.getMessage();
        }
        if (stackTraceElementA2 != null && message != null) {
            String className = stackTraceElementA2.getClassName();
            String methodName = stackTraceElementA2.getMethodName();
            int lineNumber = stackTraceElementA2.getLineNumber();
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "timestamp", Long.toString(System.currentTimeMillis()));
            y.a(jSONObjectA, "message", message);
            y.a(jSONObjectA, "sourceFile", className);
            y.b(jSONObjectA, "lineNumber", lineNumber);
            y.a(jSONObjectA, "methodName", methodName);
            y.a(jSONObjectA, "stackTrace", jSONArrayB);
            d(jSONObjectA);
            new aa.a().a("saving to disk...").a(aa.b);
            c(jSONObjectA);
            h();
        }
        new aa.a().a("..printing stacktrace").a(aa.b);
        th.printStackTrace();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00ca A[Catch: all -> 0x010a, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0013, B:14:0x003b, B:16:0x0060, B:18:0x008b, B:22:0x00c4, B:24:0x00ca, B:21:0x00ac, B:13:0x0037, B:10:0x002e, B:15:0x004c), top: B:32:0x0001, inners: #0 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:24:0x00ca, please report this as an issue */
    synchronized void g() {
        if (a) {
            JSONObject jSONObjectA = y.a();
            c cVarS = com.adcolony.sdk.a.a().s();
            if (cVarS != null) {
                AdColonyInterstitial adColonyInterstitial = com.adcolony.sdk.a.a().m().c().get(cVarS.b());
                String strB = adColonyInterstitial == null ? "" : adColonyInterstitial.b();
                String strC = adColonyInterstitial == null ? "" : adColonyInterstitial.c();
                y.a(jSONObjectA, "isAdActive", true);
                y.a(jSONObjectA, "activeAdId", strB);
                y.a(jSONObjectA, "active_creative_ad_id", strC);
            } else {
                y.a(jSONObjectA, "isAdActive", false);
                y.a(jSONObjectA, "activeAdId", "");
                y.a(jSONObjectA, "active_creative_ad_id", "");
            }
            try {
                String str = com.adcolony.sdk.a.a().o().e() + "422de421e0f4e019426b9abfd780746bc40740eb";
                if (com.adcolony.sdk.a.a().j().a(str)) {
                    JSONObject jSONObjectC = y.c(str);
                    JSONArray jSONArrayA = a(jSONObjectC);
                    JSONArray jSONArrayB = b(jSONObjectC);
                    y.b(jSONObjectA, "adCacheSize", jSONArrayA.length());
                    y.a(jSONObjectA, "listOfCachedAds", jSONArrayA);
                    y.a(jSONObjectA, "listOfCreativeAdIds", jSONArrayB);
                    if (e(jSONObjectA)) {
                        y.h(this.l, com.adcolony.sdk.a.a().o().e() + e);
                        new aa.a().a("CrashReport AdCache=").a(this.l.toString()).a(aa.b);
                        this.l = jSONObjectA;
                    }
                } else if (e(jSONObjectA)) {
                    y.h(this.l, com.adcolony.sdk.a.a().o().e() + e);
                    new aa.a().a("CrashReport AdCache=").a(this.l.toString()).a(aa.b);
                    this.l = jSONObjectA;
                }
            } catch (Exception e2) {
                new aa.a().a("Exception occurred in FileSystem: ").a(e2.toString()).a(aa.f);
            }
        }
    }

    JSONArray a(JSONObject jSONObject) {
        JSONArray jSONArrayB = y.b();
        JSONArray jSONArrayG = y.g(y.f(jSONObject, "app"), "zones");
        for (int i = 0; i < jSONArrayG.length(); i++) {
            JSONArray jSONArrayG2 = y.g(y.d(jSONArrayG, i), "ads");
            for (int i2 = 0; i2 < jSONArrayG2.length(); i2++) {
                JSONObject jSONObjectF = y.f(y.d(jSONArrayG2, i2), "legacy");
                JSONObject jSONObjectF2 = y.f(y.d(jSONArrayG2, i2), "aurora");
                if (jSONObjectF.has("uuid")) {
                    y.a(jSONArrayB, y.b(jSONObjectF, "uuid"));
                } else {
                    y.a(jSONArrayB, y.b(jSONObjectF2, "uuid"));
                }
            }
        }
        return jSONArrayB;
    }

    JSONArray b(JSONObject jSONObject) {
        JSONArray jSONArrayB = y.b();
        JSONArray jSONArrayG = y.g(y.f(jSONObject, "app"), "zones");
        for (int i = 0; i < jSONArrayG.length(); i++) {
            JSONArray jSONArrayG2 = y.g(y.d(jSONArrayG, i), "ads");
            for (int i2 = 0; i2 < jSONArrayG2.length(); i2++) {
                JSONObject jSONObjectF = y.f(y.d(jSONArrayG2, i2), "legacy");
                y.f(y.d(jSONArrayG2, i2), "aurora");
                JSONObject jSONObjectF2 = y.f(jSONObjectF, "meta");
                JSONObject jSONObjectF3 = y.f(jSONObjectF, "meta");
                if (jSONObjectF2.has("creative_id")) {
                    y.a(jSONArrayB, y.b(jSONObjectF2, "creative_id"));
                } else {
                    y.a(jSONArrayB, y.b(jSONObjectF3, "creative_id"));
                }
            }
        }
        return jSONArrayB;
    }

    synchronized void c(JSONObject jSONObject) {
        if (this.k == null) {
            this.k = y.b();
        } else if (this.k.length() == 256) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 1; i < this.k.length(); i++) {
                jSONArray.put(y.b(this.k, i));
            }
            this.k = jSONArray;
        }
        this.k.put(jSONObject);
    }

    private void h() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "crashList", this.k);
        new aa.a().a("saving object to ").a(this.g).a(aa.b);
        y.h(jSONObjectA, this.g);
    }

    private void d(JSONObject jSONObject) {
        JSONObject jSONObject2 = this.l;
        if (jSONObject2 != null) {
            String strB = y.b(jSONObject2, "activeAdId");
            boolean zD = y.d(this.l, "isAdActive");
            int iC = y.c(this.l, "adCacheSize");
            JSONArray jSONArrayG = y.g(this.l, "listOfCachedAds");
            String strB2 = y.b(this.l, "active_creative_ad_id");
            JSONArray jSONArrayG2 = y.g(this.l, "listOfCreativeAdIds");
            y.a(jSONObject, "isAdActive", zD);
            y.a(jSONObject, "activeAdId", strB);
            y.b(jSONObject, "adCacheSize", iC);
            y.a(jSONObject, "listOfCachedAds", jSONArrayG);
            y.a(jSONObject, "active_creative_ad_id", strB2);
            y.a(jSONObject, "listOfCreativeAdIds", jSONArrayG2);
        }
    }

    private void i() {
        this.j = new ArrayList();
        this.k = y.b();
        try {
            com.adcolony.sdk.a.a().j().a(new File(this.g));
            com.adcolony.sdk.a.a().j().a(new File(this.h));
        } catch (Exception unused) {
            new aa.a().a("Unable to delete log file.").a(aa.f);
        }
    }

    private void j() {
        for (int i = 0; i < this.j.size(); i++) {
            s sVar = this.j.get(i);
            new aa.a().a("Writing a crash log to adc-instruments").a(aa.b);
            ac.a(sVar);
        }
    }

    private synchronized JSONObject a(List<String> list) {
        JSONObject jSONObjectA;
        String strTrim;
        try {
            jSONObjectA = y.a();
            JSONArray jSONArrayB = y.b();
            JSONArray jSONArrayB2 = y.b();
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                int iIndexOf = str.indexOf(58);
                String strSubstring = null;
                if (iIndexOf < 0 || iIndexOf >= str.length()) {
                    strTrim = null;
                } else {
                    strSubstring = str.substring(0, iIndexOf);
                    strTrim = str.substring(iIndexOf + 1).trim();
                }
                if (strSubstring != null && strSubstring.equals("signalMessage")) {
                    y.a(jSONObjectA, "message", strTrim);
                } else if (strSubstring != null && strSubstring.equals("date")) {
                    y.a(jSONObjectA, "timestamp", strTrim);
                } else if (strSubstring != null && strSubstring.equals("threadState")) {
                    z2 = true;
                } else if (strSubstring != null && strSubstring.equals("backtrace")) {
                    z = true;
                    z2 = false;
                } else if (z2) {
                    y.a(jSONArrayB, str);
                } else if (z) {
                    y.a(jSONArrayB2, str);
                } else if (strSubstring != null) {
                    y.a(jSONObjectA, strSubstring, strTrim);
                }
            }
            y.a(jSONObjectA, "threadState", jSONArrayB);
            y.a(jSONObjectA, "stackTrace", jSONArrayB2);
            d(jSONObjectA);
        } catch (Exception unused) {
            new aa.a().a("Error occurred while parsing native crash report.").a(aa.h);
            JSONObject jSONObjectA2 = y.a();
            long jCurrentTimeMillis = System.currentTimeMillis();
            y.a(jSONObjectA2, "message", "An error occurred while parsing the native crash report.");
            y.a(jSONObjectA2, "timestamp", Long.toString(jCurrentTimeMillis));
            return jSONObjectA2;
        }
        return jSONObjectA;
    }

    private String k() {
        return com.adcolony.sdk.a.a().o().e() + m + "." + n;
    }

    private boolean e(JSONObject jSONObject) {
        if (this.l.has("isAdActive") && this.l.has("activeAdId") && this.l.has("adCacheSize") && this.l.has("listOfCachedAds")) {
            return (y.d(this.l, "isAdActive") != y.d(jSONObject, "isAdActive")) || (y.b(this.l, "activeAdId").equals(y.b(jSONObject, "activeAdId")) ^ true) || (y.c(this.l, "adCacheSize") != y.c(jSONObject, "adCacheSize")) || (y.g(this.l, "listOfCachedAds").equals(y.g(jSONObject, "listOfCachedAds")) ^ true);
        }
        return true;
    }

    private StackTraceElement a(StackTraceElement[] stackTraceElementArr, JSONArray jSONArray) {
        StackTraceElement stackTraceElement = null;
        for (StackTraceElement stackTraceElement2 : stackTraceElementArr) {
            jSONArray.put(stackTraceElement2.toString());
            String className = stackTraceElement2.getClassName();
            new aa.a().a("CRASH - classname=").a(className).a(aa.b);
            if (className.contains(f) && stackTraceElement == null) {
                stackTraceElement = stackTraceElement2;
            }
        }
        return stackTraceElement;
    }

    private class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            new aa.a().a("Caught exception.").a(aa.b);
            ADCCrashReportManager.this.a(th);
            ADCCrashReportManager.this.i.uncaughtException(thread, th);
        }
    }
}
