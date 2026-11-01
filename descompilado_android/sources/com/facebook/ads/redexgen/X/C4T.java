package com.facebook.ads.redexgen.X;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4T, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C4T {
    private final C4I B;
    private Map<Integer, C4S> C;

    public C4T(C4I baseNativeSignalCollectorController) {
        this.C = new HashMap();
        this.B = baseNativeSignalCollectorController;
        this.C = new HashMap();
    }

    @InterfaceC01641r
    public final C4S A(int signalId, List<C02093k> list) {
        switch (signalId) {
            case 10300:
                return this.B.C().L(list);
            case 10920:
                return this.B.A().M(list);
            case 10940:
                return this.B.G().L(list);
            case 10941:
                return this.B.D().L(list);
            case 10943:
                return this.B.F().L(list, EnumC02173s.GLOBAL);
            case 10944:
                return this.B.F().L(list, EnumC02173s.SECURE);
            case 10945:
                return this.B.F().L(list, EnumC02173s.SYSTEM);
            default:
                return null;
        }
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final Map<Integer, C4S> m233B() {
        B();
        return Collections.unmodifiableMap(this.C);
    }

    private void B() {
        this.C.put(10050, this.B.B().V());
        this.C.put(10051, this.B.B().L());
        this.C.put(10052, this.B.B().T());
        this.C.put(10053, this.B.B().X());
        this.C.put(10054, this.B.B().P());
        this.C.put(10055, this.B.B().Z());
        this.C.put(10056, this.B.B().N());
        this.C.put(10057, this.B.B().M());
        this.C.put(10058, this.B.B().U());
        this.C.put(10059, this.B.B().O());
        this.C.put(10060, this.B.B().W());
        this.C.put(10061, this.B.B().Y());
        this.C.put(10062, this.B.B().Q());
        this.C.put(10930, this.B.A().N());
        this.C.put(10931, this.B.A().O());
        this.C.put(10932, this.B.A().P());
        this.C.put(10934, this.B.A().L());
        this.C.put(10200, this.B.E().L());
    }
}
