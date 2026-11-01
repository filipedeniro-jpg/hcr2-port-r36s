package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.aj;
import com.my.target.common.models.ImageData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: InterstitialAdImageBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends e {

    @Nullable
    private ImageData optimalLandscapeImage;

    @Nullable
    private ImageData optimalPortraitImage;

    @NonNull
    private final List<ImageData> portraitImages = new ArrayList();

    @NonNull
    private final List<ImageData> landscapeImages = new ArrayList();

    @NonNull
    public static h newBanner() {
        return new h();
    }

    @NonNull
    public static h fromCompanion(@NonNull aj ajVar) {
        h hVarNewBanner = newBanner();
        hVarNewBanner.setId(ajVar.getId());
        String staticResource = ajVar.getStaticResource();
        if (staticResource != null) {
            hVarNewBanner.addPortraitImage(ImageData.newImageData(staticResource, ajVar.getWidth(), ajVar.getHeight()));
            hVarNewBanner.getStatHolder().a(ajVar.getStatHolder(), 0.0f);
            hVarNewBanner.trackingLink = ajVar.trackingLink;
        }
        return hVarNewBanner;
    }

    private h() {
    }

    @NonNull
    public final List<ImageData> getPortraitImages() {
        return new ArrayList(this.portraitImages);
    }

    @NonNull
    public final List<ImageData> getLandscapeImages() {
        return new ArrayList(this.landscapeImages);
    }

    @Nullable
    public final ImageData getOptimalPortraitImage() {
        return this.optimalPortraitImage;
    }

    public final void setOptimalPortraitImage(@Nullable ImageData imageData) {
        this.optimalPortraitImage = imageData;
    }

    @Nullable
    public final ImageData getOptimalLandscapeImage() {
        return this.optimalLandscapeImage;
    }

    public final void setOptimalLandscapeImage(@Nullable ImageData imageData) {
        this.optimalLandscapeImage = imageData;
    }

    public final void addPortraitImage(@NonNull ImageData imageData) {
        this.portraitImages.add(imageData);
    }

    public final void addLandscapeImage(@NonNull ImageData imageData) {
        this.landscapeImages.add(imageData);
    }
}
