package com.my.target.core.controllers;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ak;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.ci;
import com.my.target.cj;
import com.my.target.common.models.AudioData;
import com.my.target.g;
import com.my.target.instreamads.InstreamAudioAdPlayer;
import java.util.ArrayList;
import java.util.Stack;

/* JADX INFO: compiled from: InstreamAdAudioController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    @NonNull
    private final a e;

    @NonNull
    private final RunnableC0093c g;

    @Nullable
    private b i;

    @Nullable
    private ak<AudioData> j;
    private int k;
    private float l;
    private int m;
    private boolean n;

    @Nullable
    private InstreamAudioAdPlayer player;
    private float volume = 1.0f;
    private int o = 0;

    @NonNull
    private final ci f = ci.i(200);

    @NonNull
    private final Stack<ap> h = new Stack<>();

    /* JADX INFO: compiled from: InstreamAdAudioController.java */
    public interface b {
        void onBannerCompleted(@NonNull ak akVar);

        void onBannerError(@NonNull String str, @NonNull ak akVar);

        void onBannerProgressChanged(float f, float f2, @NonNull ak akVar);

        void onBannerStarted(@NonNull ak akVar);

        void onBannerStopped(@NonNull ak akVar);
    }

    static /* synthetic */ ak i(c cVar) {
        cVar.j = null;
        return null;
    }

    @NonNull
    public static c f() {
        return new c();
    }

    private c() {
        byte b2 = 0;
        this.e = new a(this, b2);
        this.g = new RunnableC0093c(this, b2);
    }

    public final void setVolume(float f) {
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.setVolume(f);
        }
        this.volume = f;
    }

    @Nullable
    public final InstreamAudioAdPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(@Nullable InstreamAudioAdPlayer instreamAudioAdPlayer) {
        InstreamAudioAdPlayer instreamAudioAdPlayer2 = this.player;
        if (instreamAudioAdPlayer2 != null) {
            instreamAudioAdPlayer2.setAdPlayerListener(null);
        }
        this.player = instreamAudioAdPlayer;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.setAdPlayerListener(this.e);
        }
    }

    public final void a(@Nullable b bVar) {
        this.i = bVar;
    }

    @Nullable
    public final Context getContext() {
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer == null) {
            return null;
        }
        return instreamAudioAdPlayer.getCurrentContext();
    }

    public final void play(@NonNull ak<AudioData> akVar) {
        this.j = akVar;
        this.n = false;
        akVar.getStatHolder().a(this.h);
        AudioData audioData = (AudioData) akVar.getMediaData();
        if (audioData == null) {
            return;
        }
        Uri uri = Uri.parse(audioData.getUrl());
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.setVolume(this.volume);
            this.player.playAdAudio(uri);
        }
    }

    public final void pause() {
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.pauseAdAudio();
        }
    }

    public final void resume() {
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.resumeAdAudio();
        }
    }

    public final void stop() {
        if (this.o == 1) {
            if (this.j != null && this.i != null) {
                Context context = getContext();
                if (context != null) {
                    cj.a(this.j.getStatHolder().x(aq.a.dx), context);
                }
                this.i.onBannerStopped(this.j);
            }
            this.o = 0;
        }
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.stopAdAudio();
        }
    }

    public final void destroy() {
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            instreamAudioAdPlayer.destroy();
        }
        this.player = null;
    }

    public final void setConnectionTimeout(int i) {
        this.m = i;
    }

    public final float getVolume() {
        return this.volume;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        ArrayList arrayList = new ArrayList();
        while (this.h.size() > 0 && this.h.peek().Y() <= f) {
            arrayList.add(this.h.pop());
        }
        InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
        if (instreamAudioAdPlayer != null) {
            cj.a(arrayList, instreamAudioAdPlayer.getCurrentContext());
        }
    }

    /* JADX INFO: compiled from: InstreamAdAudioController.java */
    private class a implements InstreamAudioAdPlayer.AdPlayerListener {
        private float volume;

        private a() {
            this.volume = 1.0f;
        }

        /* synthetic */ a(c cVar, byte b) {
            this();
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioStarted() {
            c.this.o = 1;
            if (!c.this.n && c.this.player != null) {
                c cVar = c.this;
                c.a(cVar, cVar.player.getAdAudioDuration());
            }
            c.this.f.d(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioPaused() {
            Context context = c.this.getContext();
            if (c.this.j != null && context != null) {
                cj.a(c.this.j.getStatHolder().x(aq.a.dv), context);
            }
            c.this.f.e(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioResumed() {
            Context context = c.this.getContext();
            if (c.this.j != null && context != null) {
                cj.a(c.this.j.getStatHolder().x(aq.a.dw), context);
            }
            c.this.f.d(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioStopped() {
            if (c.this.o == 1) {
                if (c.this.j != null && c.this.i != null) {
                    Context context = c.this.getContext();
                    if (context != null) {
                        cj.a(c.this.j.getStatHolder().x(aq.a.dx), context);
                    }
                    c.this.i.onBannerStopped(c.this.j);
                }
                c.this.o = 0;
            }
            c.this.f.e(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioError(@NonNull String str) {
            if (c.this.j != null && c.this.i != null) {
                c.this.i.onBannerError(str, c.this.j);
            }
            c.this.f.e(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onAdAudioCompleted() {
            if (c.this.o != 2) {
                if (c.this.j != null && c.this.i != null) {
                    c.h(c.this);
                    ak akVar = c.this.j;
                    c.i(c.this);
                    if (akVar != null) {
                        c.this.a(akVar.getDuration());
                        c.this.i.onBannerCompleted(akVar);
                    }
                }
                c.this.o = 2;
            }
            c.this.f.e(c.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAudioAdPlayer.AdPlayerListener
        public final void onVolumeChanged(float f) {
            Context context;
            float f2 = this.volume;
            if (f == f2) {
                return;
            }
            if (f2 <= 0.0f || f > 0.0f) {
                if (this.volume != 0.0f || f <= 0.0f || (context = c.this.getContext()) == null || c.this.j == null) {
                    return;
                }
                cj.a(c.this.j.getStatHolder().x(aq.a.dC), context);
                this.volume = f;
                c.this.volume = f;
                return;
            }
            Context context2 = c.this.getContext();
            if (context2 == null || c.this.j == null) {
                return;
            }
            cj.a(c.this.j.getStatHolder().x(aq.a.dD), context2);
            this.volume = f;
            c.this.volume = f;
        }
    }

    /* JADX INFO: renamed from: com.my.target.core.controllers.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: InstreamAdAudioController.java */
    private class RunnableC0093c implements Runnable {
        private RunnableC0093c() {
        }

        /* synthetic */ RunnableC0093c(c cVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            c.h(c.this);
        }
    }

    static /* synthetic */ void a(c cVar, float f) {
        ak<AudioData> akVar;
        ak<AudioData> akVar2;
        b bVar;
        ak<AudioData> akVar3 = cVar.j;
        if (akVar3 != null && (bVar = cVar.i) != null) {
            bVar.onBannerStarted(akVar3);
        }
        Context context = cVar.getContext();
        if (context != null && (akVar2 = cVar.j) != null) {
            cj.a(akVar2.getStatHolder().x(aq.a.du), context);
        }
        b bVar2 = cVar.i;
        if (bVar2 != null && (akVar = cVar.j) != null) {
            bVar2.onBannerProgressChanged(0.0f, f, akVar);
        }
        cVar.a(0.0f);
        cVar.n = true;
    }

    static /* synthetic */ void h(c cVar) {
        float adAudioDuration;
        float adAudioPosition;
        float f;
        ak<AudioData> akVar;
        b bVar;
        ak<AudioData> akVar2;
        ak<AudioData> akVar3;
        InstreamAudioAdPlayer instreamAudioAdPlayer;
        ak<AudioData> akVar4 = cVar.j;
        float duration = akVar4 != null ? akVar4.getDuration() : 0.0f;
        if (cVar.j == null) {
            cVar.f.e(cVar.g);
            return;
        }
        if (cVar.o != 1 || (instreamAudioAdPlayer = cVar.player) == null) {
            adAudioDuration = 0.0f;
            adAudioPosition = 0.0f;
            f = 0.0f;
        } else {
            adAudioDuration = instreamAudioAdPlayer.getAdAudioDuration();
            adAudioPosition = cVar.player.getAdAudioPosition();
            f = duration - adAudioPosition;
        }
        if (cVar.o != 1 || cVar.l == adAudioPosition || adAudioDuration <= 0.0f) {
            cVar.k++;
        } else {
            cVar.k = 0;
            cVar.l = adAudioPosition;
            if (adAudioPosition < duration) {
                cVar.a(adAudioPosition);
                b bVar2 = cVar.i;
                if (bVar2 != null && (akVar3 = cVar.j) != null) {
                    bVar2.onBannerProgressChanged(f, duration, akVar3);
                }
            } else {
                cVar.a(duration);
                b bVar3 = cVar.i;
                if (bVar3 != null && (akVar2 = cVar.j) != null) {
                    bVar3.onBannerProgressChanged(0.0f, duration, akVar2);
                }
                cVar.f.e(cVar.g);
                if (cVar.o != 2) {
                    cVar.o = 2;
                    InstreamAudioAdPlayer instreamAudioAdPlayer2 = cVar.player;
                    if (instreamAudioAdPlayer2 != null) {
                        instreamAudioAdPlayer2.stopAdAudio();
                    }
                    ak<AudioData> akVar5 = cVar.j;
                    if (akVar5 != null && (bVar = cVar.i) != null) {
                        cVar.j = null;
                        bVar.onBannerCompleted(akVar5);
                    }
                }
            }
        }
        if (cVar.k >= (cVar.m * 1000) / 200) {
            g.a("video freeze more then " + cVar.m + " seconds, stopping");
            cVar.f.e(cVar.g);
            b bVar4 = cVar.i;
            if (bVar4 == null || (akVar = cVar.j) == null) {
                return;
            }
            bVar4.onBannerError("Timeout", akVar);
        }
    }
}
