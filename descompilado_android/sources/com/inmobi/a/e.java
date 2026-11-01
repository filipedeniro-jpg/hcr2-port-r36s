package com.inmobi.a;

import android.os.SystemClock;

/* JADX INFO: compiled from: CarbNetworkClient.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    private static final String a = "e";

    static c a(b bVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        com.inmobi.commons.core.network.d dVarA = new com.inmobi.commons.core.network.e(bVar).a();
        c cVar = new c(dVarA);
        try {
            n.a().a(bVar.g());
            n.a().b(dVarA.c());
            n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
        } catch (Exception e) {
            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
        }
        boolean z = cVar.a;
        return cVar;
    }

    static boolean a(f fVar) {
        boolean z = false;
        int i = 0;
        while (i <= fVar.a) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            com.inmobi.commons.core.network.d dVarA = new com.inmobi.commons.core.network.e(fVar).a();
            try {
                n.a().a(fVar.g());
                n.a().b(dVarA.c());
                n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
            } catch (Exception e) {
                new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
            }
            if (!dVarA.a()) {
                z = true;
                break;
            }
            i++;
            if (i > fVar.a) {
                break;
            }
            try {
                Thread.sleep(fVar.b * 1000);
            } catch (InterruptedException unused) {
            }
        }
        if (!z) {
            try {
                com.inmobi.commons.core.e.b.a().a(new com.inmobi.commons.core.e.f("signals", "CarbUploadDiscarded"));
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
            }
        }
        return z;
    }
}
