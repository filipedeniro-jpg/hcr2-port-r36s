package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0586Hy implements Runnable {
    public final /* synthetic */ Context B;
    public final /* synthetic */ C0583Hv C;
    public final /* synthetic */ String D;
    public final /* synthetic */ int E;

    public RunnableC0586Hy(Context context, String str, int i, C0583Hv c0583Hv) {
        this.B = context;
        this.D = str;
        this.E = i;
        this.C = c0583Hv;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String strF;
        Map<String, String> mapB;
        String strG;
        try {
            if (!C0585Hx.G(this.B, this.D, this.E)) {
                Throwable cause = this.C.getCause();
                if (cause != null) {
                    strF = L0.F(this.B, cause);
                } else if (JA.FB(this.B)) {
                    strF = L0.F(this.B, this.C);
                } else {
                    strF = "" + this.C.getMessage();
                }
                if (JA.G(this.B)) {
                    mapB = C0591Id.B(this.B);
                } else if (C0585Hx.D != null) {
                    mapB = C0585Hx.D.DC();
                } else {
                    if (C0585Hx.C) {
                        throw new RuntimeException("Debug crash because sEnvironmentDataProvider not injected", this.C);
                    }
                    mapB = C0591Id.B(this.B);
                }
                String stackTrace = this.D;
                mapB.put("subtype", stackTrace);
                String stackTrace2 = String.valueOf(this.E);
                mapB.put("subtype_code", stackTrace2);
                JSONObject jSONObjectA = this.C.A();
                if (jSONObjectA != null) {
                    String stackTrace3 = jSONObjectA.toString();
                    mapB.put("additional_info", stackTrace3);
                }
                String stackTrace4 = this.D;
                if ("cache".equals(stackTrace4) && (strG = IK.G()) != null) {
                    mapB.put("available_disk_space", strG);
                }
                C0573Hl.B().CG(strF, mapB, this.B);
            }
        } catch (Throwable th) {
            C0585Hx.L(th);
        }
    }
}
