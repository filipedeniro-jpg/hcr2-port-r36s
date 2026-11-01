package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.BatteryManager;
import com.my.target.i;
import java.util.HashMap;
import java.util.Set;
import javax.inject.Singleton;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.44, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@Singleton
public final class AnonymousClass44 extends C4K {
    private final BatteryManager B;

    public AnonymousClass44(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        this.B = (BatteryManager) context.getSystemService("batterymanager");
    }

    public final C4S L() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4C
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.S(4);
            }
        };
    }

    public final C4S M() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.46
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.S(1);
            }
        };
    }

    public final C4S N() {
        final HashMap map = new HashMap();
        map.put("n", 2);
        map.put(i.I, 3);
        return new C4S() { // from class: com.facebook.ads.redexgen.X.47
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.R(map);
            }
        };
    }

    public final C4S O() {
        final HashMap map = new HashMap();
        map.put("l", "level");
        map.put("s", "scale");
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4H
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.G(Integer.MIN_VALUE, map);
            }
        };
    }

    public final C4S P() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.49
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.H("health", Integer.MIN_VALUE);
            }
        };
    }

    public final C4S Q() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4E
            @Override // com.facebook.ads.redexgen.X.C4S
            @TargetApi(28)
            public final AbstractC02043f oB() {
                return this.B.F("battery_low", false);
            }
        };
    }

    @SuppressLint({"Nullable Dereference"})
    @TargetApi(21)
    public final AbstractC02043f R(HashMap<String, Integer> map) {
        if (this.B != null) {
            Set<String> setKeySet = map.keySet();
            HashMap<String, Integer> map2 = new HashMap<>();
            for (String str : setKeySet) {
                map2.put(str, Integer.valueOf(this.B.getIntProperty(map.get(str).intValue())));
            }
            return B(map2);
        }
        return D(C3V.NULL);
    }

    @TargetApi(21)
    public final AbstractC02043f S(int property) {
        if (this.B != null) {
            return C(this.B.getIntProperty(property));
        }
        return D(C3V.NULL);
    }

    public final C4S T() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4B
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.H("plugged", Integer.MIN_VALUE);
            }
        };
    }

    public final C4S U() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.45
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.S(5);
            }
        };
    }

    public final C4S V() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4D
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.H("status", 1);
            }
        };
    }

    public final C4S W() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4G
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.J("technology");
            }
        };
    }

    public final C4S X() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4A
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.H("temperature", Integer.MIN_VALUE);
            }
        };
    }

    public final C4S Y() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4F
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.H("voltage", Integer.MIN_VALUE);
            }
        };
    }

    public final C4S Z() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.48
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.F("present", false);
            }
        };
    }
}
