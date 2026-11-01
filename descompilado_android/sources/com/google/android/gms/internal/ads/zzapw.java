package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzapw implements RewardedVideoAd {

    @NonNull
    private final zzapl zzdnl;
    private String zzdnn;
    private String zzdno;
    private final Context zzlk;
    private final Object lock = new Object();
    private final zzapv zzdnm = new zzapv(null);

    public zzapw(Context context, @Nullable zzapl zzaplVar) {
        this.zzdnl = zzaplVar == null ? new zzxr() : zzaplVar;
        this.zzlk = context.getApplicationContext();
    }

    private final void zza(String str, zzws zzwsVar) {
        synchronized (this.lock) {
            if (this.zzdnl == null) {
                return;
            }
            try {
                this.zzdnl.zza(zztu.zza(this.zzlk, zzwsVar, str));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String str, AdRequest adRequest) {
        zza(str, adRequest.zzda());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void loadAd(String str, PublisherAdRequest publisherAdRequest) {
        zza(str, publisherAdRequest.zzda());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void show() {
        synchronized (this.lock) {
            if (this.zzdnl == null) {
                return;
            }
            try {
                this.zzdnl.show();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.lock) {
            this.zzdnm.setRewardedVideoAdListener(rewardedVideoAdListener);
            if (this.zzdnl != null) {
                try {
                    this.zzdnl.zza(this.zzdnm);
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        synchronized (this.lock) {
            if (this.zzdnl != null) {
                try {
                    this.zzdnl.zza(new zztq(adMetadataListener));
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final Bundle getAdMetadata() {
        synchronized (this.lock) {
            if (this.zzdnl != null) {
                try {
                    return this.zzdnl.getAdMetadata();
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
            return new Bundle();
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setUserId(String str) {
        synchronized (this.lock) {
            this.zzdnn = str;
            if (this.zzdnl != null) {
                try {
                    this.zzdnl.setUserId(str);
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final boolean isLoaded() {
        synchronized (this.lock) {
            if (this.zzdnl == null) {
                return false;
            }
            try {
                return this.zzdnl.isLoaded();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause() {
        pause(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void pause(Context context) {
        synchronized (this.lock) {
            if (this.zzdnl == null) {
                return;
            }
            try {
                this.zzdnl.zzn(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume() {
        resume(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void resume(Context context) {
        synchronized (this.lock) {
            if (this.zzdnl == null) {
                return;
            }
            try {
                this.zzdnl.zzo(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy() {
        destroy(null);
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void destroy(Context context) {
        synchronized (this.lock) {
            this.zzdnm.setRewardedVideoAdListener(null);
            if (this.zzdnl == null) {
                return;
            }
            try {
                this.zzdnl.zzp(ObjectWrapper.wrap(context));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.lock) {
            rewardedVideoAdListener = this.zzdnm.getRewardedVideoAdListener();
        }
        return rewardedVideoAdListener;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getUserId() {
        String str;
        synchronized (this.lock) {
            str = this.zzdnn;
        }
        return str;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getMediationAdapterClassName() {
        try {
            if (this.zzdnl != null) {
                return this.zzdnl.getMediationAdapterClassName();
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setImmersiveMode(boolean z) {
        synchronized (this.lock) {
            if (this.zzdnl != null) {
                try {
                    this.zzdnl.setImmersiveMode(z);
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final void setCustomData(String str) {
        synchronized (this.lock) {
            if (this.zzdnl != null) {
                try {
                    this.zzdnl.setCustomData(str);
                    this.zzdno = str;
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAd
    public final String getCustomData() {
        String str;
        synchronized (this.lock) {
            str = this.zzdno;
        }
        return str;
    }
}
