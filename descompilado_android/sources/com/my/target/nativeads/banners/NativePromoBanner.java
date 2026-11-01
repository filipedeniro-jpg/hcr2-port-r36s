package com.my.target.nativeads.banners;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.core.models.banners.a;
import com.my.target.core.models.banners.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NativePromoBanner {

    @Nullable
    private final String advertisingLabel;

    @Nullable
    private final String ageRestrictions;

    @Nullable
    private final String category;

    @Nullable
    private final String ctaText;

    @Nullable
    private final String description;

    @Nullable
    private final String disclaimer;

    @Nullable
    private final String domain;
    private final boolean hasVideo;

    @Nullable
    private final ImageData icon;

    @Nullable
    private final ImageData image;

    @NonNull
    private final ArrayList<NativePromoCard> nativePromoCards = new ArrayList<>();

    @NonNull
    private final String navigationType;
    private final float rating;

    @Nullable
    private final String subCategory;

    @Nullable
    private final String title;
    private final int votes;

    @NonNull
    public static NativePromoBanner newBanner(@NonNull a aVar) {
        return new NativePromoBanner(aVar);
    }

    private NativePromoBanner(@NonNull a aVar) {
        this.navigationType = aVar.getNavigationType();
        this.rating = aVar.getRating();
        this.votes = aVar.getVotes();
        this.hasVideo = aVar.getVideoBanner() != null;
        String title = aVar.getTitle();
        this.title = TextUtils.isEmpty(title) ? null : title;
        String description = aVar.getDescription();
        this.description = TextUtils.isEmpty(description) ? null : description;
        String ctaText = aVar.getCtaText();
        this.ctaText = TextUtils.isEmpty(ctaText) ? null : ctaText;
        String disclaimer = aVar.getDisclaimer();
        this.disclaimer = TextUtils.isEmpty(disclaimer) ? null : disclaimer;
        String ageRestrictions = aVar.getAgeRestrictions();
        this.ageRestrictions = TextUtils.isEmpty(ageRestrictions) ? null : ageRestrictions;
        String category = aVar.getCategory();
        this.category = TextUtils.isEmpty(category) ? null : category;
        String subCategory = aVar.getSubCategory();
        this.subCategory = TextUtils.isEmpty(subCategory) ? null : subCategory;
        String domain = aVar.getDomain();
        this.domain = TextUtils.isEmpty(domain) ? null : domain;
        String advertisingLabel = aVar.getAdvertisingLabel();
        this.advertisingLabel = TextUtils.isEmpty(advertisingLabel) ? null : advertisingLabel;
        this.image = aVar.getImage();
        this.icon = aVar.getIcon();
        processCards(aVar);
    }

    private void processCards(@NonNull a aVar) {
        if (this.hasVideo) {
            return;
        }
        List<b> nativeAdCards = aVar.getNativeAdCards();
        if (nativeAdCards.isEmpty()) {
            return;
        }
        Iterator<b> it = nativeAdCards.iterator();
        while (it.hasNext()) {
            this.nativePromoCards.add(NativePromoCard.newCard(it.next()));
        }
    }

    @Nullable
    public ImageData getIcon() {
        return this.icon;
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
    public String getDisclaimer() {
        return this.disclaimer;
    }

    @Nullable
    public String getAgeRestrictions() {
        return this.ageRestrictions;
    }

    public float getRating() {
        return this.rating;
    }

    public int getVotes() {
        return this.votes;
    }

    @Nullable
    public String getCategory() {
        return this.category;
    }

    @Nullable
    public String getSubCategory() {
        return this.subCategory;
    }

    @Nullable
    public String getDomain() {
        return this.domain;
    }

    @NonNull
    public String getNavigationType() {
        return this.navigationType;
    }

    @Nullable
    public ImageData getImage() {
        return this.image;
    }

    @Nullable
    public String getAdvertisingLabel() {
        return this.advertisingLabel;
    }

    public boolean hasVideo() {
        return this.hasVideo;
    }

    @NonNull
    public ArrayList<NativePromoCard> getCards() {
        return this.nativePromoCards;
    }
}
