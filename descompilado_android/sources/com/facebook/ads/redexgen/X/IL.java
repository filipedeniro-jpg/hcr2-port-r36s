package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.WorkerThread;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IL {
    public static final String C = Build.VERSION.RELEASE;
    private final Context B;

    public IL(Context context) {
        this.B = context.getApplicationContext();
    }

    public final int A() {
        return MP.D(this.B);
    }

    @SuppressLint({"CatchGeneralException"})
    public final String B() {
        try {
            CharSequence applicationLabel = this.B.getPackageManager().getApplicationLabel(this.B.getPackageManager().getApplicationInfo(E(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        } catch (Throwable th) {
            C0585Hx.F(this.B, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.EB, new C0583Hv(th));
        }
        return "";
    }

    @SuppressLint({"CatchGeneralException"})
    public final String C() {
        String str = null;
        try {
            str = this.B.getPackageManager().getPackageInfo(E(), 0).versionName;
        } catch (Throwable th) {
            C0585Hx.F(this.B, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.EB, new C0583Hv(th));
        }
        return !TextUtils.isEmpty(str) ? str : "";
    }

    @SuppressLint({"CatchGeneralException"})
    public final int D() {
        int i = 0;
        try {
            i = this.B.getPackageManager().getPackageInfo(E(), 0).versionCode;
            return i;
        } catch (Throwable th) {
            C0585Hx.F(this.B, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.EB, new C0583Hv(th));
            return i;
        }
    }

    public final String E() {
        PendingIntent activity = PendingIntent.getActivity(this.B, 0, new Intent(), 0);
        if (activity == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.getCreatorPackage();
        }
        return activity.getTargetPackage();
    }

    @WorkerThread
    public final String F() {
        String networkOperatorName;
        TelephonyManager telephonyManager = (TelephonyManager) this.B.getSystemService("phone");
        return (telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null || networkOperatorName.length() <= 0) ? "" : networkOperatorName;
    }

    public final String G() {
        return (Build.MANUFACTURER == null || Build.MANUFACTURER.length() <= 0) ? "" : Build.MANUFACTURER;
    }

    public final String H() {
        return (Build.MODEL == null || Build.MODEL.length() <= 0) ? "" : Build.MODEL;
    }

    @SuppressLint({"CatchGeneralException"})
    public final String I() {
        String pkgName;
        try {
            String pkgName2 = E();
            if (pkgName2 != null && pkgName2.length() >= 0 && (pkgName = this.B.getPackageManager().getInstallerPackageName(pkgName2)) != null && pkgName.length() > 0) {
                return pkgName;
            }
        } catch (Throwable th) {
            C0585Hx.F(this.B, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.EB, new C0583Hv(th));
        }
        return "";
    }

    public final boolean J() {
        return this.B.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }
}
