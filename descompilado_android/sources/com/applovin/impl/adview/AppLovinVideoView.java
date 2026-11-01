package com.applovin.impl.adview;

import android.content.Context;
import android.widget.VideoView;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinVideoView extends VideoView implements q {
    private final com.applovin.impl.sdk.h a;
    private int b;
    private int c;
    private float d;

    public AppLovinVideoView(Context context, com.applovin.impl.sdk.h hVar) {
        super(context, null, 0);
        this.b = 0;
        this.c = 0;
        this.d = 0.0f;
        this.a = hVar;
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.b <= 0 || this.c <= 0 || ((Boolean) this.a.a(com.applovin.impl.sdk.b.b.fd)).booleanValue()) {
            super.onMeasure(i, i2);
            return;
        }
        int defaultSize = getDefaultSize(this.b, i);
        int defaultSize2 = getDefaultSize(this.c, i2);
        int i3 = (int) (defaultSize / this.d);
        if (defaultSize2 > i3) {
            defaultSize2 = i3;
        }
        int i4 = (int) (defaultSize2 * this.d);
        if (defaultSize > i4) {
            defaultSize = i4;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    @Override // com.applovin.impl.adview.q
    public void setVideoSize(int i, int i2) {
        this.b = i;
        this.c = i2;
        this.d = this.b / this.c;
        try {
            getHolder().setFixedSize(i, i2);
            requestLayout();
            invalidate();
        } catch (Exception unused) {
        }
    }
}
