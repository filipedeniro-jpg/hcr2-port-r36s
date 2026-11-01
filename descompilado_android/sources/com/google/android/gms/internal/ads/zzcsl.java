package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsl implements zzcra<zzcsi> {
    private String packageName;
    private zzdcs zzfnq;
    private zzast zzggv;

    public zzcsl(zzast zzastVar, zzdcs zzdcsVar, String str) {
        this.zzggv = zzastVar;
        this.zzfnq = zzdcsVar;
        this.packageName = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcsi> zzalr() {
        new zzaxf();
        final zzdcp<String> zzdcpVarZzah = zzdcf.zzah(null);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrv)).booleanValue()) {
            zzdcpVarZzah = this.zzggv.zzdw(this.packageName);
        }
        final zzdcp<String> zzdcpVarZzdx = this.zzggv.zzdx(this.packageName);
        return zzdcf.zza(zzdcpVarZzah, zzdcpVarZzdx).zza(new Callable(zzdcpVarZzah, zzdcpVarZzdx) { // from class: com.google.android.gms.internal.ads.zzcsk
            private final zzdcp zzfnv;
            private final zzdcp zzfoj;

            {
                this.zzfoj = zzdcpVarZzah;
                this.zzfnv = zzdcpVarZzdx;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzcsi((String) this.zzfoj.get(), (String) this.zzfnv.get());
            }
        }, zzawx.zzdvx);
    }
}
