package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JL {
    private Map<JE, JK> B;

    private JL() {
        this.B = new HashMap();
    }

    public /* synthetic */ JL(JJ x0) {
        this();
    }

    public final JL A(JE architecture, JK result) {
        this.B.put(architecture, result);
        return this;
    }

    public final JM B(Throwable failure) {
        return new JM(failure, JE.B(), this.B, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final JM C() {
        return new JM(null, JE.B(), this.B, 0 == true ? 1 : 0);
    }
}
