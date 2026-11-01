package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzboh extends zzbos<zzboi> implements zzboi {
    public zzboh(Set<zzbqc<zzboi>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(final zzaok zzaokVar) {
        zza(new zzbou(zzaokVar) { // from class: com.google.android.gms.internal.ads.zzbog
            private final zzaok zzfgt;

            {
                this.zzfgt = zzaokVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzboi) obj).zzb(this.zzfgt);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(final zzcvj zzcvjVar) {
        zza(new zzbou(zzcvjVar) { // from class: com.google.android.gms.internal.ads.zzboj
            private final zzcvj zzfgu;

            {
                this.zzfgu = zzcvjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzboi) obj).zza(this.zzfgu);
            }
        });
    }
}
