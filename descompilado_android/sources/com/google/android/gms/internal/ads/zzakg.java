package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakg extends zzajp {
    private final NativeAppInstallAdMapper zzddv;

    public zzakg(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzddv = nativeAppInstallAdMapper;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final zzaak zzqk() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final IObjectWrapper zzql() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final String getHeadline() {
        return this.zzddv.getHeadline();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final List getImages() {
        List<NativeAd.Image> images = this.zzddv.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image image : images) {
            arrayList.add(new zzaae(image.getDrawable(), image.getUri(), image.getScale(), image.getWidth(), image.getHeight()));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final String getBody() {
        return this.zzddv.getBody();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final zzaas zzqj() {
        NativeAd.Image icon = this.zzddv.getIcon();
        if (icon != null) {
            return new zzaae(icon.getDrawable(), icon.getUri(), icon.getScale(), icon.getWidth(), icon.getHeight());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final String getCallToAction() {
        return this.zzddv.getCallToAction();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final double getStarRating() {
        return this.zzddv.getStarRating();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final String getStore() {
        return this.zzddv.getStore();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final String getPrice() {
        return this.zzddv.getPrice();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final void recordImpression() {
        this.zzddv.recordImpression();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final void zzx(IObjectWrapper iObjectWrapper) {
        this.zzddv.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final void zzy(IObjectWrapper iObjectWrapper) {
        this.zzddv.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzddv.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final void zzz(IObjectWrapper iObjectWrapper) {
        this.zzddv.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final boolean getOverrideImpressionRecording() {
        return this.zzddv.getOverrideImpressionRecording();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final boolean getOverrideClickHandling() {
        return this.zzddv.getOverrideClickHandling();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final Bundle getExtras() {
        return this.zzddv.getExtras();
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final zzwk getVideoController() {
        if (this.zzddv.getVideoController() != null) {
            return this.zzddv.getVideoController().zzdd();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final IObjectWrapper zzru() {
        View adChoicesContent = this.zzddv.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.ads.zzajq
    public final IObjectWrapper zzrv() {
        View viewZzaaw = this.zzddv.zzaaw();
        if (viewZzaaw == null) {
            return null;
        }
        return ObjectWrapper.wrap(viewZzaaw);
    }
}
