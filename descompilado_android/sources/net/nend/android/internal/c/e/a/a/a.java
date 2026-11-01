package net.nend.android.internal.c.e.a.a;

import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: App.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private final String a;
    private final String b;
    private final String c;

    private a(C0109a c0109a) {
        this.a = c0109a.a;
        this.b = c0109a.b;
        this.c = c0109a.c;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("ver", this.b);
        jSONObject.putOpt(ServerResponseWrapper.USER_ID_FIELD, this.c);
        return jSONObject;
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.e.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: App.java */
    public static class C0109a {
        private String a;
        private String b;
        private String c;

        public C0109a a(String str) {
            this.a = str;
            return this;
        }

        public C0109a b(String str) {
            this.b = str;
            return this;
        }

        public C0109a c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.c = str;
            }
            return this;
        }

        public a a() {
            return new a(this);
        }
    }
}
