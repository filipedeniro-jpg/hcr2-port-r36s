package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcme implements zzcma<zzbju> {
    private final zzbds zzfyn;
    private final Context zzgbf;

    @GuardedBy("this")
    private final zzcvm zzgbg;
    private final zzcly zzgcs;

    @Nullable
    @GuardedBy("this")
    private zzbkb zzgct;

    public zzcme(zzbds zzbdsVar, Context context, zzcly zzclyVar, zzcvm zzcvmVar) {
        this.zzfyn = zzbdsVar;
        this.zzgbf = context;
        this.zzgcs = zzclyVar;
        this.zzgbg = zzcvmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcma
    public final boolean isLoading() {
        zzbkb zzbkbVar = this.zzgct;
        return zzbkbVar != null && zzbkbVar.isLoading();
    }

    @Override // com.google.android.gms.internal.ads.zzcma
    public final boolean zza(zztp zztpVar, String str, zzclz zzclzVar, zzcmc<? super zzbju> zzcmcVar) throws RemoteException {
        if (str == null) {
            zzatm.zzes("Ad unit ID should not be null for NativeAdLoader.");
            this.zzfyn.zzabb().execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcmd
                private final zzcme zzgcr;

                {
                    this.zzgcr = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzgcr.zzaln();
                }
            });
            return false;
        }
        zzcvt.zze(this.zzgbf, zztpVar.zzcbq);
        zzcvk zzcvkVarZzamz = this.zzgbg.zzg(zztpVar).zzdj(zzclzVar instanceof zzcmb ? ((zzcmb) zzclzVar).zzgcq : 1).zzamz();
        zzbsr zzbsrVarZzabv = this.zzfyn.zzabl().zza(new zzblu.zza().zzbx(this.zzgbf).zza(zzcvkVarZzamz).zzafu()).zza(new zzbox.zza().zza(this.zzgcs.zzalj(), this.zzfyn.zzabb()).zza(this.zzgcs.zzalk(), this.zzfyn.zzabb()).zza(this.zzgcs.zzall(), this.zzfyn.zzabb()).zza(this.zzgcs.zzalm(), this.zzfyn.zzabb()).zza(this.zzgcs.zzali(), this.zzfyn.zzabb()).zza(zzcvkVarZzamz.zzgjy, this.zzfyn.zzabb()).zzagi()).zza(this.zzgcs.zzalh()).zzabv();
        zzbsrVarZzabv.zzack().zzdk(1);
        this.zzgct = new zzbkb(this.zzfyn.zzabd(), this.zzfyn.zzabc(), zzbsrVarZzabv.zzaci().zzafo());
        this.zzgct.zza(new zzcmg(this, zzcmcVar, zzbsrVarZzabv));
        return true;
    }

    final /* synthetic */ void zzaln() {
        this.zzgcs.zzalk().onAdFailedToLoad(1);
    }
}
