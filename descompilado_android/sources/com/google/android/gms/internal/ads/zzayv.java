package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzayv implements Runnable {
    private boolean zzbox = false;
    private zzayc zzdyi;

    zzayv(zzayc zzaycVar) {
        this.zzdyi = zzaycVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzbox) {
            return;
        }
        this.zzdyi.zzwz();
        zzxu();
    }

    public final void pause() {
        this.zzbox = true;
        this.zzdyi.zzwz();
    }

    public final void resume() {
        this.zzbox = false;
        zzxu();
    }

    private final void zzxu() {
        zzatv.zzdsk.removeCallbacks(this);
        zzatv.zzdsk.postDelayed(this, 250L);
    }
}
