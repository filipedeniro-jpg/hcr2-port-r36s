package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0618Jf {
    private final JV B;
    private final String C;

    public C0618Jf(String clientToken, JV adEventManager) {
        this.C = clientToken;
        this.B = adEventManager;
    }

    public final void A(EnumC0617Je event, @Nullable Map<String, String> map) {
        if (!C0620Jh.B(this.C, event)) {
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("subtype", event.A());
        this.B.kC(this.C, map);
    }
}
