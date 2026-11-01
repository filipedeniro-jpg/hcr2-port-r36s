package com.applovin.impl.a;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.utils.o;
import com.my.target.bj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private List<k> a;
    private List<String> b;
    private int c;
    private Uri d;
    private final Set<g> e;
    private final Map<String, Set<g>> f;

    public enum a {
        UNSPECIFIED,
        LOW,
        MEDIUM,
        HIGH
    }

    private j() {
        this.a = Collections.EMPTY_LIST;
        this.b = Collections.EMPTY_LIST;
        this.e = new HashSet();
        this.f = new HashMap();
    }

    private j(c cVar) {
        this.a = Collections.EMPTY_LIST;
        this.b = Collections.EMPTY_LIST;
        this.e = new HashSet();
        this.f = new HashMap();
        this.b = cVar.h();
    }

    private static int a(String str, com.applovin.impl.sdk.h hVar) {
        try {
            List<String> listA = com.applovin.impl.sdk.utils.d.a(str, ":");
            if (listA.size() == 3) {
                return (int) (TimeUnit.HOURS.toSeconds(com.applovin.impl.sdk.utils.k.a(listA.get(0))) + TimeUnit.MINUTES.toSeconds(com.applovin.impl.sdk.utils.k.a(listA.get(1))) + ((long) com.applovin.impl.sdk.utils.k.a(listA.get(2))));
            }
        } catch (Throwable unused) {
            hVar.w().d("VastVideoCreative", "Unable to parse duration from \"" + str + "\"");
        }
        return 0;
    }

    public static j a(o oVar, j jVar, c cVar, com.applovin.impl.sdk.h hVar) {
        o oVarB;
        List<k> listA;
        o oVarB2;
        int iA;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        if (jVar == null) {
            try {
                jVar = new j(cVar);
            } catch (Throwable th) {
                hVar.w().b("VastVideoCreative", "Error occurred while initializing", th);
                return null;
            }
        }
        if (jVar.c == 0 && (oVarB2 = oVar.b(bj.gb)) != null && (iA = a(oVarB2.c(), hVar)) > 0) {
            jVar.c = iA;
        }
        o oVarB3 = oVar.b(bj.ge);
        if (oVarB3 != null && (listA = a(oVarB3, hVar)) != null && listA.size() > 0) {
            if (jVar.a != null) {
                listA.addAll(jVar.a);
            }
            jVar.a = listA;
        }
        o oVarB4 = oVar.b(bj.gg);
        if (oVarB4 != null) {
            if (jVar.d == null && (oVarB = oVarB4.b(bj.gh)) != null) {
                String strC = oVarB.c();
                if (com.applovin.impl.sdk.utils.k.b(strC)) {
                    jVar.d = Uri.parse(strC);
                }
            }
            i.a(oVarB4.a(bj.gi), jVar.e, cVar, hVar);
        }
        i.a(oVar, jVar.f, cVar, hVar);
        return jVar;
    }

    private static List<k> a(o oVar, com.applovin.impl.sdk.h hVar) {
        List<o> listA = oVar.a(bj.gf);
        ArrayList arrayList = new ArrayList(listA.size());
        List<String> listA2 = com.applovin.impl.sdk.utils.d.a((String) hVar.a(com.applovin.impl.sdk.b.b.eP));
        List<String> listA3 = com.applovin.impl.sdk.utils.d.a((String) hVar.a(com.applovin.impl.sdk.b.b.eO));
        Iterator<o> it = listA.iterator();
        while (it.hasNext()) {
            k kVarA = k.a(it.next(), hVar);
            if (kVarA != null) {
                try {
                    String strD = kVarA.d();
                    if (!com.applovin.impl.sdk.utils.k.b(strD) || listA2.contains(strD)) {
                        if (((Boolean) hVar.a(com.applovin.impl.sdk.b.b.eQ)).booleanValue()) {
                            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(kVarA.b().toString());
                            if (!com.applovin.impl.sdk.utils.k.b(fileExtensionFromUrl) || listA3.contains(fileExtensionFromUrl)) {
                            }
                        }
                        hVar.w().c("VastVideoCreative", "Video file not supported: " + kVarA);
                    }
                    arrayList.add(kVarA);
                } catch (Throwable th) {
                    hVar.w().b("VastVideoCreative", "Failed to validate vidoe file: " + kVarA, th);
                }
            }
        }
        return arrayList;
    }

    public k a(a aVar) {
        List<k> list = this.a;
        if (list == null || list.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList(3);
        for (String str : this.b) {
            for (k kVar : this.a) {
                String strD = kVar.d();
                if (com.applovin.impl.sdk.utils.k.b(strD) && str.equalsIgnoreCase(strD)) {
                    arrayList.add(kVar);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = this.a;
        }
        if (com.applovin.impl.sdk.utils.e.e()) {
            Collections.sort(arrayList, new Comparator<k>() { // from class: com.applovin.impl.a.j.1
                @Override // java.util.Comparator
                @TargetApi(19)
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(k kVar2, k kVar3) {
                    return Integer.compare(kVar2.e(), kVar3.e());
                }
            });
        }
        return (k) arrayList.get(aVar == a.LOW ? 0 : aVar == a.MEDIUM ? arrayList.size() / 2 : arrayList.size() - 1);
    }

    public List<k> a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public Uri c() {
        return this.d;
    }

    public Set<g> d() {
        return this.e;
    }

    public Map<String, Set<g>> e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        if (this.c != jVar.c) {
            return false;
        }
        List<k> list = this.a;
        if (list == null ? jVar.a != null : !list.equals(jVar.a)) {
            return false;
        }
        Uri uri = this.d;
        if (uri == null ? jVar.d != null : !uri.equals(jVar.d)) {
            return false;
        }
        Set<g> set = this.e;
        if (set == null ? jVar.e != null : !set.equals(jVar.e)) {
            return false;
        }
        Map<String, Set<g>> map = this.f;
        if (map != null) {
            return map.equals(jVar.f);
        }
        return jVar.f == null;
    }

    public int hashCode() {
        List<k> list = this.a;
        int iHashCode = (((list != null ? list.hashCode() : 0) * 31) + this.c) * 31;
        Uri uri = this.d;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        Set<g> set = this.e;
        int iHashCode3 = (iHashCode2 + (set != null ? set.hashCode() : 0)) * 31;
        Map<String, Set<g>> map = this.f;
        return iHashCode3 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "VastVideoCreative{videoFiles=" + this.a + ", durationSeconds=" + this.c + ", destinationUri=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
