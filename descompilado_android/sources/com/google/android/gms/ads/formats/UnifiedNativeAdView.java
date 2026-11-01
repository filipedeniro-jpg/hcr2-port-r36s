package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaad;
import com.google.android.gms.internal.ads.zzaaf;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzacu;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class UnifiedNativeAdView extends FrameLayout {
    private final FrameLayout zzbkc;
    private final zzaaw zzbkd;

    public UnifiedNativeAdView(Context context) {
        super(context);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    @TargetApi(21)
    public UnifiedNativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    private final void zza(String str, View view) {
        try {
            this.zzbkd.zzc(str, ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setAssetView on delegate", e);
        }
    }

    public final void setHeadlineView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_HEADLINE, view);
    }

    public final void setCallToActionView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_CALL_TO_ACTION, view);
    }

    public final void setIconView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_ICON, view);
    }

    public final void setBodyView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_BODY, view);
    }

    public final void setAdvertiserView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_ADVERTISER, view);
    }

    public final void setStoreView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_STORE, view);
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzbkd.zzi(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    public final void setPriceView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_PRICE, view);
    }

    public final void setImageView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_IMAGE, view);
    }

    public final void setStarRatingView(View view) {
        zza(UnifiedNativeAdAssetNames.ASSET_STAR_RATING, view);
    }

    public final void setMediaView(MediaView mediaView) {
        zza(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO, mediaView);
        if (mediaView != null) {
            mediaView.zza(new zzaad(this) { // from class: com.google.android.gms.ads.formats.zze
                private final UnifiedNativeAdView zzbkj;

                {
                    this.zzbkj = this;
                }

                @Override // com.google.android.gms.internal.ads.zzaad
                public final void setMediaContent(UnifiedNativeAd.MediaContent mediaContent) {
                    this.zzbkj.zza(mediaContent);
                }
            });
            mediaView.zza(new zzaaf(this) { // from class: com.google.android.gms.ads.formats.zzd
                private final UnifiedNativeAdView zzbkj;

                {
                    this.zzbkj = this;
                }

                @Override // com.google.android.gms.internal.ads.zzaaf
                public final void setImageScaleType(ImageView.ScaleType scaleType) {
                    this.zzbkj.zza(scaleType);
                }
            });
        }
    }

    public final void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd) {
        try {
            this.zzbkd.zze((IObjectWrapper) unifiedNativeAd.zzja());
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setNativeAd on delegate", e);
        }
    }

    private final View zzbk(String str) {
        try {
            IObjectWrapper iObjectWrapperZzcj = this.zzbkd.zzcj(str);
            if (iObjectWrapperZzcj != null) {
                return (View) ObjectWrapper.unwrap(iObjectWrapperZzcj);
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call getAssetView on delegate", e);
            return null;
        }
    }

    public final View getHeadlineView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_HEADLINE);
    }

    public final View getCallToActionView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_CALL_TO_ACTION);
    }

    public final View getIconView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_ICON);
    }

    public final View getBodyView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_BODY);
    }

    public final View getStoreView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_STORE);
    }

    public final View getPriceView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_PRICE);
    }

    public final View getAdvertiserView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_ADVERTISER);
    }

    public final View getImageView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_IMAGE);
    }

    public final View getStarRatingView() {
        return zzbk(UnifiedNativeAdAssetNames.ASSET_STAR_RATING);
    }

    public final MediaView getMediaView() {
        View viewZzbk = zzbk(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO);
        if (viewZzbk instanceof MediaView) {
            return (MediaView) viewZzbk;
        }
        if (viewZzbk == null) {
            return null;
        }
        zzawo.zzdv("View is not an instance of MediaView");
        return null;
    }

    public final AdChoicesView getAdChoicesView() {
        View viewZzbk = zzbk(UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW);
        if (viewZzbk instanceof AdChoicesView) {
            return (AdChoicesView) viewZzbk;
        }
        return null;
    }

    public final void destroy() {
        try {
            this.zzbkd.destroy();
        } catch (RemoteException e) {
            zzawo.zzc("Unable to destroy native ad view", e);
        }
    }

    private final FrameLayout zzd(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzaaw zzjc() {
        Preconditions.checkNotNull(this.zzbkc, "createDelegate must be called after overlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return zzuo.zzog().zza(this.zzbkc.getContext(), this, this.zzbkc);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzbkc);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.zzbkc == view) {
            return;
        }
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzbkc);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zzbkc;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        zzaaw zzaawVar = this.zzbkd;
        if (zzaawVar != null) {
            try {
                zzaawVar.zzc(ObjectWrapper.wrap(view), i);
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        zzaaw zzaawVar;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnh)).booleanValue() && (zzaawVar = this.zzbkd) != null) {
            try {
                zzaawVar.zzj(ObjectWrapper.wrap(motionEvent));
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call handleTouchEvent on delegate", e);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    final /* synthetic */ void zza(ImageView.ScaleType scaleType) {
        try {
            if (scaleType instanceof ImageView.ScaleType) {
                this.zzbkd.zzk(ObjectWrapper.wrap(scaleType));
            }
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setMediaViewImageScaleType on delegate", e);
        }
    }

    final /* synthetic */ void zza(UnifiedNativeAd.MediaContent mediaContent) {
        try {
            if (mediaContent instanceof zzacu) {
                this.zzbkd.zza(((zzacu) mediaContent).zzqu());
            } else if (mediaContent == null) {
                this.zzbkd.zza(null);
            } else {
                zzawo.zzdv("Use MediaContent provided by UnifiedNativeAd.getMediaContent");
            }
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setMediaContent on delegate", e);
        }
    }
}
