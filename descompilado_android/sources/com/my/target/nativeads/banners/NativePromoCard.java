package com.my.target.nativeads.banners;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.core.models.banners.b;

/* JADX INFO: loaded from: classes2.dex */
public class NativePromoCard {

    @Nullable
    private final String ctaText;

    @Nullable
    private final String description;

    @Nullable
    private final ImageData image;

    @Nullable
    private final String title;

    @NonNull
    static NativePromoCard newCard(@NonNull b bVar) {
        return new NativePromoCard(bVar);
    }

    private NativePromoCard(@NonNull b bVar) {
        if (!TextUtils.isEmpty(bVar.getTitle())) {
            this.title = bVar.getTitle();
        } else {
            this.title = null;
        }
        if (!TextUtils.isEmpty(bVar.getDescription())) {
            this.description = bVar.getDescription();
        } else {
            this.description = null;
        }
        if (!TextUtils.isEmpty(bVar.getCtaText())) {
            this.ctaText = bVar.getCtaText();
        } else {
            this.ctaText = null;
        }
        this.image = bVar.getImage();
    }

    @Nullable
    public String getTitle() {
        return this.title;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public String getCtaText() {
        return this.ctaText;
    }

    @Nullable
    public ImageData getImage() {
        return this.image;
    }
}
