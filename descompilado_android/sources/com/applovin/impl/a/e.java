package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.utils.o;
import com.my.target.bj;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private a a;
    private Uri b;
    private String c;

    public enum a {
        UNSPECIFIED,
        STATIC,
        IFRAME,
        HTML
    }

    private e() {
    }

    static e a(o oVar, e eVar, com.applovin.impl.sdk.h hVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        if (eVar == null) {
            try {
                eVar = new e();
            } catch (Throwable th) {
                hVar.w().b("VastNonVideoResource", "Error occurred while initializing", th);
                return null;
            }
        }
        if (eVar.b == null && !com.applovin.impl.sdk.utils.k.b(eVar.c)) {
            String strA = a(oVar, bj.gR);
            if (URLUtil.isValidUrl(strA)) {
                eVar.b = Uri.parse(strA);
                eVar.a = a.STATIC;
                return eVar;
            }
            String strA2 = a(oVar, bj.gS);
            if (com.applovin.impl.sdk.utils.k.b(strA2)) {
                eVar.a = a.IFRAME;
                if (URLUtil.isValidUrl(strA2)) {
                    eVar.b = Uri.parse(strA2);
                } else {
                    eVar.c = strA2;
                }
                return eVar;
            }
            String strA3 = a(oVar, bj.gT);
            if (com.applovin.impl.sdk.utils.k.b(strA3)) {
                eVar.a = a.HTML;
                if (URLUtil.isValidUrl(strA3)) {
                    eVar.b = Uri.parse(strA3);
                } else {
                    eVar.c = strA3;
                }
            }
        }
        return eVar;
    }

    private static String a(o oVar, String str) {
        o oVarB = oVar.b(str);
        if (oVarB != null) {
            return oVarB.c();
        }
        return null;
    }

    public a a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public void a(String str) {
        this.c = str;
    }

    public Uri b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (this.a != eVar.a) {
            return false;
        }
        Uri uri = this.b;
        if (uri == null ? eVar.b != null : !uri.equals(eVar.b)) {
            return false;
        }
        String str = this.c;
        if (str != null) {
            return str.equals(eVar.c);
        }
        return eVar.c == null;
    }

    public int hashCode() {
        a aVar = this.a;
        int iHashCode = (aVar != null ? aVar.hashCode() : 0) * 31;
        Uri uri = this.b;
        int iHashCode2 = (iHashCode + (uri != null ? uri.hashCode() : 0)) * 31;
        String str = this.c;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "VastNonVideoResource{type=" + this.a + ", resourceUri=" + this.b + ", resourceContents='" + this.c + "'}";
    }
}
