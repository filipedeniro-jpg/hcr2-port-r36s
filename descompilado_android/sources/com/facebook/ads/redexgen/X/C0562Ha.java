package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ha, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0562Ha extends C0503Es {
    public C0562Ha(Context context) {
        super(context);
        setCarouselLayoutManager(context);
    }

    @Override // com.facebook.ads.redexgen.X.C0503Es
    public C0484Dz getLayoutManager() {
        return (C0484Dz) super.getLayoutManager();
    }

    private void setCarouselLayoutManager(Context context) {
        C0484Dz c0484Dz = new C0484Dz(context, 0, false);
        if (Build.VERSION.SDK_INT >= 24) {
            c0484Dz.tA(true);
        }
        super.setLayoutManager(c0484Dz);
    }

    @Override // com.facebook.ads.redexgen.X.C0503Es
    public void setLayoutManager(AbstractC0481Dw layout) {
    }
}
