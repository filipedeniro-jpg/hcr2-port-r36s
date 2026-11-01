package com.my.target.core.engines;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;
import com.my.target.aq;
import com.my.target.ar;
import com.my.target.cc;
import com.my.target.ci;
import com.my.target.cj;
import com.my.target.da;
import com.my.target.nativeads.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: NativeAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    @NonNull
    private final NativeAd G;

    @NonNull
    private final ArrayList<com.my.target.core.models.banners.b> H = new ArrayList<>();

    @NonNull
    private final RunnableC0094a I = new RunnableC0094a(this, 0);

    @NonNull
    private final cc J = cc.bg();

    @NonNull
    private final com.my.target.core.controllers.b K;

    @NonNull
    private final ci L;

    @NonNull
    private final da.a M;
    private boolean N;

    @NonNull
    private final com.my.target.core.models.banners.a v;

    @NonNull
    public static a a(@NonNull NativeAd nativeAd, @NonNull com.my.target.core.models.banners.a aVar) {
        return new a(nativeAd, aVar);
    }

    private a(@NonNull NativeAd nativeAd, @NonNull com.my.target.core.models.banners.a aVar) {
        this.G = nativeAd;
        this.v = aVar;
        this.K = com.my.target.core.controllers.b.a(aVar, new b(this));
        float viewabilityRate = aVar.getViewabilityRate();
        if (viewabilityRate == 1.0f) {
            this.L = ci.kd;
        } else {
            this.L = ci.i((int) (viewabilityRate * 1000.0f));
        }
        this.M = new c(this.I, this.L, this.K);
    }

    public final void a(@NonNull View view, @Nullable List<View> list, int i) {
        unregisterView();
        this.K.a(view, list, this.M, i);
        if (!this.N || this.K.w() == 1) {
            if (this.K.B() || this.K.v()) {
                this.L.d(this.I);
            }
        }
    }

    public final void unregisterView() {
        this.K.unregisterView();
        this.L.e(this.I);
    }

    final void C() {
        int[] iArrA;
        int iX = this.K.x();
        Context context = this.K.getContext();
        if (iX == -1 || context == null) {
            this.L.e(this.I);
            this.K.y();
            return;
        }
        if (this.N && this.K.w() != 1) {
            this.L.e(this.I);
            this.K.z();
            return;
        }
        if (iX == 1) {
            if (!this.N) {
                this.N = true;
                cj.a(this.v.getStatHolder().x(aq.a.du), context);
                NativeAd.NativeAdListener listener = this.G.getListener();
                if (listener != null) {
                    listener.onShow(this.G);
                }
                int iW = this.K.w();
                if ((iW == 2 || iW == 3) && (iArrA = this.K.A()) != null) {
                    for (int i : iArrA) {
                        com.my.target.core.models.banners.b bVar = this.v.getNativeAdCards().get(i);
                        if (this.N && !this.H.contains(bVar) && bVar != null) {
                            cj.a(bVar.getStatHolder().x(aq.a.du), context);
                            this.H.add(bVar);
                        }
                    }
                }
            }
            if (this.K.w() == 1) {
                this.K.a(true);
                return;
            } else {
                this.L.e(this.I);
                this.K.z();
                return;
            }
        }
        if (this.K.w() == 1) {
            this.K.a(false);
        }
    }

    final void a(@NonNull View view, @NonNull int[] iArr) {
        for (int i : iArr) {
            com.my.target.core.models.banners.b bVar = this.v.getNativeAdCards().get(i);
            if (this.N && !this.H.contains(bVar)) {
                if (bVar != null) {
                    ar statHolder = bVar.getStatHolder();
                    Context context = view.getContext();
                    if (context != null) {
                        cj.a(statHolder.x(aq.a.du), context);
                    }
                }
                this.H.add(bVar);
            }
        }
    }

    final void a(@NonNull View view, int i) {
        com.my.target.g.a("Click on native card received");
        List<com.my.target.core.models.banners.b> nativeAdCards = this.v.getNativeAdCards();
        if (i >= 0 && i < nativeAdCards.size()) {
            a(nativeAdCards.get(i), view);
        }
        ar statHolder = this.v.getStatHolder();
        Context context = view.getContext();
        if (context != null) {
            cj.a(statHolder.x(aq.a.dF), context);
        }
    }

    final void D() {
        NativeAd.NativeAdListener listener = this.G.getListener();
        if (listener != null) {
            listener.onVideoComplete(this.G);
        }
    }

    final void E() {
        NativeAd.NativeAdListener listener = this.G.getListener();
        if (listener != null) {
            listener.onVideoPause(this.G);
        }
    }

    final void F() {
        NativeAd.NativeAdListener listener = this.G.getListener();
        if (listener != null) {
            listener.onVideoPlay(this.G);
        }
    }

    final void d(@Nullable View view) {
        com.my.target.g.a("Click received by native ad");
        if (view != null) {
            a(this.v, view);
        }
    }

    private void a(@Nullable ai aiVar, @NonNull View view) {
        Context context;
        if (aiVar != null && (context = view.getContext()) != null) {
            this.J.a(aiVar, context);
        }
        NativeAd.NativeAdListener listener = this.G.getListener();
        if (listener != null) {
            listener.onClick(this.G);
        }
    }

    /* JADX INFO: renamed from: com.my.target.core.engines.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NativeAdEngine.java */
    private class RunnableC0094a implements Runnable {
        private RunnableC0094a() {
        }

        /* synthetic */ RunnableC0094a(a aVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            a.this.C();
        }
    }

    /* JADX INFO: compiled from: NativeAdEngine.java */
    public static class b implements com.my.target.core.controllers.b.a {

        @NonNull
        private final a P;

        b(@NonNull a aVar) {
            this.P = aVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(@Nullable View view) {
            this.P.d(view);
        }

        @Override // com.my.target.core.controllers.a.b
        public final void s() {
            this.P.F();
        }

        @Override // com.my.target.core.controllers.a.b
        public final void t() {
            this.P.E();
        }

        @Override // com.my.target.core.controllers.a.b
        public final void u() {
            this.P.D();
        }

        @Override // com.my.target.dd.a
        public final void b(@NonNull View view, int i) {
            this.P.a(view, i);
        }

        @Override // com.my.target.dd.a
        public final void b(@NonNull View view, @NonNull int[] iArr) {
            this.P.a(view, iArr);
        }
    }

    /* JADX INFO: compiled from: NativeAdEngine.java */
    private static class c implements da.a {

        @NonNull
        private final RunnableC0094a I;

        @NonNull
        private final com.my.target.core.controllers.b K;

        @NonNull
        private final ci L;

        c(@NonNull RunnableC0094a runnableC0094a, @NonNull ci ciVar, @NonNull com.my.target.core.controllers.b bVar) {
            this.I = runnableC0094a;
            this.L = ciVar;
            this.K = bVar;
        }

        @Override // com.my.target.da.a
        public final void b(boolean z) {
            if (z) {
                this.L.d(this.I);
            } else {
                this.K.a(false);
                this.L.e(this.I);
            }
        }
    }
}
