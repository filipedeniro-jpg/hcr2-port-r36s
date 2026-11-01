package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckh<AdT> implements zzcfk<AdT> {
    private final zzcxn zzffo;
    private final zzdcs zzfzv;

    @Nullable
    private final zzzn zzgab;
    private final zzckm<AdT> zzgaf;

    public zzckh(zzcxn zzcxnVar, zzdcs zzdcsVar, @Nullable zzzn zzznVar, zzckm<AdT> zzckmVar) {
        this.zzffo = zzcxnVar;
        this.zzfzv = zzdcsVar;
        this.zzgab = zzznVar;
        this.zzgaf = zzckmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (this.zzgab == null || zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzdhp == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<AdT> zzb(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        zzaxf zzaxfVar = new zzaxf();
        zzckp zzckpVar = new zzckp();
        zzckpVar.zza(new zzckj(this, zzaxfVar, zzcvjVar, zzcvbVar, zzckpVar));
        final zzzk zzzkVar = new zzzk(zzckpVar, zzcvbVar.zzgiw.zzdhn, zzcvbVar.zzgiw.zzdhp);
        return this.zzffo.zzu(zzcxk.CUSTOM_RENDER_SYN).zza(new zzcwx(this, zzzkVar) { // from class: com.google.android.gms.internal.ads.zzckk
            private final zzzk zzgad;
            private final zzckh zzgal;

            {
                this.zzgal = this;
                this.zzgad = zzzkVar;
            }

            @Override // com.google.android.gms.internal.ads.zzcwx
            public final void run() throws Exception {
                this.zzgal.zzb(this.zzgad);
            }
        }, this.zzfzv).zzw(zzcxk.CUSTOM_RENDER_ACK).zzb(zzaxfVar).zzano();
    }

    final /* synthetic */ void zzb(zzzk zzzkVar) throws Exception {
        this.zzgab.zza(zzzkVar);
    }
}
