package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.BuildConfig;
import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.He, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0566He implements Thread.UncaughtExceptionHandler {
    private final Thread.UncaughtExceptionHandler B;
    private final Context C;
    private final Map<String, String> D;

    public C0566He(@Nullable Thread.UncaughtExceptionHandler chainedHandler, Context context, Map<String, String> map) {
        this.B = chainedHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.C = context.getApplicationContext();
        this.D = map;
    }

    private void B(Thread thread, Throwable ex) {
        if (this.B != null) {
            this.B.uncaughtException(thread, ex);
        } else {
            C();
        }
    }

    private static void C() {
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable th) {
        }
        try {
            System.exit(10);
        } catch (Throwable th2) {
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable ex) {
        try {
            String strF = L0.F(this.C, ex);
            if (!TextUtils.isEmpty(strF) && strF.contains(BuildConfig.APPLICATION_ID)) {
                Map<String, String> mapA = new C0565Hd(strF, this.D).A();
                mapA.put("subtype", "crash");
                mapA.put("subtype_code", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                double D = LX.D();
                String stackTraceString = LX.B();
                C0573Hl.B().DG(new C0574Hm(D, stackTraceString, mapA), this.C);
                if (JA.IB(this.C)) {
                    JA.F(this.C);
                }
            }
        } catch (Exception e) {
        }
        if (!JA.p(this.C) || !I6.D) {
            B(thread, ex);
        } else {
            try {
                Log.e(AudienceNetworkAds.TAG, "Facebook Audience Network process crashed with exception: ", ex);
            } catch (Throwable th) {
            }
            C();
        }
    }
}
