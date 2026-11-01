package com.my.target.nativeads;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.b;
import com.my.target.cf;
import com.my.target.common.BaseAd;
import com.my.target.common.models.ImageData;
import com.my.target.core.engines.a;
import com.my.target.cr;
import com.my.target.g;
import com.my.target.nativeads.banners.NativePromoBanner;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeAd extends BaseAd {
    private int adChoicesPlacement;

    @NonNull
    private final Context appContext;

    @Nullable
    private NativePromoBanner banner;

    @Nullable
    private NativeAdListener listener;

    @Nullable
    private a nativeAdEngine;

    public interface AdChoicesPlacement {
        public static final int aY = 1;
        public static final int ba = 0;
        public static final int bb = 3;
        public static final int bd = 2;
    }

    public interface NativeAdListener {
        void onClick(@NonNull NativeAd nativeAd);

        void onLoad(@NonNull NativeAd nativeAd);

        void onNoAd(@NonNull String str, @NonNull NativeAd nativeAd);

        void onShow(@NonNull NativeAd nativeAd);

        void onVideoComplete(@NonNull NativeAd nativeAd);

        void onVideoPause(@NonNull NativeAd nativeAd);

        void onVideoPlay(@NonNull NativeAd nativeAd);
    }

    public static void loadImageToView(@NonNull ImageData imageData, @NonNull ImageView imageView) {
        cf.a(imageData, imageView);
    }

    public NativeAd(int i, @NonNull Context context) {
        super(i, b.a.f);
        this.adChoicesPlacement = 0;
        this.appContext = context.getApplicationContext();
        g.c("NativeAd created. Version: 5.3.9");
    }

    public final void setAutoLoadVideo(boolean z) {
        this.adConfig.setAutoLoadVideo(z);
    }

    public final void setAutoLoadImages(boolean z) {
        this.adConfig.setAutoLoadImages(z);
    }

    public final boolean isAutoLoadImages() {
        return this.adConfig.isAutoLoadImages();
    }

    public final boolean isAutoLoadVideo() {
        return this.adConfig.isAutoLoadVideo();
    }

    @Nullable
    public final NativeAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable NativeAdListener nativeAdListener) {
        this.listener = nativeAdListener;
    }

    @Nullable
    public final NativePromoBanner getBanner() {
        return this.banner;
    }

    public final int getAdChoicesPlacement() {
        return this.adChoicesPlacement;
    }

    public final void setAdChoicesPlacement(int i) {
        this.adChoicesPlacement = i;
    }

    public final void load() {
        cr.newFactory(this.adConfig).a(new cr.b() { // from class: com.my.target.nativeads.NativeAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable com.my.target.core.models.sections.a aVar, @Nullable String str) {
                NativeAd.this.handleResult(aVar, str);
            }
        }).a(this.appContext);
    }

    public final void loadFromBid(@NonNull String str) {
        this.adConfig.setBidId(str);
        load();
    }

    public final void registerView(@NonNull View view, @Nullable List<View> list) {
        a aVar = this.nativeAdEngine;
        if (aVar != null) {
            aVar.a(view, list, this.adChoicesPlacement);
        }
    }

    public final void registerView(@NonNull View view) {
        registerView(view, null);
    }

    public final void unregisterView() {
        a aVar = this.nativeAdEngine;
        if (aVar != null) {
            aVar.unregisterView();
        }
    }

    final void setBanner(@NonNull com.my.target.core.models.banners.a aVar) {
        this.nativeAdEngine = a.a(this, aVar);
        this.banner = NativePromoBanner.newBanner(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable com.my.target.core.models.sections.a aVar, @Nullable String str) {
        if (this.listener != null) {
            com.my.target.core.models.banners.a aVarI = aVar == null ? null : aVar.I();
            if (aVarI == null) {
                NativeAdListener nativeAdListener = this.listener;
                if (str == null) {
                    str = "no ad";
                }
                nativeAdListener.onNoAd(str, this);
                return;
            }
            this.nativeAdEngine = a.a(this, aVarI);
            this.banner = NativePromoBanner.newBanner(aVarI);
            this.listener.onLoad(this);
        }
    }
}
