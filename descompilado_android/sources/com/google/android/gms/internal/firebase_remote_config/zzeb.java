package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: loaded from: classes.dex */
public final class zzeb {

    @NullableDecl
    private static final Object zzir;

    @NullableDecl
    private static final Method zzis;

    @NullableDecl
    private static final Method zzit;

    @Deprecated
    public static RuntimeException zzb(Throwable th) {
        zzdt.checkNotNull(th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new RuntimeException(th);
    }

    @NullableDecl
    private static Object zzck() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @NullableDecl
    private static Method zzcl() {
        try {
            Method methodZza = zza("getStackTraceDepth", Throwable.class);
            if (methodZza == null) {
                return null;
            }
            methodZza.invoke(zzck(), new Throwable());
            return methodZza;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    @NullableDecl
    private static Method zza(String str, Class<?>... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        Object objZzck = zzck();
        zzir = objZzck;
        zzis = objZzck == null ? null : zza("getStackTraceElement", Throwable.class, Integer.TYPE);
        zzit = zzir != null ? zzcl() : null;
    }
}
