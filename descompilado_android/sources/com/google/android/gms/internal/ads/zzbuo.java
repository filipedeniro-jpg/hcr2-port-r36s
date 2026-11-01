package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbuo extends zzabc implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzbvf {

    @GuardedBy("this")
    private zzbtp zzfma;
    private zzph zzfmb;
    private final WeakReference<View> zzfme;
    private final Map<String, WeakReference<View>> zzfmf = new HashMap();
    private final Map<String, WeakReference<View>> zzfmg = new HashMap();
    private final Map<String, WeakReference<View>> zzfmh = new HashMap();

    public zzbuo(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza(view, (ViewTreeObserver.OnGlobalLayoutListener) this);
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza(view, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzfme = new WeakReference<>(view);
        for (Map.Entry<String, View> entry : map.entrySet()) {
            String key = entry.getKey();
            View value = entry.getValue();
            if (value != null) {
                this.zzfmf.put(key, new WeakReference<>(value));
                if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(key) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(key)) {
                    value.setOnTouchListener(this);
                    value.setClickable(true);
                    value.setOnClickListener(this);
                }
            }
        }
        this.zzfmh.putAll(this.zzfmf);
        for (Map.Entry<String, View> entry2 : map2.entrySet()) {
            View value2 = entry2.getValue();
            if (value2 != null) {
                this.zzfmg.put(entry2.getKey(), new WeakReference<>(value2));
                value2.setOnTouchListener(this);
                value2.setClickable(false);
            }
        }
        this.zzfmh.putAll(this.zzfmg);
        this.zzfmb = new zzph(view.getContext(), view);
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final FrameLayout zzaio() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaaz
    public final synchronized void unregisterNativeAd() {
        if (this.zzfma != null) {
            this.zzfma.zzb(this);
            this.zzfma = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaaz
    public final synchronized void zze(IObjectWrapper iObjectWrapper) {
        Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(objUnwrap instanceof zzbtp)) {
            zzatm.zzeu("Not an instance of InternalNativeAd. This is most likely a transient error");
            return;
        }
        if (this.zzfma != null) {
            this.zzfma.zzb(this);
        }
        if (((zzbtp) objUnwrap).zzahg()) {
            this.zzfma = (zzbtp) objUnwrap;
            this.zzfma.zza(this);
            this.zzfma.zzy(zzaer());
            return;
        }
        zzatm.zzes("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final View zzaer() {
        return this.zzfme.get();
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final zzph zzaip() {
        return this.zzfmb;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized void zza(String str, View view, boolean z) {
        try {
            if (view == null) {
                this.zzfmh.remove(str);
                this.zzfmf.remove(str);
                this.zzfmg.remove(str);
                return;
            }
            this.zzfmh.put(str, new WeakReference<>(view));
            if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
                this.zzfmf.put(str, new WeakReference<>(view));
                view.setClickable(true);
                view.setOnClickListener(this);
                view.setOnTouchListener(this);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized Map<String, WeakReference<View>> zzaik() {
        return this.zzfmh;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized Map<String, WeakReference<View>> zzail() {
        return this.zzfmf;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final synchronized Map<String, WeakReference<View>> zzaim() {
        return this.zzfmg;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized View zzfw(String str) {
        WeakReference<View> weakReference = this.zzfmh.get(str);
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized String zzain() {
        return NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final synchronized IObjectWrapper zzaiq() {
        return null;
    }

    @Override // android.view.View.OnTouchListener
    public final synchronized boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzfma != null) {
            this.zzfma.zza(view, motionEvent, zzaer());
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public final synchronized void onClick(View view) {
        if (this.zzfma != null) {
            this.zzfma.zza(view, zzaer(), zzaik(), zzail(), true);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final synchronized void onGlobalLayout() {
        if (this.zzfma != null) {
            this.zzfma.zzb(zzaer(), zzaik(), zzail(), zzbtp.zzx(zzaer()));
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final synchronized void onScrollChanged() {
        if (this.zzfma != null) {
            this.zzfma.zzb(zzaer(), zzaik(), zzail(), zzbtp.zzx(zzaer()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaaz
    public final synchronized void zzi(IObjectWrapper iObjectWrapper) {
        if (this.zzfma != null) {
            Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (!(objUnwrap instanceof View)) {
                zzatm.zzeu("Calling NativeAdViewHolderNonagonDelegate.setClickConfirmingView with wrong wrapped object");
            }
            this.zzfma.setClickConfirmingView((View) objUnwrap);
        }
    }
}
