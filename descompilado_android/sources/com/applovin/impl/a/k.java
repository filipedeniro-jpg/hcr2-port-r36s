package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.utils.o;
import com.my.target.bj;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private Uri a;
    private Uri b;
    private a c;
    private String d;
    private int e;
    private int f;
    private int g;

    public enum a {
        Progressive,
        Streaming
    }

    private k() {
    }

    private static a a(String str) {
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            if ("progressive".equalsIgnoreCase(str)) {
                return a.Progressive;
            }
            if ("streaming".equalsIgnoreCase(str)) {
                return a.Streaming;
            }
        }
        return a.Progressive;
    }

    public static k a(o oVar, com.applovin.impl.sdk.h hVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        try {
            String strC = oVar.c();
            if (!URLUtil.isValidUrl(strC)) {
                hVar.w().d("VastVideoFile", "Unable to create video file. Could not find URL.");
                return null;
            }
            Uri uri = Uri.parse(strC);
            k kVar = new k();
            kVar.a = uri;
            kVar.b = uri;
            kVar.g = com.applovin.impl.sdk.utils.k.a(oVar.b().get("bitrate"));
            kVar.c = a(oVar.b().get(bj.gV));
            kVar.f = com.applovin.impl.sdk.utils.k.a(oVar.b().get("height"));
            kVar.e = com.applovin.impl.sdk.utils.k.a(oVar.b().get("width"));
            kVar.d = oVar.b().get("type").toLowerCase(Locale.ENGLISH);
            return kVar;
        } catch (Throwable th) {
            hVar.w().b("VastVideoFile", "Error occurred while initializing", th);
            return null;
        }
    }

    public Uri a() {
        return this.a;
    }

    public void a(Uri uri) {
        this.b = uri;
    }

    public Uri b() {
        return this.b;
    }

    public boolean c() {
        return this.c == a.Streaming;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.e != kVar.e || this.f != kVar.f || this.g != kVar.g) {
            return false;
        }
        Uri uri = this.a;
        if (uri == null ? kVar.a != null : !uri.equals(kVar.a)) {
            return false;
        }
        Uri uri2 = this.b;
        if (uri2 == null ? kVar.b != null : !uri2.equals(kVar.b)) {
            return false;
        }
        if (this.c != kVar.c) {
            return false;
        }
        String str = this.d;
        if (str != null) {
            return str.equals(kVar.d);
        }
        return kVar.d == null;
    }

    public int hashCode() {
        Uri uri = this.a;
        int iHashCode = (uri != null ? uri.hashCode() : 0) * 31;
        Uri uri2 = this.b;
        int iHashCode2 = (iHashCode + (uri2 != null ? uri2.hashCode() : 0)) * 31;
        a aVar = this.c;
        int iHashCode3 = (iHashCode2 + (aVar != null ? aVar.hashCode() : 0)) * 31;
        String str = this.d;
        return ((((((iHashCode3 + (str != null ? str.hashCode() : 0)) * 31) + this.e) * 31) + this.f) * 31) + this.g;
    }

    public String toString() {
        return "VastVideoFile{sourceVideoUri=" + this.a + ", videoUri=" + this.b + ", deliveryType=" + this.c + ", fileType='" + this.d + "', width=" + this.e + ", height=" + this.f + ", bitrate=" + this.g + '}';
    }
}
