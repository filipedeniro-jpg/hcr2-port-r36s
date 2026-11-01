package com.chartboost.sdk.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class al {
    private final com.chartboost.sdk.Libraries.f a;
    private final Map<String, com.chartboost.sdk.Libraries.h.a> b = new HashMap();

    public al(com.chartboost.sdk.Libraries.f fVar) {
        this.a = fVar;
    }

    public com.chartboost.sdk.Libraries.h.a a(String str) {
        if (b(str)) {
            if (this.b.containsKey(str)) {
                return this.b.get(str);
            }
            com.chartboost.sdk.Libraries.h.a aVar = new com.chartboost.sdk.Libraries.h.a(str, new File(this.a.d().d, String.format("%s%s", str, ".png")), this.a);
            this.b.put(str, aVar);
            return aVar;
        }
        if (!this.b.containsKey(str)) {
            return null;
        }
        this.b.remove(str);
        return null;
    }

    private boolean b(String str) {
        return this.a.b(String.format("%s%s", str, ".png"));
    }
}
