package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzajz extends zzajh {
    private final Object zzdde;
    private zzake zzddf;
    private zzapz zzddg;
    private IObjectWrapper zzddh;
    private MediationRewardedAd zzddi;

    public zzajz(@NonNull MediationAdapter mediationAdapter) {
        this.zzdde = mediationAdapter;
    }

    public zzajz(@NonNull Adapter adapter) {
        this.zzdde = adapter;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final IObjectWrapper zzrk() throws RemoteException {
        Object obj = this.zzdde;
        if (!(obj instanceof MediationBannerAdapter)) {
            String canonicalName = MediationBannerAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) obj).getBannerView());
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
        zza(iObjectWrapper, zztwVar, zztpVar, str, null, zzajjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException {
        if (!(this.zzdde instanceof MediationBannerAdapter)) {
            String canonicalName = MediationBannerAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzdde;
            mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzake(zzajjVar), zza(str, zztpVar, str2), com.google.android.gms.ads.zzb.zza(zztwVar.width, zztwVar.height, zztwVar.zzabd), new zzaka(zztpVar.zzcbn == -1 ? null : new Date(zztpVar.zzcbn), zztpVar.zzcbo, zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null, zztpVar.zzng, zzc(zztpVar), zztpVar.zzabj, zztpVar.zzcbz, zztpVar.zzabk, zza(str, zztpVar)), zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final Bundle zzrn() {
        Object obj = this.zzdde;
        if (!(obj instanceof zzbdq)) {
            String canonicalName = zzbdq.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            return new Bundle();
        }
        return ((zzbdq) obj).zzrn();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
        zza(iObjectWrapper, zztpVar, str, (String) null, zzajjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException {
        if (!(this.zzdde instanceof MediationInterstitialAdapter)) {
            String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzdde;
            mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzake(zzajjVar), zza(str, zztpVar, str2), new zzaka(zztpVar.zzcbn == -1 ? null : new Date(zztpVar.zzcbn), zztpVar.zzcbo, zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null, zztpVar.zzng, zzc(zztpVar), zztpVar.zzabj, zztpVar.zzcbz, zztpVar.zzabk, zza(str, zztpVar)), zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final Bundle getInterstitialAdapterInfo() {
        Object obj = this.zzdde;
        if (!(obj instanceof zzbdp)) {
            String canonicalName = zzbdp.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            return new Bundle();
        }
        return ((zzbdp) obj).getInterstitialAdapterInfo();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar, zzaai zzaaiVar, List<String> list) throws RemoteException {
        Object obj = this.zzdde;
        if (!(obj instanceof MediationNativeAdapter)) {
            String canonicalName = MediationNativeAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) obj;
            zzaki zzakiVar = new zzaki(zztpVar.zzcbn == -1 ? null : new Date(zztpVar.zzcbn), zztpVar.zzcbo, zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null, zztpVar.zzng, zzc(zztpVar), zztpVar.zzabj, zzaaiVar, list, zztpVar.zzcbz, zztpVar.zzabk, zza(str, zztpVar));
            Bundle bundle = zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(mediationNativeAdapter.getClass().getName()) : null;
            this.zzddf = new zzake(zzajjVar);
            mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzddf, zza(str, zztpVar, str2), zzakiVar, bundle);
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final Bundle zzro() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajq zzrl() {
        NativeAdMapper nativeAdMapperZzrw = this.zzddf.zzrw();
        if (nativeAdMapperZzrw instanceof NativeAppInstallAdMapper) {
            return new zzakg((NativeAppInstallAdMapper) nativeAdMapperZzrw);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajw zzrr() {
        UnifiedNativeAdMapper unifiedNativeAdMapperZzrx = this.zzddf.zzrx();
        if (unifiedNativeAdMapperZzrx != null) {
            return new zzaky(unifiedNativeAdMapperZzrx);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajr zzrm() {
        NativeAdMapper nativeAdMapperZzrw = this.zzddf.zzrw();
        if (nativeAdMapperZzrw instanceof NativeContentAdMapper) {
            return new zzakf((NativeContentAdMapper) nativeAdMapperZzrw);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzabo zzrq() {
        NativeCustomTemplateAd nativeCustomTemplateAdZzry = this.zzddf.zzry();
        if (nativeCustomTemplateAdZzry instanceof zzabp) {
            return ((zzabp) nativeCustomTemplateAdZzry).zzqr();
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final boolean zzrp() {
        return this.zzdde instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzapz zzapzVar, String str2) throws RemoteException {
        zzaka zzakaVar;
        Bundle bundle;
        Object obj = this.zzdde;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzawo.zzdv("Initialize rewarded video adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdde;
                Bundle bundleZza = zza(str2, zztpVar, (String) null);
                if (zztpVar != null) {
                    zzaka zzakaVar2 = new zzaka(zztpVar.zzcbn == -1 ? null : new Date(zztpVar.zzcbn), zztpVar.zzcbo, zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null, zztpVar.zzng, zzc(zztpVar), zztpVar.zzabj, zztpVar.zzcbz, zztpVar.zzabk, zza(str2, zztpVar));
                    bundle = zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                    zzakaVar = zzakaVar2;
                } else {
                    zzakaVar = null;
                    bundle = null;
                }
                mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzakaVar, str, new zzaqa(zzapzVar), bundleZza, bundle);
                return;
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
        if (!(obj instanceof Adapter)) {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        this.zzddh = iObjectWrapper;
        this.zzddg = zzapzVar;
        zzapzVar.zzai(ObjectWrapper.wrap(obj));
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zzapz zzapzVar, List<String> list) throws RemoteException {
        if (!(this.zzdde instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String canonicalName = InitializableMediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzdde;
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(zza(it.next(), (zztp) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzaqa(zzapzVar), arrayList);
        } catch (Throwable th) {
            zzawo.zzd("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(zztp zztpVar, String str) throws RemoteException {
        zza(zztpVar, str, (String) null);
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(zztp zztpVar, String str, String str2) throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzawo.zzdv("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzdde;
                mediationRewardedVideoAdAdapter.loadAd(new zzaka(zztpVar.zzcbn == -1 ? null : new Date(zztpVar.zzcbn), zztpVar.zzcbo, zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null, zztpVar.zzng, zzc(zztpVar), zztpVar.zzabj, zztpVar.zzcbz, zztpVar.zzabk, zza(str, zztpVar)), zza(str, zztpVar, str2), zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
                return;
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
        if (!(obj instanceof Adapter)) {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzb(this.zzddh, zztpVar, str, new zzakd((Adapter) obj, this.zzddg));
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void showVideo() throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzawo.zzdv("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzdde).showVideo();
                return;
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
        if (!(obj instanceof Adapter)) {
            String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
            sb.append(canonicalName);
            sb.append(" or ");
            sb.append(canonicalName2);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName3);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        MediationRewardedAd mediationRewardedAd = this.zzddi;
        if (mediationRewardedAd != null) {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(this.zzddh));
        } else {
            zzawo.zzes("Can not show null mediated rewarded ad.");
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final boolean isInitialized() throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationRewardedVideoAdAdapter) {
            zzawo.zzdv("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzdde).isInitialized();
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
        if (obj instanceof Adapter) {
            return this.zzddg != null;
        }
        String canonicalName = MediationRewardedVideoAdAdapter.class.getCanonicalName();
        String canonicalName2 = Adapter.class.getCanonicalName();
        String canonicalName3 = this.zzdde.getClass().getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 26 + String.valueOf(canonicalName2).length() + String.valueOf(canonicalName3).length());
        sb.append(canonicalName);
        sb.append(" or ");
        sb.append(canonicalName2);
        sb.append(" #009 Class mismatch: ");
        sb.append(canonicalName3);
        zzawo.zzeu(sb.toString());
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzb(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
        Bundle bundle;
        if (!(this.zzdde instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Requesting rewarded ad from adapter.");
        try {
            Adapter adapter = (Adapter) this.zzdde;
            zzakc zzakcVar = new zzakc(this, zzajjVar, adapter);
            Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            Bundle bundleZza = zza(str, zztpVar, (String) null);
            if (zztpVar.zzcbu == null || (bundle = zztpVar.zzcbu.getBundle(this.zzdde.getClass().getName())) == null) {
                bundle = new Bundle();
            }
            adapter.loadRewardedAd(new MediationRewardedAdConfiguration(context, "", bundleZza, bundle, zzc(zztpVar), zztpVar.zzng, zztpVar.zzabj, zztpVar.zzabk, zza(str, zztpVar), ""), zzakcVar);
        } catch (Exception e) {
            zzawo.zzc("", e);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzw(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (!(this.zzdde instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Show rewarded ad from adapter.");
        MediationRewardedAd mediationRewardedAd = this.zzddi;
        if (mediationRewardedAd != null) {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
        } else {
            zzawo.zzes("Can not show null mediation rewarded ad.");
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void setImmersiveMode(boolean z) throws RemoteException {
        Object obj = this.zzdde;
        if (!(obj instanceof OnImmersiveModeUpdatedListener)) {
            String canonicalName = OnImmersiveModeUpdatedListener.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            return;
        }
        try {
            ((OnImmersiveModeUpdatedListener) obj).onImmersiveModeUpdated(z);
        } catch (Throwable th) {
            zzawo.zzc("", th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzwk getVideoController() {
        Object obj = this.zzdde;
        if (!(obj instanceof com.google.android.gms.ads.mediation.zza)) {
            return null;
        }
        try {
            return ((com.google.android.gms.ads.mediation.zza) obj).getVideoController();
        } catch (Throwable th) {
            zzawo.zzc("", th);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void showInterstitial() throws RemoteException {
        if (!(this.zzdde instanceof MediationInterstitialAdapter)) {
            String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
            String canonicalName2 = this.zzdde.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22 + String.valueOf(canonicalName2).length());
            sb.append(canonicalName);
            sb.append(" #009 Class mismatch: ");
            sb.append(canonicalName2);
            zzawo.zzeu(sb.toString());
            throw new RemoteException();
        }
        zzawo.zzdv("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzdde).showInterstitial();
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void destroy() throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onDestroy();
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void pause() throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onPause();
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void resume() throws RemoteException {
        Object obj = this.zzdde;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onResume();
            } catch (Throwable th) {
                zzawo.zzc("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzv(IObjectWrapper iObjectWrapper) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        Object obj = this.zzdde;
        if (obj instanceof OnContextChangedListener) {
            ((OnContextChangedListener) obj).onContextChanged(context);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zzaez zzaezVar, List<zzafh> list) throws RemoteException {
        AdFormat adFormat;
        if (!(this.zzdde instanceof Adapter)) {
            throw new RemoteException();
        }
        zzakb zzakbVar = new zzakb(this, zzaezVar);
        ArrayList arrayList = new ArrayList();
        for (zzafh zzafhVar : list) {
            String str = zzafhVar.zzcyf;
            byte b = -1;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals(ai.a.cW)) {
                        b = 0;
                    }
                    break;
                case -1052618729:
                    if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                        b = 3;
                    }
                    break;
                case -239580146:
                    if (str.equals(Constants.CONVERT_REWARDED)) {
                        b = 2;
                    }
                    break;
                case 604727084:
                    if (str.equals(aa.e.bo)) {
                        b = 1;
                    }
                    break;
            }
            if (b == 0) {
                adFormat = AdFormat.BANNER;
            } else if (b == 1) {
                adFormat = AdFormat.INTERSTITIAL;
            } else if (b == 2) {
                adFormat = AdFormat.REWARDED;
            } else if (b == 3) {
                adFormat = AdFormat.NATIVE;
            } else {
                throw new RemoteException();
            }
            arrayList.add(new MediationConfiguration(adFormat, zzafhVar.extras));
        }
        ((Adapter) this.zzdde).initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzakbVar, arrayList);
    }

    private final Bundle zza(String str, zztp zztpVar, String str2) throws RemoteException {
        Bundle bundle;
        String strValueOf = String.valueOf(str);
        zzawo.zzdv(strValueOf.length() != 0 ? "Server parameters: ".concat(strValueOf) : new String("Server parameters: "));
        try {
            Bundle bundle2 = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                bundle = new Bundle();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    bundle.putString(next, jSONObject.getString(next));
                }
            } else {
                bundle = bundle2;
            }
            if (this.zzdde instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zztpVar != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zztpVar.zzabj);
                }
            }
            bundle.remove("max_ad_content_rating");
            return bundle;
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    private static boolean zzc(zztp zztpVar) {
        if (zztpVar.zzcbq) {
            return true;
        }
        zzuo.zzof();
        return zzawe.zzwb();
    }

    @Nullable
    private static String zza(String str, zztp zztpVar) {
        String str2 = zztpVar.zzabl;
        try {
            return new JSONObject(str).getString("max_ad_content_rating");
        } catch (JSONException unused) {
            return str2;
        }
    }
}
