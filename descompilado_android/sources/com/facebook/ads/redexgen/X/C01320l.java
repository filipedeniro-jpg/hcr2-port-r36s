package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.appevents.UserDataStore;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.exoplayer2.util.MimeTypes;
import com.my.target.be;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0l, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01320l extends AbstractC01310k implements InterfaceC01300j<Bundle> {
    public static final /* synthetic */ boolean Q;

    @Nullable
    public JV B;

    @Nullable
    public Context C;

    @Nullable
    private C7H E;

    @Nullable
    private JSONObject F;
    private H2 G;

    @Nullable
    private String H;

    @Nullable
    private AnonymousClass11 J;

    @Nullable
    private C0447Co O;

    @Nullable
    private String P;
    private final AbstractC01210a<AnonymousClass76> L = new AbstractC01210a<AnonymousClass76>() { // from class: com.facebook.ads.redexgen.X.0b
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final Class<AnonymousClass76> A() {
            return AnonymousClass76.class;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass76 event) {
            if (this.B.J == null) {
                return;
            }
            this.B.J.ME(this.B);
        }
    };
    private final AbstractC01210a<AnonymousClass70> N = new AbstractC01210a<AnonymousClass70>() { // from class: com.facebook.ads.redexgen.X.0c
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final Class<AnonymousClass70> A() {
            return AnonymousClass70.class;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass70 event) {
            this.B.D = true;
            if (this.B.J == null) {
                return;
            }
            this.B.J.KE(this.B);
        }
    };
    private final AbstractC01210a<AnonymousClass75> M = new AbstractC01210a<AnonymousClass75>() { // from class: com.facebook.ads.redexgen.X.0d
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final Class<AnonymousClass75> A() {
            return AnonymousClass75.class;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass75 event) {
            if (this.B.J == null) {
                return;
            }
            this.B.J.OE(this.B, AdError.internalError(2003));
        }
    };
    private final AbstractC01210a<AnonymousClass77> K = new AbstractC01210a<AnonymousClass77>() { // from class: com.facebook.ads.redexgen.X.0e
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final Class<AnonymousClass77> A() {
            return AnonymousClass77.class;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.facebook.ads.redexgen.X.AbstractC01210a
        public final void B(AnonymousClass77 event) {
            if (this.B.J == null) {
                return;
            }
            this.B.J.JE(this.B);
        }
    };
    private boolean D = false;
    private boolean I = false;

    static {
        Q = !C01320l.class.desiredAssertionStatus();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC01310k
    public final boolean A() {
        if (!this.D || this.O == null) {
            return false;
        }
        if (this.E.A() > 0) {
            this.O.L(this.E.A());
        }
        this.O.O(C7A.AUTO_STARTED);
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final String AC() {
        return this.H;
    }

    public final void B(Context context, AnonymousClass11 adapterListener, JV adEventManager, Bundle previousState, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = new JSONObject(previousState.getString("ad_response"));
            L(context, adapterListener, jSONObject, adEventManager, previousState, enumSet, jSONObject.optInt("video_time_polling_interval", 200));
        } catch (JSONException e) {
            if (BuildConfigApi.isDebug()) {
                Log.e(C01320l.class.toString(), "Cannot load instream", e);
            }
            adapterListener.OE(this, AdError.INTERNAL_ERROR);
        }
    }

    public final void C(Context context, AnonymousClass11 adapterListener, Map<String, Object> map, JV adEventManager, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = (JSONObject) map.get("data");
            IQ iq = (IQ) map.get("definition");
            L(context, adapterListener, jSONObject, adEventManager, null, enumSet, iq == null ? 200 : iq.I());
        } catch (JSONException e) {
            if (BuildConfigApi.isDebug()) {
                Log.e(C01320l.class.toString(), "Cannot load instream", e);
            }
            adapterListener.OE(this, AdError.INTERNAL_ERROR);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void G() throws JSONException {
        C0618Jf c0618Jf;
        if (!Q && this.C == null) {
            throw new AssertionError();
        }
        if (!Q && this.F == null) {
            throw new AssertionError();
        }
        JSONObject jSONObjectOptJSONObject = this.F.optJSONObject(MimeTypes.BASE_TYPE_TEXT);
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        this.O.A(new C02735w(this.C));
        Context context = this.C;
        if (this.B != null) {
            c0618Jf = new C0618Jf(AC(), this.B);
        } else {
            c0618Jf = null;
        }
        C02675q c02675q = new C02675q(context, c0618Jf);
        this.O.A(c02675q);
        this.O.A(new C6H(c02675q, C6I.INVSIBLE));
        this.O.A(new C6T(this.C));
        String strI = I();
        if (strI != null) {
            C6R c6r = new C6R(this.C, strI);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(9);
            c6r.setLayoutParams(layoutParams);
            c6r.setCountdownTextColor(-1);
            this.O.A(c6r);
        }
        if (this.F.has(InMobiNetworkValues.CTA) && !this.F.isNull(InMobiNetworkValues.CTA)) {
            JSONObject jSONObject = this.F.getJSONObject(InMobiNetworkValues.CTA);
            C6D c6d = new C6D(this.C, jSONObject.getString("url"), this.B, this.H, jSONObject.getString(MimeTypes.BASE_TYPE_TEXT));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            c6d.setLayoutParams(layoutParams2);
            this.O.A(c6d);
        }
        final String strH = H();
        if (!TextUtils.isEmpty(strH)) {
            final Context context2 = this.C;
            final String str = this.H;
            final float[] fArr = {0.0f, 0.0f, 8.0f, 0.0f};
            this.O.A(new AnonymousClass79(context2, strH, str, fArr) { // from class: com.facebook.ads.redexgen.X.6h
                private final C02856i B;

                {
                    super(context2);
                    this.B = new C02856i(context2, "AdChoices", strH, fArr, str);
                    addView(this.B);
                }
            });
        }
        int iJ = J();
        if (iJ > 0) {
            C02755y c02755y = new C02755y(this.C, iJ, jSONObjectOptJSONObject.optString("skipAdIn", "Skip Ad in"), jSONObjectOptJSONObject.optString("skipAd", "Skip Ad"));
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(12);
            layoutParams3.addRule(11);
            c02755y.setLayoutParams(layoutParams3);
            c02755y.setPadding(0, 0, 0, 30);
            this.O.A(c02755y);
        }
    }

    @Nullable
    private String H() {
        String string = null;
        if (!Q && this.F == null) {
            throw new AssertionError();
        }
        try {
            JSONObject jSONObject = this.F.getJSONObject("capabilities");
            if (jSONObject.has(be.a.fq) && !jSONObject.isNull(be.a.fq)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(be.a.fq);
                if (!jSONObject2.has("url")) {
                    return null;
                }
                string = jSONObject2.getString("url");
                return string;
            }
            return null;
        } catch (Exception e) {
            Log.w(String.valueOf(C01320l.class), "Invalid JSON", e);
            return string;
        }
    }

    private String I() {
        String strOptString = null;
        if (!Q && this.F == null) {
            throw new AssertionError();
        }
        try {
            JSONObject jSONObject = this.F.getJSONObject("capabilities");
            if (jSONObject.has("countdown") && !jSONObject.isNull("countdown")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("countdown");
                if (!jSONObject2.has("format")) {
                    return null;
                }
                strOptString = jSONObject2.optString("format");
                return strOptString;
            }
            return null;
        } catch (Exception e) {
            Log.w(String.valueOf(C01320l.class), "Invalid JSON", e);
            return strOptString;
        }
    }

    private int J() {
        int i = -1;
        if (!Q && this.F == null) {
            throw new AssertionError();
        }
        try {
            JSONObject jSONObject = this.F.getJSONObject("capabilities");
            if (jSONObject.has("skipButton") && !jSONObject.isNull("skipButton")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("skipButton");
                if (!jSONObject2.has("skippableSeconds")) {
                    return -1;
                }
                i = jSONObject2.getInt("skippableSeconds");
                return i;
            }
            return -1;
        } catch (Exception e) {
            Log.w(String.valueOf(C01320l.class), "Invalid JSON", e);
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String K() {
        return (this.G == null || this.P == null) ? this.P : this.G.K(this.P);
    }

    private void L(Context context, AnonymousClass11 adapterListener, JSONObject adResponse, JV adEventManager, @Nullable Bundle previousState, EnumSet<CacheFlag> enumSet, int videoPollingIntervalMs) throws JSONException {
        this.C = context;
        this.J = adapterListener;
        this.B = adEventManager;
        this.F = adResponse;
        this.D = false;
        JSONObject jSONObject = adResponse.getJSONObject("video");
        this.H = adResponse.optString(UserDataStore.CITY);
        this.O = new C0447Co(context);
        this.O.setVideoProgressReportIntervalMs(videoPollingIntervalMs);
        G();
        this.O.getEventBus().B(this.L, this.N, this.M, this.K);
        ArrayList arrayList = new ArrayList();
        final double d = 1.0E-7d;
        final double d2 = -1.0d;
        final double d3 = 0.001d;
        final boolean z = false;
        arrayList.add(new AbstractC01260f(d, d2, d3, z) { // from class: com.facebook.ads.redexgen.X.0g
            @Override // com.facebook.ads.redexgen.X.AbstractC01260f
            public final void A(boolean isCompleted, boolean isPassed, C01912s statistics) {
                this.B.M();
            }
        });
        if (previousState != null) {
            this.E = new C7G(context, adEventManager, this.O, arrayList, this.H, previousState.getBundle("logger"), null);
        } else {
            this.E = new C7G(context, adEventManager, this.O, arrayList, this.H);
        }
        this.J.NE(this, this.O);
        if (KN.B(context) == KO.MOBILE_INTERNET && jSONObject.has("videoHDURL") && !jSONObject.isNull("videoHDURL")) {
            this.P = jSONObject.getString("videoHDURL");
        } else {
            this.P = jSONObject.getString("videoURL");
        }
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.G = new H2(context);
            String strOptString = adResponse.optString("requestId");
            this.G.E(new H0(this.P, strOptString, "instream"));
            this.G.H(new InterfaceC01280h() { // from class: com.facebook.ads.redexgen.X.0i
                @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
                public final void dD() {
                    this.B.O.setVideoURI(this.B.K());
                }

                @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
                public final void nD() {
                    this.B.O.setVideoURI(this.B.K());
                }
            }, new C0558Gw(strOptString, "instream"));
            return;
        }
        this.O.setVideoURI(K());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        if (this.B == null || this.I) {
            return;
        }
        this.I = true;
        this.B.lC(this.H, new HashMap());
        if (this.J == null) {
            return;
        }
        this.J.LE(this);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01300j
    public final Bundle getSaveInstanceState() {
        if (this.E == null || this.F == null || this.O == null || this.O.getState() == C5K.IDLE) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("logger", this.E.getSaveInstanceState());
        bundle.putString("ad_response", this.F.toString());
        return bundle;
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final void onDestroy() {
        if (this.O != null) {
            this.O.P();
            this.O.B();
        }
        this.J = null;
        this.B = null;
        this.P = null;
        this.D = false;
        this.H = null;
        this.O = null;
        this.E = null;
        this.F = null;
        this.C = null;
        this.I = false;
    }
}
