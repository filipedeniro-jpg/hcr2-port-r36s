package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ab extends z {
    private final com.applovin.impl.sdk.ad.g a;
    private final AppLovinAdRewardListener c;
    private final Object d;
    private volatile boolean e;

    public ab(com.applovin.impl.sdk.ad.g gVar, AppLovinAdRewardListener appLovinAdRewardListener, com.applovin.impl.sdk.h hVar) {
        super("TaskValidateReward", hVar);
        this.d = new Object();
        this.e = false;
        this.a = gVar;
        this.c = appLovinAdRewardListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        String str;
        if (b()) {
            return;
        }
        if (i < 400 || i > 500) {
            this.c.validationRequestFailed(this.a, i);
            str = "network_timeout";
        } else {
            this.c.userRewardRejected(this.a, new HashMap(0));
            str = "rejected";
        }
        com.applovin.impl.sdk.a.e.a().a(this.a, str);
    }

    private void a(String str, Map<String, String> map) {
        if (b()) {
            return;
        }
        com.applovin.impl.sdk.a.e eVarA = com.applovin.impl.sdk.a.e.a();
        eVarA.a(this.a, str);
        eVarA.a(this.a, map);
        if (str.equals("accepted")) {
            this.c.userRewardVerified(this.a, map);
            return;
        }
        if (str.equals("quota_exceeded")) {
            this.c.userOverQuota(this.a, map);
        } else if (str.equals("rejected")) {
            this.c.userRewardRejected(this.a, map);
        } else {
            this.c.validationRequestFailed(this.a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        Map<String, String> map;
        String string;
        if (b()) {
            return;
        }
        try {
            JSONObject jSONObjectA = com.applovin.impl.sdk.utils.f.a(jSONObject);
            com.applovin.impl.sdk.utils.f.d(jSONObjectA, this.b);
            com.applovin.impl.sdk.utils.f.c(jSONObject, this.b);
            try {
                map = com.applovin.impl.sdk.utils.g.a((JSONObject) jSONObjectA.get("params"));
            } catch (Throwable unused) {
                map = new HashMap<>(0);
            }
            try {
                string = jSONObjectA.getString(IronSourceConstants.EVENTS_RESULT);
            } catch (Throwable unused2) {
                string = "network_timeout";
            }
            a(string, map);
        } catch (JSONException e) {
            a("Unable to parse API response", e);
        }
    }

    private boolean b() {
        boolean z;
        synchronized (this.d) {
            z = this.e;
        }
        return z;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.y;
    }

    public void a(boolean z) {
        synchronized (this.d) {
            this.e = z;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        String clCode = this.a.getClCode();
        HashMap map = new HashMap(3);
        map.put("zone_id", this.a.getAdZone().a());
        if (!com.applovin.impl.sdk.utils.k.b(clCode)) {
            clCode = "NO_CLCODE";
        }
        map.put("clcode", clCode);
        String strL = this.b.l();
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ef)).booleanValue() && com.applovin.impl.sdk.utils.k.b(strL)) {
            map.put("cuid", strL);
        }
        a("2.0/vr", new JSONObject(map), ((Integer) this.b.a(com.applovin.impl.sdk.b.b.bH)).intValue(), new com.applovin.impl.sdk.network.a.b<JSONObject>() { // from class: com.applovin.impl.sdk.d.ab.1
            @Override // com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                ab.this.a(i);
            }

            @Override // com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject, int i) {
                ab.this.a(jSONObject);
            }
        });
    }
}
