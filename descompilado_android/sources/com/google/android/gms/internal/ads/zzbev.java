package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbev implements zzbjg {
    private zzdvv<zzcvb> zzelj;
    private zzdvv<Set<zzbqc<zzbms>>> zzelt;
    private zzdvv<zzbmv> zzelu;
    private zzdvv<zzcvj> zzelv;
    private zzdvv<Set<zzbqc<zzbnm>>> zzemp;
    private zzdvv<zzbnl> zzemq;
    private zzdvv<String> zzemx;
    private zzdvv<zzbjt> zzemy;
    private final /* synthetic */ zzbet zzewg;
    private final zzbjf zzexd;
    private zzdvv<zzack> zzexe;
    private zzdvv<Runnable> zzexf;
    private zzdvv<zzbjb> zzexg;

    private zzbev(zzbet zzbetVar, zzbkk zzbkkVar, zzbjf zzbjfVar) {
        this.zzewg = zzbetVar;
        this.zzexd = zzbjfVar;
        this.zzelv = zzbkl.zzc(zzbkkVar);
        this.zzelj = zzbkj.zza(zzbkkVar);
        this.zzelt = zzdvr.zzar(0, 2).zzaq(this.zzewg.zzess).zzaq(this.zzewg.zzest).zzbdb();
        this.zzelu = zzdvj.zzan(zzbmy.zzi(this.zzelt));
        this.zzemp = zzdvr.zzar(4, 3).zzap(this.zzewg.zzetg).zzap(this.zzewg.zzeth).zzap(this.zzewg.zzeti).zzaq(this.zzewg.zzetj).zzaq(this.zzewg.zzetk).zzaq(this.zzewg.zzetl).zzap(this.zzewg.zzetm).zzbdb();
        this.zzemq = zzdvj.zzan(zzbnn.zzk(this.zzemp));
        this.zzemx = zzbkm.zze(zzbkkVar);
        this.zzemy = zzblg.zzb(this.zzelv, this.zzelj, this.zzelu, this.zzemq, this.zzemx);
        this.zzexe = new zzbjh(zzbjfVar);
        this.zzexf = new zzbji(zzbjfVar);
        this.zzexg = zzdvj.zzan(new zzbjk(this.zzemy, this.zzexe, this.zzexf, this.zzewg.zzele.zzejp));
    }

    @Override // com.google.android.gms.internal.ads.zzbjg
    public final zzbhy zzadb() {
        return (zzbhy) zzdvo.zza(this.zzexg.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
