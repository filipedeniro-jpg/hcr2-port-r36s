package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3v, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02203v extends C4K {
    private static final String C = C02203v.class.getSimpleName();

    @InterfaceC01641r
    private final PackageInfo B;

    public C02203v(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        this.B = C(context);
    }

    @InterfaceC01641r
    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    private PackageInfo C(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(C, "Missing Package Permissions", e);
            return null;
        }
    }

    public final C4S L() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.3w
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.B != null ? this.B.E(this.B.B.firstInstallTime) : this.B.D(C3V.NULL);
            }
        };
    }
}
