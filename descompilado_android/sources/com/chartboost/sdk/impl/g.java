package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g implements aj.a {
    private final e a;
    private final String b;

    public g(e eVar, String str) {
        this.a = eVar;
        this.b = str;
    }

    @Override // com.chartboost.sdk.impl.aj.a
    public void a(aj ajVar, JSONObject jSONObject) {
        if (this.a.f.h || com.chartboost.sdk.i.t) {
            synchronized (this.a) {
                this.a.b(this.b);
            }
        }
    }

    @Override // com.chartboost.sdk.impl.aj.a
    public void a(aj ajVar, CBError cBError) {
        if (this.a.f.h) {
            synchronized (this.a) {
                this.a.b(this.b);
            }
        }
    }
}
