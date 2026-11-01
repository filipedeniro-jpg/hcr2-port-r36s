package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzccx implements zzdbq<zzaok, zzcvj> {
    private final zzboh zzftz;

    public zzccx(zzboh zzbohVar) {
        this.zzftz = zzbohVar;
    }

    protected abstract zzdcp<zzcvj> zze(zzaok zzaokVar) throws zzcce;

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final /* synthetic */ zzdcp<zzcvj> zzf(zzaok zzaokVar) throws Exception {
        zzaok zzaokVar2 = zzaokVar;
        this.zzftz.zzb(zzaokVar2);
        zzdcp<zzcvj> zzdcpVarZze = zze(zzaokVar2);
        zzdcf.zza(zzdcpVarZze, new zzcda(this), zzawx.zzdwb);
        return zzdcpVarZze;
    }
}
