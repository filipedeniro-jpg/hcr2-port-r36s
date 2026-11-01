package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhm implements Runnable {
    private final /* synthetic */ zzhh zzahe;
    private final /* synthetic */ zzgq zzahj;

    zzhm(zzhh zzhhVar, zzgq zzgqVar) {
        this.zzahe = zzhhVar;
        this.zzahj = zzgqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahe.zzaha.zzc(this.zzahj);
    }
}
