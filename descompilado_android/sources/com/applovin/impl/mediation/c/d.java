package com.applovin.impl.mediation.c;

import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.k;
import com.applovin.impl.sdk.utils.n;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.applovin.impl.sdk.d.a {
    private final String a;
    private final String c;
    private final com.applovin.impl.mediation.b.e d;
    private final Map<String, String> e;
    private final String f;
    private final String g;
    private final boolean h;

    public d(String str, Map<String, String> map, int i, String str2, com.applovin.impl.mediation.b.e eVar, h hVar) {
        super("TaskFireMediationPostbacks", hVar);
        this.a = str;
        this.c = str + "_urls";
        this.e = n.b(map);
        this.f = String.valueOf(i);
        this.g = k.c(str2);
        this.d = eVar;
        this.h = eVar.e(this.c);
    }

    private com.applovin.impl.sdk.network.f a(String str, String str2, String str3) {
        return com.applovin.impl.sdk.network.f.b(c()).a(c(str, str2, str3)).a(false).a();
    }

    private com.applovin.impl.sdk.network.e b(String str, String str2, String str3) {
        return com.applovin.impl.sdk.network.e.j().a(c(str, str2, str3)).a(false).a();
    }

    private void b() {
        try {
            final List<String> listB = this.d.b(this.c, this.e);
            if (listB == null || listB.isEmpty()) {
                a("No postbacks to fire for event: " + this.a);
                return;
            }
            a("Firing " + listB.size() + " '" + this.a + "' postback(s)");
            final AtomicInteger atomicInteger = new AtomicInteger();
            Iterator<String> it = listB.iterator();
            while (it.hasNext()) {
                c().M().dispatchPostbackRequest(a(it.next(), this.f, this.g), q.a.MEDIATION_POSTBACKS, new AppLovinPostbackListener() { // from class: com.applovin.impl.mediation.c.d.1
                    @Override // com.applovin.sdk.AppLovinPostbackListener
                    public void onPostbackFailure(String str, int i) {
                        d.this.d("Failed to fire postback: " + str);
                    }

                    @Override // com.applovin.sdk.AppLovinPostbackListener
                    public void onPostbackSuccess(String str) {
                        d.this.a("Successfully fired postback: " + str);
                        if (atomicInteger.incrementAndGet() == listB.size()) {
                            d.this.h();
                        }
                    }
                });
            }
        } catch (Throwable th) {
            a("Unable to create postback URL for mediated '" + this.a + "'", th);
        }
    }

    private String c(String str, String str2, String str3) {
        return str.replace("{ERROR_CODE}", str2).replace("{ERROR_MESSAGE}", k.e(str3));
    }

    private void g() {
        try {
            ArrayList arrayList = new ArrayList(this.d.b(this.c, this.e));
            if (this.h) {
                arrayList.addAll(this.d.a(this.c, this.e));
            }
            if (arrayList.isEmpty()) {
                a("No persistent postbacks to fire for event: " + this.a);
                return;
            }
            a("Firing " + arrayList.size() + " '" + this.a + "' persistent postback(s)");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c().I().a(b((String) it.next(), this.f, this.g));
            }
        } catch (Throwable th) {
            a("Unable to create persistent postback URL for mediated '" + this.a + "'", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.h) {
            List<String> listA = this.d.a(this.c, this.e);
            if (listA == null || listA.isEmpty()) {
                a("Skip firing of successive urls - none found");
                return;
            }
            a("Firing " + listA.size() + " '" + this.a + "' successive postback(s)");
            Iterator<String> it = listA.iterator();
            while (it.hasNext()) {
                c().M().dispatchPostbackRequest(a(it.next(), this.f, this.g), q.a.MEDIATION_POSTBACKS, null);
            }
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.H;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (((Boolean) c().a(com.applovin.impl.sdk.b.a.j)).booleanValue()) {
            g();
        } else {
            b();
        }
    }
}
