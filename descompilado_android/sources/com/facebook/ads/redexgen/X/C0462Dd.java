package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0462Dd extends LinearLayout {
    private static final int B = (int) (J6.B * 14.0f);
    private static final int C = (int) (J6.B * 4.0f);
    private static final int E = (int) (J6.B * 72.0f);
    private static final int D = (int) (J6.B * 8.0f);

    public C0462Dd(Context context, C01481b dataBundle) {
        super(context);
        J6.O(this, -1728053248);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200L);
        new AnimationSet(true).addAnimation(alphaAnimation);
        LinearLayout linearLayoutB = B(dataBundle);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 2, 0.5f, 2, 0.5f);
        scaleAnimation.setStartOffset(200L);
        scaleAnimation.setDuration(300L);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimationSet scaleAnimations = new AnimationSet(true);
        scaleAnimations.addAnimation(scaleAnimation);
        addView(linearLayoutB, layoutParams);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 2, 0.5f, 2, 0.5f);
        scaleAnimation2.setStartOffset(1300L);
        scaleAnimation2.setDuration(300L);
        scaleAnimation2.setAnimationListener(new AnimationAnimationListenerC0464Df(this, linearLayoutB));
        scaleAnimations.addAnimation(scaleAnimation2);
        linearLayoutB.startAnimation(scaleAnimations);
    }

    private LinearLayout B(C01481b dataBundle) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        J6.O(linearLayout, 0);
        linearLayout.setOrientation(1);
        CN cn = new CN(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.setMargins(0, 0, 0, B);
        linearLayout.addView(cn, layoutParams);
        if (dataBundle.A().get(0).D().A() == C1T.PAGE_POST) {
            cn.setFullCircleCorners(true);
        } else {
            cn.setRadius(D);
        }
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setTextSize(2, 18.0f);
        textView.setText(dataBundle.A().get(0).D().I());
        textView.setTypeface(Typeface.defaultFromStyle(1));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        layoutParams2.setMargins(0, 0, 0, C);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setTextSize(2, 14.0f);
        textView2.setText(dataBundle.I().D());
        linearLayout.addView(textView2);
        new B7(cn).D(E, E).E(dataBundle.I().B());
        return linearLayout;
    }
}
