package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public class ak {
    private static final String z = "ak";
    String a;
    String b;
    public al c;
    String d;
    Object e;
    JSONObject f;
    String g;
    boolean h;
    int i;
    String j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    String q;
    String r;
    String s;
    ak t;
    List<NativeTracker> u;
    public Map<String, Object> v;
    Object w;
    int x;
    public ak y;

    public ak() {
        this("", "root", "CONTAINER");
    }

    private ak(String str, String str2, String str3) {
        this(str, str2, str3, new al());
    }

    public ak(String str, String str2, String str3, al alVar) {
        this(str, str2, str3, alVar, new LinkedList());
    }

    public ak(String str, String str2, String str3, al alVar, List<NativeTracker> list) {
        this.a = str;
        this.d = str2;
        this.b = str3;
        this.c = alVar;
        this.e = null;
        this.g = "";
        this.h = false;
        this.i = 0;
        this.j = "";
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 2;
        this.x = 0;
        this.o = -1;
        this.q = "";
        this.r = "";
        this.f = new JSONObject();
        this.u = new LinkedList();
        this.u.addAll(list);
        this.v = new HashMap();
    }

    final void a(List<NativeTracker> list, NativeTracker.TrackerEventType trackerEventType) {
        for (NativeTracker nativeTracker : list) {
            if (trackerEventType == nativeTracker.b) {
                this.u.add(nativeTracker);
            }
        }
    }

    public final void a(NativeTracker.TrackerEventType trackerEventType, @Nullable Map<String, String> map) {
        if (this.u.size() == 0) {
            return;
        }
        for (NativeTracker nativeTracker : this.u) {
            if (trackerEventType == nativeTracker.b) {
                a(nativeTracker, map);
            }
        }
    }

    final void a(List<NativeTracker> list) {
        this.u.addAll(list);
    }

    final void a(String str) {
        this.r = str.trim();
    }

    final void b(@NonNull String str) {
        this.s = str.trim();
    }

    public static ak a(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        ak akVar = new ak();
        akVar.a(str);
        if (str2 != null) {
            akVar.b(str2);
        }
        akVar.w = str3;
        return akVar;
    }

    static void a(@NonNull NativeTracker nativeTracker, @Nullable Map<String, String> map) {
        com.inmobi.rendering.a.c.a().a(com.inmobi.commons.core.utilities.d.a(nativeTracker.a, map), nativeTracker.c);
    }
}
