package com.amazon.device.ads;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
class ImageResponseReader extends ResponseReader {
    final GraphicsUtils graphicsUtils;

    ImageResponseReader(ResponseReader responseReader, GraphicsUtils graphicsUtils) {
        super(responseReader.getInputStream());
        this.graphicsUtils = graphicsUtils;
    }

    public Bitmap readAsBitmap() {
        return this.graphicsUtils.createBitmapImage(getInputStream());
    }
}
