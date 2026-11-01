package net.nend.android.internal.d;

/* JADX INFO: compiled from: PipedPromise.java */
/* JADX INFO: loaded from: classes2.dex */
class j<T, R> extends f<R> {
    private final k<T> a;
    private k<? extends R> b;

    private j(k<T> kVar) {
        this.a = kVar;
    }

    j(k<T> kVar, final g<? super T, ? extends k<? extends R>> gVar) {
        this(kVar);
        c(new d<T>() { // from class: net.nend.android.internal.d.j.1
            @Override // net.nend.android.internal.d.d
            public void a(T t) {
                try {
                    j.this.a((k) gVar.a(t));
                } catch (Exception e) {
                    j.this.a(e.getCause());
                }
            }
        });
    }

    j(k<T> kVar, final c<? super T, Throwable, ? extends k<? extends R>> cVar) {
        this(kVar);
        b((b) new b<T, Throwable>() { // from class: net.nend.android.internal.d.j.2
            @Override // net.nend.android.internal.d.b
            public void a(T t, Throwable th) {
                try {
                    j.this.a((k) cVar.a(t, th));
                } catch (Exception e) {
                    j.this.a(e.getCause());
                }
            }
        });
    }

    private void c(final d<? super T> dVar) {
        b((b) new b<T, Throwable>() { // from class: net.nend.android.internal.d.j.3
            @Override // net.nend.android.internal.d.b
            public void a(T t, Throwable th) {
                if (th != null) {
                    j.this.a(th);
                } else {
                    dVar.a(t);
                }
            }
        });
    }

    private void b(b<? super T, Throwable> bVar) {
        this.a.a(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k<? extends R> kVar) {
        this.b = kVar;
        this.b.a((d<? super Object>) new d<R>() { // from class: net.nend.android.internal.d.j.4
            @Override // net.nend.android.internal.d.d
            public void a(R r) {
                j.this.a(r);
            }
        });
        this.b.b(new d<Throwable>() { // from class: net.nend.android.internal.d.j.5
            @Override // net.nend.android.internal.d.d
            public void a(Throwable th) {
                j.this.a(th);
            }
        });
    }

    @Override // net.nend.android.internal.d.f, net.nend.android.internal.d.k
    public boolean c() {
        k<? extends R> kVar;
        return this.a.c() || ((kVar = this.b) != null && kVar.c()) || super.c();
    }
}
