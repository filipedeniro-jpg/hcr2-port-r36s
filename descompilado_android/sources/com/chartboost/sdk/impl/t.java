package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public class t extends z {
    private LinearLayout b;
    private LinearLayout c;
    private ay d;
    private az e;
    private TextView f;
    private TextView g;

    @Override // com.chartboost.sdk.impl.z
    protected int b() {
        return 72;
    }

    public t(Context context, v vVar) {
        super(context, vVar);
    }

    @Override // com.chartboost.sdk.impl.z
    protected View a() {
        Context context = getContext();
        int iRound = Math.round(getContext().getResources().getDisplayMetrics().density * 6.0f);
        this.b = new LinearLayout(context);
        this.b.setOrientation(0);
        this.b.setGravity(17);
        this.c = new LinearLayout(context);
        this.c.setOrientation(1);
        this.c.setGravity(8388627);
        this.d = new ay(context);
        this.d.setPadding(iRound, iRound, iRound, iRound);
        if (this.a.J.c()) {
            this.d.a(this.a.J);
        }
        this.e = new az(context) { // from class: com.chartboost.sdk.impl.t.1
            @Override // com.chartboost.sdk.impl.az
            protected void a(MotionEvent motionEvent) {
                t.this.a.e().b(motionEvent.getX(), motionEvent.getY(), super.getWidth(), super.getHeight());
            }
        };
        this.e.setPadding(iRound, iRound, iRound, iRound);
        if (this.a.K.c()) {
            this.e.a(this.a.K);
        }
        this.f = new TextView(getContext());
        this.f.setTextColor(-15264491);
        this.f.setTypeface(null, 1);
        this.f.setGravity(GravityCompat.START);
        this.f.setPadding(iRound, iRound, iRound, iRound / 2);
        this.g = new TextView(getContext());
        this.g.setTextColor(-15264491);
        this.g.setTypeface(null, 1);
        this.g.setGravity(GravityCompat.START);
        this.g.setPadding(iRound, 0, iRound, iRound);
        this.f.setTextSize(2, 14.0f);
        this.g.setTextSize(2, 11.0f);
        this.c.addView(this.f);
        this.c.addView(this.g);
        this.b.addView(this.d);
        this.b.addView(this.c, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.b.addView(this.e);
        return this.b;
    }

    public void a(String str, String str2) {
        this.f.setText(str);
        this.g.setText(str2);
    }
}
