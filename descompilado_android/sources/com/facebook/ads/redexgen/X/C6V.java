package com.facebook.ads.redexgen.X;

import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6V, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C6V implements AudioManager.OnAudioFocusChangeListener {
    public final /* synthetic */ C6U B;

    public C6V(C6U this$1) {
        this.B = this$1;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(final int focusChange) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.facebook.ads.redexgen.X.6W
            @Override // java.lang.Runnable
            public final void run() {
                if (this.B.B.B.getVideoView() != null && focusChange <= 0) {
                    this.B.B.B.getVideoView().H(false);
                }
            }
        });
    }
}
