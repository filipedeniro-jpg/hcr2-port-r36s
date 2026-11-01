package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.api.AdChoicesViewApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0513Fc implements AdChoicesViewApi {
    private String C;
    private TextView D;
    private final AdChoicesView F;
    private final NativeAdBase G;
    private boolean B = false;
    private final float E = J6.B;

    public C0513Fc(AdChoicesView adChoicesView, Context c, NativeAdBase ad) {
        this.F = adChoicesView;
        this.G = ad;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private ImageView I(KG adChoicesIcon) {
        ImageView imageView = new ImageView(this.F.getContext());
        this.F.addView(imageView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(adChoicesIcon.getWidth() * this.E), Math.round(adChoicesIcon.getHeight() * this.E));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.E), Math.round(this.E * 2.0f), Math.round(this.E * 2.0f), Math.round(this.E * 2.0f));
        imageView.setLayoutParams(layoutParams);
        KF.a(adChoicesIcon, imageView);
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        Paint p = new Paint();
        p.setTextSize(this.D.getTextSize());
        int iRound = Math.round(p.measureText(this.C) + (4.0f * this.E));
        int width = this.F.getWidth();
        int targetWidth = width - iRound;
        C0511Fa c0511Fa = new C0511Fa(this, width, targetWidth);
        c0511Fa.setAnimationListener(new AnimationAnimationListenerC0512Fb(this));
        c0511Fa.setDuration(300L);
        c0511Fa.setFillAfter(true);
        this.F.startAnimation(c0511Fa);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        Paint paint = new Paint();
        paint.setTextSize(this.D.getTextSize());
        int iRound = Math.round(paint.measureText(this.C) + (4.0f * this.E));
        int startWidth = this.F.getWidth();
        int textWidth = startWidth + iRound;
        this.B = true;
        FX fx = new FX(this, startWidth, textWidth);
        fx.setAnimationListener(new FZ(this));
        fx.setDuration(300L);
        fx.setFillAfter(true);
        this.F.startAnimation(fx);
    }

    @Override // com.facebook.ads.internal.api.AdChoicesViewApi
    public final void initialize(boolean expandableIcon, @Nullable NativeAdLayout nativeAdLayout) {
        ((KF) this.G.getInternalNativeAd()).Z(nativeAdLayout);
        C01360p c01360pE = ((KF) this.G.getInternalNativeAd()).E();
        if (c01360pE != null && c01360pE.W() && !c01360pE.A()) {
            this.F.setVisibility(8);
            return;
        }
        this.C = this.G.getAdChoicesText();
        if (TextUtils.isEmpty(this.C)) {
            this.C = "AdChoices";
        }
        KG adChoicesIcon = ((KF) this.G.getInternalNativeAd()).getAdChoicesIcon();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.F.setOnTouchListener(new FW(this));
        this.D = new TextView(this.F.getContext());
        this.F.addView(this.D);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (expandableIcon && adChoicesIcon != null) {
            layoutParams2.addRule(11, I(adChoicesIcon).getId());
            layoutParams2.width = 0;
            layoutParams.width = Math.round((adChoicesIcon.getWidth() + 4) * this.E);
            layoutParams.height = Math.round((adChoicesIcon.getHeight() + 2) * this.E);
            this.B = false;
        } else {
            this.B = true;
        }
        this.F.setLayoutParams(layoutParams);
        layoutParams2.addRule(15, -1);
        this.D.setLayoutParams(layoutParams2);
        this.D.setSingleLine();
        this.D.setText(this.C);
        this.D.setTextSize(10.0f);
        this.D.setTextColor(-4341303);
        MZ.C(this.F, MZ.INTERNAL_AD_CHOICES_ICON);
        MZ.C(this.D, MZ.INTERNAL_AD_CHOICES_ICON);
    }
}
