package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EO implements EN {
    public final /* synthetic */ C0503Es B;

    public EO(C0503Es this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.EN
    public final void bF(AbstractC0502Er viewHolder, EX preInfo, EX info) {
        this.B.F(viewHolder, preInfo, info);
    }

    @Override // com.facebook.ads.redexgen.X.EN
    public final void dF(AbstractC0502Er viewHolder, @NonNull EX info, @Nullable EX postInfo) {
        this.B.X.Z(viewHolder);
        this.B.G(viewHolder, info, postInfo);
    }

    @Override // com.facebook.ads.redexgen.X.EN
    public final void fF(AbstractC0502Er viewHolder, @NonNull EX preInfo, @NonNull EX postInfo) {
        viewHolder.d(false);
        if (this.B.H) {
            if (this.B.N.B(viewHolder, viewHolder, preInfo, postInfo)) {
                this.B.r();
            }
        } else {
            if (!this.B.N.D(viewHolder, preInfo, postInfo)) {
                return;
            }
            this.B.r();
        }
    }

    @Override // com.facebook.ads.redexgen.X.EN
    public final void zF(AbstractC0502Er viewHolder) {
        this.B.R.jA(viewHolder.B, this.B.X);
    }
}
