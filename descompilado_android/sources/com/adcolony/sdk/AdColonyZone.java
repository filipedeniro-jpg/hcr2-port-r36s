package com.adcolony.sdk;

import androidx.annotation.NonNull;
import com.ironsource.sdk.constants.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyZone {
    public static final int BANNER = 1;
    public static final int INTERSTITIAL = 0;
    public static final int NATIVE = 2;
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    static final int f = 5;
    static final int g = 6;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l = 5;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;

    AdColonyZone(@NonNull String str) {
        this.h = str;
    }

    private int c(int i) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return i;
        }
        c();
        return 0;
    }

    private boolean a(boolean z) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return z;
        }
        c();
        return false;
    }

    private String a(String str) {
        return a(str, "");
    }

    private String a(String str, String str2) {
        if (a.b() && !a.a().g() && !a.a().h()) {
            return str;
        }
        c();
        return str2;
    }

    public String getZoneID() {
        return a(this.h);
    }

    public int getRemainingViewsUntilReward() {
        return c(this.n);
    }

    public int getRewardAmount() {
        return c(this.q);
    }

    public String getRewardName() {
        return a(this.i);
    }

    public int getViewsPerReward() {
        return c(this.o);
    }

    public int getZoneType() {
        return this.m;
    }

    boolean a() {
        return this.l == 0;
    }

    public boolean isValid() {
        return a(this.s);
    }

    public boolean isRewarded() {
        return this.t;
    }

    public int getPlayFrequency() {
        return c(this.p);
    }

    void a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        JSONObject jSONObjectF = y.f(jSONObjectC, "reward");
        this.i = y.b(jSONObjectF, "reward_name");
        this.q = y.c(jSONObjectF, "reward_amount");
        this.o = y.c(jSONObjectF, "views_per_reward");
        this.n = y.c(jSONObjectF, "views_until_reward");
        this.j = y.b(jSONObjectF, "reward_name_plural");
        this.k = y.b(jSONObjectF, "reward_prompt");
        this.t = y.d(jSONObjectC, Constants.CONVERT_REWARDED);
        this.l = y.c(jSONObjectC, "status");
        this.m = y.c(jSONObjectC, "type");
        this.p = y.c(jSONObjectC, "play_interval");
        this.h = y.b(jSONObjectC, "zone_id");
        this.s = this.l != 1;
    }

    void a(int i) {
        this.r = i;
    }

    int b() {
        return this.r;
    }

    void b(int i) {
        this.l = i;
    }

    private void c() {
        new aa.a().a("The AdColonyZone API is not available while AdColony is disabled.").a(aa.g);
    }
}
