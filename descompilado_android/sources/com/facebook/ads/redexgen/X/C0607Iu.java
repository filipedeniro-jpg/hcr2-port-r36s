package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.ads.redexgen.X.AbstractC01210a;
import com.facebook.ads.redexgen.X.It;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Iu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0607Iu<T extends AbstractC01210a, E extends It> {
    private final Map<Class<E>, List<WeakReference<T>>> C = new HashMap();
    private final Queue<E> B = new ArrayDeque();

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    public final synchronized void A(E e) {
        if (this.B.isEmpty()) {
            this.B.add(e);
            while (!this.B.isEmpty()) {
                E event = this.B.peek();
                C(event);
                this.B.remove();
            }
        } else {
            this.B.add(e);
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    private void B(List<WeakReference<T>> list) {
        if (list != null) {
            int readPtr = 0;
            for (int writePtr = 0; writePtr < list.size(); writePtr++) {
                WeakReference<T> weakReference = list.get(writePtr);
                if (weakReference.get() != null) {
                    int writePtr2 = readPtr + 1;
                    list.set(readPtr, weakReference);
                    readPtr = writePtr2;
                }
            }
            for (int size = list.size() - 1; size >= readPtr; size--) {
                list.remove(size);
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    public final synchronized void B(T... tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                C(t);
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    private void C(E e) {
        List<WeakReference<T>> subscribers;
        if (this.C == null || (subscribers = this.C.get(e.getClass())) == null) {
            return;
        }
        B(subscribers);
        if (subscribers.isEmpty()) {
            return;
        }
        for (WeakReference<T> subscriberReference : new ArrayList(subscribers)) {
            T t = subscriberReference.get();
            if (t != null && t.C(e)) {
                t.B(e);
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    public final synchronized boolean C(T t) {
        boolean zAdd = false;
        synchronized (this) {
            if (t != null) {
                Class<E> clsA = t.A();
                if (this.C.get(clsA) == null) {
                    this.C.put(clsA, new ArrayList());
                }
                List<WeakReference<T>> list = this.C.get(clsA);
                B(list);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    T subscriber = list.get(i).get();
                    if (subscriber != t) {
                    }
                }
                zAdd = list.add(new WeakReference<>(t));
            }
        }
        return zAdd;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    public final synchronized void D(T... tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                E(t);
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.Iu != com.facebook.ads.internal.events.EventBus<T extends com.facebook.ads.redexgen.X.0a, E extends com.facebook.ads.redexgen.X.It> */
    public final synchronized boolean E(@Nullable T t) {
        boolean z = false;
        synchronized (this) {
            if (t != null) {
                List<WeakReference<T>> list = this.C.get(t.A());
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (list.get(i).get() == t) {
                            list.get(i).clear();
                            z = true;
                            break;
                        }
                    }
                }
            }
        }
        return z;
    }
}
