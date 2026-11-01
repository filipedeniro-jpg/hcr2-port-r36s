package com.applovin.impl.mediation;

import android.app.Activity;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e implements MaxAd {
    private final JSONObject a;
    private final com.applovin.impl.sdk.h b;
    private final Object c = new Object();
    private boolean d;
    private boolean e;
    private com.applovin.impl.mediation.b.a f;
    private com.applovin.impl.mediation.b.a g;
    private com.applovin.impl.mediation.b.a h;

    public e(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        this.a = jSONObject;
        this.b = hVar;
    }

    public com.applovin.impl.mediation.b.a a() {
        return this.h;
    }

    public com.applovin.impl.mediation.b.a a(Activity activity) {
        boolean z;
        com.applovin.impl.mediation.b.a aVar;
        synchronized (this.c) {
            if (this.d) {
                throw new IllegalStateException("Ad with backup was destroyed");
            }
            z = true;
            this.d = true;
            if (this.f != null) {
                aVar = this.f;
                z = false;
            } else {
                if (this.g == null) {
                    throw new IllegalStateException("Ad with backup does not have either primary or backup ad to resolve");
                }
                aVar = this.g;
            }
        }
        if (z) {
            this.b.a(activity).a(aVar);
        }
        this.h = aVar;
        return aVar;
    }

    public void a(com.applovin.impl.mediation.b.a aVar) {
        synchronized (this.c) {
            if (!this.e) {
                this.f = aVar;
            }
        }
    }

    public List<com.applovin.impl.mediation.b.a> b() {
        ArrayList arrayList;
        synchronized (this.c) {
            this.e = true;
            arrayList = new ArrayList(2);
            if (this.f != null) {
                arrayList.add(this.f);
                this.f = null;
            }
            if (this.g != null) {
                arrayList.add(this.g);
                this.g = null;
            }
        }
        return arrayList;
    }

    public void b(com.applovin.impl.mediation.b.a aVar) {
        synchronized (this.c) {
            if (!this.e) {
                this.g = aVar;
            }
        }
    }

    public boolean b(Activity activity) {
        com.applovin.impl.mediation.b.a aVar;
        synchronized (this.c) {
            aVar = null;
            if (this.g != null) {
                this.f = this.g;
                this.g = null;
                aVar = this.f;
            }
        }
        if (aVar != null) {
            this.b.a(activity).maybeScheduleBackupAdPromotedToPrimaryPostback(aVar);
        }
        return aVar != null;
    }

    public boolean c() {
        boolean z;
        synchronized (this.c) {
            z = this.e || this.d;
        }
        return z;
    }

    public long d() {
        return com.applovin.impl.sdk.utils.g.a(this.a, "ad_expiration_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.M)).longValue(), this.b);
    }

    @Override // com.applovin.mediation.MaxAd
    public String getAdUnitId() {
        return com.applovin.impl.sdk.utils.g.a(this.a, "ad_unit_id", (String) null, this.b);
    }

    @Override // com.applovin.mediation.MaxAd
    public MaxAdFormat getFormat() {
        return n.c(com.applovin.impl.sdk.utils.g.a(this.a, "ad_format", (String) null, this.b));
    }

    @Override // com.applovin.mediation.MaxAd
    public boolean isReady() {
        synchronized (this.c) {
            if (this.f == null && this.g == null) {
                return false;
            }
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediatedAdWithBackup{isReady=");
        sb.append(isReady());
        sb.append(", format=");
        sb.append(getFormat());
        sb.append(", adUnitId='");
        sb.append(getAdUnitId());
        sb.append("', hasAd=");
        sb.append(this.f != null);
        sb.append(", hasBackup=");
        sb.append(this.g != null);
        sb.append('}');
        return sb.toString();
    }
}
