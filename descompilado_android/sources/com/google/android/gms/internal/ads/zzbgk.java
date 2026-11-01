package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgk implements zzbml {
    private final zzcvj zzfaq;
    private final zzcxw zzfas;
    private final zzcvd zzfav;

    public zzbgk(zzcvj zzcvjVar, zzcxw zzcxwVar) {
        this.zzfaq = zzcvjVar;
        this.zzfas = zzcxwVar;
        this.zzfav = zzcvjVar.zzgjp.zzgjm;
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(int i) {
        this.zzfas.zza(this.zzfaq, null, this.zzfav.zzdcl);
    }
}
