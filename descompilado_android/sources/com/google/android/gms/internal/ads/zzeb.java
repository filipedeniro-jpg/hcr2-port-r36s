package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzeb implements Runnable {
    private final /* synthetic */ zzdx zzyj;

    zzeb(zzdx zzdxVar) {
        this.zzyj = zzdxVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzyt.initialize(this.zzyj.zzlk);
    }
}
