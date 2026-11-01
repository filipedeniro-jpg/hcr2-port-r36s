package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.BuildConfig;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class L0 {
    private static final Pattern B = Pattern.compile("(\\s+at\\s+[a-z.]+)[A-Z].*");

    private L0() {
    }

    public static String E(int sampleRate) {
        if (sampleRate > 0 && new Random().nextFloat() < 1.0f / sampleRate) {
            return H();
        }
        return null;
    }

    public static String F(Context ctx, @Nullable Throwable throwable) {
        int maxStacktraceLines = JA.e(ctx);
        return K(throwable, maxStacktraceLines, JA.P(ctx), JA.AB(ctx));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String G(String line) {
        Matcher matcher = B.matcher(line);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return line;
    }

    private static String H() {
        return K(new Exception("Current stack trace"), -1, -1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean I(LB window) {
        String strD = window.D();
        if (strD == null) {
            return false;
        }
        if (J(strD)) {
            return true;
        }
        Iterator<String> it = window.C().iterator();
        while (it.hasNext()) {
            if (J(it.next())) {
                return true;
            }
        }
        Iterator<String> it2 = window.B().iterator();
        while (it2.hasNext()) {
            if (J(it2.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean J(String line) {
        return line.contains(BuildConfig.APPLICATION_ID);
    }

    private static String K(@Nullable Throwable throwable, int limitContextLines, int maxStacktraceLines, boolean groupStacktraceLines) {
        LA l7;
        if (throwable == null) {
            return "";
        }
        try {
            L8 l8 = new L8();
            if (maxStacktraceLines >= 0) {
                l7 = new L6(l8, maxStacktraceLines);
            } else {
                l7 = l8;
            }
            if (limitContextLines >= 0) {
                l7 = new L1(l7, limitContextLines, limitContextLines);
            }
            if (groupStacktraceLines) {
                l7 = new L7(l7);
            }
            L9 l9 = new L9(l8, 1, l7);
            throwable.printStackTrace(new PrintWriter(new L5(l9)));
            l9.flush();
            return l8.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
