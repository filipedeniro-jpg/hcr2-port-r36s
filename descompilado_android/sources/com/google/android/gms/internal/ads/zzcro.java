package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcro implements zzcra<zzcrp> {
    private final Executor executor;
    private final ScheduledExecutorService zzfez;
    private final zzasx zzgge;
    private final Context zzlk;

    public zzcro(zzasx zzasxVar, Context context, ScheduledExecutorService scheduledExecutorService, Executor executor) {
        this.zzgge = zzasxVar;
        this.zzlk = context;
        this.zzfez = scheduledExecutorService;
        this.executor = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrp> zzalr() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckj)).booleanValue()) {
            final zzaxf zzaxfVar = new zzaxf();
            final zzdcp<AdvertisingIdClient.Info> zzdcpVarZzaj = this.zzgge.zzaj(this.zzlk);
            zzdcpVarZzaj.addListener(new Runnable(this, zzdcpVarZzaj, zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzcrr
                private final zzdcp zzfnv;
                private final zzaxf zzfsp;
                private final zzcro zzggh;

                {
                    this.zzggh = this;
                    this.zzfnv = zzdcpVarZzaj;
                    this.zzfsp = zzaxfVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzggh.zza(this.zzfnv, this.zzfsp);
                }
            }, this.executor);
            this.zzfez.schedule(new Runnable(zzdcpVarZzaj) { // from class: com.google.android.gms.internal.ads.zzcrq
                private final zzdcp zzfoj;

                {
                    this.zzfoj = zzdcpVarZzaj;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfoj.cancel(true);
                }
            }, ((Long) zzuo.zzoj().zzd(zzyt.zzckk)).longValue(), TimeUnit.MILLISECONDS);
            return zzaxfVar;
        }
        return zzdcf.zzi(new Exception("Did not ad Ad ID into query param."));
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zza(zzdcp zzdcpVar, zzaxf zzaxfVar) {
        String strZzbi;
        try {
            AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) zzdcpVar.get();
            if (info == null || !TextUtils.isEmpty(info.getId())) {
                strZzbi = null;
            } else {
                zzuo.zzof();
                strZzbi = zzawe.zzbi(this.zzlk);
            }
            zzaxfVar.set(new zzcrp(info, this.zzlk, strZzbi));
        } catch (InterruptedException | CancellationException | ExecutionException unused) {
            zzuo.zzof();
            zzaxfVar.set(new zzcrp(null, this.zzlk, zzawe.zzbi(this.zzlk)));
        }
    }
}
