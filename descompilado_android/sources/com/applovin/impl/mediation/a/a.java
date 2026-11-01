package com.applovin.impl.mediation.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.applovin.impl.mediation.a.c.b;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.g;
import com.applovin.mediation.MaxDebuggerActivity;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a implements com.applovin.impl.sdk.network.a.b<JSONObject> {
    private static WeakReference<MaxDebuggerActivity> a;
    private static final AtomicBoolean b = new AtomicBoolean();
    private final h c;
    private final n d;
    private final b e;
    private final AtomicBoolean f = new AtomicBoolean();
    private boolean g;

    public a(h hVar) {
        this.c = hVar;
        this.d = hVar.w();
        this.e = new b(hVar.z());
    }

    private void a(JSONArray jSONArray) {
        this.d.a("MediationDebuggerService", "Updating networks...");
        try {
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectA = g.a(jSONArray, i, (JSONObject) null, this.c);
                if (jSONObjectA != null) {
                    arrayList.add(new com.applovin.impl.mediation.a.a.b(jSONObjectA, this.c));
                }
            }
            Collections.sort(arrayList);
            this.e.a(arrayList);
        } catch (Throwable th) {
            this.d.b("MediationDebuggerService", "Failed to parse mediated network json object.", th);
        }
    }

    private void e() {
        if (this.f.compareAndSet(false, true)) {
            this.c.F().a(new com.applovin.impl.mediation.a.b.a(this, this.c), q.a.MEDIATION_MAIN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        WeakReference<MaxDebuggerActivity> weakReference = a;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.applovin.impl.sdk.network.a.b
    public void a(int i) {
        this.d.d("MediationDebuggerService", "Unable to fetch mediation debugger info: server returned " + i);
        this.d.e("AppLovinSdk", "Unable to show mediation debugger.");
        this.e.a((List<com.applovin.impl.mediation.a.a.b>) null);
        this.f.set(false);
    }

    @Override // com.applovin.impl.sdk.network.a.b
    public void a(JSONObject jSONObject, int i) {
        this.c.x().a(g.a(jSONObject, "networks", new JSONArray(), this.c));
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return this.g;
    }

    public void b() {
        e();
        if (f() || !b.compareAndSet(false, true)) {
            this.d.e("AppLovinSdk", "Mediation Debugger is already showing.");
            return;
        }
        this.c.V().a(new com.applovin.impl.sdk.utils.a() { // from class: com.applovin.impl.mediation.a.a.1
            @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                if (activity instanceof MaxDebuggerActivity) {
                    a.this.c.V().b(this);
                    WeakReference unused = a.a = null;
                }
            }

            @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if (activity instanceof MaxDebuggerActivity) {
                    if (!a.this.f() || a.a.get() != activity) {
                        MaxDebuggerActivity maxDebuggerActivity = (MaxDebuggerActivity) activity;
                        WeakReference unused = a.a = new WeakReference(maxDebuggerActivity);
                        maxDebuggerActivity.setListAdapter(a.this.e);
                    }
                    a.b.set(false);
                }
            }
        });
        Context contextZ = this.c.z();
        Intent intent = new Intent(contextZ, (Class<?>) MaxDebuggerActivity.class);
        intent.setFlags(DriveFile.MODE_READ_ONLY);
        contextZ.startActivity(intent);
    }

    public String toString() {
        return "MediationDebuggerService{, listAdapter=" + this.e + "}";
    }
}
