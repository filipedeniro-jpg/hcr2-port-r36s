package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.aj;

/* JADX INFO: compiled from: InterstitialAdHtmlBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends e {

    @Nullable
    private String source;

    @NonNull
    public static g newBanner() {
        return new g();
    }

    @NonNull
    public static g fromCompanion(@NonNull aj ajVar) {
        g gVarNewBanner = newBanner();
        gVarNewBanner.setId(ajVar.getId());
        gVarNewBanner.setSource(ajVar.getHtmlResource());
        gVarNewBanner.getStatHolder().a(ajVar.getStatHolder(), 0.0f);
        gVarNewBanner.trackingLink = ajVar.trackingLink;
        return gVarNewBanner;
    }

    public final void setSource(@Nullable String str) {
        this.source = str;
    }

    @Nullable
    public final String getSource() {
        return this.source;
    }

    private g() {
    }
}
