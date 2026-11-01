package com.moat.analytics.mobile.inm.a.b;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class a<T> {
    private static final a<?> a = new a<>();
    private final T b;

    private a() {
        this.b = null;
    }

    private a(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.b = t;
    }

    public static <T> a<T> a() {
        return (a<T>) a;
    }

    public static <T> a<T> a(T t) {
        return new a<>(t);
    }

    public static <T> a<T> b(T t) {
        return t == null ? a() : a(t);
    }

    public final T b() {
        T t = this.b;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public final T c(T t) {
        T t2 = this.b;
        return t2 != null ? t2 : t;
    }

    public final boolean c() {
        return this.b != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        T t = this.b;
        T t2 = ((a) obj).b;
        return t == t2 || !(t == null || t2 == null || !t.equals(t2));
    }

    public final int hashCode() {
        T t = this.b;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    public final String toString() {
        T t = this.b;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
