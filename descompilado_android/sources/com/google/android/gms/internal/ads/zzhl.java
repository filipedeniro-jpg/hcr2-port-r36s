package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhl implements Runnable {
    private final /* synthetic */ zzhh zzahe;
    private final /* synthetic */ int zzahg;
    private final /* synthetic */ long zzahh;
    private final /* synthetic */ long zzahi;

    zzhl(zzhh zzhhVar, int i, long j, long j2) {
        this.zzahe = zzhhVar;
        this.zzahg = i;
        this.zzahh = j;
        this.zzahi = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahe.zzaha.zzb(this.zzahg, this.zzahh, this.zzahi);
    }
}
