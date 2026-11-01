package com.adcolony.sdk;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class q implements p.a {
    private BlockingQueue<Runnable> a = new LinkedBlockingQueue();
    private ThreadPoolExecutor b = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS, this.a);

    q() {
    }

    void a() {
        a.a("WebServices.download", new ah() { // from class: com.adcolony.sdk.q.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                q qVar = q.this;
                qVar.a(new p(afVar, qVar));
            }
        });
        a.a("WebServices.get", new ah() { // from class: com.adcolony.sdk.q.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                q qVar = q.this;
                qVar.a(new p(afVar, qVar));
            }
        });
        a.a("WebServices.post", new ah() { // from class: com.adcolony.sdk.q.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                q qVar = q.this;
                qVar.a(new p(afVar, qVar));
            }
        });
    }

    void a(p pVar) {
        try {
            this.b.execute(pVar);
        } catch (RejectedExecutionException unused) {
            new aa.a().a("RejectedExecutionException: ThreadPoolExecutor unable to execute ").a("download for url " + pVar.a).a(aa.h);
            a(pVar, pVar.a(), null);
        }
    }

    void a(int i) {
        this.b.setCorePoolSize(i);
    }

    int b() {
        return this.b.getCorePoolSize();
    }

    @Override // com.adcolony.sdk.p.a
    public void a(p pVar, af afVar, Map<String, List<String>> map) {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "url", pVar.a);
        y.a(jSONObjectA, "success", pVar.c);
        y.b(jSONObjectA, "status", pVar.e);
        y.a(jSONObjectA, TtmlNode.TAG_BODY, pVar.b);
        y.b(jSONObjectA, "size", pVar.d);
        if (map != null) {
            JSONObject jSONObjectA2 = y.a();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String string = entry.getValue().toString();
                String strSubstring = string.substring(1, string.length() - 1);
                if (entry.getKey() != null) {
                    y.a(jSONObjectA2, entry.getKey(), strSubstring);
                }
            }
            y.a(jSONObjectA, "headers", jSONObjectA2);
        }
        afVar.a(jSONObjectA).b();
    }
}
