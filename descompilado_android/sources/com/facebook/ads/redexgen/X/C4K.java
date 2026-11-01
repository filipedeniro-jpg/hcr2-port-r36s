package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4K, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C4K {
    private final EnumC02053g B;
    private final Context C;
    private final Intent D;

    public C4K(Context context, EnumC02053g bdIntegrationSite) {
        this.C = context;
        this.B = bdIntegrationSite;
        this.D = this.C.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public final AbstractC02043f A(boolean value) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3tK = K();
        final Boolean boolValueOf = Boolean.valueOf(value);
        return new AbstractC02043f<Boolean>(jElapsedRealtime, c3tK, boolValueOf) { // from class: com.facebook.ads.redexgen.X.3e
            {
                C3S c3s = C3S.BOOLEAN;
            }

            /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Boolean> */
            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final boolean A(AbstractC02043f<Boolean> abstractC02043f) {
                return ((Boolean) B()).equals(abstractC02043f.m231B());
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final int E() {
                return 8;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final JSONObject F(JSONObject inputObject) throws JSONException {
                inputObject.put("v", B());
                return inputObject;
            }
        };
    }

    public final AbstractC02043f B(final HashMap<String, Integer> map) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3tK = K();
        return new AbstractC02043f<HashMap<String, Integer>>(jElapsedRealtime, c3tK, map) { // from class: com.facebook.ads.redexgen.X.3b
            {
                C3S c3s = C3S.INT_ARRAY;
            }

            /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.util.HashMap<java.lang.String, java.lang.Integer>> */
            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            @SuppressLint({"Nullable Dereference"})
            public final boolean A(AbstractC02043f<HashMap<String, Integer>> abstractC02043f) {
                if (((HashMap) B()).size() != abstractC02043f.m231B().size()) {
                    return false;
                }
                HashMap<String, Integer> mapM231B = abstractC02043f.m231B();
                HashMap map2 = (HashMap) B();
                Iterator<String> it = mapM231B.keySet().iterator();
                Iterator it2 = map2.keySet().iterator();
                while (it.hasNext() && it2.hasNext()) {
                    String next = it.next();
                    String str = (String) it2.next();
                    if (!next.equals(str) || !mapM231B.containsKey(next) || !map2.containsKey(str) || !mapM231B.get(next).equals(map2.get(str))) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final int E() {
                Set<String> keys = ((HashMap) B()).keySet();
                int i = 0;
                Iterator<String> it = keys.iterator();
                while (it.hasNext()) {
                    int keySize = it.next().getBytes().length;
                    i += keySize;
                }
                int keySize2 = ((HashMap) B()).size();
                return (keySize2 * 4) + i;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final JSONObject F(JSONObject inputObject) throws JSONException {
                HashMap<String, Integer> signalValue = (HashMap) B();
                Set<String> setKeySet = signalValue.keySet();
                JSONObject jSONObject = new JSONObject();
                for (String str : setKeySet) {
                    jSONObject.put(str, signalValue.get(str));
                }
                inputObject.put("v", jSONObject);
                return inputObject;
            }
        };
    }

    public final AbstractC02043f C(int value) {
        return new C01993a(SystemClock.elapsedRealtime(), K(), Integer.valueOf(value));
    }

    public final AbstractC02043f D(C3V errorType) {
        final C3U c3u = new C3U(errorType);
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3tK = K();
        return new AbstractC02043f<C3U>(jElapsedRealtime, c3tK, c3u) { // from class: com.facebook.ads.redexgen.X.3d
            {
                C3S c3s = C3S.ERROR;
            }

            /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.model.signal_value.SignalErrorValueTypeDef> */
            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final boolean A(AbstractC02043f<C3U> abstractC02043f) {
                C3U c3u2 = (C3U) B();
                C3U c3uM231B = abstractC02043f.m231B();
                return c3u2.D().equals(abstractC02043f.m231B().D()) && c3u2.A() == c3uM231B.A() && c3u2.C() != null && c3u2.C().equals(c3uM231B.C()) && c3u2.B() != null && c3u2.B().equals(c3uM231B.B()) && c3u2.E() != null && c3u2.E().equals(c3uM231B.E());
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final int E() {
                return ((C3U) B()).F();
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final JSONObject F(JSONObject inputObject) throws JSONException {
                inputObject.put("v", ((C3U) B()).G());
                return inputObject;
            }
        };
    }

    public final AbstractC02043f E(long value) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3tK = K();
        final Long lValueOf = Long.valueOf(value);
        return new AbstractC02043f<Long>(jElapsedRealtime, c3tK, lValueOf) { // from class: com.facebook.ads.redexgen.X.3Z
            {
                C3S c3s = C3S.LONG;
            }

            /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.Long> */
            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final boolean A(AbstractC02043f<Long> abstractC02043f) {
                return ((Long) B()).equals(abstractC02043f.m231B());
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final int E() {
                return 8;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final JSONObject F(JSONObject inputObject) throws JSONException {
                inputObject.put("v", B());
                return inputObject;
            }
        };
    }

    public final AbstractC02043f F(String name, boolean defaultValue) {
        if (this.D != null) {
            return A(this.D.getBooleanExtra(name, defaultValue));
        }
        return D(C3V.NULL);
    }

    public final AbstractC02043f G(int defaultValue, HashMap<String, String> map) {
        if (this.D != null) {
            HashMap<String, Integer> map2 = new HashMap<>();
            for (String str : map.keySet()) {
                map2.put(str, Integer.valueOf(this.D.getIntExtra(map.get(str), defaultValue)));
            }
            return B(map2);
        }
        return D(C3V.NULL);
    }

    public final AbstractC02043f H(String name, int defaultValue) {
        if (this.D != null) {
            return C(this.D.getIntExtra(name, defaultValue));
        }
        return D(C3V.NULL);
    }

    private final AbstractC02043f I(final String value) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3tK = K();
        return new AbstractC02043f<String>(jElapsedRealtime, c3tK, value) { // from class: com.facebook.ads.redexgen.X.3R
            {
                C3S c3s = C3S.STRING;
            }

            /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.lang.String> */
            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final boolean A(AbstractC02043f<String> abstractC02043f) {
                return ((String) B()).equals(abstractC02043f.m231B());
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final int E() {
                return ((String) B()).getBytes().length;
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC02043f
            public final JSONObject F(JSONObject inputObject) throws JSONException {
                inputObject.put("v", B());
                return inputObject;
            }
        };
    }

    public final AbstractC02043f J(String name) {
        if (this.D != null) {
            return I(this.D.getStringExtra(name));
        }
        return D(C3V.NULL);
    }

    @InterfaceC01641r
    public final C3T K() {
        if (this.B == EnumC02053g.OFFSITE) {
            return null;
        }
        return new C3T(this.C == null ? "" : this.C.getPackageName());
    }
}
