package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzww {
    private final zztu zzaax;
    private AppEventListener zzbkg;
    private boolean zzble;
    private zzth zzcbl;
    private AdListener zzcbm;
    private AdMetadataListener zzccb;
    private String zzccm;
    private final zzaje zzcee;
    private zzve zzcei;
    private OnCustomRenderedAdLoadedListener zzcej;
    private RewardedVideoAdListener zzcer;
    private boolean zzces;
    private final Context zzlk;

    public zzww(Context context) {
        this(context, zztu.zzccd, null);
    }

    public zzww(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zztu.zzccd, publisherInterstitialAd);
    }

    @VisibleForTesting
    private zzww(Context context, zztu zztuVar, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzcee = new zzaje();
        this.zzlk = context;
        this.zzaax = zztuVar;
    }

    public final AdListener getAdListener() {
        return this.zzcbm;
    }

    public final String getAdUnitId() {
        return this.zzccm;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzbkg;
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzcej;
    }

    public final boolean isLoaded() {
        try {
            if (this.zzcei == null) {
                return false;
            }
            return this.zzcei.isReady();
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
            return false;
        }
    }

    public final boolean isLoading() {
        try {
            if (this.zzcei == null) {
                return false;
            }
            return this.zzcei.isLoading();
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
            return false;
        }
    }

    public final void zza(zzws zzwsVar) {
        try {
            if (this.zzcei == null) {
                if (this.zzccm == null) {
                    zzci("loadAd");
                }
                zztw zztwVarZzny = this.zzces ? zztw.zzny() : new zztw();
                zzuc zzucVarZzog = zzuo.zzog();
                Context context = this.zzlk;
                this.zzcei = new zzuf(zzucVarZzog, context, zztwVarZzny, this.zzccm, this.zzcee).zzd(context, false);
                if (this.zzcbm != null) {
                    this.zzcei.zza(new zztl(this.zzcbm));
                }
                if (this.zzcbl != null) {
                    this.zzcei.zza(new zztk(this.zzcbl));
                }
                if (this.zzccb != null) {
                    this.zzcei.zza(new zztq(this.zzccb));
                }
                if (this.zzbkg != null) {
                    this.zzcei.zza(new zzty(this.zzbkg));
                }
                if (this.zzcej != null) {
                    this.zzcei.zza(new zzzs(this.zzcej));
                }
                if (this.zzcer != null) {
                    this.zzcei.zza(new zzapv(this.zzcer));
                }
                this.zzcei.setImmersiveMode(this.zzble);
            }
            if (this.zzcei.zza(zztu.zza(this.zzlk, zzwsVar))) {
                this.zzcee.zzf(zzwsVar.zzoy());
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setAdListener(AdListener adListener) {
        try {
            this.zzcbm = adListener;
            if (this.zzcei != null) {
                this.zzcei.zza(adListener != null ? new zztl(adListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zza(zzth zzthVar) {
        try {
            this.zzcbl = zzthVar;
            if (this.zzcei != null) {
                this.zzcei.zza(zzthVar != null ? new zztk(zzthVar) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setAdUnitId(String str) {
        if (this.zzccm != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzccm = str;
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        try {
            this.zzccb = adMetadataListener;
            if (this.zzcei != null) {
                this.zzcei.zza(adMetadataListener != null ? new zztq(adMetadataListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final Bundle getAdMetadata() {
        try {
            if (this.zzcei != null) {
                return this.zzcei.getAdMetadata();
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
        return new Bundle();
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzbkg = appEventListener;
            if (this.zzcei != null) {
                this.zzcei.zza(appEventListener != null ? new zzty(appEventListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzcej = onCustomRenderedAdLoadedListener;
            if (this.zzcei != null) {
                this.zzcei.zza(onCustomRenderedAdLoadedListener != null ? new zzzs(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzcer = rewardedVideoAdListener;
            if (this.zzcei != null) {
                this.zzcei.zza(rewardedVideoAdListener != null ? new zzapv(rewardedVideoAdListener) : null);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void zzc(boolean z) {
        this.zzces = true;
    }

    public final String getMediationAdapterClassName() {
        try {
            if (this.zzcei != null) {
                return this.zzcei.zzjp();
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
            return null;
        }
    }

    public final void show() {
        try {
            zzci("show");
            this.zzcei.showInterstitial();
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    public final void setImmersiveMode(boolean z) {
        try {
            this.zzble = z;
            if (this.zzcei != null) {
                this.zzcei.setImmersiveMode(z);
            }
        } catch (RemoteException e) {
            zzawo.zze("#008 Must be called on the main UI thread.", e);
        }
    }

    private final void zzci(String str) {
        if (this.zzcei != null) {
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63);
        sb.append("The ad unit ID must be set on InterstitialAd before ");
        sb.append(str);
        sb.append(" is called.");
        throw new IllegalStateException(sb.toString());
    }
}
