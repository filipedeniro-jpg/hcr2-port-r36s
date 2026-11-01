package com.applovin.impl.mediation.ads.a;

import com.applovin.impl.mediation.f;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.mediation.MaxAdListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    protected final String adUnitId;
    protected final n logger;
    protected final h sdk;
    protected final String tag;
    protected MaxAdListener adListener = null;
    protected final f.a loadRequestBuilder = new f.a();

    protected a(String str, String str2, h hVar) {
        this.adUnitId = str;
        this.sdk = hVar;
        this.tag = str2;
        this.logger = hVar.w();
    }

    public String getAdUnitId() {
        return this.adUnitId;
    }

    public void setExtraParameter(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        this.loadRequestBuilder.a(str, str2);
    }

    public void setListener(MaxAdListener maxAdListener) {
        this.adListener = maxAdListener;
    }
}
