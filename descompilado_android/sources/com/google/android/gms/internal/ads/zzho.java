package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzho implements Runnable {
    private final /* synthetic */ zzhh zzahe;
    private final /* synthetic */ zzij zzahl;

    zzho(zzhh zzhhVar, zzij zzijVar) {
        this.zzahe = zzhhVar;
        this.zzahl = zzijVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzahl.zzfu();
        this.zzahe.zzaha.zzd(this.zzahl);
    }
}
