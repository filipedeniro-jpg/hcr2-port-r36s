package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LN implements LM {
    private void B(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                List<String> headers = map.get(str);
                Iterator<String> it = headers.iterator();
                while (it.hasNext()) {
                    dC(str + ":" + it.next());
                }
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.LM
    public final boolean VC() {
        return AdInternalSettings.isDebugBuild();
    }

    @Override // com.facebook.ads.redexgen.X.LM
    public final void dC(String msg) {
        System.out.println(msg);
    }

    @Override // com.facebook.ads.redexgen.X.LM
    public final void sC(HttpURLConnection uc, Object content) throws IOException {
        dC("=== HTTP Request ===");
        dC(uc.getRequestMethod() + " " + uc.getURL().toString());
        if (content instanceof String) {
            dC("Content: " + ((String) content));
        }
        B(uc.getRequestProperties());
    }

    @Override // com.facebook.ads.redexgen.X.LM
    public final void tC(LU res) {
        if (res != null) {
            dC("=== HTTP Response ===");
            dC("Receive url: " + res.E());
            dC("Status: " + res.D());
            B(res.C());
            dC("Content:\n" + res.B());
        }
    }
}
