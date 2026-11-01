package com.moat.analytics.mobile.iro.base.functional;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public final class Optional<T> {

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static final Optional<?> f511 = new Optional<>();

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final T f512;

    private Optional() {
        this.f512 = null;
    }

    public static <T> Optional<T> empty() {
        return (Optional<T>) f511;
    }

    private Optional(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f512 = t;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public final T get() {
        T t = this.f512;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public final boolean isPresent() {
        return this.f512 != null;
    }

    public final T orElse(T t) {
        T t2 = this.f512;
        return t2 != null ? t2 : t;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        T t = this.f512;
        T t2 = ((Optional) obj).f512;
        return t == t2 || !(t == null || t2 == null || !t.equals(t2));
    }

    public final int hashCode() {
        T t = this.f512;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    public final String toString() {
        T t = this.f512;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
