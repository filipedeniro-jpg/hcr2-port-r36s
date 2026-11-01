package com.inmobi.ads.cache;

import android.content.ContentValues;
import androidx.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: compiled from: AssetDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static final String b = "d";
    private static d c;
    private static final Object d = new Object();
    public static final String[] a = {"id", "pending_attempts", "url", "disk_uri", "ts", "created_ts", "ttl", "soft_ttl"};

    public static d a() {
        d dVar = c;
        if (dVar == null) {
            synchronized (d) {
                dVar = c;
                if (dVar == null) {
                    dVar = new d();
                    c = dVar;
                }
            }
        }
        return dVar;
    }

    private d() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("asset", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, disk_uri TEXT, ts TEXT NOT NULL, created_ts TEXT NOT NULL, ttl TEXT NOT NULL, soft_ttl TEXT NOT NULL)");
        bVarA.b();
    }

    public final synchronized void a(a aVar) {
        if (b(aVar) <= 0) {
            ContentValues contentValuesD = d(aVar);
            com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
            bVarA.a("asset", contentValuesD);
            bVarA.b();
        }
    }

    static List<a> a(int i) {
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (bVarA.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> listA = bVarA.a("asset", a, null, null, "ts", "ts < " + (System.currentTimeMillis() - ((long) i)), "ts ASC ", null);
        bVarA.b();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public static List<a> b() {
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (bVarA.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> listA = bVarA.a("asset", a, "disk_uri IS NOT NULL", null, null, null, "created_ts DESC ", null);
        bVarA.b();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public static String c() {
        List<a> listB = b();
        if (listB.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<a> it = listB.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(URLEncoder.encode(it.next().d, "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return jSONArray.toString();
    }

    @Nullable
    static a a(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", "1");
        bVarA.b();
        if (listA.isEmpty()) {
            return null;
        }
        return a(listA.get(0));
    }

    public static List<String> d() {
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (bVarA.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> listA = bVarA.a("asset", new String[]{"url"}, null, null, null, null, "created_ts DESC ", null);
        bVarA.b();
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAsString("url"));
        }
        return arrayList;
    }

    public static a b(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        List<ContentValues> listA = bVarA.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", "1");
        bVarA.b();
        if (listA.isEmpty()) {
            return null;
        }
        return a(listA.get(0));
    }

    public static int b(a aVar) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iB = bVarA.b("asset", d(aVar), "url = ?", new String[]{String.valueOf(aVar.d)});
        bVarA.b();
        return iB;
    }

    public static void c(a aVar) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("asset", "id = ?", new String[]{String.valueOf(aVar.b)});
        bVarA.b();
    }

    public static a a(ContentValues contentValues) {
        return new a(contentValues.getAsInteger("id").intValue(), contentValues.getAsString("url"), contentValues.getAsString("disk_uri"), contentValues.getAsInteger("pending_attempts").intValue(), Long.valueOf(contentValues.getAsString("ts")).longValue(), Long.valueOf(contentValues.getAsString("created_ts")).longValue(), Long.valueOf(contentValues.getAsString("ttl")).longValue(), Long.valueOf(contentValues.getAsString("soft_ttl")).longValue());
    }

    private static ContentValues d(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.b));
        contentValues.put("url", aVar.d);
        contentValues.put("disk_uri", aVar.e);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.c));
        contentValues.put("ts", Long.toString(aVar.f));
        contentValues.put("created_ts", Long.toString(aVar.g));
        contentValues.put("ttl", Long.toString(aVar.h));
        contentValues.put("soft_ttl", Long.toString(aVar.i));
        return contentValues;
    }
}
