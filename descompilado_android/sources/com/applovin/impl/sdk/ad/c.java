package com.applovin.impl.sdk.ad;

import android.text.TextUtils;
import android.util.Base64;
import com.applovin.impl.sdk.utils.k;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private final com.applovin.impl.sdk.h a;
    private final String b;

    public enum a {
        UNSPECIFIED("UNSPECIFIED"),
        REGULAR("REGULAR"),
        AD_RESPONSE_JSON("AD_RESPONSE_JSON");

        private final String d;

        a(String str) {
            this.d = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.d;
        }
    }

    public c(String str, com.applovin.impl.sdk.h hVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Identifier is empty");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.b = str;
        this.a = hVar;
    }

    private String a(com.applovin.impl.sdk.b.b<String> bVar) {
        for (String str : this.a.b(bVar)) {
            if (this.b.startsWith(str)) {
                return str;
            }
        }
        return null;
    }

    public String a() {
        return this.b;
    }

    public a b() {
        if (a(com.applovin.impl.sdk.b.b.aL) != null) {
            return a.REGULAR;
        }
        return a(com.applovin.impl.sdk.b.b.aM) != null ? a.AD_RESPONSE_JSON : a.UNSPECIFIED;
    }

    public String c() {
        String strA = a(com.applovin.impl.sdk.b.b.aL);
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String strA2 = a(com.applovin.impl.sdk.b.b.aM);
        if (TextUtils.isEmpty(strA2)) {
            return null;
        }
        return strA2;
    }

    public JSONObject d() {
        if (b() != a.AD_RESPONSE_JSON) {
            return null;
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(new String(Base64.decode(this.b.substring(c().length()), 0), "UTF-8"));
                this.a.w().a("AdToken", "Decoded token into ad response: " + jSONObject);
                return jSONObject;
            } catch (JSONException e) {
                this.a.w().b("AdToken", "Unable to decode token '" + this.b + "' into JSON", e);
                return null;
            }
        } catch (Throwable th) {
            this.a.w().b("AdToken", "Unable to process ad response from token '" + this.b + "'", th);
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        String str = this.b;
        if (str != null) {
            return str.equals(cVar.b);
        }
        return cVar.b == null;
    }

    public int hashCode() {
        String str = this.b;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "AdToken{id=" + k.a(32, this.b) + ", type=" + b() + '}';
    }
}
