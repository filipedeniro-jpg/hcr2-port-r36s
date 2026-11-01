package net.nend.android.internal.utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* JADX INFO: compiled from: MetaDataHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {
    public static boolean a(Context context, String str, boolean z) {
        Bundle bundleA = a(context);
        return bundleA != null ? bundleA.getBoolean(str, z) : z;
    }

    public static String a(Context context, String str, String str2) {
        Bundle bundleA = a(context);
        return (bundleA == null || bundleA.getString(str) == null) ? str2 : bundleA.getString(str);
    }

    public static boolean a(Context context, String str) {
        Bundle bundleA = a(context);
        return bundleA != null && bundleA.containsKey(str);
    }

    private static Bundle a(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            j.a(k.ERR_UNEXPECTED, e);
            return null;
        }
    }
}
