package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public class y extends LinearLayout {
    final v a;
    final az b;
    private LinearLayout c;
    private ay d;
    private TextView e;
    private int f;

    public y(Context context, v vVar) {
        super(context);
        this.f = Integer.MIN_VALUE;
        this.a = vVar;
        int iRound = Math.round(context.getResources().getDisplayMetrics().density * 8.0f);
        setOrientation(1);
        setGravity(17);
        this.c = new LinearLayout(context);
        this.c.setGravity(17);
        this.c.setOrientation(0);
        this.c.setPadding(iRound, iRound, iRound, iRound);
        this.d = new ay(context);
        this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.d.setPadding(0, 0, iRound, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        vVar.a(layoutParams, vVar.H, 1.0f);
        this.e = new TextView(getContext());
        this.e.setTextColor(-1);
        this.e.setTypeface(null, 1);
        this.e.setGravity(17);
        this.e.setTextSize(2, com.chartboost.sdk.e.a(context) ? 26.0f : 16.0f);
        this.c.addView(this.d, layoutParams);
        this.c.addView(this.e, new LinearLayout.LayoutParams(-2, -2));
        this.b = new az(getContext()) { // from class: com.chartboost.sdk.impl.y.1
            @Override // com.chartboost.sdk.impl.az
            protected void a(MotionEvent motionEvent) {
                y.this.b.setEnabled(false);
                y.this.a.e().g();
            }
        };
        this.b.setContentDescription("CBWatch");
        this.b.setPadding(0, 0, 0, iRound);
        this.b.a(ImageView.ScaleType.FIT_CENTER);
        this.b.setPadding(iRound, iRound, iRound, iRound);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        vVar.a(layoutParams2, vVar.G, 1.0f);
        this.d.a(vVar.H);
        this.b.a(vVar.G);
        addView(this.c, new LinearLayout.LayoutParams(-2, -2));
        addView(this.b, layoutParams2);
        a();
    }

    public void a(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : this.f);
    }

    public void a(String str, int i) {
        this.e.setText(str);
        this.f = i;
        a(this.a.s());
    }

    public void a() {
        a(this.a.s());
    }
}
