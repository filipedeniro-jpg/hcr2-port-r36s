package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.07, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass07 implements AnonymousClass06 {
    private final JV B;
    private final C0656Ks C;

    @Nullable
    private final AdSize D;
    private final KW E;
    private final C0T F;
    private final Context G;
    private String H;
    private IP I;
    private AnonymousClass03 J;
    private final int K;
    private final String L;
    private boolean M;
    private final Handler N;
    private final Runnable O;

    static {
        MM.D();
    }

    public AnonymousClass07(Context context, String placementId, KW adTemplate, @Nullable AdSize adSize, int numAdRequested) {
        this.G = context;
        this.L = placementId;
        this.E = adTemplate;
        this.D = adSize;
        this.K = numAdRequested;
        this.C = new C0656Ks(this.G);
        this.C.C(this);
        this.F = new C0T();
        this.M = true;
        this.N = new Handler();
        this.O = new AnonymousClass04<AnonymousClass07>(this) { // from class: com.facebook.ads.redexgen.X.05
            {
                super(this);
            }

            @Override // java.lang.Runnable
            public final void run() {
                AnonymousClass07 anonymousClass07A = A();
                if (anonymousClass07A == null) {
                    return;
                }
                if (C0582Hu.B(anonymousClass07A.G)) {
                    anonymousClass07A.C();
                } else {
                    anonymousClass07A.N.postDelayed(anonymousClass07A.O, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
                }
            }
        };
        this.B = JX.E(this.G);
        C0588Ia.G(this.G);
    }

    public final void A() {
    }

    public final void B() {
        this.M = false;
        this.N.removeCallbacks(this.O);
    }

    public final void C() {
        M9 m9;
        try {
            C0640Kb c0640Kb = new C0640Kb(this.G, null, null, null);
            Context context = this.G;
            InterfaceC0596Ii interfaceC0596IiB = C0597Ij.B().B(this.G, false);
            String str = this.L;
            if (this.D != null) {
                m9 = new M9(this.D.getHeight(), this.D.getWidth());
            } else {
                m9 = null;
            }
            this.C.B(new C0647Ki(context, interfaceC0596IiB, str, m9, this.E, null, this.K, AdSettings.isTestMode(this.G), AdSettings.isChildDirected(), c0640Kb, L0.E(JA.f(this.G)), this.H));
        } catch (KR e) {
            yD(KQ.D(e));
        }
    }

    public final boolean D() {
        return this.I == null || this.I.I();
    }

    private List<C01360p> E() {
        IP ip = this.I;
        final ArrayList arrayList = new ArrayList(ip.H());
        for (IN inG = ip.G(); inG != null; inG = ip.G()) {
            C0O c0oA = this.F.A(AdPlacementType.NATIVE);
            if (c0oA != null && c0oA.HC() == AdPlacementType.NATIVE) {
                HashMap map = new HashMap();
                map.put("data", inG.B());
                map.put("definition", ip.E());
                final C01360p c01360p = (C01360p) c0oA;
                c01360p.a(this.G, new AnonymousClass01() { // from class: com.facebook.ads.redexgen.X.02
                    @Override // com.facebook.ads.redexgen.X.AnonymousClass01
                    public final void dE(C01360p adapter) {
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass01
                    public final void eE(C01360p adapter) {
                        arrayList.add(c01360p);
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass01
                    public final void fE(C01360p adapter) {
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass01
                    public final void hE(C01360p adapter, KQ error) {
                    }
                }, this.B, map, KF.c());
            }
        }
        return arrayList;
    }

    public final void E(String extraHints) {
        this.H = extraHints;
    }

    public final void F(AnonymousClass03 mListener) {
        this.J = mListener;
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass06
    @WorkerThread
    public final void JF(C0659Kw serverResponseAds) {
        IP ipC = serverResponseAds.C();
        if (ipC == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.M) {
            long jE = ipC.E().E();
            if (jE == 0) {
                jE = 1800000;
            }
            this.N.postDelayed(this.O, jE);
        }
        this.I = ipC;
        List<C01360p> listE = E();
        if (this.J != null) {
            if (listE.isEmpty()) {
                this.J.yD(KQ.C(AdErrorType.NO_FILL, ""));
            } else {
                this.J.gE(listE);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass06
    public final void yD(KQ err) {
        if (this.M) {
            this.N.postDelayed(this.O, 1800000L);
        }
        if (this.J != null) {
            this.J.yD(err);
        }
    }
}
