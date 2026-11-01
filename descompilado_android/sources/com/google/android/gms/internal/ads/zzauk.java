package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzauk {
    private boolean zzdje = false;
    private float zzdiy = 1.0f;

    public final synchronized void setAppVolume(float f) {
        this.zzdiy = f;
    }

    public final synchronized float zzoo() {
        if (!zzvr()) {
            return 1.0f;
        }
        return this.zzdiy;
    }

    public final synchronized void setAppMuted(boolean z) {
        this.zzdje = z;
    }

    public final synchronized boolean zzop() {
        return this.zzdje;
    }

    private final synchronized boolean zzvr() {
        return this.zzdiy >= 0.0f;
    }

    public static float zzbd(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return 0.0f;
        }
        return streamVolume / streamMaxVolume;
    }
}
