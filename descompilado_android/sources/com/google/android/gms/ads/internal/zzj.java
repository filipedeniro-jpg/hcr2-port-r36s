package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzand;
import com.google.android.gms.internal.ads.zzanj;
import com.google.android.gms.internal.ads.zzapo;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzawe;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzawx;
import com.google.android.gms.internal.ads.zzdf;
import com.google.android.gms.internal.ads.zzdi;
import com.google.android.gms.internal.ads.zzqu;
import com.google.android.gms.internal.ads.zztp;
import com.google.android.gms.internal.ads.zztw;
import com.google.android.gms.internal.ads.zztx;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzuq;
import com.google.android.gms.internal.ads.zzur;
import com.google.android.gms.internal.ads.zzvd;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzvm;
import com.google.android.gms.internal.ads.zzvs;
import com.google.android.gms.internal.ads.zzwk;
import com.google.android.gms.internal.ads.zzwq;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyt;
import com.google.android.gms.internal.ads.zzzn;
import java.util.Map;
import java.util.concurrent.Future;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzj extends zzvd {
    private final zzawv zzblh;
    private final zztw zzbli;
    private final Future<zzdf> zzblj = zzawx.zzdvx.submit(new zzo(this));
    private final zzq zzblk;

    @Nullable
    private WebView zzbll;

    @Nullable
    private zzur zzblm;

    @Nullable
    private zzdf zzbln;
    private AsyncTask<Void, Void, String> zzblo;
    private final Context zzlk;

    public zzj(Context context, zztw zztwVar, String str, zzawv zzawvVar) {
        this.zzlk = context;
        this.zzblh = zzawvVar;
        this.zzbli = zztwVar;
        this.zzbll = new WebView(this.zzlk);
        this.zzblk = new zzq(str);
        zzbl(0);
        this.zzbll.setVerticalScrollBarEnabled(false);
        this.zzbll.getSettings().setJavaScriptEnabled(true);
        this.zzbll.setWebViewClient(new zzm(this));
        this.zzbll.setOnTouchListener(new zzl(this));
    }

    @Override // com.google.android.gms.internal.ads.zzve
    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    @Nullable
    public final zzwk getVideoController() {
        return null;
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
    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void stopLoading() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    @Nullable
    public final String zzjp() throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final IObjectWrapper zzjm() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzbll);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzblo.cancel(true);
        this.zzblj.cancel(true);
        this.zzbll.destroy();
        this.zzbll = null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final boolean zza(zztp zztpVar) throws RemoteException {
        Preconditions.checkNotNull(this.zzbll, "This Search Ad has already been torn down");
        this.zzblk.zza(zztpVar, this.zzblh);
        this.zzblo = new zzn(this, null).execute(new Void[0]);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzur zzurVar) throws RemoteException {
        this.zzblm = zzurVar;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvm zzvmVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvh zzvhVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final Bundle getAdMetadata() {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzjn() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zztw zzjo() throws RemoteException {
        return this.zzbli;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztw zztwVar) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzand zzandVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzanj zzanjVar, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzvm zzjq() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzur zzjr() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzzn zzznVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzuq zzuqVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvs zzvsVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzapo zzapoVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbm(String str) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzyc zzycVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzwq zzwqVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztx zztxVar) {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzqu zzquVar) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    @VisibleForTesting
    final int zzbn(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter("height");
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            zzuo.zzof();
            return zzawe.zza(this.zzlk, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @VisibleForTesting
    final void zzbl(int i) {
        if (this.zzbll == null) {
            return;
        }
        this.zzbll.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
    }

    @VisibleForTesting
    final String zzjs() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath((String) zzuo.zzoj().zzd(zzyt.zzcov));
        builder.appendQueryParameter("query", this.zzblk.getQuery());
        builder.appendQueryParameter("pubId", this.zzblk.zzky());
        Map<String, String> mapZzkz = this.zzblk.zzkz();
        for (String str : mapZzkz.keySet()) {
            builder.appendQueryParameter(str, mapZzkz.get(str));
        }
        Uri uriBuild = builder.build();
        zzdf zzdfVar = this.zzbln;
        if (zzdfVar != null) {
            try {
                uriBuild = zzdfVar.zza(uriBuild, this.zzlk);
            } catch (zzdi e) {
                zzatm.zzd("Unable to process ad data", e);
            }
        }
        String strZzjt = zzjt();
        String encodedQuery = uriBuild.getEncodedQuery();
        StringBuilder sb = new StringBuilder(String.valueOf(strZzjt).length() + 1 + String.valueOf(encodedQuery).length());
        sb.append(strZzjt);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    @VisibleForTesting
    final String zzjt() {
        String strZzkx = this.zzblk.zzkx();
        if (TextUtils.isEmpty(strZzkx)) {
            strZzkx = "www.google.com";
        }
        String str = (String) zzuo.zzoj().zzd(zzyt.zzcov);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzkx).length() + 8 + String.valueOf(str).length());
        sb.append("https://");
        sb.append(strZzkx);
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzbo(String str) {
        if (this.zzbln == null) {
            return str;
        }
        Uri uriZzb = Uri.parse(str);
        try {
            uriZzb = this.zzbln.zzb(uriZzb, this.zzlk);
        } catch (zzdi e) {
            zzatm.zzd("Unable to process ad data", e);
        }
        return uriZzb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbp(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.zzlk.startActivity(intent);
    }
}
