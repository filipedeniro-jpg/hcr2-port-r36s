package com.google.android.gms.internal.firebase_remote_config;

import java.io.PrintStream;

/* JADX INFO: loaded from: classes2.dex */
public final class zzea {
    private static final zzed zzip;
    private static final int zziq;

    public static void zza(Throwable th, Throwable th2) {
        zzip.zza(th, th2);
    }

    static final class zza extends zzed {
        zza() {
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzed
        public final void zza(Throwable th, Throwable th2) {
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzed
        public final void zza(Throwable th) {
            th.printStackTrace();
        }
    }

    public static void zza(Throwable th) {
        zzip.zza(th);
    }

    private static Integer zzcj() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001e A[Catch: Throwable -> 0x002a, TryCatch #0 {Throwable -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:11:0x0024 A[Catch: Throwable -> 0x002a, TRY_LEAVE, TryCatch #0 {Throwable -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:8:0x0015 A[Catch: Throwable -> 0x002a, TryCatch #0 {Throwable -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    static {
        Integer numZzcj;
        zzed zzaVar;
        try {
            numZzcj = zzcj();
            if (numZzcj != null) {
                try {
                    if (numZzcj.intValue() >= 19) {
                        zzaVar = new zzeg();
                    } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                        zzaVar = new zzee();
                    } else {
                        zzaVar = new zza();
                    }
                } catch (Throwable th) {
                    th = th;
                    PrintStream printStream = System.err;
                    String name = zza.class.getName();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 133);
                    sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
                    sb.append(name);
                    sb.append("will be used. The error is: ");
                    printStream.println(sb.toString());
                    th.printStackTrace(System.err);
                    zzaVar = new zza();
                }
            } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                zzaVar = new zzee();
            } else {
                zzaVar = new zza();
            }
        } catch (Throwable th2) {
            th = th2;
            numZzcj = null;
        }
        zzip = zzaVar;
        zziq = numZzcj != null ? numZzcj.intValue() : 1;
    }
}
