package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakh<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzajh {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzddw;
    private final NETWORK_EXTRAS zzddx;

    public zzakh(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.zzddw = mediationAdapter;
        this.zzddx = network_extras;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzwk getVideoController() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final boolean isInitialized() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void setImmersiveMode(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void showVideo() {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zzaez zzaezVar, List<zzafh> list) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zzapz zzapzVar, List<String> list) {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzapz zzapzVar, String str2) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar, zzaai zzaaiVar, List<String> list) {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(zztp zztpVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(zztp zztpVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzb(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajq zzrl() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajr zzrm() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final boolean zzrp() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzabo zzrq() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final zzajw zzrr() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzv(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zzw(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final IObjectWrapper zzrk() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzddw;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzawo.zzeu(strValueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(strValueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) mediationAdapter).getBannerView());
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
        zza(iObjectWrapper, zztwVar, zztpVar, str, null, zzajjVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException {
        AdSize adSize;
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzddw;
        if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzawo.zzeu(strValueOf.length() != 0 ? "Not a MediationBannerAdapter: ".concat(strValueOf) : new String("Not a MediationBannerAdapter: "));
            throw new RemoteException();
        }
        zzawo.zzdv("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzddw;
            zzakk zzakkVar = new zzakk(zzajjVar);
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
            MediationServerParameters mediationServerParametersZzdf = zzdf(str);
            AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
            for (int i = 0; i < 6; i++) {
                if (adSizeArr[i].getWidth() == zztwVar.width && adSizeArr[i].getHeight() == zztwVar.height) {
                    adSize = adSizeArr[i];
                    mediationBannerAdapter.requestBannerAd(zzakkVar, activity, mediationServerParametersZzdf, adSize, zzakw.zza(zztpVar, zzc(zztpVar)), this.zzddx);
                }
            }
            adSize = new AdSize(com.google.android.gms.ads.zzb.zza(zztwVar.width, zztwVar.height, zztwVar.zzabd));
            mediationBannerAdapter.requestBannerAd(zzakkVar, activity, mediationServerParametersZzdf, adSize, zzakw.zza(zztpVar, zzc(zztpVar)), this.zzddx);
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final Bundle zzrn() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException {
        zza(iObjectWrapper, zztpVar, str, (String) null, zzajjVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzaji
    public final void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzddw;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzawo.zzeu(strValueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(strValueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzawo.zzdv("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzddw).requestInterstitialAd(new zzakk(zzajjVar), (Activity) ObjectWrapper.unwrap(iObjectWrapper), zzdf(str), zzakw.zza(zztpVar, zzc(zztpVar)), this.zzddx);
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void showInterstitial() throws RemoteException {
        MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter = this.zzddw;
        if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
            String strValueOf = String.valueOf(mediationAdapter.getClass().getCanonicalName());
            zzawo.zzeu(strValueOf.length() != 0 ? "Not a MediationInterstitialAdapter: ".concat(strValueOf) : new String("Not a MediationInterstitialAdapter: "));
            throw new RemoteException();
        }
        zzawo.zzdv("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzddw).showInterstitial();
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
    public final void destroy() throws RemoteException {
        try {
            this.zzddw.destroy();
        } catch (Throwable th) {
            zzawo.zzc("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzaji
    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    private final SERVER_PARAMETERS zzdf(String str) throws RemoteException {
        HashMap map;
        try {
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                map = new HashMap(jSONObject.length());
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.getString(next));
                }
            } else {
                map = new HashMap(0);
            }
            Class<SERVER_PARAMETERS> serverParametersType = this.zzddw.getServerParametersType();
            if (serverParametersType == null) {
                return null;
            }
            SERVER_PARAMETERS server_parametersNewInstance = serverParametersType.newInstance();
            server_parametersNewInstance.load(map);
            return server_parametersNewInstance;
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
}
