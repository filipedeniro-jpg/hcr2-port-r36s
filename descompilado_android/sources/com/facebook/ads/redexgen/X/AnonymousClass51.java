package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.51, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass51 implements AnonymousClass54 {
    private final AnonymousClass56 C;
    private Handler D;
    private EnumC02083j F;
    private Runnable G;
    private final C02254a H;
    private final AtomicBoolean E = new AtomicBoolean();
    private final HandlerThread B = new HandlerThread("bd_thread_handler", 10);

    @SuppressLint({"HandlerLeak"})
    public AnonymousClass51(AnonymousClass56 baseBdConfig, C02254a signalController) {
        this.C = baseBdConfig;
        this.H = signalController;
        this.B.start();
        this.D = new Handler(this.B.getLooper());
    }

    public final void A(final EnumC02083j reputationTier) {
        if (this.E.get() || reputationTier == null || !this.C.L()) {
            return;
        }
        this.F = reputationTier;
        this.G = new Runnable(reputationTier) { // from class: com.facebook.ads.redexgen.X.52
            private EnumC02083j C;

            {
                this.C = reputationTier;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.B.H.B(EnumC02063h.INTERVAL, this.C, null);
                if (this.B.C.H() > Math.random()) {
                    C3O.C(this.B.C, EnumC02504z.INFO, EnumC02494y.TIMER_CALLED.A(), null, null, null);
                }
                this.B.D.postDelayed(this.B.G, TimeUnit.SECONDS.toMillis(this.B.C.I()));
            }
        };
        this.H.A(reputationTier);
        this.D.post(this.G);
        this.E.compareAndSet(false, true);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass54
    public final void AG(EnumC02083j reputationTier) {
        if (this.F == reputationTier) {
            return;
        }
        this.F = reputationTier;
        if (reputationTier == EnumC02083j.BENIGN_IGNORE) {
            F();
        } else {
            A(reputationTier);
        }
    }

    private final void F() {
        if (this.G != null && this.E.get()) {
            this.D.removeCallbacks(this.G);
            this.H.D();
            this.E.compareAndSet(true, false);
        }
    }
}
