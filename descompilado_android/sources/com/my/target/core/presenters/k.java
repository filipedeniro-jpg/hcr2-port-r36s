package com.my.target.core.presenters;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.ak;
import com.my.target.common.models.VideoData;
import com.my.target.ed;
import com.my.target.eg;
import com.my.target.eh;
import com.my.target.el;
import com.my.target.ep;
import java.util.List;

/* JADX INFO: compiled from: InterstitialPromoPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k implements j {
    private long H;
    private long I;

    @NonNull
    private final d an;

    @NonNull
    private final eh ao;

    @Nullable
    private eg ap;

    @Nullable
    private ep aq;

    @Nullable
    private e ar;

    @Nullable
    private h as;

    @Nullable
    private b at;

    @NonNull
    private final com.my.target.core.models.banners.i j;

    /* JADX INFO: compiled from: InterstitialPromoPresenter.java */
    public interface b extends j.a {
        void i();

        void j();
    }

    @NonNull
    public static k a(@NonNull com.my.target.core.models.banners.i iVar, boolean z, @NonNull Context context) {
        return new k(iVar, z, context);
    }

    @Nullable
    public final b E() {
        return this.at;
    }

    @Nullable
    @VisibleForTesting
    final h F() {
        return this.as;
    }

    @VisibleForTesting
    final void G() {
        this.as = null;
    }

    @NonNull
    public final com.my.target.core.models.banners.i H() {
        return this.j;
    }

    public final void b(@Nullable b bVar) {
        this.at = bVar;
        h hVar = this.as;
        if (hVar != null) {
            hVar.a(bVar);
        }
        e eVar = this.ar;
        if (eVar != null) {
            eVar.a(bVar);
        }
    }

    public final boolean x() {
        h hVar = this.as;
        return hVar == null || hVar.x();
    }

    public final void a(boolean z) {
        h hVar = this.as;
        if (hVar != null) {
            hVar.a(z);
        }
    }

    private k(@NonNull com.my.target.core.models.banners.i iVar, boolean z, @NonNull Context context) {
        ep epVar;
        this.j = iVar;
        c cVar = new c(this);
        ak<VideoData> videoBanner = iVar.getVideoBanner();
        if (!iVar.getInterstitialAdCards().isEmpty()) {
            ep epVar2 = new ep(context);
            this.aq = epVar2;
            this.ao = epVar2;
        } else if (videoBanner != null && iVar.getStyle() == 1) {
            el elVar = new el(context, z);
            this.ap = elVar;
            this.ao = elVar;
        } else {
            ed edVar = new ed(context, z);
            this.ap = edVar;
            this.ao = edVar;
        }
        this.an = new d(this.ao);
        this.ao.setInterstitialPromoViewListener(cVar);
        this.ao.setBanner(iVar);
        this.ao.getCloseButton().setOnClickListener(new a(this));
        this.ao.setClickArea(iVar.getClickArea());
        eg egVar = this.ap;
        if (egVar != null && videoBanner != null) {
            this.as = h.a(videoBanner, egVar);
            this.as.a(videoBanner, context);
            if (videoBanner.isAutoPlay()) {
                this.H = 0L;
            }
        }
        if (videoBanner == null || !videoBanner.isAutoPlay()) {
            this.I = (long) (iVar.getAllowCloseDelay() * 1000.0f);
            if (this.I > 0) {
                com.my.target.g.a("banner will be allowed to close in " + this.I + " millis");
                a(this.I);
            } else {
                com.my.target.g.a("banner is allowed to close");
                this.ao.N();
            }
        }
        List<com.my.target.core.models.banners.f> interstitialAdCards = iVar.getInterstitialAdCards();
        if (interstitialAdCards.isEmpty() || (epVar = this.aq) == null) {
            return;
        }
        this.ar = e.a(interstitialAdCards, epVar);
    }

    @Override // com.my.target.core.presenters.j
    public final void pause() {
        h hVar = this.as;
        if (hVar != null) {
            hVar.pause();
        }
        this.ao.getView().removeCallbacks(this.an);
        if (this.H > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.H;
            if (jCurrentTimeMillis > 0) {
                long j = this.I;
                if (jCurrentTimeMillis < j) {
                    this.I = j - jCurrentTimeMillis;
                    return;
                }
            }
            this.I = 0L;
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void resume() {
        if (this.as == null) {
            long j = this.I;
            if (j > 0) {
                a(j);
            }
        }
    }

    @Override // com.my.target.core.presenters.j
    @NonNull
    public final View v() {
        return this.ao.getView();
    }

    @Override // com.my.target.core.presenters.j
    public final void stop() {
        h hVar = this.as;
        if (hVar != null) {
            hVar.stop();
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void destroy() {
        h hVar = this.as;
        if (hVar != null) {
            hVar.destroy();
        }
    }

    public final void I() {
        h hVar = this.as;
        if (hVar != null) {
            hVar.a(this.j);
        }
    }

    private void a(long j) {
        eg egVar = this.ap;
        if (egVar != null) {
            egVar.getView().removeCallbacks(this.an);
            this.H = System.currentTimeMillis();
            this.ap.getView().postDelayed(this.an, j);
        }
    }

    /* JADX INFO: compiled from: InterstitialPromoPresenter.java */
    private static class a implements View.OnClickListener {
        private k au;

        a(k kVar) {
            this.au = kVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            h hVarF = this.au.F();
            if (hVarF != null) {
                hVarF.onCloseClicked();
            }
            b bVarE = this.au.E();
            if (bVarE != null) {
                bVarE.bf();
            }
        }
    }

    /* JADX INFO: compiled from: InterstitialPromoPresenter.java */
    private static class c implements eh.a {

        @NonNull
        private final k au;

        c(@NonNull k kVar) {
            this.au = kVar;
        }

        @Override // com.my.target.eh.a
        public final void c(boolean z) {
            if (z) {
                return;
            }
            h hVarF = this.au.F();
            if (hVarF != null) {
                hVarF.a(this.au.H());
                hVarF.destroy();
            }
            this.au.G();
        }

        @Override // com.my.target.eh.a
        public final void K() {
            b bVarE = this.au.E();
            if (bVarE != null) {
                bVarE.a(this.au.H(), null, this.au.v().getContext());
            }
        }
    }

    /* JADX INFO: compiled from: InterstitialPromoPresenter.java */
    private static class d implements Runnable {

        @NonNull
        private final eh y;

        d(@NonNull eh ehVar) {
            this.y = ehVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.my.target.g.a("banner became just closeable");
            this.y.N();
        }
    }
}
