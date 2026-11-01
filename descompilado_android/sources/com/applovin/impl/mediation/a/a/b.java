package com.applovin.impl.mediation.a.a;

import android.text.TextUtils;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.g;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.adapter.MaxAdViewAdapter;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxInterstitialAdapter;
import com.applovin.mediation.adapter.MaxRewardedAdapter;
import com.applovin.sdk.AppLovinSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b implements Comparable<b> {
    private static String a = "MediatedNetwork";
    private final h b;
    private final n c;
    private final boolean d;
    private final boolean e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final List<MaxAdFormat> j;
    private final Map<String, ?> k;

    public enum a {
        MISSING,
        INCOMPLETE,
        COMPLETE
    }

    public b(JSONObject jSONObject, h hVar) {
        String adapterVersion;
        this.b = hVar;
        this.c = hVar.w();
        String sdkVersion = "";
        this.f = g.a(jSONObject, "display_name", "", hVar);
        this.i = g.a(jSONObject, "name", "", hVar);
        this.k = a(g.a(jSONObject, "ad_unit_ids", new JSONObject(), hVar));
        this.d = a(g.a(jSONObject, "existence_class", "", hVar));
        Class<? extends MaxAdapter> clsB = b(g.a(jSONObject, "adapter_class", "", hVar));
        if (clsB == null) {
            this.e = false;
            this.g = "";
            this.h = "";
            this.j = Collections.EMPTY_LIST;
            return;
        }
        List<MaxAdFormat> listA = Collections.EMPTY_LIST;
        try {
            MaxAdapter maxAdapterNewInstance = clsB.getConstructor(AppLovinSdk.class).newInstance(hVar.N());
            adapterVersion = maxAdapterNewInstance.getAdapterVersion();
            try {
                sdkVersion = maxAdapterNewInstance.getSdkVersion();
                listA = a(maxAdapterNewInstance);
            } catch (Throwable th) {
                th = th;
                this.c.e(a, "Failed to load adapter for network " + this.f + ". Please check that you have a compatible network SDK integrated. Error: " + th);
            }
        } catch (Throwable th2) {
            th = th2;
            adapterVersion = "";
        }
        this.e = true;
        this.h = adapterVersion;
        this.g = sdkVersion;
        this.j = listA;
    }

    private List<MaxAdFormat> a(MaxAdapter maxAdapter) {
        ArrayList arrayList = new ArrayList(5);
        if (maxAdapter instanceof MaxInterstitialAdapter) {
            arrayList.add(MaxAdFormat.INTERSTITIAL);
        }
        if (maxAdapter instanceof MaxRewardedAdapter) {
            arrayList.add(MaxAdFormat.REWARDED);
        }
        if (maxAdapter instanceof MaxAdViewAdapter) {
            arrayList.add(MaxAdFormat.BANNER);
            arrayList.add(MaxAdFormat.LEADER);
            arrayList.add(MaxAdFormat.MREC);
        }
        return arrayList;
    }

    private Map<String, ?> a(JSONObject jSONObject) {
        return new HashMap();
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Class<? extends MaxAdapter> b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.c.d(a, "Failed to create adapter instance. No class name provided");
            return null;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (MaxAdapter.class.isAssignableFrom(cls)) {
                return cls.asSubclass(MaxAdapter.class);
            }
            this.c.d(a, str + " error: not an instance of '" + MaxAdapter.class.getName() + "'.");
            return null;
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            this.c.b(a, "Failed to load: " + str, th);
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        return this.f.compareToIgnoreCase(bVar.f);
    }

    public a a() {
        if (this.d && this.e) {
            return a.COMPLETE;
        }
        return (this.d || this.e) ? a.INCOMPLETE : a.MISSING;
    }

    public boolean b() {
        return this.d;
    }

    public boolean c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public String toString() {
        return "MediatedNetwork{name=" + this.f + ", sdkAvailable=" + this.d + ", sdkVersion=" + this.g + ", adapterAvailable=" + this.e + ", adapterVersion=" + this.h + ", adUnitIds=" + this.k + "}";
    }
}
