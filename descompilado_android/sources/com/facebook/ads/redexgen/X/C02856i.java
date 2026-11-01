package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02856i extends RelativeLayout {
    private boolean B;
    private ImageView C;
    private final String D;
    private final String E;
    private TextView F;
    private final String G;
    private final DisplayMetrics H;

    public C02856i(Context context, String mAdChoicesText, String mAdChoicesLinkUrl, float[] cornerRadii, String mClientToken) {
        super(context);
        this.B = false;
        this.E = mAdChoicesText;
        this.D = mAdChoicesLinkUrl;
        this.G = mClientToken;
        this.H = context.getResources().getDisplayMetrics();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable.setAlpha(178);
        gradientDrawable.setCornerRadii(new float[]{cornerRadii[0] * this.H.density, cornerRadii[0] * this.H.density, cornerRadii[1] * this.H.density, cornerRadii[1] * this.H.density, cornerRadii[2] * this.H.density, cornerRadii[2] * this.H.density, cornerRadii[3] * this.H.density, cornerRadii[3] * this.H.density});
        J6.P(this, gradientDrawable);
        M();
        I();
        J();
        setMinimumWidth(Math.round(20.0f * this.H.density));
        setMinimumHeight(Math.round(18.0f * this.H.density));
    }

    private void I() {
        this.C = new ImageView(getContext());
        this.C.setImageBitmap(C0605Ir.E(EnumC0606Is.IC_AD_CHOICES));
        addView(this.C);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(this.H.density * 16.0f), Math.round(this.H.density * 16.0f));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.H.density), Math.round(this.H.density * 2.0f), Math.round(this.H.density * 2.0f), Math.round(this.H.density * 2.0f));
        this.C.setLayoutParams(layoutParams);
    }

    private void J() {
        this.F = new TextView(getContext());
        addView(this.F);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = 0;
        layoutParams.leftMargin = (int) (20.0f * this.H.density);
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        this.F.setLayoutParams(layoutParams);
        this.F.setSingleLine();
        this.F.setText(this.E);
        this.F.setTextSize(10.0f);
        this.F.setTextColor(-4341303);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        Paint p = new Paint();
        p.setTextSize(this.F.getTextSize());
        int targetWidth = Math.round(p.measureText(this.E) + (4.0f * this.H.density));
        final int width = getWidth();
        final int startWidth = width - targetWidth;
        Animation animation = new Animation() { // from class: com.facebook.ads.redexgen.X.6k
            @Override // android.view.animation.Animation
            public final void applyTransformation(float interpolatedTime, Transformation t) {
                int i = (int) (width + ((startWidth - width) * interpolatedTime));
                this.B.getLayoutParams().width = i;
                this.B.requestLayout();
                this.B.F.getLayoutParams().width = i - startWidth;
                this.B.F.requestLayout();
            }

            @Override // android.view.animation.Animation
            public final boolean willChangeBounds() {
                return true;
            }
        };
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.ads.redexgen.X.6j
            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationEnd(Animation animation2) {
                this.B.B = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationRepeat(Animation animation2) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationStart(Animation animation2) {
            }
        });
        animation.setDuration(300L);
        animation.setFillAfter(true);
        startAnimation(animation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        Paint paint = new Paint();
        paint.setTextSize(this.F.getTextSize());
        int textWidth = Math.round(paint.measureText(this.E) + (4.0f * this.H.density));
        final int startWidth = getWidth();
        final int targetWidth = textWidth + startWidth;
        this.B = true;
        Animation animation = new Animation() { // from class: com.facebook.ads.redexgen.X.6n
            @Override // android.view.animation.Animation
            public final void applyTransformation(float interpolatedTime, Transformation t) {
                int i = (int) (startWidth + ((targetWidth - startWidth) * interpolatedTime));
                this.B.getLayoutParams().width = i;
                this.B.requestLayout();
                this.B.F.getLayoutParams().width = i - startWidth;
                this.B.F.requestLayout();
            }

            @Override // android.view.animation.Animation
            public final boolean willChangeBounds() {
                return true;
            }
        };
        animation.setAnimationListener(new AnimationAnimationListenerC02886l(this));
        animation.setDuration(300L);
        animation.setFillAfter(true);
        startAnimation(animation);
    }

    private void M() {
        setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.6o
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    if (this.B.B) {
                        if (!TextUtils.isEmpty(this.B.D)) {
                            I7.B(new I7(), this.B.getContext(), Uri.parse(this.B.D), this.B.G);
                        }
                    } else {
                        this.B.L();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
