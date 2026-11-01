package com.inmobi.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.bi;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.f;
import com.inmobi.ads.i;
import com.inmobi.ads.p;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdStore.java */
/* JADX INFO: loaded from: classes2.dex */
public class h implements e.a {
    private static final String f = "h";

    @NonNull
    final a a;

    @NonNull
    f c;

    @NonNull
    public c.d d;
    long e = 0;
    private final com.inmobi.ads.cache.f g = new com.inmobi.ads.cache.f() { // from class: com.inmobi.ads.h.1
        @Override // com.inmobi.ads.cache.f
        public final void a(com.inmobi.ads.cache.b bVar) {
            String unused = h.f;
            new StringBuilder("onAssetsFetchFailure of batch ").append(bVar == null ? null : bVar.toString());
            ArrayList arrayList = new ArrayList();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap map = new HashMap();
                    map.put("url", aVar.d);
                    map.put("latency", Long.valueOf(aVar.a));
                    map.put("size", Long.valueOf(com.inmobi.commons.core.utilities.c.a(aVar.e)));
                    h.this.a.a("VideoAssetDownloadFailed", map);
                    for (com.inmobi.ads.a aVar2 : h.this.b.b(aVar.d, h.this.c.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                h.this.a.a(((Long) it.next()).longValue(), false);
            }
        }

        @Override // com.inmobi.ads.cache.f
        public final void b(com.inmobi.ads.cache.b bVar) {
            String unused = h.f;
            new StringBuilder("onAssetsFetchSuccess of batch ").append(bVar == null ? null : bVar.toString());
            ArrayList arrayList = new ArrayList();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap map = new HashMap();
                    map.put("url", aVar.d);
                    map.put("latency", Long.valueOf(aVar.a));
                    map.put("size", Long.valueOf(com.inmobi.commons.core.utilities.c.a(aVar.e)));
                    map.put("clientRequestId", bVar.f);
                    if (aVar.j) {
                        h.this.a.a("GotCachedVideoAsset", map);
                    } else {
                        h.this.a.a("VideoAssetDownloaded", map);
                    }
                    List<com.inmobi.ads.a> listA = h.this.b.a(aVar.d, h.this.c.c);
                    String unused2 = h.f;
                    StringBuilder sb = new StringBuilder("Found ");
                    sb.append(listA.size());
                    sb.append(" ads mapping to this asset");
                    for (com.inmobi.ads.a aVar2 : listA) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(h.this.c.a))) {
                arrayList.add(Long.valueOf(h.this.c.a));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Long) it.next()).longValue();
                String unused3 = h.f;
                StringBuilder sb2 = new StringBuilder("Notifying ad unit with placement ID (");
                sb2.append(jLongValue);
                sb2.append(")");
                h.this.a.a(jLongValue, true);
            }
        }
    };

    @NonNull
    public final d b = d.a();

    /* JADX INFO: compiled from: AdStore.java */
    public interface a {
        void a(long j, InMobiAdRequestStatus inMobiAdRequestStatus);

        void a(long j, @NonNull com.inmobi.ads.a aVar);

        void a(long j, boolean z);

        void a(String str, Map<String, Object> map);

        void b(long j, com.inmobi.ads.a aVar);
    }

    public h(@NonNull a aVar, @NonNull c.d dVar, @NonNull f fVar) {
        this.a = aVar;
        this.d = dVar;
        this.c = fVar;
    }

    public static void a() {
        if (com.inmobi.commons.core.utilities.b.e.b()) {
            d.c();
        }
    }

    public static void c() {
        b.b();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.inmobi.ads.h$2] */
    final void a(final com.inmobi.ads.a aVar) {
        new Thread() { // from class: com.inmobi.ads.h.2
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                d unused = h.this.b;
                d.a(aVar);
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.inmobi.ads.h$3] */
    final void a(@NonNull final String str) {
        new Thread() { // from class: com.inmobi.ads.h.3
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                d unused = h.this.b;
                d.a(str);
            }
        }.start();
    }

    @NonNull
    final String a(f fVar, boolean z) {
        b(fVar, z);
        this.e = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        HashMap map = new HashMap();
        map.put("isPreloaded", fVar.c());
        map.put("clientRequestId", fVar.i);
        map.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerCallInitiated", map);
        return fVar.i;
    }

    private void a(List<com.inmobi.ads.a> list, @NonNull String str, @Nullable String str2) {
        this.b.a(list, this.c.a, this.d.a, this.c.e, this.c.j, str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0035  */
    private void a(List<com.inmobi.ads.a> list, String str) {
        byte b;
        com.inmobi.ads.a aVar = list.get(0);
        String upperCase = aVar.e().toUpperCase(Locale.ENGLISH);
        int iHashCode = upperCase.hashCode();
        if (iHashCode != -598127114) {
            if (iHashCode == 2228139 && upperCase.equals("HTML")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (upperCase.equals("INMOBIJSON")) {
            b = 1;
        } else {
            b = -1;
        }
        if (b == 0) {
            if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(this.c.e)) {
                this.a.a(this.c.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                return;
            }
            a(list.subList(1, list.size()), str, null);
            this.a.a(this.c.a, aVar);
            a(this.c);
            return;
        }
        if (b != 1) {
            return;
        }
        a(list, str, null);
        if ("int".equals(this.c.e)) {
            this.a.b(this.c.a, aVar);
        } else if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(this.c.e)) {
            com.inmobi.ads.a aVarB = this.b.b(this.c.a, this.c.c, this.c.j, str);
            if (aVarB == null) {
                aVarB = aVar;
            } else if (!aVar.a(aVarB)) {
                list.add(0, aVarB);
            }
            this.a.a(this.c.a, aVarB);
            a(this.c);
        }
        a(list);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0051  */
    private void b(List<com.inmobi.ads.a> list, String str, @NonNull String str2) {
        byte b;
        a(list, str, str2);
        String str3 = this.c.e;
        b.b();
        com.inmobi.ads.a aVarC = d.c(str2);
        if (aVarC == null) {
            this.a.a(this.c.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
            return;
        }
        String upperCase = aVarC.e().toUpperCase(Locale.ENGLISH);
        int iHashCode = upperCase.hashCode();
        byte b2 = -1;
        boolean z = false;
        if (iHashCode != -598127114) {
            if (iHashCode == 2228139 && upperCase.equals("HTML")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (upperCase.equals("INMOBIJSON")) {
            b = 1;
        } else {
            b = -1;
        }
        if (b == 0) {
            d.a(str2);
            this.a.a(this.c.a, aVarC);
            a(this.c);
            return;
        }
        if (b != 1) {
            return;
        }
        String str4 = this.c.e;
        int iHashCode2 = str4.hashCode();
        if (iHashCode2 != -1052618729) {
            if (iHashCode2 == 104431 && str4.equals("int")) {
                b2 = 0;
            }
        } else if (str4.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
            b2 = 1;
        }
        if (b2 == 0) {
            this.a.b(this.c.a, aVarC);
        } else if (b2 == 1) {
            d.a(str2);
            this.a.a(this.c.a, aVarC);
            a(this.c);
        }
        Iterator<com.inmobi.ads.a> it = list.iterator();
        while (it.hasNext()) {
            if (aVarC.a(it.next())) {
                z = true;
                break;
            }
        }
        if (!z) {
            list.add(aVarC);
        }
        a(list);
    }

    @Override // com.inmobi.ads.e.a
    public final void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        List<com.inmobi.ads.a> listA = a(gVar, sb);
        String string = sb.toString();
        boolean zIsEmpty = TextUtils.isEmpty(string);
        if (listA == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            this.a.a(this.c.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
            return;
        }
        if (listA.size() == 0 && zIsEmpty) {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            HashMap map = new HashMap();
            map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            map.put("isPreloaded", this.c.c());
            map.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerNoFill", map);
            this.a.a(this.c.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NO_FILL));
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("numberOfAdsReturned", Integer.valueOf(listA.size()));
        map2.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
        map2.put("isPreloaded", this.c.c());
        map2.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerFill", map2);
        for (com.inmobi.ads.a aVar : listA) {
            HashMap map3 = new HashMap();
            map3.put("ts", Long.valueOf(System.currentTimeMillis()));
            map3.put("impId", aVar.g);
            map3.put("plId", Long.valueOf(aVar.d));
            this.a.a("AdCacheImpressionInserted", map3);
        }
        String strA = com.inmobi.ads.c.a.a(this.c.g);
        if (zIsEmpty) {
            a(listA, strA);
        } else {
            b(listA, strA, string);
        }
    }

    final void a(List<com.inmobi.ads.a> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        com.inmobi.ads.a aVar = list.get(0);
        if (aVar != null) {
            Set<bm> setD = aVar.d();
            if (setD.size() == 0) {
                this.a.a(this.c.a, true);
                return;
            }
            AssetStore.a().a(new com.inmobi.ads.cache.b(UUID.randomUUID().toString(), aVar.h, setD, this.g));
        }
        for (com.inmobi.ads.a aVar2 : list.subList(1, list.size())) {
            if (aVar2 != null) {
                Set<bm> setD2 = aVar2.d();
                if (setD2.size() != 0) {
                    AssetStore.a().a(new com.inmobi.ads.cache.b(UUID.randomUUID().toString(), aVar2.h, setD2, (com.inmobi.ads.cache.f) null));
                }
            }
        }
    }

    @Nullable
    private List<com.inmobi.ads.a> a(g gVar, @Nullable StringBuilder sb) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(gVar.a.b());
            sb.append(jSONObject.optString("winnerImpressionId").trim());
            JSONArray jSONArray = jSONObject.getJSONArray("ads");
            if (jSONArray == null) {
                return arrayList;
            }
            int iMin = Math.min(gVar.c.d, jSONArray.length());
            for (int i = 0; i < iMin; i++) {
                com.inmobi.ads.a aVarA = com.inmobi.ads.a.C0070a.a(jSONArray.getJSONObject(i), gVar.c.a, gVar.c.e, gVar.c.c, gVar.c.i, gVar.c.j, gVar.c.k);
                if (aVarA != null) {
                    arrayList.add(aVarA);
                }
            }
            if (iMin <= 0 || !arrayList.isEmpty()) {
                return arrayList;
            }
            return null;
        } catch (JSONException e) {
            HashMap map = new HashMap();
            map.put("errorCode", "ParsingError");
            map.put(IronSourceConstants.EVENTS_ERROR_REASON, e.getLocalizedMessage());
            map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
            map.put("im-accid", com.inmobi.commons.a.a.e());
            this.a.a("ServerError", map);
            return null;
        }
    }

    @Override // com.inmobi.ads.e.a
    public final void b(g gVar) {
        HashMap map = new HashMap();
        map.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
        map.put(IronSourceConstants.EVENTS_ERROR_REASON, gVar.a.b.b);
        map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.e));
        map.put("im-accid", com.inmobi.commons.a.a.e());
        this.a.a("ServerError", map);
        this.a.a(this.c.a, gVar.b);
    }

    final String b() {
        com.inmobi.ads.a aVarB;
        String strA = com.inmobi.ads.c.a.a(this.c.g);
        b.b();
        com.inmobi.ads.a aVar = null;
        if (d.a(this.c.a, this.c.c, this.c.j, strA) != 0 && (aVarB = this.b.b(this.c.a, this.c.c, this.c.j, strA)) != null) {
            HashMap map = new HashMap();
            map.put("clientRequestId", aVarB.h);
            map.put("im-accid", com.inmobi.commons.a.a.e());
            map.put("isPreloaded", this.c.c());
            this.a.a("AdCacheHit", map);
            a(this.c);
            aVar = aVarB;
        }
        if (aVar == null) {
            return this.c.c().equals("1") ? a(this.c, true) : a(this.c, false);
        }
        String str = aVar.h;
        this.a.a(this.c.a, aVar);
        if (!"INMOBIJSON".equalsIgnoreCase(aVar.e())) {
            return str;
        }
        a(new ArrayList(Collections.singletonList(aVar)));
        return str;
    }

    private static void b(f fVar, boolean z) {
        if (fVar != null) {
            Map<String, String> map = fVar.h;
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("preload-request", String.valueOf(z ? 1 : 0));
            fVar.h = map;
        }
    }

    public final void a(@NonNull final f fVar) {
        b.b();
        int iA = d.a(fVar.a, fVar.c, fVar.j, com.inmobi.ads.c.a.a(fVar.g));
        boolean zEquals = "int".equals(fVar.e);
        if (iA < this.d.c) {
            new StringBuilder("Cached ad count below threshold, firing ad request for Placement : ").append(fVar.a);
            final com.inmobi.ads.c.a aVarA = com.inmobi.ads.c.a.a(fVar.e);
            if (zEquals) {
                b(fVar, true);
                try {
                    new bl(new bl.a() { // from class: com.inmobi.ads.c.a.3
                        final /* synthetic */ f a;

                        public AnonymousClass3() {
                            fVar = fVar;
                        }

                        @Override // com.inmobi.ads.bl.a
                        public final void a(long j) {
                            String unused = a.d;
                        }

                        @Override // com.inmobi.ads.bl.a
                        public final void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
                            String unused = a.d;
                            new StringBuilder("Interstitial Prefetch failed with the message - ").append(inMobiAdRequestStatus.getMessage());
                        }

                        @Override // com.inmobi.ads.bl.a
                        public final void a(String str, Map<String, Object> map) {
                            a.a(str, map, fVar);
                        }
                    }, this.d).a(fVar, true, com.inmobi.ads.c.a.b.c);
                    return;
                } catch (com.inmobi.ads.a.a e) {
                    e.getMessage();
                    return;
                }
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.a.2
                final /* synthetic */ f a;
                private i.d c;

                public AnonymousClass2() {
                    fVar = fVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        Context contextB = com.inmobi.commons.a.a.b();
                        if (contextB == null) {
                            return;
                        }
                        bi biVarA = bi.a(fVar.a, fVar.g, fVar.e, fVar.f);
                        biVarA.f = fVar.j;
                        String unused = a.d;
                        StringBuilder sb = new StringBuilder("preFetchAdUnit. pid:");
                        sb.append(biVarA.a);
                        sb.append(" tp:");
                        sb.append(biVarA.b);
                        if (biVarA.c == null && biVarA.b != null) {
                            HashMap map = new HashMap();
                            map.put("tp", biVarA.b);
                            biVarA.c = map;
                        }
                        this.c = new C0073a(biVarA);
                        i iVarB = a.b(a.this.c, contextB, biVarA);
                        if (iVarB == null) {
                            return;
                        }
                        iVarB.e = biVarA.d;
                        iVarB.f = biVarA.c;
                        iVarB.n = true;
                        iVarB.q = this.c;
                        if (a.this.c.equalsIgnoreCase(ai.a.cW)) {
                            ((p) iVarB).A = fVar.c;
                            ((p) iVarB).y = true;
                        }
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
