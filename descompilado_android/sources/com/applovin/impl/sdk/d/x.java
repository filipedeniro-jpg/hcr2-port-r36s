package com.applovin.impl.sdk.d;

import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class x extends z {
    private final com.applovin.impl.sdk.ad.g a;

    public x(com.applovin.impl.sdk.ad.g gVar, com.applovin.impl.sdk.h hVar) {
        super("TaskReportReward", hVar);
        this.a = gVar;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.w;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strB = com.applovin.impl.sdk.a.e.a().b(this.a);
        if (strB == null) {
            d("No reward result was found for ad: " + this.a);
            return;
        }
        HashMap map = new HashMap(6);
        map.put(IronSourceConstants.EVENTS_RESULT, strB);
        map.put("zone_id", this.a.getAdZone().a());
        map.put("fire_percent", Integer.valueOf(this.a.ae()));
        String clCode = this.a.getClCode();
        if (!com.applovin.impl.sdk.utils.k.b(clCode)) {
            clCode = "NO_CLCODE";
        }
        map.put("clcode", clCode);
        String strL = this.b.l();
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ef)).booleanValue() && com.applovin.impl.sdk.utils.k.b(strL)) {
            map.put("cuid", strL);
        }
        Map<String, String> mapA = com.applovin.impl.sdk.a.e.a().a(this.a);
        if (mapA != null) {
            map.put("params", mapA);
        }
        a("2.0/cr", new JSONObject(map), ((Integer) this.b.a(com.applovin.impl.sdk.b.b.bI)).intValue(), new com.applovin.impl.sdk.network.a.b<JSONObject>() { // from class: com.applovin.impl.sdk.d.x.1
            @Override // com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                x.this.d("Failed to report reward for ad: " + x.this.a.getAdIdNumber() + " - error code: " + i);
            }

            @Override // com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject, int i) {
                x.this.a("Reported reward successfully for ad: " + x.this.a.getAdIdNumber());
            }
        });
    }
}
