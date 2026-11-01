package com.google.android.gms.internal.ads;

import android.os.Trace;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzoe {
    public static void beginSection(String str) {
        if (zzoh.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endSection() {
        if (zzoh.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
}
