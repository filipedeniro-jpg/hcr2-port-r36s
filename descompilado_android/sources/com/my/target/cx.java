package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: FramedImageView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cx extends FrameLayout {

    @NonNull
    private final ImageView V;

    public cx(@NonNull Context context) {
        super(context);
        this.V = new ImageView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(this.V, layoutParams);
    }

    public final void setImageBitmap(@Nullable Bitmap bitmap) {
        this.V.setImageBitmap(bitmap);
    }
}
