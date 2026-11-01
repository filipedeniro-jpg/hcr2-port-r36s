package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends View {
    protected final com.applovin.impl.sdk.h a;
    protected final Context b;

    public enum a {
        WhiteXOnOpaqueBlack,
        WhiteXOnTransparentGrey,
        Invisible
    }

    f(com.applovin.impl.sdk.h hVar, Context context) {
        super(context);
        this.b = context;
        this.a = hVar;
    }

    public static f a(com.applovin.impl.sdk.h hVar, Context context, a aVar) {
        if (aVar.equals(a.Invisible)) {
            return new l(hVar, context);
        }
        return aVar.equals(a.WhiteXOnTransparentGrey) ? new n(hVar, context) : new u(hVar, context);
    }

    public abstract void a(int i);

    public abstract a getStyle();

    public abstract float getViewScale();

    public abstract void setViewScale(float f);
}
