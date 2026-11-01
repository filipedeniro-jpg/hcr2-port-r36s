package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseIntArray;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@AnyThread
public final class C0585Hx {

    @Nullable
    public static IX D;
    private static final Set<Integer> F = new HashSet();

    @VisibleForTesting
    public static SparseIntArray E = new SparseIntArray();

    @VisibleForTesting
    public static Executor G = Executors.newSingleThreadExecutor();
    private static final List<Integer> B = Arrays.asList(10, 50, 100, 1000);
    public static boolean C = false;

    private C0585Hx() {
    }

    public static void C(Throwable t) {
        if (C) {
            throw new RuntimeException("Exception should not happen here.", t);
        }
    }

    public static void D(@Nullable Context context, String subtype, int subtypeCode, C0583Hv exception) {
        if (context == null) {
            L(new RuntimeException("Can't log Debug Event. Context is null."));
            return;
        }
        HM.C(context);
        Context context2 = context.getApplicationContext();
        if (C && exception.B() == 0) {
            String str = "Debug crash because of event with subtype = " + subtype + ", subtypeCode = " + subtypeCode;
            if (!I6.D || subtypeCode != C0584Hw.E) {
                throw new RuntimeException(str, exception);
            }
        }
        try {
            if (!K(context2, subtype, subtypeCode, Math.random())) {
                return;
            }
            I(context2, subtype, subtypeCode, exception);
        } catch (Throwable th) {
            L(th);
        }
    }

    public static void E(String subtype, int subtypeCode, C0583Hv exception) {
        D(HM.B(), subtype, subtypeCode, exception);
    }

    public static void F(Context context, String subtype, int subtypeCode, C0583Hv exception) {
        try {
            if (JA.CB(context)) {
                boolean z = false;
                synchronized (F) {
                    if (!F.contains(Integer.valueOf(subtypeCode))) {
                        F.add(Integer.valueOf(subtypeCode));
                        z = true;
                    }
                }
                if (z && K(context, subtype, subtypeCode, Math.random())) {
                    I(context, subtype, subtypeCode, exception);
                }
            }
        } catch (Throwable th) {
            L(th);
        }
    }

    @VisibleForTesting
    public static boolean G(Context context, String subtype, int subtypeCode) {
        if (!JA.r(context)) {
            return true;
        }
        int i = E.get(subtypeCode);
        int iB = JA.B(context);
        int iH = H(subtype, context);
        if (iB < iH) {
            iB = iH;
        }
        if (i >= iB) {
            if (B.contains(Integer.valueOf(i))) {
                J(context, subtypeCode, i);
            }
            E.put(subtypeCode, i + 1);
            return true;
        }
        E.put(subtypeCode, i + 1);
        return false;
    }

    private static int H(String subtype, Context context) {
        if ("cache".equals(subtype)) {
            return 200;
        }
        if ("bot_detection_wo_signal_data_logging".equals(subtype)) {
            return 50;
        }
        if ("bd_data_logging".equals(subtype)) {
            return JA.T(context);
        }
        return -1;
    }

    private static void I(Context appContext, String subtype, int subtypeCode, C0583Hv exception) {
        boolean z = D != null && D.XC();
        if (BuildConfigApi.isDebug() || z) {
            if (exception.B() == 0) {
                Log.e(AudienceNetworkAds.TAG, "Exception Debug Event with subtype = " + subtype + ", subtypeCode = " + subtypeCode, exception);
            } else {
                Log.i(AudienceNetworkAds.TAG, "Info Debug Event with subtype = " + subtype + ", subtypeCode = " + subtypeCode + ", message = " + exception.getMessage() + ", additionalInfo = " + exception.A());
            }
        }
        G.execute(new RunnableC0586Hy(appContext, subtype, subtypeCode, exception));
    }

    private static void J(Context context, int subtypeCode, int currentCounter) {
        D(context, "de_logging", C0584Hw.rB, new C0583Hv("Too many events of subtype code: " + subtypeCode, "Counter: " + currentCounter));
    }

    @VisibleForTesting
    @SuppressLint({"CatchGeneralException"})
    private static boolean K(Context appContext, String subtype, int subtypeCode, double random) {
        double d;
        try {
            int additionalDebugLoggingSamplingPercentage = JA.S(appContext);
            if (additionalDebugLoggingSamplingPercentage >= 1) {
                HashMap<String, Integer> mapR = JA.R(appContext);
                String str = subtype + ":" + subtypeCode;
                boolean zContains = mapR.keySet().contains(str);
                if (zContains) {
                    Integer tempSampling = mapR.get(str);
                    int sampling = tempSampling != null ? tempSampling.intValue() : -1;
                    if (sampling == 0) {
                        return false;
                    }
                    if (sampling > 0) {
                        return random <= 1.0d / ((double) sampling);
                    }
                    int iQ = JA.Q(appContext);
                    if (iQ < 1) {
                        return false;
                    }
                    d = ((double) (additionalDebugLoggingSamplingPercentage * iQ)) / 10000.0d;
                } else {
                    d = ((double) additionalDebugLoggingSamplingPercentage) / 100.0d;
                }
                if ("cache".equals(subtype)) {
                    int cacheEventsSampling = JA.U(appContext);
                    if (cacheEventsSampling == 0) {
                        return false;
                    }
                    if (cacheEventsSampling > 0) {
                        double d2 = 1.0d / ((double) cacheEventsSampling);
                        if (zContains) {
                            double cacheEventLogProbability = LX.C();
                            return cacheEventLogProbability <= d2 * d;
                        }
                        double cacheEventLogProbability2 = LX.C();
                        return cacheEventLogProbability2 <= d2;
                    }
                }
                return random >= 1.0d - d;
            }
            return false;
        } catch (Throwable th) {
            L(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void L(Throwable t) {
        Log.e(AudienceNetworkAds.TAG, "Exception during logging debug event.", t);
        if (C) {
            throw new RuntimeException(t);
        }
    }
}
