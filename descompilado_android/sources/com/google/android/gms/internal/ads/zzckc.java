package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckc implements zzcfk<zzbhy> {
    private final zzcxn zzffo;
    private final zzbit zzfwx;
    private final zzdcs zzfzv;
    private final Context zzgaa;

    @Nullable
    private final zzzn zzgab;

    public zzckc(Context context, zzbit zzbitVar, zzcxn zzcxnVar, zzdcs zzdcsVar, @Nullable zzzn zzznVar) {
        this.zzgaa = context;
        this.zzfwx = zzbitVar;
        this.zzffo = zzcxnVar;
        this.zzfzv = zzdcsVar;
        this.zzgab = zzznVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (this.zzgab == null || zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbhy> zzb(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        zzbhx zzbhxVarZza = this.zzfwx.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzckd(this, new View(this.zzgaa), null, zzckb.zzfzz, zzcvbVar.zzgiy.get(0)));
        final zzzk zzzkVar = new zzzk(zzbhxVarZza.zzacx(), zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp);
        return this.zzffo.zzu(zzcxk.CUSTOM_RENDER_SYN).zza(new zzcwx(this, zzzkVar) { // from class: com.google.android.gms.internal.ads.zzcke
            private final zzckc zzgac;
            private final zzzk zzgad;

            {
                this.zzgac = this;
                this.zzgad = zzzkVar;
            }

            @Override // com.google.android.gms.internal.ads.zzcwx
            public final void run() throws Exception {
                this.zzgac.zza(this.zzgad);
            }
        }, this.zzfzv).zzw(zzcxk.CUSTOM_RENDER_ACK).zzb(zzdcf.zzah(zzbhxVarZza.zzacv())).zzano();
    }

    final /* synthetic */ void zza(zzzk zzzkVar) throws Exception {
        this.zzgab.zza(zzzkVar);
    }
}
