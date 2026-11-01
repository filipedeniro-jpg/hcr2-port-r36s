package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbun extends zzaav implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzbvf {
    public static final String[] zzflv = {NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private FrameLayout zzbkc;
    private zzaan zzcwf;
    private final int zzdvt;
    private final String zzflu;
    private FrameLayout zzflx;
    private zzdcs zzfly;
    private View zzflz;

    @GuardedBy("this")
    private zzbtp zzfma;
    private zzph zzfmb;
    private boolean zzfmd;

    @GuardedBy("this")
    private Map<String, WeakReference<View>> zzflw = new HashMap();
    private IObjectWrapper zzfmc = null;
    private boolean zzefy = false;

    public zzbun(FrameLayout frameLayout, FrameLayout frameLayout2, int i) {
        String str;
        this.zzflx = frameLayout;
        this.zzbkc = frameLayout2;
        this.zzdvt = i;
        String canonicalName = frameLayout.getClass().getCanonicalName();
        if ("com.google.android.gms.ads.formats.NativeContentAdView".equals(canonicalName)) {
            str = NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE;
        } else if ("com.google.android.gms.ads.formats.NativeAppInstallAdView".equals(canonicalName)) {
            str = NativeAppInstallAd.ASSET_ATTRIBUTION_ICON_IMAGE;
        } else {
            "com.google.android.gms.ads.formats.UnifiedNativeAdView".equals(canonicalName);
            str = "3012";
        }
        this.zzflu = str;
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza((View) frameLayout, (ViewTreeObserver.OnGlobalLayoutListener) this);
        com.google.android.gms.ads.internal.zzp.zzkv();
        zzaxn.zza((View) frameLayout, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzfly = zzawx.zzdwa;
        this.zzfmb = new zzph(this.zzflx.getContext(), this.zzflx);
        frameLayout.setOnTouchListener(this);
        frameLayout.setOnClickListener(this);
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zzc(String str, IObjectWrapper iObjectWrapper) {
        zza(str, (View) ObjectWrapper.unwrap(iObjectWrapper), true);
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized void zza(String str, View view, boolean z) {
        if (this.zzefy) {
            return;
        }
        if (view == null) {
            this.zzflw.remove(str);
            return;
        }
        this.zzflw.put(str, new WeakReference<>(view));
        if (!NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) && !UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str)) {
            if (zzavs.zzcq(this.zzdvt)) {
                view.setOnTouchListener(this);
            }
            view.setClickable(true);
            view.setOnClickListener(this);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized IObjectWrapper zzcj(String str) {
        return ObjectWrapper.wrap(zzfw(str));
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized View zzfw(String str) {
        if (this.zzefy) {
            return null;
        }
        WeakReference<View> weakReference = this.zzflw.get(str);
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zze(IObjectWrapper iObjectWrapper) {
        if (this.zzefy) {
            return;
        }
        Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapper);
        if (!(objUnwrap instanceof zzbtp)) {
            zzatm.zzeu("Not an instance of native engine. This is most likely a transient error");
            return;
        }
        if (this.zzfma != null) {
            this.zzfma.zzb(this);
        }
        zzaij();
        this.zzfma = (zzbtp) objUnwrap;
        this.zzfma.zza(this);
        this.zzfma.zzy(this.zzflx);
        this.zzfma.zzz(this.zzbkc);
        if (this.zzfmd) {
            this.zzfma.zzahi().zza(this.zzcwf);
        }
    }

    private final synchronized void zzaij() {
        this.zzfly.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbuq
            private final zzbun zzfmj;

            {
                this.zzfmj = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfmj.zzair();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void destroy() {
        if (this.zzefy) {
            return;
        }
        if (this.zzfma != null) {
            this.zzfma.zzb(this);
            this.zzfma = null;
        }
        this.zzflw.clear();
        this.zzflx.removeAllViews();
        this.zzbkc.removeAllViews();
        this.zzflw = null;
        this.zzflx = null;
        this.zzbkc = null;
        this.zzflz = null;
        this.zzfmb = null;
        this.zzefy = true;
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zzc(IObjectWrapper iObjectWrapper, int i) {
    }

    @Override // android.view.View.OnClickListener
    public final synchronized void onClick(View view) {
        if (this.zzfma != null) {
            this.zzfma.cancelUnconfirmedClick();
            this.zzfma.zza(view, this.zzflx, zzaik(), zzail(), false);
        }
    }

    @Override // android.view.View.OnTouchListener
    public final synchronized boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzfma != null) {
            this.zzfma.zza(view, motionEvent, this.zzflx);
        }
        return false;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final synchronized void onGlobalLayout() {
        if (this.zzfma != null) {
            this.zzfma.zzb(this.zzflx, zzaik(), zzail(), zzbtp.zzx(this.zzflx));
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final synchronized void onScrollChanged() {
        if (this.zzfma != null) {
            this.zzfma.zzb(this.zzflx, zzaik(), zzail(), zzbtp.zzx(this.zzflx));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized Map<String, WeakReference<View>> zzaik() {
        return this.zzflw;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized Map<String, WeakReference<View>> zzail() {
        return this.zzflw;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final synchronized Map<String, WeakReference<View>> zzaim() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final synchronized String zzain() {
        return this.zzflu;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final FrameLayout zzaio() {
        return this.zzbkc;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final zzph zzaip() {
        return this.zzfmb;
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zzi(IObjectWrapper iObjectWrapper) {
        this.zzfma.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zzk(IObjectWrapper iObjectWrapper) {
        if (this.zzefy) {
            return;
        }
        this.zzfmc = iObjectWrapper;
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    @Nullable
    public final IObjectWrapper zzaiq() {
        return this.zzfmc;
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final void zzj(IObjectWrapper iObjectWrapper) {
        onTouch(this.zzflx, (MotionEvent) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzaaw
    public final synchronized void zza(zzaan zzaanVar) {
        if (this.zzefy) {
            return;
        }
        this.zzfmd = true;
        this.zzcwf = zzaanVar;
        if (this.zzfma != null) {
            this.zzfma.zzahi().zza(zzaanVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvf
    public final /* synthetic */ View zzaer() {
        return this.zzflx;
    }

    final /* synthetic */ void zzair() {
        if (this.zzflz == null) {
            this.zzflz = new View(this.zzflx.getContext());
            this.zzflz.setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
        }
        if (this.zzflx != this.zzflz.getParent()) {
            this.zzflx.addView(this.zzflz);
        }
    }
}
