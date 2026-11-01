package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclu extends zzvd {
    private final Executor zzfbm;
    private final zzbds zzfyn;
    private final Context zzgbf;

    @Nullable
    @GuardedBy("this")
    private zzdcp<zzbrc> zzgbo;

    @Nullable
    @GuardedBy("this")
    private zzzn zzgbx;

    @Nullable
    @GuardedBy("this")
    private zzbrc zzgcf;
    private final zzcls zzgbt = new zzcls();
    private final zzclr zzgbv = new zzclr();
    private final zzcto zzgce = new zzcto();

    @GuardedBy("this")
    private final zzcvm zzgbg = new zzcvm();

    @GuardedBy("this")
    private boolean zzgcg = false;

    public zzclu(zzbds zzbdsVar, Context context, zztw zztwVar, String str) {
        this.zzfyn = zzbdsVar;
        this.zzgbg.zzd(zztwVar).zzgf(str);
        this.zzfbm = zzbdsVar.zzabb();
        this.zzgbf = context;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzwk getVideoController() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setUserId(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void stopLoading() {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzand zzandVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzanj zzanjVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzqu zzquVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztw zztwVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztx zztxVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzuq zzuqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzwq zzwqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbm(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final IObjectWrapper zzjm() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzjn() {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zztw zzjo() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean zza(zztp zztpVar) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (this.zzgbo == null && !zzalf()) {
            zzcvt.zze(this.zzgbf, zztpVar.zzcbq);
            this.zzgcf = null;
            zzcvk zzcvkVarZzamz = this.zzgbg.zzg(zztpVar).zzamz();
            zzbox.zza zzaVar = new zzbox.zza();
            if (this.zzgce != null) {
                zzaVar.zza((zzbmg) this.zzgce, this.zzfyn.zzabb()).zza((zzbnm) this.zzgce, this.zzfyn.zzabb()).zza((zzbml) this.zzgce, this.zzfyn.zzabb());
            }
            zzbry zzbryVarZzadc = this.zzfyn.zzabk().zzd(new zzblu.zza().zzbx(this.zzgbf).zza(zzcvkVarZzamz).zzafu()).zzd(zzaVar.zza((zzbmg) this.zzgbt, this.zzfyn.zzabb()).zza((zzbnm) this.zzgbt, this.zzfyn.zzabb()).zza((zzbml) this.zzgbt, this.zzfyn.zzabb()).zza((zzth) this.zzgbt, this.zzfyn.zzabb()).zza(this.zzgbv, this.zzfyn.zzabb()).zzagi()).zzb(new zzcko(this.zzgbx)).zzadc();
            this.zzgbo = zzbryVarZzadc.zzaci().zzafo();
            zzdcf.zza(this.zzgbo, new zzclt(this, zzbryVarZzadc), this.zzfbm);
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgcf != null) {
            this.zzgcf.zzafi().zzbv(null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzgcf != null) {
            this.zzgcf.zzafi().zzbt(null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzgcf != null) {
            this.zzgcf.zzafi().zzbu(null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzur zzurVar) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgbt.zzc(zzurVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvm zzvmVar) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzgbv.zzb(zzvmVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (this.zzgcf == null) {
            return;
        }
        if (this.zzgcf.zzagp()) {
            this.zzgcf.zzay(this.zzgcg);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getMediationAdapterClassName() {
        if (this.zzgcf == null) {
            return null;
        }
        return this.zzgcf.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String zzjp() {
        if (this.zzgcf == null) {
            return null;
        }
        return this.zzgcf.zzjp();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzvs zzvsVar) {
        Preconditions.checkMainThread("setCorrelationIdProvider must be called on the main UI thread");
        this.zzgbg.zzc(zzvsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzgbg.zzbe(z);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean isLoading() {
        return (this.zzgbo == null || this.zzgbo.isDone()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean isReady() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzalf();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getAdUnitId() {
        return this.zzgbg.zzamy();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzvm zzjq() {
        return this.zzgbv.zzald();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzur zzjr() {
        return this.zzgbt.zzale();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzgcg = z;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzyc zzycVar) {
        this.zzgbg.zzc(zzycVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzzn zzznVar) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzgbx = zzznVar;
    }

    private final synchronized boolean zzalf() {
        return (this.zzgcf == null || this.zzgcf.isClosed()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvh zzvhVar) {
        Preconditions.checkMainThread("setAdMetadataListener must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata must be called on the main UI thread.");
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzapo zzapoVar) {
        this.zzgce.zzb(zzapoVar);
    }

    static /* synthetic */ zzdcp zza(zzclu zzcluVar, zzdcp zzdcpVar) {
        zzcluVar.zzgbo = null;
        return null;
    }
}
