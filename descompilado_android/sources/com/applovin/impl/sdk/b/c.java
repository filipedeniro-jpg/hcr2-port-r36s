package com.applovin.impl.sdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.k;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkSettings;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    protected final h a;
    protected final n b;
    protected final Context c;
    protected final SharedPreferences d;
    private final Map<String, Object> e = new HashMap();
    private Map<String, Object> f;

    public c(h hVar) {
        this.a = hVar;
        this.b = hVar.w();
        this.c = hVar.z();
        this.d = this.c.getSharedPreferences("com.applovin.sdk.1", 0);
        try {
            Field fieldA = com.applovin.impl.sdk.utils.n.a(hVar.m().getClass(), "localSettings");
            fieldA.setAccessible(true);
            this.f = (HashMap) fieldA.get(hVar.m());
        } catch (Throwable unused) {
        }
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) throws JSONException {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private <T> T c(b<T> bVar) {
        try {
            return bVar.a(this.f.get(bVar.a()));
        } catch (Throwable unused) {
            return null;
        }
    }

    private String e() {
        return "com.applovin.sdk." + com.applovin.impl.sdk.utils.n.a(this.a.u()) + ".";
    }

    public <ST> b<ST> a(String str, b<ST> bVar) {
        Iterator<b<?>> it = b.c().iterator();
        while (it.hasNext()) {
            b<ST> bVar2 = (b) it.next();
            if (bVar2.a().equals(str)) {
                return bVar2;
            }
        }
        return bVar;
    }

    public <T> T a(b<T> bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        synchronized (this.e) {
            try {
                try {
                    T t = (T) c(bVar);
                    if (t != null) {
                        return t;
                    }
                    Object obj = this.e.get(bVar.a());
                    if (obj != null) {
                        return bVar.a(obj);
                    }
                    return bVar.b();
                } catch (Throwable unused) {
                    this.a.w().d("SettingsManager", "Unable to retrieve value for setting " + bVar.a() + "; using default...");
                    return bVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.b("SettingsManager", "Saving settingsValues with the application...");
        String strE = e();
        synchronized (this.e) {
            SharedPreferences.Editor editorEdit = this.d.edit();
            for (b<?> bVar : b.c()) {
                Object obj = this.e.get(bVar.a());
                if (obj != null) {
                    this.a.a(strE + bVar.a(), obj, editorEdit);
                }
            }
            editorEdit.apply();
        }
        this.b.a("SettingsManager", "Settings saved with the application.");
    }

    public <T> void a(b<?> bVar, Object obj) {
        if (bVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        }
        synchronized (this.e) {
            this.e.put(bVar.a(), obj);
        }
        this.b.a("SettingsManager", "Setting update: " + bVar.a() + " set to \"" + obj + "\"");
    }

    public void a(AppLovinSdkSettings appLovinSdkSettings) {
        boolean z;
        boolean z2;
        this.b.b("SettingsManager", "Loading user-defined settings");
        if (appLovinSdkSettings == null) {
            return;
        }
        synchronized (this.e) {
            if (((Boolean) this.a.a(b.W)).booleanValue()) {
                this.e.put(b.W.a(), Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled()));
            }
            if (((Boolean) this.a.a(b.bv)).booleanValue()) {
                String autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                if (!k.b(autoPreloadSizes)) {
                    autoPreloadSizes = "NONE";
                }
                if (autoPreloadSizes.equals("NONE")) {
                    this.e.put(b.aV.a(), "");
                } else {
                    this.e.put(b.aV.a(), autoPreloadSizes);
                }
            }
            if (((Boolean) this.a.a(b.bw)).booleanValue()) {
                String autoPreloadTypes = appLovinSdkSettings.getAutoPreloadTypes();
                if (!k.b(autoPreloadTypes)) {
                    autoPreloadTypes = "NONE";
                }
                boolean z3 = false;
                if ("NONE".equals(autoPreloadTypes)) {
                    z = false;
                    z2 = false;
                } else {
                    z = false;
                    z2 = false;
                    for (String str : com.applovin.impl.sdk.utils.d.a(autoPreloadTypes)) {
                        if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                            z3 = true;
                        } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                            z = true;
                        } else if (str.equals(AppLovinAdType.NATIVE.getLabel())) {
                            z2 = true;
                        }
                    }
                }
                if (!z3) {
                    this.e.put(b.aV.a(), "");
                }
                this.e.put(b.aW.a(), Boolean.valueOf(z));
                this.e.put(b.aX.a(), Boolean.valueOf(z2));
            }
        }
    }

    public void a(JSONObject jSONObject) {
        n nVar;
        String str;
        String str2;
        this.b.a("SettingsManager", "Loading settings...");
        synchronized (this.e) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && next.length() > 0) {
                    try {
                        try {
                            b<Long> bVarA = a(next, (b) null);
                            if (bVarA != null) {
                                this.e.put(bVarA.a(), a(next, jSONObject, bVarA.b()));
                                if (bVarA == b.eX) {
                                    this.e.put(b.eY.a(), Long.valueOf(System.currentTimeMillis()));
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            nVar = this.b;
                            str = "SettingsManager";
                            str2 = "Unable to convert setting object ";
                            nVar.b(str, str2, th);
                        }
                    } catch (JSONException e) {
                        th = e;
                        nVar = this.b;
                        str = "SettingsManager";
                        str2 = "Unable to parse JSON settingsValues array";
                        nVar.b(str, str2, th);
                    }
                }
            }
        }
    }

    public List<String> b(b<String> bVar) {
        return com.applovin.impl.sdk.utils.d.a((String) a(bVar));
    }

    public void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.b("SettingsManager", "Loading settingsValues saved with the application...");
        String strE = e();
        synchronized (this.e) {
            for (b<?> bVar : b.c()) {
                try {
                    Object objA = this.a.a(strE + bVar.a(), null, bVar.b().getClass(), this.d);
                    if (objA != null) {
                        this.e.put(bVar.a(), objA);
                    }
                } catch (Exception e) {
                    this.b.b("SettingsManager", "Unable to load \"" + bVar.a() + "\"", e);
                }
            }
        }
    }

    public void c() {
        synchronized (this.e) {
            this.e.clear();
        }
        this.a.a(this.d);
    }

    public boolean d() {
        return this.a.m().isVerboseLoggingEnabled() || ((Boolean) a(b.W)).booleanValue();
    }
}
