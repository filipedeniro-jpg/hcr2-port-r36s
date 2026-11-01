package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7b, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03047b extends FrameLayout implements InterfaceC0624Jl {

    @Nullable
    private TextView B;

    @Nullable
    private TextView C;

    @Nullable
    private TextView D;

    @Nullable
    private TextView E;
    private final MediaView F;
    private final NativeAd G;
    private final C7Z H;
    private final ArrayList<View> I;
    private static final int O = (int) (J6.B * 6.0f);
    private static final int N = (int) (J6.B * 8.0f);
    private static final int M = (int) (J6.B * 12.0f);
    private static final int K = (int) (J6.B * 350.0f);
    private static final int J = (int) (J6.B * 250.0f);
    private static final int L = (int) (J6.B * 175.0f);

    /* JADX WARN: Type inference failed for: r2v0, types: [com.facebook.ads.redexgen.X.7Z] */
    public C03047b(final Context context, NativeAd nativeAd, final KI attributes, KJ viewType, final CN adIconView, MediaView mediaView, final AdOptionsView adOptionsView) {
        super(context);
        this.I = new ArrayList<>();
        this.G = nativeAd;
        this.F = mediaView;
        final NativeAd nativeAd2 = this.G;
        this.H = new LinearLayout(context, nativeAd2, attributes, adIconView, adOptionsView) { // from class: com.facebook.ads.redexgen.X.7Z
            private static final int B = (int) (J6.B * 32.0f);
            private static final int C = (int) (J6.B * 8.0f);

            {
                super(context);
                setOrientation(0);
                adIconView.setFullCircleCorners(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(B, B);
                layoutParams.gravity = 16;
                layoutParams.setMargins(0, 0, C, 0);
                addView(adIconView, layoutParams);
                TextView textView = new TextView(context);
                attributes.I(textView);
                textView.setMaxLines(1);
                textView.setText(nativeAd2.getAdvertiserName());
                TextView textView2 = new TextView(context);
                attributes.G(textView2);
                textView2.setMaxLines(1);
                textView2.setText(nativeAd2.getSponsoredTranslation());
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
                layoutParams2.weight = 1.0f;
                layoutParams2.gravity = 16;
                linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
                linearLayout.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
                addView(linearLayout, layoutParams2);
                addView(adOptionsView, new LinearLayout.LayoutParams(-2, -2));
            }
        };
        setPadding(M, M, M, O);
        addView(this.H, new FrameLayout.LayoutParams(-1, -2));
        if (viewType == KJ.HEIGHT_400 || viewType == KJ.RECT_DYNAMIC) {
            E(attributes);
        }
        addView(this.F, new FrameLayout.LayoutParams(-1, -2));
        if (viewType != KJ.RECT_DYNAMIC || this.G.getAdCreativeType() != NativeAd.AdCreativeType.CAROUSEL) {
            D(attributes);
            B(attributes);
            C(attributes);
        }
        this.I.add(adIconView);
        this.I.add(mediaView);
    }

    private void B(KI attributes) {
        if (this.G.getAdBodyText() != null && !this.G.getAdBodyText().trim().isEmpty()) {
            this.B = new TextView(getContext());
            attributes.G(this.B);
            this.B.setText(this.G.getAdBodyText());
            this.B.setPadding(M, 0, M, 0);
            addView(this.B, new FrameLayout.LayoutParams(-1, -2));
        }
    }

    private void C(KI attributes) {
        if (this.G.hasCallToAction()) {
            this.C = new TextView(getContext());
            J6.U(this.C);
            attributes.F(this.C);
            this.C.setText(this.G.getAdCallToAction());
            this.C.setPadding(N, N, N, N);
            addView(this.C, new FrameLayout.LayoutParams(-1, -2));
            this.I.add(this.C);
        }
    }

    private void D(KI attributes) {
        if (this.G.getAdHeadline() != null && !this.G.getAdHeadline().trim().isEmpty()) {
            this.D = new TextView(getContext());
            attributes.H(this.D);
            this.D.setText(this.G.getAdHeadline());
            this.D.setPadding(M, N, M, 0);
            addView(this.D, new FrameLayout.LayoutParams(-1, -2));
        }
    }

    private void E(KI attributes) {
        if (this.G.getAdLinkDescription() != null && !this.G.getAdLinkDescription().trim().isEmpty()) {
            this.E = new TextView(getContext());
            attributes.G(this.E);
            this.E.setText(this.G.getAdLinkDescription());
            this.E.setPadding(M, 0, M, N);
            addView(this.E, new FrameLayout.LayoutParams(-1, -2));
        }
    }

    private static void F(int widthMeasureSpec, int heightMeasureSpec, TextView... textViews) {
        for (TextView textView : textViews) {
            if (textView != null && textView.getVisibility() == 0) {
                int iE = J6.E(textView, heightMeasureSpec);
                textView.setLines(iE + 1);
                textView.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(textView.getMeasuredHeight() + (textView.getLineHeight() * iE), 1073741824));
                heightMeasureSpec -= textView.getLineHeight() * iE;
            }
        }
    }

    private void G(int containerHeightPx) {
        J6.V(this.F, containerHeightPx > L ? 0 : 8);
        J6.V(this.E, containerHeightPx > K ? 0 : 8);
        J6.V(this.B, containerHeightPx <= J ? 8 : 0);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0060  */
    private int H() {
        int measuredHeight;
        int measuredHeight2;
        int measuredHeight3;
        int linkDescHeight = 0;
        if (this.E != null && this.E.getVisibility() == 0) {
            measuredHeight = this.E.getMeasuredHeight();
        } else {
            measuredHeight = 0;
        }
        if (this.D != null && this.D.getVisibility() == 0) {
            measuredHeight2 = this.D.getMeasuredHeight();
        } else {
            measuredHeight2 = 0;
        }
        if (this.B != null) {
            int titleHeight = this.B.getVisibility();
            if (titleHeight == 0) {
                measuredHeight3 = this.B.getMeasuredHeight();
            } else {
                measuredHeight3 = 0;
            }
        } else {
            measuredHeight3 = 0;
        }
        if (this.C != null) {
            int subtitleHeight = this.C.getVisibility();
            if (subtitleHeight == 0) {
                int linkDescHeight2 = this.C.getMeasuredHeight();
                int subtitleHeight2 = M;
                int linkDescHeight3 = linkDescHeight2 + subtitleHeight2;
                int subtitleHeight3 = N;
                linkDescHeight = linkDescHeight3 + subtitleHeight3;
            }
        }
        int measuredHeight4 = getMeasuredHeight();
        int subtitleHeight4 = getMeasuredHeight();
        return ((((measuredHeight4 - subtitleHeight4) - measuredHeight) - measuredHeight2) - measuredHeight3) - linkDescHeight;
    }

    private void I() {
        if (this.E != null) {
            this.E.setLines(1);
        }
        if (this.D != null) {
            this.D.setLines(1);
        }
        if (this.B != null) {
            this.B.setLines(1);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public View getView() {
        return this;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public ArrayList<View> getViewsForInteraction() {
        return this.I;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        layout(l, t, r, getMeasuredHeight() + t);
        int t2 = t + getMeasuredHeight();
        if (this.E != null && this.E.getVisibility() == 0) {
            int measuredHeight = this.E.getMeasuredHeight();
            this.E.layout(l, t2, r, t2 + measuredHeight);
            t2 += measuredHeight;
        }
        this.F.layout(l, t2, r, this.F.getMeasuredHeight() + t2);
        int t3 = t2 + this.F.getMeasuredHeight();
        if (this.D != null) {
            this.D.layout(l, t3, r, this.D.getMeasuredHeight() + t3);
            t3 += this.D.getMeasuredHeight();
        }
        if (this.B != null && this.B.getVisibility() == 0) {
            this.B.layout(l, t3, r, this.B.getMeasuredHeight() + t3);
        }
        if (this.C != null) {
            this.C.layout(M + l, (b - this.C.getMeasuredHeight()) - M, r - M, b - M);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iMin;
        G(View.MeasureSpec.getSize(heightMeasureSpec));
        I();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iH = H();
        if (this.F.getMediaWidth() == 0 || this.F.getMediaHeight() == 0) {
            iMin = iH;
        } else {
            int requiredHeight = (int) (this.F.getMeasuredWidth() * (this.F.getMediaHeight() / this.F.getMediaWidth()));
            iMin = Math.min(requiredHeight, iH);
        }
        this.F.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(iMin, 1073741824));
        if (iMin < iH) {
            F(widthMeasureSpec, iH - iMin, this.D, this.B, this.E);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public final void unregisterView() {
        this.G.unregisterView();
    }
}
