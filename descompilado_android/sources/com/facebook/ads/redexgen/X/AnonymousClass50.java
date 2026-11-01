package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.50, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(9)
public final class AnonymousClass50<T> {
    private final LinkedBlockingDeque<T> B;
    private final int C;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    public AnonymousClass50(int circularBufferCapacity) {
        this.B = new LinkedBlockingDeque<>(circularBufferCapacity);
        this.C = circularBufferCapacity;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    public final synchronized void A(T t) {
        if (this.C > 0) {
            if (!this.B.isEmpty() && E() == 0) {
                this.B.removeFirst();
            }
            this.B.offer(t);
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    public final synchronized List<T> B() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<T> it = this.B.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    @InterfaceC01641r
    public final synchronized T C() {
        T tPeekFirst;
        if (!this.B.isEmpty()) {
            tPeekFirst = this.B.peekFirst();
        } else {
            tPeekFirst = null;
        }
        return tPeekFirst;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    @InterfaceC01641r
    public final synchronized T D() {
        T tPeekLast;
        if (!this.B.isEmpty()) {
            tPeekLast = this.B.peekLast();
        } else {
            tPeekLast = null;
        }
        return tPeekLast;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<T> */
    public final synchronized int E() {
        return this.B.remainingCapacity();
    }
}
