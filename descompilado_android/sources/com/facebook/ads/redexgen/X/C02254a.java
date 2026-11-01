package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.facebook.botdetection.signals.model.BundledSignalMetadata;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02254a {
    private static final String I = C02254a.class.getSimpleName();
    private final AnonymousClass56 B;
    private final C4Y C;
    private final C02394o D;
    private final int E;
    private final Context F;
    private final C4X G;
    private final C4W H;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.facebook.ads.redexgen.X.4U] */
    public C02254a(Context context, final AnonymousClass56 baseBdConfig) {
        final C4T c4t = new C4T(new C4I(context, baseBdConfig.B()));
        ?? r1 = new Object(c4t, baseBdConfig) { // from class: com.facebook.ads.redexgen.X.4U
            private static final String H = C4U.class.getSimpleName();
            private final AnonymousClass56 B;
            private final C4Y C;
            private int D;
            private final C4X E;
            private final C4T F;
            private final C4W G;

            {
                this.E = new C4X(baseBdConfig);
                this.G = new C4W(baseBdConfig);
                this.C = new C4Y(baseBdConfig);
                this.F = c4t;
                this.B = baseBdConfig;
                C();
            }

            public final C4Y A() {
                return this.C;
            }

            public final int B() {
                return this.D;
            }

            @InterfaceC01641r
            private List<C02093k> B(int signalId) throws JSONException {
                List<BundledSignalMetadata> bundledSignalMetadata = new ArrayList<>();
                Map<Integer, String> mapE = this.B.E();
                if (mapE != null && !mapE.isEmpty() && mapE.containsKey(Integer.valueOf(signalId))) {
                    JSONObject jSONObject = new JSONObject(mapE.get(Integer.valueOf(signalId)));
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        JSONArray jSONArray = jSONObject.getJSONArray(next);
                        if (jSONArray != null && jSONArray.length() == 2) {
                            int i = jSONArray.getInt(0);
                            int minApiLevel = jSONArray.getInt(1);
                            bundledSignalMetadata.add(E(signalId, next, i, minApiLevel));
                        }
                    }
                }
                return bundledSignalMetadata;
            }

            /* JADX INFO: renamed from: C, reason: collision with other method in class */
            public final C4X m234C() {
                return this.E;
            }

            @SuppressLint({"BadMethodUse-android.util.Log.e"})
            private final void C() {
                try {
                    JSONObject jSONObject = new JSONObject(this.B.J());
                    Integer timestamp = jSONObject.has("t") ? Integer.valueOf(jSONObject.getInt("t")) : null;
                    if (timestamp == null) {
                        return;
                    }
                    this.D = timestamp.intValue();
                    JSONArray jsonArray = jSONObject.getJSONArray(Constants.URL_CAMPAIGN);
                    F(jsonArray);
                } catch (JSONException e) {
                    C3O.C(this.B, EnumC02504z.ERROR, EnumC02494y.CONFIG_PARSING.A(), null, e.getClass().getSimpleName(), e);
                    Log.e(H, "Error Parsing Config Json", e);
                }
            }

            @InterfaceC01641r
            private static C02093k D(String key, int minApiLevel, int deprecatedApiLevel, EnumC02103l keyType) {
                switch (C4V.B[keyType.ordinal()]) {
                    case 1:
                        return new C02093k(Integer.valueOf(Integer.parseInt(key)), minApiLevel, deprecatedApiLevel);
                    case 2:
                        return new C02093k(key, minApiLevel, deprecatedApiLevel);
                    default:
                        return null;
                }
            }

            public final C4W D() {
                return this.G;
            }

            @InterfaceC01641r
            private static C02093k E(int signalId, String key, int minApiLevel, int deprecatedApiLevel) {
                switch (signalId) {
                    case 10300:
                    case 10940:
                    case 10941:
                    case 10943:
                    case 10944:
                    case 10945:
                        return D(key, minApiLevel, deprecatedApiLevel, EnumC02103l.STRING);
                    case 10920:
                        return D(key, minApiLevel, deprecatedApiLevel, EnumC02103l.INTEGER);
                    default:
                        return null;
                }
            }

            @SuppressLint({"BadMethodUse-android.util.Log.e"})
            private void F(JSONArray signalConfig) throws JSONException {
                JSONArray jSONArray;
                C4S c4sA;
                Map<Integer, C4S> mapM233B = this.F.m233B();
                if (mapM233B == null) {
                    return;
                }
                for (int i = 0; i < i && (jSONArray = signalConfig.getJSONArray(i)) != null && jSONArray.length() == 2; i++) {
                    if ((jSONArray.get(0) instanceof Integer) && (jSONArray.get(1) instanceof Integer)) {
                        int i2 = jSONArray.getInt(0);
                        int signalFlagsEncoded = jSONArray.getInt(1);
                        EnumSet<EnumC02053g> enumSetB = EnumC02053g.B(signalFlagsEncoded, EnumC02053g.class);
                        if (!G(enumSetB)) {
                            if (enumSetB.contains(EnumC02053g.BIOMETRIC)) {
                                this.C.B(new C02113m(i2, enumSetB, mapM233B.get(Integer.valueOf(i2)), null));
                            } else {
                                List<C02093k> listB = null;
                                boolean z = false;
                                if (enumSetB.contains(EnumC02053g.BUNDLE)) {
                                    listB = B(i2);
                                    z = true;
                                }
                                if (mapM233B.containsKey(Integer.valueOf(i2)) || z) {
                                    if (z) {
                                        c4sA = this.F.A(i2, listB);
                                    } else {
                                        c4sA = mapM233B.get(Integer.valueOf(i2));
                                    }
                                    C02113m c02113m = new C02113m(i2, enumSetB, c4sA, listB);
                                    if (enumSetB.contains(EnumC02053g.DYNAMIC)) {
                                        this.E.B(c02113m);
                                    } else {
                                        this.G.B(c02113m);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            private final boolean G(EnumSet<EnumC02053g> enumSet) {
                return (enumSet.contains(this.B.B()) && enumSet.contains(EnumC02053g.ACTIVE) && enumSet.contains(EnumC02053g.ANDROID_NATIVE) && !enumSet.contains(EnumC02053g.DEPRECATED)) ? false : true;
            }
        };
        this.F = context;
        this.G = r1.m234C();
        this.H = r1.D();
        this.C = r1.A();
        this.E = r1.B();
        this.B = baseBdConfig;
        this.D = new C02394o();
        this.D.A(this.C);
    }

    public final void A(EnumC02083j reputationTier) {
        this.C.C(reputationTier, this.F);
    }

    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    private static JSONObject B() {
        JSONObject jSONObject = new JSONObject();
        Map<Integer, AnonymousClass50<AbstractC02043f>> mapA = C02073i.B().A();
        if (mapA != null && !mapA.isEmpty()) {
            try {
                Set<Integer> keyset = mapA.keySet();
                for (Integer num : keyset) {
                    List<AbstractC02043f> listB = mapA.get(num).B();
                    JSONArray jSONArray = new JSONArray();
                    Iterator<AbstractC02043f> it = listB.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next().G());
                    }
                    jSONObject.put(num.toString(), jSONArray);
                }
            } catch (JSONException e) {
                Log.e(I, "Error creating output JSON", e);
            }
        }
        return jSONObject;
    }

    public final void B(EnumC02063h signalCollectionMode, EnumC02083j reputationTier, @InterfaceC01641r String bdSessionId) {
        this.G.C(reputationTier);
        if (signalCollectionMode == EnumC02063h.EVENT) {
            this.H.C(reputationTier);
            if (bdSessionId != null) {
                C(bdSessionId, reputationTier);
            }
        }
    }

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    private void C(String bdSessionId, EnumC02083j reputationTier) {
        InterfaceC0612Iz interfaceC0612IzC = this.B.C();
        if (interfaceC0612IzC != null) {
            interfaceC0612IzC.vC(C3O.B(), bdSessionId, this.E, reputationTier.A(), (int) (System.currentTimeMillis() / 1000), B());
        }
        C3O.C(this.B, EnumC02504z.INFO, EnumC02494y.SESSION_LOGGED.A(), bdSessionId, null, null);
    }

    public final boolean C() {
        List<C02113m> listD = this.G.D();
        return (listD == null || listD.isEmpty()) ? false : true;
    }

    public final void D() {
        this.D.B();
    }
}
