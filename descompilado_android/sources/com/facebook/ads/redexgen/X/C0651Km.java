package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.protocol.AdErrorType;
import com.my.target.v;
import org.json.JSONException;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Km, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0651Km extends AbstractC0650Kl {
    public final /* synthetic */ C0656Ks B;
    public final /* synthetic */ long C;

    public C0651Km(C0656Ks this$0, long j) {
        this.B = this$0;
        this.C = j;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0650Kl
    public final void A(LU lu) {
        C0642Kd.B(v.aF, "Server replied successfully");
        if (lu != null) {
            String strB = lu.B();
            C0646Kh.E(this.B.B);
            this.B.F = null;
            this.B.U(strB, this.C);
        }
    }

    private final void B(LT httpException) {
        C0646Kh.E(this.B.B);
        this.B.F = null;
        try {
            LU response = httpException.A();
            if (response != null) {
                String strB = response.B();
                C0658Kv c0658KvA = this.B.H.A(this.B.D, strB, this.C);
                if (c0658KvA.B() == Ku.ERROR) {
                    C0660Kx c0660Kx = (C0660Kx) c0658KvA;
                    String strE = c0660Kx.E();
                    AdErrorType adErrorTypeAdErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(c0660Kx.D(), AdErrorType.ERROR_MESSAGE);
                    C0656Ks c0656Ks = this.B;
                    if (strE != null) {
                        strB = strE;
                    }
                    c0656Ks.Z(KQ.C(adErrorTypeAdErrorTypeFromCode, strB));
                    return;
                }
            }
        } catch (JSONException e) {
        }
        this.B.Z(KQ.C(AdErrorType.NETWORK_ERROR, httpException.getMessage()));
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0650Kl
    public final void B(Exception exc) {
        C0642Kd.B(v.aC, "Server error occurred");
        if (LT.class.equals(exc.getClass())) {
            B((LT) exc);
        } else {
            this.B.Z(KQ.C(AdErrorType.NETWORK_ERROR, exc.getMessage()));
        }
    }
}
