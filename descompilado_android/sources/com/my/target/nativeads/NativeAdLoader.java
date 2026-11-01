package com.my.target.nativeads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.my.target.b;
import com.my.target.c;
import com.my.target.common.BaseAd;
import com.my.target.core.models.sections.a;
import com.my.target.cr;
import com.my.target.g;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeAdLoader extends BaseAd {

    @Nullable
    private c<a> adFactory;

    @NonNull
    private final Context appContext;

    @Nullable
    private OnLoad onLoad;

    public interface OnLoad {
        void onLoad(@NonNull List<NativeAd> list);
    }

    @NonNull
    public static NativeAdLoader newLoader(int i, int i2, @NonNull Context context) {
        return new NativeAdLoader(i, i2, context);
    }

    private NativeAdLoader(int i, int i2, @NonNull Context context) {
        super(i, b.a.f);
        if (i2 <= 0) {
            i2 = 1;
            g.a("NativeAdLoader: invalid bannersCount < 1, bannersCount set to 1");
        }
        this.adConfig.setBannersCount(i2);
        this.appContext = context.getApplicationContext();
        g.c("NativeAdLoader created. Version: 5.3.9");
    }

    @NonNull
    @UiThread
    public final NativeAdLoader setOnLoad(@Nullable OnLoad onLoad) {
        this.onLoad = onLoad;
        return this;
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

    @NonNull
    @UiThread
    public final NativeAdLoader load() {
        final c<a> cVarNewFactory = cr.newFactory(this.adConfig);
        this.adFactory = cVarNewFactory;
        cVarNewFactory.a(new cr.b() { // from class: com.my.target.nativeads.NativeAdLoader.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable a aVar, @Nullable String str) {
                if (cVarNewFactory == NativeAdLoader.this.adFactory) {
                    NativeAdLoader.this.adFactory = null;
                    NativeAdLoader.this.handleResult(aVar, str);
                }
            }
        }).a(this.appContext);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable a aVar, @Nullable String str) {
        if (this.onLoad != null) {
            List<com.my.target.core.models.banners.a> listR = aVar == null ? null : aVar.R();
            if (listR == null || listR.size() <= 0) {
                this.onLoad.onLoad(new ArrayList());
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (com.my.target.core.models.banners.a aVar2 : listR) {
                NativeAd nativeAd = new NativeAd(this.adConfig.getSlotId(), this.appContext);
                nativeAd.setAutoLoadImages(this.adConfig.isAutoLoadImages());
                nativeAd.setAutoLoadVideo(this.adConfig.isAutoLoadVideo());
                nativeAd.setTrackingLocationEnabled(this.adConfig.isTrackingLocationEnabled());
                nativeAd.setTrackingEnvironmentEnabled(this.adConfig.isTrackingEnvironmentEnabled());
                nativeAd.setBanner(aVar2);
                arrayList.add(nativeAd);
            }
            this.onLoad.onLoad(arrayList);
        }
    }
}
