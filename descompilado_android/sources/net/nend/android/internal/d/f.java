package net.nend.android.internal.d;

import java.util.Timer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: DeferredPromise.java */
/* JADX INFO: loaded from: classes2.dex */
class f<T> implements e<T>, k<T> {
    private T a = null;
    private Throwable b = null;
    private boolean c = false;
    private d<? super T> d = null;
    private d<Throwable> e = null;
    private b<? super T, Throwable> f = null;
    private g<Throwable, ? extends T> g = null;
    private Executor h = null;
    private Timer i;

    @Override // net.nend.android.internal.d.e
    public k<T> a() {
        return this;
    }

    f() {
    }

    @Override // net.nend.android.internal.d.e
    public void a(T t) {
        synchronized (this) {
            if (!this.c) {
                d();
                b(t);
            }
        }
    }

    @Override // net.nend.android.internal.d.e
    public void a(Throwable th) {
        synchronized (this) {
            if (!this.c) {
                d();
                if (this.g != null) {
                    b(this.g.a(th));
                } else {
                    b(th);
                }
            }
        }
    }

    @Override // net.nend.android.internal.d.k
    public k<T> a(Executor executor) {
        synchronized (this) {
            this.h = executor;
        }
        return this;
    }

    @Override // net.nend.android.internal.d.k
    public k<T> a(final d<? super T> dVar) {
        if (dVar == null) {
            return this;
        }
        synchronized (this) {
            if (this.c) {
                if (this.b == null) {
                    if (this.h != null) {
                        this.h.execute(new Runnable() { // from class: net.nend.android.internal.d.f.1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.lang.Runnable
                            public void run() {
                                dVar.a(f.this.a);
                            }
                        });
                    } else {
                        dVar.a(this.a);
                    }
                }
            } else {
                this.d = dVar;
            }
        }
        return this;
    }

    @Override // net.nend.android.internal.d.k
    public k<T> b(final d<Throwable> dVar) {
        if (dVar == null) {
            return this;
        }
        synchronized (this) {
            if (this.c) {
                if (this.b != null) {
                    if (this.h != null) {
                        this.h.execute(new Runnable() { // from class: net.nend.android.internal.d.f.2
                            @Override // java.lang.Runnable
                            public void run() {
                                dVar.a(f.this.b);
                            }
                        });
                    } else {
                        dVar.a(this.b);
                    }
                }
            } else {
                this.e = dVar;
            }
        }
        return this;
    }

    @Override // net.nend.android.internal.d.k
    public k<T> a(final b<? super T, Throwable> bVar) {
        if (bVar == null) {
            return this;
        }
        synchronized (this) {
            if (this.c) {
                if (this.h != null) {
                    this.h.execute(new Runnable() { // from class: net.nend.android.internal.d.f.3
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            bVar.a(f.this.a, f.this.b);
                        }
                    });
                } else {
                    bVar.a(this.a, this.b);
                }
            } else {
                this.f = bVar;
            }
        }
        return this;
    }

    @Override // net.nend.android.internal.d.k
    public k<T> a(g<Throwable, ? extends T> gVar) {
        synchronized (this) {
            if (this.c) {
                if (this.b != null) {
                    this.a = gVar.a(this.b);
                    this.b = null;
                }
            } else {
                this.g = gVar;
            }
        }
        return this;
    }

    @Override // net.nend.android.internal.d.k
    public <R> k<R> b(g<? super T, ? extends k<? extends R>> gVar) {
        return new j(this, gVar).a(this.h);
    }

    @Override // net.nend.android.internal.d.k
    public <R> k<R> a(c<? super T, Throwable, ? extends k<? extends R>> cVar) {
        return new j(this, cVar).a(this.h);
    }

    @Override // net.nend.android.internal.d.k
    public boolean b() {
        boolean z;
        synchronized (this) {
            z = !this.c;
        }
        return z;
    }

    @Override // net.nend.android.internal.d.k
    public boolean c() {
        synchronized (this) {
            if (this.c) {
                return false;
            }
            a((Throwable) new CancellationException());
            return true;
        }
    }

    private void b(T t) {
        this.a = t;
        this.c = true;
        try {
            a((d) this.d);
        } finally {
            a((b) this.f);
        }
    }

    private void b(Throwable th) {
        this.b = th;
        this.c = true;
        try {
            b(this.e);
        } finally {
            a((b) this.f);
        }
    }

    private void d() {
        Timer timer = this.i;
        if (timer != null) {
            timer.cancel();
            this.i = null;
        }
    }
}
