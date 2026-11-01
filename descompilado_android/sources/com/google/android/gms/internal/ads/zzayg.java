package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzayg implements Runnable {
    private final /* synthetic */ zzayc zzdyg;

    zzayg(zzayc zzaycVar) {
        this.zzdyg = zzaycVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdyg.zzd("surfaceDestroyed", new String[0]);
    }
}
