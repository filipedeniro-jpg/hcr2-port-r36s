package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class u extends h implements NativeVideoTracker {
    private WeakReference<MediaPlayer> m;

    u(String str) {
        super(str);
        p.a(3, "NativeVideoTracker", this, "In initialization method.");
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder("PartnerCode is ");
            sb.append(str == null ? "null" : "empty");
            String string = sb.toString();
            p.a("[ERROR] ", 3, "NativeVideoTracker", this, "NativeDisplayTracker creation problem, " + string);
            this.a = new m(string);
        }
        p.a("[SUCCESS] ", a() + " created");
    }

    private void a(MediaPlayer mediaPlayer) throws m {
        if (mediaPlayer == null) {
            throw new m("Null player instance");
        }
        try {
            mediaPlayer.getCurrentPosition();
        } catch (Exception unused) {
            throw new m("Playback has already completed");
        }
    }

    @Override // com.moat.analytics.mobile.inm.b
    String a() {
        return "NativeVideoTracker";
    }

    @Override // com.moat.analytics.mobile.inm.c, com.moat.analytics.mobile.inm.b
    void a(List<String> list) {
        if (!n()) {
            list.add("Player is null");
        }
        super.a(list);
    }

    @Override // com.moat.analytics.mobile.inm.c
    Map<String, Object> i() {
        MediaPlayer mediaPlayer = this.m.get();
        HashMap map = new HashMap();
        map.put("width", Integer.valueOf(mediaPlayer.getVideoWidth()));
        map.put("height", Integer.valueOf(mediaPlayer.getVideoHeight()));
        map.put("duration", Integer.valueOf(mediaPlayer.getDuration()));
        return map;
    }

    @Override // com.moat.analytics.mobile.inm.h
    boolean n() {
        WeakReference<MediaPlayer> weakReference = this.m;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.moat.analytics.mobile.inm.h
    Integer o() {
        return Integer.valueOf(this.m.get().getCurrentPosition());
    }

    @Override // com.moat.analytics.mobile.inm.h
    boolean q() {
        return this.m.get().isPlaying();
    }

    @Override // com.moat.analytics.mobile.inm.h
    Integer r() {
        return Integer.valueOf(this.m.get().getDuration());
    }

    @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
    public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
        try {
            c();
            d();
            a(mediaPlayer);
            this.m = new WeakReference<>(mediaPlayer);
            return super.a(map, view);
        } catch (Exception e) {
            m.a(e);
            String strA = m.a("trackVideoAd", e);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(strA);
            }
            p.a(3, "NativeVideoTracker", this, strA);
            p.a("[ERROR] ", a() + " " + strA);
            return false;
        }
    }
}
