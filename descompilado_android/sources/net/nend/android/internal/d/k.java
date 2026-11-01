package net.nend.android.internal.d;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Promise.java */
/* JADX INFO: loaded from: classes2.dex */
public interface k<T> {
    k<T> a(Executor executor);

    k<T> a(b<? super T, Throwable> bVar);

    <R> k<R> a(c<? super T, Throwable, ? extends k<? extends R>> cVar);

    k<T> a(d<? super T> dVar);

    k<T> a(g<Throwable, ? extends T> gVar);

    k<T> b(d<Throwable> dVar);

    <R> k<R> b(g<? super T, ? extends k<? extends R>> gVar);

    boolean b();

    boolean c();
}
