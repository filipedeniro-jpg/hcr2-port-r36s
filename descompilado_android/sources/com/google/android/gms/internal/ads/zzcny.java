package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcny implements zzcra<zzcnz> {
    private final Executor executor;
    private final ScheduledExecutorService zzfez;
    private final Context zzlk;

    public zzcny(Context context, ScheduledExecutorService scheduledExecutorService, Executor executor) {
        this.zzlk = context;
        this.zzfez = scheduledExecutorService;
        this.executor = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcnz> zzalr() {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsy)).booleanValue()) {
            return zzdcf.zzah(null);
        }
        final zzaxf zzaxfVar = new zzaxf();
        try {
            new zzczo(zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzcob
                private final zzaxf zzbrp;

                {
                    this.zzbrp = zzaxfVar;
                }

                @Override // com.google.android.gms.internal.ads.zzczo
                public final void zzbc(boolean z) {
                    this.zzbrp.set(false);
                }
            }.zzbc(false);
        } catch (Throwable unused) {
            zzatm.zzes("ArCoreApk is not ready.");
            zzaxfVar.set(false);
        }
        return zzdcf.zzb(zzdcf.zzb(zzdcf.zza(zzaxfVar, 200L, TimeUnit.MILLISECONDS, this.zzfez), new zzczs(this) { // from class: com.google.android.gms.internal.ads.zzcoa
            private final zzcny zzgeg;

            {
                this.zzgeg = this;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                return new zzcnz(false, false, ((Boolean) obj).booleanValue());
            }
        }, this.executor), Throwable.class, zzcod.zzbks, this.executor);
    }
}
