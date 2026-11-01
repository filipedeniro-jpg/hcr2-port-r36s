package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import com.my.target.ag;
import com.my.target.ai;

/* JADX INFO: compiled from: InterstitialAdCard.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends ai {
    @NonNull
    public static f newCard(@NonNull e eVar) {
        f fVar = new f();
        fVar.ctaText = eVar.ctaText;
        fVar.navigationType = eVar.navigationType;
        fVar.urlscheme = eVar.urlscheme;
        fVar.bundleId = eVar.bundleId;
        fVar.directLink = eVar.directLink;
        fVar.openInBrowser = eVar.openInBrowser;
        fVar.usePlayStoreAction = eVar.usePlayStoreAction;
        fVar.deeplink = eVar.deeplink;
        fVar.clickArea = eVar.clickArea;
        fVar.rating = eVar.rating;
        fVar.votes = eVar.votes;
        fVar.domain = eVar.domain;
        fVar.category = eVar.category;
        fVar.subCategory = eVar.subCategory;
        return fVar;
    }

    private f() {
        this.clickArea = ag.ct;
    }
}
