package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: FramedImageView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ef extends FrameLayout {

    @NonNull
    private final ImageView bF;

    public ef(@NonNull Context context) {
        super(context);
        this.bF = new ImageView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(this.bF, layoutParams);
    }

    public final void setImageBitmap(@NonNull Bitmap bitmap) {
        this.bF.setImageBitmap(bitmap);
    }
}
