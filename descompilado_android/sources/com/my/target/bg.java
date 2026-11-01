package com.my.target;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsonParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class bg {
    public static int a(@NonNull JSONObject jSONObject, @NonNull String str, int i) {
        String strOptString = jSONObject.optString(str);
        if (TextUtils.isEmpty(strOptString)) {
            return i;
        }
        try {
            return Color.parseColor(strOptString);
        } catch (Exception unused) {
            g.b("error parsing color " + strOptString);
            return i;
        }
    }
}
