package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@WorkerThread
public final class C0551Gp {
    public static String C = "";
    public static String B = "";
    public static boolean E = false;
    public static String D = "";

    private C0551Gp() {
    }

    public static void B(Context context) {
        C0550Go c0550GoB = null;
        C0552Gq c0552GqB = null;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(I6.C("SDKIDFA", context), 0);
            if (sharedPreferences.contains("attributionId")) {
                C = sharedPreferences.getString("attributionId", "");
            }
            if (sharedPreferences.contains("advertisingId")) {
                B = sharedPreferences.getString("advertisingId", "");
                E = sharedPreferences.getBoolean("limitAdTracking", E);
                D = EnumC0549Gn.SHARED_PREFS.name();
            }
            try {
                c0552GqB = C0553Gr.B(context.getContentResolver());
            } catch (Exception e) {
                C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.JB, new C0583Hv(e));
            }
            if (c0552GqB != null && c0552GqB.C != null) {
                C = c0552GqB.C;
            }
            if (MK.E() && MK.D("aid_override")) {
                C = MK.B("aid_override");
            }
            try {
                c0550GoB = C0550Go.B(context, c0552GqB);
            } catch (Exception e2) {
                C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.IB, new C0583Hv(e2));
            }
            if (c0550GoB != null) {
                String strA = c0550GoB.A();
                Boolean boolValueOf = Boolean.valueOf(c0550GoB.C());
                if (strA != null) {
                    B = strA;
                    E = boolValueOf.booleanValue();
                    String tmpAdvertisingId = c0550GoB.B().name();
                    D = tmpAdvertisingId;
                }
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("attributionId", C);
            editorEdit.putString("advertisingId", B);
            editorEdit.putBoolean("limitAdTracking", E);
            editorEdit.apply();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
