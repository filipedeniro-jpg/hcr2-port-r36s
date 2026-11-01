package com.integralads.avid.library.adcolony.weakreference;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectWrapper<T> {
    private WeakReference<T> a;

    public ObjectWrapper(T t) {
        this.a = new WeakReference<>(t);
    }

    public T get() {
        return this.a.get();
    }

    public void set(T t) {
        this.a = new WeakReference<>(t);
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean contains(Object obj) {
        T t = get();
        return (t == null || obj == null || !t.equals(obj)) ? false : true;
    }
}
