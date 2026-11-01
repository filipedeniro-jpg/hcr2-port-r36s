package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class DE extends AbstractC0466Dh {
    private final InterfaceC0528Fr B;
    private AnonymousClass81 C;
    private boolean D;

    public DE(Context context, JV adEventManager, C01481b adDataBundle, InterfaceC0525Fo listener) {
        super(context, adEventManager, listener, adDataBundle);
        this.B = new C0461Dc(this);
    }

    private void E() {
        if (TextUtils.isEmpty(super.C.C())) {
            return;
        }
        BB adWebView = this.C.getAdWebView();
        C5I viewabilityChecker = adWebView != null ? adWebView.getViewabilityChecker() : null;
        C0621Ji touchDataRecorder = adWebView != null ? adWebView.getTouchDataRecorder() : null;
        HashMap map = new HashMap();
        if (viewabilityChecker != null) {
            viewabilityChecker.A(map);
            map.put("touch", MJ.C(touchDataRecorder.m258B()));
        }
        super.D.iC(super.C.C(), map);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        super.F(activityImpl);
        activityImpl.A(this.B);
        C01581l c01581lB = C01581l.B(super.C);
        this.C = new AnonymousClass81(activityImpl.B(), c01581lB, getAdEventManager(), getAudienceNetworkListener(), new DF(activityImpl.B(), this, super.C, getAdEventManager(), getAudienceNetworkListener()), c01581lB.B().F() > 0, true);
        B(this.C, true, 1);
        this.E.setVisibility(8);
        this.C.A();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        this.C.C();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        super.onDestroy();
        E();
        this.C.B();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        this.C.D();
    }
}
