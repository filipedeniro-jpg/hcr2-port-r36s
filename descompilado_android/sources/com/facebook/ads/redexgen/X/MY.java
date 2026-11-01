package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MY {
    private static final String B = MY.class.getSimpleName();

    private MY() {
    }

    public static boolean B(int screenWidth, int screenHeight) {
        return screenWidth >= 640 && screenHeight >= 640;
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean C() {
        try {
            Class.forName("com.facebook.react.ReactActivity");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean D(Context context) {
        boolean activityDeclared = true;
        boolean z = false;
        try {
            for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                if ("com.unity3d.player.UnityPlayerActivity".equals(activityInfo.name)) {
                    z = true;
                }
            }
            if (!z && !E()) {
                activityDeclared = false;
            }
            if (BuildConfigApi.isDebug()) {
                Log.d(B, "Is Unity app: " + activityDeclared);
                return activityDeclared;
            }
            return activityDeclared;
        } catch (Throwable th) {
            if (BuildConfigApi.isDebug()) {
                Log.e(B, "Can't determine if app is Unity.", th);
            }
            return false;
        }
    }

    private static boolean E() {
        try {
            Class.forName("com.unity3d.player.UnityPlayerActivity");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
