package com.applovin.impl.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class s {
    private static final String[] a = {Constants.ParametersKeys.VIDEO_STATUS_PAUSED, "saved_instance_state"};
    private static final String[] b = {"saved_instance_state", Constants.ParametersKeys.VIDEO_STATUS_PAUSED};
    private static final String[] c = {Constants.ParametersKeys.VIDEO_STATUS_PAUSED, Constants.ParametersKeys.VIDEO_STATUS_STOPPED};
    private static final String[] d = {Constants.ParametersKeys.VIDEO_STATUS_PAUSED, "saved_instance_state", Constants.ParametersKeys.VIDEO_STATUS_STOPPED, Constants.ParametersKeys.VIDEO_STATUS_STARTED};
    private static final String[] e = {Constants.ParametersKeys.VIDEO_STATUS_PAUSED, Constants.ParametersKeys.VIDEO_STATUS_STOPPED, "saved_instance_state", Constants.ParametersKeys.VIDEO_STATUS_STARTED};
    private static final String[] f = {"saved_instance_state", Constants.ParametersKeys.VIDEO_STATUS_PAUSED, Constants.ParametersKeys.VIDEO_STATUS_STOPPED, Constants.ParametersKeys.VIDEO_STATUS_STARTED};
    private final h g;
    private volatile boolean l;
    private Date m;
    private Date n;
    private final List<String> h = new ArrayList();
    private final AtomicBoolean i = new AtomicBoolean();
    private final AtomicBoolean j = new AtomicBoolean();
    private final AtomicBoolean k = new AtomicBoolean();
    private final List<a> o = new ArrayList();
    private final Object p = new Object();

    public interface a {
        void b();

        void c();
    }

    s(h hVar) {
        this.g = hVar;
    }

    private void a(boolean z) {
        this.l = true;
        l();
        if (!z && ((Boolean) this.g.a(com.applovin.impl.sdk.b.b.eb)).booleanValue()) {
            boolean zBooleanValue = ((Boolean) this.g.a(com.applovin.impl.sdk.b.b.dY)).booleanValue();
            long millis = TimeUnit.MINUTES.toMillis(((Long) this.g.a(com.applovin.impl.sdk.b.b.ea)).longValue());
            if (this.m == null || System.currentTimeMillis() - this.m.getTime() >= millis) {
                ((EventServiceImpl) this.g.r()).a(Constants.ParametersKeys.VIDEO_STATUS_PAUSED, false);
                if (zBooleanValue) {
                    this.m = new Date();
                }
            }
            if (zBooleanValue) {
                return;
            }
            this.m = new Date();
        }
    }

    private static boolean a(List<String> list, String[] strArr) {
        int size = list.size();
        int length = strArr.length;
        if (size == 0 || length == 0 || size < strArr.length) {
            return false;
        }
        int i = size - length;
        for (int i2 = i; i2 < length; i2++) {
            if (!list.get(i2).equals(strArr[i2 - i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.h.add(Constants.ParametersKeys.VIDEO_STATUS_PAUSED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (a(this.h, c)) {
            a(this.k.get());
        }
        this.h.add("saved_instance_state");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (a(this.h, a) || a(this.h, b)) {
            a(this.k.get());
        }
        this.h.add(Constants.ParametersKeys.VIDEO_STATUS_STOPPED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.h.isEmpty()) {
            return;
        }
        List<String> list = this.h;
        String str = list.get(list.size() - 1);
        if (Constants.ParametersKeys.VIDEO_STATUS_STOPPED.equals(str) || "saved_instance_state".equals(str)) {
            this.h.add(Constants.ParametersKeys.VIDEO_STATUS_STARTED);
        } else {
            this.h.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (a(this.h, d) || a(this.h, e) || a(this.h, f)) {
            boolean zBooleanValue = ((Boolean) this.g.a(com.applovin.impl.sdk.b.b.dY)).booleanValue();
            long jLongValue = ((Long) this.g.a(com.applovin.impl.sdk.b.b.dZ)).longValue();
            this.l = false;
            m();
            if (this.k.getAndSet(false)) {
                return;
            }
            long millis = TimeUnit.MINUTES.toMillis(jLongValue);
            if (this.n == null || System.currentTimeMillis() - this.n.getTime() >= millis) {
                ((EventServiceImpl) this.g.r()).a("resumed", false);
                if (zBooleanValue) {
                    this.n = new Date();
                }
            }
            if (!zBooleanValue) {
                this.n = new Date();
            }
            this.g.G().a(com.applovin.impl.sdk.c.g.n);
            this.j.set(true);
        }
        this.h.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.h.clear();
    }

    private void l() {
        ArrayList arrayList;
        synchronized (this.p) {
            arrayList = new ArrayList(this.o);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((a) it.next()).b();
        }
    }

    private void m() {
        ArrayList arrayList;
        synchronized (this.p) {
            arrayList = new ArrayList(this.o);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((a) it.next()).c();
        }
    }

    void a(Context context) {
        if (context == null || !com.applovin.impl.sdk.utils.e.b() || !((Boolean) this.g.a(com.applovin.impl.sdk.b.b.dX)).booleanValue() || this.i.getAndSet(true)) {
            return;
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        ((Application) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.applovin.impl.sdk.s.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                s.this.k();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                s.this.k();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                s.this.f();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                s.this.j();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                s.this.g();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                s.this.i();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                s.this.h();
            }
        });
    }

    public void a(a aVar) {
        synchronized (this.p) {
            this.o.add(aVar);
        }
    }

    public boolean a() {
        return this.l;
    }

    public void b() {
        this.k.set(true);
    }

    public void b(a aVar) {
        synchronized (this.p) {
            this.o.remove(aVar);
        }
    }

    public void c() {
        this.k.set(false);
    }

    boolean d() {
        return this.i.get();
    }

    boolean e() {
        return this.j.getAndSet(false);
    }
}
