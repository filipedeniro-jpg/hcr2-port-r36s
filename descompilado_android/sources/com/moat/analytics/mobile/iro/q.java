package com.moat.analytics.mobile.iro;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class q extends g implements NativeVideoTracker {

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private WeakReference<MediaPlayer> f641;

    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˊ */
    final String mo469() {
        return "NativeVideoTracker";
    }

    q(String str) {
        super(str);
        b.m461(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder("PartnerCode is ");
            sb.append(str == null ? "null" : "empty");
            String string = sb.toString();
            String str2 = "NativeDisplayTracker creation problem, " + string;
            b.m461(3, "NativeVideoTracker", this, str2);
            b.m459("[ERROR] ", str2);
            this.f518 = new o(string);
        }
        b.m459("[SUCCESS] ", "NativeVideoTracker created");
    }

    @Override // com.moat.analytics.mobile.iro.g
    /* JADX INFO: renamed from: ॱˊ */
    final boolean mo520() {
        WeakReference<MediaPlayer> weakReference = this.f641;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
    public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            m470();
            m473();
            if (mediaPlayer == null) {
                throw new o("Null player instance");
            }
            try {
                mediaPlayer.getCurrentPosition();
                this.f641 = new WeakReference<>(mediaPlayer);
                return super.mo482(map, view);
            } catch (Exception unused) {
                throw new o("Playback has already completed");
            }
        } catch (Exception e) {
            o.m569(e);
            String strM568 = o.m568("trackVideoAd", e);
            if (this.f516 != null) {
                this.f516.onTrackingFailedToStart(strM568);
            }
            b.m461(3, "NativeVideoTracker", this, strM568);
            b.m459("[ERROR] ", "NativeVideoTracker " + strM568);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.iro.g
    /* JADX INFO: renamed from: ˋॱ */
    final Integer mo519() {
        return Integer.valueOf(this.f641.get().getCurrentPosition());
    }

    @Override // com.moat.analytics.mobile.iro.g
    /* JADX INFO: renamed from: ॱˎ */
    final boolean mo521() {
        return this.f641.get().isPlaying();
    }

    @Override // com.moat.analytics.mobile.iro.g
    /* JADX INFO: renamed from: ʻॱ */
    final Integer mo517() {
        return Integer.valueOf(this.f641.get().getDuration());
    }

    @Override // com.moat.analytics.mobile.iro.d
    /* JADX INFO: renamed from: ˊॱ */
    final Map<String, Object> mo480() throws o {
        MediaPlayer mediaPlayer = this.f641.get();
        HashMap map = new HashMap();
        map.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        map.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        map.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return map;
    }

    @Override // com.moat.analytics.mobile.iro.d, com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˏ */
    final void mo474(List<String> list) throws o {
        WeakReference<MediaPlayer> weakReference = this.f641;
        if (!((weakReference == null || weakReference.get() == null) ? false : true)) {
            list.add("Player is null");
        }
        super.mo474(list);
    }
}
