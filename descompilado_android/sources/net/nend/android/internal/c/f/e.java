package net.nend.android.internal.c.f;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAdRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends net.nend.android.internal.c.d {
    private final ArrayList<Integer> a;
    private final int b;

    e(a aVar) {
        super(aVar);
        this.a = aVar.c;
        this.b = aVar.d;
    }

    @Override // net.nend.android.internal.c.d
    public JSONObject a() throws JSONException {
        JSONObject jSONObjectA = super.a();
        jSONObjectA.put("acquiredIds", new JSONArray((Collection) this.a));
        jSONObjectA.put("videoClickAction", this.b);
        return jSONObjectA;
    }

    /* JADX INFO: compiled from: NativeAdRequest.java */
    public static class a extends net.nend.android.internal.c.d.a<a> {
        private ArrayList<Integer> c;
        private int d;

        public a a(ArrayList<Integer> arrayList) {
            this.c = arrayList;
            return this;
        }

        a b(int i) {
            this.d = i;
            return this;
        }

        @Override // net.nend.android.internal.c.d.a
        public net.nend.android.internal.c.d a() {
            return new e(this);
        }
    }
}
