package com.applovin.impl.adview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinTouchToClickListener implements View.OnTouchListener {
    private long a;
    private float b;
    private float c;
    private final Context d;
    private final View.OnClickListener e;

    public AppLovinTouchToClickListener(Context context, View.OnClickListener onClickListener) {
        this.d = context;
        this.e = onClickListener;
    }

    private float a(float f) {
        return f / this.d.getResources().getDisplayMetrics().density;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return a((float) Math.sqrt((f5 * f5) + (f6 * f6)));
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.a = System.currentTimeMillis();
            this.b = motionEvent.getX();
            this.c = motionEvent.getY();
        } else if (action == 1 && System.currentTimeMillis() - this.a < 1000 && a(this.b, this.c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
            this.e.onClick(view);
        }
        return true;
    }
}
