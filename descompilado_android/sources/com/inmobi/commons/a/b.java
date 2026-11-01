package com.inmobi.commons.a;

import android.content.Context;
import com.inmobi.commons.core.d.c;

/* JADX INFO: compiled from: SdkInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public static String a() {
        String str = "";
        for (char c : "7.2.1".toCharArray()) {
            str = c == '.' ? str + "T" : str + ((char) ((c - '0') + 65));
        }
        return "pr-SAND-" + str + "-20180920";
    }

    public static String a(Context context) {
        return c.a(context, "sdk_version_store").c("sdk_version");
    }

    public static void a(Context context, boolean z) {
        c.a(context, "sdk_version_store").a("db_deletion_failed", z);
    }
}
