package com.facebook.ads.redexgen.X;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IF {
    private IF() {
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001e A[Catch: Exception -> 0x0026, TRY_LEAVE, TryCatch #0 {Exception -> 0x0026, blocks: (B:6:0x0007, B:8:0x0018, B:10:0x001e), top: B:15:0x0007 }] */
    public static void B(Context appContext) {
        Exception e = null;
        boolean zCreateNewFile = true;
        if (appContext == null) {
            zCreateNewFile = false;
        }
        if (zCreateNewFile) {
            try {
                File file = new File(appContext.getFilesDir(), "com.facebook.ads.ipc");
                if (!file.exists()) {
                    zCreateNewFile = file.createNewFile();
                }
                if (!zCreateNewFile) {
                    e = new Exception("Can't create ipc marker.");
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else if (!zCreateNewFile) {
            e = new Exception("Can't create ipc marker.");
        }
        if (e != null) {
            C0585Hx.F(appContext, "ipc", C0584Hw.WB, new C0583Hv(e));
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001e A[Catch: Exception -> 0x0026, TRY_LEAVE, TryCatch #0 {Exception -> 0x0026, blocks: (B:6:0x0007, B:8:0x0018, B:10:0x001e), top: B:15:0x0007 }] */
    public static void C(Context appContext) {
        Exception e = null;
        boolean zDelete = true;
        if (appContext == null) {
            zDelete = false;
        }
        if (zDelete) {
            try {
                File file = new File(appContext.getFilesDir(), "com.facebook.ads.ipc");
                if (file.exists()) {
                    zDelete = file.delete();
                }
                if (!zDelete) {
                    e = new Exception("Can't delete ipc marker.");
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else if (!zDelete) {
            e = new Exception("Can't delete ipc marker.");
        }
        if (e != null) {
            C0585Hx.F(appContext, "ipc", C0584Hw.WB, new C0583Hv(e));
        }
    }

    public static boolean D(Context appContext) {
        try {
            File markerFile = new File(appContext.getFilesDir(), "com.facebook.ads.ipc");
            return markerFile.exists();
        } catch (Exception e) {
            C0585Hx.F(appContext, "ipc", C0584Hw.WB, new C0583Hv(e));
            return false;
        }
    }
}
