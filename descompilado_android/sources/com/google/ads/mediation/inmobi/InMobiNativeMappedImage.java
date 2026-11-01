package com.google.ads.mediation.inmobi;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd;

/* JADX INFO: loaded from: classes.dex */
class InMobiNativeMappedImage extends NativeAd.Image {
    private final Drawable mInMobiDrawable;
    private final double mInMobiScale;
    private final Uri mInmobiImageUri;

    public InMobiNativeMappedImage(Drawable drawable, Uri uri, double d) {
        this.mInMobiDrawable = drawable;
        this.mInmobiImageUri = uri;
        this.mInMobiScale = d;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public Drawable getDrawable() {
        return this.mInMobiDrawable;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public Uri getUri() {
        return this.mInmobiImageUri;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.Image
    public double getScale() {
        return this.mInMobiScale;
    }
}
