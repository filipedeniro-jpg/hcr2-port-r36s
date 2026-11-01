package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbem extends zzcrs {
    private final /* synthetic */ zzbeg zzele;
    private zzdvv<String> zzeoj;
    private zzdvv<zzcak> zzeoz;
    private zzdvv<Map<zzcxk, zzcak>> zzepb;
    private zzdvv<Set<zzbqc<zzcxt>>> zzepd;
    private zzdvv<Set<zzbqc<zzcxt>>> zzepk;
    private zzdvv zzepl;
    private zzdvv<zzcxn> zzepm;
    private zzdvv<ApplicationInfo> zzepn;
    private final zzcsz zzetx;
    private zzdvv<zzcro> zzety;
    private zzdvv<String> zzetz;
    private zzdvv<zzcry> zzeua;
    private zzdvv<zzcsc> zzeub;
    private zzdvv<zzcsl> zzeuc;
    private zzdvv<Boolean> zzeud;
    private zzdvv<zzcsq> zzeue;
    private zzdvv<zzcsu> zzeuf;
    private zzdvv<zzctj> zzeug;
    private zzdvv<zzcak> zzeuh;
    private zzdvv<zzcak> zzeui;
    private zzdvv<zzcak> zzeuj;

    private zzbem(zzbeg zzbegVar, zzcsz zzcszVar) {
        this.zzele = zzbegVar;
        this.zzetx = zzcszVar;
        this.zzety = new zzcrt(zzbfy.zzaeb(), this.zzele.zzejv, this.zzele.zzejr, zzcwr.zzanh());
        this.zzetz = new zzcsy(zzcszVar);
        this.zzeua = new zzcsa(zzbec.zzabp(), this.zzele.zzejv, this.zzetz, zzcwr.zzanh());
        this.zzeub = new zzcse(zzbfu.zzadv(), zzcwr.zzanh(), this.zzele.zzejv);
        this.zzeuc = new zzcsn(zzbft.zzadt(), zzcwr.zzanh(), this.zzetz);
        this.zzeud = new zzcta(zzcszVar);
        this.zzepn = new zzctb(zzcszVar);
        this.zzeue = new zzcss(zzbfw.zzadz(), this.zzele.zzejr, this.zzeud, this.zzepn);
        this.zzeuf = new zzcsw(zzbfv.zzadx(), this.zzele.zzejr, this.zzele.zzejv);
        this.zzeug = new zzctl(zzcwr.zzanh());
        this.zzeoj = new zzctd(zzcszVar);
        this.zzeoz = zzdvj.zzan(zzcae.zzajo());
        this.zzeuh = zzdvj.zzan(zzcab.zzajm());
        this.zzeui = zzdvj.zzan(zzcad.zzajn());
        this.zzeuj = zzdvj.zzan(zzcag.zzajp());
        this.zzepb = ((zzdvm) ((zzdvm) ((zzdvm) ((zzdvm) zzdvk.zzhp(4).zza(zzcxk.GMS_SIGNALS, this.zzeoz)).zza(zzcxk.BUILD_URL, this.zzeuh)).zza(zzcxk.HTTP, this.zzeui)).zza(zzcxk.PRE_PROCESS, this.zzeuj)).zzbda();
        this.zzepd = zzdvj.zzan(new zzcaf(this.zzeoj, this.zzele.zzejv, zzcwr.zzanh(), this.zzepb));
        this.zzepk = zzdvr.zzar(0, 1).zzaq(this.zzepd).zzbdb();
        this.zzepl = zzcxv.zzam(this.zzepk);
        this.zzepm = zzdvj.zzan(zzcxs.zzs(zzcwr.zzanh(), this.zzele.zzejr, this.zzepl));
    }

    private final zzcsp zzacl() {
        return new zzcsp(zzbfr.zzadr(), zzcwr.zzani(), (List) zzdvo.zza(this.zzetx.zzaml(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    private final zzcru zzacm() {
        return new zzcru(zzbfy.zzaec(), zzcwr.zzani(), (String) zzdvo.zza(this.zzetx.zzami(), "Cannot return null from a non-@Nullable @Provides method"), this.zzetx.zzamj());
    }

    @Override // com.google.android.gms.internal.ads.zzcrs
    public final zzcrd<JSONObject> zzacn() {
        return new zzcrd<>(zzcwr.zzani(), zzdan.zza((zzcra) zzdvo.zza(new zzcpw(new zzcsl(zzbft.zzadu(), zzcwr.zzani(), zzcsy.zzb(this.zzetx)), 0L, (ScheduledExecutorService) this.zzele.zzejr.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzcra) zzdvo.zza(new zzcpw(new zzcsq(zzbfw.zzaea(), (ScheduledExecutorService) this.zzele.zzejr.get(), this.zzetx.zzamk(), zzctb.zzc(this.zzetx)), ((Long) zzuo.zzoj().zzd(zzyt.zzcoj)).longValue(), (ScheduledExecutorService) this.zzele.zzejr.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzcra) zzdvo.zza(new zzcpw(new zzcsu(zzbfv.zzady(), (ScheduledExecutorService) this.zzele.zzejr.get(), zzbdu.zza(this.zzele.zzejo)), ((Long) zzuo.zzoj().zzd(zzyt.zzcoy)).longValue(), (ScheduledExecutorService) this.zzele.zzejr.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzcra) zzdvo.zza(new zzcpw(new zzcro(zzbfy.zzaec(), zzbdu.zza(this.zzele.zzejo), (ScheduledExecutorService) this.zzele.zzejr.get(), zzcwr.zzani()), 0L, (ScheduledExecutorService) this.zzele.zzejr.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzcra) zzdvo.zza(new zzcpw(new zzctj(zzcwr.zzani()), 0L, (ScheduledExecutorService) this.zzele.zzejr.get()), "Cannot return null from a non-@Nullable @Provides method"), zzcte.zzamo(), new zzcry(null, zzbdu.zza(this.zzele.zzejo), zzcsy.zzb(this.zzetx), zzcwr.zzani()), new zzcsc(zzbfu.zzadw(), zzcwr.zzani(), zzbdu.zza(this.zzele.zzejo)), zzacl(), zzacm(), (zzcra) this.zzele.zzekj.get()));
    }

    @Override // com.google.android.gms.internal.ads.zzcrs
    public final zzcrd<JSONObject> zzaco() {
        return zzcth.zza(this.zzele.zzekj.get(), zzacm(), zzacl(), zzdvj.zzao(this.zzety), zzdvj.zzao(this.zzeua), zzdvj.zzao(this.zzeub), zzdvj.zzao(this.zzeuc), zzdvj.zzao(this.zzeue), zzdvj.zzao(this.zzeuf), zzdvj.zzao(this.zzeug), zzcwr.zzani());
    }

    @Override // com.google.android.gms.internal.ads.zzcrs
    public final zzcxn zzacp() {
        return this.zzepm.get();
    }
}
