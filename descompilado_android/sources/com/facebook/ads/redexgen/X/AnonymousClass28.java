package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.appevents.UserDataStore;
import com.ironsource.sdk.constants.Constants;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.28, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@SuppressLint({"HardcodedIPAddressUse"})
public abstract class AnonymousClass28 implements AnonymousClass06 {
    private static final String O;
    private static final Handler P;

    @SuppressLint({"StaticFieldLeak"})
    private static C0656Ks Q;
    private static C0T R;
    public final AnonymousClass25 B;
    public final JV C;
    public volatile boolean D;
    public final Context E;
    public View F;
    public C0P G;

    @Nullable
    public C0O H;
    public C0O I;
    private C0647Ki J;
    private final C0656Ks K;
    private final C0T L;
    private IP M;
    private long N = -1;

    public abstract void G(C0O c0o, IP ip, IN in, Map<String, Object> map);

    public abstract void M();

    static {
        MM.D();
        O = AnonymousClass28.class.getSimpleName();
        P = new Handler(Looper.getMainLooper());
    }

    @WorkerThread
    public AnonymousClass28(Context context, AnonymousClass25 adControllerConfig) {
        this.E = context.getApplicationContext();
        this.B = adControllerConfig;
        if (Q != null) {
            this.K = Q;
        } else {
            this.K = new C0656Ks(this.E);
        }
        this.K.C(this);
        if (R != null) {
            this.L = R;
        } else {
            this.L = new C0T();
        }
        try {
            CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.E);
            }
        } catch (Exception e) {
            Log.w(O, "Failed to initialize CookieManager.", e);
        }
        C0588Ia.G(this.E);
        this.C = JX.E(this.E);
    }

    public final void A(C0O adapter) {
        if (adapter != null) {
            adapter.onDestroy();
        }
    }

    public final long B() {
        if (this.M != null) {
            return this.M.C();
        }
        return -1L;
    }

    public final Handler C() {
        return P;
    }

    /* JADX INFO: renamed from: D, reason: collision with other method in class */
    public final IQ m226D() {
        if (this.M == null) {
            return null;
        }
        return this.M.E();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        this.H = null;
        IP ip = this.M;
        IN inG = ip.G();
        if (inG == null) {
            this.G.D(KQ.C(AdErrorType.NO_FILL, ""));
            return;
        }
        String strA = inG.A();
        C0O c0oA = this.L.A(ip.E().D());
        if (c0oA == null) {
            String adapterName = "Adapter does not exist: " + strA;
            Log.e(O, adapterName);
            H();
            return;
        }
        if (this.B.B() != c0oA.HC()) {
            this.G.D(KQ.C(AdErrorType.INTERNAL_ERROR, ""));
            return;
        }
        this.H = c0oA;
        IQ iqE = ip.E();
        HashMap map = new HashMap();
        map.put("data", inG.B());
        map.put("definition", iqE);
        map.put(Constants.PLACEMENT_ID, this.B.I);
        map.put("requestTime", Long.valueOf(iqE.G()));
        map.put("data_model_type", inG.C());
        if (this.J == null) {
            KQ error = KQ.C(AdErrorType.UNKNOWN_ERROR, "environment is empty");
            this.G.D(error);
        } else {
            G(c0oA, ip, inG, map);
        }
    }

    public final void E(@Nullable String bidPayload) {
        K(bidPayload);
    }

    public final boolean F() {
        return this.M == null || this.M.I();
    }

    public final synchronized void H() {
        P.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.2B
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    this.B.D();
                } catch (Exception e) {
                    C0585Hx.D(this.B.E, "api", C0584Hw.R, new C0583Hv(e));
                }
            }
        });
    }

    public final void I() {
        if (this.I == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("load_time_ms", C0622Jj.C(this.N));
        new C0618Jf(this.I.AC(), this.C).A(EnumC0617Je.AD_LOADED_CALLBACK, map);
    }

    public final void J(Map<String, Object> map) {
        Object obj = map.get("data");
        if (obj instanceof JSONObject) {
            String strOptString = ((JSONObject) obj).optString(UserDataStore.CITY);
            if (!TextUtils.isEmpty(strOptString)) {
                new C0618Jf(strOptString, this.C).A(EnumC0617Je.ADAPTER_TIMEOUT, null);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass06
    public final synchronized void JF(final C0659Kw serverResponseAds) {
        KQ kqR;
        if (JA.OB(this.E) && (kqR = R()) != null) {
            Log.e(AudienceNetworkAds.TAG, kqR.B());
            yD(kqR);
        } else {
            C().post(new Runnable() { // from class: com.facebook.ads.redexgen.X.2A
                @Override // java.lang.Runnable
                public final void run() {
                    IP ipC = serverResponseAds.C();
                    if (ipC != null && ipC.E() != null) {
                        this.B.M = ipC;
                        this.B.H();
                        return;
                    }
                    throw new IllegalStateException("invalid placement in response");
                }
            });
        }
    }

    @WorkerThread
    public void K(@Nullable String rawBidPayload) {
        this.N = System.currentTimeMillis();
        if (Build.VERSION.SDK_INT < 17) {
            yD(new KQ(AdErrorType.API_NOT_SUPPORTED, ""));
            return;
        }
        try {
            this.J = this.B.A(this.E, new C0640Kb(this.E, rawBidPayload, this.B.I, this.B.C));
            this.K.B(this.J);
        } catch (KR e) {
            yD(KQ.D(e));
        }
    }

    public final void L(C0P listener) {
        this.G = listener;
    }

    public final void N() {
        if (this.I == null) {
            C0585Hx.D(this.E, "api", C0584Hw.O, new C0583Hv("Adapter is null on startAd"));
            this.G.D(KQ.C(AdErrorType.INTERNAL_ERROR, AdErrorType.INTERNAL_ERROR.getDefaultErrorMessage()));
        } else if (this.D) {
            C0585Hx.D(this.E, "api", C0584Hw.M, new C0583Hv("ad already started"));
            this.G.D(KQ.C(AdErrorType.AD_ALREADY_STARTED, AdErrorType.AD_ALREADY_STARTED.getDefaultErrorMessage()));
        } else {
            if (!TextUtils.isEmpty(this.I.AC())) {
                this.C.uC(this.I.AC());
            }
            this.D = true;
            M();
        }
    }

    public final void O() {
        P(false);
    }

    public final void P(boolean destroyAd) {
        if (!destroyAd && !this.D) {
            return;
        }
        A(this.I);
        this.K.A();
        this.F = null;
        this.D = false;
    }

    public final boolean Q() {
        boolean z = true;
        if (!JA.Z(this.E)) {
            if (Build.VERSION.SDK_INT >= 24 && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted("127.0.0.1")) {
                z = false;
            }
            if (!z) {
                C0585Hx.D(this.E, "cache", C0584Hw.g, new C0583Hv("Cleartext http is not allowed."));
            }
        }
        return z;
    }

    @Nullable
    public KQ R() {
        EnumSet<CacheFlag> enumSet = this.B.E;
        if (enumSet == null || enumSet.contains(CacheFlag.NONE) || Q()) {
            return null;
        }
        return new KQ(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass06
    public final synchronized void yD(final KQ error) {
        C().post(new Runnable() { // from class: com.facebook.ads.redexgen.X.2C
            @Override // java.lang.Runnable
            public final void run() {
                this.B.G.D(error);
            }
        });
    }
}
