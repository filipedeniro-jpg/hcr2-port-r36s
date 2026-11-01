package com.moat.analytics.mobile.cha;

import androidx.annotation.VisibleForTesting;
import com.moat.analytics.mobile.cha.base.asserts.Asserts;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class p<T> implements InvocationHandler {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static final Object[] f403 = new Object[0];

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private final c<T> f404;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private boolean f405;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private final LinkedList<p<T>.d> f406;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final Class<T> f407;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private T f408;

    interface c<T> {
        /* JADX INFO: renamed from: ˋ */
        Optional<T> mo269() throws o;
    }

    class d {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private final WeakReference[] f410;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private final Method f411;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final LinkedList<Object> f412;

        /* synthetic */ d(p pVar, Method method, Object[] objArr, byte b) {
            this(method, objArr);
        }

        private d(Method method, Object... objArr) {
            this.f412 = new LinkedList<>();
            objArr = objArr == null ? p.f403 : objArr;
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f412.add(obj);
                }
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2++;
            }
            this.f410 = weakReferenceArr;
            this.f411 = method;
        }
    }

    @VisibleForTesting
    private p(c<T> cVar, Class<T> cls) throws o {
        Asserts.checkNotNull(cVar);
        Asserts.checkNotNull(cls);
        this.f404 = cVar;
        this.f407 = cls;
        this.f406 = new LinkedList<>();
        t.m423().m428(new t.b() { // from class: com.moat.analytics.mobile.cha.p.1
            @Override // com.moat.analytics.mobile.cha.t.b
            /* JADX INFO: renamed from: ˎ */
            public final void mo312() throws o {
                p.this.m393();
            }
        });
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static <T> T m392(c<T> cVar, Class<T> cls) throws o {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new p(cVar, cls));
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static Boolean m391(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e) {
            o.m389(e);
            return null;
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Class<?> declaringClass = method.getDeclaringClass();
            t tVarM423 = t.m423();
            if (Object.class.equals(declaringClass)) {
                String name = method.getName();
                if ("getClass".equals(name)) {
                    return this.f407;
                }
                if ("toString".equals(name)) {
                    Object objInvoke = method.invoke(this, objArr);
                    return String.valueOf(objInvoke).replace(p.class.getName(), this.f407.getName());
                }
                return method.invoke(this, objArr);
            }
            if (this.f405 && this.f408 == null) {
                this.f406.clear();
                return m391(method);
            }
            if (tVarM423.f441 == t.a.f452) {
                m393();
                if (this.f408 != null) {
                    return method.invoke(this.f408, objArr);
                }
            }
            if (tVarM423.f441 == t.a.f453 && (!this.f405 || this.f408 != null)) {
                if (this.f406.size() >= 15) {
                    this.f406.remove(5);
                }
                this.f406.add(new d(this, method, objArr, (byte) 0));
            }
            return m391(method);
        } catch (Exception e) {
            o.m389(e);
            return m391(method);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    public void m393() throws o {
        if (!this.f405) {
            try {
                this.f408 = this.f404.mo269().orElse(null);
            } catch (Exception e) {
                a.m275("OnOffTrackerProxy", this, "Could not create instance", e);
                o.m389(e);
            }
            this.f405 = true;
        }
        if (this.f408 == null) {
            return;
        }
        for (p<T>.d dVar : this.f406) {
            try {
                Object[] objArr = new Object[((d) dVar).f410.length];
                WeakReference[] weakReferenceArr = ((d) dVar).f410;
                int length = weakReferenceArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    objArr[i2] = weakReferenceArr[i].get();
                    i++;
                    i2++;
                }
                ((d) dVar).f411.invoke(this.f408, objArr);
            } catch (Exception e2) {
                o.m389(e2);
            }
        }
        this.f406.clear();
    }
}
