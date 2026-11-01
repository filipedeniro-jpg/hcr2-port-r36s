package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhn implements Runnable {
    private final /* synthetic */ zzhh zzahe;
    private final /* synthetic */ int zzahk;

    zzhn(zzhh zzhhVar, int i) {
        this.zzahe = zzhhVar;
        this.zzahk = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahe.zzaha.zzr(this.zzahk);
    }
}
