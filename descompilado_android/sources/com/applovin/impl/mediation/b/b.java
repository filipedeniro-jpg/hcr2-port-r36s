package com.applovin.impl.mediation.b;

import android.view.View;
import com.applovin.impl.mediation.h;
import com.applovin.mediation.MaxAdFormat;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends a {
    private b(b bVar, h hVar) {
        super(bVar.t(), bVar.s(), hVar, bVar.b);
    }

    public b(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.h hVar) {
        super(jSONObject, jSONObject2, null, hVar);
    }

    @Override // com.applovin.impl.mediation.b.a
    public a a(h hVar) {
        return new b(this, hVar);
    }

    public int i() {
        return a("ad_view_width", ((Integer) this.b.a(com.applovin.impl.sdk.b.a.u)).intValue());
    }

    public int j() {
        return a("ad_view_height", ((Integer) this.b.a(com.applovin.impl.sdk.b.a.v)).intValue());
    }

    public View k() {
        if (!isReady() || this.a == null) {
            return null;
        }
        View viewA = this.a.a();
        if (viewA != null) {
            return viewA;
        }
        throw new IllegalStateException("Ad-view based ad is missing an ad view");
    }

    public long l() {
        return b("viewability_imp_delay_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.b.cn)).longValue());
    }

    public int m() {
        com.applovin.impl.sdk.b.b<Integer> bVar;
        if (getFormat() == MaxAdFormat.BANNER) {
            bVar = com.applovin.impl.sdk.b.b.co;
        } else {
            bVar = getFormat() == MaxAdFormat.MREC ? com.applovin.impl.sdk.b.b.cq : com.applovin.impl.sdk.b.b.cs;
        }
        return a("viewability_min_width", ((Integer) this.b.a(bVar)).intValue());
    }

    public int n() {
        com.applovin.impl.sdk.b.b<Integer> bVar;
        if (getFormat() == MaxAdFormat.BANNER) {
            bVar = com.applovin.impl.sdk.b.b.cp;
        } else {
            bVar = getFormat() == MaxAdFormat.MREC ? com.applovin.impl.sdk.b.b.cr : com.applovin.impl.sdk.b.b.ct;
        }
        return a("viewability_min_height", ((Integer) this.b.a(bVar)).intValue());
    }

    public float o() {
        return a("viewability_min_alpha", ((Float) this.b.a(com.applovin.impl.sdk.b.b.cu)).floatValue() / 100.0f);
    }

    public int p() {
        return a("viewability_min_pixels", -1);
    }

    public boolean q() {
        return p() >= 0;
    }

    public long r() {
        return b("viewability_timer_min_visible_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.b.cv)).longValue());
    }

    @Override // com.applovin.impl.mediation.b.e
    public String toString() {
        return "MediatedAdViewAd{width=" + i() + ", height=" + j() + ", format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + u() + "', adapterName='" + v() + "', isTesting=" + w() + ", isRefreshEnabled=" + A() + ", getAdRefreshMillis=" + B() + '}';
    }
}
