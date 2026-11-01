package com.my.target;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.my.target.al;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c<T extends al> {

    @NonNull
    protected final com.my.target.b adConfig;

    @NonNull
    protected final a<T> n;

    @Nullable
    protected String o;

    @Nullable
    private b<T> p;

    /* JADX INFO: compiled from: AdFactory.java */
    public interface a<T extends al> {
        boolean a();

        @NonNull
        d<T> b();

        @Nullable
        e<T> c();

        @NonNull
        f d();
    }

    /* JADX INFO: compiled from: AdFactory.java */
    public interface b<T extends al> {
        void b(@Nullable T t, @Nullable String str);
    }

    public c(@NonNull a<T> aVar, @NonNull com.my.target.b bVar) {
        this.n = aVar;
        this.adConfig = bVar;
    }

    @NonNull
    @AnyThread
    public final c<T> a(@NonNull b<T> bVar) {
        this.p = bVar;
        return this;
    }

    @NonNull
    @AnyThread
    public c<T> a(@NonNull Context context) {
        final Context applicationContext = context.getApplicationContext();
        h.a(new Runnable() { // from class: com.my.target.c.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                al alVarC = c.this.c(applicationContext);
                c cVar = c.this;
                cVar.a(alVarC, cVar.o);
            }
        });
        return this;
    }

    @Nullable
    @WorkerThread
    public final T b(@NonNull Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            g.b("[AdFactory] method getAdSync called from main thread");
            return null;
        }
        return (T) c(context.getApplicationContext());
    }

    @Nullable
    protected T c(@NonNull Context context) {
        af afVarA = this.n.d().a(this.adConfig, context);
        at atVarAh = at.ah();
        String strA = a(afVarA, atVarAh, context);
        if (strA == null) {
            return null;
        }
        d<T> dVarB = this.n.b();
        al alVarA = dVarB.a(strA, afVarA, null, this.adConfig, context);
        if (this.n.a()) {
            alVarA = a(afVarA.E(), alVarA, dVarB, atVarAh, context);
        }
        return (T) a(alVarA, context);
    }

    @Nullable
    protected T a(@Nullable T t, @NonNull Context context) {
        e<T> eVarC;
        return (t == null || (eVarC = this.n.c()) == null) ? t : (T) eVarC.a(t, this.adConfig, context);
    }

    @Nullable
    protected String a(@NonNull af afVar, @NonNull at atVar, @NonNull Context context) {
        atVar.f(afVar.getUrl(), context);
        if (atVar.al()) {
            return atVar.am();
        }
        this.o = atVar.j();
        return null;
    }

    @Nullable
    protected T a(@NonNull List<af> list, @Nullable T t, @NonNull d<T> dVar, @NonNull at atVar, @NonNull Context context) {
        if (list.size() <= 0) {
            return t;
        }
        Iterator<af> it = list.iterator();
        al alVarA = t;
        while (it.hasNext()) {
            alVarA = a(it.next(), alVarA, dVar, atVar, context);
        }
        return (T) alVarA;
    }

    @Nullable
    protected T a(@NonNull af afVar, @Nullable T t, @NonNull d<T> dVar, @NonNull at atVar, @NonNull Context context) {
        T t2;
        atVar.f(afVar.getUrl(), context);
        if (!atVar.al()) {
            return t;
        }
        cj.a(afVar.o(aq.a.dO), context);
        int bannersCount = t != null ? t.getBannersCount() : 0;
        String strAm = atVar.am();
        if (strAm != null) {
            t2 = (T) a(afVar.E(), dVar.a(strAm, afVar, t, this.adConfig, context), dVar, atVar, context);
        } else {
            t2 = t;
        }
        if (bannersCount != (t2 != null ? t2.getBannersCount() : 0)) {
            return t2;
        }
        cj.a(afVar.o(aq.a.dP), context);
        af afVarC = afVar.C();
        return afVarC != null ? (T) a(afVarC, t2, dVar, atVar, context) : t2;
    }

    protected void a(@Nullable final T t, @Nullable final String str) {
        if (this.p == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.p.b(t, str);
            this.p = null;
        } else {
            h.c(new Runnable() { // from class: com.my.target.c.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.p != null) {
                        c.this.p.b(t, str);
                        c.this.p = null;
                    }
                }
            });
        }
    }
}
