package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzati {
    private volatile Thread thread;
    private final Runnable zzdrh = new zzatl(this);
    private boolean zzdri = false;

    public abstract void zzst();

    public final zzdcp<?> zzup() {
        return zzawx.zzdvy.zzg(this.zzdrh);
    }
}
