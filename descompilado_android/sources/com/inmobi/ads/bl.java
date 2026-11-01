package com.inmobi.ads;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.cache.AssetStore;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PrefetchAdStore.java */
/* JADX INFO: loaded from: classes2.dex */
public class bl implements e.a {
    private static final String c = "bl";

    @NonNull
    c.d b;
    private final a d;
    private f f;
    private boolean h;
    boolean a = false;
    private long g = 0;
    private final com.inmobi.ads.cache.f i = new com.inmobi.ads.cache.f() { // from class: com.inmobi.ads.bl.1
        @Override // com.inmobi.ads.cache.f
        public final void a(com.inmobi.ads.cache.b bVar) {
            String unused = bl.c;
            new StringBuilder("onAssetsFetchFailure of batch ").append(bVar == null ? null : bVar.toString());
            ArrayList arrayList = new ArrayList();
            if (bVar != null) {
                for (com.inmobi.ads.cache.a aVar : bVar.a) {
                    HashMap map = new HashMap();
                    map.put("url", aVar.d);
                    map.put("latency", Long.valueOf(aVar.a));
                    map.put("size", Long.valueOf(com.inmobi.commons.core.utilities.c.a(aVar.e)));
                    bl.this.d.a("VideoAssetDownloadFailed", map);
                    for (com.inmobi.ads.a aVar2 : bl.this.e.b(aVar.d, bl.this.f == null ? null : bl.this.f.c)) {
                        if (!arrayList.contains(Long.valueOf(aVar2.d))) {
                            arrayList.add(Long.valueOf(aVar2.d));
                        }
                    }
                }
            }
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                bl.this.d.b(((Long) it.next()).longValue(), new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_NO_LONGER_AVAILABLE));
            }
        }

        @Override // com.inmobi.ads.cache.f
        public final void b(com.inmobi.ads.cache.b bVar) {
            String unused = bl.c;
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
                        bl.this.d.a("GotCachedVideoAsset", map);
                    } else {
                        bl.this.d.a("VideoAssetDownloaded", map);
                    }
                    List<com.inmobi.ads.a> listA = bl.this.e.a(aVar.d, bl.this.f == null ? null : bl.this.f.c);
                    String unused2 = bl.c;
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
            if (!arrayList.contains(Long.valueOf(bl.this.f.a))) {
                arrayList.add(Long.valueOf(bl.this.f.a));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Long) it.next()).longValue();
                String unused3 = bl.c;
                StringBuilder sb2 = new StringBuilder("Notifying ad unit with placement ID (");
                sb2.append(jLongValue);
                sb2.append(")");
                bl.this.d.a(jLongValue);
            }
        }
    };
    private final d e = d.a();

    /* JADX INFO: compiled from: PrefetchAdStore.java */
    public interface a {
        void a(long j);

        void a(String str, Map<String, Object> map);

        void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    public bl(a aVar, @NonNull c.d dVar) {
        this.d = aVar;
        this.b = dVar;
    }

    private boolean a(int i) {
        return SystemClock.elapsedRealtime() - this.g < ((long) (i * 1000));
    }

    @Override // com.inmobi.ads.e.a
    public final void a(g gVar) {
        List<com.inmobi.ads.a> listC = c(gVar);
        if (listC == null) {
            new StringBuilder("Could not parse ad response:").append(gVar.a.b());
            if (this.a) {
                return;
            }
            this.d.b(this.f.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
            return;
        }
        if (listC.size() == 0) {
            new StringBuilder("Ad response received but no ad available:").append(gVar.a.b());
            HashMap map = new HashMap();
            map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            map.put("isPreloaded", "1");
            map.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerNoFill", map);
            if (this.a) {
                return;
            }
            this.d.b(this.f.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NO_FILL));
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("numberOfAdsReturned", Integer.valueOf(listC.size()));
        map2.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
        map2.put("isPreloaded", "1");
        map2.put("im-accid", com.inmobi.commons.a.a.e());
        this.d.a("ServerFill", map2);
        if ("HTML".equalsIgnoreCase(listC.get(0).e()) && AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(this.f.e)) {
            if (this.a) {
                return;
            }
            this.d.b(this.f.a, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
        } else {
            this.e.a(listC, this.f.a, this.b.a, this.f.e, this.f.j, com.inmobi.ads.c.a.a(this.f.g), null);
            a(listC);
            if (this.a || this.h) {
                return;
            }
            this.d.a(this.f.a);
        }
    }

    @Override // com.inmobi.ads.e.a
    public final void b(g gVar) {
        if (this.a) {
            return;
        }
        HashMap map = new HashMap();
        map.put("errorCode", String.valueOf(gVar.a.b.a.getValue()));
        map.put(IronSourceConstants.EVENTS_ERROR_REASON, gVar.a.b.b);
        map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
        map.put("isPreloaded", "1");
        map.put("im-accid", com.inmobi.commons.a.a.e());
        this.d.a("ServerError", map);
        this.d.b(this.f.a, gVar.b);
    }

    private List<com.inmobi.ads.a> c(g gVar) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(gVar.a.b()).getJSONArray("ads");
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
            map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.g));
            map.put("isPreloaded", "1");
            map.put("im-accid", com.inmobi.commons.a.a.e());
            this.d.a("ServerError", map);
            return null;
        }
    }

    private void a(List<com.inmobi.ads.a> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        com.inmobi.ads.a aVar = list.get(0);
        if (aVar != null) {
            Set<bm> setD = aVar.d();
            if (setD.size() == 0) {
                this.d.a(this.f.a);
                return;
            }
            AssetStore.a().a(new com.inmobi.ads.cache.b(UUID.randomUUID().toString(), aVar.h, setD, this.h ? this.i : null));
        }
        for (com.inmobi.ads.a aVar2 : list.subList(1, list.size())) {
            if (aVar2 != null && aVar2.e().equalsIgnoreCase("inmobiJson")) {
                Set<bm> setD2 = aVar2.d();
                if (setD2.size() != 0) {
                    AssetStore.a().a(new com.inmobi.ads.cache.b(UUID.randomUUID().toString(), aVar2.h, setD2, (com.inmobi.ads.cache.f) null));
                }
            }
        }
    }

    @Nullable
    public final String a(f fVar, boolean z, int i) throws com.inmobi.ads.a.a {
        String strA;
        if (com.inmobi.commons.core.utilities.b.e.b()) {
            d.c();
        }
        this.a = false;
        this.f = fVar;
        this.h = z;
        b.b();
        List<com.inmobi.ads.a> listC = this.e.c(this.f.a, this.f.c, this.f.j, com.inmobi.ads.c.a.a(this.f.g));
        int size = listC.size();
        if (size == 0) {
            this.a = false;
            if (a(i)) {
                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            return a(this.f);
        }
        if (size < this.b.c) {
            this.a = true;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(listC);
            if (a(i)) {
                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
            }
            strA = a(this.f);
        } else {
            this.a = true;
            String str = listC.get(0).h;
            if (!z) {
                this.d.a(this.f.a);
            }
            a(listC);
            strA = str;
        }
        HashMap map = new HashMap();
        map.put("im-accid", com.inmobi.commons.a.a.e());
        map.put("isPreloaded", "1");
        this.d.a("AdCacheAdRequested", map);
        return strA;
    }

    @NonNull
    private String a(f fVar) {
        if (fVar != null) {
            Map<String, String> map = fVar.h;
            if (map == null) {
                map = new HashMap<>();
            }
            if (!map.containsKey("preload-request")) {
                map.put("preload-request", "1");
                fVar.h = map;
            }
        }
        this.g = SystemClock.elapsedRealtime();
        new e(fVar, this).a();
        HashMap map2 = new HashMap();
        map2.put("isPreloaded", "1");
        map2.put("clientRequestId", fVar.i);
        map2.put("im-accid", com.inmobi.commons.a.a.e());
        this.d.a("ServerCallInitiated", map2);
        return fVar.i;
    }
}
