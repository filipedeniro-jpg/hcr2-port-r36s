package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.os.Build;

/* JADX INFO: compiled from: PermissionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static boolean a(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.checkSelfPermission(str) == 0;
        }
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
