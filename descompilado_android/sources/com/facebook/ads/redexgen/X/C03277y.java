package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03277y<K, V> implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
    public int B;
    public boolean C = false;
    public int D = -1;
    public final /* synthetic */ AbstractC03107h E;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    public C03277y(AbstractC03107h this$0) {
        this.E = this$0;
        this.B = this$0.D() - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Iterator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Map.Entry<K, V> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.D++;
        this.C = true;
        return this;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Map.Entry
    public final boolean equals(Object o) {
        if (!this.C) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) o;
        return C03197q.D(entry.getKey(), this.E.B(this.D, 0)) && C03197q.D(entry.getValue(), this.E.B(this.D, 1));
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Map.Entry
    public final K getKey() {
        if (!this.C) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (K) this.E.B(this.D, 0);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Map.Entry
    public final V getValue() {
        if (!this.C) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (V) this.E.B(this.D, 1);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.D < this.B;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.C) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        Object objB = this.E.B(this.D, 0);
        Object objB2 = this.E.B(this.D, 1);
        return (objB2 != null ? objB2.hashCode() : 0) ^ (objB == null ? 0 : objB.hashCode());
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Iterator
    public final void remove() {
        if (!this.C) {
            throw new IllegalStateException();
        }
        this.E.H(this.D);
        this.D--;
        this.B--;
        this.C = false;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        if (!this.C) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (V) this.E.I(this.D, v);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7y != com.facebook.ads.internal.androidx.support.v4.util.MapCollections<K, V>$MapIterator */
    public final String toString() {
        return getKey() + Constants.RequestParameters.EQUAL + getValue();
    }
}
