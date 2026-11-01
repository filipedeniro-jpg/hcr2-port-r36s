package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgj implements zzbmg, zzbmt, zzbnm, zzth {
    private final zzcvj zzfaq;
    private final zzcvb zzfar;
    private final zzcxw zzfas;

    @GuardedBy("this")
    private boolean zzfat;

    @GuardedBy("this")
    private boolean zzfau;

    public zzbgj(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcxw zzcxwVar) {
        this.zzfaq = zzcvjVar;
        this.zzfar = zzcvbVar;
        this.zzfas = zzcxwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final synchronized void onAdLoaded() {
        if (this.zzfat) {
            ArrayList arrayList = new ArrayList(this.zzfar.zzdcj);
            arrayList.addAll(this.zzfar.zzgis);
            this.zzfas.zza(this.zzfaq, this.zzfar, true, (List<String>) arrayList);
        } else {
            this.zzfas.zza(this.zzfaq, this.zzfar, this.zzfar.zzgiu);
            this.zzfas.zza(this.zzfaq, this.zzfar, this.zzfar.zzgis);
        }
        this.zzfat = true;
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final void onAdClicked() {
        zzcxw zzcxwVar = this.zzfas;
        zzcvj zzcvjVar = this.zzfaq;
        zzcvb zzcvbVar = this.zzfar;
        zzcxwVar.zza(zzcvjVar, zzcvbVar, zzcvbVar.zzdci);
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final synchronized void onAdImpression() {
        if (!this.zzfau) {
            this.zzfas.zza(this.zzfaq, this.zzfar, this.zzfar.zzdcj);
            this.zzfau = true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void zzb(zzape zzapeVar, String str, String str2) {
        zzcxw zzcxwVar = this.zzfas;
        zzcvj zzcvjVar = this.zzfaq;
        zzcvb zzcvbVar = this.zzfar;
        zzcxwVar.zza(zzcvjVar, zzcvbVar, zzcvbVar.zzdku, zzapeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
        zzcxw zzcxwVar = this.zzfas;
        zzcvj zzcvjVar = this.zzfaq;
        zzcvb zzcvbVar = this.zzfar;
        zzcxwVar.zza(zzcvjVar, zzcvbVar, zzcvbVar.zzdkt);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
        zzcxw zzcxwVar = this.zzfas;
        zzcvj zzcvjVar = this.zzfaq;
        zzcvb zzcvbVar = this.zzfar;
        zzcxwVar.zza(zzcvjVar, zzcvbVar, zzcvbVar.zzgit);
    }
}
