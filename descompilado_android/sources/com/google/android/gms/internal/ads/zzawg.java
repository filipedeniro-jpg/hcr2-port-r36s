package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzawg extends Thread {
    private final /* synthetic */ String zzdtz;

    zzawg(zzawh zzawhVar, String str) {
        this.zzdtz = str;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        new zzaws().zzei(this.zzdtz);
    }
}
