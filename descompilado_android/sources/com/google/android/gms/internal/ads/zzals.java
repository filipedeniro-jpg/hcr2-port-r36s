package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.mediation.rtb.RtbSignalData;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzals extends zzalm {
    private MediationRewardedAd zzddi;
    private final RtbAdapter zzdei;
    private MediationInterstitialAd zzdej;
    private String zzdek = "";

    public zzals(RtbAdapter rtbAdapter) {
        this.zzdei = rtbAdapter;
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(String[] strArr, Bundle[] bundleArr) {
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zzab(IObjectWrapper iObjectWrapper) {
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzakx zzakxVar, zzajj zzajjVar, zztw zztwVar) throws RemoteException {
        try {
            this.zzdei.loadBannerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdk(str2), zzd(zztpVar), zzc(zztpVar), zztpVar.zzng, zztpVar.zzabj, zztpVar.zzabk, zza(str2, zztpVar), com.google.android.gms.ads.zzb.zza(zztwVar.width, zztwVar.height, zztwVar.zzabd), this.zzdek), new zzalr(this, zzakxVar, zzajjVar));
        } catch (Throwable th) {
            zzawo.zzc("Adapter failed to render banner ad.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzalc zzalcVar, zzajj zzajjVar) throws RemoteException {
        try {
            this.zzdei.loadInterstitialAd(new MediationInterstitialAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdk(str2), zzd(zztpVar), zzc(zztpVar), zztpVar.zzng, zztpVar.zzabj, zztpVar.zzabk, zza(str2, zztpVar), this.zzdek), new zzalu(this, zzalcVar, zzajjVar));
        } catch (Throwable th) {
            zzawo.zzc("Adapter failed to render interstitial ad.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzali zzaliVar, zzajj zzajjVar) throws RemoteException {
        try {
            this.zzdei.loadRewardedAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdk(str2), zzd(zztpVar), zzc(zztpVar), zztpVar.zzng, zztpVar.zzabj, zztpVar.zzabk, zza(str2, zztpVar), this.zzdek), new zzalt(this, zzaliVar, zzajjVar));
        } catch (Throwable th) {
            zzawo.zzc("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzald zzaldVar, zzajj zzajjVar) throws RemoteException {
        try {
            this.zzdei.loadNativeAd(new MediationNativeAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzdk(str2), zzd(zztpVar), zzc(zztpVar), zztpVar.zzng, zztpVar.zzabj, zztpVar.zzabk, zza(str2, zztpVar), this.zzdek), new zzalw(this, zzaldVar, zzajjVar));
        } catch (Throwable th) {
            zzawo.zzc("Adapter failed to render rewarded ad.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final boolean zzac(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationInterstitialAd mediationInterstitialAd = this.zzdej;
        if (mediationInterstitialAd == null) {
            return false;
        }
        try {
            mediationInterstitialAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzawo.zzc("", th);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final boolean zzad(IObjectWrapper iObjectWrapper) throws RemoteException {
        MediationRewardedAd mediationRewardedAd = this.zzddi;
        if (mediationRewardedAd == null) {
            return false;
        }
        try {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
            return true;
        } catch (Throwable th) {
            zzawo.zzc("", th);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final zzwk getVideoController() {
        MediationExtrasReceiver mediationExtrasReceiver = this.zzdei;
        if (!(mediationExtrasReceiver instanceof com.google.android.gms.ads.mediation.zza)) {
            return null;
        }
        try {
            return ((com.google.android.gms.ads.mediation.zza) mediationExtrasReceiver).getVideoController();
        } catch (Throwable th) {
            zzawo.zzc("", th);
            return null;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zztw zztwVar, zzalo zzaloVar) throws RemoteException {
        AdFormat adFormat;
        try {
            zzalv zzalvVar = new zzalv(this, zzaloVar);
            RtbAdapter rtbAdapter = this.zzdei;
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
                throw new IllegalArgumentException("Internal Error");
            }
            MediationConfiguration mediationConfiguration = new MediationConfiguration(adFormat, bundle2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(mediationConfiguration);
            rtbAdapter.collectSignals(new RtbSignalData((Context) ObjectWrapper.unwrap(iObjectWrapper), arrayList, bundle, com.google.android.gms.ads.zzb.zza(zztwVar.width, zztwVar.height, zztwVar.zzabd)), zzalvVar);
        } catch (Throwable th) {
            zzawo.zzc("Error generating signals for RTB", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final zzalx zzsc() throws RemoteException {
        return zzalx.zza(this.zzdei.getVersionInfo());
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final zzalx zzsd() throws RemoteException {
        return zzalx.zza(this.zzdei.getSDKVersionInfo());
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final void zzdh(String str) {
        this.zzdek = str;
    }

    private static Bundle zzdk(String str) throws RemoteException {
        String strValueOf = String.valueOf(str);
        zzawo.zzeu(strValueOf.length() != 0 ? "Server parameters: ".concat(strValueOf) : new String("Server parameters: "));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                bundle2.putString(next, jSONObject.getString(next));
            }
            return bundle2;
        } catch (JSONException e) {
            zzawo.zzc("", e);
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

    private final Bundle zzd(zztp zztpVar) {
        Bundle bundle;
        return (zztpVar.zzcbu == null || (bundle = zztpVar.zzcbu.getBundle(this.zzdei.getClass().getName())) == null) ? new Bundle() : bundle;
    }
}
