package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;
import com.my.target.ak;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: NativeAdBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends ai {

    @Nullable
    private ak<VideoData> videoBanner;

    @NonNull
    private final List<b> nativeAdCards = new ArrayList();
    private float viewabilityRate = 1.0f;
    private float viewabilitySquare = 0.5f;

    @NonNull
    public static a newBanner() {
        return new a();
    }

    public final void setViewabilitySquare(float f) {
        this.viewabilitySquare = f;
    }

    public final float getViewabilityRate() {
        return this.viewabilityRate;
    }

    public final void setViewabilityRate(float f) {
        this.viewabilityRate = f;
    }

    private a() {
    }

    public final void setVideoBanner(@Nullable ak<VideoData> akVar) {
        this.videoBanner = akVar;
    }

    @Nullable
    public final ak<VideoData> getVideoBanner() {
        return this.videoBanner;
    }

    public final void addNativeAdCard(@NonNull b bVar) {
        this.nativeAdCards.add(bVar);
    }

    @NonNull
    public final List<b> getNativeAdCards() {
        return new ArrayList(this.nativeAdCards);
    }

    public final float getViewabilitySquare() {
        return this.viewabilitySquare;
    }
}
