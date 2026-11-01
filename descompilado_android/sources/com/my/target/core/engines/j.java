package com.my.target.core.engines;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.af;
import com.my.target.ak;
import com.my.target.al;
import com.my.target.am;
import com.my.target.aq;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.models.VideoData;
import com.my.target.fj;
import com.my.target.instreamads.InstreamAd;
import com.my.target.instreamads.InstreamAdPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: InstreamAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j {
    private int A;
    private int B;
    private boolean C;

    @NonNull
    private final com.my.target.b adConfig;
    private int loadingTimeoutSeconds;

    @NonNull
    private final InstreamAd r;

    @NonNull
    private final com.my.target.core.models.sections.g s;

    @NonNull
    private final cc u;

    @Nullable
    private am<VideoData> v;

    @Nullable
    private ak<VideoData> w;

    @Nullable
    private InstreamAd.InstreamAdBanner x;

    @Nullable
    private List<ak<VideoData>> y;
    private float z;

    @NonNull
    private float[] midpoints = new float[0];

    @NonNull
    private final com.my.target.core.controllers.d t = com.my.target.core.controllers.d.f();

    static /* synthetic */ boolean e(j jVar) {
        jVar.C = true;
        return true;
    }

    @NonNull
    public static j a(@NonNull InstreamAd instreamAd, @NonNull com.my.target.core.models.sections.g gVar, @NonNull com.my.target.b bVar) {
        return new j(instreamAd, gVar, bVar);
    }

    private j(@NonNull InstreamAd instreamAd, @NonNull com.my.target.core.models.sections.g gVar, @NonNull com.my.target.b bVar) {
        this.r = instreamAd;
        this.s = gVar;
        this.adConfig = bVar;
        this.t.a(new a(this, (byte) 0));
        this.u = cc.bg();
    }

    public final void setVolume(float f) {
        this.t.setVolume(f);
    }

    @Nullable
    public final InstreamAdPlayer getPlayer() {
        return this.t.getPlayer();
    }

    public final void setPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        this.t.setPlayer(instreamAdPlayer);
    }

    public final void swapPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        this.t.swapPlayer(instreamAdPlayer);
    }

    public final void setMidpoints(@NonNull float[] fArr) {
        this.midpoints = fArr;
    }

    public final void setLoadingTimeoutSeconds(int i) {
        this.loadingTimeoutSeconds = i;
    }

    public final void setFullscreen(boolean z) {
        a(this.w, z ? aq.a.dz : aq.a.dA);
    }

    public final void start(@NonNull String str) {
        stop();
        this.v = this.s.a(str);
        am<VideoData> amVar = this.v;
        if (amVar != null) {
            this.t.setConnectionTimeout(amVar.P());
            this.C = false;
            this.B = this.v.Q();
            this.A = -1;
            this.y = this.v.R();
            h();
            return;
        }
        com.my.target.g.a("no section with name ".concat(String.valueOf(str)));
    }

    public final void startMidroll(float f) {
        boolean z;
        stop();
        float[] fArr = this.midpoints;
        int length = fArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else {
                if (Float.compare(fArr[i], f) == 0) {
                    z = true;
                    break;
                }
                i++;
            }
        }
        if (z) {
            this.v = this.s.a(am.a.dp);
            am<VideoData> amVar = this.v;
            if (amVar != null) {
                this.t.setConnectionTimeout(amVar.P());
                this.C = false;
                this.B = this.v.Q();
                this.A = -1;
                this.z = f;
                a(this.v, f);
                return;
            }
            return;
        }
        com.my.target.g.a("attempt to start wrong midpoint, use one of InstreamAd.getMidPoints()");
    }

    public final void pause() {
        if (this.v != null) {
            this.t.pause();
        }
    }

    public final void resume() {
        if (this.v != null) {
            this.t.resume();
        }
    }

    public final void stop() {
        if (this.v != null) {
            this.t.stop();
            a(this.v);
        }
    }

    public final void skip() {
        a(this.w, aq.a.dy);
        stop();
    }

    public final void skipBanner() {
        a(this.w, aq.a.dy);
        this.t.stop();
        h();
    }

    public final void handleClick() {
        if (this.w == null) {
            com.my.target.g.a("can't handle click: no playing banner");
            return;
        }
        Context context = this.t.getContext();
        if (context == null) {
            com.my.target.g.a("can't handle click: context is null");
        } else {
            this.u.a(this.w, context);
        }
    }

    public final void destroy() {
        this.t.destroy();
    }

    public final float getVolume() {
        return this.t.getVolume();
    }

    private void a(@NonNull am<VideoData> amVar, float f) {
        ArrayList arrayList = new ArrayList();
        Iterator it = amVar.R().iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.getPoint() == f) {
                arrayList.add(akVar);
            }
        }
        int size = arrayList.size();
        if (size > 0 && this.A < size - 1) {
            this.y = arrayList;
            h();
            return;
        }
        ArrayList<af> arrayListA = amVar.a(f);
        if (arrayListA.size() > 0) {
            a(arrayListA, amVar, f);
        } else {
            com.my.target.g.a("There is no one midpoint service for point: ".concat(String.valueOf(f)));
            b(amVar, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        List<ak<VideoData>> list;
        while (this.v != null) {
            if (this.B == 0 || (list = this.y) == null) {
                b(this.v, this.z);
                return;
            }
            int i = this.A + 1;
            if (i < list.size()) {
                this.A = i;
                ak<VideoData> akVar = this.y.get(i);
                if ("statistics".equals(akVar.getType())) {
                    a(akVar, aq.a.du);
                } else {
                    int i2 = this.B;
                    if (i2 > 0) {
                        this.B = i2 - 1;
                    }
                    this.w = akVar;
                    this.x = InstreamAd.InstreamAdBanner.newBanner(akVar);
                    this.t.play(akVar);
                    return;
                }
            } else {
                b(this.v, this.z);
                return;
            }
        }
    }

    private void b(@NonNull am<VideoData> amVar, float f) {
        af afVarT = amVar.T();
        if (afVarT != null) {
            if (am.a.dp.equals(amVar.getName())) {
                afVarT.e(true);
                afVarT.setPoint(f);
                ArrayList<af> arrayList = new ArrayList<>();
                arrayList.add(afVarT);
                com.my.target.g.a("using doAfter service for point: ".concat(String.valueOf(f)));
                a(arrayList, amVar, f);
                return;
            }
            a(afVarT, amVar);
            return;
        }
        a(amVar);
    }

    private void a(@NonNull am amVar) {
        if (amVar == this.v) {
            if (am.a.dp.equals(amVar.getName())) {
                this.v.e(this.B);
            }
            this.v = null;
            this.C = false;
            this.w = null;
            this.x = null;
            this.A = -1;
            InstreamAd.InstreamAdListener listener = this.r.getListener();
            if (listener != null) {
                listener.onComplete(amVar.getName(), this.r);
            }
        }
    }

    private void a(@NonNull af afVar, @NonNull final am<VideoData> amVar) {
        Context context = this.t.getContext();
        if (context == null) {
            com.my.target.g.a("can't load doAfter service: context is null");
            return;
        }
        com.my.target.g.a("loading doAfter service: " + afVar.getUrl());
        fj.newFactoryForAdService(afVar, this.adConfig, this.loadingTimeoutSeconds).a(new fj.b() { // from class: com.my.target.core.engines.j.1
            @Override // com.my.target.c.b
            public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
                j.a(j.this, amVar, (com.my.target.core.models.sections.g) alVar, str);
            }
        }).a(context);
    }

    private void a(@NonNull ArrayList<af> arrayList, @NonNull final am<VideoData> amVar, final float f) {
        Context context = this.t.getContext();
        if (context == null) {
            com.my.target.g.a("can't load midpoint services: context is null");
        } else {
            com.my.target.g.a("loading midpoint services for point: ".concat(String.valueOf(f)));
            fj.newFactoryForAdServices(arrayList, this.adConfig, this.loadingTimeoutSeconds).a(new fj.b() { // from class: com.my.target.core.engines.j.2
                @Override // com.my.target.c.b
                public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
                    j.a(j.this, amVar, (com.my.target.core.models.sections.g) alVar, str, f);
                }
            }).a(context);
        }
    }

    private void a(@Nullable ak akVar, @NonNull String str) {
        if (akVar == null) {
            com.my.target.g.a("can't send stat: banner is null");
            return;
        }
        Context context = this.t.getContext();
        if (context == null) {
            com.my.target.g.a("can't send stat: context is null");
        } else {
            cj.a(akVar.getStatHolder().x(str), context);
        }
    }

    /* JADX INFO: compiled from: InstreamAdEngine.java */
    private class a implements com.my.target.core.controllers.d.c {
        private a() {
        }

        /* synthetic */ a(j jVar, byte b) {
            this();
        }

        @Override // com.my.target.core.controllers.d.c
        public final void onBannerStarted(@NonNull ak akVar) {
            if (j.this.v == null || j.this.w != akVar || j.this.x == null) {
                return;
            }
            if (!j.this.C) {
                j.e(j.this);
                Context context = j.this.t.getContext();
                if (context != null) {
                    cj.a(j.this.v.q(aq.a.dB), context);
                } else {
                    com.my.target.g.a("can't send stat: context is null");
                }
            }
            InstreamAd.InstreamAdListener listener = j.this.r.getListener();
            if (listener != null) {
                listener.onBannerStart(j.this.r, j.this.x);
            }
        }

        @Override // com.my.target.core.controllers.d.c
        public final void a(@NonNull ak akVar) {
            InstreamAd.InstreamAdListener listener;
            if (j.this.v == null || j.this.w != akVar || j.this.x == null || (listener = j.this.r.getListener()) == null) {
                return;
            }
            listener.onBannerPause(j.this.r, j.this.x);
        }

        @Override // com.my.target.core.controllers.d.c
        public final void b(@NonNull ak akVar) {
            InstreamAd.InstreamAdListener listener;
            if (j.this.v == null || j.this.w != akVar || j.this.x == null || (listener = j.this.r.getListener()) == null) {
                return;
            }
            listener.onBannerResume(j.this.r, j.this.x);
        }

        @Override // com.my.target.core.controllers.d.c
        public final void onBannerStopped(@NonNull ak akVar) {
            InstreamAd.InstreamAdListener listener;
            if (j.this.v == null || j.this.w != akVar || j.this.x == null || (listener = j.this.r.getListener()) == null) {
                return;
            }
            listener.onBannerComplete(j.this.r, j.this.x);
        }

        @Override // com.my.target.core.controllers.d.c
        public final void onBannerCompleted(@NonNull ak akVar) {
            if (j.this.v == null || j.this.w != akVar || j.this.x == null) {
                return;
            }
            InstreamAd.InstreamAdListener listener = j.this.r.getListener();
            if (listener != null) {
                listener.onBannerComplete(j.this.r, j.this.x);
            }
            j.this.h();
        }

        @Override // com.my.target.core.controllers.d.c
        public final void onBannerProgressChanged(float f, float f2, @NonNull ak akVar) {
            InstreamAd.InstreamAdListener listener;
            if (j.this.v == null || j.this.w != akVar || j.this.x == null || (listener = j.this.r.getListener()) == null) {
                return;
            }
            listener.onBannerTimeLeftChange(f, f2, j.this.r);
        }

        @Override // com.my.target.core.controllers.d.c
        public final void onBannerError(@NonNull String str, @NonNull ak akVar) {
            if (j.this.v == null || j.this.w != akVar) {
                return;
            }
            InstreamAd.InstreamAdListener listener = j.this.r.getListener();
            if (listener != null) {
                listener.onError(str, j.this.r);
            }
            j.this.h();
        }
    }

    static /* synthetic */ void a(j jVar, am amVar, com.my.target.core.models.sections.g gVar, String str) {
        if (gVar == null) {
            if (str != null) {
                com.my.target.g.a("loading doAfter service failed: ".concat(String.valueOf(str)));
            }
            if (amVar == jVar.v) {
                jVar.b(amVar, jVar.z);
                return;
            }
            return;
        }
        am<VideoData> amVarA = gVar.a(amVar.getName());
        if (amVarA != null) {
            amVar.a(amVarA);
        }
        if (amVar == jVar.v) {
            jVar.y = amVar.R();
            jVar.h();
        }
    }

    static /* synthetic */ void a(j jVar, am amVar, com.my.target.core.models.sections.g gVar, String str, float f) {
        if (gVar == null) {
            if (str != null) {
                com.my.target.g.a("loading midpoint services failed: ".concat(String.valueOf(str)));
            }
            if (amVar == jVar.v && f == jVar.z) {
                jVar.b(amVar, f);
                return;
            }
            return;
        }
        am<VideoData> amVarA = gVar.a(amVar.getName());
        if (amVarA != null) {
            amVar.a(amVarA);
        }
        if (amVar == jVar.v && f == jVar.z) {
            jVar.a((am<VideoData>) amVar, f);
        }
    }
}
