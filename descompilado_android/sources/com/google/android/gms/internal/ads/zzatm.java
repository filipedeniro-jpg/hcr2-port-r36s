package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.ads.AdRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatm extends zzawo {
    public static void zzdy(String str) {
        if (zzuq()) {
            Log.v(AdRequest.LOGTAG, str);
        }
    }

    public static void zza(String str, Throwable th) {
        if (zzuq()) {
            Log.v(AdRequest.LOGTAG, str, th);
        }
    }

    public static boolean zzuq() {
        if (isLoggable(2)) {
            return ((Boolean) zzuo.zzoj().zzd(zzyt.zzclp)).booleanValue();
        }
        return false;
    }
}
