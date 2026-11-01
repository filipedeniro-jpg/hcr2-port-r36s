package com.inmobi.commons.core.c;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.inmobi.commons.core.b.c;
import com.inmobi.commons.core.network.e;
import com.inmobi.commons.core.utilities.uid.d;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: EventSubmitter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String a = "a";
    private static volatile a b;
    private static ScheduledExecutorService c;
    private static final Object d = new Object();

    public static a a() {
        a aVar = b;
        if (aVar == null) {
            synchronized (d) {
                aVar = b;
                if (aVar == null) {
                    aVar = new a();
                    b = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
        c = Executors.newSingleThreadScheduledExecutor();
    }

    public final void a(final c cVar, final String str, final int i, final int i2, final long j, final d dVar, final b bVar) {
        if (!com.inmobi.commons.core.utilities.d.a() || !com.inmobi.commons.a.a.g()) {
            bVar.a(cVar, false);
            return;
        }
        final com.inmobi.commons.core.network.c cVar2 = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_POST, str, false, dVar);
        HashMap map = new HashMap();
        map.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, cVar.b);
        cVar2.c(map);
        int i3 = i - i2;
        if (i3 > 0) {
            HashMap map2 = new HashMap();
            map2.put("X-im-retry-count", String.valueOf(i3));
            cVar2.a(map2);
        }
        cVar2.u = false;
        c.schedule(new Runnable() { // from class: com.inmobi.commons.core.c.a.1
            @Override // java.lang.Runnable
            public final void run() {
                com.inmobi.commons.core.network.d dVarA = new e(cVar2).a();
                if (dVarA.a()) {
                    if (i2 > 1) {
                        String unused = a.a;
                        StringBuilder sb = new StringBuilder("Unable to send trc events to server: ");
                        sb.append(dVarA.b());
                        sb.append(". Will retry");
                        a.this.a(cVar, str, i, i2 - 1, j, dVar, bVar);
                        return;
                    }
                    bVar.a(cVar, true);
                    return;
                }
                bVar.a(cVar);
            }
        }, i2 != i ? j : 0L, TimeUnit.SECONDS);
    }
}
