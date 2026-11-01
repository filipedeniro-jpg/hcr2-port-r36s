package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzckj implements com.google.android.gms.ads.internal.zzg {
    private final /* synthetic */ zzcvj zzfzj;
    private final /* synthetic */ zzcvb zzfzk;
    private final /* synthetic */ zzaxf zzgai;
    private final /* synthetic */ zzckp zzgaj;
    private final /* synthetic */ zzckh zzgak;

    zzckj(zzckh zzckhVar, zzaxf zzaxfVar, zzcvj zzcvjVar, zzcvb zzcvbVar, zzckp zzckpVar) {
        this.zzgak = zzckhVar;
        this.zzgai = zzaxfVar;
        this.zzfzj = zzcvjVar;
        this.zzfzk = zzcvbVar;
        this.zzgaj = zzckpVar;
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjk() {
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjl() {
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzg(View view) {
        this.zzgai.set(this.zzgak.zzgaf.zza(this.zzfzj, this.zzfzk, view, this.zzgaj));
    }
}
