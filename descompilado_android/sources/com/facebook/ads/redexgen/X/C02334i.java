package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.SystemClock;
import android.view.OrientationEventListener;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02334i {

    @InterfaceC01641r
    private static OrientationEventListener D;
    private final EnumC02053g B;
    private final C02354k C;

    public C02334i(C02354k biometricSignalValueHandler, EnumC02053g bdIntegrationSite) {
        this.C = biometricSignalValueHandler;
        this.B = bdIntegrationSite;
    }

    public final synchronized void A() {
        if (D != null) {
            D.disable();
            D = null;
        }
    }

    public final synchronized void B(final Context context) {
        if (D == null) {
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            final C3T c3t = this.B == EnumC02053g.OFFSITE ? null : new C3T(context == null ? "" : context.getPackageName());
            final int i = 3;
            D = new OrientationEventListener(context, i) { // from class: com.facebook.ads.redexgen.X.4j
                @Override // android.view.OrientationEventListener
                public final void onOrientationChanged(int degrees) {
                    this.B.C.A(new C01993a(jElapsedRealtime, c3t, Integer.valueOf(degrees)), EnumC02374m.ADD_ORIENTATION_SENSOR_VALUE);
                }
            };
            D.enable();
        }
    }
}
