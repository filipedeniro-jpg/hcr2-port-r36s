package com.inmobi.commons.core.network;

/* JADX INFO: compiled from: AsyncNetworkTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    private static final String a = "a";
    private c b;
    private InterfaceC0077a c;

    /* JADX INFO: renamed from: com.inmobi.commons.core.network.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncNetworkTask.java */
    public interface InterfaceC0077a {
        void a(d dVar);

        void b(d dVar);
    }

    public a(c cVar, InterfaceC0077a interfaceC0077a) {
        this.b = cVar;
        this.c = interfaceC0077a;
    }

    public final void a() {
        new Thread(new Runnable() { // from class: com.inmobi.commons.core.network.a.1
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                try {
                    d dVarA = new b(a.this.b).a();
                    if (dVarA.a()) {
                        a.this.c.b(dVarA);
                    } else {
                        a.this.c.a(dVarA);
                    }
                } catch (Exception e) {
                    String unused = a.a;
                    new StringBuilder("Network request failed with unexpected error: ").append(e.getMessage());
                    NetworkError networkError = new NetworkError(NetworkError.ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                    d dVar = new d();
                    dVar.b = networkError;
                    a.this.c.b(dVar);
                }
            }
        }).start();
    }
}
