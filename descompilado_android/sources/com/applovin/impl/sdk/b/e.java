package com.applovin.impl.sdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import com.applovin.impl.sdk.h;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    private static SharedPreferences b;
    private final h a;
    private final SharedPreferences c;

    public e(h hVar) {
        this.a = hVar;
        this.c = hVar.z().getSharedPreferences("com.applovin.sdk.preferences." + hVar.u(), 0);
    }

    private static SharedPreferences a(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("com.applovin.sdk.shared", 0);
        }
        return b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences, h hVar) {
        Object objValueOf;
        Object stringSet;
        Object string;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (!sharedPreferences.contains(str)) {
                return t;
            }
            if (Boolean.class.equals(cls)) {
                objValueOf = Boolean.valueOf(t != 0 ? sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()) : sharedPreferences.getBoolean(str, false));
            } else if (Float.class.equals(cls)) {
                objValueOf = Float.valueOf(t != 0 ? sharedPreferences.getFloat(str, ((Float) t).floatValue()) : sharedPreferences.getFloat(str, 0.0f));
            } else if (Integer.class.equals(cls)) {
                objValueOf = Integer.valueOf(t != 0 ? sharedPreferences.getInt(str, ((Integer) t).intValue()) : sharedPreferences.getInt(str, 0));
            } else if (Long.class.equals(cls)) {
                objValueOf = Long.valueOf(t != 0 ? sharedPreferences.getLong(str, ((Long) t).longValue()) : sharedPreferences.getLong(str, 0L));
            } else if (String.class.equals(cls)) {
                string = sharedPreferences.getString(str, (String) t);
            } else if (Set.class.isAssignableFrom(cls)) {
                stringSet = sharedPreferences.getStringSet(str, (Set) t);
            } else {
                objValueOf = t;
            }
            if (objValueOf == null) {
                return t;
            }
            objValueOf = stringSet;
            objValueOf = string;
            return (T) cls.cast(objValueOf);
        } catch (Throwable th) {
            if (hVar != null) {
                hVar.w().b("SharedPreferencesManager", "Error getting value for key: " + str, th);
            }
            return t;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
    }

    private static void a(SharedPreferences.Editor editor, h hVar) {
        if (hVar == null || !((Boolean) hVar.a(b.ad)).booleanValue()) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static <T> void a(d<T> dVar, Context context) {
        a(a(context).edit().remove(dVar.a()), (h) null);
    }

    public static <T> void a(d<T> dVar, T t, Context context) {
        a(dVar.a(), t, a(context), (SharedPreferences.Editor) null, (h) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void a(String str, T t, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, h hVar) {
        boolean z = true;
        boolean z2 = editor != null;
        if (!z2) {
            editor = sharedPreferences.edit();
        }
        if (t instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            editor.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof Integer) {
            editor.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            editor.putLong(str, ((Long) t).longValue());
        } else if (t instanceof String) {
            editor.putString(str, (String) t);
        } else if (t instanceof Set) {
            editor.putStringSet(str, (Set) t);
        } else {
            if (hVar != null) {
                hVar.w().d("SharedPreferencesManager", "Unable to put default value of invalid type: " + t);
            }
            z = false;
        }
        if (!z || z2) {
            return;
        }
        a(editor, hVar);
    }

    public static <T> T b(d<T> dVar, T t, Context context) {
        return (T) a(dVar.a(), t, dVar.b(), a(context), (h) null);
    }

    public <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return (T) a(str, t, cls, sharedPreferences, this.a);
    }

    public void a(SharedPreferences sharedPreferences) {
        a(sharedPreferences.edit().clear(), this.a);
    }

    public <T> void a(d<T> dVar) {
        a(this.c.edit().remove(dVar.a()), this.a);
    }

    public <T> void a(d<T> dVar, T t) {
        a(dVar, t, this.c);
    }

    public <T> void a(d<T> dVar, T t, SharedPreferences sharedPreferences) {
        a(dVar.a(), t, sharedPreferences);
    }

    public <T> void a(String str, T t, SharedPreferences.Editor editor) {
        a(str, t, (SharedPreferences) null, editor, this.a);
    }

    public <T> void a(String str, T t, SharedPreferences sharedPreferences) {
        a(str, t, sharedPreferences, (SharedPreferences.Editor) null, this.a);
    }

    public <T> T b(d<T> dVar, T t) {
        return (T) b(dVar, t, this.c);
    }

    public <T> T b(d<T> dVar, T t, SharedPreferences sharedPreferences) {
        return (T) a(dVar.a(), t, dVar.b(), sharedPreferences);
    }
}
