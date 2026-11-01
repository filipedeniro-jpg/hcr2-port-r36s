package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0625Jm extends FrameLayout implements InterfaceC0624Jl {
    private static final int F = (int) (J6.B * 110.0f);
    private final AdOptionsView B;
    private final KF C;
    private final KI D;
    private ArrayList<View> E;

    public C0625Jm(Context context, KF nativeAd, AdOptionsView adOptionsView, @Nullable MediaView mediaView, View adIconView, KJ type, KI attributes) {
        super(context);
        this.D = attributes;
        this.C = nativeAd;
        this.B = adOptionsView;
        this.E = new ArrayList<>();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        switch (type) {
            case HEIGHT_400:
                D(linearLayout);
            case HEIGHT_300:
                C(linearLayout, mediaView);
                break;
        }
        B(linearLayout, type, adIconView);
        addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
    }

    private void B(ViewGroup container, KJ type, View adIconView) {
        C0404Ax c0404Ax = new C0404Ax(getContext(), this.C, this.D, adIconView, this.B, F(type), E(type));
        c0404Ax.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (E(type) * J6.B)));
        container.addView(c0404Ax);
        this.E.add(c0404Ax.getIconView());
        this.E.add(c0404Ax.getCallToActionView());
    }

    private void C(ViewGroup container, RelativeLayout mediaView) {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (J6.B * 180.0f)));
        J6.O(relativeLayout, this.D.A());
        relativeLayout.addView(mediaView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (J6.B * 180.0f));
        layoutParams.addRule(13, -1);
        mediaView.setLayoutParams(layoutParams);
        container.addView(relativeLayout);
        this.E.add(mediaView);
    }

    private void D(ViewGroup container) {
        C0398Ar c0398Ar = new C0398Ar(getContext(), this.C, this.D);
        c0398Ar.setLayoutParams(new LinearLayout.LayoutParams(-1, F));
        container.addView(c0398Ar);
    }

    private static int E(KJ type) {
        switch (type) {
            case HEIGHT_400:
                return (type.A() - 180) / 2;
            case HEIGHT_300:
                return type.A() - 180;
            case HEIGHT_50:
            case HEIGHT_100:
            case HEIGHT_120:
                return type.A();
            default:
                return 0;
        }
    }

    private static boolean F(KJ type) {
        return type == KJ.HEIGHT_300 || type == KJ.HEIGHT_120;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public View getView() {
        return this;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public ArrayList<View> getViewsForInteraction() {
        return this.E;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0624Jl
    public final void unregisterView() {
        this.C.unregisterView();
    }
}
