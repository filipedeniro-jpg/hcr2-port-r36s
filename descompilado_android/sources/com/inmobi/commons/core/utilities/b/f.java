package com.inmobi.commons.core.utilities.b;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: IdentityInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {
    private static final String a = "f";

    public static Map<String, String> a() {
        HashMap map = new HashMap();
        try {
            map.put("mk-version", com.inmobi.commons.a.b.a());
            com.inmobi.commons.core.utilities.uid.c.a();
            Boolean boolG = com.inmobi.commons.core.utilities.uid.c.g();
            if (boolG != null) {
                map.put("u-id-adt", String.valueOf(boolG.booleanValue() ? 1 : 0));
            }
            map.put("ts", String.valueOf(Calendar.getInstance().getTimeInMillis()));
            Calendar calendar = Calendar.getInstance();
            map.put("tz", String.valueOf(calendar.get(15) + calendar.get(16)));
            h hVarA = h.a();
            HashMap map2 = new HashMap();
            if (hVarA.d && hVarA.a != null) {
                map2.put("u-s-id", hVarA.a);
            }
            map.putAll(map2);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting UID info; ").append(e.getMessage());
        }
        return map;
    }
}
