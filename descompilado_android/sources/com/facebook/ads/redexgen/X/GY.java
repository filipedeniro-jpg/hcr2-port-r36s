package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAd;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GY implements InterfaceC01280h {
    public final /* synthetic */ GZ B;
    public final /* synthetic */ List C;

    public GY(GZ this$1, List list) {
        this.B = this$1;
        this.C = list;
    }

    private void B() {
        this.B.B.F = true;
        this.B.B.H.clear();
        this.B.B.D = 0;
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            this.B.B.H.add(new NativeAd(this.B.B.B, new KF(this.B.B.B, (C01360p) it.next(), null, KF.c(), this.B.B.C)));
        }
        if (this.B.B.G == null) {
            return;
        }
        this.B.B.G.onAdsLoaded();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void dD() {
        B();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01280h
    public final void nD() {
        B();
    }
}
