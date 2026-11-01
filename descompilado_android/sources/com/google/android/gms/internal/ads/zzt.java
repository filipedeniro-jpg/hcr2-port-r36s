package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzt implements Runnable {
    private final /* synthetic */ String zzas;
    private final /* synthetic */ long zzat;
    private final /* synthetic */ zzq zzau;

    zzt(zzq zzqVar, String str, long j) {
        this.zzau = zzqVar;
        this.zzas = str;
        this.zzat = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzau.zzae.zza(this.zzas, this.zzat);
        this.zzau.zzae.zzc(this.zzau.toString());
    }
}
