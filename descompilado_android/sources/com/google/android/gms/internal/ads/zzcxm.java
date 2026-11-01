package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcxm extends zzbos<zzcxt> implements zzcxl<zzcxk> {
    zzcxm(Set<zzbqc<zzcxt>> set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzcxl
    public final void zza(final zzcww<zzcxk, ?> zzcwwVar) {
        zza(new zzbou(zzcwwVar) { // from class: com.google.android.gms.internal.ads.zzcxp
            private final zzcww zzgmh;

            {
                this.zzgmh = zzcwwVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                zzcww zzcwwVar2 = this.zzgmh;
                ((zzcxt) obj).zza((zzcxk) zzcwwVar2.zzanl(), zzcwwVar2.zzanm());
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcxl
    public final void zzb(final zzcww<zzcxk, ?> zzcwwVar) {
        zza(new zzbou(zzcwwVar) { // from class: com.google.android.gms.internal.ads.zzcxo
            private final zzcww zzgmh;

            {
                this.zzgmh = zzcwwVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                zzcww zzcwwVar2 = this.zzgmh;
                ((zzcxt) obj).zzb((zzcxk) zzcwwVar2.zzanl(), zzcwwVar2.zzanm());
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcxl
    public final void zza(final zzcww<zzcxk, ?> zzcwwVar, final Throwable th) {
        zza(new zzbou(zzcwwVar, th) { // from class: com.google.android.gms.internal.ads.zzcxr
            private final zzcww zzgmh;
            private final Throwable zzgmi;

            {
                this.zzgmh = zzcwwVar;
                this.zzgmi = th;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                zzcww zzcwwVar2 = this.zzgmh;
                ((zzcxt) obj).zza((zzcxk) zzcwwVar2.zzanl(), zzcwwVar2.zzanm(), this.zzgmi);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzcxl
    public final void zzc(final zzcww<zzcxk, ?> zzcwwVar) {
        zza(new zzbou(zzcwwVar) { // from class: com.google.android.gms.internal.ads.zzcxq
            private final zzcww zzgmh;

            {
                this.zzgmh = zzcwwVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                zzcww zzcwwVar2 = this.zzgmh;
                ((zzcxt) obj).zzc((zzcxk) zzcwwVar2.zzanl(), zzcwwVar2.zzanm());
            }
        });
    }
}
