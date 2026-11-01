package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakf extends zzaju {
    private final NativeContentAdMapper zzddu;

    public zzakf(NativeContentAdMapper nativeContentAdMapper) {
        this.zzddu = nativeContentAdMapper;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final zzaak zzqk() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final IObjectWrapper zzql() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final String getHeadline() {
        return this.zzddu.getHeadline();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final List getImages() {
        List<NativeAd.Image> images = this.zzddu.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image image : images) {
            arrayList.add(new zzaae(image.getDrawable(), image.getUri(), image.getScale(), image.getWidth(), image.getHeight()));
        }
        return arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final String getBody() {
        return this.zzddu.getBody();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final zzaas zzqm() {
        NativeAd.Image logo = this.zzddu.getLogo();
        if (logo != null) {
            return new zzaae(logo.getDrawable(), logo.getUri(), logo.getScale(), logo.getWidth(), logo.getHeight());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final String getCallToAction() {
        return this.zzddu.getCallToAction();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final String getAdvertiser() {
        return this.zzddu.getAdvertiser();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final void recordImpression() {
        this.zzddu.recordImpression();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final void zzx(IObjectWrapper iObjectWrapper) {
        this.zzddu.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final void zzy(IObjectWrapper iObjectWrapper) {
        this.zzddu.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzddu.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final void zzz(IObjectWrapper iObjectWrapper) {
        this.zzddu.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final boolean getOverrideImpressionRecording() {
        return this.zzddu.getOverrideImpressionRecording();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final boolean getOverrideClickHandling() {
        return this.zzddu.getOverrideClickHandling();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final Bundle getExtras() {
        return this.zzddu.getExtras();
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final IObjectWrapper zzru() {
        View adChoicesContent = this.zzddu.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final zzwk getVideoController() {
        if (this.zzddu.getVideoController() != null) {
            return this.zzddu.getVideoController().zzdd();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzajr
    public final IObjectWrapper zzrv() {
        View viewZzaaw = this.zzddu.zzaaw();
        if (viewZzaaw == null) {
            return null;
        }
        return ObjectWrapper.wrap(viewZzaaw);
    }
}
