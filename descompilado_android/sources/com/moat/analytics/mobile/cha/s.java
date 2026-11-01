package com.moat.analytics.mobile.cha;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class s extends i implements NativeVideoTracker {

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private WeakReference<MediaPlayer> f433;

    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final String mo303() {
        return "NativeVideoTracker";
    }

    s(String str) {
        super(str);
        a.m273(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder("PartnerCode is ");
            sb.append(str == null ? "null" : "empty");
            String string = sb.toString();
            String str2 = "NativeDisplayTracker creation problem, " + string;
            a.m273(3, "NativeVideoTracker", this, str2);
            a.m270("[ERROR] ", str2);
            this.f309 = new o(string);
        }
        a.m270("[SUCCESS] ", "NativeVideoTracker created");
    }

    @Override // com.moat.analytics.mobile.cha.i
    /* JADX INFO: renamed from: ͺ */
    final boolean mo333() {
        WeakReference<MediaPlayer> weakReference = this.f433;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
    public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            m304();
            m305();
            if (mediaPlayer == null) {
                throw new o("Null player instance");
            }
            try {
                mediaPlayer.getCurrentPosition();
                this.f433 = new WeakReference<>(mediaPlayer);
                return super.mo288(map, view);
            } catch (Exception unused) {
                throw new o("Playback has already completed");
            }
        } catch (Exception e) {
            o.m389(e);
            String strM388 = o.m388("trackVideoAd", e);
            if (this.f303 != null) {
                this.f303.onTrackingFailedToStart(strM388);
            }
            a.m273(3, "NativeVideoTracker", this, strM388);
            a.m270("[ERROR] ", "NativeVideoTracker " + strM388);
            return false;
        }
    }

    @Override // com.moat.analytics.mobile.cha.i
    /* JADX INFO: renamed from: ॱˋ */
    final Integer mo334() {
        return Integer.valueOf(this.f433.get().getCurrentPosition());
    }

    @Override // com.moat.analytics.mobile.cha.i
    /* JADX INFO: renamed from: ॱˎ */
    final boolean mo335() {
        return this.f433.get().isPlaying();
    }

    @Override // com.moat.analytics.mobile.cha.i
    /* JADX INFO: renamed from: ॱᐝ */
    final Integer mo336() {
        return Integer.valueOf(this.f433.get().getDuration());
    }

    @Override // com.moat.analytics.mobile.cha.b
    /* JADX INFO: renamed from: ᐝ */
    final Map<String, Object> mo290() throws o {
        MediaPlayer mediaPlayer = this.f433.get();
        HashMap map = new HashMap();
        map.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        map.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        map.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return map;
    }

    @Override // com.moat.analytics.mobile.cha.b, com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final void mo283(List<String> list) throws o {
        WeakReference<MediaPlayer> weakReference = this.f433;
        if (!((weakReference == null || weakReference.get() == null) ? false : true)) {
            list.add("Player is null");
        }
        super.mo283(list);
    }
}
