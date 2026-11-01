package com.inmobi.ads;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ImpressionTracker.java */
/* JADX INFO: loaded from: classes2.dex */
class y {
    private static final String b = "y";

    @NonNull
    final Map<View, b> a;

    @NonNull
    private final ce c;

    @NonNull
    private final Map<View, b> d;

    @NonNull
    private final Handler e;

    @NonNull
    private final c f;
    private final long g;

    @Nullable
    private ce.c h;

    @NonNull
    private a i;

    /* JADX INFO: compiled from: ImpressionTracker.java */
    interface a {
        void a(View view, Object obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ImpressionTracker.java */
    static class b {
        Object a;
        int b;
        int c;
        long d = Long.MAX_VALUE;

        b(Object obj, int i, int i2) {
            this.a = obj;
            this.b = i;
            this.c = i2;
        }
    }

    y(com.inmobi.ads.c.k kVar, @NonNull ce ceVar, @NonNull a aVar) {
        this(new WeakHashMap(), new WeakHashMap(), ceVar, new Handler(), kVar, aVar);
    }

    private y(@NonNull Map<View, b> map, @NonNull Map<View, b> map2, @NonNull ce ceVar, @NonNull Handler handler, @NonNull com.inmobi.ads.c.k kVar, @NonNull a aVar) {
        this.a = map;
        this.d = map2;
        this.c = ceVar;
        this.g = kVar.d;
        this.h = new ce.c() { // from class: com.inmobi.ads.y.1
            @Override // com.inmobi.ads.ce.c
            public final void a(@NonNull List<View> list, @NonNull List<View> list2) {
                for (View view : list) {
                    b bVar = (b) y.this.a.get(view);
                    if (bVar == null) {
                        y.this.a(view);
                    } else {
                        b bVar2 = (b) y.this.d.get(view);
                        if (bVar2 == null || !bVar.a.equals(bVar2.a)) {
                            bVar.d = SystemClock.uptimeMillis();
                            y.this.d.put(view, bVar);
                        }
                    }
                }
                Iterator<View> it = list2.iterator();
                while (it.hasNext()) {
                    y.this.d.remove(it.next());
                }
                y.this.d();
            }
        };
        this.c.c = this.h;
        this.e = handler;
        this.f = new c(this);
        this.i = aVar;
    }

    final void a(View view, @NonNull Object obj, int i, int i2) {
        b bVar = this.a.get(view);
        if (bVar == null || !bVar.a.equals(obj)) {
            a(view);
            b bVar2 = new b(obj, i, i2);
            this.a.put(view, bVar2);
            this.c.a(view, obj, bVar2.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view) {
        this.a.remove(view);
        this.d.remove(view);
        this.c.a(view);
    }

    final View a(@NonNull Object obj) {
        View key;
        Iterator<Map.Entry<View, b>> it = this.a.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                key = null;
                break;
            }
            Map.Entry<View, b> next = it.next();
            if (next.getValue().a.equals(obj)) {
                key = next.getKey();
                break;
            }
        }
        if (key != null) {
            a(key);
        }
        return key;
    }

    final void a() {
        this.c.f();
        this.e.removeCallbacksAndMessages(null);
        this.d.clear();
    }

    final void b() {
        for (Map.Entry<View, b> entry : this.a.entrySet()) {
            this.c.a(entry.getKey(), entry.getValue().a, entry.getValue().b);
        }
        d();
        this.c.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.e.hasMessages(0)) {
            return;
        }
        this.e.postDelayed(this.f, this.g);
    }

    /* JADX INFO: compiled from: ImpressionTracker.java */
    static class c implements Runnable {

        @NonNull
        private final ArrayList<View> a = new ArrayList<>();
        private WeakReference<y> b;

        c(y yVar) {
            this.b = new WeakReference<>(yVar);
        }

        @Override // java.lang.Runnable
        public final void run() {
            y yVar = this.b.get();
            if (yVar != null) {
                for (Map.Entry entry : yVar.d.entrySet()) {
                    View view = (View) entry.getKey();
                    b bVar = (b) entry.getValue();
                    if (y.a(bVar.d, bVar.c) && this.b.get() != null) {
                        yVar.i.a(view, bVar.a);
                        this.a.add(view);
                    }
                }
                Iterator<View> it = this.a.iterator();
                while (it.hasNext()) {
                    yVar.a(it.next());
                }
                this.a.clear();
                if (yVar.d.isEmpty()) {
                    return;
                }
                yVar.d();
            }
        }
    }

    final void c() {
        this.a.clear();
        this.d.clear();
        this.c.f();
        this.e.removeMessages(0);
        this.c.e();
        this.h = null;
    }

    static /* synthetic */ boolean a(long j, int i) {
        return SystemClock.uptimeMillis() - j >= ((long) i);
    }
}
