package com.moat.analytics.mobile.iro;

import androidx.annotation.VisibleForTesting;
import com.moat.analytics.mobile.iro.base.asserts.Asserts;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class s<T> implements InvocationHandler {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static final Object[] f644 = new Object[0];

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final LinkedList<s<T>.e> f645;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private final Class<T> f646;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean f647;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final a<T> f648;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private T f649;

    interface a<T> {
        /* JADX INFO: renamed from: ˏ */
        Optional<T> mo448() throws o;
    }

    class e {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private final Method f651;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private final LinkedList<Object> f652;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final WeakReference[] f653;

        /* synthetic */ e(s sVar, Method method, Object[] objArr, byte b) {
            this(method, objArr);
        }

        private e(Method method, Object... objArr) {
            this.f652 = new LinkedList<>();
            objArr = objArr == null ? s.f644 : objArr;
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f652.add(obj);
                }
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2++;
            }
            this.f653 = weakReferenceArr;
            this.f651 = method;
        }
    }

    @VisibleForTesting
    private s(a<T> aVar, Class<T> cls) throws o {
        Asserts.checkNotNull(aVar);
        Asserts.checkNotNull(cls);
        this.f648 = aVar;
        this.f646 = cls;
        this.f645 = new LinkedList<>();
        t.m598().m608(new t.a() { // from class: com.moat.analytics.mobile.iro.s.1
            @Override // com.moat.analytics.mobile.iro.t.a
            /* JADX INFO: renamed from: ॱ */
            public final void mo544() throws o {
                s.this.m591();
            }
        });
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static <T> T m589(a<T> aVar, Class<T> cls) throws o {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new s(aVar, cls));
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static Boolean m588(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e2) {
            o.m569(e2);
            return null;
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            Class<?> declaringClass = method.getDeclaringClass();
            t tVarM598 = t.m598();
            if (Object.class.equals(declaringClass)) {
                String name = method.getName();
                if ("getClass".equals(name)) {
                    return this.f646;
                }
                if ("toString".equals(name)) {
                    Object objInvoke = method.invoke(this, objArr);
                    return String.valueOf(objInvoke).replace(s.class.getName(), this.f646.getName());
                }
                return method.invoke(this, objArr);
            }
            if (this.f647 && this.f649 == null) {
                this.f645.clear();
                return m588(method);
            }
            if (tVarM598.f660 == t.c.f677) {
                m591();
                if (this.f649 != null) {
                    return method.invoke(this.f649, objArr);
                }
            }
            if (tVarM598.f660 == t.c.f676 && (!this.f647 || this.f649 != null)) {
                if (this.f645.size() >= 15) {
                    this.f645.remove(5);
                }
                this.f645.add(new e(this, method, objArr, (byte) 0));
            }
            return m588(method);
        } catch (Exception e2) {
            o.m569(e2);
            return m588(method);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public void m591() throws o {
        if (!this.f647) {
            try {
                this.f649 = this.f648.mo448().orElse(null);
            } catch (Exception e2) {
                b.m457("OnOffTrackerProxy", this, "Could not create instance", e2);
                o.m569(e2);
            }
            this.f647 = true;
        }
        if (this.f649 == null) {
            return;
        }
        for (s<T>.e eVar : this.f645) {
            try {
                Object[] objArr = new Object[((e) eVar).f653.length];
                WeakReference[] weakReferenceArr = ((e) eVar).f653;
                int length = weakReferenceArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    objArr[i2] = weakReferenceArr[i].get();
                    i++;
                    i2++;
                }
                ((e) eVar).f651.invoke(this.f649, objArr);
            } catch (Exception e3) {
                o.m569(e3);
            }
        }
        this.f645.clear();
    }
}
