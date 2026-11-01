package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import com.my.target.ai;

/* JADX INFO: compiled from: NativeAdCard.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends ai {
    @NonNull
    public static b newCard(@NonNull a aVar) {
        b bVar = new b();
        bVar.ctaText = aVar.ctaText;
        bVar.navigationType = aVar.navigationType;
        bVar.urlscheme = aVar.urlscheme;
        bVar.bundleId = aVar.bundleId;
        bVar.directLink = aVar.directLink;
        bVar.openInBrowser = aVar.openInBrowser;
        bVar.usePlayStoreAction = aVar.usePlayStoreAction;
        bVar.deeplink = aVar.deeplink;
        bVar.clickArea = aVar.clickArea;
        bVar.rating = aVar.rating;
        bVar.votes = aVar.votes;
        bVar.domain = aVar.domain;
        bVar.category = aVar.category;
        bVar.subCategory = aVar.subCategory;
        return bVar;
    }

    private b() {
    }
}
