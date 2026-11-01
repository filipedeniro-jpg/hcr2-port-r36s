package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaim {
    private zzagt zzdba;
    private zzdcp<zzahr> zzdbb;

    zzaim(zzagt zzagtVar) {
        this.zzdba = zzagtVar;
    }

    private final void zzri() {
        if (this.zzdbb == null) {
            final zzaxf zzaxfVar = new zzaxf();
            this.zzdbb = zzaxfVar;
            this.zzdba.zzb((zzdf) null).zza(new zzaxj(zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzail
                private final zzaxf zzbrp;

                {
                    this.zzbrp = zzaxfVar;
                }

                @Override // com.google.android.gms.internal.ads.zzaxj
                public final void zzh(Object obj) {
                    this.zzbrp.set((zzahr) obj);
                }
            }, new zzaxh(zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzaio
                private final zzaxf zzbrp;

                {
                    this.zzbrp = zzaxfVar;
                }

                @Override // com.google.android.gms.internal.ads.zzaxh
                public final void run() {
                    this.zzbrp.setException(new zzahw("Cannot get Javascript Engine"));
                }
            });
        }
    }

    public final <I, O> zzaip<I, O> zzb(String str, zzaia<I> zzaiaVar, zzahx<O> zzahxVar) {
        zzri();
        return new zzaip<>(this.zzdbb, str, zzaiaVar, zzahxVar);
    }

    public final void zzc(final String str, final zzadx<? super zzahr> zzadxVar) {
        zzri();
        this.zzdbb = zzdcf.zzb(this.zzdbb, new zzdbq(str, zzadxVar) { // from class: com.google.android.gms.internal.ads.zzain
            private final String zzcys;
            private final zzadx zzcyu;

            {
                this.zzcys = str;
                this.zzcyu = zzadxVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                zzahr zzahrVar = (zzahr) obj;
                zzahrVar.zza(this.zzcys, this.zzcyu);
                return zzdcf.zzah(zzahrVar);
            }
        }, zzawx.zzdwb);
    }

    public final void zzd(final String str, final zzadx<? super zzahr> zzadxVar) {
        this.zzdbb = zzdcf.zzb(this.zzdbb, new zzczs(str, zzadxVar) { // from class: com.google.android.gms.internal.ads.zzaiq
            private final String zzcys;
            private final zzadx zzcyu;

            {
                this.zzcys = str;
                this.zzcyu = zzadxVar;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                zzahr zzahrVar = (zzahr) obj;
                zzahrVar.zzb(this.zzcys, this.zzcyu);
                return zzahrVar;
            }
        }, zzawx.zzdwb);
    }
}
