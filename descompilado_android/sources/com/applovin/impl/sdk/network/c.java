package com.applovin.impl.sdk.network;

import android.content.Context;
import android.text.TextUtils;
import com.applovin.impl.sdk.utils.n;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static final Object a = new Object();

    private static JSONObject a(String str, Context context) {
        JSONObject jSONObjectB = b(context);
        if (jSONObjectB == null) {
            jSONObjectB = new JSONObject();
        }
        if (!jSONObjectB.has(str)) {
            try {
                jSONObjectB.put(str, new JSONObject());
            } catch (JSONException unused) {
            }
        }
        return jSONObjectB;
    }

    static void a(int i, String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (a) {
            String strD = n.d(str);
            JSONObject jSONObjectA = a(strD, context);
            String string = Integer.toString(i);
            JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject(strD);
            try {
                jSONObjectOptJSONObject.put(string, jSONObjectOptJSONObject.optInt(string) + 1);
            } catch (JSONException unused) {
            }
            try {
                jSONObjectA.put(strD, jSONObjectOptJSONObject);
            } catch (JSONException unused2) {
            }
            a(jSONObjectA, context);
        }
    }

    public static void a(Context context) {
        synchronized (a) {
            com.applovin.impl.sdk.b.e.a((com.applovin.impl.sdk.b.d) com.applovin.impl.sdk.b.d.k, context);
        }
    }

    private static void a(JSONObject jSONObject, Context context) {
        com.applovin.impl.sdk.b.e.a(com.applovin.impl.sdk.b.d.k, jSONObject.toString(), context);
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject;
        synchronized (a) {
            try {
                try {
                    jSONObject = new JSONObject((String) com.applovin.impl.sdk.b.e.b(com.applovin.impl.sdk.b.d.k, "{}", context));
                } catch (JSONException unused) {
                    return new JSONObject();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return jSONObject;
    }
}
