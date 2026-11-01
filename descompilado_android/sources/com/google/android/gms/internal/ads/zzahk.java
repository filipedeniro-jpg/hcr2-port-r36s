package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzahk extends zzaxi<zzahr> {
    private final Object lock = new Object();
    private final zzaho zzczx;
    private boolean zzczy;

    public zzahk(zzaho zzahoVar) {
        this.zzczx = zzahoVar;
    }

    public final void release() {
        synchronized (this.lock) {
            if (this.zzczy) {
                return;
            }
            this.zzczy = true;
            zza(new zzahj(this), new zzaxg());
            zza(new zzahm(this), new zzahl(this));
        }
    }
}
