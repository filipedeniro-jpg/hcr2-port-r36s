package com.my.target;

import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsEventDeserializer.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static final String PREFIX = "adman://onEvent,";

    @Nullable
    public static v a(@NonNull ConsoleMessage consoleMessage) {
        String strMessage = consoleMessage.message();
        if (TextUtils.isEmpty(strMessage) || !d(strMessage)) {
            return null;
        }
        try {
            return b(new JSONObject(strMessage.substring(16)));
        } catch (JSONException e) {
            g.b(e.getMessage());
            return null;
        }
    }

    private static boolean d(@NonNull String str) {
        return str.startsWith(PREFIX);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Nullable
    private static v b(@NonNull JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("event");
        JSONObject jSONObject2 = jSONObject.has("data") ? jSONObject.getJSONObject("data") : null;
        byte b = -1;
        int i = 0;
        switch (string.hashCode()) {
            case -1349867671:
                if (string.equals(v.aC)) {
                    b = 0;
                }
                break;
            case -1012956973:
                if (string.equals(v.aN)) {
                    b = 4;
                }
                break;
            case 157935686:
                if (string.equals(v.aM)) {
                    b = 5;
                }
                break;
            case 172943136:
                if (string.equals(v.aH)) {
                    b = 2;
                }
                break;
            case 747469392:
                if (string.equals(v.aO)) {
                    b = 3;
                }
                break;
            case 1109243225:
                if (string.equals(v.aA)) {
                    b = 1;
                }
                break;
        }
        if (b == 0) {
            if (jSONObject2 != null) {
                String str = jSONObject2.has("error") ? "jsError error: " + jSONObject2.getString("error") : "jsError";
                if (jSONObject2.has("message")) {
                    str = str + " message: " + jSONObject2.getString("message");
                }
                return new u(str);
            }
            return new u();
        }
        if (b == 1) {
            if (jSONObject2 != null && jSONObject2.has("width") && jSONObject2.has("height")) {
                return new w(jSONObject2.getInt("width"), jSONObject2.getInt("height"));
            }
            return new w();
        }
        if (b == 2) {
            if (jSONObject2 == null || !jSONObject2.has("format") || !jSONObject2.has("banners")) {
                return null;
            }
            String string2 = jSONObject2.getString("format");
            JSONArray jSONArray = jSONObject2.getJSONArray("banners");
            int length = jSONArray.length();
            String[] strArr = new String[length];
            while (i < length) {
                strArr[i] = jSONArray.getString(i);
                i++;
            }
            return new t(strArr, string2);
        }
        if (b == 3) {
            if (jSONObject2 != null && jSONObject2.has("width") && jSONObject2.has("height")) {
                return new y(jSONObject2.getInt("width"), jSONObject2.getInt("height"));
            }
            return null;
        }
        if (b != 4) {
            if (b == 5) {
                if (jSONObject2 != null && jSONObject2.has("format") && jSONObject2.has("bannerId")) {
                    return new s(jSONObject2.getString("bannerId"), jSONObject2.getString("format"), jSONObject2.optString("url"));
                }
                return null;
            }
            return new C0690r(string);
        }
        if (jSONObject2 == null || !jSONObject2.has("stats")) {
            return null;
        }
        JSONArray jSONArray2 = jSONObject2.getJSONArray("stats");
        int length2 = jSONArray2.length();
        ArrayList arrayList = new ArrayList();
        while (i < length2) {
            arrayList.add(jSONArray2.getString(i));
            i++;
        }
        return new z(arrayList, jSONObject2.has("type") ? jSONObject2.getString("type") : null);
    }

    private j() {
    }
}
