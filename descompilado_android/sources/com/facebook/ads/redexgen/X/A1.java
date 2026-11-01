package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class A1 {
    private A1() {
    }

    public static AbstractC03789x B(AF params, int availableHeight, int availableWidth, boolean loadUrlOnStart) {
        C1Y colorInfo;
        if (params.H() == 1) {
            colorInfo = params.C().D().B();
        } else {
            colorInfo = params.C().D().A();
        }
        C01491c c01491c = params.C().A().get(0);
        Context contextB = params.B();
        JV jvA = params.A();
        String strC = params.C().C();
        C01491c adInfo = params.C().A().get(0);
        AnonymousClass09 anonymousClass09C = C0A.C(contextB, jvA, strC, Uri.parse(adInfo.A().C()), new HashMap(), false, true);
        if (!params.C().A().get(0).F() || !(anonymousClass09C instanceof C0H)) {
            return null;
        }
        C03719q c03719q = new C03719q(params, colorInfo, availableHeight, availableWidth, (C0H) anonymousClass09C, loadUrlOnStart);
        c03719q.I(c01491c);
        return c03719q;
    }
}
