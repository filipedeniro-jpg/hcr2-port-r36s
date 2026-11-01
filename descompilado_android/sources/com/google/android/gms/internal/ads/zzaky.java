package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaky extends zzajv {
    private final UnifiedNativeAdMapper zzdef;

    public zzaky(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zzdef = unifiedNativeAdMapper;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final zzaak zzqk() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getHeadline() {
        return this.zzdef.getHeadline();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final List getImages() {
        List<NativeAd.Image> images = this.zzdef.getImages();
        ArrayList arrayList = new ArrayList();
        if (images != null) {
            for (NativeAd.Image image : images) {
                arrayList.add(new zzaae(image.getDrawable(), image.getUri(), image.getScale(), image.getWidth(), image.getHeight()));
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getBody() {
        return this.zzdef.getBody();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final zzaas zzqj() {
        NativeAd.Image icon = this.zzdef.getIcon();
        if (icon != null) {
            return new zzaae(icon.getDrawable(), icon.getUri(), icon.getScale(), icon.getWidth(), icon.getHeight());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getCallToAction() {
        return this.zzdef.getCallToAction();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getAdvertiser() {
        return this.zzdef.getAdvertiser();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final double getStarRating() {
        if (this.zzdef.getStarRating() != null) {
            return this.zzdef.getStarRating().doubleValue();
        }
        return -1.0d;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getStore() {
        return this.zzdef.getStore();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final String getPrice() {
        return this.zzdef.getPrice();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final zzwk getVideoController() {
        if (this.zzdef.getVideoController() != null) {
            return this.zzdef.getVideoController().zzdd();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final IObjectWrapper zzru() {
        View adChoicesContent = this.zzdef.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final IObjectWrapper zzrv() {
        View viewZzaaw = this.zzdef.zzaaw();
        if (viewZzaaw == null) {
            return null;
        }
        return ObjectWrapper.wrap(viewZzaaw);
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final float getMediaContentAspectRatio() {
        return this.zzdef.getMediaContentAspectRatio();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final IObjectWrapper zzql() {
        Object objZzjf = this.zzdef.zzjf();
        if (objZzjf == null) {
            return null;
        }
        return ObjectWrapper.wrap(objZzjf);
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final Bundle getExtras() {
        return this.zzdef.getExtras();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final boolean getOverrideImpressionRecording() {
        return this.zzdef.getOverrideImpressionRecording();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final boolean getOverrideClickHandling() {
        return this.zzdef.getOverrideClickHandling();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final void recordImpression() {
        this.zzdef.recordImpression();
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final void zzx(IObjectWrapper iObjectWrapper) {
        this.zzdef.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdef.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.ads.zzajw
    public final void zzz(IObjectWrapper iObjectWrapper) {
        this.zzdef.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
