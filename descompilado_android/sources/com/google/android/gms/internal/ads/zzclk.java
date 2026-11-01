package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclk extends zzvd implements com.google.android.gms.ads.internal.overlay.zzw, zzbnv, zzqt {
    private final ViewGroup zzfcw;
    private final zzbds zzfyn;
    private final Context zzgbf;
    private zzqu zzgbk;

    @Nullable
    private zzbhh zzgbm;

    @Nullable
    @GuardedBy("this")
    protected zzbhs zzgbn;

    @Nullable
    @GuardedBy("this")
    private zzdcp<zzbhs> zzgbo;
    private AtomicBoolean zzgbj = new AtomicBoolean();
    private final zzclq zzgbl = new zzclq();

    @GuardedBy("this")
    private final zzcvm zzgbg = new zzcvm();

    public zzclk(zzbds zzbdsVar, Context context, zztw zztwVar, String str) {
        this.zzfcw = new FrameLayout(context);
        this.zzfyn = zzbdsVar;
        this.zzgbf = context;
        this.zzgbg.zzd(zztwVar).zzgf(str);
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
    public final void zza(zzuq zzuqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzur zzurVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvh zzvhVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzvm zzvmVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzwq zzwqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zzbm(String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzvm zzjq() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final zzur zzjr() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final IObjectWrapper zzjm() {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzfcw);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zztx zztxVar) {
        this.zzgbg.zzb(zztxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean zza(zztp zztpVar) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (this.zzgbo != null) {
            return false;
        }
        this.zzgbj = new AtomicBoolean();
        zzcvt.zze(this.zzgbf, zztpVar.zzcbq);
        zzbho zzbhoVarZzb = zzb(this.zzgbg.zzg(zztpVar).zzamz());
        this.zzgbo = zzbhoVarZzb.zzaci().zzafo();
        zzdcf.zza(this.zzgbo, new zzcll(this, zzbhoVarZzb), this.zzfyn.zzabb());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.google.android.gms.ads.internal.overlay.zzo zza(zzbhs zzbhsVar) {
        boolean zZzzr = zzbhsVar.zzzr();
        int iIntValue = ((Integer) zzuo.zzoj().zzd(zzyt.zzcqc)).intValue();
        com.google.android.gms.ads.internal.overlay.zzr zzrVar = new com.google.android.gms.ads.internal.overlay.zzr();
        zzrVar.size = 50;
        zzrVar.paddingLeft = zZzzr ? iIntValue : 0;
        zzrVar.paddingRight = zZzzr ? 0 : iIntValue;
        zzrVar.paddingTop = 0;
        zzrVar.paddingBottom = iIntValue;
        return new com.google.android.gms.ads.internal.overlay.zzo(this.zzgbf, zzrVar, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RelativeLayout.LayoutParams zzb(zzbhs zzbhsVar) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(zzbhsVar.zzzr() ? 11 : 9);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(zzbhs zzbhsVar) {
        zzbhsVar.zza(this);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzw
    public final void zzsk() {
        zzalc();
    }

    @Override // com.google.android.gms.internal.ads.zzqt
    public final void zzmd() {
        zzalc();
    }

    @Override // com.google.android.gms.internal.ads.zzbnv
    public final void zzafv() {
        int iZzaen;
        zzbhs zzbhsVar = this.zzgbn;
        if (zzbhsVar != null && (iZzaen = zzbhsVar.zzaen()) > 0) {
            this.zzgbm = new zzbhh(this.zzfyn.zzabc(), com.google.android.gms.ads.internal.zzp.zzkf());
            this.zzgbm.zza(iZzaen, new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzclm
                private final zzclk zzgbi;

                {
                    this.zzgbi = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzgbi.zzalb();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzakz, reason: merged with bridge method [inline-methods] */
    public final void zzalc() {
        if (this.zzgbj.compareAndSet(false, true)) {
            zzbhs zzbhsVar = this.zzgbn;
            zzqz zzqzVarZzaet = zzbhsVar != null ? zzbhsVar.zzaet() : null;
            if (zzqzVarZzaet != null) {
                try {
                    zzqzVarZzaet.zzme();
                } catch (RemoteException e) {
                    zzawo.zzc("", e);
                }
            }
            this.zzfcw.removeAllViews();
            zzbhh zzbhhVar = this.zzgbm;
            if (zzbhhVar != null) {
                com.google.android.gms.ads.internal.zzp.zzkb().zzb(zzbhhVar);
            }
            destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zztw zzala() {
        return zzcvo.zza(this.zzgbf, (List<zzcva>) Collections.singletonList(this.zzgbn.zzaeq()));
    }

    private final synchronized zzbho zzb(zzcvk zzcvkVar) {
        return this.zzfyn.zzabj().zzb(new zzblu.zza().zzbx(this.zzgbf).zza(zzcvkVar).zzafu()).zzb(new zzbox.zza().zza(this.zzgbl, this.zzfyn.zzabb()).zza(this, this.zzfyn.zzabb()).zzagi()).zza(new zzbht(this.zzfcw)).zzabw();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        if (this.zzgbn != null) {
            this.zzgbn.destroy();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final void zza(zzqu zzquVar) {
        this.zzgbk = zzquVar;
        this.zzgbl.zzb(zzquVar);
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized zztw zzjo() {
        Preconditions.checkMainThread("getAdSize must be called on the main UI thread.");
        if (this.zzgbn == null) {
            return null;
        }
        return zzcvo.zza(this.zzgbf, (List<zzcva>) Collections.singletonList(this.zzgbn.zzaeq()));
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zztw zztwVar) {
        Preconditions.checkMainThread("setAdSize must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getMediationAdapterClassName() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String zzjp() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized boolean isLoading() {
        return (this.zzgbo == null || this.zzgbo.isDone()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized String getAdUnitId() {
        return this.zzgbg.zzamy();
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zzjn() {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzvs zzvsVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void setManualImpressionsEnabled(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized zzwk getVideoController() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzyc zzycVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final synchronized void zza(zzzn zzznVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzve
    public final Bundle getAdMetadata() {
        return new Bundle();
    }

    final /* synthetic */ void zzalb() {
        this.zzfyn.zzabb().execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzclj
            private final zzclk zzgbi;

            {
                this.zzgbi = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzgbi.zzalc();
            }
        });
    }

    static /* synthetic */ zzdcp zza(zzclk zzclkVar, zzdcp zzdcpVar) {
        zzclkVar.zzgbo = null;
        return null;
    }
}
