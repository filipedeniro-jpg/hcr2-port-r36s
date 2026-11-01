package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzayi {
    private final Context zzcft;
    private final zzayt zzdxm;
    private final ViewGroup zzdyh;
    private zzayc zzdyi;

    public zzayi(Context context, ViewGroup viewGroup, zzbbc zzbbcVar) {
        this(context, viewGroup, zzbbcVar, null);
    }

    @VisibleForTesting
    private zzayi(Context context, ViewGroup viewGroup, zzayt zzaytVar, zzayc zzaycVar) {
        this.zzcft = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdyh = viewGroup;
        this.zzdxm = zzaytVar;
        this.zzdyi = null;
    }

    public final void zze(int i, int i2, int i3, int i4) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        zzayc zzaycVar = this.zzdyi;
        if (zzaycVar != null) {
            zzaycVar.zzd(i, i2, i3, i4);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, int i5, boolean z, zzayq zzayqVar) {
        if (this.zzdyi != null) {
            return;
        }
        zzzc.zza(this.zzdxm.zzxo().zzpy(), this.zzdxm.zzxk(), "vpr2");
        Context context = this.zzcft;
        zzayt zzaytVar = this.zzdxm;
        this.zzdyi = new zzayc(context, zzaytVar, i5, z, zzaytVar.zzxo().zzpy(), zzayqVar);
        this.zzdyh.addView(this.zzdyi, 0, new ViewGroup.LayoutParams(-1, -1));
        this.zzdyi.zzd(i, i2, i3, i4);
        this.zzdxm.zzao(false);
    }

    public final zzayc zzxc() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzdyi;
    }

    public final void onPause() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        zzayc zzaycVar = this.zzdyi;
        if (zzaycVar != null) {
            zzaycVar.pause();
        }
    }

    public final void onDestroy() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        zzayc zzaycVar = this.zzdyi;
        if (zzaycVar != null) {
            zzaycVar.destroy();
            this.zzdyh.removeView(this.zzdyi);
            this.zzdyi = null;
        }
    }
}
