package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.d.j;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.h;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;

/* JADX INFO: loaded from: classes.dex */
public class PostbackServiceImpl implements AppLovinPostbackService {
    private final h a;

    public PostbackServiceImpl(h hVar) {
        this.a = hVar;
    }

    @Override // com.applovin.sdk.AppLovinPostbackService
    public void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackRequest(f.b(this.a).a(str).a(false).a(), appLovinPostbackListener);
    }

    public void dispatchPostbackRequest(f fVar, q.a aVar, AppLovinPostbackListener appLovinPostbackListener) {
        this.a.F().a(new j(fVar, aVar, this.a, appLovinPostbackListener), aVar);
    }

    public void dispatchPostbackRequest(f fVar, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackRequest(fVar, q.a.POSTBACKS, appLovinPostbackListener);
    }

    public String toString() {
        return "PostbackService{}";
    }
}
