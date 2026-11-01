package net.nend.android.internal.d;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: PromiseLite.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {
    public static <T> e<T> a() {
        return new f();
    }

    public static <T> k<T> a(T t) {
        e eVarA = a();
        eVarA.a(t);
        return eVarA.a();
    }

    public static <T> k<T> a(Throwable th) {
        e eVarA = a();
        eVarA.a(th);
        return eVarA.a();
    }

    public static <T> k<T> a(ExecutorService executorService, Callable<T> callable) {
        return new h(executorService, callable);
    }

    public static <T1, T2> k<m<T1, T2>> a(k<? extends T1> kVar, k<? extends T2> kVar2) {
        return a(new m(), (k<?>[]) new k[]{kVar, kVar2});
    }

    public static <T1, T2, T3> k<n<T1, T2, T3>> a(k<? extends T1> kVar, k<? extends T2> kVar2, k<? extends T3> kVar3) {
        return a(new n(), (k<?>[]) new k[]{kVar, kVar2, kVar3});
    }

    private static <T extends i> k<T> a(final T t, k<?>... kVarArr) {
        if (t == null || kVarArr.length == 0) {
            return a((Throwable) new IllegalArgumentException("An empty parameters was passed."));
        }
        int length = kVarArr.length;
        final Object[] objArr = new Object[length];
        final AtomicInteger atomicInteger = new AtomicInteger(length);
        final e eVarA = a();
        for (int i = 0; i < length; i++) {
            final int i2 = i;
            kVarArr[i].a(new b<Object, Throwable>() { // from class: net.nend.android.internal.d.l.1
                @Override // net.nend.android.internal.d.b
                public void a(Object obj, Throwable th) {
                    if (obj != null) {
                        objArr[i2] = obj;
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        t.a(objArr);
                        eVarA.a(t);
                    }
                }
            });
        }
        return eVarA.a();
    }
}
