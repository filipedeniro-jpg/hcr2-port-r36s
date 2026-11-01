package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccz extends zzccx {
    private static final Pattern zzfuc = Pattern.compile("Received error HTTP response code: (.*)");
    private final ScheduledExecutorService zzfez;
    private final zzcvk zzffn;
    private final zzdcs zzfnq;
    private final zzccd zzfua;
    private final zzcev zzfub;

    zzccz(zzboh zzbohVar, zzcvk zzcvkVar, zzccd zzccdVar, zzdcs zzdcsVar, ScheduledExecutorService scheduledExecutorService, zzcev zzcevVar) {
        super(zzbohVar);
        this.zzffn = zzcvkVar;
        this.zzfua = zzccdVar;
        this.zzfnq = zzdcsVar;
        this.zzfez = scheduledExecutorService;
        this.zzfub = zzcevVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccx
    public final zzdcp<zzcvj> zze(zzaok zzaokVar) throws zzcce {
        zzdcp<zzcvj> zzdcpVarZzb = zzdcf.zzb(this.zzfua.zzc(zzaokVar), new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzcdc
            private final zzccz zzfue;

            {
                this.zzfue = this;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfue.zzd((InputStream) obj);
            }
        }, this.zzfnq);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrh)).booleanValue()) {
            zzdcpVarZzb = zzdcf.zzb(zzdcf.zza(zzdcpVarZzb, ((Integer) zzuo.zzoj().zzd(zzyt.zzcri)).intValue(), TimeUnit.SECONDS, this.zzfez), TimeoutException.class, zzcdb.zzbks, zzawx.zzdwb);
        }
        zzdcf.zza(zzdcpVarZzb, new zzcde(this), zzawx.zzdwb);
        return zzdcpVarZzb;
    }

    final /* synthetic */ zzdcp zzd(InputStream inputStream) throws Exception {
        return zzdcf.zzah(new zzcvj(new zzcve(this.zzffn), zzcvh.zza(new InputStreamReader(inputStream))));
    }
}
