package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ks, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C0656Ks {

    @Nullable
    private static InterfaceC0654Kq J;
    private C0647Ki B;
    private Map<String, String> C;
    private final Context D;
    private LI F;
    private AnonymousClass06 G;
    private static final ThreadFactoryC0664Lb K = new ThreadFactoryC0664Lb();
    private static final Executor I = Executors.newCachedThreadPool(K);
    private final C0657Kt H = C0657Kt.B();
    private final String E = C0661Ky.B();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:125)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @WorkerThread
    public void S(String str, long j) {
        try {
            C0658Kv c0658KvA = this.H.A(this.D, str, j);
            IP ipC = c0658KvA.C();
            if (ipC != null) {
                JA.Y(this.D).E(ipC.F());
                if (I6.D) {
                    if (JA.L(this.D)) {
                        IF.B(this.D);
                    } else {
                        IF.C(this.D);
                    }
                }
                C01972y.P(this.D, ipC.B());
                C0646Kh.C(ipC.E().F(), this.B);
                I0.B(this.D, I, ipC);
                C0583Hv c0583Hv = new C0583Hv(String.valueOf(MY.C()));
                c0583Hv.D(1);
                C0585Hx.F(this.D, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.NB, c0583Hv);
            }
            switch (c0658KvA.B()) {
                case ADS:
                    C0659Kw c0659Kw = (C0659Kw) c0658KvA;
                    if (ipC != null) {
                        if (ipC.E().A()) {
                            C0646Kh.D(str, this.B);
                        }
                        String str2 = this.C != null ? this.C.get("CLIENT_REQUEST_ID") : null;
                        if (!TextUtils.isEmpty(c0658KvA.A()) && !TextUtils.isEmpty(str2)) {
                            new J8(this.D, str2, c0658KvA.A()).A();
                        }
                    }
                    Y(c0659Kw);
                    break;
                case ERROR:
                    C0660Kx c0660Kx = (C0660Kx) c0658KvA;
                    String strE = c0660Kx.E();
                    AdErrorType adErrorTypeAdErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(c0660Kx.D(), AdErrorType.ERROR_MESSAGE);
                    X(c0660Kx.D(), strE);
                    if (strE == null) {
                        strE = str;
                    }
                    Z(KQ.C(adErrorTypeAdErrorTypeFromCode, strE));
                    break;
                default:
                    Z(KQ.C(AdErrorType.UNKNOWN_RESPONSE, str));
                    break;
            }
        } catch (Exception e) {
            Z(KQ.C(AdErrorType.PARSER_FAILURE, e.getMessage()));
        }
    }

    public C0656Ks(Context context) {
        this.D = context.getApplicationContext();
        C0611Iy.C(context);
    }

    public final void A() {
        if (this.F != null) {
            this.F.G(1);
            this.F.H(1);
            this.F = null;
        }
    }

    public final void B(C0647Ki mAdEnvironmentData) {
        T(mAdEnvironmentData, false);
    }

    public final void C(AnonymousClass06 mListener) {
        this.G = mListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void Q(C0659Kw serverResponseAds) {
        if (this.G != null) {
            this.G.JF(serverResponseAds);
        }
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void R(KQ err) {
        if (this.G != null) {
            this.G.yD(err);
        }
        A();
    }

    private final void T(C0647Ki mAdEnvironmentData, boolean isForCache) {
        C0655Kr c0655KrFetch;
        A();
        if (!isForCache && J != null && (c0655KrFetch = J.fetch(this, mAdEnvironmentData)) != null) {
            if (c0655KrFetch.B != null) {
                Y(c0655KrFetch.B);
                return;
            } else if (c0655KrFetch.C != null) {
                Z(c0655KrFetch.C);
                return;
            }
        }
        if (KN.B(this.D) == KO.NONE) {
            W();
            Z(new KQ(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.B = mAdEnvironmentData;
        IK.H(this.D);
        if (C0646Kh.F(mAdEnvironmentData)) {
            String strB = C0646Kh.B(mAdEnvironmentData);
            if (strB != null) {
                U(strB, 0L);
                return;
            } else {
                Z(KQ.C(AdErrorType.LOAD_TOO_FREQUENTLY, null));
                return;
            }
        }
        I.execute(new RunnableC0648Kj(this, mAdEnvironmentData, isForCache));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AnyThread
    public void U(String response, long requestTime) {
        I.execute(new RunnableC0649Kk(this, response, requestTime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbstractC0650Kl V(long requestTimeMs) {
        return new C0651Km(this, requestTimeMs);
    }

    private void W() {
        if (this.D == null || !IB.E(this.D)) {
            return;
        }
        C0583Hv c0583Hv = new C0583Hv("ARPLN");
        c0583Hv.D(1);
        C0585Hx.D(this.D, "network", C0584Hw.cB, c0583Hv);
    }

    @AnyThread
    private void X(int i, String str) {
        C0642Kd.B("noFillHook", "NO FILL received");
        C0642Kd.B("noFillHook", String.format(Locale.US, "No Fill error code [%s] %s", Integer.valueOf(i), str));
    }

    @AnyThread
    private void Y(C0659Kw serverResponseAds) {
        ExecutorC0616Jd.B(new RunnableC0652Kn(this, serverResponseAds));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AnyThread
    public void Z(KQ err) {
        ExecutorC0616Jd.B(new RunnableC0653Ko(this, err));
    }
}
