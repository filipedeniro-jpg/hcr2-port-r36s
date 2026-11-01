package com.my.target;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsStartCall.java */
/* JADX INFO: loaded from: classes2.dex */
public class o extends k {
    public o(@NonNull String str, @Nullable String[] strArr, int i) throws JSONException {
        JSONArray jSONArray;
        super("start");
        this.ao.put("format", str);
        this.ao.put("orientation", i);
        this.ao.put("rotation", false);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            jSONArray = new JSONArray(strArr);
        } else {
            jSONArray = new JSONArray();
            for (String str2 : strArr) {
                jSONArray.put(str2);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("excludeBanners", jSONArray);
        this.ao.put("filter", jSONObject);
    }
}
