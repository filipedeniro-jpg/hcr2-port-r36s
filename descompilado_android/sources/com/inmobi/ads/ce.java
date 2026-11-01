package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: VisibilityTracker.java */
/* JADX INFO: loaded from: classes2.dex */
abstract class ce {
    private static final String d = "ce";
    boolean a;

    @NonNull
    final Map<View, d> b;

    @Nullable
    c c;

    @NonNull
    private final ArrayList<View> e;
    private long f;

    @NonNull
    private final a g;

    @NonNull
    private final b h;

    @NonNull
    private final Handler i;
    private boolean j;

    /* JADX INFO: compiled from: VisibilityTracker.java */
    interface a {
        boolean a(@Nullable View view, @Nullable View view2, int i, Object obj);
    }

    /* JADX INFO: compiled from: VisibilityTracker.java */
    interface c {
        void a(List<View> list, List<View> list2);
    }

    protected abstract int a();

    protected abstract void b();

    static /* synthetic */ boolean a(ce ceVar) {
        ceVar.j = false;
        return false;
    }

    /* JADX INFO: compiled from: VisibilityTracker.java */
    static class d {
        int a;
        long b;
        View c;
        Object d;

        d() {
        }
    }

    ce(a aVar) {
        this(new WeakHashMap(10), aVar, new Handler(Looper.getMainLooper()));
    }

    private ce(@NonNull Map<View, d> map, @NonNull a aVar, @NonNull Handler handler) {
        this.f = 0L;
        this.a = true;
        this.b = map;
        this.g = aVar;
        this.i = handler;
        this.h = new b(this);
        this.e = new ArrayList<>(50);
    }

    public void c() {
        this.h.run();
        this.i.removeCallbacksAndMessages(null);
        this.j = false;
        this.a = true;
    }

    public void d() {
        this.a = false;
        g();
    }

    protected final void a(@NonNull View view) {
        if (this.b.remove(view) != null) {
            this.f--;
            if (this.b.size() == 0) {
                c();
            }
        }
    }

    protected final void f() {
        this.b.clear();
        this.i.removeMessages(0);
        this.j = false;
    }

    final View a(@Nullable Object obj) {
        View key = null;
        if (obj == null) {
            return null;
        }
        for (Map.Entry<View, d> entry : this.b.entrySet()) {
            if (entry.getValue().d.equals(obj)) {
                key = entry.getKey();
                break;
            }
        }
        if (key != null) {
            a(key);
        }
        return key;
    }

    protected void e() {
        f();
        this.c = null;
        this.a = true;
    }

    final void g() {
        if (this.j || this.a) {
            return;
        }
        this.j = true;
        this.i.postDelayed(this.h, a());
    }

    /* JADX INFO: compiled from: VisibilityTracker.java */
    static class b implements Runnable {
        private WeakReference<ce> c;

        @NonNull
        private final ArrayList<View> b = new ArrayList<>();

        @NonNull
        private final ArrayList<View> a = new ArrayList<>();

        b(ce ceVar) {
            this.c = new WeakReference<>(ceVar);
        }

        @Override // java.lang.Runnable
        public final void run() {
            c cVar;
            ce ceVar = this.c.get();
            if (ceVar != null) {
                ce.a(ceVar);
                for (Map.Entry entry : ceVar.b.entrySet()) {
                    View view = (View) entry.getKey();
                    if (ceVar.g.a(((d) entry.getValue()).c, view, ((d) entry.getValue()).a, ((d) entry.getValue()).d)) {
                        this.a.add(view);
                    } else {
                        this.b.add(view);
                    }
                }
            }
            if (ceVar != null && (cVar = ceVar.c) != null) {
                cVar.a(this.a, this.b);
            }
            this.a.clear();
            this.b.clear();
            if (ceVar != null) {
                ceVar.b();
            }
        }
    }

    protected final void a(@NonNull View view, @Nullable Object obj, int i) {
        d dVar = this.b.get(view);
        if (dVar == null) {
            dVar = new d();
            this.b.put(view, dVar);
            this.f++;
        }
        dVar.a = i;
        long j = this.f;
        dVar.b = j;
        dVar.c = view;
        dVar.d = obj;
        if (j % 50 == 0) {
            long j2 = j - 50;
            for (Map.Entry<View, d> entry : this.b.entrySet()) {
                if (entry.getValue().b < j2) {
                    this.e.add(entry.getKey());
                }
            }
            Iterator<View> it = this.e.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            this.e.clear();
        }
        if (1 == this.b.size()) {
            d();
        }
    }
}
