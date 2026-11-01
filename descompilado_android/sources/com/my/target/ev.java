package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InterstitialVideoTextureView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ev extends TextureView {
    public ev(@NonNull Context context) {
        super(context);
    }

    @Nullable
    public final Bitmap getScreenShot() {
        try {
            return getBitmap(getWidth(), getHeight());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
