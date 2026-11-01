package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d<T extends al> {
    private static final int VERSION = 2;

    @Nullable
    public abstract T a(@NonNull String str, @NonNull af afVar, @Nullable T t, @NonNull b bVar, @NonNull Context context);

    static boolean isVast(@NonNull String str) {
        String strTrim = str.trim();
        return strTrim.startsWith(bj.fR) || strTrim.startsWith(bj.fQ);
    }

    @Nullable
    protected JSONObject a(@Nullable String str, @NonNull Context context) {
        if (str != null) {
            str = str.trim();
        }
        if (str != null && !"".equals(str)) {
            g.a("Converting to JSON...");
            try {
                JSONObject jSONObject = new JSONObject(str);
                g.a("done");
                if (a(jSONObject)) {
                    return jSONObject;
                }
                g.a("invalid json version");
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        g.a("parsing ad response: empty data");
        return null;
    }

    private boolean a(@NonNull JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("version");
            g.a("json version: " + string);
            int iIndexOf = string.indexOf(".");
            return iIndexOf > 0 && Integer.parseInt(string.substring(0, iIndexOf), 10) == 2;
        } catch (NumberFormatException | JSONException unused) {
        }
    }
}
