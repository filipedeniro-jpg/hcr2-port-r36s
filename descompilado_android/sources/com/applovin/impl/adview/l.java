package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class l extends f {
    private float c;

    public l(com.applovin.impl.sdk.h hVar, Context context) {
        super(hVar, context);
        this.c = 1.0f;
    }

    @Override // com.applovin.impl.adview.f
    public void a(int i) {
        setViewScale(i / 30.0f);
    }

    @Override // com.applovin.impl.adview.f
    public f.a getStyle() {
        return f.a.Invisible;
    }

    @Override // com.applovin.impl.adview.f
    public float getViewScale() {
        return this.c;
    }

    @Override // com.applovin.impl.adview.f
    public void setViewScale(float f) {
        this.c = f;
    }
}
