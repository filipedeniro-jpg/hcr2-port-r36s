package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbmj extends zzbos<zzbml> implements zzbml {
    public zzbmj(Set<zzbqc<zzbml>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(final int i) {
        zza(new zzbou(i) { // from class: com.google.android.gms.internal.ads.zzbmi
            private final int zzdvo;

            {
                this.zzdvo = i;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzbml) obj).onAdFailedToLoad(this.zzdvo);
            }
        });
    }
}
