package com.facebook.ads.redexgen.X;

import android.content.Intent;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0527Fq {
    private final C0531Fu B;
    private final JV C;
    private final Intent D;

    private C0527Fq(C0531Fu anActivityImpl, Intent intent, JV adEventManager) {
        this.B = anActivityImpl;
        this.D = intent;
        this.C = adEventManager;
    }

    public /* synthetic */ C0527Fq(C0531Fu x0, Intent x1, JV x2, C0524Fn x3) {
        this(x0, x1, x2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq L() {
        return new C0567Hf(this.B, this.C, new C0526Fp(this.B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq M() {
        return new FV(this.B.B(), (C1W) this.D.getSerializableExtra("rewardedVideoAdDataBundle"), this.C, new C0530Ft(this.B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq N() {
        return new C03428n(this.B.C.getApplicationContext(), this.C, new C0526Fp(this.B), V());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq O(RelativeLayout relativeLayout) {
        C7 c7 = new C7(this.B, this.C, new C0526Fp(this.B));
        c7.A(relativeLayout);
        c7.B(this.D.getIntExtra("video_time_polling_interval", 200));
        J6.O(relativeLayout, ViewCompat.MEASURED_STATE_MASK);
        return c7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq P() {
        return new C8S(this.B.B(), this.C, new H2(this.B.B()), new C0526Fp(this.B), V());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq Q() {
        return new C0465Dg(this.B.B(), V(), this.C, new C0526Fp(this.B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq R() {
        return new DE(this.B.B(), this.C, V(), new C0526Fp(this.B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq S() {
        return new D1(this.B.B(), this.C, V(), new H2(this.B.B()), new C0526Fp(this.B));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq T() {
        return new CO(this.B.B(), this.C, new C0447Co(this.B.B()), new C0530Ft(this.B), (C01601n) this.D.getSerializableExtra("rewardedVideoAdDataBundle"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InterfaceC0578Hq U() {
        return new C0436Cd(this.B.B(), this.C, new C0530Ft(this.B), (C01601n) this.D.getSerializableExtra("rewardedVideoAdDataBundle"));
    }

    private C01481b V() {
        return (C01481b) this.D.getSerializableExtra("ad_data_bundle");
    }
}
