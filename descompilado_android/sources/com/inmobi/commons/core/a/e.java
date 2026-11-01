package com.inmobi.commons.core.a;

/* JADX INFO: compiled from: InMobiCrashHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class e implements Thread.UncaughtExceptionHandler {
    private static final String b = "e";
    private Thread.UncaughtExceptionHandler a;

    e(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        boolean z = false;
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (stackTraceElement.getClassName().contains("com.inmobi.")) {
                z = true;
                break;
            }
        }
        if (z) {
            try {
                a.a().a(new d(thread, th));
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(th.getMessage());
                sb.append(")");
            }
        }
        this.a.uncaughtException(thread, th);
    }
}
