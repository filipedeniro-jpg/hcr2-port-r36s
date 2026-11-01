package com.applovin.impl.sdk.network;

import android.content.SharedPreferences;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.k;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private final h a;
    private final n b;
    private ArrayList<e> c;
    private ArrayList<e> d;
    private final Object e;
    private final SharedPreferences f;

    public d(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = hVar;
        this.b = hVar.w();
        this.f = hVar.z().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
        this.e = new Object();
        this.c = c();
        this.d = new ArrayList<>();
    }

    private void b(e eVar) {
        synchronized (this.e) {
            if (this.c.size() < ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dA)).intValue()) {
                this.c.add(eVar);
                d();
                this.b.a("PersistentPostbackManager", "Enqueued postback: " + eVar);
            } else {
                this.b.c("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + eVar);
            }
        }
    }

    private ArrayList<e> c() {
        Set<String> set = (Set) this.a.b(com.applovin.impl.sdk.b.d.g, new LinkedHashSet(0), this.f);
        ArrayList<e> arrayList = new ArrayList<>(Math.max(1, set.size()));
        int iIntValue = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dB)).intValue();
        this.b.a("PersistentPostbackManager", "Deserializing " + set.size() + " postback(s).");
        for (String str : set) {
            try {
                e eVar = new e(new JSONObject(str), this.a);
                if ((!((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dC)).booleanValue() || eVar.f() >= iIntValue) && eVar.f() <= iIntValue) {
                    this.b.a("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + eVar);
                } else {
                    arrayList.add(eVar);
                }
            } catch (Throwable th) {
                this.b.b("PersistentPostbackManager", "Unable to deserialize postback request from json: " + str, th);
            }
        }
        this.b.a("PersistentPostbackManager", "Successfully loaded postback queue with " + arrayList.size() + " postback(s).");
        return arrayList;
    }

    private void c(final e eVar) {
        this.b.a("PersistentPostbackManager", "Preparing to submit postback..." + eVar);
        if (this.a.c()) {
            this.b.a("PersistentPostbackManager", "Skipping postback dispatch because SDK is still initializing - postback will be dispatched afterwards");
            return;
        }
        synchronized (this.e) {
            eVar.g();
            d();
        }
        int iIntValue = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dB)).intValue();
        if (eVar.f() <= iIntValue) {
            this.a.M().dispatchPostbackRequest(f.b(this.a).a(eVar.a()).b(eVar.d()).c(eVar.b()).a(eVar.c() != null ? new JSONObject(eVar.c()) : null).a(eVar.e()).a(), new AppLovinPostbackListener() { // from class: com.applovin.impl.sdk.network.d.1
                @Override // com.applovin.sdk.AppLovinPostbackListener
                public void onPostbackFailure(String str, int i) {
                    d.this.b.b("PersistentPostbackManager", "Failed to submit postback with errorCode " + i + ". Will retry later...  Postback: " + eVar);
                    d.this.e(eVar);
                }

                @Override // com.applovin.sdk.AppLovinPostbackListener
                public void onPostbackSuccess(String str) {
                    d.this.d(eVar);
                    d.this.b.a("PersistentPostbackManager", "Successfully submitted postback: " + eVar);
                    d.this.b();
                }
            });
            return;
        }
        this.b.c("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + iIntValue + ". Dequeuing postback: " + eVar);
        d(eVar);
    }

    private void d() {
        n nVar;
        String str;
        if (com.applovin.impl.sdk.utils.e.b()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.c.size());
            Iterator<e> it = this.c.iterator();
            while (it.hasNext()) {
                try {
                    linkedHashSet.add(it.next().i().toString());
                } catch (Throwable th) {
                    this.b.b("PersistentPostbackManager", "Unable to serialize postback request to JSON.", th);
                }
            }
            this.a.a((com.applovin.impl.sdk.b.d<LinkedHashSet>) com.applovin.impl.sdk.b.d.g, linkedHashSet, this.f);
            nVar = this.b;
            str = "Wrote updated postback queue to disk.";
        } else {
            nVar = this.b;
            str = "Skipping writing postback queue to disk due to old Android version...";
        }
        nVar.a("PersistentPostbackManager", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(e eVar) {
        synchronized (this.e) {
            this.c.remove(eVar);
            d();
        }
        this.b.a("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(e eVar) {
        synchronized (this.e) {
            this.d.add(eVar);
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.c != null) {
                Iterator it = new ArrayList(this.c).iterator();
                while (it.hasNext()) {
                    c((e) it.next());
                }
            }
        }
    }

    public void a(e eVar) {
        a(eVar, true);
    }

    public void a(e eVar, boolean z) {
        if (k.b(eVar.a())) {
            if (z) {
                eVar.h();
            }
            synchronized (this.e) {
                b(eVar);
                c(eVar);
            }
        }
    }

    public void b() {
        synchronized (this.e) {
            Iterator<e> it = this.d.iterator();
            while (it.hasNext()) {
                c(it.next());
            }
            this.d.clear();
        }
    }
}
