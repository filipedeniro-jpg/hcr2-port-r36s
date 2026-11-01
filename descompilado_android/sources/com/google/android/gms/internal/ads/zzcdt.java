package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdt extends zzaoa {
    private final Executor zzfbm;
    private final zzapb zzfur;
    private final zzaoy zzfus;
    private final zzbga zzfut;
    private final Context zzlk;

    public zzcdt(Context context, Executor executor, zzapb zzapbVar, zzbga zzbgaVar, zzaoy zzaoyVar) {
        zzyt.initialize(context);
        this.zzlk = context;
        this.zzfbm = executor;
        this.zzfur = zzapbVar;
        this.zzfus = zzaoyVar;
        this.zzfut = zzbgaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final zzanx zza(zzanv zzanvVar) throws RemoteException {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zza(zzanv zzanvVar, zzaoc zzaocVar) throws RemoteException {
    }

    public final zzdcp<InputStream> zzh(zzaok zzaokVar) {
        zzaid zzaidVarZza = com.google.android.gms.ads.internal.zzp.zzkl().zza(this.zzlk, zzawv.zzwk());
        final zzcrs zzcrsVarZza = this.zzfut.zza(zzaokVar);
        zzdbq zzdbqVar = new zzdbq(zzcrsVarZza) { // from class: com.google.android.gms.internal.ads.zzcdw
            private final zzcrs zzfuu;

            {
                this.zzfuu = zzcrsVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfuu.zzacn().zzs(com.google.android.gms.ads.internal.zzp.zzjy().zzd((Bundle) obj));
            }
        };
        zzcwu zzcwuVar = zzcdv.zzfti;
        zzahv zzahvVarZza = zzaidVarZza.zza("AFMA_getAdDictionary", zzaic.zzdan, zzcdy.zzdam);
        zzahv zzahvVarZza2 = zzaidVarZza.zza("google.afma.response.normalize", zzceg.zzfvb, zzaic.zzdao);
        zzceh zzcehVar = new zzceh(this.zzlk, zzaokVar.zzdid.zzbnh, this.zzfur, zzaokVar.zzdjd);
        zzcxn zzcxnVarZzacp = zzcrsVarZza.zzacp();
        final zzcww zzcwwVarZzano = zzcxnVarZzacp.zza(zzcxk.GMS_SIGNALS, zzdcf.zzah(zzaokVar.zzdlo)).zza(zzdbqVar).zzb(zzcwuVar).zzano();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrk)).booleanValue()) {
            return zzcxnVarZzacp.zza(zzcxk.AD_REQUEST, zzcwwVarZzano).zza(zzaidVarZza.zza("google.afma.request.getAdResponse", zzaic.zzdan, zzaic.zzdan)).zzb(zzcdx.zzfti).zzano();
        }
        final zzcww zzcwwVarZzano2 = zzcxnVarZzacp.zza(zzcxk.BUILD_URL, zzcwwVarZzano).zza(zzahvVarZza).zzano();
        final zzcww zzcwwVarZzano3 = zzcxnVarZzacp.zza(zzcxk.HTTP, zzcwwVarZzano2, zzcwwVarZzano).zzb(new Callable(zzcwwVarZzano, zzcwwVarZzano2) { // from class: com.google.android.gms.internal.ads.zzcea
            private final zzdcp zzfnv;
            private final zzdcp zzfoj;

            {
                this.zzfoj = zzcwwVarZzano;
                this.zzfnv = zzcwwVarZzano2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzcek((JSONObject) this.zzfoj.get(), (zzaoq) this.zzfnv.get());
            }
        }).zzb(zzcehVar).zzano();
        return zzcxnVarZzacp.zza(zzcxk.PRE_PROCESS, zzcwwVarZzano, zzcwwVarZzano2, zzcwwVarZzano3).zzb(new Callable(zzcwwVarZzano3, zzcwwVarZzano, zzcwwVarZzano2) { // from class: com.google.android.gms.internal.ads.zzcdz
            private final zzdcp zzffe;
            private final zzdcp zzfnv;
            private final zzdcp zzfoj;

            {
                this.zzfoj = zzcwwVarZzano3;
                this.zzfnv = zzcwwVarZzano;
                this.zzffe = zzcwwVarZzano2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzceg((zzcej) this.zzfoj.get(), (JSONObject) this.zzfnv.get(), (zzaoq) this.zzffe.get());
            }
        }).zza(zzahvVarZza2).zzano();
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zza(zzaok zzaokVar, zzaoe zzaoeVar) {
        zzdcp<InputStream> zzdcpVarZzh = zzh(zzaokVar);
        zza(zzdcpVarZzh, zzaoeVar);
        zzdcpVarZzh.addListener(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcec
            private final zzcdt zzfuw;

            {
                this.zzfuw = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfuw.zzakg();
            }
        }, this.zzfbm);
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zzb(zzaok zzaokVar, zzaoe zzaoeVar) {
        zzdcp<InputStream> zzdcpVarZzano;
        zzaid zzaidVarZza = com.google.android.gms.ads.internal.zzp.zzkl().zza(this.zzlk, zzawv.zzwk());
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsb)).booleanValue()) {
            zzdcpVarZzano = zzdcf.zzi(new Exception("Signal collection disabled."));
        } else {
            zzcrs zzcrsVarZza = this.zzfut.zza(zzaokVar);
            final zzcrd<JSONObject> zzcrdVarZzaco = this.zzfut.zza(zzaokVar).zzaco();
            zzdcpVarZzano = zzcrsVarZza.zzacp().zza(zzcxk.GET_SIGNALS, zzdcf.zzah(zzaokVar.zzdlo)).zza(new zzdbq(zzcrdVarZzaco) { // from class: com.google.android.gms.internal.ads.zzceb
                private final zzcrd zzfuv;

                {
                    this.zzfuv = zzcrdVarZzaco;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    return this.zzfuv.zzs(com.google.android.gms.ads.internal.zzp.zzjy().zzd((Bundle) obj));
                }
            }).zzw(zzcxk.JS_SIGNALS).zza(zzaidVarZza.zza("google.afma.request.getSignals", zzaic.zzdan, zzaic.zzdao)).zzano();
        }
        zza(zzdcpVarZzano, zzaoeVar);
    }

    private final void zza(zzdcp<InputStream> zzdcpVar, zzaoe zzaoeVar) {
        zzdcf.zza(zzdcf.zzb(zzdcpVar, new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzcee
            private final zzcdt zzfuw;

            {
                this.zzfuw = this;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return zzdcf.zzah(zzcvu.zze((InputStream) obj));
            }
        }, zzawx.zzdvx), new zzced(this, zzaoeVar), zzawx.zzdwb);
    }

    final /* synthetic */ void zzakg() {
        zzaxb.zza(this.zzfus.zztf(), "persistFlags");
    }
}
