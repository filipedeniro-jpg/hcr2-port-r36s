package com.applovin.impl.mediation.b;

import android.os.SystemClock;
import com.applovin.impl.mediation.h;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends e implements MaxAd {
    protected h a;
    private final AtomicBoolean c;

    protected a(JSONObject jSONObject, JSONObject jSONObject2, h hVar, com.applovin.impl.sdk.h hVar2) {
        super(jSONObject, jSONObject2, hVar2);
        this.c = new AtomicBoolean();
        this.a = hVar;
    }

    private long i() {
        return b("load_started_time_ms", 0L);
    }

    public abstract a a(h hVar);

    public boolean a() {
        return b("is_backup", (Boolean) false);
    }

    public h b() {
        return this.a;
    }

    public String c() {
        return b("bid_response", (String) null);
    }

    public String d() {
        return b("third_party_ad_placement_id", (String) null);
    }

    public long e() {
        if (i() > 0) {
            return SystemClock.elapsedRealtime() - i();
        }
        return -1L;
    }

    public void f() {
        c("load_started_time_ms", SystemClock.elapsedRealtime());
    }

    public AtomicBoolean g() {
        return this.c;
    }

    @Override // com.applovin.mediation.MaxAd
    public String getAdUnitId() {
        return a("ad_unit_id", (String) null);
    }

    @Override // com.applovin.mediation.MaxAd
    public MaxAdFormat getFormat() {
        return n.c(a("ad_format", (String) null));
    }

    public void h() {
        this.a = null;
    }

    @Override // com.applovin.mediation.MaxAd
    public boolean isReady() {
        h hVar = this.a;
        return hVar != null && hVar.c() && this.a.d();
    }
}
