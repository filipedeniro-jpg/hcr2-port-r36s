package com.applovin.impl.adview;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
class i extends Dialog implements h {
    private final Activity a;
    private final com.applovin.impl.sdk.h b;
    private final com.applovin.impl.sdk.n c;
    private final c d;
    private final com.applovin.impl.sdk.ad.a e;
    private RelativeLayout f;
    private f g;

    i(com.applovin.impl.sdk.ad.a aVar, c cVar, Activity activity, com.applovin.impl.sdk.h hVar) {
        super(activity, R.style.Theme.Translucent.NoTitleBar);
        if (aVar == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("No main view specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.b = hVar;
        this.c = hVar.w();
        this.a = activity;
        this.d = cVar;
        this.e = aVar;
        requestWindowFeature(1);
        setCancelable(false);
    }

    private int a(int i) {
        return AppLovinSdkUtils.dpToPx(this.a, i);
    }

    private void a(f.a aVar) {
        if (this.g != null) {
            this.c.c("ExpandedAdDialog", "Attempting to create duplicate close button");
            return;
        }
        this.g = f.a(this.b, getContext(), aVar);
        this.g.setVisibility(8);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.i.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.c();
            }
        });
        this.g.setClickable(false);
        int iA = a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.cB)).intValue());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iA, iA);
        layoutParams.addRule(10);
        layoutParams.addRule(((Boolean) this.b.a(com.applovin.impl.sdk.b.b.cE)).booleanValue() ? 9 : 11);
        this.g.a(iA);
        int iA2 = a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.cD)).intValue());
        int iA3 = a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.cC)).intValue());
        layoutParams.setMargins(iA3, iA2, iA3, 0);
        this.f.addView(this.g, layoutParams);
        this.g.bringToFront();
        int iA4 = a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.cF)).intValue());
        View view = new View(this.a);
        view.setBackgroundColor(0);
        int i = iA + iA4;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, i);
        layoutParams2.addRule(10);
        layoutParams2.addRule(((Boolean) this.b.a(com.applovin.impl.sdk.b.b.cE)).booleanValue() ? 9 : 11);
        layoutParams2.setMargins(iA3 - a(5), iA2 - a(5), iA3 - a(5), 0);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.i.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (i.this.g.isClickable()) {
                    i.this.g.performClick();
                }
            }
        });
        this.f.addView(view, layoutParams2);
        view.bringToFront();
    }

    private void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        this.f = new RelativeLayout(this.a);
        this.f.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f.setBackgroundColor(-1157627904);
        this.f.addView(this.d);
        if (!this.e.k()) {
            a(this.e.l());
            d();
        }
        setContentView(this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.d.a("javascript:al_onCloseTapped();", new Runnable() { // from class: com.applovin.impl.adview.i.1
            @Override // java.lang.Runnable
            public void run() {
                i.this.dismiss();
            }
        });
    }

    private void d() {
        this.a.runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.i.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (i.this.g == null) {
                        i.this.c();
                    }
                    i.this.g.setVisibility(0);
                    i.this.g.bringToFront();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(((Long) i.this.b.a(com.applovin.impl.sdk.b.b.cA)).longValue());
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.applovin.impl.adview.i.6.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                            i.this.g.setClickable(true);
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                        }
                    });
                    i.this.g.startAnimation(alphaAnimation);
                } catch (Throwable th) {
                    i.this.c.b("ExpandedAdDialog", "Unable to fade in close button", th);
                    i.this.c();
                }
            }
        });
    }

    public com.applovin.impl.sdk.ad.a a() {
        return this.e;
    }

    @Override // android.app.Dialog, android.content.DialogInterface, com.applovin.impl.adview.h
    public void dismiss() {
        com.applovin.impl.sdk.c.d dVarB = this.d.b();
        if (dVarB != null) {
            dVarB.e();
        }
        this.a.runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.i.3
            @Override // java.lang.Runnable
            public void run() {
                i.this.f.removeView(i.this.d);
                i.super.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        this.d.a("javascript:al_onBackPressed();", new Runnable() { // from class: com.applovin.impl.adview.i.2
            @Override // java.lang.Runnable
            public void run() {
                i.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        try {
            Window window = getWindow();
            if (window != null) {
                window.setFlags(this.a.getWindow().getAttributes().flags, this.a.getWindow().getAttributes().flags);
                if (this.e.x()) {
                    window.addFlags(16777216);
                }
            } else {
                this.c.d("ExpandedAdDialog", "Unable to turn on hardware acceleration - window is null");
            }
        } catch (Throwable th) {
            this.c.b("ExpandedAdDialog", "Setting window flags failed.", th);
        }
    }
}
