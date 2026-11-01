package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzel {
    private final int zza;
    private final int zzb = -1;

    private zzel(String str, int i) {
        this.zza = zzc(str);
    }

    public final boolean zza() {
        return this.zza >= zzc("16.2.1");
    }

    public final boolean zza(String str) {
        return this.zza >= zzc(str);
    }

    public static String zzb() {
        return zzb("firebase-auth");
    }

    private static String zzb(String str) {
        String version = LibraryVersion.getInstance().getVersion(str);
        return (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) ? "-1" : version;
    }

    public static zzel zzc() {
        return new zzel(zzb("firebase-auth-impl"), -1);
    }

    private static int zzc(String str) {
        List<String> listZza = com.google.android.gms.internal.firebase_auth.zzan.zza(".").zza((CharSequence) str);
        if (listZza.size() == 1) {
            return Integer.parseInt(str);
        }
        if (listZza.size() == 3) {
            return (Integer.parseInt(listZza.get(0)) * 1000000) + (Integer.parseInt(listZza.get(1)) * 1000) + Integer.parseInt(listZza.get(2));
        }
        String strConcat = "";
        for (String str2 : listZza) {
            String strValueOf = String.valueOf(strConcat);
            String strValueOf2 = String.valueOf(str2);
            strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        return Integer.parseInt(strConcat);
    }
}
