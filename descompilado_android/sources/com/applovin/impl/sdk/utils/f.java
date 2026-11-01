package com.applovin.impl.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appsflyer.share.Constants;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.sdk.precache.DownloadManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private static final int[] a = {7, 4, 2, 1, 11};
    private static final int[] b = {5, 6, 10, 3, 9, 8, 14};
    private static final int[] c = {15, 12, 13};
    private static final String d = f.class.getSimpleName();

    private static NetworkInfo a(Context context) {
        ConnectivityManager connectivityManager;
        if (!com.applovin.impl.sdk.i.a("android.permission.ACCESS_NETWORK_STATE", context) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static String a(InputStream inputStream, com.applovin.impl.sdk.h hVar) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[((Integer) hVar.a(com.applovin.impl.sdk.b.b.dP)).intValue()];
            while (true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Throwable th) {
            hVar.w().b(d, "Encountered error while reading stream", th);
            return null;
        }
    }

    public static String a(String str, com.applovin.impl.sdk.h hVar) {
        return a((String) hVar.a(com.applovin.impl.sdk.b.b.aG), str, hVar);
    }

    public static String a(String str, String str2, com.applovin.impl.sdk.h hVar) {
        if (str == null || str.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        return str + str2;
    }

    public static JSONObject a(JSONObject jSONObject) throws JSONException {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    public static void a(int i, com.applovin.impl.sdk.h hVar) {
        com.applovin.impl.sdk.n nVarW;
        StringBuilder sb;
        String str;
        com.applovin.impl.sdk.b.c cVarY = hVar.y();
        if (i == 401) {
            cVarY.a(com.applovin.impl.sdk.b.b.U, "");
            cVarY.a(com.applovin.impl.sdk.b.b.V, "");
            cVarY.a();
            nVarW = hVar.w();
            sb = new StringBuilder();
            sb.append("SDK key \"");
            sb.append(hVar.u());
            str = "\" is rejected by AppLovin. Please make sure the SDK key is correct.";
        } else {
            if (i != 418) {
                if ((i < 400 || i >= 500) && i != -1) {
                    return;
                }
                hVar.f();
                return;
            }
            cVarY.a((com.applovin.impl.sdk.b.b<?>) com.applovin.impl.sdk.b.b.T, (Object) true);
            cVarY.a();
            nVarW = hVar.w();
            sb = new StringBuilder();
            sb.append("SDK key \"");
            sb.append(hVar.u());
            str = "\" has been blocked. Please contact AppLovin support at support@applovin.com.";
        }
        sb.append(str);
        nVarW.e("AppLovinSdk", sb.toString());
    }

    private static boolean a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, com.applovin.impl.sdk.h hVar) {
        if (!(com.applovin.impl.sdk.i.a("android.permission.ACCESS_NETWORK_STATE", context) && context.getSystemService("connectivity") != null)) {
            return true;
        }
        NetworkInfo networkInfoA = a(context);
        return networkInfoA != null ? networkInfoA.isConnected() : ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.dK)).booleanValue();
    }

    public static String b(String str, com.applovin.impl.sdk.h hVar) {
        return a((String) hVar.a(com.applovin.impl.sdk.b.b.aH), str, hVar);
    }

    public static void c(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        String strA = g.a(jSONObject, "persisted_data", (String) null, hVar);
        if (k.b(strA)) {
            hVar.a(com.applovin.impl.sdk.b.d.t, strA);
            hVar.w().b(d, "Updated persisted data");
        }
    }

    public static void d(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        try {
            if (jSONObject.has(DownloadManager.SETTINGS)) {
                com.applovin.impl.sdk.b.c cVarY = hVar.y();
                if (jSONObject.isNull(DownloadManager.SETTINGS)) {
                    return;
                }
                cVarY.a(jSONObject.getJSONObject(DownloadManager.SETTINGS));
                cVarY.a();
                hVar.w().a(d, "New settings processed");
            }
        } catch (JSONException e) {
            hVar.w().b(d, "Unable to parse settings out of API response", e);
        }
    }

    public static Map<String, String> e(com.applovin.impl.sdk.h hVar) {
        String str;
        HashMap map = new HashMap();
        String strU = (String) hVar.a(com.applovin.impl.sdk.b.b.V);
        if (!k.b(strU)) {
            if (!((Boolean) hVar.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
                strU = hVar.u();
                str = "api_key";
            }
            map.put("sc", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.X)));
            map.put("sc2", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.Y)));
            map.put("server_installed_at", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.Z)));
            n.a("persisted_data", k.e((String) hVar.a(com.applovin.impl.sdk.b.d.t)), map);
            return map;
        }
        str = "device_token";
        map.put(str, strU);
        map.put("sc", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.X)));
        map.put("sc2", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.Y)));
        map.put("server_installed_at", k.e((String) hVar.a(com.applovin.impl.sdk.b.b.Z)));
        n.a("persisted_data", k.e((String) hVar.a(com.applovin.impl.sdk.b.d.t)), map);
        return map;
    }

    public static void e(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        JSONArray jSONArrayA = g.a(jSONObject, "zones", (JSONArray) null, hVar);
        if (jSONArrayA != null) {
            for (com.applovin.impl.sdk.ad.d dVar : hVar.R().a(jSONArrayA)) {
                if (dVar.d()) {
                    hVar.q().preloadAds(dVar);
                } else {
                    hVar.p().preloadAds(dVar);
                }
            }
            hVar.O().a(hVar.R().b());
            hVar.P().a(hVar.R().b());
        }
    }

    public static String f(com.applovin.impl.sdk.h hVar) {
        NetworkInfo networkInfoA = a(hVar.z());
        String str = "unknown";
        if (networkInfoA != null) {
            int type = networkInfoA.getType();
            int subtype = networkInfoA.getSubtype();
            if (type == 1) {
                str = "wifi";
            } else if (type == 0) {
                if (a(subtype, a)) {
                    str = "2g";
                } else if (a(subtype, b)) {
                    str = ConnectivityService.NETWORK_TYPE_3G;
                } else {
                    str = a(subtype, c) ? "4g" : "mobile";
                }
            }
            hVar.w().a(d, "Network " + type + Constants.URL_PATH_DELIMITER + subtype + " resolved to " + str);
        }
        return str;
    }

    public static void f(JSONObject jSONObject, com.applovin.impl.sdk.h hVar) {
        JSONObject jSONObjectA = g.a(jSONObject, "variables", (JSONObject) null, hVar);
        if (jSONObjectA != null) {
            hVar.t().updateVariables(jSONObjectA);
        }
    }

    public static String g(com.applovin.impl.sdk.h hVar) {
        return a((String) hVar.a(com.applovin.impl.sdk.b.b.aE), "4.0/ad", hVar);
    }

    public static String h(com.applovin.impl.sdk.h hVar) {
        return a((String) hVar.a(com.applovin.impl.sdk.b.b.aF), "4.0/ad", hVar);
    }
}
