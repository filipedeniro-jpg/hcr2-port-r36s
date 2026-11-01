package com.inmobi.ads;

import android.net.Uri;
import com.facebook.appevents.AppEventsConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: AdNetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends com.inmobi.commons.core.network.c {
    private static final String A = "f";
    public long a;
    String b;
    public String c;
    int d;
    public String e;
    public String f;
    public Map<String, String> g;
    Map<String, String> h;
    public final String i;
    public InMobiAdRequest.MonetizationContext j;
    public final r k;
    boolean l;

    public f(String str, long j, com.inmobi.commons.core.utilities.uid.d dVar, String str2) {
        super(HttpRequest.METHOD_POST, str, a(str), dVar, a(str), 0);
        this.b = "json";
        this.d = 1;
        this.l = false;
        this.a = j;
        this.o.put("im-plid", String.valueOf(this.a));
        this.o.putAll(com.inmobi.commons.core.utilities.b.g.d());
        this.o.putAll(com.inmobi.commons.core.utilities.b.c.c());
        this.o.put("u-appIS", com.inmobi.commons.core.utilities.b.a.a().a);
        this.o.putAll(com.inmobi.a.m.a().f());
        this.o.putAll(com.inmobi.a.m.a().e());
        Map<String, String> map = this.o;
        com.inmobi.a.b.a aVarA = com.inmobi.a.b.b.a();
        HashMap map2 = new HashMap();
        if (aVarA != null) {
            map2.put("c-ap-bssid", String.valueOf(aVarA.a));
        }
        map.putAll(map2);
        Map<String, String> map3 = this.o;
        ArrayList arrayList = (ArrayList) com.inmobi.a.b.c.a();
        HashMap map4 = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            map4.put("v-ap-bssid", String.valueOf(((com.inmobi.a.b.a) arrayList.get(arrayList.size() - 1)).a));
        }
        map3.putAll(map4);
        this.o.putAll(com.inmobi.a.a.c.b());
        this.o.putAll(com.inmobi.a.a.c.c());
        this.o.putAll(com.inmobi.a.a.c.a());
        this.i = UUID.randomUUID().toString();
        this.o.put("client-request-id", this.i);
        if (str2 != null) {
            this.o.put("u-appcache", str2);
        }
        this.o.put("sdk-flavor", "row");
        this.k = new r();
        this.o.put("skdv", this.z.c);
        this.o.put("skdm", this.k.a(this.z.b, this.z.a));
    }

    private static boolean a(String str) {
        if (str == null) {
            return true;
        }
        Uri uri = Uri.parse(str);
        return "http".equals(uri.getScheme()) || !"https".equals(uri.getScheme());
    }

    @Override // com.inmobi.commons.core.network.c
    public final void a() {
        super.a();
        this.o.put("format", this.b);
        this.o.put("mk-ads", String.valueOf(this.d));
        this.o.put("adtype", this.e);
        if (this.f != null) {
            this.o.put("p-keywords", this.f);
        }
        InMobiAdRequest.MonetizationContext monetizationContext = this.j;
        String str = "M10N_CONTEXT_ACTIVITY";
        if (monetizationContext != null && monetizationContext == InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER) {
            str = "M10N_CONTEXT_OTHER";
        }
        this.o.put("m10n_context", str);
        Map<String, String> map = this.g;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!this.o.containsKey(entry.getKey())) {
                    this.o.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.h != null) {
            this.o.putAll(this.h);
        }
    }

    public final String c() {
        return this.h.containsKey("preload-request") ? this.h.get("preload-request") : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    @Override // com.inmobi.commons.core.network.c
    public final boolean b() {
        return this.l || super.b();
    }
}
