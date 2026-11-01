package com.adcolony.sdk;

import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ae {
    final String a;
    private final int b;
    private HashMap<Integer, MediaPlayer> c = new HashMap<>();
    private HashMap<Integer, a> d = new HashMap<>();
    private HashMap<Integer, Boolean> e = new HashMap<>();
    private HashMap<Integer, Boolean> f = new HashMap<>();
    private ArrayList<MediaPlayer> g = new ArrayList<>();

    ae(String str, int i) {
        this.a = str;
        this.b = i;
    }

    void a(af afVar) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        JSONObject jSONObjectC = afVar.c();
        int iC = y.c(jSONObjectC, "id");
        a aVar = new a(iC, y.d(jSONObjectC, "repeats"));
        this.c.put(Integer.valueOf(iC), mediaPlayer);
        this.d.put(Integer.valueOf(iC), aVar);
        this.e.put(Integer.valueOf(iC), false);
        this.f.put(Integer.valueOf(iC), false);
        mediaPlayer.setOnErrorListener(aVar);
        mediaPlayer.setOnPreparedListener(aVar);
        try {
            mediaPlayer.setDataSource(y.b(jSONObjectC, "filepath"));
        } catch (Exception unused) {
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", iC);
            y.a(jSONObjectA, "ad_session_id", this.a);
            new af("AudioPlayer.on_error", this.b, jSONObjectA).b();
        }
        mediaPlayer.prepareAsync();
    }

    void a() {
        this.g.clear();
        for (MediaPlayer mediaPlayer : this.c.values()) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                this.g.add(mediaPlayer);
            }
        }
    }

    void b() {
        Iterator<MediaPlayer> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
        this.g.clear();
    }

    void b(af afVar) {
        int iC = y.c(afVar.c(), "id");
        if (this.f.get(Integer.valueOf(iC)).booleanValue()) {
            this.c.get(Integer.valueOf(iC)).pause();
        }
    }

    void c(af afVar) {
        int iC = y.c(afVar.c(), "id");
        if (this.e.get(Integer.valueOf(iC)).booleanValue()) {
            this.c.get(Integer.valueOf(iC)).start();
            this.f.put(Integer.valueOf(iC), true);
        }
    }

    void d(af afVar) {
        this.c.remove(Integer.valueOf(y.c(afVar.c(), "id"))).release();
    }

    void e(af afVar) {
        int iC = y.c(afVar.c(), "id");
        if (this.f.get(Integer.valueOf(iC)).booleanValue()) {
            MediaPlayer mediaPlayer = this.c.get(Integer.valueOf(iC));
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    HashMap<Integer, MediaPlayer> c() {
        return this.c;
    }

    private class a implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
        int a;
        boolean b;

        a(int i, boolean z) {
            this.a = i;
            this.b = z;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", this.a);
            y.a(jSONObjectA, "ad_session_id", ae.this.a);
            new af("AudioPlayer.on_error", ae.this.b, jSONObjectA).b();
            return true;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setLooping(this.b);
            ae.this.e.put(Integer.valueOf(this.a), true);
            JSONObject jSONObjectA = y.a();
            y.b(jSONObjectA, "id", this.a);
            y.a(jSONObjectA, "ad_session_id", ae.this.a);
            new af("AudioPlayer.on_ready", ae.this.b, jSONObjectA).b();
        }
    }
}
