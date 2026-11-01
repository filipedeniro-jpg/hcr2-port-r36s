package net.nend.android.internal.c.e.a.a;

import com.my.target.i;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Screen.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private final int a;
    private final int b;
    private final int c;

    c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", this.a);
        jSONObject.put("height", this.b);
        jSONObject.put(i.N, this.c);
        return jSONObject;
    }

    /* JADX INFO: compiled from: Screen.java */
    public static class a {
        private int a;
        private int b;
        private int c;

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a c(int i) {
            this.c = i;
            return this;
        }

        public c a() {
            return new c(this);
        }
    }
}
