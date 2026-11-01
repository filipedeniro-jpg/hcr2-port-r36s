package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.NumberFormat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Aw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0403Aw extends B5 {
    private TextView B;
    private final LinearLayout C;
    private final RelativeLayout D;
    private TextView E;
    private LinearLayout F;
    private final TextView G;
    private C0400At H;
    private TextView I;
    private static final int P = C5W.B(-1, 77);
    private static final int M = (int) (12.0f * J6.B);
    private static final int N = (int) (8.0f * J6.B);
    private static final int J = (int) (26.0f * J6.B);
    private static final int L = (int) (144.0f * J6.B);
    private static final int K = (int) (48.0f * J6.B);
    private static final int O = (int) (16.0f * J6.B);
    private static final int Q = (int) (14.0f * J6.B);

    public C0403Aw(Context context, int iconSize, boolean isIconFullCircle, C1Y colors, String clickEventName, JV adEventManager, InterfaceC0525Fo adListener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        super(context, iconSize, colors, clickEventName, adEventManager, adListener, viewabilityChecker, touchDataRecorder);
        super.E.setFullCircleCorners(isIconFullCircle);
        setPadding(M, M, M, 0);
        this.D = D();
        this.C = C();
        super.B.addView(this.D);
        super.B.addView(this.C);
        B();
        this.G = E();
        addView(super.B);
        addView(super.C);
        addView(this.G);
    }

    private void B() {
        super.C.setLayoutParams(new LinearLayout.LayoutParams(-1, K));
        super.C.setPadding(0, 0, 0, 0);
    }

    private LinearLayout C() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setPadding(0, 0, 0, M);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.D.getId());
        linearLayout.setLayoutParams(layoutParams);
        this.B = new TextView(getContext());
        this.B.setMaxLines(2);
        this.B.setEllipsize(TextUtils.TruncateAt.END);
        this.B.setGravity(16);
        this.B.setTextColor(-1);
        J6.Q(this.B, false, 16);
        linearLayout.addView(this.B, new LinearLayout.LayoutParams(-1, -2));
        return linearLayout;
    }

    private RelativeLayout D() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = M;
        relativeLayout.setLayoutParams(layoutParams);
        J6.U(relativeLayout);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(super.D, super.D);
        layoutParams2.addRule(15);
        layoutParams2.addRule(9);
        relativeLayout.addView(super.E, layoutParams2);
        RelativeLayout titleAndRatingContainer = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = N;
        layoutParams3.addRule(1, super.E.getId());
        layoutParams3.addRule(15);
        relativeLayout.addView(titleAndRatingContainer, layoutParams3);
        this.I = new TextView(getContext());
        J6.U(this.I);
        this.I.setLayoutParams(B5.F);
        this.I.setTextColor(-1);
        J6.Q(this.I, true, 18);
        titleAndRatingContainer.addView(this.I);
        this.F = new LinearLayout(getContext());
        this.F.setOrientation(0);
        this.F.setGravity(16);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, O);
        layoutParams4.topMargin = N / 2;
        layoutParams4.addRule(3, this.I.getId());
        titleAndRatingContainer.addView(this.F, layoutParams4);
        this.H = new C0400At(getContext(), Q, 5, P, -1);
        this.H.setGravity(16);
        this.F.addView(this.H, new LinearLayout.LayoutParams(-2, -1));
        this.E = new TextView(getContext());
        this.E.setTextColor(-1);
        this.E.setGravity(16);
        this.E.setIncludeFontPadding(false);
        J6.Q(this.E, false, 14);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams5.leftMargin = N;
        this.F.addView(this.E, layoutParams5);
        return relativeLayout;
    }

    public final void D(int orientation) {
        boolean z = orientation == 1;
        setOrientation(z ? 1 : 0);
        if (!z) {
            setWeightSum(5.0f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 4.0f;
            layoutParams.bottomMargin = J - M;
            super.B.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, K);
            layoutParams2.bottomMargin = J / 2;
            layoutParams2.weight = 1.0f;
            layoutParams2.gravity = 80;
            super.C.setLayoutParams(layoutParams2);
            super.C.setMinWidth(L);
            J6.M(this.G);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.G.getLayoutParams();
            layoutParams3.topMargin = N;
            layoutParams3.bottomMargin = 0;
            this.C.addView(this.G);
            this.G.setGravity(3);
        }
    }

    private TextView E() {
        TextView textView = new TextView(getContext());
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(17);
        textView.setTextColor(-1);
        textView.setAllCaps(true);
        J6.Q(textView, false, 12);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = M;
        layoutParams.bottomMargin = J;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final View getExpandableLayout() {
        return this.C;
    }

    @Override // com.facebook.ads.redexgen.X.B5
    public void setInfo(C1V metadata, C1Z ctaData, String clientToken, String imageUrl, @Nullable CP listener) {
        super.setInfo(metadata, ctaData, clientToken, imageUrl, listener);
        this.I.setText(metadata.I());
        this.B.setText(metadata.C());
        this.G.setText(metadata.G());
        if (TextUtils.isEmpty(ctaData.B())) {
            J6.F(super.C);
        }
        if (TextUtils.isEmpty(metadata.G())) {
            J6.F(this.G);
        }
        if (TextUtils.isEmpty(metadata.F())) {
            this.F.setVisibility(8);
            return;
        }
        this.F.setVisibility(0);
        this.H.setRating(Float.parseFloat(metadata.F()));
        this.E.setText("(" + NumberFormat.getNumberInstance().format(Integer.parseInt(metadata.E())) + ")");
    }
}
