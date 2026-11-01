package com.my.target.core.engines;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.internal.AnalyticsEvents;
import com.my.target.ads.MyTargetView;
import com.my.target.ai;
import com.my.target.al;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.ck;
import com.my.target.df;
import com.my.target.dk;
import com.tonyodev.fetch.FetchService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: StandardAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    @NonNull
    private final MyTargetView a;

    @NonNull
    private final com.my.target.b adConfig;

    @NonNull
    private final com.my.target.c<com.my.target.core.models.sections.b> b;

    @NonNull
    private final com.my.target.core.presenters.c.a c;

    @NonNull
    private final Context context;

    @Nullable
    private com.my.target.core.presenters.c g;
    private boolean h;
    private long i;
    private long j;
    private boolean k = true;
    private int l = -1;

    @NonNull
    private final C0095b d = new C0095b(this);

    @NonNull
    private final d e = new d(this);

    @NonNull
    private final a f = new a();

    @NonNull
    public static b a(@NonNull MyTargetView myTargetView, @NonNull com.my.target.b bVar) {
        return new b(myTargetView, bVar);
    }

    private b(@NonNull MyTargetView myTargetView, @NonNull com.my.target.b bVar) {
        this.a = myTargetView;
        this.adConfig = bVar;
        this.context = myTargetView.getContext();
        this.c = new c(myTargetView);
        this.b = df.newFactory(bVar);
    }

    public final void a(boolean z) {
        this.f.setAttached(z);
        this.f.setFocused(this.a.hasWindowFocus());
        if (this.f.h()) {
            start();
        } else {
            if (z || !this.f.k()) {
                return;
            }
            stop();
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        this.f.setFocused(z);
        if (this.f.h()) {
            start();
        } else if (this.f.i()) {
            resume();
        } else if (this.f.canPause()) {
            pause();
        }
    }

    public final void a(@NonNull com.my.target.core.models.sections.b bVar) {
        com.my.target.core.presenters.d dVarB;
        com.my.target.core.presenters.a aVarA;
        if (this.f.k()) {
            stop();
        }
        this.h = bVar.E() && this.adConfig.isRefreshAd() && !this.adConfig.getFormat().equals(com.my.target.b.a.b);
        com.my.target.core.models.banners.c cVarG = bVar.G();
        if (cVarG != null) {
            this.l = cVarG.getTimeout() * 1000;
            this.i = System.currentTimeMillis() + ((long) this.l);
            this.j = 0L;
            if (this.h && this.f.isPaused()) {
                this.j = this.l;
            }
            if ("mraid".equals(cVarG.getType())) {
                final boolean z = this.k;
                if (this.a.getListener() != null) {
                    String format = this.adConfig.getFormat();
                    com.my.target.core.presenters.c cVar = this.g;
                    if (cVar instanceof com.my.target.core.presenters.a) {
                        aVarA = (com.my.target.core.presenters.a) cVar;
                    } else {
                        if (cVar != null) {
                            cVar.a(null);
                            this.g.destroy();
                        }
                        aVarA = com.my.target.core.presenters.a.a(this.a);
                        aVarA.a(this.c);
                        this.g = aVarA;
                        a(aVarA.I(), format);
                    }
                    aVarA.a(new com.my.target.core.presenters.a.c() { // from class: com.my.target.core.engines.b.1
                        @Override // com.my.target.core.presenters.a.c
                        public final void bk() {
                            MyTargetView.MyTargetViewListener listener;
                            if (z && (listener = b.this.a.getListener()) != null) {
                                listener.onLoad(b.this.a);
                            }
                            b.this.f.setFocused(b.this.a.hasWindowFocus());
                            b.this.f.c(true);
                            if (b.this.f.h()) {
                                b.this.start();
                            }
                        }

                        @Override // com.my.target.core.presenters.a.c
                        public final void onNoAd(@NonNull String str) {
                            MyTargetView.MyTargetViewListener listener;
                            if (z && (listener = b.this.a.getListener()) != null) {
                                listener.onNoAd(str, b.this.a);
                            }
                            b.this.f.c(false);
                        }

                        @Override // com.my.target.core.presenters.a.c
                        public final void pause() {
                            b.this.g();
                        }

                        @Override // com.my.target.core.presenters.a.c
                        public final void resume() {
                            b.this.f();
                        }

                        @Override // com.my.target.core.presenters.a.c
                        public final void a(@NonNull String str, @NonNull com.my.target.core.models.banners.c cVar2, @NonNull Context context) {
                            cj.a(cVar2.getStatHolder().x(str), context);
                        }

                        @Override // com.my.target.core.presenters.a.c
                        public final void a(float f, float f2, @NonNull com.my.target.core.models.banners.c cVar2, @NonNull Context context) {
                            Set<ap> setAd = cVar2.getStatHolder().ad();
                            if (setAd.isEmpty()) {
                                return;
                            }
                            ArrayList arrayList = new ArrayList();
                            for (ap apVar : setAd) {
                                float f3 = f2 - f;
                                float fY = apVar.Y();
                                if (fY < 0.0f && apVar.Z() >= 0.0f) {
                                    fY = (f2 / 100.0f) * apVar.Z();
                                }
                                if (fY >= 0.0f && fY < f3) {
                                    arrayList.add(apVar);
                                }
                            }
                            cj.a(arrayList, context);
                        }
                    });
                    aVarA.b(bVar);
                }
            } else if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(cVarG.getViewSettings().l())) {
                boolean z2 = this.k;
                MyTargetView.MyTargetViewListener listener = this.a.getListener();
                if (listener != null) {
                    String format2 = this.adConfig.getFormat();
                    com.my.target.core.presenters.c cVar2 = this.g;
                    if (cVar2 != null) {
                        cVar2.a(null);
                        this.g.destroy();
                    }
                    com.my.target.core.presenters.b bVarA = com.my.target.core.presenters.b.a(format2, this.context);
                    this.g = bVarA;
                    bVarA.a(this.c);
                    bVarA.b(bVar);
                    a(bVarA.I(), format2);
                    if (z2) {
                        listener.onLoad(this.a);
                    }
                    this.f.setFocused(this.a.hasWindowFocus());
                    this.f.c(true);
                    if (this.f.h()) {
                        start();
                    }
                }
            } else {
                final boolean z3 = this.k;
                if (this.a.getListener() != null) {
                    String format3 = this.adConfig.getFormat();
                    com.my.target.core.presenters.c cVar3 = this.g;
                    if (cVar3 instanceof com.my.target.core.presenters.d) {
                        dVarB = (com.my.target.core.presenters.d) cVar3;
                    } else {
                        if (cVar3 != null) {
                            cVar3.a(null);
                            this.g.destroy();
                        }
                        dVarB = com.my.target.core.presenters.d.b(format3, this.context);
                        dVarB.a(this.c);
                        this.g = dVarB;
                        a(dVarB.I(), format3);
                    }
                    dVarB.a(new com.my.target.core.presenters.d.a() { // from class: com.my.target.core.engines.b.2
                        @Override // com.my.target.core.presenters.d.a
                        public final void bk() {
                            MyTargetView.MyTargetViewListener listener2;
                            if (z3 && (listener2 = b.this.a.getListener()) != null) {
                                listener2.onLoad(b.this.a);
                            }
                            b.this.f.setFocused(b.this.a.hasWindowFocus());
                            b.this.f.c(true);
                            if (b.this.f.h()) {
                                b.this.start();
                            }
                        }

                        @Override // com.my.target.core.presenters.d.a
                        public final void onNoAd(@NonNull String str) {
                            MyTargetView.MyTargetViewListener listener2;
                            if (z3 && (listener2 = b.this.a.getListener()) != null) {
                                listener2.onNoAd(str, b.this.a);
                            }
                            b.this.f.c(false);
                        }
                    });
                    dVarB.b(bVar);
                }
            }
        }
        this.k = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start() {
        int i = this.l;
        if (i > 0 && this.h) {
            this.a.postDelayed(this.e, i);
        }
        com.my.target.core.presenters.c cVar = this.g;
        if (cVar != null) {
            cVar.start();
        }
        this.f.l(true);
    }

    private void resume() {
        if (this.j > 0 && this.h) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.j;
            this.i = jCurrentTimeMillis + j;
            this.a.postDelayed(this.e, j);
            this.j = 0L;
        }
        com.my.target.core.presenters.c cVar = this.g;
        if (cVar != null) {
            cVar.resume();
        }
        this.f.m(false);
    }

    private void pause() {
        this.a.removeCallbacks(this.e);
        if (this.h) {
            this.j = this.i - System.currentTimeMillis();
        }
        com.my.target.core.presenters.c cVar = this.g;
        if (cVar != null) {
            cVar.pause();
        }
        this.f.m(true);
    }

    private void stop() {
        this.f.l(false);
        this.a.removeCallbacks(this.e);
        com.my.target.core.presenters.c cVar = this.g;
        if (cVar != null) {
            cVar.stop();
        }
    }

    public final void destroy() {
        if (this.f.k()) {
            stop();
        }
        this.f.j();
        this.a.removeAllViews();
        com.my.target.core.presenters.c cVar = this.g;
        if (cVar != null) {
            cVar.destroy();
            this.g = null;
        }
    }

    @VisibleForTesting
    final void f() {
        this.f.d(false);
        if (this.f.i()) {
            resume();
        }
    }

    @VisibleForTesting
    final void g() {
        if (this.f.canPause()) {
            pause();
        }
        this.f.d(true);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002e  */
    private void a(@NonNull dk dkVar, @NonNull String str) {
        byte b;
        ck ckVarX = ck.x(this.a.getContext());
        int iHashCode = str.hashCode();
        if (iHashCode != -1476994234) {
            if (iHashCode == -1177968780 && str.equals(com.my.target.b.a.c)) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals(com.my.target.b.a.b)) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0) {
            dkVar.a(ckVarX.l(300), ckVarX.l(250));
        } else if (b == 1) {
            dkVar.a(ckVarX.l(728), ckVarX.l(90));
        } else {
            dkVar.a(ckVarX.l(FetchService.ACTION_LOGGING), ckVarX.l(50));
            dkVar.setFlexibleWidth(true);
            dkVar.setMaxWidth(ckVarX.l(640));
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        dkVar.setLayoutParams(layoutParams);
        this.a.removeAllViews();
        this.a.addView(dkVar);
    }

    /* JADX INFO: renamed from: com.my.target.core.engines.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: StandardAdEngine.java */
    private static class C0095b implements df.a {

        @NonNull
        private final WeakReference<b> u;

        @Override // com.my.target.c.b
        public final /* synthetic */ void b(@Nullable al alVar, @Nullable String str) {
            com.my.target.core.models.sections.b bVar = (com.my.target.core.models.sections.b) alVar;
            if (bVar != null) {
                b bVar2 = this.u.get();
                if (bVar2 != null) {
                    bVar2.a(bVar);
                    return;
                }
                return;
            }
            com.my.target.g.a("No new ad");
            b bVar3 = this.u.get();
            if (bVar3 != null) {
                b.d(bVar3);
            }
        }

        C0095b(@NonNull b bVar) {
            this.u = new WeakReference<>(bVar);
        }
    }

    /* JADX INFO: compiled from: StandardAdEngine.java */
    private static class d implements Runnable {

        @NonNull
        private final WeakReference<b> u;

        d(@NonNull b bVar) {
            this.u = new WeakReference<>(bVar);
        }

        @Override // java.lang.Runnable
        public final void run() {
            b bVar = this.u.get();
            if (bVar != null) {
                com.my.target.g.a("load new standard ad");
                bVar.b.a(bVar.d).a(bVar.context);
            }
        }
    }

    /* JADX INFO: compiled from: StandardAdEngine.java */
    private static class c implements com.my.target.core.presenters.c.a {

        @NonNull
        private final MyTargetView a;

        c(@NonNull MyTargetView myTargetView) {
            this.a = myTargetView;
        }

        @Override // com.my.target.core.presenters.c.a
        public final void a(@NonNull ai aiVar, @Nullable String str) {
            MyTargetView.MyTargetViewListener listener = this.a.getListener();
            if (listener != null) {
                listener.onClick(this.a);
            }
            cc ccVarBg = cc.bg();
            if (TextUtils.isEmpty(str)) {
                ccVarBg.a(aiVar, this.a.getContext());
            } else {
                ccVarBg.a(aiVar, str, this.a.getContext());
            }
        }

        @Override // com.my.target.core.presenters.c.a
        public final void a(@NonNull ai aiVar) {
            cj.a(aiVar.getStatHolder().x(aq.a.du), this.a.getContext());
        }
    }

    /* JADX INFO: compiled from: StandardAdEngine.java */
    @VisibleForTesting
    static class a {
        private boolean o;
        private boolean p;
        private boolean q;
        private boolean r;
        private boolean s;
        private boolean t;

        a() {
        }

        public final void m(boolean z) {
            this.p = z;
        }

        public final void setAttached(boolean z) {
            this.r = z;
        }

        public final void setFocused(boolean z) {
            this.s = z;
        }

        public final boolean h() {
            return this.r && this.q && this.s && !this.o;
        }

        public final boolean i() {
            return this.q && this.o && this.s && !this.t && this.p;
        }

        public final void l(boolean z) {
            this.o = z;
            this.p = false;
        }

        public final boolean canPause() {
            return (this.p || !this.o || this.s) ? false : true;
        }

        public final void j() {
            this.t = false;
            this.q = false;
        }

        public final void c(boolean z) {
            this.q = z;
        }

        public final void d(boolean z) {
            this.t = z;
        }

        public final boolean isPaused() {
            return this.p;
        }

        public final boolean k() {
            return this.o;
        }
    }

    static /* synthetic */ void d(b bVar) {
        if (bVar.l > 0) {
            bVar.a.removeCallbacks(bVar.e);
            bVar.a.postDelayed(bVar.e, bVar.l);
        }
    }
}
