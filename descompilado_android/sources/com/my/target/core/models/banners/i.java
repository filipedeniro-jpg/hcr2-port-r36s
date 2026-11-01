package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ak;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: InterstitialAdPromoBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i extends e {

    @Nullable
    private e endCard;

    @Nullable
    private ImageData playIcon;

    @Nullable
    private ImageData storeIcon;
    private int style;

    @Nullable
    private ak<VideoData> videoBanner;
    private int footerColor = -39322;
    private int ctaButtonColor = -16733198;
    private int ctaButtonTouchColor = -16746839;
    private int ctaButtonTextColor = -1;

    @NonNull
    private final List<f> interstitialAdCards = new ArrayList();

    @NonNull
    public static i newBanner() {
        return new i();
    }

    private i() {
    }

    public final void setVideoBanner(@Nullable ak<VideoData> akVar) {
        this.videoBanner = akVar;
    }

    @Nullable
    public final ak<VideoData> getVideoBanner() {
        return this.videoBanner;
    }

    @Nullable
    public final ImageData getPlayIcon() {
        return this.playIcon;
    }

    public final void setPlayIcon(@Nullable ImageData imageData) {
        this.playIcon = imageData;
    }

    @Nullable
    public final ImageData getStoreIcon() {
        return this.storeIcon;
    }

    public final void setStoreIcon(@Nullable ImageData imageData) {
        this.storeIcon = imageData;
    }

    public final void setFooterColor(int i) {
        this.footerColor = i;
    }

    public final void setCtaButtonColor(int i) {
        this.ctaButtonColor = i;
    }

    public final void setCtaButtonTouchColor(int i) {
        this.ctaButtonTouchColor = i;
    }

    public final void setCtaButtonTextColor(int i) {
        this.ctaButtonTextColor = i;
    }

    public final int getFooterColor() {
        return this.footerColor;
    }

    public final int getCtaButtonColor() {
        return this.ctaButtonColor;
    }

    public final int getCtaButtonTouchColor() {
        return this.ctaButtonTouchColor;
    }

    public final int getCtaButtonTextColor() {
        return this.ctaButtonTextColor;
    }

    @NonNull
    public final List<f> getInterstitialAdCards() {
        return this.interstitialAdCards;
    }

    public final void addInterstitialAdCard(@NonNull f fVar) {
        this.interstitialAdCards.add(fVar);
    }

    public final int getStyle() {
        return this.style;
    }

    public final void setStyle(int i) {
        this.style = i;
    }

    public final void setEndCard(@Nullable e eVar) {
        this.endCard = eVar;
    }

    @Nullable
    public final e getEndCard() {
        return this.endCard;
    }
}
