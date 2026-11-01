package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0418Bl extends AbstractC0432Bz {
    private final ImageView B;
    private final LinearLayout C;
    private final HorizontalScrollView D;
    private final LinearLayout E;
    private static final int H = (int) (4.0f * J6.B);
    private static final int G = (int) (10.0f * J6.B);
    private static final int F = (int) (44.0f * J6.B);

    public C0418Bl(Context context, JV adEventManager, String clientToken, int parentWidth, int parentHeight) {
        super(context, adEventManager, clientToken);
        this.B = new ImageView(getContext());
        this.B.setPadding(G, G, G, G);
        this.B.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.B.setColorFilter(-10459280);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(F, F);
        layoutParams.gravity = 16;
        this.C = new LinearLayout(getContext());
        this.C.setOrientation(0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        this.D = new HorizontalScrollView(getContext());
        this.D.setHorizontalScrollBarEnabled(false);
        this.D.setLayoutParams(layoutParams2);
        this.D.addView(this.C, layoutParams2);
        this.E = new LinearLayout(getContext());
        this.E.setOrientation(0);
        J6.O(this.E, -218103809);
        this.E.setMotionEventSplittingEnabled(false);
        this.E.addView(this.B, layoutParams);
        this.E.addView(this.D, layoutParams2);
        addView(this.E, new FrameLayout.LayoutParams(parentWidth, parentHeight));
        this.E.setClickable(true);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void B(AnonymousClass31 reason, EnumC01982z type) {
        this.B.setOnClickListener(null);
        TextView textView = new TextView(getContext());
        J6.Q(textView, true, 14);
        textView.setText(C01972y.C(getContext()));
        textView.setGravity(17);
        J6.G(this.E);
        this.E.removeAllViews();
        this.E.addView(textView, new LinearLayout.LayoutParams(-1, -1));
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void D() {
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.CROSS));
        this.B.setOnClickListener(new ViewOnClickListenerC0424Br(this));
        C0430Bx c0430Bx = new C0430Bx(getContext());
        c0430Bx.setData(C01972y.H(getContext()), EnumC0606Is.HIDE_AD);
        c0430Bx.setOnClickListener(new ViewOnClickListenerC0423Bq(this, c0430Bx));
        C0430Bx c0430Bx2 = new C0430Bx(getContext());
        c0430Bx2.setData(C01972y.N(getContext()), EnumC0606Is.REPORT_AD);
        c0430Bx2.setOnClickListener(new ViewOnClickListenerC0422Bp(this, c0430Bx2));
        C0430Bx c0430Bx3 = new C0430Bx(getContext());
        c0430Bx3.setData(C01972y.O(getContext()), EnumC0606Is.AD_CHOICES_ICON);
        c0430Bx3.setOnClickListener(new ViewOnClickListenerC0421Bo(this, c0430Bx3));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, H, H, H);
        J6.G(this.E);
        this.C.removeAllViews();
        this.C.addView(c0430Bx, layoutParams);
        this.C.addView(c0430Bx2, layoutParams);
        this.C.addView(c0430Bx3, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void E(AnonymousClass31 reason, EnumC01982z type) {
        J6.G(this.E);
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.BACK_ARROW));
        this.B.setOnClickListener(new ViewOnClickListenerC0420Bn(this));
        this.C.removeAllViews();
        this.D.fullScroll(17);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, H, H, H);
        for (AnonymousClass31 anonymousClass31 : reason.B()) {
            C0430Bx c0430Bx = new C0430Bx(getContext());
            c0430Bx.setData(anonymousClass31.F(), null);
            c0430Bx.setOnClickListener(new ViewOnClickListenerC0419Bm(this, c0430Bx, anonymousClass31));
            this.C.addView(c0430Bx, layoutParams);
        }
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
