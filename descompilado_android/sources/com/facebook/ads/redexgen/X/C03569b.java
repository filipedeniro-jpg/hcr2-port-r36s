package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9b, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03569b extends RelativeLayout {
    private final C0401Au B;
    private final View C;

    public C03569b(Context context, View mediaView) {
        super(context);
        this.C = mediaView;
        this.B = new C0401Au(context);
        J6.U(this.B);
    }

    public final void A(@Nullable View progressBar, @Nullable View muteButton) {
        B(progressBar, muteButton, 8, null, false);
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
    public final void B(@Nullable View progressBar, @Nullable View muteButton, int muteButtonVerticalAlignment, @Nullable C0394An titleAndDescription, boolean drawTextInsideMedia) {
        this.B.addView(this.C, new RelativeLayout.LayoutParams(-1, -2));
        if (muteButton != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(AbstractC0390Aj.F, AbstractC0390Aj.F);
            layoutParams.addRule(muteButtonVerticalAlignment, this.C.getId());
            layoutParams.addRule(7, this.C.getId());
            layoutParams.setMargins(AbstractC0390Aj.E, AbstractC0390Aj.E, AbstractC0390Aj.E, AbstractC0390Aj.E);
            this.B.addView(muteButton, layoutParams);
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.C.getId());
        if (titleAndDescription != null) {
            if (drawTextInsideMedia) {
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                titleAndDescription.setAlignment(3);
                layoutParams3.setMargins(AbstractC0390Aj.E / 2, AbstractC0390Aj.E / 2, AbstractC0390Aj.E / 2, AbstractC0390Aj.E / 2);
                linearLayout.addView(titleAndDescription, layoutParams3);
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                J6.P(linearLayout, gradientDrawable);
            } else {
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams4.addRule(3, this.B.getId());
                layoutParams4.setMargins(0, AbstractC0390Aj.E, 0, 0);
                titleAndDescription.setAlignment(17);
                addView(titleAndDescription, layoutParams4);
            }
        }
        if (progressBar != null) {
            linearLayout.addView(progressBar, new RelativeLayout.LayoutParams(-1, -2));
        }
        this.B.addView(linearLayout, layoutParams2);
        addView(this.B, new RelativeLayout.LayoutParams(-1, -2));
    }

    public final void C(int height) {
        this.C.setLayoutParams(new RelativeLayout.LayoutParams(-1, height));
    }
}
