package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants;
import java.util.Locale;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.In, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0601In {
    public static String B = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    private static final AtomicBoolean D = new AtomicBoolean();
    private static final AtomicReference<String> C = new AtomicReference<>();

    private C0601In() {
    }

    public static String E(Context context, IL deviceTrackingParams) {
        String strE = deviceTrackingParams.E();
        if (TextUtils.isEmpty(strE) && !D.getAndSet(true)) {
            C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.OB, new C0583Hv("PI_NULL"));
        }
        return strE;
    }

    public static String F(IL deviceTrackingParams, Context context, boolean shouldCheckSystemHttpAgent) {
        return I(context, shouldCheckSystemHttpAgent) + " [FBAN/AudienceNetworkForAndroid;FBSN/" + Constants.JAVASCRIPT_INTERFACE_NAME + ";FBSV/" + IL.C + ";FBAB/" + E(context, deviceTrackingParams) + ";FBAV/" + deviceTrackingParams.C() + ";FBBV/" + deviceTrackingParams.D() + ";FBVS/" + BuildConfigApi.getVersionName() + ";FBLC/" + Locale.getDefault().toString() + Constants.RequestParameters.RIGHT_BRACKETS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(17)
    public static String G(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    @SuppressLint({"CatchGeneralException"})
    @Nullable
    private static String H(Context context) {
        FutureTask futureTask = new FutureTask(new CallableC0600Im(context));
        for (int i = 0; i < 3; i++) {
            ExecutorC0616Jd.B(futureTask);
            try {
                return (String) futureTask.get();
            } catch (Throwable th) {
                J(context, th);
                SystemClock.sleep(500L);
            }
        }
        return null;
    }

    @Nullable
    private static String I(Context context, boolean shouldCheckSystemHttpAgent) {
        if (context == null) {
            return B;
        }
        if (shouldCheckSystemHttpAgent) {
            return System.getProperty("http.agent");
        }
        String str = C.get();
        if (str != null) {
            return str;
        }
        String strH = H(context);
        if (strH != null) {
            return strH;
        }
        return B;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void J(Context context, Throwable executionException) {
        C0585Hx.D(context, "web_view", C0584Hw.wB, new C0583Hv(executionException));
    }
}
