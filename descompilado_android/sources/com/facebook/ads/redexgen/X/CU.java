package com.facebook.ads.redexgen.X;

import com.google.android.exoplayer2.DefaultRenderersFactory;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CU extends FO {
    public final /* synthetic */ CO B;

    public CU(CO this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(C03026z event) {
        if (this.B.j != null && this.B.C != null) {
            if (JA.y(this.B.L) && this.B.j.getState() == C5K.BUFFERING) {
                this.B.a.postDelayed(new CV(this, this.B.j.getCurrentPositionInMillis()), DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
            if (this.B.j.getDuration() - this.B.j.getCurrentPositionInMillis() <= 3000 && this.B.C.B()) {
                this.B.C.A();
            }
            if (this.B.e()) {
                int unskippableSeconds = this.B.B.K().F();
                int duration = unskippableSeconds == 0 ? this.B.j.getDuration() : Math.min(unskippableSeconds * 1000, this.B.j.getDuration());
                int totalTimeInMillis = event.A();
                int i = duration - totalTimeInMillis;
                int remainingUnskippableTimeInMillis = i / 1000;
                this.B.n(remainingUnskippableTimeInMillis);
                if (i <= 0) {
                    this.B.h();
                    if (this.B.D != null) {
                        this.B.D.A(true);
                    }
                }
            }
        }
    }
}
