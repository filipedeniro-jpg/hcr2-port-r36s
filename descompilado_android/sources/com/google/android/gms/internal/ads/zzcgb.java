package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgb implements zzcfn<zzbhy, zzcvs, zzcgu> {
    private final zzawv zzbkw;
    private final Executor zzfbm;
    private final zzbit zzfwx;
    private final Context zzlk;

    public zzcgb(Context context, zzawv zzawvVar, zzbit zzbitVar, Executor executor) {
        this.zzlk = context;
        this.zzbkw = zzawvVar;
        this.zzfwx = zzbitVar;
        this.zzfbm = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgu> zzcflVar) throws zzcvr {
        zztw zztwVarZza = zzcvo.zza(this.zzlk, zzcvbVar.zzgiy);
        if (this.zzbkw.zzdvt < 4100000) {
            zzcflVar.zzdde.zza(this.zzlk, zztwVarZza, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), (zzajj) zzcflVar.zzfwt);
        } else {
            zzcflVar.zzdde.zza(this.zzlk, zztwVarZza, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), zzavm.zza(zzcvbVar.zzgiw), (zzajj) zzcflVar.zzfwt);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbhy zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbit zzbitVar = this.zzfwx;
        zzbkk zzbkkVar = new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev);
        View view = ((zzcvs) zzcflVar.zzdde).getView();
        zzcvs zzcvsVar = (zzcvs) zzcflVar.zzdde;
        zzcvsVar.getClass();
        zzbhx zzbhxVarZza = zzbitVar.zza(zzbkkVar, new zzbib(view, null, zzcge.zza(zzcvsVar), zzcvbVar.zzgiy.get(0)));
        zzbhxVarZza.zzacw().zzq(((zzcvs) zzcflVar.zzdde).getView());
        zzbhxVarZza.zzabx().zza(new zzbgh((zzcvs) zzcflVar.zzdde), this.zzfbm);
        ((zzcgu) zzcflVar.zzfwt).zza(zzbhxVarZza.zzacc());
        return zzbhxVarZza.zzacv();
    }
}
