package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzake implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzajj zzddq;
    private NativeAdMapper zzddr;
    private UnifiedNativeAdMapper zzdds;
    private NativeCustomTemplateAd zzddt;

    public zzake(zzajj zzajjVar) {
        this.zzddq = zzajjVar;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void zza(MediationBannerAdapter mediationBannerAdapter, String str, String str2) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAppEvent.");
        try {
            this.zzddq.onAppEvent(str, str2);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClicked.");
        try {
            this.zzddq.onAdClicked();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClosed.");
        try {
            this.zzddq.onAdClosed();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error. ");
        sb.append(i);
        zzawo.zzdv(sb.toString());
        try {
            this.zzddq.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLeftApplication.");
        try {
            this.zzddq.onAdLeftApplication();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdOpened.");
        try {
            this.zzddq.onAdOpened();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerListener
    public final void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLoaded.");
        try {
            this.zzddq.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClicked.");
        try {
            this.zzddq.onAdClicked();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClosed.");
        try {
            this.zzddq.onAdClosed();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzawo.zzdv(sb.toString());
        try {
            this.zzddq.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLeftApplication.");
        try {
            this.zzddq.onAdLeftApplication();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdOpened.");
        try {
            this.zzddq.onAdOpened();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialListener
    public final void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLoaded.");
        try {
            this.zzddq.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLoaded.");
        this.zzddr = nativeAdMapper;
        this.zzdds = null;
        zza(mediationNativeAdapter, this.zzdds, this.zzddr);
        try {
            this.zzddq.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, UnifiedNativeAdMapper unifiedNativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLoaded.");
        this.zzdds = unifiedNativeAdMapper;
        this.zzddr = null;
        zza(mediationNativeAdapter, this.zzdds, this.zzddr);
        try {
            this.zzddq.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    private static void zza(MediationNativeAdapter mediationNativeAdapter, @Nullable UnifiedNativeAdMapper unifiedNativeAdMapper, @Nullable NativeAdMapper nativeAdMapper) {
        if (mediationNativeAdapter instanceof AdMobAdapter) {
            return;
        }
        VideoController videoController = new VideoController();
        videoController.zza(new zzajx());
        if (unifiedNativeAdMapper != null && unifiedNativeAdMapper.hasVideoContent()) {
            unifiedNativeAdMapper.zza(videoController);
        }
        if (nativeAdMapper == null || !nativeAdMapper.hasVideoContent()) {
            return;
        }
        nativeAdMapper.zza(videoController);
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        String strValueOf = String.valueOf(nativeCustomTemplateAd.getCustomTemplateId());
        zzawo.zzdv(strValueOf.length() != 0 ? "Adapter called onAdLoaded with template id ".concat(strValueOf) : new String("Adapter called onAdLoaded with template id "));
        this.zzddt = nativeCustomTemplateAd;
        try {
            this.zzddq.onAdLoaded();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
        if (nativeCustomTemplateAd instanceof zzabp) {
            try {
                this.zzddq.zza(((zzabp) nativeCustomTemplateAd).zzqr(), str);
                return;
            } catch (RemoteException e) {
                zzawo.zze("#007 Could not call remote method.", e);
                return;
            }
        }
        zzawo.zzeu("Unexpected native custom template ad type.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        zzawo.zzdv(sb.toString());
        try {
            this.zzddq.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdOpened.");
        try {
            this.zzddq.onAdOpened();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdClosed.");
        try {
            this.zzddq.onAdClosed();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onAdLeftApplication.");
        try {
            this.zzddq.onAdLeftApplication();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzddr;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzdds;
        if (this.zzddt == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzawo.zze("#007 Could not call remote method.", null);
                return;
            }
            if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideClickHandling()) {
                zzawo.zzdv("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideClickHandling()) {
                zzawo.zzdv("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzawo.zzdv("Adapter called onAdClicked.");
        try {
            this.zzddq.onAdClicked();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzddr;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzdds;
        if (this.zzddt == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzawo.zze("#007 Could not call remote method.", null);
                return;
            }
            if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideImpressionRecording()) {
                zzawo.zzdv("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            } else if (nativeAdMapper != null && !nativeAdMapper.getOverrideImpressionRecording()) {
                zzawo.zzdv("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzawo.zzdv("Adapter called onAdImpression.");
        try {
            this.zzddq.onAdImpression();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeListener
    public final void onVideoEnd(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzawo.zzdv("Adapter called onVideoEnd.");
        try {
            this.zzddq.onVideoEnd();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    public final NativeAdMapper zzrw() {
        return this.zzddr;
    }

    public final UnifiedNativeAdMapper zzrx() {
        return this.zzdds;
    }

    public final NativeCustomTemplateAd zzry() {
        return this.zzddt;
    }
}
