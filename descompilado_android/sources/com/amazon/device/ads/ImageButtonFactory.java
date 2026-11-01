package com.amazon.device.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

/* JADX INFO: compiled from: ImageViewFactory.java */
/* JADX INFO: loaded from: classes.dex */
class ImageButtonFactory implements ImageViewFactory {
    ImageButtonFactory() {
    }

    @Override // com.amazon.device.ads.ImageViewFactory
    public BitmapDrawable createBitmapDrawable(Resources resources, String str) {
        return new BitmapDrawable(resources, str);
    }

    @Override // com.amazon.device.ads.ImageViewFactory
    public ImageButton createImageView(Context context, String str) {
        ImageButton imageButton = new ImageButton(context);
        imageButton.setContentDescription(str);
        return imageButton;
    }
}
