package com.chartboost.sdk.impl;

import android.app.Application;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class o {
    static List<String> a = new ArrayList();
    static HashMap<String, p> b = new HashMap<>();

    static boolean b() {
        return true;
    }

    public static void a() {
        a.clear();
        if (b()) {
            a.add("moat");
        }
    }

    public static void a(List<String> list) {
        for (String str : list) {
            if (a.contains(str) && !b.containsKey(str)) {
                b.put(str, null);
            }
        }
    }

    public static void a(Application application, boolean z, boolean z2, boolean z3) {
        for (String str : b.keySet()) {
            if (str.contains("moat")) {
                if (b.get(str) != null) {
                    b.get(str).b();
                }
                r rVar = new r();
                rVar.a(application, z, z2, z3);
                b.put("moat", rVar);
            }
        }
    }

    public static void a(WebView webView, HashSet<String> hashSet) {
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            p pVar = b.get(it.next());
            if (pVar != null) {
                pVar.a(webView);
            }
        }
    }

    public static void c() {
        for (p pVar : b.values()) {
            if (pVar != null) {
                pVar.a();
            }
        }
    }

    public static void d() {
        Iterator<String> it = b.keySet().iterator();
        while (it.hasNext()) {
            p pVar = b.get(it.next());
            if (pVar != null) {
                pVar.b();
            }
        }
    }

    public static JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        List<String> list = a;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        return jSONArray;
    }

    public static JSONArray f() {
        JSONArray jSONArray = new JSONArray();
        HashMap<String, p> map = b;
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        return jSONArray;
    }

    public static String a(HashSet<String> hashSet) {
        JSONArray jSONArray = new JSONArray();
        for (String str : hashSet) {
            if (b.get(str) != null) {
                jSONArray.put(str);
            }
        }
        return jSONArray.toString();
    }
}
