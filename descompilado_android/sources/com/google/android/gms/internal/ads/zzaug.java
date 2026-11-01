package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(24)
public class zzaug extends zzauh {
    @Override // com.google.android.gms.internal.ads.zzaub
    public final boolean zza(Activity activity, Configuration configuration) {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcpz)).booleanValue()) {
            return false;
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqb)).booleanValue()) {
            return activity.isInMultiWindowMode();
        }
        zzuo.zzof();
        int iZza = zzawe.zza(activity, configuration.screenHeightDp);
        int iZza2 = zzawe.zza(activity, configuration.screenWidthDp);
        WindowManager windowManager = (WindowManager) activity.getApplicationContext().getSystemService("window");
        com.google.android.gms.ads.internal.zzp.zzjy();
        DisplayMetrics displayMetricsZza = zzatv.zza(windowManager);
        int i = displayMetricsZza.heightPixels;
        int i2 = displayMetricsZza.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        double d = activity.getResources().getDisplayMetrics().density;
        Double.isNaN(d);
        int iRound = ((int) Math.round(d + 0.5d)) * ((Integer) zzuo.zzoj().zzd(zzyt.zzcpy)).intValue();
        return !(zze(i, iZza + dimensionPixelSize, iRound) && zze(i2, iZza2, iRound));
    }

    @VisibleForTesting
    private static boolean zze(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }
}
