package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3Y, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C3Y<K, V> extends AbstractC02043f<HashMap<K, V>> {
    private final HashMap<K, V> B;
    private final int C;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3Y != com.facebook.botdetection.signals.model.signal_value.MapSignalValueType<K, V> */
    public C3Y(long timeStampMS, @InterfaceC01641r C3T signalCollectionContext, HashMap<K, V> map, C3S signalValueType, int sizeOfMap) {
        super(timeStampMS, signalCollectionContext, map, signalValueType);
        this.B = map;
        this.C = sizeOfMap;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3Y != com.facebook.botdetection.signals.model.signal_value.MapSignalValueType<K, V> */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<java.util.HashMap<K, V>> */
    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    @SuppressLint({"Nullable Dereference"})
    public final boolean A(AbstractC02043f<HashMap<K, V>> abstractC02043f) {
        if (abstractC02043f.m231B().size() != ((HashMap) B()).size()) {
            return false;
        }
        HashMap<K, V> mapM231B = abstractC02043f.m231B();
        HashMap map = (HashMap) B();
        Iterator<K> it = mapM231B.keySet().iterator();
        Iterator<K> it2 = map.keySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            K next = it.next();
            K prevSignalValueKey = it2.next();
            if (!next.equals(prevSignalValueKey) || !mapM231B.containsKey(next) || !map.containsKey(prevSignalValueKey)) {
                return false;
            }
            C4M c4m = (C4M) mapM231B.get(next);
            C4M c4m2 = (C4M) map.get(prevSignalValueKey);
            if (c4m == null && c4m2 == null) {
                return false;
            }
            if ((c4m2 == null) ^ (c4m == null)) {
                return true;
            }
            if (!c4m.SC(c4m2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3Y != com.facebook.botdetection.signals.model.signal_value.MapSignalValueType<K, V> */
    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    public final int E() {
        return this.C;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3Y != com.facebook.botdetection.signals.model.signal_value.MapSignalValueType<K, V> */
    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
    public final JSONObject F(JSONObject inputObject) throws JSONException {
        Set<K> setKeySet = this.B.keySet();
        JSONObject jSONObject = new JSONObject();
        for (K k : setKeySet) {
            C4M mapSignalValueDef = (C4M) this.B.get(k);
            if (mapSignalValueDef != null) {
                mapSignalValueDef.uF(k, jSONObject);
            }
        }
        inputObject.put("v", jSONObject);
        return inputObject;
    }
}
