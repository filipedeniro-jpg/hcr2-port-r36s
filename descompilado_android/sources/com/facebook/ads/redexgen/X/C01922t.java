package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import com.my.target.bj;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2t, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01922t implements InterfaceC01300j<Bundle> {
    private boolean B;
    private boolean C;
    private boolean D;
    private final AbstractC01260f E;
    private final C01912s F;
    private C01912s G;

    public C01922t(AbstractC01260f mRule) {
        this.C = false;
        this.D = false;
        this.B = false;
        this.E = mRule;
        this.F = new C01912s(mRule.D);
        this.G = new C01912s(mRule.D);
    }

    public C01922t(AbstractC01260f mRule, Bundle previousState) {
        this.C = false;
        this.D = false;
        this.B = false;
        this.E = mRule;
        this.F = (C01912s) LZ.B(previousState.getByteArray("testStats"));
        this.G = (C01912s) LZ.B(previousState.getByteArray("viewableStats"));
        this.C = previousState.getBoolean(Constants.ParametersKeys.VIDEO_STATUS_ENDED);
        this.D = previousState.getBoolean("passed");
        this.B = previousState.getBoolean(bj.gt);
    }

    public final void A(double seconds, double viewableRatio) {
        double dB;
        if (this.C) {
            return;
        }
        this.F.C(seconds, viewableRatio);
        this.G.C(seconds, viewableRatio);
        if (this.E.B) {
            dB = this.G.A().D();
        } else {
            dB = this.G.A().B();
        }
        if (this.E.C >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.F.A().E() > this.E.C && dB == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            B();
        } else {
            if (dB < this.E.E) {
                return;
            }
            D();
        }
    }

    private void B() {
        this.B = true;
        C();
    }

    private void C() {
        this.C = true;
        this.E.A(this.B, this.D, this.D ? this.G : this.F);
    }

    private void D() {
        this.D = true;
        B();
    }

    public final void E() {
        if (!this.C) {
            this.G.F();
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01300j
    public final Bundle getSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("viewableStats", LZ.C(this.G));
        bundle.putByteArray("testStats", LZ.C(this.F));
        bundle.putBoolean(Constants.ParametersKeys.VIDEO_STATUS_ENDED, this.C);
        bundle.putBoolean("passed", this.D);
        bundle.putBoolean(bj.gt, this.B);
        return bundle;
    }
}
