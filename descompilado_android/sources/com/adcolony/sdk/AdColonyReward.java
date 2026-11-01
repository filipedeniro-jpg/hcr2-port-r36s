package com.adcolony.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyReward {
    private int a;
    private String b;
    private String c;
    private boolean d;

    AdColonyReward(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.a = y.c(jSONObjectC, "reward_amount");
        this.b = y.b(jSONObjectC, "reward_name");
        this.d = y.d(jSONObjectC, "success");
        this.c = y.b(jSONObjectC, "zone_id");
    }

    public int getRewardAmount() {
        return this.a;
    }

    public String getRewardName() {
        return this.b;
    }

    public String getZoneID() {
        return this.c;
    }

    public boolean success() {
        return this.d;
    }
}
