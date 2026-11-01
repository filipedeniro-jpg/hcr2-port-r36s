package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.h.a;
import com.chartboost.sdk.impl.ac;
import com.chartboost.sdk.impl.aq;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.s;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
class f implements Runnable {
    boolean a = false;
    Chartboost.CBFramework b = null;
    Chartboost.CBMediation c = null;
    String d = null;
    String e = null;
    CBLogging.Level f = null;
    ChartboostDelegate g = null;
    Activity h = null;
    String i = null;
    String j = null;
    private final int k;

    f(int i) {
        this.k = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            switch (this.k) {
                case 0:
                    if (h.a() == null) {
                        synchronized (h.class) {
                            if (h.a() == null) {
                                if (this.h == null) {
                                    CBLogging.b("ChartboostCommand", "Activity object is null. Please pass a valid activity object");
                                    return;
                                }
                                if (!b.a((Context) this.h)) {
                                    CBLogging.b("ChartboostCommand", "Permissions not set correctly");
                                    return;
                                }
                                if (!b.b(this.h)) {
                                    CBLogging.b("ChartboostCommand", "Please add CBImpressionActivity in AndroidManifest.xml following README.md instructions.");
                                }
                                if (!TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.j)) {
                                    s sVarA = s.a();
                                    g gVarA = g.a();
                                    Handler handler = sVarA.a;
                                    o.a();
                                    ScheduledExecutorService scheduledExecutorService = null;
                                    try {
                                        ScheduledExecutorService scheduledExecutorService2 = (ScheduledExecutorService) gVarA.a(ac.a());
                                        try {
                                            h hVar = new h(this.h, this.i, this.j, sVarA, scheduledExecutorService2, handler, (ExecutorService) gVarA.a(ac.a(4)));
                                            h.a(hVar);
                                            hVar.b.c();
                                            hVar.getClass();
                                            hVar.a(hVar.new a(3));
                                        } catch (Throwable th) {
                                            th = th;
                                            scheduledExecutorService = scheduledExecutorService2;
                                            if (scheduledExecutorService != null) {
                                                scheduledExecutorService.shutdown();
                                            }
                                            CBLogging.a("ChartboostCommand", "Unable to start threads", th);
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                CBLogging.b("ChartboostCommand", "AppId or AppSignature is null. Please pass a valid id's");
                                return;
                            }
                            return;
                        }
                    }
                    return;
                case 1:
                    i.u = this.a;
                    return;
                case 2:
                default:
                    return;
                case 3:
                    i.i = this.c;
                    i.j = this.d;
                    i.h = i.i + " " + i.j;
                    return;
                case 4:
                    if (this.b == null) {
                        CBLogging.b("ChartboostCommand", "Pass a valid CBFramework enum value");
                        return;
                    }
                    i.d = this.b;
                    i.e = this.d;
                    i.f = String.format("%s %s", this.b, this.d);
                    return;
                case 5:
                    b.a(this.d);
                    return;
                case 6:
                    i.a = this.e;
                    return;
                case 7:
                    if (b.b()) {
                        CBLogging.a = this.f;
                        return;
                    }
                    return;
                case 8:
                    i.c = this.g;
                    aq.a("SdkSettings.assignDelegate", this.g);
                    return;
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(f.class, "run (" + this.k + ")", e);
        }
        com.chartboost.sdk.Tracking.a.a(f.class, "run (" + this.k + ")", e);
    }
}
