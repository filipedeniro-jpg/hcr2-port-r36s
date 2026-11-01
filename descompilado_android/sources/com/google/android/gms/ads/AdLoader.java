package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzaai;
import com.google.android.gms.internal.ads.zzacv;
import com.google.android.gms.internal.ads.zzacx;
import com.google.android.gms.internal.ads.zzacy;
import com.google.android.gms.internal.ads.zzacz;
import com.google.android.gms.internal.ads.zzada;
import com.google.android.gms.internal.ads.zzadb;
import com.google.android.gms.internal.ads.zzaje;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zztl;
import com.google.android.gms.internal.ads.zztu;
import com.google.android.gms.internal.ads.zztw;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzuw;
import com.google.android.gms.internal.ads.zzux;
import com.google.android.gms.internal.ads.zzws;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class AdLoader {
    private final zztu zzaax;
    private final zzuw zzaay;
    private final Context zzlk;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static class Builder {
        private final zzux zzaba;
        private final Context zzlk;

        @KeepForSdk
        @Deprecated
        public Builder withCorrelator(@NonNull Correlator correlator) {
            return this;
        }

        public Builder(Context context, String str) {
            this((Context) Preconditions.checkNotNull(context, "context cannot be null"), zzuo.zzog().zzb(context, str, new zzaje()));
        }

        private Builder(Context context, zzux zzuxVar) {
            this.zzlk = context;
            this.zzaba = zzuxVar;
        }

        @Deprecated
        public Builder forContentAd(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzaba.zza(new zzacy(onContentAdLoadedListener));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to add content ad listener", e);
            }
            return this;
        }

        @Deprecated
        public Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzaba.zza(new zzacv(onAppInstallAdLoadedListener));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forUnifiedNativeAd(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
            try {
                this.zzaba.zza(new zzadb(onUnifiedNativeAdLoadedListener));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to add google native ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            try {
                this.zzaba.zza(str, new zzada(onCustomTemplateAdLoadedListener), onCustomClickListener == null ? null : new zzacx(onCustomClickListener));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder forPublisherAdView(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener, AdSize... adSizeArr) {
            if (adSizeArr == null || adSizeArr.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzaba.zza(new zzacz(onPublisherAdViewLoadedListener), new zztw(this.zzlk, adSizeArr));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to add publisher banner ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzaba.zzb(new zztl(adListener));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzaba.zza(new zzaai(nativeAdOptions));
            } catch (RemoteException e) {
                zzawo.zzd("Failed to specify native ad options", e);
            }
            return this;
        }

        public Builder withPublisherAdViewOptions(PublisherAdViewOptions publisherAdViewOptions) {
            try {
                this.zzaba.zza(publisherAdViewOptions);
            } catch (RemoteException e) {
                zzawo.zzd("Failed to specify DFP banner ad options", e);
            }
            return this;
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.zzlk, this.zzaba.zzon());
            } catch (RemoteException e) {
                zzawo.zzc("Failed to build AdLoader.", e);
                return null;
            }
        }
    }

    AdLoader(Context context, zzuw zzuwVar) {
        this(context, zzuwVar, zztu.zzccd);
    }

    private AdLoader(Context context, zzuw zzuwVar, zztu zztuVar) {
        this.zzlk = context;
        this.zzaay = zzuwVar;
        this.zzaax = zztuVar;
    }

    private final void zza(zzws zzwsVar) {
        try {
            this.zzaay.zzb(zztu.zza(this.zzlk, zzwsVar));
        } catch (RemoteException e) {
            zzawo.zzc("Failed to load ad.", e);
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzda());
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAds(AdRequest adRequest, int i) {
        try {
            this.zzaay.zza(zztu.zza(this.zzlk, adRequest.zzda()), i);
        } catch (RemoteException e) {
            zzawo.zzc("Failed to load ads.", e);
        }
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzda());
    }

    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzaay.zzjp();
        } catch (RemoteException e) {
            zzawo.zzd("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzaay.isLoading();
        } catch (RemoteException e) {
            zzawo.zzd("Failed to check if ad is loading.", e);
            return false;
        }
    }
}
