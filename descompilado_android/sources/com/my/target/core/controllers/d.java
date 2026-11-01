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
import com.my.target.cm;
import com.my.target.common.models.VideoData;
import com.my.target.g;
import com.my.target.instreamads.InstreamAdPlayer;
import java.util.ArrayList;
import java.util.Stack;

/* JADX INFO: compiled from: InstreamAdVideoController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {

    @NonNull
    private final a e;

    @NonNull
    private final b g;

    @Nullable
    private cm i;

    @Nullable
    private c j;

    @Nullable
    private ak<VideoData> k;
    private int l;
    private float m;
    private int n;
    private boolean o;

    @Nullable
    private InstreamAdPlayer player;
    private float volume = 1.0f;
    private int p = 0;

    @NonNull
    private final ci f = ci.i(200);

    @NonNull
    private final Stack<ap> h = new Stack<>();

    /* JADX INFO: compiled from: InstreamAdVideoController.java */
    public interface c {
        void a(@NonNull ak akVar);

        void b(@NonNull ak akVar);

        void onBannerCompleted(@NonNull ak akVar);

        void onBannerError(@NonNull String str, @NonNull ak akVar);

        void onBannerProgressChanged(float f, float f2, @NonNull ak akVar);

        void onBannerStarted(@NonNull ak akVar);

        void onBannerStopped(@NonNull ak akVar);
    }

    @NonNull
    public static d f() {
        return new d();
    }

    private d() {
        byte b2 = 0;
        this.e = new a(this, b2);
        this.g = new b(this, b2);
    }

    public final void setVolume(float f) {
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            instreamAdPlayer.setVolume(f);
        }
        this.volume = f;
    }

    @Nullable
    public final InstreamAdPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        InstreamAdPlayer instreamAdPlayer2 = this.player;
        if (instreamAdPlayer2 != null) {
            instreamAdPlayer2.setAdPlayerListener(null);
        }
        this.player = instreamAdPlayer;
        if (instreamAdPlayer != null) {
            cm cmVar = this.i;
            if (cmVar != null) {
                cmVar.setView(instreamAdPlayer.getView());
            }
            instreamAdPlayer.setAdPlayerListener(this.e);
            return;
        }
        cm cmVar2 = this.i;
        if (cmVar2 != null) {
            cmVar2.setView(null);
        }
    }

    public final void swapPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        VideoData videoData;
        InstreamAdPlayer instreamAdPlayer2 = this.player;
        if (instreamAdPlayer2 != null) {
            instreamAdPlayer2.setAdPlayerListener(null);
            this.player.stopAdVideo();
        }
        this.player = instreamAdPlayer;
        if (instreamAdPlayer != null) {
            cm cmVar = this.i;
            if (cmVar != null) {
                cmVar.setView(instreamAdPlayer.getView());
            }
            instreamAdPlayer.setAdPlayerListener(this.e);
        } else {
            cm cmVar2 = this.i;
            if (cmVar2 != null) {
                cmVar2.setView(null);
            }
        }
        ak<VideoData> akVar = this.k;
        if (akVar == null || (videoData = (VideoData) akVar.getMediaData()) == null) {
            return;
        }
        Uri uri = Uri.parse(videoData.getUrl());
        if (instreamAdPlayer != null) {
            instreamAdPlayer.setVolume(this.volume);
            instreamAdPlayer.playAdVideo(uri, videoData.getWidth(), videoData.getHeight(), this.m);
        }
    }

    public final void a(@Nullable c cVar) {
        this.j = cVar;
    }

    @Nullable
    public final Context getContext() {
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer == null) {
            return null;
        }
        return instreamAdPlayer.getView().getContext();
    }

    public final void play(@NonNull ak<VideoData> akVar) {
        this.k = akVar;
        this.o = false;
        akVar.getStatHolder().a(this.h);
        this.i = cm.a(akVar.getStatHolder());
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            this.i.setView(instreamAdPlayer.getView());
        }
        VideoData videoData = (VideoData) akVar.getMediaData();
        if (videoData == null) {
            return;
        }
        Uri uri = Uri.parse(videoData.getUrl());
        InstreamAdPlayer instreamAdPlayer2 = this.player;
        if (instreamAdPlayer2 != null) {
            instreamAdPlayer2.setVolume(this.volume);
            this.player.playAdVideo(uri, videoData.getWidth(), videoData.getHeight());
        }
    }

    public final void pause() {
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            instreamAdPlayer.pauseAdVideo();
        }
    }

    public final void resume() {
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            instreamAdPlayer.resumeAdVideo();
        }
    }

    public final void stop() {
        if (this.p == 1) {
            if (this.k != null && this.j != null) {
                Context context = getContext();
                if (context != null) {
                    cj.a(this.k.getStatHolder().x(aq.a.dx), context);
                }
                this.j.onBannerStopped(this.k);
            }
            this.p = 0;
        }
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            instreamAdPlayer.stopAdVideo();
        }
        g();
    }

    public final void destroy() {
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            instreamAdPlayer.destroy();
        }
        this.player = null;
        g();
    }

    public final void setConnectionTimeout(int i) {
        this.n = i;
    }

    public final float getVolume() {
        return this.volume;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.k = null;
        cm cmVar = this.i;
        if (cmVar != null) {
            cmVar.destroy();
            this.i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        ArrayList arrayList = new ArrayList();
        while (this.h.size() > 0 && this.h.peek().Y() <= f) {
            arrayList.add(this.h.pop());
        }
        InstreamAdPlayer instreamAdPlayer = this.player;
        if (instreamAdPlayer != null) {
            cj.a(arrayList, instreamAdPlayer.getView().getContext());
        }
    }

    /* JADX INFO: compiled from: InstreamAdVideoController.java */
    private class a implements InstreamAdPlayer.AdPlayerListener {
        private float volume;

        private a() {
            this.volume = 1.0f;
        }

        /* synthetic */ a(d dVar, byte b) {
            this();
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoStarted() {
            d.this.p = 1;
            if (!d.this.o && d.this.player != null) {
                d dVar = d.this;
                d.a(dVar, dVar.player.getAdVideoDuration());
            }
            d.this.f.d(d.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoPaused() {
            Context context = d.this.getContext();
            if (d.this.k != null && context != null) {
                cj.a(d.this.k.getStatHolder().x(aq.a.dv), context);
            }
            d.this.f.e(d.this.g);
            if (d.this.k == null || d.this.j == null) {
                return;
            }
            d.this.j.a(d.this.k);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoResumed() {
            Context context = d.this.getContext();
            if (d.this.k != null && context != null) {
                cj.a(d.this.k.getStatHolder().x(aq.a.dw), context);
            }
            d.this.f.d(d.this.g);
            if (d.this.k == null || d.this.j == null) {
                return;
            }
            d.this.j.b(d.this.k);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoStopped() {
            if (d.this.p == 1) {
                if (d.this.k != null && d.this.j != null) {
                    Context context = d.this.getContext();
                    if (context != null) {
                        cj.a(d.this.k.getStatHolder().x(aq.a.dx), context);
                    }
                    d.this.j.onBannerStopped(d.this.k);
                }
                d.this.p = 0;
            }
            d.this.f.e(d.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoError(@NonNull String str) {
            if (d.this.k != null && d.this.j != null) {
                d.this.j.onBannerError(str, d.this.k);
            }
            d.this.f.e(d.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onAdVideoCompleted() {
            if (d.this.p != 2) {
                if (d.this.k != null && d.this.j != null) {
                    d.h(d.this);
                    if (d.this.k != null) {
                        ak akVar = d.this.k;
                        d.this.g();
                        if (akVar != null) {
                            d.this.a(akVar.getDuration());
                            d.this.j.onBannerCompleted(akVar);
                        }
                    }
                }
                d.this.p = 2;
            }
            d.this.f.e(d.this.g);
        }

        @Override // com.my.target.instreamads.InstreamAdPlayer.AdPlayerListener
        public final void onVolumeChanged(float f) {
            Context context;
            float f2 = this.volume;
            if (f == f2) {
                return;
            }
            if (f2 <= 0.0f || f > 0.0f) {
                if (this.volume != 0.0f || f <= 0.0f || (context = d.this.getContext()) == null || d.this.k == null) {
                    return;
                }
                cj.a(d.this.k.getStatHolder().x(aq.a.dC), context);
                this.volume = f;
                d.this.volume = f;
                return;
            }
            Context context2 = d.this.getContext();
            if (context2 == null || d.this.k == null) {
                return;
            }
            cj.a(d.this.k.getStatHolder().x(aq.a.dD), context2);
            this.volume = f;
            d.this.volume = f;
        }
    }

    /* JADX INFO: compiled from: InstreamAdVideoController.java */
    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(d dVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            d.h(d.this);
        }
    }

    static /* synthetic */ void a(d dVar, float f) {
        ak<VideoData> akVar;
        ak<VideoData> akVar2;
        c cVar;
        ak<VideoData> akVar3 = dVar.k;
        if (akVar3 != null && (cVar = dVar.j) != null) {
            cVar.onBannerStarted(akVar3);
        }
        Context context = dVar.getContext();
        if (context != null && (akVar2 = dVar.k) != null) {
            cj.a(akVar2.getStatHolder().x(aq.a.du), context);
        }
        c cVar2 = dVar.j;
        if (cVar2 != null && (akVar = dVar.k) != null) {
            cVar2.onBannerProgressChanged(f, f, akVar);
        }
        dVar.a(0.0f);
        dVar.o = true;
    }

    static /* synthetic */ void h(d dVar) {
        float adVideoDuration;
        float adVideoPosition;
        float f;
        ak<VideoData> akVar;
        c cVar;
        ak<VideoData> akVar2;
        ak<VideoData> akVar3;
        InstreamAdPlayer instreamAdPlayer;
        ak<VideoData> akVar4 = dVar.k;
        float duration = akVar4 != null ? akVar4.getDuration() : 0.0f;
        if (dVar.k == null) {
            dVar.f.e(dVar.g);
            return;
        }
        if (dVar.p != 1 || (instreamAdPlayer = dVar.player) == null) {
            adVideoDuration = 0.0f;
            adVideoPosition = 0.0f;
            f = 0.0f;
        } else {
            adVideoDuration = instreamAdPlayer.getAdVideoDuration();
            adVideoPosition = dVar.player.getAdVideoPosition();
            f = duration - adVideoPosition;
        }
        if (dVar.p != 1 || dVar.m == adVideoPosition || adVideoDuration <= 0.0f) {
            dVar.l++;
        } else {
            dVar.l = 0;
            dVar.m = adVideoPosition;
            if (adVideoPosition < duration) {
                dVar.a(adVideoPosition);
                cm cmVar = dVar.i;
                if (cmVar != null) {
                    cmVar.e(adVideoPosition);
                }
                c cVar2 = dVar.j;
                if (cVar2 != null && (akVar3 = dVar.k) != null) {
                    cVar2.onBannerProgressChanged(f, duration, akVar3);
                }
            } else {
                dVar.a(duration);
                dVar.m = duration;
                cm cmVar2 = dVar.i;
                if (cmVar2 != null) {
                    cmVar2.e(duration);
                }
                c cVar3 = dVar.j;
                if (cVar3 != null && (akVar2 = dVar.k) != null) {
                    cVar3.onBannerProgressChanged(0.0f, duration, akVar2);
                }
                dVar.f.e(dVar.g);
                if (dVar.p != 2) {
                    dVar.p = 2;
                    InstreamAdPlayer instreamAdPlayer2 = dVar.player;
                    if (instreamAdPlayer2 != null) {
                        instreamAdPlayer2.stopAdVideo();
                    }
                    ak<VideoData> akVar5 = dVar.k;
                    dVar.g();
                    if (akVar5 != null && (cVar = dVar.j) != null) {
                        cVar.onBannerCompleted(akVar5);
                    }
                }
            }
        }
        if (dVar.l >= (dVar.n * 1000) / 200) {
            g.a("video freeze more then " + dVar.n + " seconds, stopping");
            dVar.f.e(dVar.g);
            c cVar4 = dVar.j;
            if (cVar4 == null || (akVar = dVar.k) == null) {
                return;
            }
            cVar4.onBannerError("Timeout", akVar);
        }
    }
}
