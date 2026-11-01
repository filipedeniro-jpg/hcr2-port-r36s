package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ki, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0647Ki {
    private KT B;
    private final AdPlacementType C;
    private M9 D;
    private KW E;
    private final C0640Kb F;
    private Context G;

    @Nullable
    private String H;
    private boolean I;
    private boolean J;
    private int K;
    private String L;

    @Nullable
    private String M;
    private final Map<String, String> N;

    @Nullable
    private final String O;

    @WorkerThread
    public C0647Ki(Context context, InterfaceC0596Ii staticEnv, String placementId, M9 adSize, KW adTemplate, @Nullable String testAdType, int numAdRequested, boolean testMode, boolean isChildDirected, C0640Kb bidPayload, @Nullable String stackTrace, @Nullable String extraHints) {
        this.G = context;
        this.N = staticEnv.CC();
        this.L = placementId;
        this.D = adSize;
        this.E = adTemplate;
        this.O = testAdType;
        this.K = numAdRequested;
        this.J = testMode;
        this.I = isChildDirected;
        this.F = bidPayload;
        this.B = KT.B(adTemplate);
        this.C = this.B.A();
        this.M = stackTrace;
        this.H = extraHints;
    }

    public final KT A() {
        return this.B;
    }

    public final M9 B() {
        return this.D;
    }

    private void B(Map<String, String> params, String key, String value) {
        params.put(key, value);
    }

    public final KW C() {
        return this.E;
    }

    public final C0640Kb D() {
        return this.F;
    }

    public final int E() {
        return this.K;
    }

    public final String F() {
        return this.L;
    }

    public final Map<String, String> G() {
        Map<String, String> params = new HashMap<>(this.N);
        B(params, "IDFA", C0551Gp.B);
        B(params, "IDFA_FLAG", C0551Gp.E ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "1");
        B(params, "COPPA", String.valueOf(this.I));
        B(params, "PLACEMENT_ID", this.L);
        if (this.C != AdPlacementType.UNKNOWN) {
            B(params, "PLACEMENT_TYPE", this.C.toString().toLowerCase());
        }
        if (this.D != null) {
            B(params, "WIDTH", String.valueOf(this.D.B()));
            B(params, "HEIGHT", String.valueOf(this.D.A()));
        }
        if (this.E != null) {
            B(params, "TEMPLATE_ID", String.valueOf(this.E.A()));
        }
        if (this.J) {
            B(params, "TEST_MODE", "1");
        }
        if (this.O != null) {
            B(params, "DEMO_AD_ID", this.O);
        }
        if (this.K != 0) {
            B(params, "NUM_ADS_REQUESTED", String.valueOf(this.K));
        }
        B(params, "KG_RESTRICTED", String.valueOf(J4.B(this.G)));
        B(params, "REQUEST_TIME", C0622Jj.D(System.currentTimeMillis()));
        if (this.F.D()) {
            B(params, "BID_ID", this.F.A());
        }
        String strB = this.F.B();
        if (!TextUtils.isEmpty(strB)) {
            B(params, "BID_TIME_TOKEN", strB);
        }
        if (this.M != null) {
            B(params, "STACK_TRACE", this.M);
        }
        B(params, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        B(params, "AD_REPORTING_CONFIG_LAST_UPDATE_TIME", C0622Jj.F(C01972y.I(this.G)));
        if (this.H != null) {
            B(params, "EXTRA_HINTS", this.H);
        }
        return params;
    }
}
