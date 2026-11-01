package com.applovin.impl.a;

import android.net.Uri;
import com.applovin.impl.sdk.utils.o;
import com.my.target.bj;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private int a;
    private int b;
    private Uri c;
    private e d;
    private Set<g> e = new HashSet();
    private Map<String, Set<g>> f = new HashMap();

    private b() {
    }

    public static b a(o oVar, b bVar, c cVar, com.applovin.impl.sdk.h hVar) {
        o oVarB;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        if (bVar == null) {
            try {
                bVar = new b();
            } catch (Throwable th) {
                hVar.w().b("VastCompanionAd", "Error occurred while initializing", th);
                return null;
            }
        }
        if (bVar.a == 0 && bVar.b == 0) {
            int iA = com.applovin.impl.sdk.utils.k.a(oVar.b().get("width"));
            int iA2 = com.applovin.impl.sdk.utils.k.a(oVar.b().get("height"));
            if (iA > 0 && iA2 > 0) {
                bVar.a = iA;
                bVar.b = iA2;
            }
        }
        bVar.d = e.a(oVar, bVar.d, hVar);
        if (bVar.c == null && (oVarB = oVar.b(bj.gj)) != null) {
            String strC = oVarB.c();
            if (com.applovin.impl.sdk.utils.k.b(strC)) {
                bVar.c = Uri.parse(strC);
            }
        }
        i.a(oVar.a(bj.gk), bVar.e, cVar, hVar);
        i.a(oVar, bVar.f, cVar, hVar);
        return bVar;
    }

    public Uri a() {
        return this.c;
    }

    public e b() {
        return this.d;
    }

    public Set<g> c() {
        return this.e;
    }

    public Map<String, Set<g>> d() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != bVar.a || this.b != bVar.b) {
            return false;
        }
        Uri uri = this.c;
        if (uri == null ? bVar.c != null : !uri.equals(bVar.c)) {
            return false;
        }
        e eVar = this.d;
        if (eVar == null ? bVar.d != null : !eVar.equals(bVar.d)) {
            return false;
        }
        Set<g> set = this.e;
        if (set == null ? bVar.e != null : !set.equals(bVar.e)) {
            return false;
        }
        Map<String, Set<g>> map = this.f;
        if (map != null) {
            return map.equals(bVar.f);
        }
        return bVar.f == null;
    }

    public int hashCode() {
        int i = ((this.a * 31) + this.b) * 31;
        Uri uri = this.c;
        int iHashCode = (i + (uri != null ? uri.hashCode() : 0)) * 31;
        e eVar = this.d;
        int iHashCode2 = (iHashCode + (eVar != null ? eVar.hashCode() : 0)) * 31;
        Set<g> set = this.e;
        int iHashCode3 = (iHashCode2 + (set != null ? set.hashCode() : 0)) * 31;
        Map<String, Set<g>> map = this.f;
        return iHashCode3 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "VastCompanionAd{width=" + this.a + ", height=" + this.b + ", destinationUri=" + this.c + ", nonVideoResource=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
