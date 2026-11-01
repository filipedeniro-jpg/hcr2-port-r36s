package com.inmobi.commons.core.d;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/* JADX INFO: compiled from: KeyValueStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    private static HashMap<String, c> b = new HashMap<>();
    private static final Object c = new Object();
    public SharedPreferences a;

    private c(Context context, String str) {
        this.a = context.getSharedPreferences(str, 0);
    }

    public static String a(String str) {
        return "com.im.keyValueStore." + str;
    }

    public static c a(Context context, String str) {
        String strA = a(str);
        c cVar = b.get(strA);
        if (cVar != null) {
            return cVar;
        }
        synchronized (c) {
            c cVar2 = b.get(strA);
            if (cVar2 != null) {
                return cVar2;
            }
            c cVar3 = new c(context, strA);
            b.put(strA, cVar3);
            return cVar3;
        }
    }

    public static c b(String str) {
        return a(com.inmobi.commons.a.a.b(), str);
    }

    public final void a(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public final String c(String str) {
        return this.a.getString(str, null);
    }

    public final void a(String str, int i) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public final int d(String str) {
        return this.a.getInt(str, Integer.MIN_VALUE);
    }

    public final void a(String str, long j) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    public final long b(String str, long j) {
        return this.a.getLong(str, j);
    }

    public final void a(String str, boolean z) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public final boolean b(String str, boolean z) {
        return this.a.getBoolean(str, z);
    }
}
