package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhk implements Runnable {
    private final /* synthetic */ zzhh zzahe;
    private final /* synthetic */ zzij zzahf;

    zzhk(zzhh zzhhVar, zzij zzijVar) {
        this.zzahe = zzhhVar;
        this.zzahf = zzijVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahe.zzaha.zzc(this.zzahf);
    }
}
