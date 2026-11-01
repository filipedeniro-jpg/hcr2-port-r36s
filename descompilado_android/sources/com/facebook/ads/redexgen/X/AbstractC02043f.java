package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.util.Log;
import java.util.EnumSet;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC02043f<T> {
    private static final String F = AbstractC02043f.class.getSimpleName();

    @InterfaceC01641r
    private final C3T B;
    private final T C;
    private final C3S D;
    private final long E;

    public abstract boolean A(AbstractC02043f<T> abstractC02043f);

    public abstract int E();

    public abstract JSONObject F(JSONObject jSONObject) throws JSONException;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    public AbstractC02043f(long timeStampMS, @InterfaceC01641r C3T signalCollectionContext, T t, C3S signalValueType) {
        this.E = timeStampMS;
        this.B = signalCollectionContext;
        this.C = t;
        this.D = signalValueType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    @InterfaceC01641r
    final C3T B() {
        return this.B;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final T m231B() {
        return this.C;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    private final C3S C() {
        return this.D;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    public final boolean C(@InterfaceC01641r AbstractC02043f<T> abstractC02043f, EnumSet<EnumC02053g> enumSet) {
        if (abstractC02043f == null || C() != abstractC02043f.C()) {
            return false;
        }
        if (C() == C3S.ERROR && abstractC02043f.C() == C3S.ERROR) {
            return A(abstractC02043f);
        }
        boolean zA = false;
        if (enumSet.contains(EnumC02053g.EQUAL_BY_VALUE)) {
            zA = A(abstractC02043f);
        }
        if (zA && enumSet.contains(EnumC02053g.EQUAL_BY_CONTEXT)) {
            zA = (B() == null || abstractC02043f.B() == null || !B().A().equals(abstractC02043f.B().A())) ? false : true;
        }
        if (zA && enumSet.contains(EnumC02053g.EQUAL_BY_TIMESTAMP)) {
            zA = this.E == abstractC02043f.D();
        }
        return zA;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    /* JADX INFO: renamed from: D, reason: collision with other method in class */
    public final int m232D() {
        int length;
        if (B() != null) {
            length = B().A().getBytes().length;
        } else {
            length = 0;
        }
        return length + 8 + E();
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    private final long D() {
        return this.E;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<T> */
    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    public final JSONObject G() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t", this.E / 1000.0f);
            if (this.B != null) {
                jSONObject.put("ctx", this.B.B());
            }
            if (this.D != C3S.ERROR) {
                F(jSONObject);
            } else {
                jSONObject.put("e", ((C3U) this.C).G());
            }
        } catch (JSONException e) {
            Log.e(F, "Error Creating JSON", e);
        }
        return jSONObject;
    }
}
