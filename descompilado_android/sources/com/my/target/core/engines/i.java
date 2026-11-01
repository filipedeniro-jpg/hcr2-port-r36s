package com.my.target.core.engines;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.af;
import com.my.target.aj;
import com.my.target.ak;
import com.my.target.al;
import com.my.target.am;
import com.my.target.aq;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.models.AudioData;
import com.my.target.fe;
import com.my.target.instreamads.InstreamAudioAd;
import com.my.target.instreamads.InstreamAudioAdPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: InstreamAudioAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i {
    private int A;
    private int B;
    private boolean C;

    @NonNull
    private final com.my.target.b adConfig;
    private int loadingTimeoutSeconds;

    @NonNull
    private final InstreamAudioAd q;

    @NonNull
    private final com.my.target.core.models.sections.f r;

    @NonNull
    private final cc t;

    @Nullable
    private am<AudioData> u;

    @Nullable
    private ak<AudioData> v;

    @Nullable
    private InstreamAudioAd.InstreamAudioAdBanner w;

    @Nullable
    private List<InstreamAudioAd.InstreamAdCompanionBanner> x;

    @Nullable
    private List<ak<AudioData>> y;
    private float z;

    @NonNull
    private float[] midpoints = new float[0];

    @NonNull
    private final com.my.target.core.controllers.c s = com.my.target.core.controllers.c.f();

    static /* synthetic */ boolean e(i iVar) {
        iVar.C = true;
        return true;
    }

    @NonNull
    public static i a(@NonNull InstreamAudioAd instreamAudioAd, @NonNull com.my.target.core.models.sections.f fVar, @NonNull com.my.target.b bVar) {
        return new i(instreamAudioAd, fVar, bVar);
    }

    private i(@NonNull InstreamAudioAd instreamAudioAd, @NonNull com.my.target.core.models.sections.f fVar, @NonNull com.my.target.b bVar) {
        this.q = instreamAudioAd;
        this.r = fVar;
        this.adConfig = bVar;
        this.s.a(new a(this, (byte) 0));
        this.t = cc.bg();
    }

    public final void setVolume(float f) {
        this.s.setVolume(f);
    }

    @Nullable
    public final InstreamAudioAdPlayer getPlayer() {
        return this.s.getPlayer();
    }

    public final void setPlayer(@Nullable InstreamAudioAdPlayer instreamAudioAdPlayer) {
        this.s.setPlayer(instreamAudioAdPlayer);
    }

    public final void setMidpoints(@NonNull float[] fArr) {
        this.midpoints = fArr;
    }

    public final void setLoadingTimeoutSeconds(int i) {
        this.loadingTimeoutSeconds = i;
    }

    public final void start(@NonNull String str) {
        stop();
        this.u = this.r.a(str);
        am<AudioData> amVar = this.u;
        if (amVar != null) {
            this.s.setConnectionTimeout(amVar.P());
            this.C = false;
            this.B = this.u.Q();
            this.A = -1;
            this.y = this.u.R();
            g();
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
            this.u = this.r.a(am.a.dp);
            am<AudioData> amVar = this.u;
            if (amVar != null) {
                this.s.setConnectionTimeout(amVar.P());
                this.C = false;
                this.B = this.u.Q();
                this.A = -1;
                this.z = f;
                a(this.u, f);
                return;
            }
            return;
        }
        com.my.target.g.a("attempt to start wrong midpoint, use one of InstreamAd.getMidPoints()");
    }

    public final void pause() {
        if (this.u != null) {
            this.s.pause();
        }
    }

    public final void resume() {
        if (this.u != null) {
            this.s.resume();
        }
    }

    public final void stop() {
        if (this.u != null) {
            this.s.stop();
            a(this.u);
        }
    }

    public final void skip() {
        a(this.v, aq.a.dy);
        stop();
    }

    public final void skipBanner() {
        a(this.v, aq.a.dy);
        this.s.stop();
        g();
    }

    public final void handleCompanionClick(@NonNull InstreamAudioAd.InstreamAdCompanionBanner instreamAdCompanionBanner) {
        Context context = this.s.getContext();
        if (context == null) {
            com.my.target.g.a("can't handle click: context is null");
            return;
        }
        aj ajVarA = a(instreamAdCompanionBanner);
        if (ajVarA == null) {
            com.my.target.g.a("can't handle click: companion banner not found");
        } else {
            this.t.a(ajVarA, context);
        }
    }

    public final void handleCompanionClick(@NonNull InstreamAudioAd.InstreamAdCompanionBanner instreamAdCompanionBanner, @NonNull Context context) {
        aj ajVarA = a(instreamAdCompanionBanner);
        if (ajVarA == null) {
            com.my.target.g.a("can't handle click: companion banner not found");
        } else {
            this.t.a(ajVarA, context);
        }
    }

    public final void handleCompanionShow(@NonNull InstreamAudioAd.InstreamAdCompanionBanner instreamAdCompanionBanner) {
        Context context = this.s.getContext();
        if (context == null) {
            com.my.target.g.a("can't handle show: context is null");
            return;
        }
        aj ajVarA = a(instreamAdCompanionBanner);
        if (ajVarA == null) {
            com.my.target.g.a("can't handle show: companion banner not found");
        } else {
            cj.a(ajVarA.getStatHolder().x(aq.a.du), context);
        }
    }

    public final void destroy() {
        this.s.destroy();
    }

    public final float getVolume() {
        return this.s.getVolume();
    }

    private void a(@NonNull am<AudioData> amVar, float f) {
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
            g();
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
    public void g() {
        List<ak<AudioData>> list;
        while (this.u != null) {
            if (this.B == 0 || (list = this.y) == null) {
                b(this.u, this.z);
                return;
            }
            int i = this.A + 1;
            if (i < list.size()) {
                this.A = i;
                ak<AudioData> akVar = this.y.get(i);
                if ("statistics".equals(akVar.getType())) {
                    a(akVar, aq.a.du);
                } else {
                    int i2 = this.B;
                    if (i2 > 0) {
                        this.B = i2 - 1;
                    }
                    this.v = akVar;
                    this.w = InstreamAudioAd.InstreamAudioAdBanner.newBanner(akVar);
                    this.x = new ArrayList(this.w.companionBanners);
                    this.s.play(akVar);
                    return;
                }
            } else {
                b(this.u, this.z);
                return;
            }
        }
    }

    private void b(@NonNull am<AudioData> amVar, float f) {
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

    private void a(@NonNull am<AudioData> amVar) {
        if (amVar == this.u) {
            if (am.a.dp.equals(amVar.getName())) {
                this.u.e(this.B);
            }
            this.u = null;
            this.C = false;
            this.v = null;
            this.w = null;
            this.A = -1;
            InstreamAudioAd.InstreamAudioAdListener listener = this.q.getListener();
            if (listener != null) {
                listener.onComplete(amVar.getName(), this.q);
            }
        }
    }

    private void a(@NonNull af afVar, @NonNull final am<AudioData> amVar) {
        Context context = this.s.getContext();
        if (context == null) {
            com.my.target.g.a("can't load doAfter service: context is null");
            return;
        }
        com.my.target.g.a("loading doAfter service: " + afVar.getUrl());
        fe.newFactoryForAdService(afVar, this.adConfig, this.loadingTimeoutSeconds).a(new fe.b() { // from class: com.my.target.core.engines.i.1
            @Override // com.my.target.c.b
            public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
                i.a(i.this, amVar, (com.my.target.core.models.sections.f) alVar, str);
            }
        }).a(context);
    }

    private void a(@NonNull ArrayList<af> arrayList, @NonNull final am<AudioData> amVar, final float f) {
        Context context = this.s.getContext();
        if (context == null) {
            com.my.target.g.a("can't load midpoint services: context is null");
        } else {
            com.my.target.g.a("loading midpoint services for point: ".concat(String.valueOf(f)));
            fe.newFactoryForAdServices(arrayList, this.adConfig, this.loadingTimeoutSeconds).a(new fe.b() { // from class: com.my.target.core.engines.i.2
                @Override // com.my.target.c.b
                public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
                    i.a(i.this, amVar, (com.my.target.core.models.sections.f) alVar, str, f);
                }
            }).a(context);
        }
    }

    private void a(@Nullable ak akVar, @NonNull String str) {
        if (akVar == null) {
            com.my.target.g.a("can't send stat: banner is null");
            return;
        }
        Context context = this.s.getContext();
        if (context == null) {
            com.my.target.g.a("can't send stat: context is null");
        } else {
            cj.a(akVar.getStatHolder().x(str), context);
        }
    }

    @Nullable
    private aj a(@NonNull InstreamAudioAd.InstreamAdCompanionBanner instreamAdCompanionBanner) {
        ak<AudioData> akVar;
        if (this.x == null || this.w == null || (akVar = this.v) == null) {
            com.my.target.g.a("can't find companion banner: no playing banner");
            return null;
        }
        ArrayList<aj> companionBanners = akVar.getCompanionBanners();
        int iIndexOf = this.x.indexOf(instreamAdCompanionBanner);
        if (iIndexOf < 0 || iIndexOf >= companionBanners.size()) {
            com.my.target.g.a("can't find companion banner: provided instreamAdCompanionBanner not found in current playing banner");
            return null;
        }
        return companionBanners.get(iIndexOf);
    }

    /* JADX INFO: compiled from: InstreamAudioAdEngine.java */
    private class a implements com.my.target.core.controllers.c.b {
        private a() {
        }

        /* synthetic */ a(i iVar, byte b) {
            this();
        }

        @Override // com.my.target.core.controllers.c.b
        public final void onBannerStarted(@NonNull ak akVar) {
            if (i.this.u == null || i.this.v != akVar || i.this.w == null) {
                return;
            }
            if (!i.this.C) {
                i.e(i.this);
                Context context = i.this.s.getContext();
                if (context != null) {
                    cj.a(i.this.u.q(aq.a.dB), context);
                } else {
                    com.my.target.g.a("can't send stat: context is null");
                }
            }
            InstreamAudioAd.InstreamAudioAdListener listener = i.this.q.getListener();
            if (listener != null) {
                listener.onBannerStart(i.this.q, i.this.w);
            }
        }

        @Override // com.my.target.core.controllers.c.b
        public final void onBannerStopped(@NonNull ak akVar) {
            InstreamAudioAd.InstreamAudioAdListener listener;
            if (i.this.u == null || i.this.v != akVar || i.this.w == null || (listener = i.this.q.getListener()) == null) {
                return;
            }
            listener.onBannerComplete(i.this.q, i.this.w);
        }

        @Override // com.my.target.core.controllers.c.b
        public final void onBannerCompleted(@NonNull ak akVar) {
            if (i.this.u == null || i.this.v != akVar || i.this.w == null) {
                return;
            }
            InstreamAudioAd.InstreamAudioAdListener listener = i.this.q.getListener();
            if (listener != null) {
                listener.onBannerComplete(i.this.q, i.this.w);
            }
            i.this.g();
        }

        @Override // com.my.target.core.controllers.c.b
        public final void onBannerProgressChanged(float f, float f2, @NonNull ak akVar) {
            InstreamAudioAd.InstreamAudioAdListener listener;
            if (i.this.u == null || i.this.v != akVar || i.this.w == null || (listener = i.this.q.getListener()) == null) {
                return;
            }
            listener.onBannerTimeLeftChange(f, f2, i.this.q);
        }

        @Override // com.my.target.core.controllers.c.b
        public final void onBannerError(@NonNull String str, @NonNull ak akVar) {
            if (i.this.u == null || i.this.v != akVar) {
                return;
            }
            InstreamAudioAd.InstreamAudioAdListener listener = i.this.q.getListener();
            if (listener != null) {
                listener.onError(str, i.this.q);
            }
            i.this.g();
        }
    }

    static /* synthetic */ void a(i iVar, am amVar, com.my.target.core.models.sections.f fVar, String str) {
        if (fVar == null) {
            if (str != null) {
                com.my.target.g.a("loading doAfter service failed: ".concat(String.valueOf(str)));
            }
            if (amVar == iVar.u) {
                iVar.b(amVar, iVar.z);
                return;
            }
            return;
        }
        am<AudioData> amVarA = fVar.a(amVar.getName());
        if (amVarA != null) {
            amVar.a(amVarA);
        }
        if (amVar == iVar.u) {
            iVar.y = amVar.R();
            iVar.g();
        }
    }

    static /* synthetic */ void a(i iVar, am amVar, com.my.target.core.models.sections.f fVar, String str, float f) {
        if (fVar == null) {
            if (str != null) {
                com.my.target.g.a("loading midpoint services failed: ".concat(String.valueOf(str)));
            }
            if (amVar == iVar.u && f == iVar.z) {
                iVar.b(amVar, f);
                return;
            }
            return;
        }
        am<AudioData> amVarA = fVar.a(amVar.getName());
        if (amVarA != null) {
            amVar.a(amVarA);
        }
        if (amVar == iVar.u && f == iVar.z) {
            iVar.a((am<AudioData>) amVar, f);
        }
    }
}
