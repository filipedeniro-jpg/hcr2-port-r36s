package com.applovin.impl.mediation;

import android.text.TextUtils;
import com.applovin.impl.sdk.n;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapters.MediationAdapterBase;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class g {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private final Object c = new Object();
    private final Map<String, Class<? extends MaxAdapter>> d = new HashMap();
    private final Set<String> e = new HashSet();

    g(com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = hVar;
        this.b = hVar.w();
    }

    private h a(com.applovin.impl.mediation.b.e eVar, Class<? extends MaxAdapter> cls) {
        try {
            h hVar = new h(eVar, (MediationAdapterBase) cls.getConstructor(AppLovinSdk.class).newInstance(this.a.N()), this.a);
            if (hVar.c()) {
                return hVar;
            }
            this.b.e("MediationAdapterManager", "Adapter is disabled after initialization: " + eVar);
            return null;
        } catch (Throwable th) {
            this.b.c("MediationAdapterManager", "Failed to load adapter: " + eVar, th);
            return null;
        }
    }

    private Class<? extends MaxAdapter> a(String str) {
        n nVar;
        String str2;
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null) {
                nVar = this.b;
                str2 = "No class found for " + str;
            } else {
                if (MaxAdapter.class.isAssignableFrom(cls)) {
                    return cls.asSubclass(MaxAdapter.class);
                }
                nVar = this.b;
                str2 = str + " error: not an instance of '" + MaxAdapter.class.getName() + "'.";
            }
            nVar.e("MediationAdapterManager", str2);
            return null;
        } catch (Throwable th) {
            this.b.c("MediationAdapterManager", "Failed to load: " + str, th);
            return null;
        }
    }

    h a(com.applovin.impl.mediation.b.e eVar) {
        Class<? extends MaxAdapter> clsA;
        n nVar;
        String str;
        if (eVar == null) {
            throw new IllegalArgumentException("No adapter spec specified");
        }
        String strV = eVar.v();
        String strU = eVar.u();
        if (TextUtils.isEmpty(strV)) {
            nVar = this.b;
            str = "No adapter name provided for " + strU + ", not loading the adapter ";
        } else {
            if (!TextUtils.isEmpty(strU)) {
                synchronized (this.c) {
                    if (this.e.contains(strU)) {
                        this.b.a("MediationAdapterManager", "Not attempting to load " + strV + " due to prior errors");
                        return null;
                    }
                    if (this.d.containsKey(strU)) {
                        clsA = this.d.get(strU);
                    } else {
                        clsA = a(strU);
                        if (clsA == null) {
                            this.e.add(strU);
                            this.b.e("MediationAdapterManager", "Failed to load adapter classname: " + strU);
                            return null;
                        }
                    }
                    h hVarA = a(eVar, clsA);
                    if (hVarA != null) {
                        this.b.a("MediationAdapterManager", "Loaded " + strV);
                        this.d.put(strU, clsA);
                        return hVarA;
                    }
                    this.b.d("MediationAdapterManager", "Failed to load " + strV);
                    this.e.add(strU);
                    return null;
                }
            }
            nVar = this.b;
            str = "Unable to find default classname for '" + strV + "'";
        }
        nVar.d("MediationAdapterManager", str);
        return null;
    }

    Collection<String> a() {
        Set setUnmodifiableSet;
        synchronized (this.c) {
            HashSet hashSet = new HashSet(this.d.size());
            Iterator<Class<? extends MaxAdapter>> it = this.d.values().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getName());
            }
            setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        }
        return setUnmodifiableSet;
    }

    Collection<String> b() {
        Set setUnmodifiableSet;
        synchronized (this.c) {
            setUnmodifiableSet = Collections.unmodifiableSet(this.e);
        }
        return setUnmodifiableSet;
    }
}
