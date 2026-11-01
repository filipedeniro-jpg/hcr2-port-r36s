package com.applovin.impl.a;

import android.net.Uri;
import com.applovin.impl.sdk.utils.n;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.applovin.impl.sdk.ad.g {
    private final String a;
    private final String b;
    private final f c;
    private final long d;
    private final j e;
    private final com.applovin.impl.a.b f;
    private final Set<g> g;
    private final Set<g> h;

    /* JADX INFO: renamed from: com.applovin.impl.a.a$a, reason: collision with other inner class name */
    public static class C0012a {
        private JSONObject a;
        private JSONObject b;
        private com.applovin.impl.sdk.ad.b c;
        private com.applovin.impl.sdk.h d;
        private long e;
        private String f;
        private String g;
        private f h;
        private j i;
        private com.applovin.impl.a.b j;
        private Set<g> k;
        private Set<g> l;

        private C0012a() {
        }

        public C0012a a(long j) {
            this.e = j;
            return this;
        }

        public C0012a a(com.applovin.impl.a.b bVar) {
            this.j = bVar;
            return this;
        }

        public C0012a a(f fVar) {
            this.h = fVar;
            return this;
        }

        public C0012a a(j jVar) {
            this.i = jVar;
            return this;
        }

        public C0012a a(com.applovin.impl.sdk.ad.b bVar) {
            this.c = bVar;
            return this;
        }

        public C0012a a(com.applovin.impl.sdk.h hVar) {
            if (hVar == null) {
                throw new IllegalArgumentException("No sdk specified.");
            }
            this.d = hVar;
            return this;
        }

        public C0012a a(String str) {
            this.f = str;
            return this;
        }

        public C0012a a(Set<g> set) {
            this.k = set;
            return this;
        }

        public C0012a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                throw new IllegalArgumentException("No ad object specified.");
            }
            this.a = jSONObject;
            return this;
        }

        public a a() {
            return new a(this);
        }

        public C0012a b(String str) {
            this.g = str;
            return this;
        }

        public C0012a b(Set<g> set) {
            this.l = set;
            return this;
        }

        public C0012a b(JSONObject jSONObject) {
            if (jSONObject == null) {
                throw new IllegalArgumentException("No full ad response specified.");
            }
            this.b = jSONObject;
            return this;
        }
    }

    private enum b {
        VIDEO,
        COMPANION_AD
    }

    public enum c {
        IMPRESSION,
        VIDEO_CLICK,
        COMPANION_CLICK,
        VIDEO,
        COMPANION,
        ERROR
    }

    private a(C0012a c0012a) {
        super(c0012a.a, c0012a.b, c0012a.c, c0012a.d);
        this.a = c0012a.f;
        this.c = c0012a.h;
        this.b = c0012a.g;
        this.e = c0012a.i;
        this.f = c0012a.j;
        this.g = c0012a.k;
        this.h = c0012a.l;
        this.d = c0012a.e;
    }

    private Set<g> a(b bVar, String[] strArr) {
        com.applovin.impl.a.b bVar2;
        j jVar;
        if (strArr == null || strArr.length <= 0) {
            return Collections.emptySet();
        }
        Map<String, Set<g>> mapD = null;
        if (bVar == b.VIDEO && (jVar = this.e) != null) {
            mapD = jVar.e();
        } else if (bVar == b.COMPANION_AD && (bVar2 = this.f) != null) {
            mapD = bVar2.d();
        }
        HashSet hashSet = new HashSet();
        if (mapD != null && !mapD.isEmpty()) {
            for (String str : strArr) {
                if (mapD.containsKey(str)) {
                    hashSet.addAll(mapD.get(str));
                }
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static C0012a aB() {
        return new C0012a();
    }

    private String aC() {
        String stringFromAdObject = getStringFromAdObject("vimp_url", null);
        if (stringFromAdObject != null) {
            return stringFromAdObject.replace("{CLCODE}", getClCode());
        }
        return null;
    }

    private j.a aD() {
        j.a[] aVarArrValues = j.a.values();
        int iIntValue = ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.eR)).intValue();
        return (iIntValue < 0 || iIntValue >= aVarArrValues.length) ? j.a.UNSPECIFIED : aVarArrValues[iIntValue];
    }

    private Set<g> aE() {
        j jVar = this.e;
        return jVar != null ? jVar.d() : Collections.emptySet();
    }

    private Set<g> aF() {
        com.applovin.impl.a.b bVar = this.f;
        return bVar != null ? bVar.c() : Collections.emptySet();
    }

    public j a() {
        return this.e;
    }

    public Set<g> a(c cVar, String str) {
        return a(cVar, new String[]{str});
    }

    public Set<g> a(c cVar, String[] strArr) {
        this.sdk.w().a("VastAd", "Retrieving trackers of type '" + cVar + "' and events '" + strArr + "'...");
        if (cVar == c.IMPRESSION) {
            return this.g;
        }
        if (cVar == c.VIDEO_CLICK) {
            return aE();
        }
        if (cVar == c.COMPANION_CLICK) {
            return aF();
        }
        if (cVar == c.VIDEO) {
            return a(b.VIDEO, strArr);
        }
        if (cVar == c.COMPANION) {
            return a(b.COMPANION_AD, strArr);
        }
        if (cVar == c.ERROR) {
            return this.h;
        }
        this.sdk.w().d("VastAd", "Failed to retrieve trackers of invalid type '" + cVar + "' and events '" + strArr + "'");
        return Collections.emptySet();
    }

    public void a(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("html_template", str);
            }
        } catch (Throwable unused) {
        }
    }

    public boolean aA() {
        return getBooleanFromAdObject("cache_video", true);
    }

    @Override // com.applovin.impl.sdk.ad.g
    public List<com.applovin.impl.sdk.c.a> an() {
        return n.a("vimp_urls", this.adObject, getClCode(), aC(), this.sdk);
    }

    @Override // com.applovin.impl.sdk.ad.g
    public boolean b() {
        k kVarC = c();
        return kVarC != null && kVarC.c();
    }

    public k c() {
        j jVar = this.e;
        if (jVar != null) {
            return jVar.a(aD());
        }
        return null;
    }

    public com.applovin.impl.a.b d() {
        return this.f;
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri e() {
        k kVarC = c();
        if (kVarC != null) {
            return kVarC.b();
        }
        return null;
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a) || !super.equals(obj)) {
            return false;
        }
        a aVar = (a) obj;
        String str = this.a;
        if (str == null ? aVar.a != null : !str.equals(aVar.a)) {
            return false;
        }
        String str2 = this.b;
        if (str2 == null ? aVar.b != null : !str2.equals(aVar.b)) {
            return false;
        }
        f fVar = this.c;
        if (fVar == null ? aVar.c != null : !fVar.equals(aVar.c)) {
            return false;
        }
        j jVar = this.e;
        if (jVar == null ? aVar.e != null : !jVar.equals(aVar.e)) {
            return false;
        }
        com.applovin.impl.a.b bVar = this.f;
        if (bVar == null ? aVar.f != null : !bVar.equals(aVar.f)) {
            return false;
        }
        Set<g> set = this.g;
        if (set == null ? aVar.g != null : !set.equals(aVar.g)) {
            return false;
        }
        Set<g> set2 = this.h;
        if (set2 != null) {
            return set2.equals(aVar.h);
        }
        return aVar.h == null;
    }

    public List<String> f() {
        return com.applovin.impl.sdk.utils.d.a(getStringFromAdObject("vast_resource_cache_prefix", null));
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri g() {
        j jVar = this.e;
        if (jVar != null) {
            return jVar.c();
        }
        return null;
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public long getCreatedAtMillis() {
        return this.d;
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri h() {
        return g();
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public boolean hasVideoUrl() {
        List<k> listA;
        j jVar = this.e;
        return (jVar == null || (listA = jVar.a()) == null || listA.size() <= 0) ? false : true;
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.a;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        f fVar = this.c;
        int iHashCode4 = (iHashCode3 + (fVar != null ? fVar.hashCode() : 0)) * 31;
        j jVar = this.e;
        int iHashCode5 = (iHashCode4 + (jVar != null ? jVar.hashCode() : 0)) * 31;
        com.applovin.impl.a.b bVar = this.f;
        int iHashCode6 = (iHashCode5 + (bVar != null ? bVar.hashCode() : 0)) * 31;
        Set<g> set = this.g;
        int iHashCode7 = (iHashCode6 + (set != null ? set.hashCode() : 0)) * 31;
        Set<g> set2 = this.h;
        return iHashCode7 + (set2 != null ? set2.hashCode() : 0);
    }

    public boolean i() {
        return getBooleanFromAdObject("vast_fire_click_trackers_on_html_clicks", false);
    }

    public String j() {
        return getStringFromAdObject("html_template", "");
    }

    public Uri k() {
        String stringFromAdObject = getStringFromAdObject("html_template_url", null);
        if (com.applovin.impl.sdk.utils.k.b(stringFromAdObject)) {
            return Uri.parse(stringFromAdObject);
        }
        return null;
    }

    public boolean l() {
        return getBooleanFromAdObject("cache_companion_ad", true);
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public String toString() {
        return "VastAd{title='" + this.a + "', adDescription='" + this.b + "', systemInfo=" + this.c + ", videoCreative=" + this.e + ", companionAd=" + this.f + ", impressionTrackers=" + this.g + ", errorTrackers=" + this.h + '}';
    }

    @Override // com.applovin.impl.sdk.ad.g
    public boolean w() {
        return g() != null;
    }
}
