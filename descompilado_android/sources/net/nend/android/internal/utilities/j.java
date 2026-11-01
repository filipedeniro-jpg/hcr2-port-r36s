package net.nend.android.internal.utilities;

import android.util.Log;
import net.nend.android.NendAdLogger;

/* JADX INFO: compiled from: NendLog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j {
    private static boolean a(NendAdLogger.LogLevel logLevel) {
        return logLevel.getInt() >= NendAdLogger.getLogLevel().getInt();
    }

    private static void a(int i, String str, Throwable th) {
        if (i == 2 || i == 3) {
            a(str, th, NendAdLogger.LogLevel.DEBUG);
            return;
        }
        if (i == 4) {
            a(str, th, NendAdLogger.LogLevel.INFO);
        } else if (i == 5) {
            a(str, th, NendAdLogger.LogLevel.WARN);
        } else {
            if (i != 6) {
                return;
            }
            a(str, th, NendAdLogger.LogLevel.ERROR);
        }
    }

    private static void a(String str, Throwable th, NendAdLogger.LogLevel logLevel) {
        if (a(logLevel)) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
            String str2 = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":\n" + str;
            if (th != null) {
                str2 = str2 + '\n' + Log.getStackTraceString(th);
            }
            if (NendAdLogger.sharedInstance().logger != null) {
                NendAdLogger.sharedInstance().logger.logMessage(str2, logLevel);
            }
        }
    }

    public static void a(String str) {
        a(3, str, (Throwable) null);
    }

    public static void a(k kVar, Throwable th) {
        a(3, kVar.b(), th);
    }

    public static void b(String str) {
        a(4, str, (Throwable) null);
    }

    public static void c(String str) {
        a(5, str, (Throwable) null);
    }

    public static void a(String str, Throwable th) {
        a(5, str, th);
    }

    public static void a(k kVar) {
        a(5, kVar.b(), (Throwable) null);
    }

    public static void b(k kVar, Throwable th) {
        a(5, kVar.b(), th);
    }

    public static void d(String str) {
        a(6, str, (Throwable) null);
    }

    public static void b(String str, Throwable th) {
        a(6, str, th);
    }

    public static void a(k kVar, String str) {
        a(6, kVar.a(str), (Throwable) null);
    }

    public static void c(k kVar, Throwable th) {
        a(6, kVar.b(), th);
    }
}
