package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchx implements zzcfn<zzbtp, zzalj, zzcgu> {
    private final zzbsr zzfyh;
    private zzajw zzfyl;
    private final Context zzlk;

    public zzchx(Context context, zzbsr zzbsrVar) {
        this.zzlk = context;
        this.zzfyh = zzbsrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl<zzalj, zzcgu> zzcflVar) throws zzcvr {
        try {
            zzcflVar.zzdde.zzdh(zzcvbVar.zzdek);
            zzcflVar.zzdde.zza(zzcvbVar.zzehw, zzcvbVar.zzgiz.toString(), zzcvjVar.zzgjo.zzffn.zzgjs, ObjectWrapper.wrap(this.zzlk), new zzchz(this, zzcflVar), (zzajj) zzcflVar.zzfwt);
        } catch (RemoteException e) {
            throw new zzcvr(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcfn
    public final /* synthetic */ zzbtp zzb(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar) throws zzcvr, zzcin {
        if (!zzcvjVar.zzgjo.zzffn.zzgju.contains(Integer.toString(6))) {
            throw new zzcin("Unified must be used for RTB.", 1);
        }
        zzbtx zzbtxVarZzb = zzbtx.zzb(this.zzfyl);
        if (!zzcvjVar.zzgjo.zzffn.zzgju.contains(Integer.toString(zzbtxVarZzb.zzahl()))) {
            throw new zzcin("No corresponding native ad listener", 0);
        }
        zzbuc zzbucVarZza = this.zzfyh.zza(new zzbkk(zzcvjVar, zzcvbVar, zzcflVar.zzfev), new zzbuj(zzbtxVarZzb), new zzbvm(null, null, this.zzfyl));
        ((zzcgu) zzcflVar.zzfwt).zza(zzbucVarZza.zzacc());
        return zzbucVarZza.zzacd();
    }
}
