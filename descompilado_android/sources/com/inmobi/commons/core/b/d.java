package com.inmobi.commons.core.b;

import androidx.annotation.NonNull;
import com.my.target.aa;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: EventProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public class d implements com.inmobi.commons.core.c.b {
    private static final String a = "d";
    private b d;
    private e e;
    private ScheduledExecutorService h;
    private AtomicBoolean b = new AtomicBoolean(false);
    private AtomicBoolean c = new AtomicBoolean(false);
    private List<String> g = new LinkedList();
    private HashMap<String, a> f = new HashMap<>(1);

    public d(@NonNull b bVar, @NonNull e eVar, @NonNull a aVar) {
        this.d = bVar;
        this.e = eVar;
        a(aVar);
    }

    public final void a() {
        ScheduledExecutorService scheduledExecutorService = this.h;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.h = null;
        }
        this.b.set(false);
        this.c.set(true);
        this.g.clear();
        this.f.clear();
    }

    @NonNull
    private a b(@NonNull String str) {
        return this.f.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, com.inmobi.commons.core.utilities.uid.d dVar) {
        int i;
        long j;
        c cVarA;
        if (this.c.get() || this.b.get()) {
            return;
        }
        this.d.b(b(str).a, str);
        int iA = this.d.a(str);
        int iA2 = com.inmobi.commons.core.utilities.b.b.a();
        if (iA2 == 1) {
            i = b(str).g;
        } else {
            i = b(str).i;
        }
        if (iA2 == 1) {
            j = b(str).h;
        } else {
            j = b(str).j;
        }
        if ((i <= iA || this.d.a(b(str).c, str)) && (cVarA = this.e.a(str)) != null) {
            this.b.set(true);
            a aVarB = b(str);
            com.inmobi.commons.core.c.a aVarA = com.inmobi.commons.core.c.a.a();
            String str2 = aVarB.e;
            int i2 = aVarB.d + 1;
            aVarA.a(cVarA, str2, i2, i2, j, dVar, this);
        }
    }

    @Override // com.inmobi.commons.core.c.b
    public final void a(c cVar) {
        String strB = this.d.b(cVar.a.get(0).intValue());
        this.d.a(cVar.a);
        if (strB != null) {
            this.d.c(System.currentTimeMillis(), strB);
            this.b.set(false);
        }
    }

    @Override // com.inmobi.commons.core.c.b
    public final void a(c cVar, boolean z) {
        String strB = this.d.b(cVar.a.get(0).intValue());
        if (cVar.c && z) {
            this.d.a(cVar.a);
        }
        if (strB != null) {
            this.d.c(System.currentTimeMillis(), strB);
            this.b.set(false);
        }
    }

    public final void a(@NonNull a aVar) {
        String str = aVar.b;
        if (str == null) {
            str = aa.f.bq;
        }
        this.f.put(str, aVar);
    }

    public final void a(@NonNull final String str) {
        if (this.c.get()) {
            return;
        }
        if (str == null) {
            str = aa.f.bq;
        }
        long j = b(str).f;
        if (j <= 0) {
            a(str, (com.inmobi.commons.core.utilities.uid.d) null);
            return;
        }
        if (this.g.contains(str)) {
            return;
        }
        this.g.add(str);
        if (this.h == null) {
            this.h = Executors.newSingleThreadScheduledExecutor();
        }
        ScheduledExecutorService scheduledExecutorService = this.h;
        Runnable runnable = new Runnable() { // from class: com.inmobi.commons.core.b.d.1
            final /* synthetic */ com.inmobi.commons.core.utilities.uid.d b = null;

            @Override // java.lang.Runnable
            public final void run() {
                d.this.a(str, this.b);
            }
        };
        a aVarB = b(str);
        long jB = this.d.b(str);
        if (jB == -1) {
            this.d.c(System.currentTimeMillis(), str);
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(jB) + aVarB.f;
        scheduledExecutorService.scheduleAtFixedRate(runnable, seconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) > 0 ? seconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) : 0L, j, TimeUnit.SECONDS);
    }
}
