package com.applovin.impl.a;

import com.applovin.impl.sdk.utils.o;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private String a;
    private String b;

    private f() {
    }

    public static f a(o oVar, f fVar, com.applovin.impl.sdk.h hVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        if (fVar == null) {
            try {
                fVar = new f();
            } catch (Throwable th) {
                hVar.w().b("VastSystemInfo", "Error occurred while initializing", th);
                return null;
            }
        }
        if (!com.applovin.impl.sdk.utils.k.b(fVar.a)) {
            String strC = oVar.c();
            if (com.applovin.impl.sdk.utils.k.b(strC)) {
                fVar.a = strC;
            }
        }
        if (!com.applovin.impl.sdk.utils.k.b(fVar.b)) {
            String str = oVar.b().get("version");
            if (com.applovin.impl.sdk.utils.k.b(str)) {
                fVar.b = str;
            }
        }
        return fVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        String str = this.a;
        if (str == null ? fVar.a != null : !str.equals(fVar.a)) {
            return false;
        }
        String str2 = this.b;
        if (str2 != null) {
            return str2.equals(fVar.b);
        }
        return fVar.b == null;
    }

    public int hashCode() {
        String str = this.a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "VastSystemInfo{name='" + this.a + "', version='" + this.b + "'}";
    }
}
