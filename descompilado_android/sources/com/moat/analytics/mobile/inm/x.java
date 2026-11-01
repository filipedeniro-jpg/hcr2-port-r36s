package com.moat.analytics.mobile.inm;

import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class x<T> implements InvocationHandler {
    private static final Object[] a = new Object[0];
    private final a<T> b;
    private final Class<T> c;
    private final LinkedList<x<T>.b> d;
    private boolean e;
    private T f;

    interface a<T> {
        com.moat.analytics.mobile.inm.a.b.a<T> a();
    }

    private class b {
        private final WeakReference[] b;
        private final LinkedList<Object> c;
        private final Method d;

        private b(Method method, Object... objArr) {
            this.c = new LinkedList<>();
            objArr = objArr == null ? x.a : objArr;
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.c.add(obj);
                }
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2++;
            }
            this.b = weakReferenceArr;
            this.d = method;
        }
    }

    @VisibleForTesting
    x(a<T> aVar, Class<T> cls) {
        com.moat.analytics.mobile.inm.a.a.a.a(aVar);
        com.moat.analytics.mobile.inm.a.a.a.a(cls);
        this.b = aVar;
        this.c = cls;
        this.d = new LinkedList<>();
        w.a().a(new w.b() { // from class: com.moat.analytics.mobile.inm.x.1
            @Override // com.moat.analytics.mobile.inm.w.b
            public void c() {
                x.this.c();
            }

            @Override // com.moat.analytics.mobile.inm.w.b
            public void d() {
            }
        });
    }

    static <T> T a(a<T> aVar, Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new x(aVar, cls));
    }

    private Object a(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e) {
            m.a(e);
            return null;
        }
    }

    private Object a(Method method, Object[] objArr) throws IllegalAccessException, InvocationTargetException {
        Class<?> declaringClass = method.getDeclaringClass();
        w wVarA = w.a();
        if (Object.class.equals(declaringClass)) {
            String name = method.getName();
            if ("getClass".equals(name)) {
                return this.c;
            }
            if (!"toString".equals(name)) {
                return method.invoke(this, objArr);
            }
            Object objInvoke = method.invoke(this, objArr);
            return String.valueOf(objInvoke).replace(x.class.getName(), this.c.getName());
        }
        if (this.e && this.f == null) {
            this.d.clear();
            return a(method);
        }
        if (wVarA.a == w.d.ON) {
            c();
            T t = this.f;
            if (t != null) {
                return method.invoke(t, objArr);
            }
        }
        if (wVarA.a == w.d.OFF && (!this.e || this.f != null)) {
            b(method, objArr);
        }
        return a(method);
    }

    private void b() {
        if (this.e) {
            return;
        }
        try {
            this.f = this.b.a().c(null);
        } catch (Exception e) {
            p.a("OnOffTrackerProxy", this, "Could not create instance", e);
            m.a(e);
        }
        this.e = true;
    }

    private void b(Method method, Object[] objArr) {
        if (this.d.size() >= 15) {
            this.d.remove(5);
        }
        this.d.add(new b(method, objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        b();
        if (this.f == null) {
            return;
        }
        for (x<T>.b bVar : this.d) {
            try {
                Object[] objArr = new Object[((b) bVar).b.length];
                WeakReference[] weakReferenceArr = ((b) bVar).b;
                int length = weakReferenceArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    objArr[i2] = weakReferenceArr[i].get();
                    i++;
                    i2++;
                }
                ((b) bVar).d.invoke(this.f, objArr);
            } catch (Exception e) {
                m.a(e);
            }
        }
        this.d.clear();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return a(method, objArr);
        } catch (Exception e) {
            m.a(e);
            return a(method);
        }
    }
}
