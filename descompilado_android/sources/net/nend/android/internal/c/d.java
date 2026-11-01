package net.nend.android.internal.c;

import android.text.TextUtils;
import net.nend.android.NendAdUserFeature;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Nend2AdRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private final int a;
    private final String b;
    private final net.nend.android.internal.c.e.a.a.b c;
    private final net.nend.android.internal.c.e.a.a.a d;
    private final String e;
    private final String f;
    private final String g;
    private final long h;
    private final NendAdUserFeature i;

    public d(a<?> aVar) {
        this.a = ((a) aVar).c;
        this.b = ((a) aVar).d;
        this.c = ((a) aVar).e;
        this.d = ((a) aVar).f;
        this.e = ((a) aVar).g;
        this.f = ((a) aVar).h;
        this.g = ((a) aVar).i;
        this.h = ((a) aVar).j;
        this.i = ((a) aVar).k;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("apiKey", this.b);
        jSONObject.put("adspotId", this.a);
        jSONObject.put("device", this.c.a());
        jSONObject.put("app", this.d.a());
        jSONObject.putOpt("mediation", this.e);
        jSONObject.put("sdk", this.f);
        jSONObject.put("sdkVer", this.g);
        jSONObject.put("clientTime", this.h);
        NendAdUserFeature nendAdUserFeature = this.i;
        jSONObject.putOpt("feature", nendAdUserFeature != null ? nendAdUserFeature.toJson() : null);
        return jSONObject;
    }

    /* JADX INFO: compiled from: Nend2AdRequest.java */
    public static abstract class a<T extends a<T>> {
        public final net.nend.android.internal.c.e.a.a.b.a a = new net.nend.android.internal.c.e.a.a.b.a();
        public final net.nend.android.internal.c.e.a.a.a.C0109a b = new net.nend.android.internal.c.e.a.a.a.C0109a();
        private int c;
        private String d;
        private net.nend.android.internal.c.e.a.a.b e;
        private net.nend.android.internal.c.e.a.a.a f;
        private String g;
        private String h;
        private String i;
        private long j;
        private NendAdUserFeature k;

        public abstract d a();

        public T a(int i) {
            this.c = i;
            return this;
        }

        public T a(String str) {
            this.d = str;
            return this;
        }

        public T a(net.nend.android.internal.c.e.a.a.b bVar) {
            this.e = bVar;
            return this;
        }

        public T a(net.nend.android.internal.c.e.a.a.a aVar) {
            this.f = aVar;
            return this;
        }

        public T b(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.g = str;
            }
            return this;
        }

        public T c(String str) {
            this.h = str;
            return this;
        }

        public T d(String str) {
            this.i = str;
            return this;
        }

        public T a(long j) {
            this.j = j;
            return this;
        }

        public T a(NendAdUserFeature nendAdUserFeature) {
            this.k = nendAdUserFeature;
            return this;
        }
    }
}
