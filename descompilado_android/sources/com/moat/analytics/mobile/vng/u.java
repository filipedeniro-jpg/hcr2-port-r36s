package com.moat.analytics.mobile.vng;

import android.media.MediaPlayer;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class u extends h<MediaPlayer> implements NativeVideoTracker {
    u(String str) {
        super(str);
        p.a(3, "NativeVideoTracker", this, "In initialization method.");
        p.a("[SUCCESS] ", a() + " created");
    }

    @Override // com.moat.analytics.mobile.vng.b
    String a() {
        return "NativeVideoTracker";
    }

    @Override // com.moat.analytics.mobile.vng.c
    protected Map<String, Object> g() {
        MediaPlayer mediaPlayer = (MediaPlayer) this.j.get();
        HashMap map = new HashMap();
        map.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        map.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        map.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return map;
    }

    @Override // com.moat.analytics.mobile.vng.h
    protected Integer j() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getCurrentPosition());
    }

    @Override // com.moat.analytics.mobile.vng.h
    protected boolean k() {
        return ((MediaPlayer) this.j.get()).isPlaying();
    }

    @Override // com.moat.analytics.mobile.vng.h
    protected Integer l() {
        return Integer.valueOf(((MediaPlayer) this.j.get()).getDuration());
    }

    @Override // com.moat.analytics.mobile.vng.h, com.moat.analytics.mobile.vng.c
    /* JADX INFO: renamed from: trackVideoAd, reason: merged with bridge method [inline-methods] */
    public boolean a(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        StringBuilder sb;
        String str;
        if (mediaPlayer == null) {
            p.a(3, "NativeVideoTracker", this, "Null player instance. Not tracking.");
            sb = new StringBuilder();
            sb.append(a());
            str = " tracking didn't start, Null player instance";
        } else {
            try {
                mediaPlayer.getCurrentPosition();
                return super.a(map, mediaPlayer, view);
            } catch (Exception unused) {
                p.a(3, "NativeVideoTracker", this, "Playback has already completed. Not tracking.");
                sb = new StringBuilder();
                sb.append(a());
                sb.append(" tracking didn't started for ");
                sb.append(e());
                str = ", playback has already completed";
            }
        }
        sb.append(str);
        p.a("[ERROR] ", sb.toString());
        return false;
    }
}
