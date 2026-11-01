package com.amazon.device.ads;

import android.content.Context;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
class DisplayUtils {
    private static int[][] rotationArray = {new int[]{1, 0, 9, 8}, new int[]{0, 9, 8, 1}};

    DisplayUtils() {
    }

    public static int determineCanonicalScreenOrientation(Context context, AndroidBuildInfo androidBuildInfo) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = context.getResources().getConfiguration().orientation;
        char c = 0;
        if (i != 1 ? i != 2 || rotation == 1 || rotation == 3 : rotation == 0 || rotation == 2) {
            c = 1;
        }
        return rotationArray[c ^ 1][rotation];
    }
}
