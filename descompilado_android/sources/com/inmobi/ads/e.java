package com.inmobi.ads;

import android.os.SystemClock;

/* JADX INFO: compiled from: AdNetworkClient.java */
/* JADX INFO: loaded from: classes2.dex */
final class e implements com.inmobi.commons.core.network.a.InterfaceC0077a {
    private static final String a = "e";
    private f b;
    private a c;
    private long d = 0;

    /* JADX INFO: compiled from: AdNetworkClient.java */
    public interface a {
        void a(g gVar);

        void b(g gVar);
    }

    public e(f fVar, a aVar) {
        this.b = fVar;
        this.c = aVar;
    }

    public final void a() {
        this.d = SystemClock.elapsedRealtime();
        new com.inmobi.commons.core.network.a(this.b, this).a();
    }

    @Override // com.inmobi.commons.core.network.a.InterfaceC0077a
    public final void a(com.inmobi.commons.core.network.d dVar) {
        g gVar = new g(this.b, dVar);
        try {
            com.inmobi.a.n.a().a(this.b.g());
            com.inmobi.a.n.a().b(dVar.c());
            com.inmobi.a.n.a().c(SystemClock.elapsedRealtime() - this.d);
            this.c.a(gVar);
        } catch (Exception e) {
            new StringBuilder("Handling ad fetch success encountered an unexpected error: ").append(e.getMessage());
        }
    }

    @Override // com.inmobi.commons.core.network.a.InterfaceC0077a
    public final void b(com.inmobi.commons.core.network.d dVar) {
        g gVar = new g(this.b, dVar);
        new StringBuilder("Ad fetch failed:").append(gVar.a.b.b);
        try {
            com.inmobi.a.n.a().a(this.b.g());
            com.inmobi.a.n.a().b(dVar.c());
            this.c.b(gVar);
        } catch (Exception e) {
            new StringBuilder("Handling ad fetch failed encountered an unexpected error: ").append(e.getMessage());
        }
    }
}
