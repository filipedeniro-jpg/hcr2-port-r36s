package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakk<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzajj zzddq;

    public zzakk(zzajj zzajjVar) {
        this.zzddq = zzajjVar;
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzawo.zzdv("Adapter called onClick.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakj(this));
        } else {
            try {
                this.zzddq.onAdClicked();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzawo.zzdv("Adapter called onDismissScreen.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zzeu("#008 Must be called on the main UI thread.");
            zzawe.zzzb.post(new zzako(this));
        } else {
            try {
                this.zzddq.onAdClosed();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, AdRequest.ErrorCode errorCode) {
        String strValueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error. ");
        sb.append(strValueOf);
        zzawo.zzdv(sb.toString());
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakn(this, errorCode));
        } else {
            try {
                this.zzddq.onAdFailedToLoad(zzakw.zza(errorCode));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzawo.zzdv("Adapter called onLeaveApplication.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakq(this));
        } else {
            try {
                this.zzddq.onAdLeftApplication();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzawo.zzdv("Adapter called onPresentScreen.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakp(this));
        } else {
            try {
                this.zzddq.onAdOpened();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzawo.zzdv("Adapter called onReceivedAd.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzaks(this));
        } else {
            try {
                this.zzddq.onAdLoaded();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzawo.zzdv("Adapter called onDismissScreen.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakr(this));
        } else {
            try {
                this.zzddq.onAdClosed();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, AdRequest.ErrorCode errorCode) {
        String strValueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error ");
        sb.append(strValueOf);
        sb.append(".");
        zzawo.zzdv(sb.toString());
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzaku(this, errorCode));
        } else {
            try {
                this.zzddq.onAdFailedToLoad(zzakw.zza(errorCode));
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzawo.zzdv("Adapter called onLeaveApplication.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakt(this));
        } else {
            try {
                this.zzddq.onAdLeftApplication();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzawo.zzdv("Adapter called onPresentScreen.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakm(this));
        } else {
            try {
                this.zzddq.onAdOpened();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzawo.zzdv("Adapter called onReceivedAd.");
        zzuo.zzof();
        if (!zzawe.zzwc()) {
            zzawo.zze("#008 Must be called on the main UI thread.", null);
            zzawe.zzzb.post(new zzakl(this));
        } else {
            try {
                this.zzddq.onAdLoaded();
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
            }
        }
    }

    static /* synthetic */ zzajj zza(zzakk zzakkVar) {
        return zzakkVar.zzddq;
    }
}
