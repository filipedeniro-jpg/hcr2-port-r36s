package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public final class zzayu implements AudioManager.OnAudioFocusChangeListener {
    private float zzcw = 1.0f;
    private boolean zzdxs;
    private final AudioManager zzeas;
    private final zzayx zzeat;
    private boolean zzeau;
    private boolean zzeav;

    public zzayu(Context context, zzayx zzayxVar) {
        this.zzeas = (AudioManager) context.getSystemService("audio");
        this.zzeat = zzayxVar;
    }

    public final void setMuted(boolean z) {
        this.zzeav = z;
        zzxt();
    }

    public final void setVolume(float f) {
        this.zzcw = f;
        zzxt();
    }

    public final float getVolume() {
        float f = this.zzeav ? 0.0f : this.zzcw;
        if (this.zzeau) {
            return f;
        }
        return 0.0f;
    }

    public final void zzxg() {
        this.zzdxs = true;
        zzxt();
    }

    public final void zzxh() {
        this.zzdxs = false;
        zzxt();
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public final void onAudioFocusChange(int i) {
        this.zzeau = i > 0;
        this.zzeat.zzwq();
    }

    private final void zzxt() {
        boolean z;
        boolean z2;
        boolean z3 = this.zzdxs && !this.zzeav && this.zzcw > 0.0f;
        if (z3 && !(z2 = this.zzeau)) {
            AudioManager audioManager = this.zzeas;
            if (audioManager != null && !z2) {
                this.zzeau = audioManager.requestAudioFocus(this, 3, 2) == 1;
            }
            this.zzeat.zzwq();
            return;
        }
        if (z3 || !(z = this.zzeau)) {
            return;
        }
        AudioManager audioManager2 = this.zzeas;
        if (audioManager2 != null && z) {
            this.zzeau = audioManager2.abandonAudioFocus(this) == 0;
        }
        this.zzeat.zzwq();
    }
}
