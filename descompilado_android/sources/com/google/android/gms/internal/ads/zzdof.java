package com.google.android.gms.internal.ads;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdof {
    private static final zzdoe zzhet;
    private static final int zzheu;

    public static void zza(Throwable th, Throwable th2) {
        zzhet.zza(th, th2);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zza extends zzdoe {
        zza() {
        }

        @Override // com.google.android.gms.internal.ads.zzdoe
        public final void zza(Throwable th, Throwable th2) {
        }

        @Override // com.google.android.gms.internal.ads.zzdoe
        public final void zzj(Throwable th) {
            th.printStackTrace();
        }

        @Override // com.google.android.gms.internal.ads.zzdoe
        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    public static void zzj(Throwable th) {
        zzhet.zzj(th);
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzhet.zza(th, printWriter);
    }

    private static Integer zzawz() {
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
        Integer numZzawz;
        zzdoe zzaVar;
        try {
            numZzawz = zzawz();
            if (numZzawz != null) {
                try {
                    if (numZzawz.intValue() >= 19) {
                        zzaVar = new zzdok();
                    } else if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
                        zzaVar = new zzdoi();
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
                zzaVar = new zzdoi();
            } else {
                zzaVar = new zza();
            }
        } catch (Throwable th2) {
            th = th2;
            numZzawz = null;
        }
        zzhet = zzaVar;
        zzheu = numZzawz != null ? numZzawz.intValue() : 1;
    }
}
