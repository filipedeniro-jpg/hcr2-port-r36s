package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeBannerAd;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7c, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03057c extends LinearLayout implements InterfaceC0624Jl {
    private final NativeBannerAd B;
    private final ArrayList<View> C;
    private static final int E = (int) (J6.B * 42.0f);
    private static final int D = (int) (J6.B * 48.0f);
    private static final int F = (int) (J6.B * 54.0f);
    private static final int H = (int) (J6.B * 4.0f);
    private static final int G = (int) (J6.B * 8.0f);

    public C03057c(Context context, NativeBannerAd nativeAd, final KI attributes, final KJ type, MediaView adIconView, final AdOptionsView adOptionsView) {
        LinearLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams iconContainerParams;
        super(context);
        this.C = new ArrayList<>();
        this.B = nativeAd;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        int iB = B(type);
        C0401Au c0401Au = new C0401Au(getContext());
        c0401Au.setFullCircleCorners(true);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(iB, iB);
        layoutParams2.gravity = 16;
        c0401Au.addView(adIconView, new LinearLayout.LayoutParams(-1, -1));
        linearLayout.addView(c0401Au, layoutParams2);
        final Context context2 = getContext();
        final NativeBannerAd nativeBannerAd = this.B;
        LinearLayout linearLayout2 = new LinearLayout(context2, nativeBannerAd, type, attributes, adOptionsView) { // from class: com.facebook.ads.redexgen.X.7e
            {
                TextView textView = new TextView(getContext());
                attributes.I(textView);
                textView.setText(nativeBannerAd.getAdvertiserName());
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setMaxLines(1);
                TextView textView2 = new TextView(getContext());
                attributes.G(textView2);
                textView2.setText(nativeBannerAd.getAdUntrimmedBodyText());
                textView2.setLines(type == KJ.HEIGHT_120 ? 2 : 1);
                LinearLayout linearLayout3 = new LinearLayout(getContext());
                linearLayout3.setOrientation(0);
                linearLayout3.setGravity(16);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2);
                layoutParams3.weight = 1.0f;
                linearLayout3.addView(textView, layoutParams3);
                linearLayout3.addView(adOptionsView, new LinearLayout.LayoutParams(-2, -2));
                setOrientation(1);
                ViewGroup.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                addView(linearLayout3, layoutParams4);
                addView(textView2, layoutParams4);
            }
        };
        linearLayout2.setPadding(G, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2);
        layoutParams3.weight = 1.0f;
        layoutParams3.gravity = 16;
        linearLayout.addView(linearLayout2, layoutParams3);
        if (type == KJ.HEIGHT_50) {
            int i = H;
            int i2 = H;
            int i3 = H;
            int iconSize = H;
            setPadding(i, i2, i3, iconSize);
            setOrientation(0);
            layoutParams = new LinearLayout.LayoutParams(0, -1);
            iconContainerParams = new LinearLayout.LayoutParams(-2, -1);
            linearLayout.setPadding(0, 0, H, 0);
        } else {
            setPadding(G, G, G, G);
            setOrientation(1);
            layoutParams = new LinearLayout.LayoutParams(-1, 0);
            iconContainerParams = new LinearLayout.LayoutParams(-1, -2);
            linearLayout.setPadding(0, 0, 0, G);
        }
        layoutParams.weight = 1.0f;
        addView(linearLayout, layoutParams);
        TextView textView = new TextView(getContext());
        textView.setPadding(G, H, G, H);
        attributes.F(textView);
        textView.setText(this.B.getAdCallToAction());
        addView(textView, iconContainerParams);
        this.C.add(adIconView);
        this.C.add(textView);
    }

    private static int B(KJ type) {
        switch (type) {
            case HEIGHT_50:
                return E;
            case HEIGHT_100:
                return D;
            default:
                return F;
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public View getView() {
        return this;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public ArrayList<View> getViewsForInteraction() {
        return this.C;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public final void unregisterView() {
        this.B.unregisterView();
    }
}
