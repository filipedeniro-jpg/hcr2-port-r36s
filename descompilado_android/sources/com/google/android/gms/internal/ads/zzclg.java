package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclg extends zzvd {
    private final ViewGroup zzfcw;
    private final zzcvk zzffn;
    private final zzur zzfip;
    private final zzbhy zzgbd;
    private final Context zzlk;

    public zzclg(Context context, @Nullable zzur zzurVar, zzcvk zzcvkVar, zzbhy zzbhyVar) {
        this.zzlk = context;
        this.zzfip = zzurVar;
        this.zzffn = zzcvkVar;
        this.zzgbd = zzbhyVar;
        FrameLayout frameLayout = new FrameLayout(this.zzlk);
        frameLayout.removeAllViews();
        frameLayout.addView(this.zzgbd.zzaer(), com.google.android.gms.ads.internal.zzp.zzka().zzvn());
        frameLayout.setMinimumHeight(zzjo().heightPixels);
        frameLayout.setMinimumWidth(zzjo().widthPixels);
        this.zzfcw = frameLayout;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final boolean isLoading() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final boolean isReady() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setUserId(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void showInterstitial() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void stopLoading() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzand zzandVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzanj zzanjVar, String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzapo zzapoVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzqu zzquVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztx zztxVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzwq zzwqVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbm(String str) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final IObjectWrapper zzjm() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfcw);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgbd.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final boolean zza(zztp zztpVar) throws RemoteException {
        zzatm.zzet("loadAd is not supported for a Publisher AdView returned from AdLoader.");
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgbd.zzafi().zzbt(null);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzgbd.zzafi().zzbu(null);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzjn() throws RemoteException {
        this.zzgbd.zzjn();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zztw zzjo() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        return zzcvo.zza(this.zzlk, (List<zzcva>) Collections.singletonList(this.zzgbd.zzaeq()));
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzgbd.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzwk getVideoController() throws RemoteException {
        return this.zzgbd.getVideoController();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final String getAdUnitId() throws RemoteException {
        return this.zzffn.zzgjt;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzvm zzjq() throws RemoteException {
        return this.zzffn.zzgjy;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzur zzjr() throws RemoteException {
        return this.zzfip;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final String zzjp() throws RemoteException {
        return this.zzgbd.zzjp();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzyc zzycVar) throws RemoteException {
        zzatm.zzet("setVideoOptions is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztw zztwVar) throws RemoteException {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        zzbhy zzbhyVar = this.zzgbd;
        if (zzbhyVar != null) {
            zzbhyVar.zza(this.zzfcw, zztwVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzzn zzznVar) throws RemoteException {
        zzatm.zzet("setOnCustomRenderedAdLoadedListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzuq zzuqVar) throws RemoteException {
        zzatm.zzet("setAdClickListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvs zzvsVar) throws RemoteException {
        zzatm.zzet("setCorrelationIdProvider is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        zzatm.zzet("setManualImpressionsEnabled is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzur zzurVar) throws RemoteException {
        zzatm.zzet("setAdListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvm zzvmVar) throws RemoteException {
        zzatm.zzet("setAppEventListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvh zzvhVar) throws RemoteException {
        zzatm.zzet("setAdMetadataListener is not supported in Publisher AdView returned by AdLoader.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final Bundle getAdMetadata() throws RemoteException {
        zzatm.zzet("getAdMetadata is not supported in Publisher AdView returned by AdLoader.");
        return new Bundle();
    }
}
