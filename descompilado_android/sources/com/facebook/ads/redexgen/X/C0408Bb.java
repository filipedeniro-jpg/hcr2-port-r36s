package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0408Bb extends AbstractC0432Bz {
    private final ImageView B;
    private final LinearLayout C;
    private final ScrollView D;
    private static final int G = (int) (8.0f * J6.B);
    private static final int F = (int) (10.0f * J6.B);
    private static final int E = (int) (44.0f * J6.B);

    public C0408Bb(Context context, JV adEventManager, String clientToken, int width, int height) {
        super(context, adEventManager, clientToken);
        this.B = new ImageView(getContext());
        this.B.setPadding(F, F, F, F);
        this.B.setColorFilter(-10459280);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.gravity = 3;
        this.B.setLayoutParams(layoutParams);
        this.D = new ScrollView(getContext());
        this.D.setFillViewport(true);
        J6.O(this.D, -218103809);
        this.C = new LinearLayout(getContext());
        this.C.setOrientation(1);
        this.C.setPadding(G, G, G, G);
        this.D.addView(this.C, new FrameLayout.LayoutParams(-1, -1));
        addView(this.D, new LinearLayout.LayoutParams(width, height));
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void B(AnonymousClass31 reason, EnumC01982z type) {
        String strH;
        EnumC0606Is enumC0606Is;
        int i;
        this.B.setOnClickListener(null);
        if (type == EnumC01982z.REPORT) {
            strH = C01972y.E(getContext());
            enumC0606Is = EnumC0606Is.REPORT_AD;
            i = -552389;
        } else {
            strH = C01972y.H(getContext());
            enumC0606Is = EnumC0606Is.HIDE_AD;
            i = -13272859;
        }
        C2 c2A = new C3(getContext()).D(super.B).K(strH).J(C01972y.C(getContext())).E(reason.F()).H(false).B(enumC0606Is).C(i).L(false).G(false).A();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        J6.G(this.C);
        this.D.fullScroll(33);
        this.C.removeAllViews();
        this.C.addView(c2A, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void D() {
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.CROSS));
        this.B.setOnClickListener(new ViewOnClickListenerC0413Bg(this));
        C0430Bx c0430Bx = new C0430Bx(getContext());
        c0430Bx.setData(C01972y.H(getContext()), EnumC0606Is.HIDE_AD);
        c0430Bx.setOnClickListener(new ViewOnClickListenerC0412Bf(this, c0430Bx));
        C0430Bx c0430Bx2 = new C0430Bx(getContext());
        c0430Bx2.setData(C01972y.N(getContext()), EnumC0606Is.REPORT_AD);
        c0430Bx2.setOnClickListener(new ViewOnClickListenerC0411Be(this, c0430Bx2));
        C0430Bx c0430Bx3 = new C0430Bx(getContext());
        c0430Bx3.setData(C01972y.O(getContext()), EnumC0606Is.AD_CHOICES_ICON);
        c0430Bx3.setOnClickListener(new ViewOnClickListenerC0410Bd(this, c0430Bx3));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(G, G, G, G);
        layoutParams.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, 0);
        layoutParams2.gravity = 17;
        layoutParams2.weight = 1.0f;
        J6.G(this.C);
        this.C.removeAllViews();
        this.C.addView(this.B);
        this.C.addView(linearLayout, layoutParams2);
        linearLayout.addView(c0430Bx, layoutParams);
        linearLayout.addView(c0430Bx2, layoutParams);
        linearLayout.addView(c0430Bx3, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void E(AnonymousClass31 reason, EnumC01982z type) {
        C0414Bh c0414Bh = new C0414Bh(getContext(), reason, super.B, type == EnumC01982z.REPORT ? EnumC0606Is.REPORT_AD : EnumC0606Is.HIDE_AD);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.BACK_ARROW));
        this.B.setOnClickListener(new ViewOnClickListenerC0409Bc(this));
        J6.G(this.C);
        this.D.fullScroll(33);
        this.C.removeAllViews();
        this.C.addView(this.B);
        this.C.addView(c0414Bh, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void G() {
        J6.J(this);
        J6.M(this);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final boolean H() {
        return true;
    }
}
