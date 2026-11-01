package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchv implements zzcfn<zzbtp, zzcvs, zzcgu> {
    private final Executor zzfbm;
    private final zzbsr zzfyh;
    private final Context zzlk;

    public zzchv(Context context, zzbsr zzbsrVar, Executor executor) {
        this.zzlk = context;
        this.zzfyh = zzbsrVar;
        this.zzfbm = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzcvs, zzcgu> zzcflVar) throws zzcvr {
        zzcflVar.zzdde.zza(this.zzlk, zzcvjVar.zzgjo.zzffn.zzgjs, zzcvbVar.zzgiz.toString(), zzavm.zza(zzcvbVar.zzgiw), (zzajj) zzcflVar.zzfwt, zzcvjVar.zzgjo.zzffn.zzddy, zzcvjVar.zzgjo.zzffn.zzgju);
    }

    private static boolean zza(zzcvj zzcvjVar, int i) {
        return zzcvjVar.zzgjo.zzffn.zzgju.contains(Integer.toString(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbtp zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar) throws zzcvr, zzcin {
        zzbtx zzbtxVarZza;
        zzajq zzajqVarZzrl = ((zzcvs) zzcflVar.zzdde).zzrl();
        zzajr zzajrVarZzrm = ((zzcvs) zzcflVar.zzdde).zzrm();
        zzajw zzajwVarZzrr = ((zzcvs) zzcflVar.zzdde).zzrr();
        if (zzajwVarZzrr != null && zza(zzcvjVar, 6)) {
            zzbtxVarZza = zzbtx.zzb(zzajwVarZzrr);
        } else if (zzajqVarZzrl != null && zza(zzcvjVar, 6)) {
            zzbtxVarZza = zzbtx.zzb(zzajqVarZzrl);
        } else if (zzajqVarZzrl != null && zza(zzcvjVar, 2)) {
            zzbtxVarZza = zzbtx.zza(zzajqVarZzrl);
        } else if (zzajrVarZzrm != null && zza(zzcvjVar, 6)) {
            zzbtxVarZza = zzbtx.zzb(zzajrVarZzrm);
        } else if (zzajrVarZzrm != null && zza(zzcvjVar, 1)) {
            zzbtxVarZza = zzbtx.zza(zzajrVarZzrm);
        } else {
            throw new zzcin("No native ad mappers", 0);
        }
        if (!zzcvjVar.zzgjo.zzffn.zzgju.contains(Integer.toString(zzbtxVarZza.zzahl()))) {
            throw new zzcin("No corresponding native ad listener", 0);
        }
        zzbuc zzbucVarZza = this.zzfyh.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbuj(zzbtxVarZza), new zzbvm(zzajrVarZzrm, zzajqVarZzrl, zzajwVarZzrr));
        ((zzcgu) zzcflVar.zzfwt).zza(zzbucVarZza.zzacc());
        zzbucVarZza.zzabx().zza(new zzbgh((zzcvs) zzcflVar.zzdde), this.zzfbm);
        return zzbucVarZza.zzacd();
    }
}
