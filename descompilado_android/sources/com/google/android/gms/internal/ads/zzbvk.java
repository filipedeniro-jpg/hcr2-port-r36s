package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbvk implements zzbuf {
    private final zzawv zzblh;
    private final zzcvb zzfef;
    private final zzcvk zzffn;
    private final zzbmo zzfjd;
    private final zzbmf zzfje;
    private boolean zzfji = false;
    private boolean zzfjl = false;

    @Nullable
    private final zzajq zzfnf;

    @Nullable
    private final zzajr zzfng;

    @Nullable
    private final zzajw zzfnh;
    private final Context zzlk;

    public zzbvk(@Nullable zzajq zzajqVar, @Nullable zzajr zzajrVar, @Nullable zzajw zzajwVar, zzbmo zzbmoVar, zzbmf zzbmfVar, Context context, zzcvb zzcvbVar, zzawv zzawvVar, zzcvk zzcvkVar) {
        this.zzfnf = zzajqVar;
        this.zzfng = zzajrVar;
        this.zzfnh = zzajwVar;
        this.zzfjd = zzbmoVar;
        this.zzfje = zzbmfVar;
        this.zzlk = context;
        this.zzfef = zzcvbVar;
        this.zzblh = zzawvVar;
        this.zzffn = zzcvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void cancelUnconfirmedClick() {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void destroy() {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void setClickConfirmingView(View view) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, MotionEvent motionEvent, @Nullable View view2) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(zzacl zzaclVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzagz() {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzahb() {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzf(Bundle bundle) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzfp(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzg(Bundle bundle) {
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final boolean zzh(Bundle bundle) {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        try {
            IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(view);
            HashMap<String, View> mapZzb = zzb(map);
            HashMap<String, View> mapZzb2 = zzb(map2);
            if (this.zzfnh != null) {
                this.zzfnh.zzc(iObjectWrapperWrap, ObjectWrapper.wrap(mapZzb), ObjectWrapper.wrap(mapZzb2));
                return;
            }
            if (this.zzfnf != null) {
                this.zzfnf.zzc(iObjectWrapperWrap, ObjectWrapper.wrap(mapZzb), ObjectWrapper.wrap(mapZzb2));
                this.zzfnf.zzy(iObjectWrapperWrap);
            } else if (this.zzfng != null) {
                this.zzfng.zzc(iObjectWrapperWrap, ObjectWrapper.wrap(mapZzb), ObjectWrapper.wrap(mapZzb2));
                this.zzfng.zzy(iObjectWrapperWrap);
            }
        } catch (RemoteException e) {
            zzatm.zzd("Failed to call trackView", e);
        }
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> map2 = new HashMap<>();
        if (map == null) {
            return map2;
        }
        synchronized (map) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                View view = entry.getValue().get();
                if (view != null) {
                    map2.put(entry.getKey(), view);
                }
            }
        }
        return map2;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map) {
        try {
            IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(view);
            if (this.zzfnh != null) {
                this.zzfnh.zzz(iObjectWrapperWrap);
            } else if (this.zzfnf != null) {
                this.zzfnf.zzz(iObjectWrapperWrap);
            } else if (this.zzfng != null) {
                this.zzfng.zzz(iObjectWrapperWrap);
            }
        } catch (RemoteException e) {
            zzatm.zzd("Failed to call untrackView", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable View view2, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, boolean z) {
        if (this.zzfjl && this.zzfef.zzdcx) {
            return;
        }
        zzab(view);
    }

    private final void zzab(View view) {
        try {
            if (this.zzfnh != null && !this.zzfnh.getOverrideClickHandling()) {
                this.zzfnh.zzx(ObjectWrapper.wrap(view));
                this.zzfje.onAdClicked();
            } else if (this.zzfnf != null && !this.zzfnf.getOverrideClickHandling()) {
                this.zzfnf.zzx(ObjectWrapper.wrap(view));
                this.zzfje.onAdClicked();
            } else {
                if (this.zzfng == null || this.zzfng.getOverrideClickHandling()) {
                    return;
                }
                this.zzfng.zzx(ObjectWrapper.wrap(view));
                this.zzfje.onAdClicked();
            }
        } catch (RemoteException e) {
            zzatm.zzd("Failed to call handleClick", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        if (!this.zzfjl) {
            zzatm.zzeu("Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.");
        } else if (!this.zzfef.zzdcx) {
            zzatm.zzeu("Custom click reporting for 3p ads failed. Ad unit id not whitelisted.");
        } else {
            zzab(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzqs() {
        this.zzfjl = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final boolean isCustomClickGestureEnabled() {
        return this.zzfef.zzdcx;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2) {
        try {
            if (!this.zzfji && this.zzfef.zzgjb != null) {
                this.zzfji |= com.google.android.gms.ads.internal.zzp.zzki().zzb(this.zzlk, this.zzblh.zzbnh, this.zzfef.zzgjb.toString(), this.zzffn.zzgjt);
            }
            if (this.zzfnh != null && !this.zzfnh.getOverrideImpressionRecording()) {
                this.zzfnh.recordImpression();
                this.zzfjd.onAdImpression();
            } else if (this.zzfnf != null && !this.zzfnf.getOverrideImpressionRecording()) {
                this.zzfnf.recordImpression();
                this.zzfjd.onAdImpression();
            } else {
                if (this.zzfng == null || this.zzfng.getOverrideImpressionRecording()) {
                    return;
                }
                this.zzfng.recordImpression();
                this.zzfjd.onAdImpression();
            }
        } catch (RemoteException e) {
            zzatm.zzd("Failed to call recordImpression", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable zzwb zzwbVar) {
        zzatm.zzeu("Mute This Ad is not supported for 3rd party ads");
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(zzvx zzvxVar) {
        zzatm.zzeu("Mute This Ad is not supported for 3rd party ads");
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzaha() {
        zzatm.zzeu("Mute This Ad is not supported for 3rd party ads");
    }
}
