package com.facebook.ads.redexgen.X;

import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0642Kd {
    private static final DateFormat B = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
    private static boolean C = false;

    private C0642Kd() {
    }

    public static void B(String methodName, String description) {
        if (!C) {
            return;
        }
        Log.d("ADNW_DEBUG_LOGGER", String.format(Locale.US, "%s %s", D(), description));
    }

    public static void C() {
        C = true;
    }

    private static String D() {
        return B.format(Calendar.getInstance().getTime());
    }
}
