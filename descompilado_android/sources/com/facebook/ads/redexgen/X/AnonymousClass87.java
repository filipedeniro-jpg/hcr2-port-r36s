package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.87, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnonymousClass87<T> implements AnonymousClass86<T> {
    private final Object[] B;
    private int C;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.87 != com.facebook.ads.internal.androidx.support.v4.util.Pools$SimplePool<T> */
    public AnonymousClass87(int maxPoolSize) {
        if (maxPoolSize <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.B = new Object[maxPoolSize];
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.87 != com.facebook.ads.internal.androidx.support.v4.util.Pools$SimplePool<T> */
    private boolean B(T t) {
        for (int i = 0; i < this.C; i++) {
            if (this.B[i] == t) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.87 != com.facebook.ads.internal.androidx.support.v4.util.Pools$SimplePool<T> */
    @Override // com.facebook.ads.redexgen.X.AnonymousClass86
    public T SB() {
        if (this.C <= 0) {
            return null;
        }
        int i = this.C - 1;
        T t = (T) this.B[i];
        this.B[i] = null;
        this.C--;
        return t;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.87 != com.facebook.ads.internal.androidx.support.v4.util.Pools$SimplePool<T> */
    @Override // com.facebook.ads.redexgen.X.AnonymousClass86
    public boolean lF(T t) {
        if (B(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        if (this.C >= this.B.length) {
            return false;
        }
        this.B[this.C] = t;
        this.C++;
        return true;
    }
}
