package com.facebook.ads.redexgen.X;

import android.util.Base64;
import com.facebook.ads.internal.protocol.AdErrorType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class RunnableC0648Kj implements Runnable {
    public final /* synthetic */ C0656Ks B;
    public final /* synthetic */ boolean C;
    public final /* synthetic */ C0647Ki D;

    public RunnableC0648Kj(C0656Ks this$0, C0647Ki c0647Ki, boolean z) {
        this.B = this$0;
        this.D = c0647Ki;
        this.C = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0551Gp.B(this.B.D);
        C0604Iq.B(this.B.D);
        if (this.D.D().E()) {
            try {
                this.D.D().F(C0551Gp.B);
            } catch (KR e) {
                this.B.Z(KQ.D(e));
            }
            this.B.U(this.D.D().C(), 0L);
            return;
        }
        this.B.C = this.D.G();
        if (this.C && C0656Ks.J != null) {
            C0656Ks.J.provideHttpsParams(this.B, this.B.C);
        }
        try {
            this.B.C.put("M_BANNER_KEY", new String(Base64.encode((this.B.D.getPackageName() + " " + this.B.D.getPackageManager().getInstallerPackageName(this.B.D.getPackageName())).getBytes(), 2)));
        } catch (Exception e2) {
        }
        try {
            this.B.F = IB.D(this.B.D, this.D.C() == KW.NATIVE_250 || this.D.C() == KW.NATIVE_UNKNOWN || this.D.C() == KW.NATIVE_BANNER || this.D.C() == null);
            this.B.F.F(this.B.E, this.B.F.C().B(this.B.C), this.B.V(C0622Jj.B()));
        } catch (Exception e3) {
            this.B.Z(KQ.C(AdErrorType.AD_REQUEST_FAILED, e3.getMessage()));
        }
    }
}
