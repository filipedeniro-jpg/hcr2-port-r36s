package com.applovin.impl.mediation.c;

import android.app.Activity;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxErrorCodes;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.applovin.impl.sdk.d.a {
    private final String a;
    private final JSONObject c;
    private final JSONObject d;
    private final MaxAdListener e;
    private final Activity f;

    e(String str, JSONObject jSONObject, JSONObject jSONObject2, h hVar, Activity activity, MaxAdListener maxAdListener) {
        super("TaskLoadAdapterAd " + str, hVar);
        this.a = str;
        this.c = jSONObject;
        this.d = jSONObject2;
        this.f = activity;
        this.e = maxAdListener;
    }

    private com.applovin.impl.mediation.b.a b() throws JSONException {
        String string = this.d.getString("ad_format");
        MaxAdFormat maxAdFormatC = n.c(string);
        if (maxAdFormatC == MaxAdFormat.BANNER || maxAdFormatC == MaxAdFormat.MREC || maxAdFormatC == MaxAdFormat.LEADER) {
            return new com.applovin.impl.mediation.b.b(this.c, this.d, this.b);
        }
        if (maxAdFormatC == MaxAdFormat.NATIVE) {
            return new com.applovin.impl.mediation.b.d(this.c, this.d, this.b);
        }
        if (maxAdFormatC == MaxAdFormat.INTERSTITIAL || maxAdFormatC == MaxAdFormat.REWARDED) {
            return new com.applovin.impl.mediation.b.c(this.c, this.d, this.b);
        }
        throw new IllegalArgumentException("Unsupported ad format: " + string);
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.C;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.b.a(this.f).loadThirdPartyMediatedAd(this.a, b(), this.f, this.e);
        } catch (Throwable th) {
            a("Unable to process adapter ad", th);
            this.b.H().a(a());
            com.applovin.impl.sdk.utils.h.a(this.e, this.a, MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, this.b);
        }
    }
}
