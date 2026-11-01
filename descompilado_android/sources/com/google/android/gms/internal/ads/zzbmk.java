package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmk extends zzbos<zzbmp> implements zzbmp {
    public zzbmk(Set<zzbqc<zzbmp>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzbmp
    public final void zzcm(final int i) {
        zza(new zzbou(i) { // from class: com.google.android.gms.internal.ads.zzbmn
            private final int zzdvo;

            {
                this.zzdvo = i;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbmp) obj).zzcm(this.zzdvo);
            }
        });
    }
}
