package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C1H extends C1G<HZ> {
    private static final int F = (int) (4.0f * J6.B);
    public final List<KF> B;

    @Nullable
    private C1F C;
    private final int D;
    private final C1B E = new C1B() { // from class: com.facebook.ads.redexgen.X.1C
        @Override // com.facebook.ads.redexgen.X.C1B
        public final void B() {
            if (this.B.C == null) {
                return;
            }
            this.B.C.eD();
        }
    };

    public C1H(C0480Dv recyclerView, List<KF> list) {
        this.D = recyclerView.getChildSpacing();
        this.B = list;
    }

    @Override // com.facebook.ads.redexgen.X.C1G
    public final int C() {
        return this.B.size();
    }

    private ViewGroup.MarginLayoutParams D(int position) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(position == 0 ? this.D * 2 : this.D, 0, position >= this.B.size() + (-1) ? this.D * 2 : this.D, 0);
        return marginLayoutParams;
    }

    public final void Q(ImageView imageView, final int position) {
        final KF kf = this.B.get(position);
        KG adCoverImage = kf.getAdCoverImage();
        if (adCoverImage != null) {
            B7 b7B = new B7(imageView).B();
            b7B.C(new C1D() { // from class: com.facebook.ads.redexgen.X.1E
                @Override // com.facebook.ads.redexgen.X.C1D
                public final void FE(boolean success) {
                    if (position == 0) {
                        kf.g(this.B.E);
                    }
                    kf.Q(success, true);
                }
            });
            b7B.E(adCoverImage.getUrl());
        }
    }

    @Override // com.facebook.ads.redexgen.X.C1G
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public void M(HZ holder, int position) {
        holder.h().setLayoutParams(D(position));
    }

    public final void S(C1F listener) {
        this.C = listener;
    }
}
