package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8P, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C8P extends AbstractC0502Er {
    private final SparseBooleanArray B;
    private final int C;
    private final int D;
    private final int E;
    private final C9K F;
    private C5I G;
    private final int H;

    @Nullable
    private C5I I;
    private C1B J;

    public C8P(C9K itemView, SparseBooleanArray cardsImpressions, C5I parentViewabilityChecker, int childWidth, int extraSpacing, int itemSpacing, int totalItems) {
        super(itemView);
        this.F = itemView;
        this.B = cardsImpressions;
        this.G = parentViewabilityChecker;
        this.C = childWidth;
        this.D = extraSpacing;
        this.E = itemSpacing;
        this.H = totalItems;
    }

    private void E(final JV adEventManager, final C0621Ji touchDataRecorder, final String clientToken, final C03298a cardInfo) {
        if (this.B.get(cardInfo.B())) {
            return;
        }
        if (this.I != null) {
            this.I.G();
            this.I = null;
        }
        final Map<String, String> mapC = cardInfo.C();
        this.J = new C1B() { // from class: com.facebook.ads.redexgen.X.8R
            @Override // com.facebook.ads.redexgen.X.C1B
            public final void B() {
                if (!this.B.G.C() && !TextUtils.isEmpty(clientToken) && !this.B.B.get(cardInfo.B())) {
                    if (this.B.I != null) {
                        this.B.I.A(mapC);
                    }
                    mapC.put("touch", MJ.C(touchDataRecorder.m258B()));
                    adEventManager.lC(clientToken, mapC);
                    this.B.B.put(cardInfo.B(), true);
                }
            }
        };
        this.I = new C5I(this.F, 10, this.J);
        this.I.D(100);
        this.I.E(100);
        this.F.setOnAssetsLoadedListener(new C9D() { // from class: com.facebook.ads.redexgen.X.8Q
            @Override // com.facebook.ads.redexgen.X.C9D
            public final void XD() {
                if (cardInfo.B() == 0) {
                    this.B.G.F();
                }
                this.B.I.F();
            }
        });
    }

    public final void h(C03298a cardInfo, JV adEventManager, H2 adCacheManager, C0621Ji touchDataRecorder, String clientToken) {
        int iB = cardInfo.B();
        this.F.setTag(-1593835536, Integer.valueOf(iB));
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(this.C, -2);
        marginLayoutParams.setMargins(iB == 0 ? this.D : this.E, 0, iB >= this.H + (-1) ? this.D : this.E, 0);
        String strB = cardInfo.A().C().B();
        String strH = cardInfo.A().C().H();
        this.F.setIsVideo(TextUtils.isEmpty(strH) ? false : true);
        if (this.F.S()) {
            this.F.setVideoPlaceholderUrl(strB);
            this.F.setVideoUrl(adCacheManager.K(strH));
        } else {
            this.F.setImageUrl(strB);
        }
        this.F.setLayoutParams(marginLayoutParams);
        this.F.setAdTitleAndDescription(cardInfo.A().D().I(), cardInfo.A().D().C());
        this.F.setCTAInfo(cardInfo.A().A(), cardInfo.C());
        this.F.Q(cardInfo.C());
        E(adEventManager, touchDataRecorder, clientToken, cardInfo);
    }
}
