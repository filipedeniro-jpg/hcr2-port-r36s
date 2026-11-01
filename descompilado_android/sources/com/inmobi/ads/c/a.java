package com.inmobi.ads.c;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.ac;
import com.inmobi.ads.aj;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.c;
import com.inmobi.ads.f;
import com.inmobi.ads.i;
import com.inmobi.ads.p;
import com.inmobi.commons.core.utilities.Logger;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: AdPreFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements com.inmobi.commons.core.configs.b.c {
    public static c b = null;
    private static final String d = "a";
    private static volatile a e;
    private static volatile a f;
    private static volatile a g;
    public String c;
    public static ConcurrentHashMap<bi, i> a = new ConcurrentHashMap<>(8, 0.9f, 3);
    private static final Object h = new Object();
    private static final Object i = new Object();
    private static final Object j = new Object();

    private static a d() {
        a aVar = e;
        if (aVar == null) {
            synchronized (h) {
                aVar = e;
                if (aVar == null) {
                    aVar = new a(ai.a.cW);
                    e = aVar;
                }
            }
        }
        return aVar;
    }

    private static a e() {
        a aVar = f;
        if (aVar == null) {
            synchronized (i) {
                aVar = f;
                if (aVar == null) {
                    aVar = new a("int");
                    f = aVar;
                }
            }
        }
        return aVar;
    }

    private static a f() {
        a aVar = g;
        if (aVar == null) {
            synchronized (j) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    @NonNull
    public static a a(String str) {
        byte b2;
        int iHashCode = str.hashCode();
        if (iHashCode != -1396342996) {
            if (iHashCode != -1052618729) {
                if (iHashCode == 104431 && str.equals("int")) {
                    b2 = 2;
                } else {
                    b2 = -1;
                }
            } else if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                b2 = 1;
            } else {
                b2 = -1;
            }
        } else if (str.equals(ai.a.cW)) {
            b2 = 0;
        } else {
            b2 = -1;
        }
        if (b2 == 0) {
            return d();
        }
        if (b2 == 1) {
            return f();
        }
        if (b2 == 2) {
            return e();
        }
        throw new IllegalArgumentException("Unknown adType passed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static i b(String str, @NonNull Context context, @NonNull bi biVar) {
        byte b2 = -1;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1396342996) {
                if (iHashCode != -1052618729) {
                    if (iHashCode == 104431 && str.equals("int")) {
                        b2 = 1;
                    }
                } else if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                    b2 = 2;
                }
            } else if (str.equals(ai.a.cW)) {
                b2 = 0;
            }
            if (b2 == 0) {
                return p.a(context, biVar, (i.b) null, 1);
            }
            if (b2 == 1) {
                return ac.a.a(com.inmobi.commons.a.a.b(), biVar, null);
            }
            if (b2 != 2) {
                return null;
            }
            return aj.a(context, biVar, (i.b) null, 1);
        } catch (IllegalStateException e2) {
            e2.getMessage();
        }
    }

    a(String str) {
        this.c = str;
        b = new c();
        com.inmobi.commons.core.configs.b.a().a(b, this);
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        b = (c) aVar;
        com.inmobi.commons.core.e.b.a().a("ads", b.l);
    }

    private void g() {
        Iterator<Map.Entry<bi, i>> it = a.entrySet().iterator();
        while (it.hasNext()) {
            try {
                Map.Entry<bi, i> next = it.next();
                final i value = next.getValue();
                if (value.h()) {
                    StringBuilder sb = new StringBuilder("cleanUpExpiredCachedAdUnits. pid:");
                    sb.append(next.getKey().a);
                    sb.append(" tp:");
                    sb.append(next.getKey().b);
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.a.5
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                value.v();
                            } catch (Exception e2) {
                                String unused = a.d;
                                new StringBuilder("Encountered an unexpected error clearing the ad unit: ").append(e2.getMessage());
                                Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered an unexpected error clearing an old ad");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                            }
                        }
                    });
                    it.remove();
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered an unexpected error in expiring ad units; ").append(e2.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return;
            }
        }
    }

    public final void b() {
        h();
        g();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.inmobi.ads.c.a$7] */
    public final void a(final bi biVar) {
        if (b.c(this.c).a) {
            new Thread() { // from class: com.inmobi.ads.c.a.7
                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(biVar);
                    int iA = bj.a().a(arrayList, a.b.c(a.this.c).c);
                    if (iA > 0) {
                        HashMap map = new HashMap();
                        map.put(com.my.target.i.aa, Integer.valueOf(iA));
                        map.put("type", a.this.c);
                        map.put("plId", Long.valueOf(biVar.a));
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "PreLoadPidOverflow", map);
                    }
                }
            }.start();
        }
    }

    private void h() {
        if (b.c(this.c).a) {
            bj.a();
            int iA = bj.a(b.c(this.c).b, this.c);
            if (iA > 0) {
                try {
                    HashMap map = new HashMap();
                    map.put("type", this.c);
                    map.put(com.my.target.i.aa, Integer.valueOf(iA));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "PreLoadPidExpiry", map);
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                    sb.append(e2.getMessage());
                    sb.append(")");
                }
            }
        }
    }

    @NonNull
    public static String a(Map<String, String> map) {
        return (map == null || map.get("tp") == null) ? "" : map.get("tp");
    }

    /* JADX INFO: renamed from: com.inmobi.ads.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdPreFetcher.java */
    static class C0073a implements i.d {
        private bi a;

        C0073a(bi biVar) {
            this.a = biVar;
        }

        @Override // com.inmobi.ads.i.d
        public final void a(@NonNull i iVar) {
            String unused = a.d;
            a.a.remove(this.a);
        }

        @Override // com.inmobi.ads.i.d
        public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
            String unused = a.d;
            new StringBuilder("onAdLoadFailed called. Status:").append(inMobiAdRequestStatus.getMessage());
            a.a.remove(this.a);
            if (InMobiAdRequestStatus.StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
                iVar.d("PreLoadServerNoFill");
            }
        }
    }

    public static void a(String str, Map<String, Object> map, f fVar) {
        HashMap map2 = new HashMap();
        map2.put("type", fVar.e);
        map2.put("plId", Long.valueOf(fVar.a));
        map2.put("isPreloaded", 1);
        map2.put("networkType", Integer.valueOf(com.inmobi.commons.core.utilities.b.b.a()));
        map2.put("ts", Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            map2.put("clientRequestId", fVar.i);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue());
        }
        try {
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", str, map2);
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
            sb.append(e2.getMessage());
            sb.append(")");
        }
    }

    public final void a() {
        Application application = (Application) com.inmobi.commons.a.a.b();
        if (application != null) {
            application.registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.inmobi.ads.c.a.4
                @Override // android.content.ComponentCallbacks
                public final void onConfigurationChanged(Configuration configuration) {
                }

                @Override // android.content.ComponentCallbacks
                public final void onLowMemory() {
                }

                @Override // android.content.ComponentCallbacks2
                public final void onTrimMemory(int i2) {
                    if (i2 == 15) {
                        a.a(a.this);
                    }
                }
            });
        }
        h();
        g();
        if (b.c(this.c).a) {
            bj.a();
            ArrayList arrayList = (ArrayList) bj.a(this.c);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                final bi biVar = (bi) arrayList.get(i2);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.a.1
                    private i.d c;

                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Context contextB = com.inmobi.commons.a.a.b();
                            if (contextB == null) {
                                return;
                            }
                            String unused = a.d;
                            StringBuilder sb = new StringBuilder("preFetchAdUnit. pid:");
                            sb.append(biVar.a);
                            sb.append(" tp:");
                            sb.append(biVar.b);
                            if (biVar.c == null && biVar.b != null) {
                                HashMap map = new HashMap();
                                map.put("tp", biVar.b);
                                biVar.c = map;
                            }
                            this.c = new C0073a(biVar);
                            i iVarB = a.b(a.this.c, contextB, biVar);
                            if (iVarB == null) {
                                return;
                            }
                            iVarB.e = biVar.d;
                            iVarB.f = biVar.c;
                            iVarB.n = true;
                            iVarB.q = this.c;
                            iVarB.a(true);
                        } catch (Exception e2) {
                            String unused2 = a.d;
                            new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e2.getMessage());
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                        }
                    }
                });
            }
        }
    }

    static /* synthetic */ void a(a aVar) {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return;
        }
        new Handler(contextB.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.a.6
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    String unused = a.d;
                    Iterator<Map.Entry<bi, i>> it = a.a.entrySet().iterator();
                    while (it.hasNext()) {
                        it.next().getValue().v();
                        it.remove();
                    }
                } catch (Exception e2) {
                    String unused2 = a.d;
                    new StringBuilder("SDK encountered unexpected error in flushing ad unit cache; ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        });
    }
}
