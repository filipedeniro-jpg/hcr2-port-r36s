package com.inmobi.commons.core.utilities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ApplicationFocusChangeObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String a = "a";
    private static Object c;
    private static volatile a g;
    private static List<b> b = new ArrayList();
    private static boolean d = false;
    private static HandlerThread e = null;
    private static final Object f = new Object();

    /* JADX INFO: compiled from: ApplicationFocusChangeObserver.java */
    public interface b {
        void a(boolean z);
    }

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
    }

    public final void a(b bVar) {
        b.add(bVar);
        if (b.size() == 1 && com.inmobi.commons.a.a.a()) {
            HandlerThread handlerThread = new HandlerThread("ApplicationFocusChangeObserverHandler");
            e = handlerThread;
            handlerThread.start();
            Class<?> cls = null;
            for (Class<?> cls2 : Application.class.getDeclaredClasses()) {
                if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                    new Class[1][0] = cls2;
                    cls = cls2;
                }
            }
            c = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.inmobi.commons.core.utilities.a.1
                private final Handler b = new HandlerC0078a(a.e.getLooper());
                private WeakReference<Activity> c;

                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (objArr == null) {
                        return null;
                    }
                    String name = method.getName();
                    byte b2 = -1;
                    int iHashCode = name.hashCode();
                    if (iHashCode != 195654633) {
                        if (iHashCode != 1495889555) {
                            if (iHashCode == 1508755423 && name.equals("onActivityStopped")) {
                                b2 = 2;
                            }
                        } else if (name.equals("onActivityStarted")) {
                            b2 = 0;
                        }
                    } else if (name.equals("onActivityResumed")) {
                        b2 = 1;
                    }
                    if (b2 == 0 || b2 == 1) {
                        Activity activity = (Activity) objArr[0];
                        WeakReference<Activity> weakReference = this.c;
                        if (weakReference == null || weakReference.get() != activity) {
                            this.c = new WeakReference<>(activity);
                        }
                        this.b.removeMessages(1001);
                        this.b.sendEmptyMessage(1002);
                        return null;
                    }
                    if (b2 != 2) {
                        return null;
                    }
                    Activity activity2 = (Activity) objArr[0];
                    WeakReference<Activity> weakReference2 = this.c;
                    if (weakReference2 == null || weakReference2.get() != activity2) {
                        return null;
                    }
                    this.b.sendEmptyMessageDelayed(1001, 3000L);
                    return null;
                }
            });
            Application application = (Application) com.inmobi.commons.a.a.b();
            if (c != null) {
                try {
                    Application.class.getMethod("registerActivityLifecycleCallbacks", cls).invoke(application, c);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                } catch (Exception e2) {
                    try {
                        HashMap map = new HashMap();
                        map.put("type", "GenericException");
                        map.put("message", e2.getMessage());
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", map);
                    } catch (Exception unused2) {
                        StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                        sb.append(e2.getMessage());
                        sb.append(")");
                    }
                }
            }
        }
    }

    public static void b() {
        d = true;
    }

    public static void c() {
        d = false;
    }

    /* JADX INFO: renamed from: com.inmobi.commons.core.utilities.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ApplicationFocusChangeObserver.java */
    static class HandlerC0078a extends Handler {
        boolean a;

        HandlerC0078a(Looper looper) {
            super(looper);
            this.a = true;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (a.d) {
                return;
            }
            if (message.what == 1001 && this.a) {
                this.a = false;
                a.a(false);
                String unused = a.a;
            } else {
                if (message.what != 1002 || this.a) {
                    return;
                }
                this.a = true;
                a.a(true);
                String unused2 = a.a;
            }
        }
    }

    static /* synthetic */ void a(final boolean z) {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return;
        }
        new Handler(contextB.getMainLooper()).post(new Runnable() { // from class: com.inmobi.commons.core.utilities.a.2
            @Override // java.lang.Runnable
            public final void run() {
                Iterator it = a.b.iterator();
                while (it.hasNext()) {
                    try {
                        ((b) it.next()).a(z);
                    } catch (Exception e2) {
                        String unused = a.a;
                        new StringBuilder("SDK encountered an unexpected error in handling focus change event; ").append(e2.getMessage());
                    }
                }
            }
        });
    }
}
