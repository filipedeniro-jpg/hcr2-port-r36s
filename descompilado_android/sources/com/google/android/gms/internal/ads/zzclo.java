package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclo extends zzvd implements zzboe {
    private final ViewGroup zzfcw;
    private final zzbds zzfyn;

    @Nullable
    @GuardedBy("this")
    private zzbhy zzgbd;
    private final Context zzgbf;

    @Nullable
    @GuardedBy("this")
    private zzdcp<zzbhy> zzgbo;
    private final zzboa zzgbw;

    @Nullable
    @GuardedBy("this")
    private zzzn zzgbx;
    private final zzcls zzgbt = new zzcls();
    private final zzclp zzgbu = new zzclp();
    private final zzclr zzgbv = new zzclr();

    @GuardedBy("this")
    private final zzcvm zzgbg = new zzcvm();

    public zzclo(zzbds zzbdsVar, Context context, zztw zztwVar, String str) {
        this.zzfcw = new FrameLayout(context);
        this.zzfyn = zzbdsVar;
        this.zzgbf = context;
        this.zzgbg.zzd(zztwVar).zzgf(str);
        this.zzgbw = zzbdsVar.zzabf();
        this.zzgbw.zza(this, this.zzfyn.zzabb());
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final boolean isReady() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setImmersiveMode(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void setUserId(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void showInterstitial() {
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
    public final void zza(zzapo zzapoVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzqu zzquVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztx zztxVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzwq zzwqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbm(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final IObjectWrapper zzjm() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfcw);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean zza(zztp zztpVar) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (this.zzgbo != null) {
            return false;
        }
        zzcvt.zze(this.zzgbf, zztpVar.zzcbq);
        zzcvk zzcvkVarZzamz = this.zzgbg.zzg(zztpVar).zzamz();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqo)).booleanValue() && this.zzgbg.zzjo().zzcci && this.zzgbt != null) {
            this.zzgbt.onAdFailedToLoad(1);
            return false;
        }
        zzbit zzbitVarZzc = zzc(zzcvkVarZzamz);
        this.zzgbo = zzbitVarZzc.zzaci().zzafo();
        zzdcf.zza(this.zzgbo, new zzcln(this, zzbitVarZzc), this.zzfyn.zzabb());
        return true;
    }

    private final synchronized zzbit zzc(zzcvk zzcvkVar) {
        return this.zzfyn.zzabi().zzc(new zzblu.zza().zzbx(this.zzgbf).zza(zzcvkVar).zzafu()).zzc(new zzbox.zza().zza((zzth) this.zzgbt, this.zzfyn.zzabb()).zza(this.zzgbu, this.zzfyn.zzabb()).zza((zzbmg) this.zzgbt, this.zzfyn.zzabb()).zza((zzbnm) this.zzgbt, this.zzfyn.zzabb()).zza((zzbml) this.zzgbt, this.zzfyn.zzabb()).zza(this.zzgbv, this.zzfyn.zzabb()).zzagi()).zza(new zzcko(this.zzgbx)).zzb(new zzbsn(zzbui.zzfls, null)).zza(new zzbjn(this.zzgbw)).zzb(new zzbht(this.zzfcw)).zzacr();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgbd != null) {
            this.zzgbd.destroy();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzgbd != null) {
            this.zzgbd.zzafi().zzbt(null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzgbd != null) {
            this.zzgbd.zzafi().zzbu(null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzur zzurVar) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgbt.zzc(zzurVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzur zzjr() {
        return this.zzgbt.zzale();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvm zzvmVar) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzgbv.zzb(zzvmVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzvm zzjq() {
        return this.zzgbv.zzald();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zzjn() {
        Preconditions.checkMainThread("recordManualImpression must be called on the main UI thread.");
        if (this.zzgbd != null) {
            this.zzgbd.zzjn();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized zztw zzjo() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        if (this.zzgbd != null) {
            return zzcvo.zza(this.zzgbf, (List<zzcva>) Collections.singletonList(this.zzgbd.zzaeq()));
        }
        return this.zzgbg.zzjo();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zztw zztwVar) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
        this.zzgbg.zzd(zztwVar);
        if (this.zzgbd != null) {
            this.zzgbd.zza(this.zzfcw, zztwVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getMediationAdapterClassName() {
        if (this.zzgbd == null) {
            return null;
        }
        return this.zzgbd.getMediationAdapterClassName();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String zzjp() {
        if (this.zzgbd == null) {
            return null;
        }
        return this.zzgbd.zzjp();
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
    public final synchronized zzwk getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzgbd == null) {
            return null;
        }
        return this.zzgbd.getVideoController();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getAdUnitId() {
        return this.zzgbg.zzamy();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzyc zzycVar) {
        Preconditions.checkMainThread("setVideoOptions must be called on the main UI thread.");
        this.zzgbg.zzc(zzycVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzuq zzuqVar) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzgbu.zzb(zzuqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzzn zzznVar) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzgbx = zzznVar;
    }

    @Override // com.google.android.gms.internal.ads.zzboe
    public final synchronized void zzafy() {
        boolean zZza;
        Object parent = this.zzfcw.getParent();
        if (parent instanceof View) {
            View view = (View) parent;
            zZza = com.google.android.gms.ads.internal.zzp.zzjy().zza(view, view.getContext());
        } else {
            zZza = false;
        }
        if (zZza) {
            zza(this.zzgbg.zzamx());
        } else {
            this.zzgbw.zzde(60);
        }
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

    static /* synthetic */ zzdcp zza(zzclo zzcloVar, zzdcp zzdcpVar) {
        zzcloVar.zzgbo = null;
        return null;
    }
}
