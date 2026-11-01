package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class k extends ad<Void> {
    final j a;
    private final l k;
    private final ai l;

    k(l lVar, ai aiVar, j jVar, File file) {
        super(HttpRequest.METHOD_GET, jVar.c, 2, file);
        this.j = 1;
        this.k = lVar;
        this.l = aiVar;
        this.a = jVar;
    }

    @Override // com.chartboost.sdk.impl.ad
    public ae a() {
        HashMap map = new HashMap();
        map.put("X-Chartboost-App", com.chartboost.sdk.i.k);
        map.put("X-Chartboost-Client", CBUtility.b());
        map.put("X-Chartboost-Reachability", Integer.toString(this.l.a()));
        return new ae(map, null, null);
    }

    @Override // com.chartboost.sdk.impl.ad
    public void a(Void r1, ag agVar) {
        this.k.a(this, null, null);
    }

    @Override // com.chartboost.sdk.impl.ad
    public void a(CBError cBError, ag agVar) {
        this.k.a(this, cBError, agVar);
    }
}
