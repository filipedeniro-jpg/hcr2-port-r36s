package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JW extends HO<String> {
    public final /* synthetic */ JX B;
    public final /* synthetic */ JQ C;

    public JW(JX this$0, JQ jq) {
        this.B = this$0;
        this.C = jq;
    }

    @Override // com.facebook.ads.redexgen.X.HO
    public final void A(int code, String message) {
        super.A(code, message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.HO
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final void C(String result) {
        super.C(result);
        if (AnonymousClass59.G(this.B.B) && this.C.G() != EnumC0613Ja.VIDEO) {
            this.B.F.A(this.C.G().toString(), result);
        }
        if (this.C.I()) {
            this.B.D.B();
        } else {
            this.B.D.A();
        }
    }
}
