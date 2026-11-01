package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzano implements Thread.UncaughtExceptionHandler {
    private final /* synthetic */ Thread.UncaughtExceptionHandler zzdie;
    private final /* synthetic */ zzann zzdif;

    zzano(zzann zzannVar, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdif = zzannVar;
        this.zzdie = uncaughtExceptionHandler;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzdif.zza(thread, th);
        } catch (Throwable unused) {
            zzawo.zzes("AdMob exception reporter failed reporting the exception.");
        } finally {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzdie;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }
}
