package com.my.target;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.ironsource.sdk.constants.Constants;
import java.nio.charset.Charset;
import org.json.JSONObject;

/* JADX INFO: compiled from: LogMessage.java */
/* JADX INFO: loaded from: classes2.dex */
public class az {

    @NonNull
    private static String eb = "https://ad.mail.ru/sdk/log/";

    @VisibleForTesting
    public static boolean ec = true;

    @Nullable
    private String ax;

    @Nullable
    private String ed;

    @Nullable
    private String ee;

    @Nullable
    private String ef;

    @NonNull
    private final String name;
    private int slotId;

    @NonNull
    private final String type;

    /* JADX INFO: compiled from: LogMessage.java */
    public interface a {
        public static final String ei = "Bad value";
        public static final String ej = "Required field";
        public static final String ek = "Json error";
        public static final String el = "JS error";
    }

    /* JADX INFO: compiled from: LogMessage.java */
    private interface b {
        public static final String A = "os";
        public static final String DATA = "data";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String URL = "url";
        public static final String W = "sdkver";
        public static final String em = "sdk";
        public static final String en = "osver";
        public static final String eo = "message";
        public static final String ep = "slot";
        public static final String eq = "bannerId";
    }

    /* JADX INFO: compiled from: LogMessage.java */
    private interface c {
        public static final String ERROR = "error";
    }

    @NonNull
    public static az z(@NonNull String str) {
        return new az(str, "error");
    }

    private az(@NonNull String str, @NonNull String str2) {
        this.name = str;
        this.type = str2;
    }

    @NonNull
    public az A(@Nullable String str) {
        this.ed = str;
        return this;
    }

    @NonNull
    public az g(int i) {
        this.slotId = i;
        return this;
    }

    @NonNull
    public az B(@Nullable String str) {
        this.ee = str;
        return this;
    }

    @NonNull
    public az C(@Nullable String str) {
        this.ax = str;
        return this;
    }

    @NonNull
    public az D(@Nullable String str) {
        this.ef = str;
        return this;
    }

    public void e(@NonNull final Context context) {
        h.b(new Runnable() { // from class: com.my.target.az.1
            @Override // java.lang.Runnable
            public void run() {
                String strAp = az.this.ap();
                g.a("send message to log:\n " + strAp);
                if (az.ec) {
                    av.ak().y(Base64.encodeToString(strAp.getBytes(Charset.forName("UTF-8")), 0)).f(az.eb, context);
                }
            }
        });
    }

    @NonNull
    @VisibleForTesting
    String ap() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk", "myTarget");
            jSONObject.put(b.W, "5.3.9");
            jSONObject.put("os", Constants.JAVASCRIPT_INTERFACE_NAME);
            jSONObject.put("osver", Build.VERSION.RELEASE);
            jSONObject.put("type", this.type);
            jSONObject.put("name", this.name);
            if (this.ed != null) {
                jSONObject.put("message", this.ed);
            }
            if (this.slotId > 0) {
                jSONObject.put(b.ep, this.slotId);
            }
            if (this.ee != null) {
                jSONObject.put("url", this.ee);
            }
            if (this.ax != null) {
                jSONObject.put("bannerId", this.ax);
            }
            if (this.ef != null) {
                jSONObject.put("data", this.ef);
            }
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }
}
