package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class NativeAdView extends FrameLayout {
    private final FrameLayout zzbkc;
    private final zzaaw zzbkd;

    public NativeAdView(Context context) {
        super(context);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    @TargetApi(21)
    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzbkc = zzd(context);
        this.zzbkd = zzjc();
    }

    public void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public AdChoicesView getAdChoicesView() {
        View viewZzbk = zzbk(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
        if (viewZzbk instanceof AdChoicesView) {
            return (AdChoicesView) viewZzbk;
        }
        return null;
    }

    protected final void zza(String str, View view) {
        try {
            this.zzbkd.zzc(str, ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setAssetView on delegate", e);
        }
    }

    protected final View zzbk(String str) {
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

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzbkd.zze((IObjectWrapper) nativeAd.zzja());
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setNativeAd on delegate", e);
        }
    }

    public void destroy() {
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
        Preconditions.checkNotNull(this.zzbkc, "createDelegate must be called after mOverlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return zzuo.zzog().zza(this.zzbkc.getContext(), this, this.zzbkc);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzbkc);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.zzbkc == view) {
            return;
        }
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzbkc);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zzbkc;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
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
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
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
}
