package com.applovin.impl.mediation;

import android.app.Activity;
import android.os.Bundle;
import com.applovin.impl.sdk.n;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.applovin.impl.sdk.utils.a {
    private final com.applovin.impl.sdk.a a;
    private final n b;
    private InterfaceC0014a c;
    private com.applovin.impl.mediation.b.c d;
    private int e;
    private boolean f;

    /* JADX INFO: renamed from: com.applovin.impl.mediation.a$a, reason: collision with other inner class name */
    public interface InterfaceC0014a {
        void a(com.applovin.impl.mediation.b.c cVar);
    }

    a(com.applovin.impl.sdk.h hVar) {
        this.b = hVar.w();
        this.a = hVar.V();
    }

    public void a() {
        this.b.a("AdActivityObserver", "Cancelling...");
        this.a.b(this);
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = false;
    }

    public void a(com.applovin.impl.mediation.b.c cVar, InterfaceC0014a interfaceC0014a) {
        this.b.a("AdActivityObserver", "Starting for ad " + cVar.getAdUnitId() + "...");
        a();
        this.c = interfaceC0014a;
        this.d = cVar;
        this.a.a(this);
    }

    @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.f) {
            this.f = true;
        }
        this.e++;
        this.b.a("AdActivityObserver", "Created Activity: " + activity + ", counter is " + this.e);
    }

    @Override // com.applovin.impl.sdk.utils.a, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (this.f) {
            this.e--;
            this.b.a("AdActivityObserver", "Destroyed Activity: " + activity + ", counter is " + this.e);
            if (this.e <= 0) {
                this.b.a("AdActivityObserver", "Last ad Activity destroyed");
                if (this.c != null) {
                    this.b.a("AdActivityObserver", "Invoking callback...");
                    this.c.a(this.d);
                }
                a();
            }
        }
    }
}
