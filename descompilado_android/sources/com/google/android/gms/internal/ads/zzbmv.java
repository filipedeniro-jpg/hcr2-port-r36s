package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmv extends zzbos<zzbms> {
    public zzbmv(Set<zzbqc<zzbms>> set) {
        super(set);
    }

    public final void zzbt(final Context context) {
        zza(new zzbou(context) { // from class: com.google.android.gms.internal.ads.zzbmu
            private final Context zzdpm;

            {
                this.zzdpm = context;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbms) obj).zzbt(this.zzdpm);
            }
        });
    }

    public final void zzbu(final Context context) {
        zza(new zzbou(context) { // from class: com.google.android.gms.internal.ads.zzbmx
            private final Context zzdpm;

            {
                this.zzdpm = context;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbms) obj).zzbu(this.zzdpm);
            }
        });
    }

    public final void zzbv(final Context context) {
        zza(new zzbou(context) { // from class: com.google.android.gms.internal.ads.zzbmw
            private final Context zzdpm;

            {
                this.zzdpm = context;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbms) obj).zzbv(this.zzdpm);
            }
        });
    }

    public final void zza(zzbqs zzbqsVar, Executor executor) {
        zza(zzbqc.zzb(new zzbmz(this, zzbqsVar), executor));
    }
}
