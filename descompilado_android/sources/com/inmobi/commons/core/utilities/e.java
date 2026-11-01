package com.inmobi.commons.core.utilities;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: compiled from: PermissionUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static boolean a(Context context, String str, String str2) {
        try {
            return context.checkCallingOrSelfPermission(str2) == 0;
        } catch (Exception e) {
            try {
                HashMap map = new HashMap();
                map.put("type", "RuntimeException");
                map.put("message", e.getMessage());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a(str, "ExceptionCaught", map);
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e.getMessage());
                sb.append(")");
            }
            return false;
        }
    }
}
