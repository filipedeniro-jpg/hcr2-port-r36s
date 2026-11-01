package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Model.e;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a implements b {
    private static a d;
    private static final Long g = Long.valueOf(TimeUnit.MINUTES.toMillis(5));
    private final AtomicReference<e> e;
    private boolean f = false;
    private long h = System.currentTimeMillis() - g.longValue();

    public a(AtomicReference<e> atomicReference) {
        d = this;
        this.e = atomicReference;
    }

    public void a() {
        a("start");
        a("did-become-active");
    }

    private void a(String str) {
        if (this.e.get().n) {
            a(SettingsJsonConstants.SESSION_KEY, str, null, null, null, false);
        }
    }

    public void b() {
        c();
    }

    private void c() {
        if (this.e.get().n) {
            a(SettingsJsonConstants.SESSION_KEY, TtmlNode.END, null, null, null, null, null, false);
            a("did-become-active");
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (this.e.get().o) {
            a("webview-track", str, str2, str3, str4, null, null, false);
        }
    }

    public void a(JSONObject jSONObject) {
        e eVar = this.e.get();
        if (eVar.o) {
            a("folder", com.chartboost.sdk.b.a(eVar), null, null, null, null, jSONObject, false);
        }
    }

    public void a(String str, String str2, String str3) {
        if (this.e.get().o) {
            a("load", str, str2, str3, null, false);
        }
    }

    public void b(String str, String str2, String str3) {
        if (this.e.get().o) {
            a("ad-show", str, str2, str3, null, false);
        }
    }

    public void c(String str, String str2, String str3) {
        if (this.e.get().o) {
            a("ad-click", str, str2, str3, null, false);
        }
    }

    public void d(String str, String str2, String str3) {
        if (this.e.get().o) {
            a("ad-close", str, str2, str3, null, false);
        }
    }

    public void e(String str, String str2, String str3) {
        if (this.e.get().o) {
            a("ad-dismiss", str, str2, str3, null, false);
        }
    }

    public void a(String str, String str2, String str3, String str4, boolean z) {
        if (this.e.get().o) {
            if (TextUtils.isEmpty(str3)) {
                str3 = "empty-adid";
            }
            a("ad-error", str, str2, str3, str4, z);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (this.e.get().l) {
            a("ad-unit-error", str, str2, str3, str4, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("adId", str5), com.chartboost.sdk.Libraries.e.a("location", str6), com.chartboost.sdk.Libraries.e.a("state", str7)), true);
        }
    }

    public void b(String str, String str2, String str3, String str4) {
        if (this.e.get().o) {
            if (TextUtils.isEmpty(str3)) {
                str3 = "empty-adid";
            }
            a("ad-warning", str, str2, str3, str4, false);
        }
    }

    public void a(String str, String str2) {
        e eVar = this.e.get();
        if (eVar.o) {
            a("download-asset-start", com.chartboost.sdk.b.a(eVar), str, str2, null, null, null, false);
        }
    }

    public void a(String str, String str2, long j, long j2, long j3) {
        if (this.e.get().p) {
            a("download-asset-failure", str, str2, null, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("processingMs", Long.valueOf(j)), com.chartboost.sdk.Libraries.e.a("getResponseCodeMs", Long.valueOf(j2)), com.chartboost.sdk.Libraries.e.a("readDataMs", Long.valueOf(j3))), false);
        }
    }

    public void a(String str, long j, long j2, long j3) {
        if (this.e.get().p) {
            a("download-asset-success", str, null, null, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("processingMs", Long.valueOf(j)), com.chartboost.sdk.Libraries.e.a("getResposeCodeMs", Long.valueOf(j2)), com.chartboost.sdk.Libraries.e.a("readDataMs", Long.valueOf(j3))), false);
        }
    }

    public void b(String str, String str2) {
        if (this.e.get().o) {
            a("playback-complete", str, str2, null, null, false);
        }
    }

    public void c(String str, String str2) {
        if (this.e.get().o) {
            a("replay", str, str2, null, null, false);
        }
    }

    public void d(String str, String str2) {
        if (this.e.get().o) {
            a("playback-start", str, str2, null, null, false);
        }
    }

    public void e(String str, String str2) {
        if (this.e.get().o) {
            a("playback-stop", str, str2, null, null, false);
        }
    }

    public static void a(Class cls, String str, Exception exc) {
        exc.printStackTrace();
        a aVar = d;
        if (aVar != null) {
            aVar.b(cls, str, exc);
        }
    }

    private synchronized void b(Class cls, String str, Exception exc) {
        if (this.e == null) {
            return;
        }
        e eVar = this.e.get();
        if (eVar != null && eVar.k && !this.f) {
            this.f = true;
            try {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - this.h >= g.longValue()) {
                        a("exception", cls.getName(), str, exc.getClass().getName(), exc.getMessage(), eVar.r ? Log.getStackTraceString(exc) : null, null, true);
                        this.h = jCurrentTimeMillis;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.f = false;
            } catch (Throwable th) {
                this.f = false;
                throw th;
            }
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        if (this.e.get().o) {
            a(str, str2, str3, str4, str5, str6, jSONObject, false);
        }
    }

    private void a(String str, String str2, String str3, String str4, String str5, boolean z) {
        a(str, str2, str3, str4, str5, null, new JSONObject(), z);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, boolean z) {
        if (this.e.get().o) {
            if (str == null) {
                str = "unknown event";
            }
            CBLogging.a("CBTrack", str);
        }
    }
}
