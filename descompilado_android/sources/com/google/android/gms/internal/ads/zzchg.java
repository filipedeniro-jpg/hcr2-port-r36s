package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchg implements zzcfn<zzbrc, zzcvs, zzcgu> {
    private final zzawv zzbkw;
    private final Executor zzfbm;
    private final zzbry zzfxh;
    private final Context zzlk;

    public zzchg(Context context, zzawv zzawvVar, zzbry zzbryVar, Executor executor) {
        this.zzlk = context;
        this.zzbkw = zzawvVar;
        this.zzfxh = zzbryVar;
        this.zzfbm = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgu> zzcflVar) throws zzcvr {
        if (this.zzbkw.zzdvt < 4100000) {
            zzcflVar.zzdde.zza(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), (zzajj) zzcflVar.zzfwt);
        } else {
            zzcflVar.zzdde.zza(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), zzavm.zza(zzcvbVar.zzgiw), (zzajj) zzcflVar.zzfwt);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbrc zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, final zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbre zzbreVarZza = this.zzfxh.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbrd(new zzbse(zzcflVar) { // from class: com.google.android.gms.internal.ads.zzchf
            private final zzcfl zzfxe;

            {
                this.zzfxe = zzcflVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.internal.ads.zzbse
            public final void zza(boolean z, Context context) {
                zzcfl zzcflVar2 = this.zzfxe;
                try {
                    ((zzcvs) zzcflVar2.zzdde).setImmersiveMode(z);
                    ((zzcvs) zzcflVar2.zzdde).showInterstitial();
                } catch (zzcvr unused) {
                    zzatm.zzet("Cannot show interstitial.");
                }
            }
        }));
        zzbreVarZza.zzabx().zza(new zzbgh((zzcvs) zzcflVar.zzdde), this.zzfbm);
        ((zzcgu) zzcflVar.zzfwt).zza(zzbreVarZza.zzacc());
        return zzbreVarZza.zzade();
    }
}
