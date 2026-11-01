package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.botdetection.signals.library.nativesignals.SystemPropertiesSignalCollector;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02123n extends C4K {
    private static final String C = C02123n.class.getSimpleName();

    @InterfaceC01641r
    private static Class D;

    @InterfaceC01641r
    private static Method E;
    private final Context B;

    public C02123n(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        this.B = context;
        D = F(this.B);
        E = G();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Nullable Dereference"})
    public AbstractC02043f E(HashMap<String, C02133o> map) {
        int iA = 0;
        Set<String> keySet = map.keySet();
        for (String str : keySet) {
            iA += map.get(str).A() + str.getBytes().length;
        }
        return new C3Y(SystemClock.elapsedRealtime(), K(), map, C3S.MAP, iA);
    }

    @InterfaceC01641r
    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    private static Class F(Context context) {
        try {
            return context.getClassLoader().loadClass("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
            Log.e(C, "Error fetching System Class", e);
            return null;
        }
    }

    @InterfaceC01641r
    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    private static Method G() {
        Method method = null;
        try {
            Class<?>[] clsArr = {String.class};
            if (D == null) {
                return null;
            }
            method = D.getMethod("get", clsArr);
            return method;
        } catch (NoSuchMethodException e) {
            Log.e(C, "Error fetching System Method", e);
            return method;
        }
    }

    public final C4S L(final List<C02093k> list) {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.3p
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                HashMap<String, SystemPropertiesSignalCollector.SystemProperty> systemPropertiesMap = new HashMap<>();
                for (C02093k c02093k : list) {
                    if (c02093k.B()) {
                        String str = (String) c02093k.A();
                        C02133o systemProperty = new C02133o(str);
                        systemPropertiesMap.put(str, systemProperty);
                    }
                }
                return this.B.E((HashMap<String, C02133o>) systemPropertiesMap);
            }
        };
    }
}
