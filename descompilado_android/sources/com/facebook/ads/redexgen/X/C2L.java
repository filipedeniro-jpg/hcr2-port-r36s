package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2L extends AnonymousClass28 {
    public C2L(Context context, AnonymousClass25 adControllerConfig) {
        super(context, adControllerConfig);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void G(C0O adAdapter, IP placement, final IN placementAd, final Map<String, Object> map) {
        final C01360p c01360p = (C01360p) adAdapter;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final Runnable runnable = new Runnable() { // from class: com.facebook.ads.redexgen.X.2J
            @Override // java.lang.Runnable
            public final void run() {
                this.B.J(map);
                this.B.A(c01360p);
                Map mapH = this.B.H(jCurrentTimeMillis);
                mapH.put("error", "-1");
                mapH.put(NotificationCompat.CATEGORY_MESSAGE, "timeout");
                this.B.G(placementAd.D(IR.REQUEST), mapH);
                this.B.H();
            }
        };
        C().postDelayed(runnable, placement.E().H());
        c01360p.a(this.E, new AnonymousClass01() { // from class: com.facebook.ads.redexgen.X.2K
            public boolean D = false;
            public boolean C = false;
            public boolean B = false;

            @Override // com.facebook.ads.redexgen.X.AnonymousClass01
            public final void dE(C01360p adapter) {
                if (!this.B) {
                    this.B = true;
                    this.E.G(placementAd.D(IR.CLICK), null);
                }
                if (this.E.G != null) {
                    this.E.G.A();
                }
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass01
            public final void eE(C01360p adapter) {
                if (adapter != this.E.H) {
                    return;
                }
                this.E.C().removeCallbacks(runnable);
                this.E.I = adapter;
                super/*com.facebook.ads.redexgen.X.28*/.I();
                this.E.G.B(adapter);
                if (this.D) {
                    return;
                }
                this.D = true;
                this.E.G(placementAd.D(IR.REQUEST), this.E.H(jCurrentTimeMillis));
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass01
            public final void fE(C01360p adapter) {
                if (!this.C) {
                    this.C = true;
                    this.E.G(placementAd.D(IR.IMPRESSION), null);
                }
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass01
            public final void hE(C01360p adapter, KQ error) {
                if (adapter != this.E.H) {
                    return;
                }
                this.E.C().removeCallbacks(runnable);
                this.E.A(adapter);
                if (!this.D) {
                    this.D = true;
                    Map mapH = this.E.H(jCurrentTimeMillis);
                    mapH.put("error", String.valueOf(error.A().getErrorCode()));
                    mapH.put(NotificationCompat.CATEGORY_MESSAGE, String.valueOf(error.B()));
                    this.E.G(placementAd.D(IR.REQUEST), mapH);
                }
                this.E.H();
            }
        }, this.C, map, KF.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(List<String> list, Map<String, String> map) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            new I9(this.E, map).execute(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> H(long requestTime) {
        HashMap map = new HashMap();
        map.put("delay", String.valueOf(System.currentTimeMillis() - requestTime));
        return map;
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void K(String rawBidPayload) {
        KQ kqD = C2F.D(this.E, 0, 1);
        if (kqD != null) {
            yD(kqD);
        } else {
            super.K(rawBidPayload);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void M() {
        C01360p c01360p = (C01360p) this.I;
        if (!c01360p.W()) {
            throw new IllegalStateException("ad is not ready or already displayed");
        }
        this.G.J(c01360p);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    @Nullable
    public final KQ R() {
        if (this.B.J == null || this.B.J == K0.NONE || Q()) {
            return null;
        }
        return new KQ(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
