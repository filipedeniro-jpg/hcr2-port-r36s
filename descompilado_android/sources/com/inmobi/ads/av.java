package com.inmobi.ads;

import android.media.MediaPlayer;

/* JADX INFO: compiled from: NativeMediaPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class av extends MediaPlayer {
    private static av e;
    int a = 0;
    int b = 0;
    private av c;
    private static final Object d = new Object();
    private static int f = 0;

    public static av a() {
        synchronized (d) {
            if (e != null) {
                av avVar = e;
                e = avVar.c;
                avVar.c = null;
                f--;
                return avVar;
            }
            return new av();
        }
    }

    public final void b() {
        if (3 == this.a) {
            return;
        }
        synchronized (d) {
            if (f < 5) {
                this.c = e;
                e = this;
                f++;
            }
        }
    }
}
