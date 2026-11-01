package com.inmobi.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.utilities.b.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: FileManager.java */
/* JADX INFO: loaded from: classes2.dex */
final class a {
    @SuppressLint({"SdCardPath"})
    public static boolean a(Context context) {
        List listAsList = Arrays.asList("carbpreference", "IMAdMLtvpRuleCache", "inmobiAppAnalyticsSession", "aeskeygenerate", "impref", "IMAdTrackerStatusUpload", "IMAdMMediationCache", "inmobiAppAnalyticsAppId", "inmobiAppAnalyticsSession", "inmobisdkaid", "IMAdTrackerStatusUpload", "testAppPref");
        for (int i = 0; i < listAsList.size(); i++) {
            File file = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) listAsList.get(i)) + ".xml");
            if (file.exists()) {
                file.delete();
            }
        }
        List listAsList2 = Arrays.asList(c.a("carb_store"), c.a("config_store"), c.a("aes_key_store"), c.a("mraid_js_store"), g.a());
        for (int i2 = 0; i2 < listAsList2.size(); i2++) {
            File file2 = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + ((String) listAsList2.get(i2)) + ".xml");
            if (file2.exists()) {
                file2.delete();
            }
        }
        List listAsList3 = Arrays.asList("inmobi.cache", "inmobi.cache.data", "inmobi.cache.data.events.number", "inmobi.cache.data.events.timestamp");
        for (int i3 = 0; i3 < listAsList3.size(); i3++) {
            if (context.getCacheDir() != null) {
                File file3 = new File(context.getCacheDir(), (String) listAsList3.get(i3));
                if (file3.exists()) {
                    file3.delete();
                }
            }
        }
        List listAsList4 = Arrays.asList("eventlog", "imai_click_events");
        for (int i4 = 0; i4 < listAsList4.size(); i4++) {
            if (context.getDir("data", 0) != null) {
                File file4 = new File(context.getDir("data", 0), (String) listAsList4.get(i4));
                if (file4.exists()) {
                    file4.delete();
                }
            }
        }
        return b(context).size() != 0;
    }

    private static boolean a(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        return databasePath == null || !databasePath.exists() || context.deleteDatabase(str);
    }

    public static List<String> b(Context context) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        hashSet.add("adcache.db");
        hashSet.add("appengage.db");
        hashSet.add("im.db");
        hashSet.add("ltvp.db");
        hashSet.add("analytics.db");
        hashSet.add("com.im.db");
        String[] strArrDatabaseList = context.databaseList();
        if (strArrDatabaseList != null && strArrDatabaseList.length > 0) {
            for (String str : strArrDatabaseList) {
                if (hashSet.contains(str) && !a(context, str)) {
                    arrayList.add(str);
                } else if (str.matches("com\\.im_([0-9]+\\.){3}db") && !str.equals(com.inmobi.commons.core.d.a.a) && !a(context, str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
}
