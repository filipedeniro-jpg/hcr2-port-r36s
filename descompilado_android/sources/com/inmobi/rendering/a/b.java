package com.inmobi.rendering.a;

import android.content.ContentValues;
import com.my.target.aq;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ClickDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    static final String[] a = {"id", "pending_attempts", "url", "ping_in_webview", "follow_redirect", "ts", "created_ts", "track_extras"};
    private static final String b = "b";

    b() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a(aq.a.dF, "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, ping_in_webview TEXT NOT NULL, follow_redirect TEXT NOT NULL, ts TEXT NOT NULL, track_extras TEXT, created_ts TEXT NOT NULL )");
        bVarA.b();
    }

    public static boolean a() {
        return com.inmobi.commons.core.d.b.a().a(aq.a.dF) == 0;
    }

    public final synchronized boolean a(a aVar, int i) {
        ContentValues contentValuesB = b(aVar);
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (bVarA.a(aq.a.dF) >= i) {
            try {
                HashMap map = new HashMap();
                map.put("pingUrl", aVar.b);
                map.put("errorCode", "MaxDbLimitBreach");
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("ads", "PingDiscarded", map);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e.getMessage());
                sb.append(")");
            }
            a aVarA = a(bVarA.a(aq.a.dF, a, "ts= (SELECT MIN(ts) FROM click LIMIT 1)", null, null, null, null, null).get(0));
            StringBuilder sb2 = new StringBuilder("Deleting click (");
            sb2.append(aVarA.a);
            sb2.append(")");
            a(aVarA);
        }
        bVarA.a(aq.a.dF, contentValuesB);
        bVarA.b();
        return true;
    }

    public static void a(a aVar) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a(aq.a.dF, "id = ?", new String[]{String.valueOf(aVar.a)});
        bVarA.b();
    }

    static a a(ContentValues contentValues) {
        int iIntValue = contentValues.getAsInteger("id").intValue();
        int iIntValue2 = contentValues.getAsInteger("pending_attempts").intValue();
        String asString = contentValues.getAsString("url");
        long jLongValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        long jLongValue2 = Long.valueOf(contentValues.getAsString("created_ts")).longValue();
        boolean zBooleanValue = Boolean.valueOf(contentValues.getAsString("follow_redirect")).booleanValue();
        boolean zBooleanValue2 = Boolean.valueOf(contentValues.getAsString("ping_in_webview")).booleanValue();
        String asString2 = contentValues.getAsString("track_extras");
        HashMap map = new HashMap();
        if (asString2 != null) {
            try {
                map.putAll(a(new JSONObject(asString2)));
            } catch (JSONException e) {
                StringBuilder sb = new StringBuilder("JSONException in parsing extras (");
                sb.append(e.getMessage());
                sb.append(")");
            } catch (Exception e2) {
                StringBuilder sb2 = new StringBuilder("Exception in parsing extras (");
                sb2.append(e2.getMessage());
                sb2.append(")");
            }
        }
        return new a(iIntValue, asString, map, zBooleanValue, zBooleanValue2, iIntValue2, jLongValue, jLongValue2);
    }

    static ContentValues b(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.a));
        contentValues.put("url", aVar.b);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.f));
        contentValues.put("ts", Long.toString(aVar.d));
        contentValues.put("created_ts", Long.toString(aVar.e));
        contentValues.put("follow_redirect", Boolean.toString(aVar.i));
        contentValues.put("ping_in_webview", Boolean.toString(aVar.h));
        if (aVar.c != null && aVar.c.size() > 0) {
            contentValues.put("track_extras", new JSONObject(aVar.c).toString());
        }
        return contentValues;
    }

    private static Map<String, String> a(JSONObject jSONObject) throws JSONException {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, (String) jSONObject.get(next));
        }
        return map;
    }
}
