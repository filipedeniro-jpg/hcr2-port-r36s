package com.integralads.avid.library.inmobi.weakreference;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class ObjectWrapper<T> {
    private WeakReference<T> weakReference;

    public ObjectWrapper(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    public T get() {
        return this.weakReference.get();
    }

    public void set(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean contains(Object obj) {
        T t = get();
        return (t == null || obj == null || !t.equals(obj)) ? false : true;
    }
}
