package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzawf {
    private final View view;
    private boolean zzbnv;
    private boolean zzbrt;
    private Activity zzdvh;
    private boolean zzdvi;
    private ViewTreeObserver.OnGlobalLayoutListener zzdvj;
    private ViewTreeObserver.OnScrollChangedListener zzdvk = null;

    public zzawf(Activity activity, View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.zzdvh = activity;
        this.view = view;
        this.zzdvj = onGlobalLayoutListener;
    }

    public final void zzh(Activity activity) {
        this.zzdvh = activity;
    }

    public final void zzwe() {
        this.zzbrt = true;
        if (this.zzbnv) {
            zzwg();
        }
    }

    public final void zzwf() {
        this.zzbrt = false;
        zzwh();
    }

    public final void onAttachedToWindow() {
        this.zzbnv = true;
        if (this.zzbrt) {
            zzwg();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzbnv = false;
        zzwh();
    }

    private final void zzwg() {
        ViewTreeObserver viewTreeObserverZzi;
        if (this.zzdvi) {
            return;
        }
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzdvj;
        if (onGlobalLayoutListener != null) {
            Activity activity = this.zzdvh;
            if (activity != null && (viewTreeObserverZzi = zzi(activity)) != null) {
                viewTreeObserverZzi.addOnGlobalLayoutListener(onGlobalLayoutListener);
            }
            com.google.android.gms.ads.internal.zzp.zzkv();
            zzaxn.zza(this.view, this.zzdvj);
        }
        this.zzdvi = true;
    }

    private final void zzwh() {
        ViewTreeObserver viewTreeObserverZzi;
        Activity activity = this.zzdvh;
        if (activity != null && this.zzdvi) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzdvj;
            if (onGlobalLayoutListener != null && (viewTreeObserverZzi = zzi(activity)) != null) {
                com.google.android.gms.ads.internal.zzp.zzka();
                viewTreeObserverZzi.removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
            this.zzdvi = false;
        }
    }

    private static ViewTreeObserver zzi(Activity activity) {
        Window window;
        View decorView;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }
}
