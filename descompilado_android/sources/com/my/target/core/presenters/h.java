package com.my.target.core.presenters;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ak;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.cj;
import com.my.target.cm;
import com.my.target.eg;
import com.my.target.ej;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: InterstitialMediaPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {

    @Nullable
    private k.b A;

    @NonNull
    private final eg P;

    @NonNull
    private final cm Q;
    private boolean R;

    @Nullable
    private Set<ap> T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean Y;
    private boolean allowClose;
    private float duration;

    @NonNull
    private final ak videoBanner;
    private boolean S = true;

    @NonNull
    private final a O = new a();

    static /* synthetic */ boolean j(h hVar) {
        hVar.S = false;
        return false;
    }

    static /* synthetic */ boolean l(h hVar) {
        hVar.V = true;
        return true;
    }

    static /* synthetic */ boolean q(h hVar) {
        hVar.W = true;
        return true;
    }

    @NonNull
    public static h a(@NonNull ak akVar, @NonNull eg egVar) {
        return new h(akVar, egVar);
    }

    private h(@NonNull ak akVar, @NonNull eg egVar) {
        this.videoBanner = akVar;
        this.P = egVar;
        egVar.setMediaListener(this.O);
        this.Q = cm.a(akVar.getStatHolder());
        this.Q.setView(egVar.getPromoMediaView());
    }

    public final void a(@NonNull ak akVar, @NonNull Context context) {
        this.W = akVar.isAllowBackButton();
        this.allowClose = akVar.isAllowClose();
        if (this.allowClose && akVar.getAllowCloseDelay() == 0.0f && akVar.isAutoPlay()) {
            com.my.target.g.a("banner is allowed to close");
            this.P.N();
        }
        this.duration = akVar.getDuration();
        this.R = akVar.isAutoMute();
        if (this.R) {
            this.P.e(0);
            return;
        }
        if (akVar.isAutoPlay()) {
            d(context);
        }
        this.P.e(2);
    }

    public final void a(com.my.target.core.models.banners.i iVar) {
        this.P.N();
        this.P.a(iVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bv() {
        if (this.P.isPlaying()) {
            d(this.P.getView().getContext());
        }
        this.P.e(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(@NonNull Context context) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus(this.O, 3, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(@NonNull Context context) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus(this.O);
        }
    }

    public final void stop() {
        e(this.P.getView().getContext());
    }

    public final void destroy() {
        e(this.P.getView().getContext());
        this.P.destroy();
    }

    public final void pause() {
        this.P.pause();
        e(this.P.getView().getContext());
        if (!this.P.isPlaying() || this.P.isPaused()) {
            return;
        }
        cj.a(this.videoBanner.getStatHolder().x(aq.a.dv), this.P.getView().getContext());
    }

    public final void onCloseClicked() {
        this.P.stop(true);
        e(this.P.getView().getContext());
        if (this.V) {
            cj.a(this.videoBanner.getStatHolder().x(aq.a.dy), this.P.getView().getContext());
        }
    }

    public final void a(boolean z) {
        this.U = z;
    }

    public final boolean x() {
        return this.W;
    }

    public final void a(@Nullable k.b bVar) {
        this.A = bVar;
    }

    /* JADX INFO: compiled from: InterstitialMediaPresenter.java */
    public class a implements ej.a {
        @Override // com.my.target.co.a
        public final void bA() {
        }

        @Override // com.my.target.co.a
        public final void bw() {
        }

        @Override // com.my.target.co.a
        public final void bz() {
        }

        public a() {
        }

        @Override // com.my.target.ej.a
        public final void y() {
            if (!h.this.R) {
                h hVar = h.this;
                hVar.d(hVar.P.getView().getContext());
            }
            h.this.P.play();
        }

        public final void z() {
            if (h.this.R) {
                h.this.bv();
                cj.a(h.this.videoBanner.getStatHolder().x(aq.a.dC), h.this.P.getView().getContext());
                h.this.R = false;
            } else {
                h.c(h.this);
                cj.a(h.this.videoBanner.getStatHolder().x(aq.a.dD), h.this.P.getView().getContext());
                h.this.R = true;
            }
        }

        @Override // com.my.target.ej.a
        public final void A() {
            h hVar = h.this;
            hVar.e(hVar.P.getView().getContext());
            cj.a(h.this.videoBanner.getStatHolder().x(aq.a.dv), h.this.P.getView().getContext());
            h.this.P.pause();
        }

        @Override // com.my.target.ej.a
        public final void B() {
            cj.a(h.this.videoBanner.getStatHolder().x(aq.a.dw), h.this.P.getView().getContext());
            h.this.P.resume();
            if (!h.this.R) {
                h.this.bv();
            } else {
                h.c(h.this);
            }
        }

        @Override // com.my.target.co.a
        public final void f(float f) {
            h.this.P.e(f <= 0.0f);
        }

        @Override // com.my.target.co.a
        public final void bx() {
            if (h.this.allowClose && h.this.videoBanner.getAllowCloseDelay() == 0.0f) {
                h.this.P.N();
            }
            h.this.P.O();
        }

        @Override // com.my.target.co.a
        public final void by() {
            if (h.this.U) {
                h.this.P.pause();
            }
        }

        @Override // com.my.target.co.a
        public final void b(float f, float f2) {
            while (true) {
                h.this.P.setTimeChanged(f);
                h.this.Y = false;
                if (h.this.S) {
                    h.i(h.this);
                    cj.a(h.this.videoBanner.getStatHolder().x(aq.a.du), h.this.P.getView().getContext());
                    h.a(h.this, 0.0f);
                    h.j(h.this);
                }
                if (!h.this.V) {
                    h.l(h.this);
                }
                if (h.this.allowClose && h.this.videoBanner.isAutoPlay() && h.this.videoBanner.getAllowCloseDelay() <= f) {
                    h.this.P.N();
                }
                if (f <= h.this.duration) {
                    break;
                } else {
                    f = h.this.duration;
                }
            }
            if (f != 0.0f) {
                h.a(h.this, f);
            }
            if (f == h.this.duration) {
                onComplete();
            }
        }

        @Override // com.my.target.co.a
        public final void W(String str) {
            com.my.target.g.a("Video playing error: ".concat(String.valueOf(str)));
            h.n(h.this);
            if (h.this.A != null) {
                h.this.A.j();
            }
        }

        @Override // com.my.target.co.a
        public final void onComplete() {
            if (h.this.Y) {
                return;
            }
            h.this.Y = true;
            com.my.target.g.a("Video playing complete:");
            h.q(h.this);
            h.n(h.this);
            if (h.this.A != null) {
                h.this.A.i();
            }
            h.this.P.N();
            h.this.P.finish();
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(final int i) {
            boolean zIsCurrentThread;
            if (Build.VERSION.SDK_INT >= 23) {
                zIsCurrentThread = Looper.getMainLooper().isCurrentThread();
            } else {
                zIsCurrentThread = Thread.currentThread() == Looper.getMainLooper().getThread();
            }
            if (zIsCurrentThread) {
                h.a(h.this, i);
            } else {
                com.my.target.h.c(new Runnable() { // from class: com.my.target.core.presenters.h.a.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        h.a(h.this, i);
                    }
                });
            }
        }
    }

    static /* synthetic */ void c(h hVar) {
        hVar.e(hVar.P.getView().getContext());
        hVar.P.e(0);
    }

    static /* synthetic */ void i(h hVar) {
        Set<ap> set = hVar.T;
        if (set != null) {
            set.clear();
        }
        hVar.T = hVar.videoBanner.getStatHolder().ad();
    }

    static /* synthetic */ void a(h hVar, float f) {
        hVar.Q.e(f);
        Set<ap> set = hVar.T;
        if (set == null || set.isEmpty()) {
            return;
        }
        Iterator<ap> it = hVar.T.iterator();
        while (it.hasNext()) {
            ap next = it.next();
            if (next.Y() <= f) {
                cj.a(next, hVar.P.getView().getContext());
                it.remove();
            }
        }
    }

    static /* synthetic */ void n(h hVar) {
        hVar.S = true;
        hVar.P.N();
        hVar.e(hVar.P.getView().getContext());
        hVar.P.stop(hVar.videoBanner.isAllowReplay());
    }

    static /* synthetic */ void a(h hVar, int i) {
        if (i == -3) {
            com.my.target.g.a("Audiofocus loss can duck, set volume to 0.3");
            if (hVar.R) {
                return;
            }
            hVar.P.e(1);
            return;
        }
        if (i == -2 || i == -1) {
            hVar.pause();
            com.my.target.g.a("Audiofocus loss, pausing");
        } else if (i == 1 || i == 2 || i == 4) {
            com.my.target.g.a("Audiofocus gain, unmuting");
            if (hVar.R) {
                return;
            }
            hVar.bv();
        }
    }
}
