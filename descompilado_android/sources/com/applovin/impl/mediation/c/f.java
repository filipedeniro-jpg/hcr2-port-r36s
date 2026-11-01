package com.applovin.impl.mediation.c;

import android.app.Activity;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.g;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxErrorCodes;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.applovin.impl.sdk.d.a {
    private final String a;
    private final MaxAdFormat c;
    private final JSONObject d;
    private final JSONArray e;
    private final com.applovin.impl.mediation.f f;
    private final MaxAdListener g;
    private final Activity h;
    private final AtomicBoolean i;
    private final com.applovin.impl.mediation.e j;
    private final Object k;
    private a l;
    private int m;

    private enum a {
        BACKUP_AD_STATE_NOT_NEEDED,
        BACKUP_AD_STATE_LOADING,
        BACKUP_AD_STATE_WAITING_FOR_RESPONSE,
        BACKUP_AD_STATE_LOADED,
        BACKUP_AD_STATE_FAILED
    }

    private class b extends com.applovin.impl.sdk.d.a {
        private final JSONArray c;
        private final int d;

        b(int i, JSONArray jSONArray) {
            super("TaskProcessNextWaterfallAd", f.this.b);
            if (i >= 0 && i < jSONArray.length()) {
                this.c = jSONArray;
                this.d = i;
            } else {
                throw new IllegalArgumentException("Invalid ad index specified: " + i);
            }
        }

        private String a(int i) {
            if (i >= 0 && i < this.c.length()) {
                try {
                    return g.a(this.c.getJSONObject(i), "type", "undefined", this.b);
                } catch (JSONException unused) {
                    d("Unable to parse next ad from the ad response");
                }
            }
            return "undefined";
        }

        private void b() throws JSONException {
            f.this.m = this.d;
            JSONObject jSONObject = this.c.getJSONObject(this.d);
            if (f.b(jSONObject)) {
                g();
                return;
            }
            String strA = a(this.d);
            if ("adapter".equalsIgnoreCase(strA)) {
                a("Starting task for adapter ad...");
                this.b.F().a(new e(f.this.a, jSONObject, f.this.d, this.b, f.this.h, new com.applovin.impl.mediation.d.a(f.this.g, this.b) { // from class: com.applovin.impl.mediation.c.f.b.1
                    @Override // com.applovin.impl.mediation.d.a, com.applovin.mediation.MaxAdListener
                    public void onAdLoadFailed(String str, int i) {
                        b.this.h();
                    }

                    @Override // com.applovin.impl.mediation.d.a, com.applovin.mediation.MaxAdListener
                    public void onAdLoaded(MaxAd maxAd) {
                        f.this.a(maxAd);
                    }
                }));
                return;
            }
            d("Unable to process ad of unknown type: " + strA);
            f.this.a(-800);
        }

        private void g() {
            String str;
            a aVarA = f.this.a(a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE);
            if (aVarA == a.BACKUP_AD_STATE_LOADING) {
                return;
            }
            if (aVarA == a.BACKUP_AD_STATE_LOADED) {
                if (f.this.j.b(f.this.h)) {
                    b("Backup ad was promoted to primary");
                    return;
                }
                str = "Failed to promote backup ad to primary: nothing promoted";
            } else {
                if (aVarA == a.BACKUP_AD_STATE_FAILED) {
                    h();
                    return;
                }
                str = "Unknown state of loading the backup ad: " + aVarA;
            }
            d(str);
            f.this.a(-5201);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h() {
            if (f.this.j.c()) {
                c("Not loading next waterfall ad because returned ad was already displayed");
                return;
            }
            if (this.d >= this.c.length() - 1) {
                f.this.i();
                return;
            }
            b("Attempting to load next ad (" + this.d + ") after failure...");
            this.b.F().a(f.this.new b(this.d + 1, this.c), com.applovin.impl.mediation.d.c.a(f.this.c, ((Boolean) this.b.a(com.applovin.impl.sdk.b.a.G)).booleanValue() ? q.a.MAIN : q.a.BACKGROUND, this.b));
        }

        @Override // com.applovin.impl.sdk.d.a
        public i a() {
            return i.E;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b();
            } catch (Throwable th) {
                a("Encountered error while processing ad number " + this.d, th);
                this.b.H().a(a());
                f.this.i();
            }
        }
    }

    f(String str, MaxAdFormat maxAdFormat, JSONObject jSONObject, com.applovin.impl.mediation.f fVar, Activity activity, h hVar, MaxAdListener maxAdListener) {
        super("TaskProcessMediationWaterfall " + str, hVar);
        this.a = str;
        this.c = maxAdFormat;
        this.d = jSONObject;
        this.f = fVar;
        this.g = maxAdListener;
        this.h = activity;
        this.e = this.d.optJSONArray("ads");
        this.j = new com.applovin.impl.mediation.e(jSONObject, hVar);
        this.i = new AtomicBoolean();
        this.k = new Object();
        this.l = a.BACKUP_AD_STATE_NOT_NEEDED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a a(a aVar) {
        a aVar2;
        synchronized (this.k) {
            aVar2 = this.l;
            this.l = aVar;
            b("Backup ad state changed from " + aVar2 + " to " + aVar);
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        com.applovin.impl.sdk.c.h hVarG;
        com.applovin.impl.sdk.c.g gVar;
        if (i == 204) {
            hVarG = this.b.G();
            gVar = com.applovin.impl.sdk.c.g.s;
        } else if (i == -5001) {
            hVarG = this.b.G();
            gVar = com.applovin.impl.sdk.c.g.t;
        } else {
            hVarG = this.b.G();
            gVar = com.applovin.impl.sdk.c.g.u;
        }
        hVarG.a(gVar);
        if (this.i.compareAndSet(false, true)) {
            b("Notifying parent of ad load failure...");
            com.applovin.impl.sdk.utils.h.a(this.g, this.a, i, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MaxAd maxAd) {
        if (!(maxAd instanceof com.applovin.impl.mediation.b.a)) {
            a(-5201);
        } else {
            this.j.a((com.applovin.impl.mediation.b.a) maxAd);
            h();
        }
    }

    private void b() throws JSONException {
        JSONObject jSONObject;
        int i = 0;
        while (true) {
            if (i >= this.e.length()) {
                jSONObject = null;
                break;
            }
            jSONObject = this.e.getJSONObject(i);
            if (b(jSONObject)) {
                break;
            } else {
                i++;
            }
        }
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 != null) {
            b("Loading backup ad...");
            a(a.BACKUP_AD_STATE_LOADING);
            this.b.F().a(new e(this.a, jSONObject2, this.d, this.b, this.h, new com.applovin.impl.mediation.d.a(this.g, this.b) { // from class: com.applovin.impl.mediation.c.f.1
                @Override // com.applovin.impl.mediation.d.a, com.applovin.mediation.MaxAdListener
                public void onAdLoadFailed(String str, int i2) {
                    f.this.g();
                }

                @Override // com.applovin.impl.mediation.d.a, com.applovin.mediation.MaxAdListener
                public void onAdLoaded(MaxAd maxAd) {
                    f.this.b(maxAd);
                }
            }), q.a.MEDIATION_BACKUP);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MaxAd maxAd) {
        if (!(maxAd instanceof com.applovin.impl.mediation.b.a)) {
            a(-5201);
            return;
        }
        b("Backup ad loaded");
        com.applovin.impl.mediation.b.a aVar = (com.applovin.impl.mediation.b.a) maxAd;
        if (a(a.BACKUP_AD_STATE_LOADED) == a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE) {
            this.b.a(this.h).maybeScheduleBackupAdPromotedToPrimaryPostback(aVar);
            this.j.a(aVar);
        } else {
            this.j.b(aVar);
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(JSONObject jSONObject) {
        return jSONObject.optBoolean("is_backup");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        d("Backup ad failed to load...");
        if (a(a.BACKUP_AD_STATE_FAILED) == a.BACKUP_AD_STATE_WAITING_FOR_RESPONSE) {
            new b(this.m, this.e).h();
        }
    }

    private void h() {
        if (this.i.compareAndSet(false, true)) {
            b("Notifying parent of ad load success...");
            com.applovin.impl.sdk.utils.h.a(this.g, this.j, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        a(MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED);
    }

    private boolean j() {
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.a.S)).booleanValue()) {
            return true;
        }
        MaxAdFormat maxAdFormat = this.c;
        MaxAdFormat maxAdFormatC = n.c(g.a(this.d, "ad_format", (String) null, this.b));
        boolean zA = com.applovin.impl.mediation.d.c.a(maxAdFormat, maxAdFormatC);
        if (!zA) {
            d("Requested ad format: " + maxAdFormat + ", is not equal to ad response format: " + maxAdFormatC);
        }
        return zA;
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.D;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a("Processing ad response...");
            int length = this.e != null ? this.e.length() : 0;
            if (length <= 0) {
                c("No ads were returned from the server");
                n.a(this.a, this.d, this.b);
                a(204);
            } else {
                if (!j()) {
                    a(-800);
                    return;
                }
                b();
                a("Loading the first out of " + length + " ads...");
                this.b.F().a(new b(0, this.e));
            }
        } catch (Throwable th) {
            a("Encountered error while processing ad response", th);
            i();
            this.b.H().a(a());
        }
    }
}
