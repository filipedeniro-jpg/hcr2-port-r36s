package net.nend.android.internal.c.e.a.a;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Device.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    private final int a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final int g;
    private final c h;
    private final d i;
    private final int j;
    private final String k;
    private final boolean l;

    private b(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("os", this.a);
        jSONObject.put("osVer", this.b);
        jSONObject.put("model", this.c);
        jSONObject.put("userAgent", this.d);
        jSONObject.putOpt("gaid", this.e);
        jSONObject.put("language", this.f);
        jSONObject.put("orientation", this.g);
        jSONObject.putOpt("screen", this.h.a());
        jSONObject.putOpt("sensor", this.i.a());
        jSONObject.put("mediaVol", this.j);
        jSONObject.putOpt("carrier", this.k);
        jSONObject.putOpt("isWifi", Boolean.valueOf(this.l));
        return jSONObject;
    }

    /* JADX INFO: compiled from: Device.java */
    public static class a {
        private int a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private int g;
        private c h;
        private d i;
        private int j;
        private String k;
        private boolean l;

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a b(String str) {
            if (str == null) {
                str = "";
            }
            this.c = str;
            return this;
        }

        public a c(String str) {
            this.d = str;
            return this;
        }

        public a d(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.e = str;
            }
            return this;
        }

        public a e(String str) {
            this.f = str;
            return this;
        }

        public a b(int i) {
            this.g = i;
            return this;
        }

        public a a(c cVar) {
            this.h = cVar;
            return this;
        }

        public a a(d dVar) {
            this.i = dVar;
            return this;
        }

        public a c(int i) {
            this.j = i;
            return this;
        }

        public a f(String str) {
            this.k = str;
            return this;
        }

        public a a(boolean z) {
            this.l = z;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
