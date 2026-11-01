package com.applovin.impl.adview;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
public class a extends RelativeLayout {
    private final ProgressBar a;

    public a(Activity activity, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams;
        super(activity);
        setClickable(false);
        this.a = new ProgressBar(activity, null, i2);
        this.a.setIndeterminate(true);
        this.a.setClickable(false);
        if (i == -2 || i == -1) {
            layoutParams = new RelativeLayout.LayoutParams(i, i);
        } else {
            int iDpToPx = AppLovinSdkUtils.dpToPx(activity, i);
            layoutParams = new RelativeLayout.LayoutParams(iDpToPx, iDpToPx);
        }
        layoutParams.addRule(13);
        this.a.setLayoutParams(layoutParams);
        addView(this.a);
    }

    public void a() {
        setVisibility(0);
    }

    public void b() {
        setVisibility(8);
    }

    public void setColor(int i) {
        this.a.getIndeterminateDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }
}
