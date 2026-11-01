package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzciq<AdT> implements zzdbq<zzcvj, AdT> {
    private final Executor executor;
    private final zzcxw zzfas;
    private final ScheduledExecutorService zzfck;
    private final zzcxn zzffo;
    private final zzbmj zzfhg;
    private final zzbjv<AdT> zzfyx;
    private final zzcil zzfyy;

    public zzciq(zzcxn zzcxnVar, zzcil zzcilVar, zzbmj zzbmjVar, zzcxw zzcxwVar, zzbjv<AdT> zzbjvVar, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.zzffo = zzcxnVar;
        this.zzfyy = zzcilVar;
        this.zzfhg = zzbmjVar;
        this.zzfas = zzcxwVar;
        this.zzfyx = zzbjvVar;
        this.executor = executor;
        this.zzfck = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final /* synthetic */ zzdcp zzf(zzcvj zzcvjVar) throws Exception {
        final zzcvj zzcvjVar2 = zzcvjVar;
        zzcww zzcwwVarZzano = this.zzffo.zzu(zzcxk.RENDER_CONFIG_INIT).zzb(zzdcf.zzi(new zzcin("No ad configs", 3))).zzano();
        this.zzfhg.zza(new zzbgk(zzcvjVar2, this.zzfas), this.executor);
        int i = 0;
        for (final zzcvb zzcvbVar : zzcvjVar2.zzgjp.zzgjl) {
            for (String str : zzcvbVar.zzgiq) {
                final zzcfk<AdT> zzcfkVarZze = this.zzfyx.zze(zzcvbVar.zzfif, str);
                if (zzcfkVarZze != null && zzcfkVarZze.zza(zzcvjVar2, zzcvbVar)) {
                    zzcxf<I> zzcxfVarZza = this.zzffo.zza(zzcxk.RENDER_CONFIG_WATERFALL, zzcwwVarZzano);
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26);
                    sb.append("render-config-");
                    sb.append(i);
                    sb.append("-");
                    sb.append(str);
                    zzcwwVarZzano = zzcxfVarZza.zzgi(sb.toString()).zza(Throwable.class, new zzdbq(this, zzcvbVar, zzcfkVarZze, zzcvjVar2) { // from class: com.google.android.gms.internal.ads.zzcip
                        private final zzcvb zzfxy;
                        private final zzcvj zzfya;
                        private final zzciq zzfyv;
                        private final zzcfk zzfyw;

                        {
                            this.zzfyv = this;
                            this.zzfxy = zzcvbVar;
                            this.zzfyw = zzcfkVarZze;
                            this.zzfya = zzcvjVar2;
                        }

                        @Override // com.google.android.gms.internal.ads.zzdbq
                        public final zzdcp zzf(Object obj) {
                            return this.zzfyv.zza(this.zzfxy, this.zzfyw, this.zzfya, (Throwable) obj);
                        }
                    }).zzano();
                    break;
                }
            }
            i++;
        }
        return zzcwwVarZzano;
    }

    final /* synthetic */ zzdcp zza(zzcvb zzcvbVar, zzcfk zzcfkVar, zzcvj zzcvjVar, Throwable th) throws Exception {
        return this.zzfyy.zza(zzcvbVar, zzdcf.zza(zzcfkVar.zzb(zzcvjVar, zzcvbVar), zzcvbVar.zzgjf, TimeUnit.MILLISECONDS, this.zzfck));
    }
}
