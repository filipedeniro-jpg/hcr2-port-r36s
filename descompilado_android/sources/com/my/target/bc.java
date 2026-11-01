package com.my.target;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;
import java.net.HttpCookie;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MyTargetJSONCookie.java */
/* JADX INFO: loaded from: classes2.dex */
public class bc {
    @Nullable
    String a(@NonNull HttpCookie httpCookie) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("name", httpCookie.getName());
            jSONObject.putOpt("value", httpCookie.getValue());
            jSONObject.putOpt("comment", httpCookie.getComment());
            jSONObject.putOpt("commentUrl", httpCookie.getCommentURL());
            jSONObject.putOpt("domain", httpCookie.getDomain());
            jSONObject.putOpt("maxage", Long.valueOf(httpCookie.getMaxAge()));
            jSONObject.putOpt("path", httpCookie.getPath());
            jSONObject.putOpt("portlist", httpCookie.getPortlist());
            jSONObject.putOpt("version", Integer.valueOf(httpCookie.getVersion()));
            jSONObject.putOpt("secure", Boolean.valueOf(httpCookie.getSecure()));
            jSONObject.putOpt("discard", Boolean.valueOf(httpCookie.getDiscard()));
            if (Build.VERSION.SDK_INT >= 24) {
                jSONObject.putOpt("httpOnly", Boolean.valueOf(httpCookie.isHttpOnly()));
            } else {
                try {
                    Field declaredField = httpCookie.getClass().getDeclaredField("httpOnly");
                    declaredField.setAccessible(true);
                    jSONObject.putOpt("httpOnly", declaredField.get(httpCookie));
                } catch (IllegalAccessException | NoSuchFieldException unused) {
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            g.a("Exception encoding cookie" + e.getMessage());
            return null;
        }
    }

    @Nullable
    HttpCookie E(@NonNull String str) {
        JSONException e;
        HttpCookie httpCookie;
        try {
            JSONObject jSONObject = new JSONObject(str);
            httpCookie = new HttpCookie(jSONObject.getString("name"), jSONObject.getString("value"));
            try {
                httpCookie.setComment(jSONObject.getString("comment"));
                httpCookie.setCommentURL(jSONObject.getString("commentUrl"));
                httpCookie.setDomain(jSONObject.getString("domain"));
                httpCookie.setMaxAge(jSONObject.getInt("maxage"));
                httpCookie.setPath(jSONObject.getString("path"));
                httpCookie.setPortlist(jSONObject.getString("portlist"));
                httpCookie.setVersion(jSONObject.getInt("version"));
                httpCookie.setSecure(jSONObject.getBoolean("secure"));
                httpCookie.setDiscard(jSONObject.getBoolean("discard"));
                if (Build.VERSION.SDK_INT >= 24) {
                    httpCookie.setHttpOnly(jSONObject.getBoolean("httpOnly"));
                } else {
                    try {
                        Field declaredField = httpCookie.getClass().getDeclaredField("httpOnly");
                        declaredField.setAccessible(true);
                        declaredField.set(httpCookie, Boolean.valueOf(jSONObject.getBoolean("httpOnly")));
                    } catch (IllegalAccessException | NoSuchFieldException unused) {
                    }
                }
            } catch (JSONException e2) {
                e = e2;
                g.a("Exception decoding cookie" + e.getMessage());
            }
        } catch (JSONException e3) {
            e = e3;
            httpCookie = null;
        }
        return httpCookie;
    }
}
