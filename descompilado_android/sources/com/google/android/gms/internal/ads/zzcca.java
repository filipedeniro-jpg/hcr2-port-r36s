package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzcca implements zzdcg<String> {
    final /* synthetic */ zzcbp zzfsv;

    zzcca(zzcbp zzcbpVar) {
        this.zzfsv = zzcbpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        synchronized (this) {
            zzcbp.zza(this.zzfsv, true);
            this.zzfsv.zza("com.google.android.gms.ads.MobileAds", false, "Internal Error.", (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfsv.zzfsg));
            this.zzfsv.zzfsh.setException(new Exception());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(@Nullable String str) {
        final String str2 = str;
        synchronized (this) {
            zzcbp.zza(this.zzfsv, true);
            this.zzfsv.zza("com.google.android.gms.ads.MobileAds", true, "", (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfsv.zzfsg));
            this.zzfsv.executor.execute(new Runnable(this, str2) { // from class: com.google.android.gms.internal.ads.zzcbz
                private final String zzcyl;
                private final zzcca zzfsu;

                {
                    this.zzfsu = this;
                    this.zzcyl = str2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzcca zzccaVar = this.zzfsu;
                    zzccaVar.zzfsv.zzga(this.zzcyl);
                }
            });
        }
    }
}
