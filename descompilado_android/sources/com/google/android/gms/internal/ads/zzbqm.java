package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqm extends zzbos<zzaeh> implements zzaeh {
    public zzbqm(Set<zzbqc<zzaeh>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    public final synchronized void zzqv() {
        zza(zzbql.zzfgm);
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    public final void zza(final zzaqd zzaqdVar) {
        zza(new zzbou(zzaqdVar) { // from class: com.google.android.gms.internal.ads.zzbqo
            private final zzaqd zzfhq;

            {
                this.zzfhq = zzaqdVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzaeh) obj).zza(this.zzfhq);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzaeh
    public final void zzqw() {
        zza(zzbqn.zzfgm);
    }
}
