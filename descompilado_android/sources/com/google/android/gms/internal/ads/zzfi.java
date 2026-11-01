package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfi implements Runnable {
    private final /* synthetic */ zzfj zzaai;

    zzfi(zzfj zzfjVar) {
        this.zzaai = zzfjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaai.zzcx();
    }
}
