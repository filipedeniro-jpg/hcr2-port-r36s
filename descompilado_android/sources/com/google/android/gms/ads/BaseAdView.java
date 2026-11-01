package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzth;
import com.google.android.gms.internal.ads.zzwu;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
class BaseAdView extends ViewGroup {
    protected final zzwu zzabf;

    public BaseAdView(Context context, int i) {
        super(context);
        this.zzabf = new zzwu(this, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zzabf = new zzwu(this, attributeSet, false, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zzabf = new zzwu(this, attributeSet, false, i2);
    }

    public void destroy() {
        this.zzabf.destroy();
    }

    public AdListener getAdListener() {
        return this.zzabf.getAdListener();
    }

    public AdSize getAdSize() {
        return this.zzabf.getAdSize();
    }

    public String getAdUnitId() {
        return this.zzabf.getAdUnitId();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        this.zzabf.zza(adRequest.zzda());
    }

    public void pause() {
        this.zzabf.pause();
    }

    public void resume() {
        this.zzabf.resume();
    }

    public boolean isLoading() {
        return this.zzabf.isLoading();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAdListener(AdListener adListener) {
        this.zzabf.setAdListener(adListener);
        if (adListener == 0) {
            this.zzabf.zza((zzth) null);
            this.zzabf.setAppEventListener(null);
            return;
        }
        if (adListener instanceof zzth) {
            this.zzabf.zza((zzth) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zzabf.setAppEventListener((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zzabf.setAdSizes(adSize);
    }

    public void setAdUnitId(String str) {
        this.zzabf.setAdUnitId(str);
    }

    public String getMediationAdapterClassName() {
        return this.zzabf.getMediationAdapterClassName();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
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
    protected void onMeasure(int i, int i2) {
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
