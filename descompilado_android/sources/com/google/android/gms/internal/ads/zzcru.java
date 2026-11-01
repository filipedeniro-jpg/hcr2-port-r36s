package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcru implements zzcra<zzcrv> {
    private final Executor executor;

    @Nullable
    private final PackageInfo zzdij;
    private final zzasx zzgge;
    private final String zzggi;

    public zzcru(zzasx zzasxVar, Executor executor, String str, @Nullable PackageInfo packageInfo) {
        this.zzgge = zzasxVar;
        this.executor = executor;
        this.zzggi = str;
        this.zzdij = packageInfo;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrv> zzalr() {
        return zzdcf.zzb(zzdcf.zzb(this.zzgge.zza(this.zzggi, this.zzdij), zzcrx.zzdoi, this.executor), Throwable.class, new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzcrw
            private final zzcru zzggk;

            {
                this.zzggk = this;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzggk.zze((Throwable) obj);
            }
        }, this.executor);
    }

    final /* synthetic */ zzdcp zze(Throwable th) throws Exception {
        return zzdcf.zzah(new zzcrv(this.zzggi));
    }
}
