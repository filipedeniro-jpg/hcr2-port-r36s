package com.inmobi.rendering.mraid;

import android.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.RenderView;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: MraidResizeProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {
    private static final String d = "f";
    public RenderView a;
    public ViewGroup b;
    public int c;

    public f(RenderView renderView) {
        this.a = renderView;
    }

    public final void a() {
        char c;
        if (this.b == null) {
            this.b = (ViewGroup) this.a.getParent();
            this.c = this.b.indexOfChild(this.a);
        }
        h resizeProperties = this.a.getResizeProperties();
        FrameLayout frameLayout = new FrameLayout(this.a.getContainerContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(this.a.getWidth(), this.a.getHeight());
        frameLayout.setId(65535);
        this.b.addView(frameLayout, this.c, layoutParams);
        this.b.removeView(this.a);
        float f = com.inmobi.commons.core.utilities.b.c.a().c;
        int i = (int) ((resizeProperties.b * f) + 0.5f);
        int i2 = (int) ((resizeProperties.c * f) + 0.5f);
        FrameLayout frameLayout2 = (FrameLayout) this.b.getRootView().findViewById(R.id.content);
        FrameLayout frameLayout3 = new FrameLayout(this.a.getContainerContext());
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = new RelativeLayout(this.a.getContainerContext());
        ViewGroup.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i, i2);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i, i2);
        frameLayout3.setId(65534);
        if (this.a.getParent() != null) {
            ((ViewGroup) this.a.getParent()).removeAllViews();
        }
        relativeLayout.addView(this.a, layoutParams4);
        String str = resizeProperties.a;
        float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
        CustomView customView = new CustomView(this.a.getContainerContext(), f2, 1);
        customView.setId(65531);
        customView.setOnClickListener(new View.OnClickListener() { // from class: com.inmobi.rendering.mraid.f.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                f.this.a.b();
            }
        });
        if (str == null || str.length() == 0 || (!str.equals(Constants.ForceClosePosition.TOP_LEFT) && !str.equals(Constants.ForceClosePosition.TOP_RIGHT) && !str.equals(Constants.ForceClosePosition.BOTTOM_LEFT) && !str.equals(Constants.ForceClosePosition.BOTTOM_RIGHT) && !str.equals("top-center") && !str.equals("bottom-center") && !str.equals(TtmlNode.CENTER))) {
            str = Constants.ForceClosePosition.TOP_RIGHT;
        }
        int i3 = (int) (f2 * 50.0f);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i3, i3);
        if (str.equals(Constants.ForceClosePosition.TOP_RIGHT) || str.equals(Constants.ForceClosePosition.BOTTOM_RIGHT)) {
            layoutParams5.addRule(11);
        }
        if (str.equals(Constants.ForceClosePosition.BOTTOM_RIGHT) || str.equals(Constants.ForceClosePosition.BOTTOM_LEFT) || str.equals("bottom-center")) {
            layoutParams5.addRule(12);
            layoutParams5.addRule(4);
        }
        if (str.equals("bottom-center") || str.equals("top-center") || str.equals(TtmlNode.CENTER)) {
            layoutParams5.addRule(13);
        }
        if (str.equals("top-center")) {
            layoutParams5.addRule(10);
        }
        relativeLayout.addView(customView, layoutParams5);
        frameLayout3.addView(relativeLayout, layoutParams3);
        frameLayout2.addView(frameLayout3, layoutParams2);
        float f3 = com.inmobi.commons.core.utilities.b.c.a().c;
        int i4 = (int) ((resizeProperties.b * f3) + 0.5f);
        int i5 = (int) ((resizeProperties.c * f3) + 0.5f);
        int i6 = (int) ((resizeProperties.d * f3) + 0.5f);
        int i7 = (int) ((resizeProperties.e * f3) + 0.5f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.b.getLocationOnScreen(iArr);
        frameLayout2.getLocationOnScreen(iArr2);
        iArr[1] = iArr[1] - iArr2[1];
        iArr[0] = iArr[0] - iArr2[0];
        iArr[0] = iArr[0] + i6;
        iArr[1] = iArr[1] + i7;
        if (resizeProperties.f) {
            c = 1;
        } else {
            if (i4 > frameLayout2.getWidth() - iArr[0]) {
                iArr[0] = frameLayout2.getWidth() - i4;
            }
            c = 1;
            if (i5 > frameLayout2.getHeight() - iArr[1]) {
                iArr[1] = frameLayout2.getHeight() - i5;
            }
            if (iArr[0] < 0) {
                iArr[0] = 0;
            }
            if (iArr[1] < 0) {
                iArr[1] = 0;
            }
        }
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(i4, i5);
        layoutParams6.leftMargin = iArr[0];
        layoutParams6.topMargin = iArr[c];
        layoutParams6.gravity = GravityCompat.START;
        frameLayout3.setLayoutParams(layoutParams6);
        frameLayout3.setBackgroundColor(0);
    }
}
