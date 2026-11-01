package com.inmobi.commons.core.network;

import android.util.Base64;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.inmobi.commons.core.configs.g;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";
    private com.inmobi.commons.core.utilities.uid.d b;
    private boolean c;
    private byte[] d;
    private byte[] e;
    private boolean f;
    protected Map<String, String> m;
    protected Map<String, String> n;
    public Map<String, String> o;
    String p;
    public String q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public long v;
    boolean w;
    public int x;
    public boolean y;
    public g z;

    public c(String str, String str2, boolean z, com.inmobi.commons.core.utilities.uid.d dVar) {
        this(str, str2, z, dVar, false, 0);
    }

    public c(String str, String str2) {
        this(str, str2, false, null, false, 0);
        this.f = false;
    }

    public c(String str, String str2, com.inmobi.commons.core.utilities.uid.d dVar, int i) {
        this(str, str2, true, dVar, false, i);
    }

    public c(String str, String str2, boolean z, com.inmobi.commons.core.utilities.uid.d dVar, boolean z2, int i) {
        this.m = new HashMap();
        this.r = 60000;
        this.s = 60000;
        this.t = true;
        this.u = true;
        this.v = -1L;
        this.x = 0;
        this.f = true;
        this.y = false;
        this.p = str;
        this.q = str2;
        this.c = z;
        this.b = dVar;
        this.m.put("User-Agent", com.inmobi.commons.a.a.f());
        this.w = z2;
        this.x = i;
        if (HttpRequest.METHOD_GET.equals(str)) {
            this.n = new HashMap();
        } else if (HttpRequest.METHOD_POST.equals(str)) {
            this.o = new HashMap();
        }
        this.z = new g();
        com.inmobi.commons.core.configs.b.a().a(this.z, (com.inmobi.commons.core.configs.b.c) null);
    }

    public final void a(Map<String, String> map) {
        if (map != null) {
            this.m.putAll(map);
        }
    }

    public final void b(Map<String, String> map) {
        if (map != null) {
            this.n.putAll(map);
        }
    }

    public final void c(Map<String, String> map) {
        this.o.putAll(map);
    }

    public final Map<String, String> d() {
        com.inmobi.commons.core.utilities.d.a(this.m);
        return this.m;
    }

    public final String e() {
        String strC;
        String str = this.q;
        if (this.n == null || (strC = c()) == null || strC.trim().length() == 0) {
            return str;
        }
        if (!str.contains("?")) {
            str = str + "?";
        }
        if (!str.endsWith(Constants.RequestParameters.AMPERSAND) && !str.endsWith("?")) {
            str = str + Constants.RequestParameters.AMPERSAND;
        }
        return str + strC;
    }

    @CallSuper
    public void a() {
        JSONObject jSONObjectA;
        com.inmobi.commons.core.utilities.b.e.c();
        this.x = com.inmobi.commons.core.utilities.b.e.a(this.x == 1);
        if (this.u) {
            if (HttpRequest.METHOD_GET.equals(this.p)) {
                d(this.n);
            } else if (HttpRequest.METHOD_POST.equals(this.p)) {
                d(this.o);
            }
        }
        if (this.f && (jSONObjectA = com.inmobi.commons.core.utilities.b.e.a()) != null) {
            if (HttpRequest.METHOD_GET.equals(this.p)) {
                this.n.put("consentObject", jSONObjectA.toString());
            } else if (HttpRequest.METHOD_POST.equals(this.p)) {
                this.o.put("consentObject", jSONObjectA.toString());
            }
        }
        if (HttpRequest.METHOD_GET.equals(this.p)) {
            this.n.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
        } else if (HttpRequest.METHOD_POST.equals(this.p)) {
            this.o.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
        }
    }

    private String c() {
        com.inmobi.commons.core.utilities.d.a(this.n);
        return com.inmobi.commons.core.utilities.d.a(this.n, Constants.RequestParameters.AMPERSAND);
    }

    public final String f() {
        com.inmobi.commons.core.utilities.d.a(this.o);
        String strA = com.inmobi.commons.core.utilities.d.a(this.o, Constants.RequestParameters.AMPERSAND);
        new StringBuilder("Post body url: ").append(this.q);
        if (!b()) {
            return strA;
        }
        this.d = com.inmobi.commons.core.utilities.a.b.a(16);
        this.e = com.inmobi.commons.core.utilities.a.b.a();
        byte[] bArr = this.d;
        byte[] bArr2 = this.e;
        g gVar = this.z;
        byte[] bArrA = com.inmobi.commons.core.utilities.a.b.a(8);
        HashMap map = new HashMap();
        map.put("sm", com.inmobi.commons.core.utilities.a.b.a(strA, bArr2, bArr, bArrA, gVar.b, gVar.a));
        map.put("sn", gVar.c);
        return com.inmobi.commons.core.utilities.d.a(map, Constants.RequestParameters.AMPERSAND);
    }

    public boolean b() {
        return this.c;
    }

    private void d(Map<String, String> map) {
        map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
        map.putAll(com.inmobi.commons.core.utilities.b.b.a(this.y));
        map.putAll(com.inmobi.commons.core.utilities.b.f.a());
        if (this.b != null) {
            if (b()) {
                com.inmobi.commons.core.utilities.uid.d dVar = this.b;
                HashMap map2 = new HashMap();
                map2.put("u-id-map", new JSONObject(dVar.a((String) null, false)).toString());
                map.putAll(map2);
                return;
            }
            com.inmobi.commons.core.utilities.uid.d dVar2 = this.b;
            String string = Integer.toString(new Random().nextInt());
            String strA = com.inmobi.commons.core.utilities.a.c.a(new JSONObject(dVar2.a(string, true)).toString());
            Map<? extends String, ? extends String> map3 = new HashMap<>();
            map3.put("u-id-map", strA);
            map3.put("u-id-key", string);
            com.inmobi.commons.core.utilities.uid.c.a();
            map3.put("u-key-ver", com.inmobi.commons.core.utilities.uid.c.d());
            map.putAll(map3);
        }
    }

    @Nullable
    public final byte[] a(byte[] bArr) {
        try {
            return com.inmobi.commons.core.utilities.a.b.a(Base64.decode(bArr, 0), this.e, this.d);
        } catch (IllegalArgumentException e) {
            new StringBuilder("Msg : ").append(e.getMessage());
            return null;
        }
    }

    public final long g() {
        long length = 0;
        try {
            if (HttpRequest.METHOD_GET.equals(this.p)) {
                length = 0 + ((long) c().length());
            } else if (HttpRequest.METHOD_POST.equals(this.p)) {
                length = ((long) f().length()) + 0;
            }
        } catch (Exception unused) {
        }
        return length;
    }
}
