package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class aa extends LinearLayout {
    private final Paint a;
    private final float b;
    private int c;

    public aa(Context context) {
        super(context);
        this.b = 1.0f;
        this.c = 0;
        int iRound = Math.round(context.getResources().getDisplayMetrics().density * 5.0f);
        setPadding(iRound, iRound, iRound, iRound);
        setBaselineAligned(false);
        this.a = new Paint();
        this.a.setStyle(Paint.Style.FILL);
    }

    public void a(int i) {
        this.a.setColor(i);
        invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = getContext().getResources().getDisplayMetrics().density;
        if ((this.c & 1) > 0) {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), f * 1.0f, this.a);
        }
        if ((this.c & 2) > 0) {
            canvas.drawRect(canvas.getWidth() - (f * 1.0f), 0.0f, canvas.getWidth(), canvas.getHeight(), this.a);
        }
        if ((this.c & 4) > 0) {
            canvas.drawRect(0.0f, canvas.getHeight() - (f * 1.0f), canvas.getWidth(), canvas.getHeight(), this.a);
        }
        if ((this.c & 8) > 0) {
            canvas.drawRect(0.0f, 0.0f, f * 1.0f, canvas.getHeight(), this.a);
        }
    }

    public void b(int i) {
        this.c = i;
    }
}
