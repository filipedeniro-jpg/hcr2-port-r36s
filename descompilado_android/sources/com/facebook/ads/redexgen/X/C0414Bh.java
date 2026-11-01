package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0414Bh extends LinearLayout {
    private static final int D = (int) (40.0f * J6.B);
    private static final int E = (int) (20.0f * J6.B);
    private static final int F = (int) (10.0f * J6.B);
    private final C1 B;
    private final AnonymousClass31 C;

    public C0414Bh(Context context, AnonymousClass31 reason, C1 navigation, EnumC0606Is icon) {
        this(context, reason, navigation, null, icon);
    }

    public C0414Bh(Context context, AnonymousClass31 reason, C1 navigation, @Nullable String title, EnumC0606Is icon) {
        super(context);
        this.C = reason;
        this.B = navigation;
        setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        if (!TextUtils.isEmpty(title)) {
            View viewC = C(title);
            viewC.setPadding(0, 0, 0, 0);
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            J6.O(view, -10459280);
            addView(viewC, layoutParams);
            addView(view);
        }
        if (!TextUtils.isEmpty(this.C.E())) {
            View viewE = E(icon, this.C.E());
            viewE.setPadding(0, F, 0, F);
            addView(viewE, layoutParams);
        }
        View viewD = D();
        viewD.setPadding(0, F, 0, 0);
        addView(viewD, layoutParams);
    }

    private View C(String title) {
        ImageView imageView = new ImageView(getContext());
        imageView.setColorFilter(-10459280);
        imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.BACK_ARROW));
        imageView.setPadding(0, F, F * 2, F);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(D, D);
        imageView.setOnClickListener(new ViewOnClickListenerC0416Bj(this));
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(title);
        J6.Q(textView, true, 16);
        textView.setTextColor(-14934495);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 0, D, 0);
        layoutParams2.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }

    private View D() {
        C0407Ba c0407Ba = new C0407Ba(getContext());
        for (AnonymousClass31 anonymousClass31 : this.C.B()) {
            C0430Bx c0430Bx = new C0430Bx(getContext());
            c0430Bx.setData(anonymousClass31.F(), null);
            c0430Bx.setOnClickListener(new ViewOnClickListenerC0415Bi(this, c0430Bx, anonymousClass31));
            c0407Ba.addView(c0430Bx);
        }
        return c0407Ba;
    }

    private View E(EnumC0606Is icon, String subtitle) {
        ImageView imageView = new ImageView(getContext());
        imageView.setColorFilter(-10459280);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.gravity = 16;
        imageView.setImageBitmap(C0605Ir.E(icon));
        TextView textView = new TextView(getContext());
        J6.Q(textView, true, 14);
        textView.setTextColor(-10459280);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        textView.setText(subtitle);
        textView.setPadding(F, 0, 0, 0);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }
}
