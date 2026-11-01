package com.chartboost.sdk.impl;

import android.content.SharedPreferences;
import android.os.Handler;
import com.applovin.mediation.AppLovinNativeAdapter;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.c.RunnableC0032c;
import com.chartboost.sdk.impl.c.a;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    final ScheduledExecutorService a;
    public final com.chartboost.sdk.Libraries.f b;
    final com.chartboost.sdk.Libraries.i c;
    final Handler d;
    final com.chartboost.sdk.c e;
    final c f;
    ScheduledFuture<?> k;
    private final l l;
    private final ah m;
    private final ai n;
    private final ap o;
    private final AtomicReference<com.chartboost.sdk.Model.e> p;
    private final SharedPreferences q;
    private final com.chartboost.sdk.Tracking.a r;
    private final ak s;
    private final com.chartboost.sdk.d t;
    private final al u;
    int g = 0;
    private final long z = TimeUnit.SECONDS.toNanos(5);
    private final long A = TimeUnit.SECONDS.toNanos(1);
    private final String[] B = {"ASKED_TO_CACHE", "ASKED_TO_SHOW", "REQUESTING_TO_CACHE", "REQUESTING_TO_SHOW", "DOWNLOADING_TO_CACHE", "DOWNLOADING_TO_SHOW", "READY", "ASKING_UI_TO_SHOW_AD", "DONE"};
    private int v = 1;
    final Map<String, f> h = new HashMap();
    final SortedSet<f> j = new TreeSet();
    final SortedSet<f> i = new TreeSet();
    private final Map<String, Long> x = new HashMap();
    private final Map<String, Integer> y = new HashMap();
    private boolean w = false;

    public e(c cVar, ScheduledExecutorService scheduledExecutorService, l lVar, com.chartboost.sdk.Libraries.f fVar, ah ahVar, ai aiVar, ap apVar, AtomicReference<com.chartboost.sdk.Model.e> atomicReference, SharedPreferences sharedPreferences, com.chartboost.sdk.Libraries.i iVar, com.chartboost.sdk.Tracking.a aVar, Handler handler, com.chartboost.sdk.c cVar2, ak akVar, com.chartboost.sdk.d dVar, al alVar) {
        this.a = scheduledExecutorService;
        this.l = lVar;
        this.b = fVar;
        this.m = ahVar;
        this.n = aiVar;
        this.o = apVar;
        this.p = atomicReference;
        this.q = sharedPreferences;
        this.c = iVar;
        this.r = aVar;
        this.d = handler;
        this.e = cVar2;
        this.s = akVar;
        this.t = dVar;
        this.u = alVar;
        this.f = cVar;
    }

    void a() {
        if (this.g == 0) {
            this.g = 1;
            b();
        }
    }

    void b() {
        if (this.w) {
            return;
        }
        try {
            this.w = true;
            d();
            if (this.g == 1 && !a(this.j, 1, 3, 1, "show")) {
                a(this.i, 0, 2, 2, "cache");
            }
            c();
        } finally {
            this.w = false;
        }
    }

    private void c() {
        Long lValueOf;
        if (this.g == 1) {
            long jB = this.c.b();
            lValueOf = null;
            for (Map.Entry<String, Long> entry : this.x.entrySet()) {
                if (this.h.get(entry.getKey()) != null) {
                    long jMax = Math.max(this.z, entry.getValue().longValue() - jB);
                    if (lValueOf == null || jMax < lValueOf.longValue()) {
                        lValueOf = Long.valueOf(jMax);
                    }
                }
            }
        } else {
            lValueOf = null;
        }
        if (lValueOf != null && this.k != null) {
            if (Math.abs(lValueOf.longValue() - this.k.getDelay(TimeUnit.NANOSECONDS)) <= TimeUnit.SECONDS.toNanos(5L)) {
                return;
            }
        }
        ScheduledFuture<?> scheduledFuture = this.k;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.k = null;
        }
        if (lValueOf != null) {
            this.k = this.a.schedule(new a(2, null, null, null), lValueOf.longValue(), TimeUnit.NANOSECONDS);
        }
    }

    private boolean a(SortedSet<f> sortedSet, int i, int i2, int i3, String str) {
        Iterator<f> it = sortedSet.iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (next.c != i || next.d != null) {
                it.remove();
            } else if (e(next.b)) {
                continue;
            } else if (!this.f.g(next.b)) {
                next.c = 8;
                this.h.remove(next.b);
                it.remove();
            } else {
                next.c = i2;
                it.remove();
                a(next, i3, str);
                return true;
            }
        }
        return false;
    }

    public synchronized com.chartboost.sdk.Model.a a(String str) {
        f fVar = this.h.get(str);
        if (fVar == null || !(fVar.c == 6 || fVar.c == 7)) {
            return null;
        }
        return fVar.d;
    }

    void b(String str) {
        if (e()) {
            c cVar = this.f;
            cVar.getClass();
            this.d.postDelayed(cVar.new a(4, str, CBError.CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.A);
            return;
        }
        f fVar = this.h.get(str);
        if (fVar != null && fVar.c == 6 && !a(fVar.d)) {
            this.h.remove(str);
            fVar = null;
        }
        if (fVar == null) {
            int i = this.v;
            this.v = i + 1;
            fVar = new f(i, str, 0);
            this.h.put(str, fVar);
            this.i.add(fVar);
        }
        fVar.f = true;
        if (fVar.h == null) {
            fVar.h = Long.valueOf(this.c.b());
        }
        switch (fVar.c) {
            case 6:
            case 7:
                Handler handler = this.d;
                c cVar2 = this.f;
                cVar2.getClass();
                handler.post(cVar2.new a(0, str, null));
                break;
        }
        b();
    }

    private boolean a(com.chartboost.sdk.Model.a aVar) {
        File file = this.b.d().a;
        for (com.chartboost.sdk.Model.b bVar : aVar.c.values()) {
            if (!bVar.a(file).exists()) {
                CBLogging.b("AdUnitManager", "Asset does not exist: " + bVar.b);
                return false;
            }
        }
        return true;
    }

    private void d() {
        long jB = this.c.b();
        Iterator<Long> it = this.x.values().iterator();
        while (it.hasNext()) {
            if (jB - it.next().longValue() >= 0) {
                it.remove();
            }
        }
    }

    private boolean e(String str) {
        return this.x.containsKey(str);
    }

    private void a(final f fVar, int i, String str) {
        aj ajVar;
        try {
            com.chartboost.sdk.Model.e eVar = this.p.get();
            boolean z = this.f.a == 2;
            boolean z2 = eVar.y && !z;
            final long jB = this.c.b();
            final boolean z3 = z;
            final boolean z4 = z2;
            aj.a aVar = new aj.a() { // from class: com.chartboost.sdk.impl.e.1
                @Override // com.chartboost.sdk.impl.aj.a
                public void a(aj ajVar2, JSONObject jSONObject) {
                    com.chartboost.sdk.Model.a aVar2;
                    try {
                        fVar.p = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(e.this.c.b() - jB));
                        fVar.q = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(ajVar2.h));
                        fVar.r = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(ajVar2.i));
                        if (z3) {
                            aVar2 = new com.chartboost.sdk.Model.a(0, jSONObject, true);
                        } else if (z4) {
                            aVar2 = new com.chartboost.sdk.Model.a(1, jSONObject, false);
                        } else {
                            aVar2 = new com.chartboost.sdk.Model.a(0, jSONObject, false);
                        }
                        e.this.a(fVar, aVar2);
                    } catch (JSONException e) {
                        com.chartboost.sdk.Tracking.a.a(e.class, "sendAdGetRequest.onSuccess", e);
                        e.this.a(fVar, new CBError(CBError.a.UNEXPECTED_RESPONSE, "Response conversion failure"));
                    }
                }

                @Override // com.chartboost.sdk.impl.aj.a
                public void a(aj ajVar2, CBError cBError) {
                    e.this.a(fVar, cBError);
                }
            };
            boolean z5 = fVar.c == 2;
            if (z) {
                aj ajVar2 = new aj(this.f.d, this.o, this.r, i, aVar);
                ajVar2.l = true;
                ajVar2.a("location", fVar.b);
                ajVar2.a("cache", Boolean.valueOf(z5));
                ajVar2.a("raw", (Object) true);
                fVar.e = 0;
                ajVar = ajVar2;
            } else if (z2) {
                am amVar = new am(String.format(this.f.e, eVar.F), this.o, this.r, i, aVar);
                amVar.a("cache_assets", this.b.c(), 0);
                amVar.a("location", fVar.b, 0);
                amVar.a("cache", Boolean.valueOf(z5), 0);
                amVar.l = true;
                fVar.e = 1;
                ajVar = amVar;
            } else {
                aj ajVar3 = new aj(this.f.d, this.o, this.r, i, aVar);
                ajVar3.a("local-videos", this.b.b());
                ajVar3.l = true;
                ajVar3.a("location", fVar.b);
                ajVar3.a("cache", Boolean.valueOf(z5));
                fVar.e = 0;
                ajVar = ajVar3;
            }
            ajVar.j = 1;
            this.g = 2;
            this.m.a(ajVar);
            this.r.a(this.f.a(fVar.e.intValue()), str, fVar.b);
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "sendAdGetRequest", e);
            a(fVar, new CBError(CBError.a.MISCELLANEOUS, "error sending ad-get request"));
        }
    }

    synchronized void a(f fVar, com.chartboost.sdk.Model.a aVar) {
        this.g = 1;
        fVar.c = fVar.c == 2 ? 4 : 5;
        fVar.d = aVar;
        c(fVar);
        b();
    }

    private void c(final f fVar) {
        if (fVar.d != null) {
            if (fVar.c == 5 || fVar.c == 4) {
                int i = fVar.c == 5 ? 1 : 2;
                if (fVar.g <= i) {
                    return;
                }
                h hVar = new h() { // from class: com.chartboost.sdk.impl.e.2
                    @Override // com.chartboost.sdk.impl.h
                    public void a(boolean z, int i2, int i3) {
                        e.this.a(fVar, z, i2, i3);
                    }
                };
                fVar.g = i;
                this.l.a(i, fVar.d.c, new AtomicInteger(), (h) com.chartboost.sdk.g.a().a(hVar));
            }
        }
    }

    synchronized void a(f fVar, boolean z, int i, int i2) {
        if (fVar.c == 4 || fVar.c == 5) {
            fVar.n = Integer.valueOf(i);
            fVar.o = Integer.valueOf(i2);
            if (z) {
                d(fVar);
            } else {
                e(fVar);
            }
        }
        b();
    }

    private void d(f fVar) {
        int i = fVar.c;
        long jB = this.c.b();
        if (fVar.h != null) {
            fVar.k = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(jB - fVar.h.longValue()));
        }
        if (fVar.i != null) {
            fVar.l = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(jB - fVar.i.longValue()));
        }
        b(fVar, "ad-unit-cached");
        fVar.c = 6;
        if (fVar.f) {
            Handler handler = this.d;
            c cVar = this.f;
            cVar.getClass();
            handler.post(cVar.new a(0, fVar.b, null));
        }
        if (i == 5) {
            h(fVar);
        }
    }

    private void e(f fVar) {
        b(fVar, CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        f(fVar);
        g(fVar);
    }

    private void f(f fVar) {
        this.h.remove(fVar.b);
        fVar.c = 8;
        fVar.d = null;
    }

    synchronized void a(f fVar, CBError cBError) {
        if (this.g == 0) {
            return;
        }
        this.g = 1;
        b(fVar, cBError.c());
        f(fVar);
        g(fVar);
        b();
    }

    private void g(f fVar) {
        com.chartboost.sdk.Model.e eVar = this.p.get();
        long j = eVar.s;
        int i = eVar.t;
        Integer num = this.y.get(fVar.b);
        if (num == null) {
            num = 0;
        }
        Integer numValueOf = Integer.valueOf(Math.min(num.intValue(), i));
        this.y.put(fVar.b, Integer.valueOf(numValueOf.intValue() + 1));
        this.x.put(fVar.b, Long.valueOf(this.c.b() + TimeUnit.MILLISECONDS.toNanos(j << numValueOf.intValue())));
    }

    void c(String str) {
        if (e()) {
            c cVar = this.f;
            cVar.getClass();
            this.d.postDelayed(cVar.new a(4, str, CBError.CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.A);
            return;
        }
        f fVar = this.h.get(str);
        if (fVar == null) {
            int i = this.v;
            this.v = i + 1;
            fVar = new f(i, str, 1);
            this.h.put(str, fVar);
            this.j.add(fVar);
        }
        if (fVar.i == null) {
            fVar.i = Long.valueOf(this.c.b());
        }
        switch (fVar.c) {
            case 0:
                this.i.remove(fVar);
                this.j.add(fVar);
                fVar.c = 1;
                break;
            case 2:
                fVar.c = 3;
                break;
            case 4:
                fVar.c = 5;
                c(fVar);
                break;
            case 6:
                h(fVar);
                break;
        }
        b();
    }

    private void h(f fVar) {
        CBError.CBImpressionError cBImpressionErrorA;
        String strA;
        if (!this.n.b()) {
            Handler handler = this.d;
            c cVar = this.f;
            cVar.getClass();
            handler.post(cVar.new a(4, fVar.b, CBError.CBImpressionError.INTERNET_UNAVAILABLE_AT_SHOW));
            return;
        }
        com.chartboost.sdk.Model.c cVarA = null;
        try {
            com.chartboost.sdk.Model.a aVar = fVar.d;
            File file = this.b.d().a;
            if (aVar.b == 0 && (this.f.g || aVar.p.equals("video"))) {
                cBImpressionErrorA = a(aVar.a);
                if (cBImpressionErrorA != null) {
                    CBLogging.b("AdUnitManager", "Video media unavailable for the impression");
                }
            } else {
                cBImpressionErrorA = null;
            }
            if (cBImpressionErrorA == null) {
                for (com.chartboost.sdk.Model.b bVar : aVar.c.values()) {
                    if (!bVar.a(file).exists()) {
                        CBLogging.b("AdUnitManager", "Asset does not exist: " + bVar.b);
                        cBImpressionErrorA = CBError.CBImpressionError.ASSET_MISSING;
                    }
                }
            }
            if (cBImpressionErrorA == null) {
                if (aVar.b == 1) {
                    strA = a(aVar, file);
                    if (strA == null) {
                        cBImpressionErrorA = CBError.CBImpressionError.ERROR_LOADING_WEB_VIEW;
                    }
                } else {
                    strA = null;
                }
                if (cBImpressionErrorA == null) {
                    cVarA = a(fVar, strA);
                }
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "showReady", e);
            cBImpressionErrorA = CBError.CBImpressionError.INTERNAL;
        }
        if (cBImpressionErrorA == null) {
            fVar.c = 7;
            com.chartboost.sdk.c cVar2 = this.e;
            cVar2.getClass();
            com.chartboost.sdk.c.RunnableC0032c runnableC0032c = cVar2.new RunnableC0032c(10);
            runnableC0032c.d = cVarA;
            fVar.j = Long.valueOf(this.c.b());
            this.d.post(runnableC0032c);
            return;
        }
        b(fVar, cBImpressionErrorA);
        f(fVar);
    }

    private String a(com.chartboost.sdk.Model.a aVar, File file) {
        if (aVar.r == null) {
            CBLogging.b("AdUnitManager", "AdUnit does not have a template body");
            return null;
        }
        File fileA = aVar.r.a(file);
        HashMap map = new HashMap();
        map.putAll(aVar.d);
        map.put("{% certification_providers %}", o.a(aVar.s));
        for (Map.Entry<String, com.chartboost.sdk.Model.b> entry : aVar.c.entrySet()) {
            map.put(entry.getKey(), entry.getValue().b);
        }
        try {
            return n.a(fileA, map);
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "loadTemplateHtml", e);
            return null;
        }
    }

    private com.chartboost.sdk.Model.c a(f fVar, String str) {
        return new com.chartboost.sdk.Model.c(fVar.d, new d(this, fVar), this.b, this.m, this.o, this.q, this.r, this.d, this.e, this.s, this.t, this.u, this.f, fVar.b, str);
    }

    void a(f fVar, CBError.CBImpressionError cBImpressionError) {
        b(fVar, cBImpressionError);
        if (fVar.c == 7) {
            if (cBImpressionError == CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE) {
                fVar.c = 6;
                fVar.j = null;
                fVar.i = null;
                fVar.m = null;
                return;
            }
            g(fVar);
            f(fVar);
            b();
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0069  */
    /* JADX WARN: Instruction removed from duplicated block: B:33:0x0069, please report this as an issue */
    private void b(f fVar, CBError.CBImpressionError cBImpressionError) {
        String str;
        Handler handler = this.d;
        c cVar = this.f;
        cVar.getClass();
        handler.post(cVar.new a(4, fVar.b, cBImpressionError));
        if (cBImpressionError == CBError.CBImpressionError.NO_AD_FOUND) {
            return;
        }
        String str2 = null;
        String str3 = fVar.d != null ? fVar.d.f : null;
        String str4 = (fVar.c == 0 || fVar.c == 2 || fVar.c == 4) ? "cache" : "show";
        Integer numValueOf = Integer.valueOf(fVar.d != null ? fVar.d.b : fVar.e.intValue());
        if (numValueOf != null) {
            str2 = numValueOf.intValue() == 0 ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE : "web";
        }
        String str5 = str2;
        if (fVar.c >= 0) {
            int i = fVar.c;
            String[] strArr = this.B;
            if (i < strArr.length) {
                str = strArr[fVar.c];
            } else {
                str = "Unknown state: " + fVar.c;
            }
        } else {
            str = "Unknown state: " + fVar.c;
        }
        this.r.a(this.f.b, str4, str5, cBImpressionError.toString(), str3, fVar.b, str);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x005f  */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x005f, please report this as an issue */
    private void b(f fVar, String str) {
        Integer numValueOf;
        String str2;
        if (this.p.get().p) {
            String str3 = null;
            String str4 = fVar.d != null ? fVar.d.f : null;
            String str5 = (fVar.c == 0 || fVar.c == 2 || fVar.c == 4) ? "cache" : "show";
            if (fVar.d != null) {
                numValueOf = Integer.valueOf(fVar.d.b);
            } else {
                numValueOf = fVar.e;
            }
            if (numValueOf != null) {
                str3 = numValueOf.intValue() == 0 ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE : "web";
            }
            String str6 = str3;
            if (fVar.c >= 0) {
                int i = fVar.c;
                String[] strArr = this.B;
                if (i < strArr.length) {
                    str2 = strArr[fVar.c];
                } else {
                    str2 = "Unknown state: " + fVar.c;
                }
            } else {
                str2 = "Unknown state: " + fVar.c;
            }
            this.r.a(str, this.f.b, str5, str6, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("adGetRequestSubmitToCallbackMs", fVar.p), com.chartboost.sdk.Libraries.e.a("downloadRequestToCompletionMs", fVar.n), com.chartboost.sdk.Libraries.e.a("downloadAccumulatedProcessingMs", fVar.o), com.chartboost.sdk.Libraries.e.a("adGetRequestGetResponseCodeMs", fVar.q), com.chartboost.sdk.Libraries.e.a("adGetRequestReadDataMs", fVar.r), com.chartboost.sdk.Libraries.e.a("cacheRequestToReadyMs", fVar.k), com.chartboost.sdk.Libraries.e.a("showRequestToReadyMs", fVar.l), com.chartboost.sdk.Libraries.e.a("showRequestToShownMs", fVar.m), com.chartboost.sdk.Libraries.e.a("adId", str4), com.chartboost.sdk.Libraries.e.a("location", fVar.b), com.chartboost.sdk.Libraries.e.a("state", str2)), false);
        }
    }

    void a(f fVar) {
        if (fVar.c == 7) {
            fVar.c = 6;
            fVar.j = null;
            fVar.i = null;
            fVar.m = null;
        }
    }

    void b(f fVar) {
        if (fVar.c == 7) {
            if (fVar.i != null && fVar.m == null) {
                fVar.m = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(this.c.b() - fVar.i.longValue()));
            }
            b(fVar, "ad-unit-shown");
            this.y.remove(fVar.b);
            Handler handler = this.d;
            c cVar = this.f;
            cVar.getClass();
            handler.post(cVar.new a(5, fVar.b, null));
            i(fVar);
            f(fVar);
            b();
        }
    }

    void d(String str) {
        f fVar = this.h.get(str);
        if (fVar == null || fVar.c != 6) {
            return;
        }
        f(fVar);
        b();
    }

    private void i(f fVar) {
        aj ajVar = new aj(this.f.f, this.o, this.r, 2, new g(this, fVar.b));
        ajVar.j = 1;
        ajVar.a("cached", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        String str = fVar.d.f;
        if (!str.isEmpty()) {
            ajVar.a(AppLovinNativeAdapter.KEY_EXTRA_AD_ID, str);
        }
        ajVar.a("location", fVar.b);
        this.m.a(ajVar);
        this.r.b(this.f.a(fVar.d.b), fVar.b, str);
    }

    CBError.CBImpressionError a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return CBError.CBImpressionError.INVALID_RESPONSE;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("assets");
        if (jSONObjectOptJSONObject == null) {
            return CBError.CBImpressionError.INVALID_RESPONSE;
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(CBUtility.a(CBUtility.a()) ? "video-portrait" : "video-landscape");
        if (jSONObjectOptJSONObject2 == null) {
            return CBError.CBImpressionError.VIDEO_UNAVAILABLE_FOR_CURRENT_ORIENTATION;
        }
        String strOptString = jSONObjectOptJSONObject2.optString("id");
        if (strOptString.isEmpty()) {
            return CBError.CBImpressionError.VIDEO_ID_MISSING;
        }
        if (new File(this.b.d().g, strOptString).exists()) {
            return null;
        }
        return CBError.CBImpressionError.VIDEO_UNAVAILABLE;
    }

    private boolean e() {
        return this.f.a == 0 && !com.chartboost.sdk.i.u && this.q.getInt("cbPrefSessionCount", 0) == 1;
    }

    public class a implements Runnable {
        final int a;
        final String b;
        final f c;
        final CBError.CBImpressionError d;

        public a(int i, String str, f fVar, CBError.CBImpressionError cBImpressionError) {
            this.a = i;
            this.b = str;
            this.c = fVar;
            this.d = cBImpressionError;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                synchronized (e.this) {
                    int i = this.a;
                    if (i == 0) {
                        e.this.a();
                    } else {
                        switch (i) {
                            case 2:
                                e.this.k = null;
                                e.this.b();
                                break;
                            case 3:
                                e.this.b(this.b);
                                break;
                            case 4:
                                e.this.c(this.b);
                                break;
                            case 5:
                                e.this.b(this.c);
                                break;
                            case 6:
                                e.this.a(this.c, this.d);
                                break;
                            case 7:
                                e.this.a(this.c);
                                break;
                            case 8:
                                e.this.d(this.b);
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "run", e);
            }
        }
    }
}
