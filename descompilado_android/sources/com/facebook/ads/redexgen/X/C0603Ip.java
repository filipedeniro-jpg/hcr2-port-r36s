package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ip, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0603Ip implements InterfaceC0602Io {
    public final /* synthetic */ Context B;

    public C0603Ip(Context context) {
        this.B = context;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0602Io
    public final Map<String, String> tB() {
        HashMap map = new HashMap();
        if (!C0551Gp.E) {
            map.put("X-FB-Pool-Routing-Token", C0597Ij.B().B(this.B, true).KC());
        }
        return map;
    }
}
