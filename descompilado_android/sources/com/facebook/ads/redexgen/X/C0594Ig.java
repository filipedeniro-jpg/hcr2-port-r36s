package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ig, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0594Ig implements InterfaceC0592Ie {
    public final /* synthetic */ String[] B;

    public C0594Ig(String[] strArr) {
        this.B = strArr;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0592Ie
    public final boolean RB(String parameter) {
        for (String str : this.B) {
            if (str.equals(parameter)) {
                return false;
            }
        }
        return true;
    }
}
