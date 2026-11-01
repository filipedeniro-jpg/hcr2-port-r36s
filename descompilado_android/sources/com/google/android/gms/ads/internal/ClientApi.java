package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.ads.internal.overlay.zzz;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzaaz;
import com.google.android.gms.internal.ads.zzajd;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzapl;
import com.google.android.gms.internal.ads.zzaqg;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzbds;
import com.google.android.gms.internal.ads.zzbun;
import com.google.android.gms.internal.ads.zzbuo;
import com.google.android.gms.internal.ads.zzcli;
import com.google.android.gms.internal.ads.zzclk;
import com.google.android.gms.internal.ads.zzclo;
import com.google.android.gms.internal.ads.zzclu;
import com.google.android.gms.internal.ads.zztw;
import com.google.android.gms.internal.ads.zzux;
import com.google.android.gms.internal.ads.zzve;
import com.google.android.gms.internal.ads.zzvq;
import com.google.android.gms.internal.ads.zzvu;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class ClientApi extends zzvq {
    @KeepForSdk
    public ClientApi() {
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzvu zzg(IObjectWrapper iObjectWrapper) {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzane zzh(IObjectWrapper iObjectWrapper) {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zza(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzclo(zzbds.zza(context, zzajdVar, i), context, zztwVar, str);
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zzb(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzclu(zzbds.zza(context, zzajdVar, i), context, zztwVar, str);
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzux zza(IObjectWrapper iObjectWrapper, String str, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzcli(zzbds.zza(context, zzajdVar, i), context, str);
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaaw zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzbun((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2), 15301000);
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzapl zza(IObjectWrapper iObjectWrapper, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return zzbds.zza(context, zzajdVar, i).zzabn().zzbs(context).zzadd().zzadi();
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzamu zzf(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel adOverlayInfoParcelZzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (adOverlayInfoParcelZzc == null) {
            return new zzt(activity);
        }
        int i = adOverlayInfoParcelZzc.zzdhr;
        if (i == 1) {
            return new com.google.android.gms.ads.internal.overlay.zzq(activity);
        }
        if (i == 2) {
            return new zzz(activity);
        }
        if (i == 3) {
            return new zzy(activity);
        }
        if (i == 4) {
            return new zzs(activity, adOverlayInfoParcelZzc);
        }
        return new zzt(activity);
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzvu zza(IObjectWrapper iObjectWrapper, int i) {
        return zzbds.zzd((Context) ObjectWrapper.unwrap(iObjectWrapper), i).zzabh();
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zza(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, int i) {
        return new zzj((Context) ObjectWrapper.unwrap(iObjectWrapper), zztwVar, str, new zzawv(15301000, i, true, false));
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaaz zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzbuo((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaqg zzb(IObjectWrapper iObjectWrapper, String str, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return zzbds.zza(context, zzajdVar, i).zzabn().zzbs(context).zzfm(str).zzadd().zzadj();
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zzc(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzclk(zzbds.zza(context, zzajdVar, i), context, zztwVar, str);
    }
}
