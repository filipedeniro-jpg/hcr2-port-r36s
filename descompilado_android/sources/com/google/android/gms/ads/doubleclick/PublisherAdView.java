package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzve;
import com.google.android.gms.internal.ads.zzwu;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class PublisherAdView extends ViewGroup {
    private final zzwu zzabx;

    public PublisherAdView(Context context) {
        super(context);
        this.zzabx = new zzwu(this);
    }

    @KeepForSdk
    @Deprecated
    public final void setCorrelator(Correlator correlator) {
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzabx = new zzwu(this, attributeSet, true);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzabx = new zzwu(this, attributeSet, true);
    }

    public final VideoController getVideoController() {
        return this.zzabx.getVideoController();
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzabx.setVideoOptions(videoOptions);
    }

    public final VideoOptions getVideoOptions() {
        return this.zzabx.getVideoOptions();
    }

    public final void destroy() {
        this.zzabx.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzabx.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzabx.getAdSize();
    }

    public final AdSize[] getAdSizes() {
        return this.zzabx.getAdSizes();
    }

    public final String getAdUnitId() {
        return this.zzabx.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzabx.getAppEventListener();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzabx.getOnCustomRenderedAdLoadedListener();
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzabx.zza(publisherAdRequest.zzda());
    }

    public final void pause() {
        this.zzabx.pause();
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzabx.setManualImpressionsEnabled(z);
    }

    public final void recordManualImpression() {
        this.zzabx.recordManualImpression();
    }

    public final void resume() {
        this.zzabx.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzabx.setAdListener(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (adSizeArr == null || adSizeArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzabx.zza(adSizeArr);
    }

    public final void setAdUnitId(String str) {
        this.zzabx.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzabx.setAppEventListener(appEventListener);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzabx.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    public final String getMediationAdapterClassName() {
        return this.zzabx.getMediationAdapterClassName();
    }

    public final boolean isLoading() {
        return this.zzabx.isLoading();
    }

    public final boolean zza(zzve zzveVar) {
        return this.zzabx.zza(zzveVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int i5 = ((i3 - i) - measuredWidth) / 2;
        int i6 = ((i4 - i2) - measuredHeight) / 2;
        childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int heightInPixels;
        int measuredWidth = 0;
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            measureChild(childAt, i, i2);
            measuredWidth = childAt.getMeasuredWidth();
            heightInPixels = childAt.getMeasuredHeight();
        } else {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzawo.zzc("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                heightInPixels = adSize.getHeightInPixels(context);
                measuredWidth = widthInPixels;
            } else {
                heightInPixels = 0;
            }
        }
        setMeasuredDimension(View.resolveSize(Math.max(measuredWidth, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(heightInPixels, getSuggestedMinimumHeight()), i2));
    }
}
