package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzwu {
    private final zztu zzaax;
    private VideoOptions zzbjx;
    private boolean zzbke;
    private AppEventListener zzbkg;
    private zzth zzcbl;
    private AdListener zzcbm;
    private AdSize[] zzccl;
    private String zzccm;
    private final zzaje zzcee;
    private final AtomicBoolean zzcef;
    private final VideoController zzceg;

    @VisibleForTesting
    private final zzun zzceh;
    private zzve zzcei;
    private OnCustomRenderedAdLoadedListener zzcej;
    private ViewGroup zzcek;
    private int zzcel;

    private static boolean zzcj(int i) {
        return i == 1;
    }

    public zzwu(ViewGroup viewGroup) {
        this(viewGroup, null, false, zztu.zzccd, 0);
    }

    public zzwu(ViewGroup viewGroup, int i) {
        this(viewGroup, null, false, zztu.zzccd, i);
    }

    public zzwu(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zztu.zzccd, 0);
    }

    public zzwu(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, int i) {
        this(viewGroup, attributeSet, false, zztu.zzccd, i);
    }

    @VisibleForTesting
    private zzwu(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zztu zztuVar, zzve zzveVar, int i) {
        zztw zztwVarZznz;
        this.zzcee = new zzaje();
        this.zzceg = new VideoController();
        this.zzceh = new zzwt(this);
        this.zzcek = viewGroup;
        this.zzaax = zztuVar;
        this.zzcei = null;
        this.zzcef = new AtomicBoolean(false);
        this.zzcel = i;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zztz zztzVar = new zztz(context, attributeSet);
                this.zzccl = zztzVar.zzr(z);
                this.zzccm = zztzVar.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzawe zzaweVarZzof = zzuo.zzof();
                    AdSize adSize = this.zzccl[0];
                    int i2 = this.zzcel;
                    if (adSize.equals(AdSize.INVALID)) {
                        zztwVarZznz = zztw.zznz();
                    } else {
                        zztw zztwVar = new zztw(context, adSize);
                        zztwVar.zzcch = zzcj(i2);
                        zztwVarZznz = zztwVar;
                    }
                    zzaweVarZzof.zza(viewGroup, zztwVarZznz, "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzuo.zzof().zza(viewGroup, new zztw(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    @VisibleForTesting
    private zzwu(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zztu zztuVar, int i) {
        this(viewGroup, attributeSet, z, zztuVar, null, i);
    }

    public final void destroy() {
        try {
            if (this.zzcei != null) {
                this.zzcei.destroy();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final AdListener getAdListener() {
        return this.zzcbm;
    }

    public final AdSize getAdSize() {
        zztw zztwVarZzjo;
        try {
            if (this.zzcei != null && (zztwVarZzjo = this.zzcei.zzjo()) != null) {
                return com.google.android.gms.ads.zzb.zza(zztwVarZzjo.width, zztwVarZzjo.height, zztwVarZzjo.zzabd);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
        AdSize[] adSizeArr = this.zzccl;
        if (adSizeArr != null) {
            return adSizeArr[0];
        }
        return null;
    }

    public final AdSize[] getAdSizes() {
        return this.zzccl;
    }

    public final String getAdUnitId() {
        zzve zzveVar;
        if (this.zzccm == null && (zzveVar = this.zzcei) != null) {
            try {
                this.zzccm = zzveVar.getAdUnitId();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
        return this.zzccm;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzbkg;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcej;
    }

    public final void zza(zzws zzwsVar) {
        zzve zzveVarZzd;
        try {
            if (this.zzcei == null) {
                if ((this.zzccl == null || this.zzccm == null) && this.zzcei == null) {
                    throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
                }
                Context context = this.zzcek.getContext();
                zztw zztwVarZza = zza(context, this.zzccl, this.zzcel);
                if ("search_v2".equals(zztwVarZza.zzabd)) {
                    zzveVarZzd = new zzug(zzuo.zzog(), context, zztwVarZza, this.zzccm).zzd(context, false);
                } else {
                    zzveVarZzd = new zzub(zzuo.zzog(), context, zztwVarZza, this.zzccm, this.zzcee).zzd(context, false);
                }
                this.zzcei = zzveVarZzd;
                this.zzcei.zza(new zztl(this.zzceh));
                if (this.zzcbl != null) {
                    this.zzcei.zza(new zztk(this.zzcbl));
                }
                if (this.zzbkg != null) {
                    this.zzcei.zza(new zzty(this.zzbkg));
                }
                if (this.zzcej != null) {
                    this.zzcei.zza(new zzzs(this.zzcej));
                }
                if (this.zzbjx != null) {
                    this.zzcei.zza(new zzyc(this.zzbjx));
                }
                this.zzcei.setManualImpressionsEnabled(this.zzbke);
                try {
                    IObjectWrapper iObjectWrapperZzjm = this.zzcei.zzjm();
                    if (iObjectWrapperZzjm != null) {
                        this.zzcek.addView((View) ObjectWrapper.unwrap(iObjectWrapperZzjm));
                    }
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
            if (this.zzcei.zza(zztu.zza(this.zzcek.getContext(), zzwsVar))) {
                this.zzcee.zzf(zzwsVar.zzoy());
            }
        } catch (RemoteException e2) {
            zzawo.zze("#007 Could not call remote method.", e2);
        }
    }

    public final void pause() {
        try {
            if (this.zzcei != null) {
                this.zzcei.pause();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void recordManualImpression() {
        if (this.zzcef.getAndSet(true)) {
            return;
        }
        try {
            if (this.zzcei != null) {
                this.zzcei.zzjn();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void resume() {
        try {
            if (this.zzcei != null) {
                this.zzcei.resume();
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        this.zzcbm = adListener;
        this.zzceh.zza(adListener);
    }

    public final void zza(zzth zzthVar) {
        try {
            this.zzcbl = zzthVar;
            if (this.zzcei != null) {
                this.zzcei.zza(zzthVar != null ? new zztk(zzthVar) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (this.zzccl != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        zza(adSizeArr);
    }

    public final void zza(AdSize... adSizeArr) {
        this.zzccl = adSizeArr;
        try {
            if (this.zzcei != null) {
                this.zzcei.zza(zza(this.zzcek.getContext(), this.zzccl, this.zzcel));
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
        this.zzcek.requestLayout();
    }

    public final void setAdUnitId(String str) {
        if (this.zzccm != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzccm = str;
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzbkg = appEventListener;
            if (this.zzcei != null) {
                this.zzcei.zza(appEventListener != null ? new zzty(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzcej = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzcei != null) {
                this.zzcei.zza(onCustomRenderedAdLoadedListener != null ? new zzzs(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbke = z;
        try {
            if (this.zzcei != null) {
                this.zzcei.setManualImpressionsEnabled(this.zzbke);
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzcei != null) {
                return this.zzcei.zzjp();
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final boolean isLoading() {
        try {
            if (this.zzcei != null) {
                return this.zzcei.isLoading();
            }
            return false;
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final VideoController getVideoController() {
        return this.zzceg;
    }

    public final zzwk zzdd() {
        zzve zzveVar = this.zzcei;
        if (zzveVar == null) {
            return null;
        }
        try {
            return zzveVar.getVideoController();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzbjx = videoOptions;
        try {
            if (this.zzcei != null) {
                this.zzcei.zza(videoOptions == null ? null : new zzyc(videoOptions));
            }
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final VideoOptions getVideoOptions() {
        return this.zzbjx;
    }

    public final boolean zza(zzve zzveVar) {
        if (zzveVar == null) {
            return false;
        }
        try {
            IObjectWrapper iObjectWrapperZzjm = zzveVar.zzjm();
            if (iObjectWrapperZzjm == null || ((View) ObjectWrapper.unwrap(iObjectWrapperZzjm)).getParent() != null) {
                return false;
            }
            this.zzcek.addView((View) ObjectWrapper.unwrap(iObjectWrapperZzjm));
            this.zzcei = zzveVar;
            return true;
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    private static zztw zza(Context context, AdSize[] adSizeArr, int i) {
        for (AdSize adSize : adSizeArr) {
            if (adSize.equals(AdSize.INVALID)) {
                return zztw.zznz();
            }
        }
        zztw zztwVar = new zztw(context, adSizeArr);
        zztwVar.zzcch = zzcj(i);
        return zztwVar;
    }
}
