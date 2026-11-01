package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class BA extends FrameLayout {
    private final ImageView B;
    private final ImageView C;
    private int D;
    private int E;

    public BA(Context context) {
        super(context);
        this.C = new ImageView(context);
        this.B = new ImageView(context);
        B();
    }

    public BA(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.C = new ImageView(context, attrs);
        this.B = new ImageView(context, attrs);
        B();
    }

    public BA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.C = new ImageView(context, attrs, defStyleAttr);
        this.B = new ImageView(context, attrs, defStyleAttr);
        B();
    }

    @TargetApi(21)
    public BA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.C = new ImageView(context, attrs, defStyleAttr, defStyleRes);
        this.B = new ImageView(context, attrs, defStyleAttr, defStyleRes);
        B();
    }

    private void B() {
        addView(this.B, new FrameLayout.LayoutParams(-1, -1));
        addView(this.C, new FrameLayout.LayoutParams(-2, -2));
        MZ.C(this.C, MZ.INTERNAL_AD_MEDIA);
        setId(J6.D());
    }

    public ImageView getBodyImageView() {
        return this.C;
    }

    public int getImageHeight() {
        return this.D;
    }

    public int getImageWidth() {
        return this.E;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.E <= 0 || this.D <= 0) {
            super.onLayout(changed, l, t, r, b);
            return;
        }
        int i = r - l;
        int i2 = b - t;
        float fMin = Math.min(i / this.E, i2 / this.D);
        int i3 = (int) (this.E * fMin);
        int expectedImageWidth = this.D;
        int i4 = (int) (expectedImageWidth * fMin);
        this.B.layout(l, t, r, b);
        int expectedImageHeight = i / 2;
        int l2 = l + expectedImageHeight;
        int centerX = i2 / 2;
        int t2 = t + centerX;
        int centerY = i3 / 2;
        int i5 = l2 - centerY;
        int centerY2 = i4 / 2;
        int centerY3 = i4 / 2;
        this.C.layout(i5, t2 - centerY2, (i3 / 2) + l2, centerY3 + t2);
        this.B.setVisibility(0);
    }

    public void setImage(@Nullable Bitmap bitmap, @Nullable Bitmap blurBitmap) {
        if (blurBitmap != null) {
            J6.P(this.B, new BitmapDrawable(getContext().getResources(), blurBitmap));
        } else {
            J6.O(this.B, 0);
        }
        if (bitmap != null) {
            this.E = bitmap.getWidth();
            this.D = bitmap.getHeight();
            this.C.setImageBitmap(Bitmap.createBitmap(bitmap));
            return;
        }
        this.C.setImageDrawable(null);
    }
}
