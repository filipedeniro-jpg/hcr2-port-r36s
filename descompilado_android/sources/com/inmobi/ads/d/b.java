package com.inmobi.ads.d;

import com.inmobi.commons.core.f.b;
import com.inmobi.commons.core.utilities.b.e;

/* JADX INFO: compiled from: TRCCollectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements a {
    private final com.inmobi.b.a a;

    public b(com.inmobi.b.a aVar) {
        this.a = aVar;
    }

    @Override // com.inmobi.ads.d.a
    public final void a(com.inmobi.commons.core.f.b bVar) {
        com.inmobi.b.a aVar = this.a;
        if (aVar.c.b(bVar.j).h) {
            aVar.a.execute(new Runnable() { // from class: com.inmobi.b.a.1
                final /* synthetic */ b a;

                public AnonymousClass1() {
                    bVar = bVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    e.c();
                    if (e.d() == 0) {
                        String unused = a.e;
                    } else {
                        a.a(a.this, bVar);
                        a.a(a.this, bVar.j);
                    }
                }
            });
        }
    }
}
