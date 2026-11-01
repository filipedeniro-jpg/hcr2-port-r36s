package com.facebook.ads.redexgen.X;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C9L extends FrameLayout {
    public final C9M B;

    public C9L(AF params, C1Y colorInfo, int availableHeight, int availableWidth, CP listener) {
        super(params.B());
        boolean z = params.H() != 1;
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        new B7(imageView, 12, 8).D(z ? J6.C.heightPixels : availableHeight, z ? availableWidth : J6.C.widthPixels).A(JA.JB(getContext())).E(params.C().A().get(0).C().B());
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        addView(imageView);
        FrameLayout tintLayout = new FrameLayout(params.B());
        addView(tintLayout, layoutParams);
        tintLayout.setBackgroundColor(-1507645649);
        FrameLayout frameLayout = new FrameLayout(params.B());
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(z ? availableWidth : -1, z ? -1 : availableHeight);
        layoutParams2.gravity = 48;
        addView(frameLayout, layoutParams2);
        this.B = new C9M(params, colorInfo, listener);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 17;
        frameLayout.addView(this.B, layoutParams3);
    }

    public final void A(String title, String description, String sponsoredText, boolean isCarousel, boolean longerDescription) {
        this.B.A(title, description, sponsoredText, isCarousel, longerDescription);
    }
}
