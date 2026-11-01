package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdac {

    @NullableDecl
    private static final Object zzgoh;

    @NullableDecl
    private static final Method zzgoi;

    @NullableDecl
    private static final Method zzgoj;

    public static void zzf(Throwable th) {
        zzczx.checkNotNull(th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    @Deprecated
    public static RuntimeException zzg(Throwable th) {
        zzf(th);
        throw new RuntimeException(th);
    }

    @NullableDecl
    private static Object zzaob() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @NullableDecl
    private static Method zzaoc() {
        try {
            Method methodZza = zza("getStackTraceDepth", Throwable.class);
            if (methodZza == null) {
                return null;
            }
            methodZza.invoke(zzaob(), new Throwable());
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
        Object objZzaob = zzaob();
        zzgoh = objZzaob;
        zzgoi = objZzaob == null ? null : zza("getStackTraceElement", Throwable.class, Integer.TYPE);
        zzgoj = zzgoh != null ? zzaoc() : null;
    }
}
