package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaz;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzuo;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class NativeAdViewHolder {
    public static WeakHashMap<View, NativeAdViewHolder> zzbka = new WeakHashMap<>();
    private zzaaz zzbjz;
    private WeakReference<View> zzbkb;

    public NativeAdViewHolder(View view, Map<String, View> map, Map<String, View> map2) {
        Preconditions.checkNotNull(view, "ContainerView must not be null");
        if ((view instanceof NativeAdView) || (view instanceof UnifiedNativeAdView)) {
            zzawo.zzes("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
            return;
        }
        if (zzbka.get(view) != null) {
            zzawo.zzes("The provided containerView is already in use with another NativeAdViewHolder.");
            return;
        }
        zzbka.put(view, this);
        this.zzbkb = new WeakReference<>(view);
        this.zzbjz = zzuo.zzog().zza(view, zzb(map), zzb(map2));
    }

    private static HashMap<String, View> zzb(Map<String, View> map) {
        if (map == null) {
            return new HashMap<>();
        }
        return new HashMap<>(map);
    }

    public final void setNativeAd(NativeAd nativeAd) {
        zze((IObjectWrapper) nativeAd.zzja());
    }

    public final void setNativeAd(UnifiedNativeAd unifiedNativeAd) {
        zze((IObjectWrapper) unifiedNativeAd.zzja());
    }

    private final void zze(IObjectWrapper iObjectWrapper) {
        WeakReference<View> weakReference = this.zzbkb;
        View view = weakReference != null ? weakReference.get() : null;
        if (view == null) {
            zzawo.zzeu("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!zzbka.containsKey(view)) {
            zzbka.put(view, this);
        }
        zzaaz zzaazVar = this.zzbjz;
        if (zzaazVar != null) {
            try {
                zzaazVar.zze(iObjectWrapper);
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void unregisterNativeAd() {
        zzaaz zzaazVar = this.zzbjz;
        if (zzaazVar != null) {
            try {
                zzaazVar.unregisterNativeAd();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        WeakReference<View> weakReference = this.zzbkb;
        View view = weakReference != null ? weakReference.get() : null;
        if (view != null) {
            zzbka.remove(view);
        }
    }

    public final void setClickConfirmingView(View view) {
        try {
            this.zzbjz.zzi(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzawo.zzc("Unable to call setClickConfirmingView on delegate", e);
        }
    }
}
