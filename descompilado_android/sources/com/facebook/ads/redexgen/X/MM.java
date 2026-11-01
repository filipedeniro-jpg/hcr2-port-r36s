package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MM {
    private MM() {
    }

    public static void B(Context context, String message) {
        if (AdInternalSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", message + " (displayed for test ads only)");
        }
    }

    public static <P, PR, R> AsyncTask<P, PR, R> C(Executor executor, AsyncTask<P, PR, R> asyncTask, P... pArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(executor, pArr);
        } else {
            asyncTask.execute(pArr);
        }
        return asyncTask;
    }

    public static void D() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable th) {
        }
    }
}
