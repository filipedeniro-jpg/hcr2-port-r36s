package com.facebook.ads.internal.api;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@Keep
public abstract class AdNativeComponentView extends RelativeLayout {
    public abstract View getAdContentsView();

    public AdNativeComponentView(Context context) {
        super(context);
    }

    public AdNativeComponentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AdNativeComponentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public AdNativeComponentView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
