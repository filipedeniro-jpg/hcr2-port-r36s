package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.google.android.gms.drive.DriveFile;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2I, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2I extends AnonymousClass28 {
    public C2I(Context context, AnonymousClass25 adControllerConfig) {
        super(context, adControllerConfig);
    }

    private AnonymousClass12 E(final Runnable interstitialTimeout) {
        return new AnonymousClass12() { // from class: com.facebook.ads.redexgen.X.2H
            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void PE(C01340n adapter, String url, boolean playerHandles) {
                this.B.G.A();
                boolean z = !TextUtils.isEmpty(url);
                if (playerHandles && z) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(this.B.E instanceof Activity)) {
                        intent.addFlags(DriveFile.MODE_READ_ONLY);
                    }
                    intent.setData(Uri.parse(url));
                    this.B.E.startActivity(intent);
                }
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void QE(C01340n adapter) {
                this.B.G.G();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void RE(C01340n adapter) {
                this.B.G.H();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void SE(C01340n adapter) {
                if (adapter != this.B.H) {
                    return;
                }
                if (adapter == null) {
                    C0585Hx.D(this.B.E, "api", C0584Hw.V, new C0583Hv("Adapter is null on loadInterstitialAd"));
                    TE(adapter, AdError.internalError(2004));
                } else {
                    this.B.C().removeCallbacks(interstitialTimeout);
                    this.B.I = adapter;
                    super/*com.facebook.ads.redexgen.X.28*/.I();
                    this.B.G.B(adapter);
                }
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void TE(C01340n adapter, AdError error) {
                if (adapter != this.B.H) {
                    return;
                }
                this.B.C().removeCallbacks(interstitialTimeout);
                this.B.A(adapter);
                if (!JA.H(this.B.E)) {
                    this.B.H();
                }
                this.B.G.D(new KQ(error.getErrorCode(), error.getErrorMessage()));
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void UE(C01340n c01340n) {
                C0642Kd.B("onInterstitialLoggingImpression", "Interstitial impression fired");
                this.B.G.I();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void VE() {
                this.B.G.M();
            }

            @Override // com.facebook.ads.redexgen.X.AnonymousClass12
            public final void onInterstitialActivityDestroyed() {
                this.B.G.F();
            }
        };
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void G(C0O adAdapter, IP placement, IN placementAd, final Map<String, Object> map) {
        final C01340n c01340n = (C01340n) adAdapter;
        Runnable interstitialTimeout = new Runnable() { // from class: com.facebook.ads.redexgen.X.2G
            @Override // java.lang.Runnable
            public final void run() {
                this.B.J(map);
                this.B.A(c01340n);
                if (JA.H(this.B.E)) {
                    this.B.H = null;
                    this.B.G.D(new KQ(AdErrorType.INTERSTITIAL_AD_TIMEOUT, ""));
                } else {
                    this.B.H();
                }
            }
        };
        C().postDelayed(interstitialTimeout, placement.E().H());
        c01340n.A(this.E, E(interstitialTimeout), map, this.B.E, this.B.F, this.B.H);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass28
    public final void M() {
        ((C01340n) this.I).B();
    }
}
