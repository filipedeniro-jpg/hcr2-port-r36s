package com.facebook.ads.redexgen.X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7v, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03247v<T> implements Iterator<T> {
    public boolean B = false;
    public int C;
    public final int D;
    public int E;
    public final /* synthetic */ AbstractC03107h F;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7v != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$ArrayIterator<T> */
    public C03247v(AbstractC03107h this$0, int offset) {
        this.F = this$0;
        this.D = offset;
        this.E = this$0.D();
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7v != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$ArrayIterator<T> */
    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.C < this.E;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7v != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$ArrayIterator<T> */
    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T t = (T) this.F.B(this.C, this.D);
        this.C++;
        this.B = true;
        return t;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7v != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$ArrayIterator<T> */
    @Override // java.util.Iterator
    public final void remove() {
        if (!this.B) {
            throw new IllegalStateException();
        }
        this.C--;
        this.E--;
        this.B = false;
        this.F.H(this.C);
    }
}
