package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7G, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C7G extends C7H {
    public int B;
    private boolean C;
    private final AbstractC01210a<AnonymousClass76> D;
    private final AbstractC01210a<AnonymousClass72> E;
    private final AbstractC01210a<AnonymousClass71> F;
    private final GC G;
    private final AbstractC01210a<C03026z> H;
    private final AbstractC01210a<C03016y> I;
    private final AbstractC01210a<C03006x> J;
    private final AbstractC01210a<C02996w> K;
    private final GM L;
    private final AbstractC01210a<C02956s> M;
    private final AbstractC01210a<C02936q> N;
    private final C0447Co O;

    public C7G(Context context, JV adEventManager, C0447Co simpleVideoView, String clientToken) {
        this(context, adEventManager, simpleVideoView, new ArrayList(), clientToken);
    }

    public C7G(Context context, JV adEventManager, C0447Co simpleVideoView, String clientToken, @Nullable Bundle previousInstanceState) {
        this(context, adEventManager, simpleVideoView, new ArrayList(), clientToken, previousInstanceState, null);
    }

    public C7G(Context context, JV adEventManager, C0447Co simpleVideoView, String clientToken, @Nullable Map<String, String> extraParams) {
        this(context, adEventManager, simpleVideoView, new ArrayList(), clientToken, null, extraParams);
    }

    public C7G(Context context, JV adEventManager, C0447Co simpleVideoView, List<AbstractC01260f> list, String clientToken) {
        super(context, adEventManager, simpleVideoView, list, clientToken);
        this.L = new GM() { // from class: com.facebook.ads.redexgen.X.7Q
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02966t event) {
                this.B.E();
            }
        };
        this.J = new AbstractC01210a<C03006x>() { // from class: com.facebook.ads.redexgen.X.7P
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03006x> A() {
                return C03006x.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03006x event) {
                this.B.D();
            }
        };
        this.E = new AbstractC01210a<AnonymousClass72>() { // from class: com.facebook.ads.redexgen.X.7O
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass72> A() {
                return AnonymousClass72.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.B();
                this.B.J(event.A(), false, ((double) event.A()) < 2000.0d);
            }
        };
        this.F = new AbstractC01210a<AnonymousClass71>() { // from class: com.facebook.ads.redexgen.X.7N
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass71> A() {
                return AnonymousClass71.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                if (!this.B.C) {
                    this.B.C = true;
                } else {
                    this.B.C();
                }
            }
        };
        this.H = new AbstractC01210a<C03026z>() { // from class: com.facebook.ads.redexgen.X.7M
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03026z> A() {
                return C03026z.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03026z event) {
                int iA = event.A();
                if (this.B.B > 0 && iA == this.B.O.getDuration() && this.B.O.getDuration() > this.B.B) {
                    return;
                }
                this.B.I(iA);
            }
        };
        this.D = new AbstractC01210a<AnonymousClass76>() { // from class: com.facebook.ads.redexgen.X.7L
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass76> A() {
                return AnonymousClass76.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                int iA = event.A();
                int iB = event.B();
                if (this.B.B > 0 && iA == iB && iB > this.B.B) {
                    return;
                }
                if (iB < iA + 500) {
                    if (iB == 0) {
                        this.B.H(this.B.B);
                        return;
                    } else {
                        this.B.H(iB);
                        return;
                    }
                }
                this.B.H(iA);
            }
        };
        this.I = new AbstractC01210a<C03016y>() { // from class: com.facebook.ads.redexgen.X.7K
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03016y> A() {
                return C03016y.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03016y event) {
                this.B.K(event.A(), event.B());
            }
        };
        this.M = new AbstractC01210a<C02956s>() { // from class: com.facebook.ads.redexgen.X.7J
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02956s> A() {
                return C02956s.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02956s event) {
                this.B.F();
            }
        };
        this.N = new AbstractC01210a<C02936q>() { // from class: com.facebook.ads.redexgen.X.7I
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02936q> A() {
                return C02936q.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02936q event) {
                this.B.G();
            }
        };
        this.K = new AbstractC01210a<C02996w>() { // from class: com.facebook.ads.redexgen.X.7T
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02996w> A() {
                return C02996w.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02996w event) {
                this.B.K(this.B.A(), this.B.A());
            }
        };
        this.G = new GC() { // from class: com.facebook.ads.redexgen.X.7S
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass70 event) {
                this.B.B = this.B.O.getDuration();
            }
        };
        this.C = false;
        this.O = simpleVideoView;
        this.O.getEventBus().B(this.L, this.H, this.J, this.F, this.E, this.D, this.I, this.M, this.N, this.G, this.K);
    }

    public C7G(Context context, JV adEventManager, C0447Co simpleVideoView, List<AbstractC01260f> list, String clientToken, @Nullable Bundle previousInstanceState, @Nullable Map<String, String> map) {
        super(context, adEventManager, simpleVideoView, list, clientToken, previousInstanceState, map);
        this.L = new GM() { // from class: com.facebook.ads.redexgen.X.7Q
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02966t event) {
                this.B.E();
            }
        };
        this.J = new AbstractC01210a<C03006x>() { // from class: com.facebook.ads.redexgen.X.7P
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03006x> A() {
                return C03006x.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03006x event) {
                this.B.D();
            }
        };
        this.E = new AbstractC01210a<AnonymousClass72>() { // from class: com.facebook.ads.redexgen.X.7O
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass72> A() {
                return AnonymousClass72.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.B();
                this.B.J(event.A(), false, ((double) event.A()) < 2000.0d);
            }
        };
        this.F = new AbstractC01210a<AnonymousClass71>() { // from class: com.facebook.ads.redexgen.X.7N
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass71> A() {
                return AnonymousClass71.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                if (!this.B.C) {
                    this.B.C = true;
                } else {
                    this.B.C();
                }
            }
        };
        this.H = new AbstractC01210a<C03026z>() { // from class: com.facebook.ads.redexgen.X.7M
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03026z> A() {
                return C03026z.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03026z event) {
                int iA = event.A();
                if (this.B.B > 0 && iA == this.B.O.getDuration() && this.B.O.getDuration() > this.B.B) {
                    return;
                }
                this.B.I(iA);
            }
        };
        this.D = new AbstractC01210a<AnonymousClass76>() { // from class: com.facebook.ads.redexgen.X.7L
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<AnonymousClass76> A() {
                return AnonymousClass76.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                int iA = event.A();
                int iB = event.B();
                if (this.B.B > 0 && iA == iB && iB > this.B.B) {
                    return;
                }
                if (iB < iA + 500) {
                    if (iB == 0) {
                        this.B.H(this.B.B);
                        return;
                    } else {
                        this.B.H(iB);
                        return;
                    }
                }
                this.B.H(iA);
            }
        };
        this.I = new AbstractC01210a<C03016y>() { // from class: com.facebook.ads.redexgen.X.7K
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C03016y> A() {
                return C03016y.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C03016y event) {
                this.B.K(event.A(), event.B());
            }
        };
        this.M = new AbstractC01210a<C02956s>() { // from class: com.facebook.ads.redexgen.X.7J
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02956s> A() {
                return C02956s.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02956s event) {
                this.B.F();
            }
        };
        this.N = new AbstractC01210a<C02936q>() { // from class: com.facebook.ads.redexgen.X.7I
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02936q> A() {
                return C02936q.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02936q event) {
                this.B.G();
            }
        };
        this.K = new AbstractC01210a<C02996w>() { // from class: com.facebook.ads.redexgen.X.7T
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final Class<C02996w> A() {
                return C02996w.class;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(C02996w event) {
                this.B.K(this.B.A(), this.B.A());
            }
        };
        this.G = new GC() { // from class: com.facebook.ads.redexgen.X.7S
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass70 event) {
                this.B.B = this.B.O.getDuration();
            }
        };
        this.C = false;
        this.O = simpleVideoView;
        this.O.getEventBus().B(this.L, this.H, this.J, this.F, this.E, this.D, this.I, this.M, this.N, this.K);
    }

    public final void L() {
        this.O.getStateHandler().post(new Runnable() { // from class: com.facebook.ads.redexgen.X.7R
            @Override // java.lang.Runnable
            public final void run() {
                this.B.O.getEventBus().D(this.B.L, this.B.H, this.B.J, this.B.F, this.B.E, this.B.D, this.B.I, this.B.M, this.B.N, this.B.G, this.B.K);
            }
        });
    }
}
