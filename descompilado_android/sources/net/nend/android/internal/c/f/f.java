package net.nend.android.internal.c.f;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import net.nend.android.internal.d.k;
import org.json.JSONObject;

/* JADX INFO: compiled from: VideoAdLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends net.nend.android.internal.c.a {

    @VisibleForTesting
    final net.nend.android.internal.utilities.g.c<net.nend.android.internal.b.c.a> d;
    private final net.nend.android.internal.utilities.g.c<net.nend.android.internal.b.c.c> e;

    public f(Context context) {
        super(context);
        this.d = new net.nend.android.internal.c.a.AbstractC0107a<net.nend.android.internal.b.c.a>() { // from class: net.nend.android.internal.c.f.f.1
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return b.a;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // net.nend.android.internal.c.a.AbstractC0107a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public net.nend.android.internal.b.c.a a(JSONObject jSONObject) {
                net.nend.android.internal.utilities.b.a("JsonResponseEvent", jSONObject);
                return net.nend.android.internal.b.c.a.a(jSONObject);
            }
        };
        this.e = new net.nend.android.internal.c.a.AbstractC0107a<net.nend.android.internal.b.c.c>() { // from class: net.nend.android.internal.c.f.f.2
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return b.b;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // net.nend.android.internal.c.a.AbstractC0107a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public net.nend.android.internal.b.c.c a(JSONObject jSONObject) {
                net.nend.android.internal.utilities.b.a("JsonResponseEvent", jSONObject);
                return net.nend.android.internal.b.c.c.a(jSONObject);
            }
        };
    }

    public k<net.nend.android.internal.b.c.a> a(int i, String str, String str2, String str3) {
        return b(i, str, str2, str3, this.d);
    }

    public k<net.nend.android.internal.b.c.c> b(int i, String str, String str2, String str3) {
        return b(i, str, str2, str3, this.e);
    }

    public void a(net.nend.android.internal.b.c.d dVar) {
        if (TextUtils.isEmpty(dVar.f)) {
            return;
        }
        a.a(dVar);
        this.a.a(dVar.f);
    }

    private <V extends net.nend.android.internal.b.c.d> k<V> b(int i, String str, String str2, String str3, net.nend.android.internal.utilities.g.c<V> cVar) {
        final Context context = this.b.get();
        return a(i, str, str2, str3, cVar).b((net.nend.android.internal.d.g) new net.nend.android.internal.d.g<V, k<? extends V>>() { // from class: net.nend.android.internal.c.f.f.3
            /* JADX WARN: Incorrect types in method signature: (TV;)Lnet/nend/android/internal/d/k<+TV;>; */
            @Override // net.nend.android.internal.d.g
            public k a(net.nend.android.internal.b.c.d dVar) {
                net.nend.android.internal.utilities.b.a("ApiResponseTimeEvent", Long.valueOf(System.currentTimeMillis() - f.this.c));
                return f.this.a.a(dVar, context);
            }
        });
    }

    @Override // net.nend.android.internal.c.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public g.a a(int i, String str, String str2) {
        return new g.a().a(i).a(str).b(str2);
    }
}
