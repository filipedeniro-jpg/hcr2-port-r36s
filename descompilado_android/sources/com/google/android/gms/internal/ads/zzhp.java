package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhp extends Thread {
    private final /* synthetic */ AudioTrack zzahm;
    private final /* synthetic */ zzhq zzahn;

    zzhp(zzhq zzhqVar, AudioTrack audioTrack) {
        this.zzahn = zzhqVar;
        this.zzahm = audioTrack;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            this.zzahm.flush();
            this.zzahm.release();
        } finally {
            this.zzahn.zzahv.open();
        }
    }
}
